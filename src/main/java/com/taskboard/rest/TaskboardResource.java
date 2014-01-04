package com.taskboard.rest;

import com.taskboard.domain.Taskboard;
import com.taskboard.service.TaskboardService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/taskboards")
public class TaskboardResource {

    @Inject
    private TaskboardService taskboardService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Taskboard createdNewTaskboard(Taskboard taskboard) {
        return taskboardService.save(taskboard);

    }




}