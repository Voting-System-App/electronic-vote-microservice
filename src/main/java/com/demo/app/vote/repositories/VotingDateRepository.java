package com.demo.app.vote.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VotingDateRepository extends ReactiveMongoRepository<VotingDate,String> {
}