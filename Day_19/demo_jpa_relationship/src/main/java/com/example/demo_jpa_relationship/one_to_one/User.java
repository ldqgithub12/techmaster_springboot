package com.example.demo_jpa_relationship.one_to_one;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Đánh dấu có mỗi quan hệ 1-1 với IdentityCard
    @JoinColumn(name = "identity_card_id") // Liên kết với nhau qua khóa ngoại identity_card_id
    private IdentityCard identityCard;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }
}
