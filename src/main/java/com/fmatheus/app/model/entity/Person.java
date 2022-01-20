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
@Table(name = "person", catalog = "movies")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 70)
    private String name;

    @Column(name = "document", nullable = false, length = 20)
    private String document;

    @ToString.Exclude
    @JoinColumn(name = "id_person_type", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonType personType;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<Contact> contactCollection;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<User> userCollection;


}
