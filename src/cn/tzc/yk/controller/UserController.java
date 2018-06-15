package cn.tzc.yk.controller;

import cn.tzc.yk.mapper.UserMapper;
import cn.tzc.yk.po.Item;
import cn.tzc.yk.po.User;
import cn.tzc.yk.serviceImpl.ItemServiceImpl;
import cn.tzc.yk.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ItemServiceImpl itemService;

    @RequestMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser() {
        List<User> ls = null;
        try {
            ls = userService.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    @RequestMapping("/hasAccount")
    @ResponseBody
    public int hasAccount(@RequestParam(name = "name") String name){
        int n = 0;
        boolean hasAccount = false;
        try {
            hasAccount = userService.hasAccount(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(hasAccount==true){
            n = 1;
        }
        return n;
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user){
        int id = 0;
        try {
            id = userService.getMaxId()+1;
            user.setId(id);
            System.out.println("user:"+user.getId()+":"+user.getUsername()+":"+user.getPassword());
            userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping("/login")
    @ResponseBody
    public int login(@RequestBody User user){
        int n = 0;
        try {
            n = userService.login(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    @RequestMapping("/changeUserInfo")
    @ResponseBody
    public String changeUserInfo(@RequestBody User user){
        try {
           userService.changeUserInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam(name = "id") int id){
        try {
           userService.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

}