package com.example.ChatBotVK;

import com.example.ChatBotVK.Service.VkGroupService;
import com.example.ChatBotVK.model.VkGroup;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Init {

    private VkGroupService vkGroupService;

    public Init(VkGroupService vkGroupService) {
        this.vkGroupService = vkGroupService;
    }

    @PostConstruct
    public void init(){
        saveGroup(105995953L, "7356b31df1afb39b820f2138cc668612312f79662a73434094860893113aa5c3ebf0482c0cba9220735b3", "test");
        saveGroup(10L, "7356b31df1afb39b820f2138cc668612312f79662a73434094860893113aa5c3ebf0482c0cba9220735b3", "test2");
        saveGroup(1L, "7356b31df1afb39b820f2138cc668612312f79662a73434094860893113aa5c3ebf0482c0cba9220735b3", "test3");
    }

    public void saveGroup(Long id, String token, String name){
        vkGroupService.saveGroup(new VkGroup(id, token, name));
    }


}
