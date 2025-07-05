package com.utact.demo.message.service;

import com.utact.demo.message.entity.Message;
import com.utact.demo.message.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        return messageRepository.findAllByOrderByTimestampDesc();
    }

    public Message save(Message message) {
        messageRepository.save(message);
        return message;
    }
}
