package com.utact.demo.message.dto;

import java.time.OffsetDateTime;

public record MessageDTO(
        Long id,
        String content,
        String sender,
        Integer rating,
        OffsetDateTime sendTime
) {
}
