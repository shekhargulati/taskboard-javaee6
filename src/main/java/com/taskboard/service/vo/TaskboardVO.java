package com.taskboard.service.vo;

/**
 * Created with IntelliJ IDEA.
 * User: shekhargulati
 * Date: 05/01/14
 * Time: 5:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskboardVO {
    private Long id;
    private String name;
    private String description;

    public TaskboardVO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

