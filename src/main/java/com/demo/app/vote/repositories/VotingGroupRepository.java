package com.demo.app.vote.repositories;

import com.demo.app.vote.entities.VotingGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;

@Repository
public interface VotingGroupRepository extends ReactiveMongoRepository<VotingGroup,String> {
    Flux<VotingGroup> findByVotingDate_Id(String id);
    Flux<VotingGroup> findByNameAndAndVotingDate_Date(String name,Date date);
    Flux<VotingGroup> findByVotingDate_Date(Date date);
}
