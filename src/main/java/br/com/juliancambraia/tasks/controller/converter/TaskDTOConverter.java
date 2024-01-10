package br.com.juliancambraia.tasks.controller.converter;

import br.com.juliancambraia.tasks.controller.dto.TaskDTO;
import br.com.juliancambraia.tasks.enums.TaskState;
import br.com.juliancambraia.tasks.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskDTOConverter {
  
  public TaskDTO convert(Task task) {
    return Optional.ofNullable(task)
        .map(source -> {
          var dto = new TaskDTO();
          dto.setId(source.getId());
          dto.setTitle(source.getTitle());
          dto.setDescription(source.getDescription());
          dto.setPriority(source.getPriority());
          dto.setState(source.getState());
          return dto;
        }).orElse(null);
  }
  
  public Task convert(TaskDTO dto) {
    return Optional.ofNullable(dto)
        .map(source -> Task.builder()
            .withId(dto.getId())
            .withTitle(dto.getTitle())
            .withDescription(dto.getDescription())
            .withPriority(dto.getPriority())
            .withState(dto.getState())
            .build()
        ).orElse(null);
  }
  
  public Task convert(String id, String title, String description, int priority, TaskState state) {
    return Task.builder()
        .withId(id)
        .withDescription(description)
        .withTitle(title)
        .withPriority(priority)
        .withState(state)
        .build();
  }
  
  public List<TaskDTO> convertList(List<Task> taskList) {
    
    return Optional.ofNullable(taskList)
        .map(array -> array.stream()
            .map(this::convert)
            .toList())
        .orElse(new ArrayList<>());
  }
}
