package com.taskboard.service;

import com.taskboard.domain.Task;
import com.taskboard.domain.Taskboard;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shekhargulati
 * Date: 05/01/14
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TaskService {
   public Task addTask(Taskboard taskboard, Task task);

    List<Task> findTasksForTaskboard(Long taskboardId);
}
