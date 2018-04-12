package com.htsc.htbcps.myDemo.dubboservice;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htsc.htbcps.myDemo.dto.User;
import com.htsc.htbcps.myDemo.services.SayHelloService;


/**
 * Hello world!
 *
 */
@ComponentScan(basePackages = { "com.htsc.htbcps.myDemo.*"}) // 组件扫描
@EnableAutoConfiguration
@SpringBootApplication
@ImportResource({ "classpath:dubbo-config.xml" })
@MapperScan("com.htsc.htbcps.myDemo.dal.dao")
@RequestMapping(value = "/")
public class MyDemoApplication 
{
	private static final Logger logger = LoggerFactory.getLogger(MyDemoApplication.class);
	
	@Autowired
    private KafkaTemplate<String, String> template;
	
	@Autowired
	private SayHelloService sayHelloService;
	
    public static void main( String[] args ) throws InterruptedException
    {
    	logger.info("项目开始启动!");
    	ConfigurableApplicationContext ctx = SpringApplication.run(MyDemoApplication.class, args);
        logger.info("项目启动!");
    }
    
    @RequestMapping("/send")
    @ResponseBody
    String send(String topic, String key, String data) {
        template.send(topic, key, data);
        return "success";
    }
    
    @RequestMapping("/sayHello")
    @ResponseBody
    String sayHello(String word) {
        System.out.println("----我是cn.larry.spring.service.DemoService.run()----1");
        String result = sayHelloService.SayHello(word);
        System.out.println("----我是cn.larry.spring.service.DemoService.run()----2");
        return result;
    }
    
    @RequestMapping("/getUser")
    @ResponseBody
    String getUser(String userId) {
        System.out.println("----我是com.htsc.htbcps.myDemo.dubboservice.getUser()----1");
        User user = sayHelloService.getUser(userId);
        System.out.println("----我是com.htsc.htbcps.myDemo.dubboservice.getUser()----2");
        return user.toString();
    }
}
