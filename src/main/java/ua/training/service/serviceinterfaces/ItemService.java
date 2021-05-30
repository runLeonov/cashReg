package ua.training.service.serviceinterfaces;

import ua.training.dao.entity.Model;

public interface ItemService<K, T extends Model>  {
    T getById(K id);

    boolean insert(T entity);

    T update(T entity);

    boolean delete(K id);
}
