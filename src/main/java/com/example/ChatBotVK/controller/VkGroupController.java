package com.example.ChatBotVK.controller;

import com.example.ChatBotVK.Service.VkGroupService;
import com.example.ChatBotVK.model.MyKeyboard;
import com.example.ChatBotVK.model.VkGroup;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Api(value = "API для работы с  группами",
        description = "API предоставляет возможность работы с группами", produces = "application/json")
@Controller
public class VkGroupController {

    private VkGroupService vkGroupService;
    public VkGroupController(VkGroupService vkGroupService) {
        this.vkGroupService = vkGroupService;
    }
    @ApiOperation(value = "Добаdляет в модель все группы и возвращает groups.html")
    @GetMapping("/groups")
    public String allGroups(Model model){
        List<VkGroup> groups = vkGroupService.getAllGroups();
        model.addAttribute("groups", groups);

        return "groups";
    }
    @ApiOperation(value = "Удаляет группу по id")
    @GetMapping(value = "groups/delete")
    public String deleteGroup(@RequestParam("id") Long id){
        vkGroupService.delete(id);
        return "redirect:/groups/";
    }
    @ApiOperation(value = "Запускает чат-бота для группы по id")
    @GetMapping(value = "groups/start")
    public String startBot(@RequestParam("id") Long id){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    vkGroupService.startBot(id);
                } catch (ClientException | ApiException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return "redirect:/groups/";
    }
    @ApiOperation(value = "Возвращает страницу для добавления группы")
    @GetMapping(value = {"groups/new"})
    public String addNewGroupForm(Model model) {
        model.addAttribute("group", new VkGroup());

        return "new";
    }


    @ApiOperation(value = "Добавляет новую группу")
    @PostMapping("/groups/add_group")
    public String addNewGroup(@ModelAttribute("group") VkGroup vkGroup){
       vkGroupService.add(vkGroup);

        return "redirect:/groups/";
    }
    @PostConstruct
    public void init(){
        System.out.println("=====");

    }


}
