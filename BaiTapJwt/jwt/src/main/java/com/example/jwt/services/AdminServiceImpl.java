package com.example.jwt.services;

import com.example.jwt.entity.*;
import com.example.jwt.repository.*;
import com.example.jwt.request.UpsertBlogRequest;
import com.example.jwt.request.UpsertCategoryRequest;
import com.example.jwt.request.UpsertUserRequest;
import com.github.slugify.Slugify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements IAdminServices{
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(BlogRepository blogRepository, CategoryRepository categoryRepository, CommentRepository commentRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.blogRepository = blogRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<Blog> getAllBlog(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Blog> result = blogRepository.findAll(pageable);
        return result;
    }

    @Override
    public Blog getBlogById(int id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Blog addNewBlog(UpsertBlogRequest request) {
        Slugify slugify = Slugify.builder().build();
        List<Category> list = categoryRepository.findAll();
        Blog blog = new Blog();
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setThumbnail(request.getThumbnail());
        blog.setStatus(request.getStatus());
        blog.setPulishedAt(null);
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setCreatedAt(LocalDateTime.now());
        List<Category> rdList = new ArrayList<>();
        for (int j = 0; j < request.getCategoryIds().size(); j++) {
            Category rdCategory = list.get(request.getCategoryIds().get(j));
            rdList.add(rdCategory);
        blog.setCategories(rdList);
        }
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(int id, Blog blog) {
        Blog blogUpdate = blogRepository.findById(id).orElse(null);
        if (blogUpdate == null){
            throw new RuntimeException("Khong tim thay blog");
        }
        else {
            blogUpdate.setTitle(blog.getTitle());
            blogUpdate.setContent(blog.getContent());
            blogUpdate.setDescription(blog.getDescription());
            blogUpdate.setThumbnail(blog.getThumbnail());
            blogUpdate.setStatus(blog.getStatus());
            blogUpdate.setUpdatedAt(LocalDateTime.now());
            blogUpdate.setCategories(blog.getCategories());
        }
        return blogRepository.save(blogUpdate);
    }

    @Override
    public void deleteBlog(int id) {

    }

    @Override
    public Page<Blog> findBlogByKeyword(String keyword, int page, int pageSize) {
        return null;
    }

    @Override
    public Page<Category> getAllCategory(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Category> result = categoryRepository.findAll(pageable);
        return result;
    }

    @Override
    public Category addNewCategory(UpsertCategoryRequest request) {
        List<Category> list = categoryRepository.findAll();
        Category category = new Category();
        boolean isExist = false;
        for (Category item:list) {
            if (item.getName().equals(request.getName())){
                isExist = true;
            }
        }
        if (isExist == false){
            category.setName(request.getName());
        }
        else {
            throw new RuntimeException("Đã tồn tại rồi");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int id, Category category) {
        List<Category> list = categoryRepository.findAll();
        Category category1 = categoryRepository.findById(id).orElse(null);
        if (category1 == null){
            throw new RuntimeException("Khong tim thay category");
        }
        boolean isExist = false;
        for (Category item:list) {
            if (item.getName().equals(category.getName())){
                isExist = true;
            }
        }
        if (isExist == false){
            category1.setName(category.getName());
        }
        else {
            throw new RuntimeException("Đã tồn tại rồi");
        }
        return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(int id) {

    }

    @Override
    public Page<User> getAllUser(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> result = userRepository.findAll(pageable);
        return result;
    }

    @Override
    public User addNewUser(UpsertUserRequest request) {
        List<Role> list = roleRepository.findAll();
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();

        for (int i = 0;i<request.getRoleIds().size();i++){
            Role role = list.get(request.getRoleIds().get(i));
            roles.add(role);
        }
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(int id, User user) {
        User userUpdate = userRepository.findById(id).orElse(null);
        if (userUpdate == null){
            throw new RuntimeException("Không tìm thấy id");
        }
        else {
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setName(user.getName());
            userUpdate.setAvatar(user.getAvatar());
            userUpdate.setRoles(user.getRoles());
            userRepository.save(userUpdate);
        }
        return userUpdate;
    }

    @Override
    public Page<Comment> getAllComment(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Comment> result = commentRepository.findAll(pageable);
        return result;
    }

    @Override
    public Comment updateComment(int id, Comment comment) {
        Comment comment1 = commentRepository.findById(id).get();
        comment1.setContent(comment.getContent());
        comment1.setUpdatedAt(LocalDateTime.now());
        comment1.setBlog(comment.getBlog());
        comment1.setUser(comment1.getUser());
        commentRepository.save(comment1);
        return comment1;
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
