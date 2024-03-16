package com.thesis.iwantmylawyer.chatbot;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chatbot")
public class ChatBotController {
    private final ChatBotService chatBotService;

    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @GetMapping
    public String chatWithBot(@RequestParam("userInput") String userInput){
        return  chatBotService.chatWithBot(userInput);
    }
    @DeleteMapping
    public void refreshChat(){
        chatBotService.refreshMessages();
    }
}
