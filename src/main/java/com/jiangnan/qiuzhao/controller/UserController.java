package com.jiangnan.qiuzhao.controller;

import com.aliyuncs.exceptions.ClientException;
import com.jiangnan.qiuzhao.entity.*;
import com.jiangnan.qiuzhao.service.FriendsService;
import com.jiangnan.qiuzhao.service.Impl.SendSmsService;
import com.jiangnan.qiuzhao.utils.VerifyCodeUtils;
import com.jiangnan.qiuzhao.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SendSmsService sendSmsService;
    @Autowired
    private FriendsService friendsService;
    @PostMapping("save")
    public Map<String,Object> save(@RequestBody User user,String code,HttpServletRequest request){
        HashMap<String,Object> hashMap = new HashMap<>();
        String sessionCode = (String) request.getSession().getAttribute("code");
        if(!code.equalsIgnoreCase(sessionCode)){
            hashMap.put("state",false);
            hashMap.put("msg","验证码错误");
            return hashMap;
        }
        User byUsername = userService.findByUsername(user.getUsername());
        if(byUsername!=null){
            hashMap.put("state",false);
            hashMap.put("msg","用户已存在");
        }
        try {
            userService.save(user);
            hashMap.put("state",true);
            hashMap.put("msg","注册成功");
        } catch (Exception e) {
            hashMap.put("state",false);
            hashMap.put("msg","注册失败");
        }
        return hashMap;
    }

    @GetMapping("getCheckCode")
    public Map<String,String> getCheckCode(String phoneNumber, HttpServletRequest request) throws ClientException {
        String s = VerifyCodeUtils.generateVerifyCode(4);
        request.getSession().setAttribute("code",s);
        Map<String, String> stringStringMap = sendSmsService.sendcheckCode(phoneNumber, s);
        return stringStringMap;
    }
    @GetMapping("getUser")
    public AllUser getUser(HttpServletRequest request){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        String username = userDetails.getUsername();
        User byUsername = userService.findByUsername(username);
        AllUser allUser = new AllUser();
        BeanUtils.copyProperties(byUsername,allUser);
        List<Friends> allFrinend = friendsService.findAllFrinendAsk(allUser.getId());
        List<UserFriend> userFriendList = new LinkedList<>();
        for(Friends friends:allFrinend){
            String userOneId = friends.getUserOneId();
            User byId = userService.findById(userOneId);
            UserFriend userFriend = new UserFriend();
            userFriend.setUserId(userOneId);
            userFriend.setFriendId(friends.getId());
            userFriend.setUsername(byId.getUsername());
            userFriendList.add(userFriend);
        }
        allUser.setFriendsAskList(userFriendList);
        request.getSession().setAttribute("alluser",allUser);
        request.getSession().setAttribute("user",byUsername);
        return allUser;
    }
    @GetMapping("getFriend")
    private Map<String,Object> getFriend(HttpServletRequest request){
        User user1 = (User) request.getSession().getAttribute("user");
        List<User> allfriend = userService.findALlfriend(user1.getId());
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("user",user1);
        System.out.println(allfriend);
        List<UserFriend> list = new LinkedList<>();
        for(User user:allfriend){
            UserFriend userFriend = new UserFriend();
            userFriend.setUserId(user.getId());
            userFriend.setUsername(user.getUsername());
            String friendId = friendsService.findFriendId(user1.getId(), user.getId());
            userFriend.setFriendId(friendId);
            list.add(userFriend);
        }
        hashMap.put("friend",list);
        return hashMap;
    }
}
