package com.demo.app.vote.services;

import com.demo.app.vote.entities.Voting;
import com.demo.app.vote.entities.VotingGroup;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VotingService {
    Flux<VotingGroup> findAllGroups();
    Mono<Boolean> isActiveVote(String name);
    Flux<Voting> findAll();
    Mono<Voting> save(Voting voting);
    Mono<Voting> update(Voting voting, String id);
    Mono<String> delete(String id);
}
