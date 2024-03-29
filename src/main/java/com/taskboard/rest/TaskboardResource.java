package com.taskboard.rest;

import com.taskboard.domain.Taskboard;
import com.taskboard.service.TaskboardService;
import com.taskboard.service.vo.TaskboardVO;

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
    public List<TaskboardVO> allTaskboards(){
        List<TaskboardVO> taskboards = taskboardService.findAll();
        return taskboards;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Taskboard createdNewTaskboard(Taskboard taskboard) {
        return taskboardService.save(taskboard);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{taskboard_id}")
    public Taskboard viewTaskboard(@PathParam("taskboard_id") Long taskboardId){
        return taskboardService.find(taskboardId);
    }

}