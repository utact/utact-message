package com.utact.demo.message.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime sendTime;

    public void setTimestamp(LocalDateTime now) {
        this.sendTime = now;
    }
}
