package com.example.demoCosmos.repo;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.example.demoCosmos.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends CosmosRepository<Todo, String> {

    @Query(value = "SELECT * FROM c")
    List<Todo> getAllTodoList();
}
