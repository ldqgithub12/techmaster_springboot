package com.example.demojpa.repository;

import com.example.demojpa.dto.UserDto;
import com.example.demojpa.entity.User;
import com.example.demojpa.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 1. Method query (creation query) : đặt tên method theo đúng chuẩn được quy định
    List<User> findByName(String name);
    List<User> findByNameStartingWith(String prefix);
    List<User> findByNameContainingIgnoreCase(String name);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByName(String name);
    List<User> findByNameOrderByEmailDesc(String name);
    List<User> findByPasswordEndsWithIgnoreCaseOrderByNameAsc(String password);
    long countDistinctByEmailContains(String email);
    List<User> findTop5ByName(String name);

    // 2. JPQL Query (JPA Query Language) : Query dựa theo entity
//    @Query("select u from User u where u.name = ?1")
    @Query(value="select * from user u where u.name like '%:name%'", nativeQuery=true)
    List<User> findByNameJPQL(@Param("name") String name);

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmailJPQL(@Param("email") String email);

//    @Query("select u from User u where u.name = ?1 limit 5")
//    List<User> findTop5ByNameJPQL(String name);

    // 3. Native Query : Viết câu lệnh native dựa theo database
    @Query(nativeQuery = true, value = "select * from user u where u.name = ?1")
    List<User> findByName_NativeQuery(String name);

    @Query(nativeQuery = true, value = "select * from user u where u.name = ?1 limit 5")
    List<User> findTop5ByName_NativeQuery(String name);

    // Query DTO
    // 1. Query Entity => mapper DTO
    // 2. JPQL
    @Query("select new com.example.demojpa.dto.UserDto(u.id, u.name,u.email) from User u where u.name = ?1")
    List<UserDto> findUserDtoByName(String name);

    // 3. Native Query
    @Query(nativeQuery = true, name = "getUserDtoUsingNativeQuery")
    List<UserDto> getUserDtoUsingNativeQuery();

    // 4. Projection (inteface)
    UserProjection findUserProjectionByEmail(String email);
    //Sap xep

}
