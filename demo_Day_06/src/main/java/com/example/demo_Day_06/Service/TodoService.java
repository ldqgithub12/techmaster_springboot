package com.example.demo_Day_06.Service;

import com.example.demo_Day_06.exceptions.NotFoundException;
import com.example.demo_Day_06.models.CreateTodoRequest;
import com.example.demo_Day_06.models.Todo;
import com.example.demo_Day_06.models.UpdateTodoRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoService {
    private List<Todo> todoList;

    public TodoService() {
        todoList = new ArrayList<>();
//        todoList.add(new Todo(1,"Làm Bài tập",true, LocalDateTime.now(),"normal"));
//        todoList.add(new Todo(2,"Làm Bài tập 2",false, LocalDateTime.now(),"normal"));
//        todoList.add(new Todo(3,"Làm Bài tập 3",false, LocalDateTime.now(),"high"));
        todoList.add(new Todo(1,"Làm Bài tập",true));
        todoList.add(new Todo(2,"Làm Bài tập 2",false));
        todoList.add(new Todo(3,"Làm Bài tập 3",false));

    }
    private int generateId(){
        return (int)Math.floor(Math.random()+(1000-100+1)-100);
    }
    public List<Todo> getAll(){
        return todoList;
    }
    public Todo getById(int id){
        for (Todo t: todoList){
            if (Objects.equals(t.getId(),id)){
                return t;
            }
        }
        throw new NotFoundException("Not found todo with id: "+id);
    }
    public Todo createTodo(CreateTodoRequest createTodoRequest){
        Todo todo = Todo.builder()
                .id(generateId())
//                .level(createTodoRequest.getLevel())
                .title(createTodoRequest.getTitle())
                .status(false)
//                .createAt(LocalDateTime.now())
                .build();
        todoList.add(todo);
        return todo;
    }
    public Todo updateId(int id, UpdateTodoRequest updateTodoRequest){
        for (Todo t: todoList){
            if (Objects.equals(t.getId(),id)){
                t.setTitle(updateTodoRequest.getTitle());
                t.setStatus(updateTodoRequest.getStatus());
//                t.setLevel(updateTodoRequest.getLevel());
                return t;
            }
        }
        return null;
    }
    public void deleteById(int id){
        todoList.removeIf(todo -> todo.getId() == id);
    }
}
