package com.ashu.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private ZonedDateTime postTime;

    private String message;

    private Long userId;

}
