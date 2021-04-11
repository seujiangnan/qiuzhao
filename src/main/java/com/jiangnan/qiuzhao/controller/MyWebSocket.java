package com.jiangnan.qiuzhao.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.jiangnan.qiuzhao.config.HttpSessionConfigurator;
import com.jiangnan.qiuzhao.entity.AllUser;
import com.jiangnan.qiuzhao.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/room/{cpyId}",configurator = HttpSessionConfigurator.class)
@Component
public class MyWebSocket {
    private static CopyOnWriteArraySet<MyWebSocket> webSockets=new CopyOnWriteArraySet<MyWebSocket>();
    private Session session;
    private String username;
    private HashMap<String,Object> hashMap;
    private String cpyId;
    @OnOpen
    public void onOpen(Session session, @PathParam(value="cpyId")String cpyId, EndpointConfig config){
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        User user = (User) httpSession.getAttribute("user");
        hashMap = new HashMap<>();
        this.username = user.getUsername();
        this.session = session;
        this.cpyId = cpyId;
        System.out.println(this.username);
        hashMap.put("username",this.username);
        webSockets.add(this);
        System.out.println("有新链接加入！当前在线人数为："+webSockets.size());
        hashMap.put("state","3");
        hashMap.put("message","恭喜您成功连接上WebSocket-->当前在线人数为："+helpcurrentpeople());
        this.session.getAsyncRemote().sendText(JSONUtils.toJSONString(hashMap));
        for(MyWebSocket item:webSockets){
            if(item.cpyId.equals(this.cpyId)) {
                if (item.username != this.username) {
                    hashMap.put("state", "3");
                    item.session.getAsyncRemote().sendText(helpMessage("有人进入了聊天室,当前在线人数为："+helpcurrentpeople()));
                }
            }
        }
    }
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        for(MyWebSocket item:webSockets){
            if(item.cpyId.equals(this.cpyId)) {
                if (item.username != this.username) {
                    hashMap.put("state", "3");
                    item.session.getAsyncRemote().sendText(helpMessage("有人退出了聊天室,当前在线人数为："+helpcurrentpeople()));
                }
            }
        }
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
        for(MyWebSocket item:webSockets){
            if(item.cpyId.equals(this.cpyId)) {
                if (item.username != this.username) {
                    hashMap.put("state", "1");
                    item.session.getAsyncRemote().sendText(helpMessage(message));
                } else {
                    hashMap.put("state", "0");
                    item.session.getAsyncRemote().sendText(helpMessage(message));
                }
            }
        }
    }
    public String helpMessage(String message){
        hashMap.put("data",new Date());
        hashMap.put("message",message);
        String string = JSONUtils.toJSONString(hashMap);
        return string;
    }
    private int helpcurrentpeople(){
        int cur = 0;
        for(MyWebSocket item:webSockets){
            if(item.cpyId.equals(this.cpyId)) {
                cur++;
            }
        }
        return cur;
    }
}
