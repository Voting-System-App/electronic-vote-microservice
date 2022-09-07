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
    private Flux<VotingGroup> updateVotingGroupByDateGroup(String name,Date date) {
        Flux<VotingGroup> listGeneralGroup = votingGroupRepository.findAllByVotingDate_DateBetween(dateTime.minusDays(date),date).flatMap(list->{
            list.setIsActive(false);
            return votingGroupRepository.save(list);
        });
        Flux<VotingGroup> listSingleGroup = votingGroupRepository.findAllByNameAndAndVotingDate_DateBetween(name,dateTime.minusDays(date),date).flatMap(list->{
            list.setIsActive(true);
            return votingGroupRepository.save(list);
        });
        return listGeneralGroup.thenMany(listSingleGroup);
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
        }).flatMapMany(groupDate-> votingGroupRepository.findAllByVotingDate_Id(groupDate.getId()));
    }
    @Override
    @Transactional
    public Mono<VotingDate> update(VotingDate votingDate, String id) {
        return votingDateRepository.findById(id).flatMap(result->{
            result.setDate(votingDate.getDate());
            return votingGroupRepository.findAllByVotingDate_Id(id).flatMap(groups->{
                        groups.setVotingDate(result);
                        return votingGroupRepository.save(groups);
                    }
            ).flatMap(chain-> votingRepository.findByVotingDate_Id(id).flatMap(data->{
               data.setVotingDate(chain.getVotingDate());
               return votingRepository.save(data);
            })).then(votingDateRepository.save(result));
        });
    }
    @Scheduled(cron = "0 0 7,17 * * *")
    public void updateVotingGroup(){
        LocalDate localDate = LocalDate.now(ZoneId.of("America/Lima"));
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        updateVotingGroupByDateGroup(dateTime.compareHours(time),date).subscribeOn(Schedulers.immediate()).subscribe();
        updateVotingStatus(date).subscribeOn(Schedulers.immediate()).subscribe();
    }
}
