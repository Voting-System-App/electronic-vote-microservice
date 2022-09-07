package com.demo.app.vote.services.impl;

import com.demo.app.vote.entities.Voting;
import com.demo.app.vote.repositories.VotingRepository;
import com.demo.app.vote.services.VotingService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VotingServiceImpl implements VotingService {

    private final VotingRepository votingRepository;

    public VotingServiceImpl(VotingRepository votingRepository) {
        this.votingRepository = votingRepository;
    }

    @Override
    public Flux<Voting> findAll() {
        return votingRepository.findAll();
    }

    @Override
    public Mono<Voting> save(Voting voting) {
        voting.setIsActive(false);
        return votingRepository.save(voting);
    }

    @Override
    public Mono<Voting> update(Voting voting, String id) {
        return votingRepository.findById(id).flatMap(result->{
            result.setDescription(voting.getDescription());
            result.setVotingStatus(voting.getVotingStatus());
            return votingRepository.save(result);
        });
    }

    @Override
    public Mono<String> delete(String id) {
        return votingRepository.deleteById(id).thenReturn("Id eliminado:"+id);
    }
}
