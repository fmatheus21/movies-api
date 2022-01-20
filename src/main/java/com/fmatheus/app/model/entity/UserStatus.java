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
@Table(name = "user_status", catalog = "movies")
public class UserStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userStatus")
    private Collection<User> userCollection;


}
