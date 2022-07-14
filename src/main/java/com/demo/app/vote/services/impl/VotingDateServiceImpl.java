package com.demo.app.vote.services.impl;

import com.demo.app.vote.entities.VotingDate;
import com.demo.app.vote.entities.VotingGroup;
import com.demo.app.vote.repositories.VotingDateRepository;
import com.demo.app.vote.repositories.VotingGroupRepository;
import com.demo.app.vote.services.VotingDateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Time;

@Service
public class VotingDateServiceImpl implements VotingDateService {

    private final VotingDateRepository votingDateRepository;

    private final VotingGroupRepository votingGroupRepository;

    public VotingDateServiceImpl(VotingDateRepository votingDateRepository, VotingGroupRepository votingGroupRepository) {
        this.votingDateRepository = votingDateRepository;
        this.votingGroupRepository = votingGroupRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<VotingDate> findAll() {
        return votingDateRepository.findAll();
    }

    @Override
    @Transactional
    public Mono<VotingDate> save(VotingDate votingDate) {
        VotingGroup data = new VotingGroup();
        data.setName("A");
        data.setTime(Time.valueOf("07:00"));
        return votingDateRepository.save(votingDate);
    }

    @Override
    @Transactional
    public Mono<VotingDate> update(VotingDate votingDate, String id) {
        return votingDateRepository.findById(id).flatMap(result->{
            result.setDate(votingDate.getDate());
            return votingDateRepository.save(result);
        });
    }
}
