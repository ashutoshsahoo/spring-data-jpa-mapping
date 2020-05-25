package com.ashu.one2one.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocker implements Serializable {

	private static final long serialVersionUID = 1L;

	private String lockerNo;

	private Long employeeId;

}
