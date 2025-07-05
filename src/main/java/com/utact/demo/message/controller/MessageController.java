package com.utact.demo.message.controller;

import com.utact.demo.message.entity.Message;
import com.utact.demo.message.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "${https://utact.vercel.app/}")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getMessages();
    }

    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        if (message.getContent() == null || message.getContent().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        if (message.getSender() == null || message.getSender().trim().isEmpty()) {
            message.setSender("익명의 방문자");
        }
        if (message.getRating() == null) {
            message.setRating(5);
        }
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage = messageService.save(message);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }
}
