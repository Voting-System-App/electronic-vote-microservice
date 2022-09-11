package com.demo.app.vote.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "voting_date")
@Data
public class VotingDate extends Audit{
    @Id
    private String id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
}
