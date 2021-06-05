package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Model;

public interface ItemDAOI<K, T extends Model> {
    /**
     * get entity by id
     *
     * @param id of entity
     * @return object
     */
    T findById(K id);

    /**
     * add new entity
     *
     * @param entity to add
     * @return success of execution
     */
    boolean insert(T entity);

    /**
     * update entity
     *
     * @param entity to update
     * @return updated object
     */
    T update(T entity);

    /**
     * delete entity by id
     *
     * @param id of entity
     * @return success of execution
     */
    boolean delete(K id);
}
