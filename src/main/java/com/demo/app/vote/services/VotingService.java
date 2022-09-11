package com.demo.app.vote.services;

import com.demo.app.vote.entities.Voting;
import com.demo.app.vote.entities.VotingGroup;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VotingService {
    @Transactional(readOnly = true)
    Flux<VotingGroup> findAllGroups();

    Flux<Voting> findAll();
    Mono<Voting> save(Voting voting);
    Mono<Voting> update(Voting voting, String id);
    Mono<String> delete(String id);
}
