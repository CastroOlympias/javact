package com.david.fitness_bff.connections;

// Import the single master DTO menu
import com.david.fitness_bff.contracts.DTO;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SampleTwoConnection {

    @QueryMapping
    public DTO.SampleTwoData getSampleTwo(@Argument String id) {
        // Access the nested shape via dot-notation
        return new DTO.SampleTwoData(id, true);
    }

    // ⬇️ ADD THIS TO MATCH YOUR SCHEMA MUTATION
    @MutationMapping
    public DTO.SampleTwoData toggleSampleTwo(@Argument String id) {
        return new DTO.SampleTwoData(id, true);
    }
}