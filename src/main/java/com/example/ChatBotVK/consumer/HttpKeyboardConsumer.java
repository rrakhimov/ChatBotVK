package com.example.ChatBotVK.consumer;

import com.example.ChatBotVK.model.MyKeyboard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class HttpKeyboardConsumer implements KeyboardConsumer{
    @Value("${rest.server.host}/api/keyboard/all")
    private String allKeyboardsUrl;

    private final RestTemplate restTemplate = new RestTemplate();


    /**
     * Метод запрашивает у сервера список всех кнопок клавиатуры с ответами
     *
     * @return - список клавиатур
     */
    @Override
    public List<MyKeyboard> getKeyboardsList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<List<MyKeyboard>> response =
                    restTemplate.exchange(allKeyboardsUrl,
                            HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
                            }, "");
            return response.getBody();
        } catch (HttpStatusCodeException httpStatusCodeException) {
            return null;
        }
    }


}
