package com.example.foodList.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int index);
    T save(T entity);
    void deleteByID(int index);
    List<T> findAll();
}
