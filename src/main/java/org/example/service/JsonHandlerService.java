package org.example.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.model.Status;
import org.example.model.Task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonHandlerService{
    private static final String FILE_PATH = "person.json";
    private final ObjectMapper objectMapper;
    File file;

    public JsonHandlerService(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // <-- pretty print


        file = new File(FILE_PATH);
    }

    public List<Task> readTasks() throws IOException {
        // Ensure the file exists
        if (!file.exists()) {
            file.createNewFile();
            saveTasks(new ArrayList<>()); // initialize empty list
        }

        // If file is empty, initialize it with an empty array
        if (file.length() == 0) {
            saveTasks(new ArrayList<>());
        }

        // Read the tasks from the file
        return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
    }

    public void printTaskPrettyJson() throws IOException {
        List<Task> tasks = readTasks();
        System.out.println(objectMapper.writeValueAsString(tasks));
    }



    public void saveTasks(List<Task> tasks) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, tasks);
    }

    public void addTask(Task task) throws IOException {
        List<Task> tasks = readTasks();
        tasks.add(task);
        saveTasks(tasks);
    }

    public void getTaskTodo() throws IOException {
        List<Task> tasks = readTasks();
        System.out.println(tasks.stream()
                .filter(task -> task.getStatus() == Status.TODO)
                .findAny()
                .orElse(null));
    }

    public void getTaskInProgress() throws IOException {
        List<Task> tasks = readTasks();

        System.out.println(tasks.stream()
                .filter(task -> task.getStatus() == Status.IN_PROGRESS)
                .findAny()
                .orElse(null));
    }

    public void getTaskDone() throws IOException {
        List<Task> tasks = readTasks();
        System.out.println(tasks.stream()
                .filter(task -> task.getStatus() == Status.DONE)
                .findAny()
                .orElse(null));
    }


    public void deleteTasks(Long id) throws IOException {
        List<Task> tasks = readTasks();
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if(removed){
            saveTasks(tasks);
        }
    }

    public void updateTaskDescription(Long id, String description) throws IOException{
        List<Task> tasks = readTasks();
        Task updatedTask = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
        assert updatedTask != null;
        updatedTask.setDescription(description);
        updatedTask.setUpdatedAt(LocalDateTime.now());
        saveTasks(tasks);
    }

    public void updateTaskInProgress(Long id) throws IOException{
        List<Task> tasks = readTasks();
        Task updatedTask = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
        assert updatedTask != null;
        updatedTask.setStatus(Status.IN_PROGRESS);
        updatedTask.setUpdatedAt(LocalDateTime.now());
        saveTasks(tasks);
    }

    public void updateTaskDone(Long id) throws IOException{
        List<Task> tasks = readTasks();
        Task updatedTask = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
        assert updatedTask != null;
        updatedTask.setStatus(Status.DONE);
        updatedTask.setUpdatedAt(LocalDateTime.now());
        saveTasks(tasks);
    }
}
