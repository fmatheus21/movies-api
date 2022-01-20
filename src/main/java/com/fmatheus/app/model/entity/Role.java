package com.fmatheus.app.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "role", catalog = "movies")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "method", nullable = false, length = 10)
    private String method;

    @Column(name = "role", nullable = false, length = 100)
    private String role;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ToString.Exclude
    @JoinTable(name = "user_role_join", joinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> userCollection;

}
