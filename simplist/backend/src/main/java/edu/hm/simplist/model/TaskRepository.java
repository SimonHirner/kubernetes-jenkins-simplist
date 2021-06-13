package edu.hm.simplist.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    Task findByTitle(String title);

    List<Task> findAllByCompletedIsTrue();

    List<Task> findAllByCompletedIsFalse();

}
