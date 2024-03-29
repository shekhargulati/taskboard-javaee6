package com.taskboard.domain;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shekhargulati
 * Date: 04/01/14
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tasks")
@Access(AccessType.FIELD)
@NamedQuery(name="findTasksByTaskboard",query = "SELECT t from Task t where taskboard =:taskboard")
public class Task {

    @NotNull
    @Id
    @TableGenerator(table = "ID_GEN",name = "task_gen",initialValue = 10000,allocationSize = 100)
    @GeneratedValue(generator = "task_gen")
    private Long id;

    @Column
    @NotNull
    @Size(max=140)
    private String task;

    @Column
    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TaskTypes")
    private List<String> tags = new ArrayList<String>();

    @Column
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @NotNull
    @Min(0)
    @Max(10)
    private int points;

    @Column
    @NotNull
    private boolean done = false;

    @Column(updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedAt;

    @ManyToOne
    @JoinColumn(name = "taskboard_id")
    @NotNull
    @JsonBackReference
    private Taskboard taskboard;

    @Transient
    private long taskboardId;


    protected Task() {
    }

    public Task(String task, List<String> tags, Date dueDate, int points) {
        this.task = task;
        this.tags = tags;
        this.dueDate = dueDate;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Taskboard getTaskboard() {
        return taskboard;
    }

    public void setTaskboard(Taskboard taskboard) {
        this.taskboard = taskboard;
    }

    public long getTaskboardId() {
        return taskboardId;
    }

    public void setTaskboardId(long taskboardId) {
        this.taskboardId = taskboardId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", tags=" + tags +
                ", dueDate=" + dueDate +
                ", points=" + points +
                ", done=" + done +
                ", createdOn=" + createdOn +
                ", finishedAt=" + finishedAt +
                '}';
    }
}
