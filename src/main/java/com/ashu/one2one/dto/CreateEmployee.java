package com.ashu.one2one.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployee implements Serializable {

	@Serial
	private static final long serialVersionUID = -7752434247855900569L;

	private String name;

}
