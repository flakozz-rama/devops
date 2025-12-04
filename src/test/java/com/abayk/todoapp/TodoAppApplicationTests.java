package com.abayk.todoapp;

import com.abayk.todoapp.controller.TaskController;
import com.abayk.todoapp.model.Task;
import com.abayk.todoapp.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoAppApplicationTests {

    @Autowired
    private TaskController taskController;

    @Autowired
    private TaskService taskService;

    @Test
    void contextLoads() {
        assertNotNull(taskController);
        assertNotNull(taskService);
    }

    @Test
    void testCreateAndGetTask() {
        Task task = new Task("Test Task", "Test Description", false);
        Task createdTask = taskService.createTask(task);

        assertNotNull(createdTask.getId());
        assertEquals("Test Task", createdTask.getTitle());
        assertEquals("Test Description", createdTask.getDescription());
        assertFalse(createdTask.isCompleted());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task("Original Title", "Original Description", false);
        Task createdTask = taskService.createTask(task);

        createdTask.setTitle("Updated Title");
        createdTask.setCompleted(true);
        Task updatedTask = taskService.updateTask(createdTask.getId(), createdTask);

        assertEquals("Updated Title", updatedTask.getTitle());
        assertTrue(updatedTask.isCompleted());
    }

    @Test
    void testGetAllTasks() {
        int initialSize = taskService.getAllTasks().size();

        taskService.createTask(new Task("Task 1", "Description 1", false));
        taskService.createTask(new Task("Task 2", "Description 2", true));

        assertEquals(initialSize + 2, taskService.getAllTasks().size());
    }
}
