package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Check;

public interface ICheckDAO extends ItemDAOI<Integer, Check> {
    boolean insert();

    Integer findLast();

    boolean insertToDeleted(Check entity);

    Integer getCountOfChecks();

    public Integer getCountOfDeletedChecks();
}
