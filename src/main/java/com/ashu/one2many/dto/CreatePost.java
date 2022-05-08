package com.ashu.one2many.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePost implements Serializable {

	@Serial
	private static final long serialVersionUID = -6163350023418653204L;

	private String message;

	private Long userId;

}
