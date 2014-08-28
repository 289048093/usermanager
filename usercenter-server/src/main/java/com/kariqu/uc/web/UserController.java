package com.kariqu.uc.web;

import com.kariqu.uc.domain.User;
import com.kariqu.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: SanAction
 * @Date:2014/7/4 17:08
 * @Version: 1.0.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("find")
    @ResponseBody
    public User findByUserName(@RequestParam(value = "userName") String userName) {
        return this.userService.findUserByName(userName);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String registerUser(User user){
        userService.registerUser(user);
        return "";
    }
}
