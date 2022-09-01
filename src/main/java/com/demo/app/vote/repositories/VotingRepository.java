package com.demo.app.vote.repositories;

import com.demo.app.vote.entities.Voting;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends ReactiveMongoRepository<Voting,String> {
}
