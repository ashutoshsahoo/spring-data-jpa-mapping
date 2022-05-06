package com.ashu.one2one.model;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Locker implements Serializable {

	@Serial
	private static final long serialVersionUID = 7176130763100798174L;

	@Id
	private Long id;

	private String lockerNo;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Employee employee;

	public Locker(Employee employee, String lockerNo) {
		super();
		this.employee = employee;
		this.lockerNo = lockerNo;
	}

}
