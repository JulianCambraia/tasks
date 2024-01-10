package br.com.juliancambraia.tasks.repository;

import br.com.juliancambraia.tasks.model.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
public class TaskCustomRepository {
  private final MongoOperations mongoOperations;
  
  public TaskCustomRepository(MongoOperations mongoOperations) {
    this.mongoOperations = mongoOperations;
  }
  
  public Page<Task> findPaginated(Task task, Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
    
    ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("priority", "state");
    
    Example<Task> example = Example.of(task, matcher);
    
    Query query = Query.query(Criteria.byExample(example)).with(pageable);
    
    if (task.getPriority() > 0) {
      query.addCriteria(Criteria.where("priority").is(task.getPriority()));
    }
    
    if (task.getState() != null) {
      query.addCriteria(Criteria.where("state").is(task.getState()));
    }
    
    return PageableExecutionUtils.getPage(mongoOperations.find(query, Task.class), pageable, () -> mongoOperations.count(query, Task.class));
  }
}
