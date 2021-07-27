package com.example.ChatBotVK.Service;

import com.example.ChatBotVK.botstarter.BotStarter;
import com.example.ChatBotVK.model.VkGroup;
import com.example.ChatBotVK.repository.VkGroupRepository;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VkGroupService {
    private VkGroupRepository vkGroupRepository;
    private BotStarter botStarter;

    public VkGroupService(VkGroupRepository vkGroupRepository, BotStarter botStarter) {
        this.vkGroupRepository = vkGroupRepository;
        this.botStarter = botStarter;
    }

    @Transactional
    public VkGroup getVkGroupById(Long id) {
        return vkGroupRepository.findVkGroupById(id);
    }

    @Transactional
    public List<VkGroup> getAllGroups() {
        return vkGroupRepository.findAll();
    }

    @Transactional
    public void saveGroup(VkGroup vkGroup) {
        vkGroupRepository.save(vkGroup);
    }


    @Transactional
    public void delete(Long id) {
        vkGroupRepository.deleteById(id);
    }

    public void startBot(Long id) throws ClientException, InterruptedException, ApiException {
        botStarter.botRun(Math.toIntExact(id), vkGroupRepository.findVkGroupById(id).getToken());
    }

    @Transactional
    public void add(VkGroup vkGroup) {
        vkGroupRepository.save(vkGroup);
    }
}
