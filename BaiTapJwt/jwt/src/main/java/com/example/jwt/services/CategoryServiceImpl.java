package com.example.jwt.services;

import com.example.jwt.dto.CategoryDto;
import com.example.jwt.dto.CommentDto;
import com.example.jwt.entity.Blog;
import com.example.jwt.entity.Category;
import com.example.jwt.entity.Comment;
import com.example.jwt.entity.User;
import com.example.jwt.exceptions.CustomException;
import com.example.jwt.repository.BlogRepository;
import com.example.jwt.repository.CategoryRepository;
import com.example.jwt.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    public CategoryServiceImpl(BlogRepository blogRepository, CategoryRepository categoryRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Blog> getAllBlog(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Blog> result = blogRepository.findAllByStatusTrue(pageable);
        return result;
    }

    @Override
    public List<Blog> findBlog(String name) {
        List<Blog> result = new ArrayList<>();
        if (name == ""){
            return result;
        }
        result =  blogRepository.findByTitleContains(name);
        result = result.stream().filter(blog -> blog.getStatus()==true).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> result = categories.stream().map(category -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setUsed(blogRepository.countByCategoriesId(category.getId()));
            return categoryDto;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CategoryDto> categoryUsedMost(String top) {
        try {
            int intTop = Integer.parseInt(top.substring(3));
            if (intTop > 0) {
                List<CategoryDto> categoryDtos = getAllCategory();
                categoryDtos.sort(new Comparator<CategoryDto>() {
                    @Override
                    public int compare(CategoryDto o1, CategoryDto o2) {
                        return Long.compare(o2.getUsed(), o1.getUsed());
                    }
                });
                List<CategoryDto> result = new ArrayList<>();
                for (int i = 0; i < intTop; i++) {
                    result.add(categoryDtos.get(i));
                }
                return result;
            }
            else {
                throw new CustomException("Top nhập vào phải > 0");
            }
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @Override
    public List<Blog> getByCategory(String category) {
        return blogRepository.findByCategoriesName(category).stream().filter(blog -> blog.getStatus() == true).collect(Collectors.toList());

    }

    @Override
    public Blog getBlogByIdAndSlug(int id, String slug) {
        return blogRepository.findByIdAndSlug(id,slug);
    }
    @Override
    public List<CommentDto> listComment(int id){
        List<Comment> comment =commentRepository.findByBlogId(id);
        List<CommentDto> result = comment.stream().map(comment1 -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setComment(comment1.getContent());
            commentDto.setCreatedAt(comment1.getCreatedAt());
            commentDto.setUpdateAt(comment1.getUpdatedAt());
            commentDto.setUsername(comment1.getUser().getName());
            return commentDto;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public String getCommentUser(int id) {
        return commentRepository.findUserByCommentId(id);
    }
}
