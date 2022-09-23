package at.htl.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.transaction.Transactional;

public interface Repository<Entity, Identification> extends PanacheRepositoryBase<Entity, Identification> {

    default Entity save(Entity entity) {
        return getEntityManager().merge(entity);
    }

    default boolean removeById(Identification id) {
        return deleteById(id);
    }

    default void remove(Entity entity) {
        delete(entity);
    }
}
