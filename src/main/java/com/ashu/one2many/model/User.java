package com.ashu.one2many.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "USERS")
@NoArgsConstructor
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 7989869971092538013L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1, initialValue = 1001)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    // @JsonBackReference
    private Set<Post> posts = new HashSet<>();

    public User(String name) {
        super();
        this.name = name;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setUser(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setUser(null);
    }

}
