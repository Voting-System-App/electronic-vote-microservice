package com.demo.app.vote.services;


import com.demo.app.vote.entities.VotingDate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VotingDateService {
    Flux<VotingDate> findAll();
    Mono<VotingDate> save(VotingDate votingDate);
    Mono<VotingDate> update(VotingDate votingDate,String id);
}
