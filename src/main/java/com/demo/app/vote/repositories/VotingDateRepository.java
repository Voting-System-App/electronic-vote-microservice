package com.demo.app.vote.repositories;

import com.demo.app.vote.entities.VotingDate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;

@Repository
public interface VotingDateRepository extends ReactiveMongoRepository<VotingDate,String> {
}