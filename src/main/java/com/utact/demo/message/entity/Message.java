package com.utact.demo.message.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String sender;
    private Integer rating;
    private OffsetDateTime sendTime;

    public Message() {
    }

    public Message(Long id, String content, String sender, Integer rating, OffsetDateTime offsetDateTime) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.rating = rating;
        this.sendTime = offsetDateTime;
    }
}
