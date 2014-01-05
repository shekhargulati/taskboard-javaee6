package com.taskboard.service;

import com.taskboard.domain.Taskboard;
import com.taskboard.service.vo.TaskboardVO;

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
    public List<TaskboardVO> findAll() {
        return entityManager.createQuery("select NEW com.taskboard.service.vo.TaskboardVO(t.id,t.name,t.description) FROM Taskboard AS t",TaskboardVO.class).getResultList();
    }

    @Override
    public Taskboard find(Long taskboardId) {
        Taskboard taskboard = entityManager.find(Taskboard.class, taskboardId);
        if(taskboard !=null && taskboard.getTasks()!=null && !taskboard.getTasks().isEmpty()){
            taskboard.getTasks().iterator().next();
        }
        return taskboard;
    }

    @Override
    public void updateTotalPoints(Taskboard taskboard, int points) {
        int currentTotalPoints = taskboard.getTotalPoints();
        int totalPoints = currentTotalPoints + points;
        taskboard.setTotalPoints(totalPoints);
        entityManager.merge(taskboard);
    }
}
