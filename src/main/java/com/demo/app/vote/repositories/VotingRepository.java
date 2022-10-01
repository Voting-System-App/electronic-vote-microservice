package com.demo.app.vote.repositories;

import com.demo.app.vote.entities.Voting;
import com.demo.app.vote.entities.VotingStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

@Repository
public interface VotingRepository extends ReactiveMongoRepository<Voting,String> {
    Flux<Voting> findAllByDateBetween(Date startDate, Date endDate);
    Flux<Voting> findAllByVotingStatus(VotingStatus status);
    Flux<Voting> findAllByCityAndVotingStatus(String city, VotingStatus status);
    Flux<Voting> findAllByCityAndVotingStatusAndIdIsNotIn(String city, VotingStatus status, List<String> id);
    Flux<Voting> findAllByCityAndVotingStatusAndIdIsIn(String city, VotingStatus status, List<String> id);
}
