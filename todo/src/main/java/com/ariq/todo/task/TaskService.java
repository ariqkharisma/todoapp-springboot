package com.ariq.todo.task;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAll(Optional<Boolean> optionalDone, Optional<String> optionalPriority) {
        if (optionalPriority.isPresent()) {
            return this.taskRepository.findByPriority(optionalPriority.get());
        }

        if (optionalDone.isPresent()) {
            return this.taskRepository.findByIsDone(optionalDone.get());
        }

        return this.taskRepository.findAll(Sort.by(Direction.DESC, "priority"));
    }

    public Task getOne(int id) {
        return this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException());
    }

    public Task createOne(Task newTask) {
        return this.taskRepository.save(newTask);
    }

    public Task updateOne(int id, Task task) {
        Task existingTask = this.getOne(id);

        existingTask.setTask(task.getTask());

       existingTask.setDone(task.isDone());
        
        existingTask.setPriority(task.getPriority());

        Task taskUpdated = this.taskRepository.save(existingTask);

        return taskUpdated;
    }

    public void deleteOne(int id) {
        this.taskRepository.deleteById(id);
    }

}
