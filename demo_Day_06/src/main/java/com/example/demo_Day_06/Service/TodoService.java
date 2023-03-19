package com.example.demo_Day_06.Service;

import com.example.demo_Day_06.exceptions.NotFoundException;
import com.example.demo_Day_06.models.CreateTodoRequest;
import com.example.demo_Day_06.models.Todo;
import com.example.demo_Day_06.models.UpdateTodoRequest;
import com.example.demo_Day_06.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private List<Todo> todoList;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
//        todoList = new ArrayList<>();
//        todoList.add(new Todo(1,"Làm Bài tập",true, LocalDateTime.now(),"normal"));
//        todoList.add(new Todo(2,"Làm Bài tập 2",false, LocalDateTime.now(),"normal"));
//        todoList.add(new Todo(3,"Làm Bài tập 3",false, LocalDateTime.now(),"high"));
 //       todoList.add(new Todo(1,"Làm Bài tập",true));
 //       todoList.add(new Todo(2,"Làm Bài tập 2",false));
  //      todoList.add(new Todo(3,"Làm Bài tập 3",false));

    }
    private int generateId(){
        return (int)Math.floor(Math.random()+(1000-100+1)-100);
    }
    public List<Todo> getAll(){
        return todoRepository.findAll();
    }
    public Todo getById(int id){
        Todo tod = todoRepository.findById(id).get();
        if (tod != null){
            return tod;
        }
        else {
            throw new NotFoundException("Can not find id "+id);
        }
    }
    public Todo createTodo(CreateTodoRequest createTodoRequest){
        Todo todo = Todo.builder()
                .id(generateId())
//                .level(createTodoRequest.getLevel())
                .title(createTodoRequest.getTitle())
                .status(false)
//                .createAt(LocalDateTime.now())
                .build();
        todoRepository.save(todo);
        return todo;
    }
    public Todo updateId(int id, UpdateTodoRequest updateTodoRequest){
      Todo todo = todoRepository.findById(id).get();
      if (todo != null){
          todo.setTitle(updateTodoRequest.getTitle());
          todo.setStatus(updateTodoRequest.getStatus());
          todoRepository.save(todo);
          return todo;
      }
      else throw new NotFoundException("Can not find id "+id);
    }
    public void deleteById(int id){
        todoRepository.deleteById(id);
    }
}
