package com.example.demo_jpa_relationship.one_to_many;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToOne(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Blogs>blogs = new ArrayList<>();
}
