package com.taskboard.service;

import com.taskboard.domain.Taskboard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: shekhargulati
 * Date: 04/01/14
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class TaskboardServiceImpl implements TaskboardService {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Taskboard save(Taskboard taskboard) {
        entityManager.persist(taskboard);
        return taskboard;
    }
}
