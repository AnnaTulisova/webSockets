package com.tulisova.webSockets.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Data
@Accessors(chain = true)
public class ChatDTO {
    private Long id;
    private Long name;
}
