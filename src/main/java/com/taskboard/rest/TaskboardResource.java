package com.taskboard.rest;

import com.taskboard.domain.Taskboard;
import com.taskboard.service.TaskboardService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Path("/taskboards")
public class TaskboardResource {

    @Inject
    private TaskboardService taskboardService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Taskboard> allTaskboards(){
        List<Taskboard> taskboards = new ArrayList<Taskboard>();
        taskboards.add(new Taskboard("My Taskboar", "My Taskboard Description",new Date(),new Date(),10));
        return taskboards;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Taskboard createdNewTaskboard(Taskboard taskboard) {
        return taskboardService.save(taskboard);

    }




}