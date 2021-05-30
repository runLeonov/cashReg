package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Model;

public interface ItemDAOI<K, T extends Model> {
    T findById(K id);

    boolean insert(T entity);

    T update(T entity);

    boolean delete(K id);
}
