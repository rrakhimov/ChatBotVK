package com.example.ChatBotVK.controller;

import com.example.ChatBotVK.botstarter.BotStarter;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    private BotStarter botStarter;
    public HomePageController(BotStarter botStarter) {
        this.botStarter = botStarter;
    }

        @Value("${bot.groupid}")
    private int groupId;
    @Value("${bot.token}")
    private String token;

    @GetMapping(value = "/")
    public String homePage() {
        return "homepage";
    }

    @GetMapping("/start")
    public String startBot(){
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    botStarter.botRun(groupId, token);
                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return "homepage";
    }

}
