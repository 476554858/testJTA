package com.zjx.testJTA.controller;

import com.zjx.testJTA.pojo.User;
import com.zjx.testJTA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JmsTemplate jmsTemplate;

    @ResponseBody
    @RequestMapping("/add")
    @Transactional
    public String addUser(User user){
        //userRepository.save(user); 我修改的第二次
        jmsTemplate.convertAndSend("testQueue",user.getName());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/findAll")
    public List<User> findAll(){
        List<User> users =  userRepository.findAll();
        return users;
    }
}
