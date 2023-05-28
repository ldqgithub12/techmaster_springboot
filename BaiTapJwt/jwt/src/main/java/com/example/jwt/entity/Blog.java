package com.example.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "blog")
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "pulished_at")
    private LocalDateTime pulishedAt;

    @Column(name = "status", columnDefinition = "int default 0")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    //@JsonIgnore
    private User user;

    public String getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (this.createdAt ==null){
            return null;
        }
        return createdAt.format(formatter);
    }

    public String getUpdatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (this.updatedAt == null){
            return "";
        }
        return updatedAt.format(formatter);
    }

    public String getPulishedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (this.pulishedAt == null){
            return "";
        }
        return pulishedAt.format(formatter);
    }

    //    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
//    private List<comment> comments;
    @ManyToMany
    @JoinTable(name = "blog_category",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if(status) {
            pulishedAt = createdAt;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
        if(status) {
            pulishedAt = updatedAt;
        }
    }
}
