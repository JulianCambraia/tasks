package br.com.juliancambraia.tasks.service;

import br.com.juliancambraia.tasks.model.Task;
import br.com.juliancambraia.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TaskService {
  
  private final TaskRepository repository;
  
  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }
  
  public Mono<Task> insert(Task task) {
    return Mono.just(task)
        .map(Task::insert)
        .flatMap(this::save); // .flatMap(it -> this.save(it))
  }
  
  private Mono<Task> save(Task task) {
    return Mono.just(task)
        .map(repository::save);
  }
  
  public Mono<List<Task>> list() {
    return Mono.just(repository.findAll());
  }
}
