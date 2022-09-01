package com.demo.app.vote.services;

import com.demo.app.vote.entities.VotingDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VotingDetailService {
    Flux<VotingDetail> findAll();
    Mono<VotingDetail> save(VotingDetail votingDetail);
    Mono<VotingDetail> update(VotingDetail votingDetail, String id);
    Mono<Void> delete(String id);
}
