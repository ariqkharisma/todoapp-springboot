package com.ariq.todo.task;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    
    private int id;
    private String task;
    private boolean isDone;
    private String priority;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
