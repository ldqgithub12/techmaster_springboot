package com.example.demojpa.entity;

import com.example.demojpa.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "useInfo", // tên kết quả ở bước 1
                classes = @ConstructorResult(
                        targetClass = UserDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "email", type = String.class)
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getUserDtoUsingNativeQuery", // Đặt tên cho câu lệnh query sử dụng trong repo
        resultSetMapping = "useInfo", // Đặt tên cho kết quả trả về -> sử dụng ở step 2
        query = "select u.id, u.name, u.email from user u") // Định nghĩa câu lệnh native query
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user")
public class User {
    // Tự sinh :
    // 1. Database tự sinh
    // 2. User tự sinh
    // Không tự sinh : User tự thêm, đảm bảo không được trùng ID
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;
}
