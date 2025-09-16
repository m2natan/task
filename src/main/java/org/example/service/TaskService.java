package org.example.service;

import org.example.model.Status;
import org.example.model.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class TaskService {
    private final JsonHandlerService jsonHandlerService = new JsonHandlerService();
    private long nextId = 1;
    public void AddTask(String description) throws IOException {
        List<Task> tasks = jsonHandlerService.readTasks();
        if(!tasks.isEmpty()){
            nextId = tasks.getLast().getId() + 1;
        }
        Task task = new Task(nextId, description, Status.TODO, LocalDateTime.now(), LocalDateTime.now());
        jsonHandlerService.addTask(task);
    }

    public void UpdateTask(Long id, String description) throws IOException {
        jsonHandlerService.updateTaskDescription(id, description);
    }

    public void DeleteTask(Long id) throws IOException {
        jsonHandlerService.deleteTasks(id);
    }

    public void UpdateStatusToInProgress(Long id) throws IOException {
        jsonHandlerService.updateTaskInProgress(id);
    }

    public void UpdateStatusToDone(Long id) throws IOException {
        jsonHandlerService.updateTaskDone(id);
    }

    public void ListTasks() throws IOException {
        jsonHandlerService.printTaskPrettyJson();
    }

    public void ListTasksDone() throws IOException {
        jsonHandlerService.getTaskDone();
    }

    public void ListTasksInProgress() throws IOException {
        jsonHandlerService.getTaskInProgress();
    }

    public void ListTasksTodo() throws IOException {
        jsonHandlerService.getTaskTodo();
    }


}
