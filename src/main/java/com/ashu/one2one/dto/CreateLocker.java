package com.ashu.one2one.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocker implements Serializable {

	@Serial
	private static final long serialVersionUID = -8458875898523895374L;

	private String lockerNo;

	private Long employeeId;

}
