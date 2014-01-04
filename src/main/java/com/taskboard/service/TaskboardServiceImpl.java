package com.taskboard.service;

import com.taskboard.domain.Taskboard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    @Override
    public List<Taskboard> findAll() {
        return entityManager.createQuery("select t from Taskboard t",Taskboard.class).getResultList();
    }
}
