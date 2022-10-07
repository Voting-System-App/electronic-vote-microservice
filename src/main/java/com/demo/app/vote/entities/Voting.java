package com.demo.app.vote.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Document(collection = "voting")
@Data
public class Voting extends Audit<String>{
    @Id
    private String id;
    private String description;
    @Field(name = "voting_status")
    @Enumerated(EnumType.STRING)
    private VotingStatus votingStatus;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private String city;
    @Field(name = "is_active")
    private Boolean isActive;
}
