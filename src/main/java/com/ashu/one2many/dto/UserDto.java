package com.ashu.one2many.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

	@Serial
	private static final long serialVersionUID = -7082586220103925271L;

	private Long id;

	private String name;

}
