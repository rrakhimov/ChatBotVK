package com.example.ChatBotVK;

import com.example.ChatBotVK.properties.TestProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableConfigurationProperties(TestProperties.class)
public class ChatBotVkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBotVkApplication.class, args);
	}

}
