package ua.training.dao.daoimpl.interfaces;

import ua.training.dao.entity.Check;

public interface ICheckDAO extends ItemDAOI<Integer, Check> {


    /**
     * Add new check
     *
     * @return success of execution
     */
    boolean insert();

    /**
     * Get id of last check
     *
     * @return the id of the last check
     */
    Integer findLast();


    /**
     * Add deleted check to database with deleted checks
     *
     * @param entity check object
     * @return success of execution
     */
    boolean insertToDeleted(Check entity);

    /**
     * Get count of checks
     *
     * @return count of existing checks
     */
    Integer getCountOfChecks();


    /**
     * Get count of deleted checks
     *
     * @return count of deleted checks
     */
    Integer getCountOfDeletedChecks();
}
