package com.demo.app.vote.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import java.sql.Time;

@Document(collection = "voting_group")
@Data
public class VotingGroup {
    @Id
    private String id;
    @NotEmpty
    private String name;
    @JsonFormat(pattern = "HH:mm")
    private Time time;
    @Field(name = "is_active")
    private Boolean isActive;
    @ManyToOne
    private VotingDate votingDate;
}
