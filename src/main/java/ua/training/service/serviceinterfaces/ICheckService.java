package ua.training.service.serviceinterfaces;

import ua.training.dao.entity.Check;
import ua.training.dao.entity.ProductInCheckStore;

import java.util.List;

public interface ICheckService extends ItemService<Integer, Check> {
    Integer findLast();

    boolean insert(List<ProductInCheckStore> productList);
}
