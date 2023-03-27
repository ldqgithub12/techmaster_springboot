package com.example.demo_jpa_relationship;

import com.example.demo_jpa_relationship.one_to_one.IdentityCard;
import com.example.demo_jpa_relationship.one_to_one.IdentityCardRepository;
import com.example.demo_jpa_relationship.one_to_one.User;
import com.example.demo_jpa_relationship.one_to_one.UserRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class OneToOneTest {
    @Autowired
    private IdentityCardRepository identityCardRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    void save_user_card(){
        Faker faker = new Faker();
        for (int i = 0;i<30;i++){
            IdentityCard identityCard = IdentityCard.builder()
                    .code(String.valueOf(faker.number().numberBetween(1000,9000)))
                    .build();
            identityCardRepository.save(identityCard);

            User user = User.builder()
                    .name(String.valueOf(faker.name().name()))
                    .identityCard(identityCard)
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void delete_id(){
        userRepository.deleteById(1);
    }
}
