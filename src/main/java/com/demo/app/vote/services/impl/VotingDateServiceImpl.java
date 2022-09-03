package com.demo.app.vote.services.impl;

import com.demo.app.vote.entities.VotingDate;
import com.demo.app.vote.entities.VotingGroup;
import com.demo.app.vote.repositories.VotingDateRepository;
import com.demo.app.vote.repositories.VotingGroupRepository;
import com.demo.app.vote.services.VotingDateService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public Flux<VotingDate> findAllByDate(Date date) {
        return votingDateRepository.findByDate(date);
    }

    @Override
    @Transactional
    public Flux<VotingGroup> save(VotingDate votingDate){
        List<String> group = Arrays.asList("A","B","C","D","E","F","G","H","I","J");
        List<String> date = Arrays.asList("07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00");
        List<VotingGroup> list = new ArrayList<>();
        return votingDateRepository.save(votingDate).flatMap(result->{
            for (int i = 0; i < 10; i++) {
                VotingGroup data = new VotingGroup();
                data.setName(String.valueOf(group.get(i)));
                data.setTime(date.get(i));
                data.setIsActive(false);
                data.setVotingDate(result);
                list.add(data);
            }
            return votingGroupRepository.saveAll(list).then(Mono.just(result));
        }).flatMapMany(groupDate-> votingGroupRepository.findByVotingDate_Id(groupDate.getId()));
    }

    @Override
    public Flux<VotingGroup> updateVotingGroupByDateGroup(String name,Date date) {
        return votingGroupRepository.findByVotingDate_Date(date).flatMap(list->{
            list.setIsActive(false);
            return votingGroupRepository.save(list);
        });
    }

    @Override
    @Transactional
    public Mono<VotingDate> update(VotingDate votingDate, String id) {
        return votingDateRepository.findById(id).flatMap(result->{
            result.setDate(votingDate.getDate());
            return votingGroupRepository.findByVotingDate_Id(id).flatMap(groups->{
                        groups.setVotingDate(result);
                        return votingGroupRepository.save(groups);
                    }
            ).then(votingDateRepository.save(result));
        });
    }
}
