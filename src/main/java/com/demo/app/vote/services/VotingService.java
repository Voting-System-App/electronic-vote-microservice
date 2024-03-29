package com.demo.app.vote.services;

import com.demo.app.vote.entities.Voting;
import com.demo.app.vote.entities.VotingGroup;
import com.demo.app.vote.entities.VotingStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface VotingService {
    Flux<VotingGroup> findAllGroups();
    Mono<Voting> findById(String id);
    Mono<Boolean> isActiveVote(String name);
    Flux<Voting> findByStatus(VotingStatus status);
    Flux<Voting> findByCityAndStatus(String city,VotingStatus status);
    Flux<Voting> findByAndUserCityAndStatus(String city, VotingStatus status, String voterId);
    Flux<Voting> findAll();
    Mono<Voting> save(Voting voting);
    Mono<Voting> update(Voting voting, String id);
    Mono<String> delete(String id);
}
