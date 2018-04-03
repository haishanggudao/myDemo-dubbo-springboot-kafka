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
}
