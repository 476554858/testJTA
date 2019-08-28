package com.zjx.testJTA;

import com.zjx.testJTA.pojo.User;
import com.zjx.testJTA.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJtaApplicationTests {

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	UserRepository userRepository;
	@Test
	public void contextLoads() {
		jmsTemplate.convertAndSend("testQueue","我是张三");
		System.out.println("--------");
	}

	@Test
	public void testJPA(){
		User user = new User();
		//user.setId(1);
		user.setName("张三");
		userRepository.save(user);
	}

}
