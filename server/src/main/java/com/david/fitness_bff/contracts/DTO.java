package com.david.fitness_bff.contracts;

// The Master Menu Class (Matches the filename DTO.java)
public class DTO {

    // Modular Item Menu 1 - Single-line immutable record
    public record SampleOneData(String id, String value) {}

    // Modular Item Menu 2 - Single-line immutable record
    public record SampleTwoData(String id, boolean flag) {}
}