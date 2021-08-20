package com.example.ChatBotVK;

import com.example.ChatBotVK.Service.VkGroupService;
import com.example.ChatBotVK.consumer.HttpKeyboardConsumer;
import com.example.ChatBotVK.model.VkGroup;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Init {

    private VkGroupService vkGroupService;

    private HttpKeyboardConsumer httpKeyboardConsumer;

    public Init(VkGroupService vkGroupService, HttpKeyboardConsumer httpKeyboardConsumer) {
        this.vkGroupService = vkGroupService;
        this.httpKeyboardConsumer = httpKeyboardConsumer;
    }

    @PostConstruct
    public void init(){
        saveGroup(105995953L, "7356b31df1afb39b820f2138cc668612312f79662a73434094860893113aa5c3ebf0482c0cba9220735b3", "test");
        saveGroup(10L, "7356b31df1afb39b820f2138cc668612312f79662a73434094860893113aa5c3ebf0482c0cba9220735b3", "test2");
        saveGroup(1L, "7356b31df1afb39b820f2138cc668612312f79662a73434094860893113aa5c3ebf0482c0cba9220735b3", "test3");
      //  httpKeyboardConsumer.getKeyboardsList();
    }

    public void saveGroup(Long id, String token, String name){
        vkGroupService.saveGroup(new VkGroup(id, token, name));
    }


}
