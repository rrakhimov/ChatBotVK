package com.example.ChatBotVK.Service;

import com.example.ChatBotVK.consumer.KeyboardConsumer;
import com.example.ChatBotVK.model.MyKeyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyboardService {

    @Autowired
    @Qualifier(value = "reactiveWebKeyboardConsumer")
//    @Qualifier(value = "httpKeyboardConsumer")
    private KeyboardConsumer keyboardConsumer;

    public List<MyKeyboard> getKeyboardsList() {
        return keyboardConsumer.getKeyboardsList();
    }
}
