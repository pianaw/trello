package ru.kpfu.trelloapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudService<T, ID> {
    Page<T> findAll(Pageable pageable);
    Optional<T> findById(ID id);
    T save(T t);
    Boolean delete(T t);
    Boolean deleteById(ID id);
}
