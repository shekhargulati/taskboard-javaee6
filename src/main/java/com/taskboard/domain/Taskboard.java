package com.taskboard.domain;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shekhargulati
 * Date: 04/01/14
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "taskboards")
@Access(AccessType.FIELD)
public class Taskboard {
    @Id
    @NotNull
    @TableGenerator(table = "ID_GEN", name = "taskboard_gen",allocationSize = 100,initialValue = 1)
    @GeneratedValue(generator = "taskboard_gen")
    private Long id;

    @Column
    @NotNull
    @Size(max = 140)
    private String name;

    @Column
    @Size(max=4000)
    private String description;

    @Column(updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();

    @Column
    @Future
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date start;

    @Column
    @Future
    @Temporal(TemporalType.DATE)
    private Date end;

    @Column
    @Max(100)
    @NotNull
    private int capacity;

    @OneToMany(mappedBy = "taskboard")
    private List<Task> tasks;


    protected Taskboard() {
    }


    public Taskboard(String name, String description, Date start, Date end, int capacity) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Taskboard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", start=" + start +
                ", end=" + end +
                ", capacity=" + capacity +
                '}';
    }
}
