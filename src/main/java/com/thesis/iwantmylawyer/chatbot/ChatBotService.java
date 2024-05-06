package com.thesis.iwantmylawyer.chatbot;


import com.thesis.iwantmylawyer.constant.Constant;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatBotService {
    private final ChatClient chatClient;
    private final List<Message> messageList;

    public ChatBotService(ChatClient chatClient) {
        this.chatClient = chatClient;
        this.messageList = new ArrayList<>();
        Message systemMessage = new SystemPromptTemplate(Constant.AI_SYSTEM_PROMPT)
                .createMessage();
        messageList.add(systemMessage);
    }

    public ChatBotResponse chatWithBot(String userInput){
        UserMessage userMessage = new UserMessage(userInput);
        messageList.add(userMessage);
        Prompt promptList = new Prompt(messageList);
        ChatResponse aiResponse = chatClient.call(promptList);
        messageList.add(aiResponse.getResult().getOutput());

        String content = aiResponse.getResult().getOutput().getContent();
        return new ChatBotResponse(content);

    }
    public void refreshMessages(){
        List<Message> elementsToRemove = messageList.subList(0,messageList.size());
        this.messageList.removeAll(elementsToRemove);
    }

}
