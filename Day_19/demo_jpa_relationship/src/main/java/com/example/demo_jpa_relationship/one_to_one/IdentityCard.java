package com.example.demo_jpa_relationship.one_to_one;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="identity_card")
public class IdentityCard {
    @Id
    private String id;

    @Column(name="code")
    private String code;
}

