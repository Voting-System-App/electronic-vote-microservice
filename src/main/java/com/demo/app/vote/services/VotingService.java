package com.demo.app.vote.services;

import com.demo.app.vote.entities.Voting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VotingService {
    Flux<Voting> findAll();
    Mono<Voting> save(Voting voting);
    Mono<Voting> update(Voting voting, String id);
    Mono<String> delete(String id);
}
