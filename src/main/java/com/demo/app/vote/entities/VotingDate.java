package com.demo.app.vote.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Document(collection = "voting_date")
@Data
public class VotingDate {
    @Id
    private String id;
    @NotEmpty
    private Date date;
}
