package com.demo.app.vote.services;

import com.demo.app.vote.entities.VotingDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VotingDetailService {
    Flux<VotingDetail> findAll();
    Mono<VotingDetail> save(VotingDetail votingDetail);
    Mono<Long> findAllByCandidateListId(String id);
    Mono<Long> findAllByCandidateListPoliticalPartyId(String id);
    Mono<Long> findAllByVoterCityStateName(String name);
    Mono<Long> findAllByVotingId(String id);
}
