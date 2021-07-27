package com.example.ChatBotVK.repository;

import com.example.ChatBotVK.model.VkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VkGroupRepository extends JpaRepository<VkGroup, Long> {

    VkGroup findVkGroupById(Long keyboardId);

    List<VkGroup> findAll();


}
