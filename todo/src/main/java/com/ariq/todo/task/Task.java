package com.ariq.todo.task;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String task;
    private boolean isDone;
    private String priority;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
    
    public Task(@NotEmpty(message = "Task name cannot be empty") String task,
            @NotEmpty(message = "Task priority cannot be empty") String priority) {
        this.task = task;
        this.priority = priority;
    }

    public Task(String task, Boolean isDone, String priority) {
        this.task = task;
        this.isDone = isDone;
        this.priority = priority;
    }    

    public TaskResponse convertToResponse() {
        return new TaskResponse(this.id, this.task, this.isDone, this.priority, this.createdAt, this.updatedAt);
    }
}
