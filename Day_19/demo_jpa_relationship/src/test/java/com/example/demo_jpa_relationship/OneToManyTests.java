package com.example.demo_jpa_relationship;

import com.example.demo_jpa_relationship.one_to_many.AuthorRepository;
import com.example.demo_jpa_relationship.one_to_many.Authors;
import com.example.demo_jpa_relationship.one_to_many.BlogRepository;
import com.example.demo_jpa_relationship.one_to_many.Blogs;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class OneToManyTests {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BlogRepository blogRepository;
    @Test
    void save_author_blog_2(){
        Faker faker = new Faker();
        List<Blogs> blogs = new ArrayList<>();
        for (int i =0;i<5;i++){
            Authors authors = Authors.builder()
                    .name(faker.name().fullName())
                    .build();

            authorRepository.save(authors);
            for (int j = 0;j<3;j++){
                Blogs blogs1 = Blogs.builder()
                        .title(faker.book().title())
                        .authors(authors)
                        .build();
                blogRepository.save(blogs1);
            }
        }

    }
//    void save_author_blog(){
//        Faker faker = new Faker();
//        List<Blogs> blogs = new ArrayList<>();
//        for (int i =0;i<5;i++){
//
//            for (int j = 0;j<3;j++){
//                Blogs blogs1 = Blogs.builder()
//                        .title(faker.book().title())
//                        .build();
//                blogs.add(blogs1);
//            }
//        }
//        Authors authors = Authors.builder()
//                .name(faker.name().fullName())
//                .blogs(blogs)
//                .build();
//        authorRepository.save(authors);
//    }
}
