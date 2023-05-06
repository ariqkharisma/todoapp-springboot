package com.ariq.todo.task;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    @NotEmpty(message = "Task name cannot be empty")
    private String task;

    private Boolean isDone;

    @NotEmpty(message = "Task priority cannot be empty")
    private String priority;


    public Task convertToEntity() {
        return new Task(this.task, this.isDone, this.priority);
    }
}
