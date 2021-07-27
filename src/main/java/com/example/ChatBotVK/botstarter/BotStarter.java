package com.example.ChatBotVK.botstarter;

import com.example.ChatBotVK.Service.KeyboardService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class BotStarter {
    private TransportClient transportClient = new HttpTransportClient();
    private VkApiClient vk = new VkApiClient(transportClient);
    private Random random = new Random();

    @Autowired
    private KeyboardService keyboardService;


    public void botRun(int groupId, String token) throws ClientException, ApiException, InterruptedException {
        GroupActor actor = new GroupActor(groupId, token);
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        Keyboard keyboard = new Keyboard();
        keyboard.setButtons(keyboardService.getAllKeys());

        while (true) {
            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            if (!messages.isEmpty()) {
                messages.forEach(message -> {
                    try {
                        String userMessage = message.getText();
                        if (userMessage.equals("Кнопки")) {
                            vk.messages().send(actor).message("Кнопки ответа:").userId(message.getFromId()).randomId(random.nextInt(10000)).keyboard(keyboard).execute();
                        } else if (keyboardService.getKeyboardMap().containsKey(userMessage)) {
                            vk.messages().send(actor).message(keyboardService.getKeyboardMap().get(userMessage) ).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();

                        } else {
                            vk.messages().send(actor).message(userMessage).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                    } catch (ApiException | ClientException e) {
                        e.printStackTrace();
                    }
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }
    }
}
