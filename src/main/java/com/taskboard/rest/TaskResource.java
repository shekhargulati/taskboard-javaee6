package com.taskboard.rest;

import com.taskboard.domain.Task;
import com.taskboard.domain.Taskboard;
import com.taskboard.service.TaskService;
import com.taskboard.service.TaskboardService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shekhargulati
 * Date: 05/01/14
 * Time: 12:00 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/taskboards/{taskboard_id}/tasks")
public class TaskResource {

    @Inject
    private TaskboardService taskboardService;

    @Inject
    private TaskService taskService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Task addTaskToTaskboard(@PathParam("taskboard_id") Long taskboardId, Task task){
        Taskboard taskboard = taskboardService.find(taskboardId);
        if(taskboard == null){
            throw new RuntimeException(String.format("No taskboard found with id %d",taskboard));
        }
        Task addedTask = taskService.addTask(taskboard, task);
        taskboardService.updateTotalPoints(taskboard,addedTask.getPoints());
        return addedTask;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> allTasksForTaskboard(@PathParam("taskboard_id") Long taskboardId){
        Taskboard taskboard = taskboardService.find(taskboardId);
        if(taskboard == null){
            throw new RuntimeException(String.format("No taskboard found with id %d",taskboard));
        }
        return taskService.findTasksForTaskboard(taskboard);
    }
}
