package com.demo.app.vote.services;


import com.demo.app.vote.entities.VotingDate;
import com.demo.app.vote.entities.VotingGroup;
import org.springframework.format.annotation.DateTimeFormat;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface VotingDateService {
    Flux<VotingDate> findAll();

    Flux<VotingDate> findAllByDate(Date date);
    Flux<VotingGroup> save(VotingDate votingDate);

    Flux<VotingGroup> updateVotingGroupByDateGroup(String name,@DateTimeFormat(pattern = "dd-MM-yyyy") Date date);
    Mono<VotingDate> update(VotingDate votingDate,String id);
}
