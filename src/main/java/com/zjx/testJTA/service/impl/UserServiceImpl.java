package com.zjx.testJTA.service.impl;

import com.zjx.testJTA.pojo.User;
import com.zjx.testJTA.repository.UserRepository;
import com.zjx.testJTA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    JmsTemplate jmsTemplate;
    @Transactional
    @JmsListener(destination = "testQueue")
    public void save(String name)throws Exception{
        System.out.println("---------------"+name);
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        throw new RuntimeException("人为出错");
    }

    @Transactional
    @JmsListener(destination = "DLQ.testQueue")
    public void againSave(String name)throws Exception{
        jmsTemplate.convertAndSend("testQueue",name);
    }

}
