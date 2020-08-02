package com.pth.iflow.common.repositories;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEntityRepository<TEntity> {

    List<TEntity> getAll();

    Optional<TEntity> getById(UUID id);

    void save(TEntity entity);

    void update(TEntity entity);

    void delete(TEntity entity);
}