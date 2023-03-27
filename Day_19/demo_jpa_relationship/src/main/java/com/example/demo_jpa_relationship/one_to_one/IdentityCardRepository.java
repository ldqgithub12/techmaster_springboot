package com.example.demo_jpa_relationship.one_to_one;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityCardRepository extends JpaRepository<IdentityCard,String> {
}
