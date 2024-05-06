package com.thesis.iwantmylawyer.chatbot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chatbot")
public class ChatBotController {
    private final ChatBotService chatBotService;

    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @GetMapping
    public ResponseEntity<ChatBotResponse> chatWithBot(@RequestParam("userInput") String userInput){
        return new ResponseEntity<>(chatBotService.chatWithBot(userInput), HttpStatus.OK);
    }
    @DeleteMapping
    public void refreshChat(){
        chatBotService.refreshMessages();
    }
}
