package com.jiangnan.qiuzhao.controller;

import com.jiangnan.qiuzhao.entity.Friends;
import com.jiangnan.qiuzhao.entity.User;
import com.jiangnan.qiuzhao.service.FriendsService;
import com.jiangnan.qiuzhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("friend")
@CrossOrigin
public class FriendsController {
    @Autowired
    private FriendsService friendsService;
    @Autowired
    private UserService userService;

    @GetMapping("delete")
    private Map<String,Object> delete(String id){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            friendsService.delete(id);
            hashMap.put("state",true);
            hashMap.put("msg","山一程，水一程，感谢彼此之间的陪伴");
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put("state",false);
            hashMap.put("msg","出现了一点小问题,可能是对方在想你");
        }
        return hashMap;
    }
    @GetMapping("update")
    private Map<String,Object> update(String id){
        HashMap<String,Object> hashMap = new HashMap<>();
        Friends byId = friendsService.findById(id);
        byId.setUserOneState("1");
        byId.setUserTwoState("1");
        try {
            friendsService.update(byId);
            hashMap.put("state",true);
            hashMap.put("msg","恭喜您收获了一个新的小伙伴哦");
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put("state",false);
            hashMap.put("msg","哎呀 出了一点小问题");
        }
        return hashMap;
    }
    @GetMapping("save")
    private Map<String,Object> save(String username, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        HashMap<String,Object> hashMap = new HashMap<>();
        User byUsername = userService.findByUsername(username);

        if(byUsername==null){
            hashMap.put("state",false);
            hashMap.put("msg","查无此人");
        }else {
            String friendId = friendsService.findFriendId(user.getId(), byUsername.getId());
            if(friendId==null) {
                Friends friends = new Friends();
                friends.setUserOneId(user.getId());
                friends.setUserOneState("1");
                friends.setUserTwoId(byUsername.getId());
                friends.setUserTwoState("0");
                try {
                    friendsService.save(friends);
                    hashMap.put("state", true);
                    hashMap.put("msg", "您的好友申请已经发出，请等待");
                } catch (Exception e) {
                    hashMap.put("state", false);
                    hashMap.put("msg", "发生了一点点的小错误");
                    e.printStackTrace();
                }
            }else {
                hashMap.put("state",false);
                hashMap.put("msg","你们已经是好友啦/你的好友申请已经提交了，不要做舔狗哦");
            }
        }
        return hashMap;

    }


}
