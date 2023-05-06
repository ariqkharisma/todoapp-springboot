package com.ariq.todo.task;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ariq.todo.utils.PriorityValidation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/todos")
    public ResponseEntity<List<TaskResponse>> getAllTasks(@RequestParam("isDone") Optional<Boolean> isDone,
            @RequestParam("priority") Optional<String> priority) {
        
        List<Task> tasksList = this.taskService.getAll(isDone, priority);
        List<TaskResponse> tasksResponse = tasksList.stream().map(task -> task.convertToResponse()).toList();        
        
        return ResponseEntity.ok().body(tasksResponse);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") int id) {
        Task task =  this.taskService.getOne(id);
        TaskResponse taskResponse = task.convertToResponse();

        return ResponseEntity.ok().body(taskResponse);
    }

    @PostMapping("/todos")
    public ResponseEntity<TaskResponse> postTask(@Valid @RequestBody TaskRequest taskRequest) {
        boolean priorityValidation = PriorityValidation.check(taskRequest.getPriority());

        if (!priorityValidation) {
            return ResponseEntity.badRequest().body(null);
        }

        Task newTask = taskRequest.convertToEntity();
        Task savedTask = this.taskService.createOne(newTask);
        TaskResponse taskResponse = savedTask.convertToResponse();

        return ResponseEntity.created(null).body(taskResponse);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable int id, @RequestBody TaskRequest taskRequest) {
        boolean priorityValidation = PriorityValidation.check(taskRequest.getPriority());

        if (!priorityValidation) {
            return ResponseEntity.badRequest().body(null);
        }

        Task task = taskRequest.convertToEntity();
        Task taskupdated = this.taskService.updateOne(id, task);
        TaskResponse taskResponse = taskupdated.convertToResponse();

        return ResponseEntity.ok().body(taskResponse);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTask(@PathVariable int id) {
        this.taskService.deleteOne(id);
    }
}
