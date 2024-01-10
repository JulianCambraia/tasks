package br.com.juliancambraia.tasks.service;

import br.com.juliancambraia.tasks.model.Task;
import br.com.juliancambraia.tasks.repository.TaskCustomRepository;
import br.com.juliancambraia.tasks.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TaskService {
  
  private final TaskRepository repository;
  private final TaskCustomRepository taskCustomRepository;
  
  public TaskService(TaskRepository repository, TaskCustomRepository taskCustomRepository) {
    this.repository = repository;
    this.taskCustomRepository = taskCustomRepository;
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
  
  public Page<Task> findPaginated(Task task, Integer pageNumber, Integer pageSize) {
    return taskCustomRepository.findPaginated(task, pageNumber, pageSize);
  }
}
