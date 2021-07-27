package com.example.ChatBotVK.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VkGroup {

    @Id
    private Long id;
    private String token;
    private String name;

    public VkGroup() {
    }


    public VkGroup(Long id, String token, String name) {
        this.id = id;
        this.token = token;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
