package com.grupo5.tareas.api.files;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FileRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public FileRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void save(File file) {
        dynamoDBMapper.save(file);
    }

    public File findById(String id) {
        return dynamoDBMapper.load(File.class, id);
    }

    public List<File> findAll() {
        return dynamoDBMapper.scan(File.class, new DynamoDBScanExpression());
    }

    public List<File> findByAssignmentId(String assignmentId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":assignmentId", new AttributeValue().withS(assignmentId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("assignment_id = :assignmentId")
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.scan(File.class, scanExpression);
    }

    public Boolean delete(String id) {
        try {
            File file = findById(id);
            dynamoDBMapper.delete(file);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
