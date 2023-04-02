## Câu 1
@Enitty và @Tabble được dùng để ánh xạ 1 đối tượng (1 class)  trong Java thành 1 bảng trong CSDL
. Thuộc tính name trong @Entity và @Table là để xác định khai báo tên của bảng trong csdl.
Tuy nhiên, 2 annotaiton này có sự khác biệt về cách sử dụng và mục đích của chúng.
Thuộc tính name trong @Entity: được sử dụng để đặt tên cho một thực thể trong hệ thống JPA, nếu không dúng thuộc tính name thì bảng trong csdl sẽ có tên giống với class trong java.
@Table: cho phép người dùng đặt tên bảng khác với tên của class trong java.

## Câu 2:
Bổ sung thêm thuộc tính: "spring.jpa.show-sql = true"

## Câu 3: 
Để đổi tên cột muốn khác với tên thuộc tính ta sử dụng tham số "@Column(name = ten_cot_muon_tao)"

Để yêu cầu tính duy nhất, không trùng lặp dữ liệu ta dùng tham số : "@Column(unique = true)"

Để buộc trường dữ liệu trong cột không được null, ta dùng tham số: "@Column(nullable = false)"

## Câu 4:
Ngay trước khi insert ta sử dụng : "@PrePersist"
Ngay trước khi update ta sử dụng : "@PreUpdate"

## Câu 5:
JpaRepository kế thừa từ 2 interface khác: 
* PagingAndSortingRepository: Cung cấp các phương thức phân trang và sắp xếp cho các truy vấn
* CrudRepository: Cung cấp các phương thức cơ bản cho việc tạo, đọc, cập nhật và xóa các đối tượng Entity

## Câu 6
Khai báo một interface repository thao tác với một Entity tên là Post, kiểu dữ liệu trường Identity là Long, tuân thủ interface JpaRepository

```java
public interface PostRepository extends JpaRepository<Post,Long>{}
```

## Câu 7
Khi một cột được đánh dấu Identity bằng cách sử dụng @Id thì nó sẽ được đánh dấu là cột duy nhất trong CSDL. Vì vậy, không cần dùng thêm @Column(unique = true)

## Câu 8

Trong models Employee
```java
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@NamedQuery(name = "Employee.findAllCustom", query = "SELECT e FROM Employee e")
public class Employee {
    @Id
    private int id;
    private String emailAddress;
    private String firstName;
    private String lastName;
}
```


Repository
```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
     List<Employee> findByEmailAddressAndLastName(String email, String lastName);
     List<Employee> findAllByFirstNameOrLastName(String firstName, String lastName);
     List<Employee> findByLastNameOrderByFistNameAsc(String lastName);
     List<Employee> findByFirstNameIgnoreCase(String firstName);
}

```

## Câu 9:
* @NameQuery là một câu truy vấn được định nghĩa trước, được lưu trữ trong metadata của entity, có thể được sử dụng trong toàn bộ ứng dụng. Nó sử dụng tên để định danh và được thực thi bằng Entity manage

* Trong models Employee ta khai báo
```java
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@NamedQuery(name = "Employee.findAllCustom", query = "SELECT e FROM Employee e")
public class Employee {
    @Id
    private int id;
    private String emailAddress;
    private String firstName;
    private String lastName;
}

```

Trong EmployeeRepository ta khai báo
```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
     List<Employee> findByEmailAddressAndLastName(String email, String lastName);
     List<Employee> findAllByFirstNameOrLastName(String firstName, String lastName);
     List<Employee> findByLastNameOrderByFistNameAsc(String lastName);
     List<Employee> findByFirstNameIgnoreCase(String firstName);

     List<Employee> findAllCustom();
}

```

```java
@Service
public class EmployeeServices {
    @Autowired
    EmployeeRepository employeeRepository;
    EntityManagerFactory emf;
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public List<Employee> findAllCustom(String lastName){
        Query namedQuery = getEntityManager().createNamedQuery("Employee.findAllCustom");
        namedQuery.setParameter("lastName", lastName);
        return (List<Employee>) namedQuery.getResultList();
    }

}

```

* @Query là câu truy vấn được định nghĩa bằng cách viết trực tiếp câu query trong phần thân của phương thức trong repository
```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByEmailAddressAndLastName(String email, String lastName);
    List<Employee> findAllByFirstNameOrLastName(String firstName, String lastName);
    List<Employee> findByLastNameOrderByFistNameAsc(String lastName);
    List<Employee> findByFirstNameIgnoreCase(String firstName);

    List<Employee> findAllCustom();
    
    @Query("SELECT e FROM Employee e WHERE e.lastName = :lastName")
    List<Employee> findByLastName(@Param("lastName") String lastName);
}
```
 ## Câu 10
```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {      
     List<Employee> findByEmailAddressAndLastName(String email, String lastName);   
     List<Employee> findAllByFirstNameOrLastName(String firstName, String lastName);
     List<Employee> findByLastNameOrderByFistNameAsc(String lastName);              
     List<Employee> findByFirstNameIgnoreCase(String firstName);                    
                                                                                    
     List<Employee> findAllCustom();                                                
                                                                                    
     //Phần này là câu 10                                                           
     Page<Employee> findByLastName(String lastName, PageRequest pageRequest);       
     Iterable<Employee> findAllDemoSort(Sort sort);                                 
}                                                                                   
```

### Câu 11
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    //auto update null khi xoa category
    @PreRemove
    private void preRemove() {
        for (Product product : products) {
            product.setCategory(null);
        }
    }
}
```

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();
}
```

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();
}
```

### Câu 13
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post")
public class Post {
    @Id
    private String id;
    private String title;

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
```