package com.demo.app.vote.services.impl;

import com.demo.app.vote.entities.VotingDetail;
import com.demo.app.vote.repositories.VotingDetailRepository;
import com.demo.app.vote.services.VotingDetailService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VotingDetailServiceImpl implements VotingDetailService {

    private final VotingDetailRepository votingDetailRepository;

    public VotingDetailServiceImpl(VotingDetailRepository votingDetailRepository) {
        this.votingDetailRepository = votingDetailRepository;
    }

    @Override
    public Flux<VotingDetail> findAll() {
        return votingDetailRepository.findAll();
    }

    @Override
    public Mono<VotingDetail> save(VotingDetail votingDetail) {
        return votingDetailRepository.save(votingDetail);
    }

    @Override
    public Mono<Void> delete(String id) {
        return votingDetailRepository.deleteById(id);
    }
}
