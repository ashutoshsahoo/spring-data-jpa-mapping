package com.ashu.one2many.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = -657331407889087174L;

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "POST_CREATE_TIME", updatable = false)
    @CreatedDate
    private LocalDateTime postCreateTime;

    @Column(name = "POST_LAST_UPDATE_TIME")
    @LastModifiedDate
    private LocalDateTime postLastUpdateTime;

    @Column(name = "MESSAGE", nullable = false, length = 512)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    // @JsonManagedReference
    private User user;

    public Post(User user, String message) {
        super();
        this.user = user;
        this.message = message;
    }

}
