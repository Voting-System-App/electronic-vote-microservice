package com.demo.app.vote.services.impl;

import com.demo.app.vote.entities.Voting;
import com.demo.app.vote.entities.VotingDate;
import com.demo.app.vote.entities.VotingGroup;
import com.demo.app.vote.repositories.VotingDateRepository;
import com.demo.app.vote.repositories.VotingGroupRepository;
import com.demo.app.vote.repositories.VotingRepository;
import com.demo.app.vote.services.VotingDateService;
import com.demo.app.vote.utils.DateComparison;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class VotingDateServiceImpl implements VotingDateService {

    private final VotingDateRepository votingDateRepository;
    private final DateComparison dateTime;
    private final VotingGroupRepository votingGroupRepository;
    private final VotingRepository votingRepository;

    public VotingDateServiceImpl(VotingDateRepository votingDateRepository, DateComparison dateTime, VotingGroupRepository votingGroupRepository, VotingRepository votingRepository) {
        this.votingDateRepository = votingDateRepository;
        this.dateTime = dateTime;
        this.votingGroupRepository = votingGroupRepository;
        this.votingRepository = votingRepository;
    }
    private Mono<VotingGroup> updateVotingGroupByDateGroup(String name) {
        Flux<VotingGroup> generalGroup = votingGroupRepository.findAll().flatMap(list->{
            list.setIsActive(false);
            return votingGroupRepository.save(list);
        });
        Mono<VotingGroup> singleGroup = votingGroupRepository.findByName(name).flatMap(list->{
            System.out.println(list);
            list.setIsActive(true);
            return votingGroupRepository.save(list);
        });
        return generalGroup.then(singleGroup);
    }
    private Flux<Voting> updateVotingStatus(Date date){
        return votingRepository.findAllByVotingDate_DateBetween(dateTime.minusDays(date),date).flatMap(result->{
            result.setIsActive(true);
            return votingRepository.save(result);
        });
    }
    @Override
    @Transactional(readOnly = true)
    public Flux<VotingDate> findAll() {
        return votingDateRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<VotingGroup> findAllGroups() {
        return votingGroupRepository.findAll();
    }

    @Override
    @Transactional
    public Mono<VotingDate> update(VotingDate votingDate, String id) {
        return votingDateRepository.findById(id).flatMap(result-> {
            result.setDate(votingDate.getDate());
            return votingRepository.findByVotingDate_Id(id).flatMap(data -> {
                data.setVotingDate(votingDate);
                return votingRepository.save(data);
            }).then(votingDateRepository.save(result));
        });
    }

    @Override
    public Mono<VotingDate> save(VotingDate votingDate) {
        return votingDateRepository.save(votingDate);
    }

    @Scheduled(cron = "0 0 7,17 * * *")
    public void updateVotingGroup(){
        LocalDate localDate = LocalDate.now(ZoneId.of("America/Lima"));
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalTime time = LocalTime.now();
        System.out.println(dateTime.compareHours(time));
        updateVotingGroupByDateGroup(dateTime.compareHours(time)).subscribeOn(Schedulers.immediate()).subscribe();
        updateVotingStatus(date).subscribeOn(Schedulers.immediate()).subscribe();
    }
}
