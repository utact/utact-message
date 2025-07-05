package com.utact.demo.message.service;

import com.utact.demo.message.entity.Message;
import com.utact.demo.message.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        List<Message> messages = messageRepository.findAllByOrderBySendTimeDesc();
        return messages != null ? messages : Collections.emptyList();
    }

    public Message save(Message request) {
        Message message = new Message(
                request.getId(),
                request.getContent(),
                request.getSender(),
                request.getRating(),
                request.getSendTime()
        );

        messageRepository.save(message);

        return message;
    }
}
