package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Message createMessage(Message message){
        if(message.getMessageText() == null || message.getMessageText().trim().isEmpty()){
            return null;
        }

        if(message.getMessageText().length() > 255) {
            return null;
        }

        if(message.getPostedBy() == null || !accountRepository.existsById(message.getPostedBy())){
            return null;
        }

        return messageRepository.save(message);
    }
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
     public Message getMessageById(Integer messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }
    public Integer deleteMessage(Integer messageId) {
        if (messageRepository.existsById(messageId)){
            messageRepository.deleteById(messageId);
            return 1;
        }
        return null;
    }
}



