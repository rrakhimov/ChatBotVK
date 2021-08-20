package com.example.ChatBotVK.consumer;

import com.example.ChatBotVK.model.MyKeyboard;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class KafkaKeyboardConsumer implements KeyboardConsumer{

    private List<MyKeyboard> keyboardList;

    @KafkaListener(topics="all_keyboards")
    public void orderListener(ConsumerRecord<Long, List<MyKeyboard>> record){
        keyboardList = record.value();
    }

    @Override
    public List<MyKeyboard> getKeyboardsList() {

        return keyboardList;
    }
}
