package com.fmatheus.app.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movie", catalog = "movies")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "rating", nullable = false, precision = 2, scale = 1)
    private BigDecimal rating;

    @Lob
    @Column(name = "url_trailer", length = 2147483647)
    private String urlTrailer;

    @Column(name = "image", nullable = false, length = 30)
    private String image;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ToString.Exclude
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User createdBy;

    @ToString.Exclude
    @JoinColumn(name = "updated_by", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User updatedBy;

}
