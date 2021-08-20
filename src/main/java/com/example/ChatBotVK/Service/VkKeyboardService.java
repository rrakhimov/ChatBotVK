package com.example.ChatBotVK.Service;

import com.example.ChatBotVK.model.MyKeyboard;
import com.vk.api.sdk.objects.messages.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VkKeyboardService {

    private KeyboardService keyboardService;

    public VkKeyboardService(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }


    public Keyboard getKeyboard(){
        Keyboard keyboard = new Keyboard();
        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<MyKeyboard> myKeyboards = keyboardService.getKeyboardsList();

        for (MyKeyboard myKeyboard : myKeyboards) {
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel(myKeyboard.getText())
                    .setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        }

        allKey.add(line1);
        keyboard.setButtons(allKey);
        return keyboard;
    }
}
