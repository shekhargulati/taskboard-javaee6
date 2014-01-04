package com.taskboard.service;

import com.taskboard.domain.Taskboard;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shekhargulati
 * Date: 04/01/14
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TaskboardService {

    public Taskboard save(Taskboard taskboard);


    List<Taskboard> findAll();

    Taskboard find(Long taskboardId);
}
