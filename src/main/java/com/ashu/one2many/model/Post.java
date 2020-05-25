package com.ashu.one2many.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", unique = true, updatable = false, nullable = false)
	private String id;

	@Column(name = "POST_TIME", nullable = false, updatable = false)
	@CreationTimestamp
	private ZonedDateTime postTime;

	@Column(name = "MESSAGE", nullable = false, length = 512)
	private String message;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID")
	// @JsonManagedReference
	private User user;

	public Post(User user, String message) {
		super();
		this.user = user;
		this.message = message;
	}

}
