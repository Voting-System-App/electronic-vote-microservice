package com.demo.app.vote.repositories;

import com.demo.app.vote.entities.Voting;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Repository
public interface VotingRepository extends ReactiveMongoRepository<Voting,String> {
    Mono<Voting> findByVotingDate_Id(String id);
    Flux<Voting> findAllByVotingDate_DateBetween(Date startDate, Date endDate);
}
