package com.ariq.todo.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Task not found")
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}

