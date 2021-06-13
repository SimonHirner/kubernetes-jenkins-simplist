package edu.hm.simplist.controller;

import edu.hm.simplist.model.Task;
import edu.hm.simplist.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        logger.info("Get all Tasks.");
        return new ResponseEntity<>(taskRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        logger.info("Get all completed Tasks.");
        return new ResponseEntity<>(taskRepository.findAllByCompletedIsTrue(), HttpStatus.OK);
    }

    @GetMapping("/uncompleted")
    public ResponseEntity<List<Task>> getUnCompletedTasks() {
        logger.info("Get all uncompleted Tasks.");
        return new ResponseEntity<>(taskRepository.findAllByCompletedIsFalse(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        logger.info("Get Task with ID \"{}\".", id);
        return taskRepository.findById(id)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        logger.info("Create new Task.");
        taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        logger.info("Update Task with ID \"{}\".", id);
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.save(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable String id) {
        logger.info("Delete Task with ID \"{}\".", id);
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.delete(taskRepository.findById(id).get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
