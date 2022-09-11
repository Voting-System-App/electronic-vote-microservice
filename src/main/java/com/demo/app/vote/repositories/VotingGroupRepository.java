package com.demo.app.vote.repositories;

import com.demo.app.vote.entities.VotingGroup;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VotingGroupRepository extends ReactiveMongoRepository<VotingGroup,String> {
    Mono<VotingGroup> findByName(String name);
}
