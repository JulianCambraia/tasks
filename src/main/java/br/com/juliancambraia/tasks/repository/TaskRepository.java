package br.com.juliancambraia.tasks.repository;

import br.com.juliancambraia.tasks.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
}