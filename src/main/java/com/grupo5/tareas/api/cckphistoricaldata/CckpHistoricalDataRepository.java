package com.grupo5.tareas.api.cckphistoricaldata;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CckpHistoricalDataRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public CckpHistoricalDataRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void save(CckpHistoricalData cckpHistoricalData) {
        dynamoDBMapper.save(cckpHistoricalData);
    }

    public void saveAll(List<CckpHistoricalData> cckpHistoricalDataList) {
        cckpHistoricalDataList.forEach(this::save);
    }

    public List<CckpHistoricalData> findAll() {
        return dynamoDBMapper.scan(CckpHistoricalData.class, new DynamoDBScanExpression());
    }

    public CckpHistoricalData findByYearAndMonth(String year, String month) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":year", new AttributeValue().withS(year));
        eav.put(":month", new AttributeValue().withS(month));

        // Define a map for ExpressionAttributeNames to avoid using reserved words
        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#yr", "year");  // Use a placeholder for 'year'
        expressionAttributeNames.put("#mn", "month");  // Use a placeholder for 'month'

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("#yr = :year and #mn = :month")
                .withExpressionAttributeValues(eav)
                .withExpressionAttributeNames(expressionAttributeNames);  // Add the alias mapping

        List<CckpHistoricalData> cckpHistoricalDataList = dynamoDBMapper.scan(CckpHistoricalData.class, scanExpression);
        return cckpHistoricalDataList.isEmpty() ? null : cckpHistoricalDataList.get(0);
    }

}
