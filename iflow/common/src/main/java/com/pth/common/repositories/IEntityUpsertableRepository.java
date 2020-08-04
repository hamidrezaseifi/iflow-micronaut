package com.pth.common.repositories;


public interface IEntityUpsertableRepository<TEntity> extends IEntityRepository<TEntity> {
    void upsert(TEntity entity);
}
