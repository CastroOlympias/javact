package com.david.fitness_bff.collections;

public class SampleOneModel {
    // These fields map directly to your PostgreSQL columns
    private Long id;
    private String dbValue;

    // Default constructor required by database row-mappers
    public SampleOneModel() {}

    public SampleOneModel(Long id, String dbValue) {
        this.id = id;
        this.dbValue = dbValue;
    }

    // Getters and Setters for database hydration
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDbValue() { return dbValue; }
    public void setDbValue(String dbValue) { this.dbValue = dbValue; }
}