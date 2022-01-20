package com.fmatheus.app.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "error_system", catalog = "movies")
public class ErrorSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "name_class", nullable = false, length = 100)
    private String nameClass;

    @Column(name = "name_method", nullable = false, length = 100)
    private String nameMethod;

    @Lob
    @Column(name = "message", nullable = false, length = 2147483647)
    private String message;

}
