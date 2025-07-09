package com.smansu4.taskmanagerwebapi.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

//This class is a dynamo bean
@DynamoDbBean
public class TaskItem {
    private String id;
    private String title;
    private String description;
    private Boolean completed;

    @DynamoDbPartitionKey
    public String getId() { return id; }
    public void setId(String id) {this.id = id;}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description;}
    public void setDescription(String description) { this.description = description;}

    public Boolean getCompleted() { return completed;}
    public void setCompleted(Boolean completed) { this.completed = completed;}
}
