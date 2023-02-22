package com.example.demo_Day_06.controller;

import com.example.demo_Day_06.Service.TodoService;
import com.example.demo_Day_06.exceptions.NotFoundException;
import com.example.demo_Day_06.models.CreateTodoRequest;
import com.example.demo_Day_06.models.ErrorResponse;
import com.example.demo_Day_06.models.Todo;
import com.example.demo_Day_06.models.UpdateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @GetMapping("/list")
    public List<Todo> getAllToDo(){
        return todoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
//        try{
//            return ResponseEntity.ok(todoService.getById(id));
//        }
//        catch (NotFoundException ex){
//            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage());
//            return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
//        }
        return ResponseEntity.ok(todoService.getById(id));
    }

    @PostMapping("/")
    public Todo createTodo(@RequestBody @Valid CreateTodoRequest request){
        return todoService.createTodo(request);
    }

    @PutMapping("/{id}")
    public Todo updateToDo(@PathVariable int id, @RequestBody UpdateTodoRequest updateTodoRequest){
        return todoService.updateId(id,updateTodoRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable int id){
        todoService.deleteById(id);
    }
}
