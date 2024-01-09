package br.com.juliancambraia.tasks.controller;

import br.com.juliancambraia.tasks.controller.converter.TaskDTOConverter;
import br.com.juliancambraia.tasks.controller.dto.TaskDTO;
import br.com.juliancambraia.tasks.model.Task;
import br.com.juliancambraia.tasks.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  
  private final TaskService service;
  private final TaskDTOConverter converter;
  
  
  public TaskController(TaskService service, TaskDTOConverter converter) {
    this.service = service;
    this.converter = converter;
  }
  
  @GetMapping
  public Mono<List<TaskDTO>> getTasks() {
    return service.list()
        .map(converter::convertList);
  }
  
  @PostMapping
  public Mono<TaskDTO> createTask(@RequestBody Task task) {
    return service.insert(task)
        .map(converter::convert);
  }
}
