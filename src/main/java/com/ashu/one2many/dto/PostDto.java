package com.ashu.one2many.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 5968418094686221081L;

    private UUID id;

    private LocalDateTime postCreateTime;

    private LocalDateTime postLastUpdateTime;

    private String message;

    private Long userId;

}
