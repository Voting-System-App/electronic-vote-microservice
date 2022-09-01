package com.demo.app.vote.services;

import com.demo.app.vote.entities.VotingDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VotingDetailService {
    Flux<VotingDetail> findAll();
    Mono<VotingDetail> save(VotingDetail votingDetail);
    Mono<Void> delete(String id);
}
