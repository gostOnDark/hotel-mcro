package com.company.hotel.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/chat")
public class ChatController {
    @GetMapping("/to/{cid}")
    public String handleChatMessage(@PathVariable String cid, Model model){
        model.addAttribute("cid", cid);
        return "chatRoom";
    }
}
