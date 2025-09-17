package org.task;

import org.task.service.TaskService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("please write a valid action");
            return;
        }

        String action;
        TaskService taskService = new TaskService();
        String key1, key2;

        try{
            action = args[0];
            switch (action) {
                case "add" -> {
                    key1 = args[1];
                    taskService.AddTask(key1);
                }
                case "update" -> {
                    key1 = args[1];
                    key2 = args[2];
                    System.out.println(key1 + ' ' + key2);
                    taskService.UpdateTask(Long.valueOf(key1), key2);
                }
                case "delete" -> {
                    key1 = args[1];
                    taskService.DeleteTask(Long.valueOf(key1));
                }
                case "mark-in-progress" -> {
                    key1 = args[1];
                    taskService.UpdateStatusToInProgress(Long.valueOf(key1));
                }
                case "mark-done" -> {
                    key1 = args[1];
                    taskService.UpdateStatusToDone(Long.valueOf(key1));
                }
                case "list" ->
                {
                    if (args.length == 2){
                        key1 = args[1];
                        if (key1.equals("todo")){
                            taskService.ListTasksTodo();
                        }
                        else if(key1.equals("in-progress")){
                            taskService.ListTasksInProgress();
                        }
                        else{
                            taskService.ListTasksDone();
                        }
                    }else{
                        taskService.ListTasks();
                    }
                }
                default -> System.out.println("default");
            }
        }catch (IndexOutOfBoundsException indexOutOfBoundsException){
            System.out.println("please write a valid action");
        }catch (Exception exception){
            System.out.println("something wrong on our end");
        }


    }
}