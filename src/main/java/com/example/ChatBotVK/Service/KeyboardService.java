package com.example.ChatBotVK.Service;

import com.example.ChatBotVK.consumer.HttpKeyboardConsumer;
import com.example.ChatBotVK.consumer.KeyboardConsumer;
import com.example.ChatBotVK.model.MyKeyboard;
import com.vk.api.sdk.objects.messages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class KeyboardService {
    private List<MyKeyboard> myKeyboards;
    private HashMap<String, String> keyboardMap = new HashMap<>();

    @Autowired
    @Qualifier(value = "kafkaKeyboardConsumer")
    private KeyboardConsumer keyboardConsumer;


    public List<List<KeyboardButton>> getAllKeys() {
        List<List<KeyboardButton>> allKeys = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();

        myKeyboards = keyboardConsumer.getKeyboardsList();

        for (MyKeyboard myKeyboard : myKeyboards) {
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel(myKeyboard.getText())
                    .setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
            keyboardMap.put(myKeyboard.getText(), myKeyboard.getResponce());
        }

        allKeys.add(line1);
        return allKeys;
    }

    public Keyboard getKeyboard() {
        Keyboard keyboard = new Keyboard();
        keyboard.setButtons(getAllKeys());
        return keyboard;
    }



    public HashMap<String, String> getKeyboardMap() {
        return keyboardMap;
    }
}
