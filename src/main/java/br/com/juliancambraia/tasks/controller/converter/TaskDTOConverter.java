package br.com.juliancambraia.tasks.controller.converter;

import br.com.juliancambraia.tasks.controller.dto.TaskDTO;
import br.com.juliancambraia.tasks.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskDTOConverter {
  
  public TaskDTO convert(Task task) {
    return Optional.ofNullable(task)
        .map(source -> {
          var dto = new TaskDTO();
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
            .withTitle(dto.getTitle())
            .withDescription(dto.getDescription())
            .withPriority(dto.getPriority())
            .withState(dto.getState())
            .build()
        ).orElse(null);
  }
  
  public List<TaskDTO> convertList(List<Task> taskList) {
    
    return Optional.ofNullable(taskList)
        .map(array -> array.stream()
            .map(this::convert)
            .collect(Collectors.toList()))
        .orElse(new ArrayList<>());
  }
}
