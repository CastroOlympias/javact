package com.david.fitness_bff.connections;

import com.david.fitness_bff.collections.SampleOneModel;
import com.david.fitness_bff.contracts.DTO;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SampleOneConnection {

    private final JdbcTemplate jdbcTemplate;

    // Direct injection of the DB driver into the controller/resolver
    public SampleOneConnection(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // --- READ OPERATION (Query) ---
    @QueryMapping
    public DTO.SampleOneData getSampleOne(@Argument String id) {
        String sql = "SELECT id, db_value FROM sample_table WHERE id = ?";

        System.out.println(">>> 📥 Incoming GraphQL Query Argument 'id': " + id);

        try {
            // Directly querying the database and map to the model shape
            SampleOneModel dbModel = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new SampleOneModel(
                    rs.getLong("id"),
                    rs.getString("db_value")), Long.parseLong(id));

            return new DTO.SampleOneData(dbModel.getId().toString(), dbModel.getDbValue());
        } catch (Exception e) {
            // Fallback placeholder if the database table isn't set up yet
            return new DTO.SampleOneData(id, "Fallback: Resolver direct DB read active");
        }
    }

    // --- WRITE OPERATION (Mutation) ---
    @MutationMapping
    public DTO.SampleOneData createSampleOne(@Argument String value) {
        String insertSql = "INSERT INTO sample_table (db_value) VALUES (?) RETURNING id, db_value";

        try {
            // Directly writing to the database
            SampleOneModel newDbModel = jdbcTemplate.queryForObject(insertSql, (rs, rowNum) -> new SampleOneModel(
                    rs.getLong("id"),
                    rs.getString("db_value")), value);

            return new DTO.SampleOneData(newDbModel.getId().toString(), newDbModel.getDbValue());
        } catch (Exception e) {
            // Fallback placeholder for the sandbox circuit
            return new DTO.SampleOneData("100", "Fallback: Resolver direct DB write active (" + value + ")");
        }
    }
}