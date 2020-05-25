package com.ashu.one2many.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePost implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private Long userId;

}
