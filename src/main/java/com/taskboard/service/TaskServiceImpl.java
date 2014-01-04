package com.taskboard.service;

import com.taskboard.domain.Task;
import com.taskboard.domain.Taskboard;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shekhargulati
 * Date: 05/01/14
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class TaskServiceImpl implements TaskService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Task addTask(Taskboard taskboard, Task task) {
        task.setTaskboard(taskboard);
        entityManager.persist(task);
        return task;
    }

    @Override
    public List<Task> findTasksForTaskboard(Long taskboardId) {
        TypedQuery<Task> query = entityManager.createNamedQuery("findTasksByTaskboard",Task.class);
        return query.setParameter("taskboardId",taskboardId).getResultList();
    }

}
