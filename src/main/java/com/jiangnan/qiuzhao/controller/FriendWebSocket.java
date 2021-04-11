package com.jiangnan.qiuzhao.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.jiangnan.qiuzhao.config.HttpSessionConfigurator;
import com.jiangnan.qiuzhao.entity.Friends;
import com.jiangnan.qiuzhao.entity.User;
import com.jiangnan.qiuzhao.service.FriendsService;
import com.jiangnan.qiuzhao.service.Impl.ActiveMqProduct;
import com.jiangnan.qiuzhao.service.Impl.FrinendsServiceImpl;
import com.jiangnan.qiuzhao.utils.ApplicationContextutils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/friend/{friendId}",configurator = HttpSessionConfigurator.class)
@Service
@EnableScheduling
public class FriendWebSocket {
    @Autowired
    private ActiveMqProduct activeMqProduct;
    @Autowired
    private FriendsService friendsService;
    private static CopyOnWriteArraySet<FriendWebSocket> webSockets=new CopyOnWriteArraySet<FriendWebSocket>();
    private Session session;
    private String username;
    private HashMap<String,Object> hashMap;
    private String friendId;
    private String friendUserId;
    private boolean flag ;
    private boolean flage2;
    @OnOpen
    public void onOpen(Session session, @PathParam(value="friendId")String friendId, EndpointConfig config){
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        User user = (User) httpSession.getAttribute("user");
        flag=false;
        flage2 = false;
        hashMap = new HashMap<>();
        this.username = user.getUsername();
        this.session = session;
        this.friendId = friendId;
        this.friendsService=(FriendsService)ApplicationContextutils.getBean("frinendsServiceImpl");
        this.activeMqProduct=(ActiveMqProduct) ApplicationContextutils.getBean("activeMqProduct");

        Friends byId = this.friendsService.findById(this.friendId);

        if(user.getId().equals(byId.getUserOneId())){
            friendUserId=byId.getUserTwoId();
        }else {
            friendUserId=byId.getUserOneId();
        }
        System.out.println("-----"+this.friendId);
        System.out.println(this.username);
        hashMap.put("username",this.username);
        webSockets.add(this);
        System.out.println("有新链接加入！当前在线人数为："+webSockets.size());
        hashMap.put("state","3");
        hashMap.put("message","建立回话");
        this.session.getAsyncRemote().sendText(JSONUtils.toJSONString(hashMap));
        try {
            List<String> getmsg = this.activeMqProduct.getmsg(friendId+user.getId());
            if(getmsg !=null &&getmsg.size()!=0){
                for(String s:getmsg) {
                    this.session.getBasicRemote().sendText(s);
                }
            }
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }

        for (FriendWebSocket item : webSockets) {
            if (item.friendId.equals(this.friendId)) {
                if (item.username != this.username) {
                    item.session.getAsyncRemote().sendText(helpMessage("您的好友上线了！"));
                    this.flage2 = true;
                }
            }
        }
    }
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        System.out.println("有一链接关闭！当前在线人数为" +webSockets.size());
    }
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的消息："+message);
        borodcast(message);
    }
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println(error.toString());
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void borodcast(String message){
        for(FriendWebSocket item:webSockets){
            System.out.println("====="+item.friendId);
            if(item.friendId.equals(this.friendId)) {
                if (item.username != this.username) {
                    flag = true;
                    hashMap.put("state", "1");
                    item.session.getAsyncRemote().sendText(helpMessage(message));
                } else {
                    hashMap.put("state", "0");
                    item.session.getAsyncRemote().sendText(helpMessage(message));
                }
            }
        }
        if(!flag){
            hashMap.put("state","1");
            String s = helpMessage(message);
            activeMqProduct.queue(friendId+friendUserId,s);
//            this.session.getAsyncRemote().sendText(helpMessage("您的好友离线了,他可能无法收到您的消息"));
        }
        flag = false;
    }
    public String helpMessage(String message){
        hashMap.put("data",new Date());
        hashMap.put("message",message);
        String string = JSONUtils.toJSONString(hashMap);
        return string;
    }

}
