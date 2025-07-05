package com.utact.demo.message.controller;

import com.utact.demo.message.dto.MessageDTO;
import com.utact.demo.message.entity.Message;
import com.utact.demo.message.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageDTO> getAllMessages() {
        return messageService.getMessages().stream()
                .map(message -> new MessageDTO(
                        message.getId(),
                        message.getContent(),
                        message.getSender(),
                        message.getRating(),
                        message.getSendTime()
                ))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<MessageDTO> addMessage(@RequestBody MessageDTO request) {
        if (request.content() == null || request.content().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        String sender = request.sender();
        if (sender == null || sender.trim().isEmpty()) {
            sender = "익명의 방문자";
        }

        Integer rating = request.rating();
        if (rating == null) {
            rating = 5;
        }

        Message message = new Message();
        message.setContent(request.content());
        message.setSender(sender);
        message.setRating(rating);
        message.setSendTime(OffsetDateTime.now());

        Message savedMessage = messageService.save(message);

        MessageDTO savedMessageDto = new MessageDTO(
                savedMessage.getId(),
                savedMessage.getContent(),
                savedMessage.getSender(),
                savedMessage.getRating(),
                savedMessage.getSendTime()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessageDto);
    }
}
