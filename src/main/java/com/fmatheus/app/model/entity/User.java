package com.fmatheus.app.model.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user", catalog = "movies")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "avatar", nullable = false, length = 50)
    private String avatar;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 70)
    private String password;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<Movie> movieCollection;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updatedBy")
    private Collection<Movie> movieCollection1;

    @ToString.Exclude
    @JoinColumn(name = "id_person", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person person;

    @ToString.Exclude
    @JoinColumn(name = "id_user_status", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private UserStatus userStatus;

    @ToString.Exclude
    @JoinTable(name = "user_role_join",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roleCollection;


}
