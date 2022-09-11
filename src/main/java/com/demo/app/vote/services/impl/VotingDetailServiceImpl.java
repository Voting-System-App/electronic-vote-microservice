package com.demo.app.vote.services.impl;

import com.demo.app.vote.entities.VotingDetail;
import com.demo.app.vote.entities.VotingStatus;
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
        votingDetail.getVoting().setVotingStatus(VotingStatus.COMPLETED);
        votingDetail.getVoting().setIsActive(true);
        return votingDetailRepository.save(votingDetail);
    }

    @Override
    public Mono<Long> findAllByCandidateListId(String id) {
        return votingDetailRepository.findAllByCandidateList_Id(id).count();
    }

    @Override
    public Mono<Long> findAllByCandidateListPoliticalPartyId(String id) {
        return votingDetailRepository.findAllByCandidateList_PoliticalParty_Id(id).count();
    }

    @Override
    public Mono<Long> findAllByVoterCityStateName(String name) {
        return votingDetailRepository.findAllByVoter_City(name).count();
    }

    @Override
    public Mono<Long> findAllByVotingId(String id) {
        return votingDetailRepository.findAllByVoting_Id(id).count();
    }

}
