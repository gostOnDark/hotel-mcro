package com.company.hotel.admin.controller;

import com.company.hotel.admin.entity.ResultMsg;
import com.company.hotel.admin.entity.User;
import com.company.hotel.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/changeLock")
    @ResponseBody
    public ResultMsg changeLock(@RequestParam(value = "userid") String userid, @RequestParam(value = "locked") Boolean locked) {
        userService.changeLock(userid, locked);
        return ResultMsg.build(200, "更新成功");
    }
    @GetMapping("/userPageInfo")
    public String userPageInfo(int pageNum, int pageSize, String orderField, int orderType, Model model){
        model.addAttribute("resultMsg",userService.userPadgeInfo(pageNum,pageSize,orderField,orderType));
        return "/userlist";

    }
    @GetMapping("/addUser")
    @ResponseBody
    public ResultMsg addUser(User user){
        return userService.addUser(user);
    }
}
