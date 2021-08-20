package com.example.ChatBotVK.consumer;

import com.example.ChatBotVK.model.MyKeyboard;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ReactiveWebKeyboardConsumer implements KeyboardConsumer {

    private WebClient client = WebClient.create("http://localhost:8484");
    private List<MyKeyboard> list = new ArrayList<>();


    @HystrixCommand(fallbackMethod = "callKeyboardService_Fallback", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
            value="1100")
    })
    @Override
    public List<MyKeyboard> getKeyboardsList() {

        Flux<MyKeyboard> myKeyboardFlux = client.get()
                .uri("api/keyboard/all")
                .retrieve()
                .bodyToFlux(MyKeyboard.class);

        list = myKeyboardFlux.collectList().block();
        return list;
    }

    @SuppressWarnings("unused")
    private List<MyKeyboard> callKeyboardService_Fallback() {

          System.out.println("Fallback! " + new Date());
        return list;
    }
}
