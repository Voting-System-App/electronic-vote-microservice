package com.demo.app.vote.services.impl;

import com.demo.app.vote.entities.Voting;
import com.demo.app.vote.entities.VotingGroup;
import com.demo.app.vote.entities.VotingStatus;
import com.demo.app.vote.repositories.VotingGroupRepository;
import com.demo.app.vote.repositories.VotingRepository;
import com.demo.app.vote.services.VotingService;
import com.demo.app.vote.utils.DateComparison;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class VotingServiceImpl implements VotingService {

    private final VotingRepository votingRepository;
    private final DateComparison dateTime;
    private final VotingGroupRepository votingGroupRepository;

    public VotingServiceImpl(VotingRepository votingRepository, DateComparison dateTime, VotingGroupRepository votingGroupRepository) {
        this.votingRepository = votingRepository;
        this.dateTime = dateTime;
        this.votingGroupRepository = votingGroupRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public Flux<VotingGroup> findAllGroups() {
        return votingGroupRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Voting> findById(String id) {
        return votingRepository.findById(id);
    }
    @Override
    public Mono<Boolean> isActiveVote(String name) {
        return votingGroupRepository.findByName(name).flatMap(result-> result.getIsActive()?Mono.just(true):Mono.just(false));
    }

    @Override
    public Flux<Voting> findByStatus(VotingStatus status) {
        return votingRepository.findAllByVotingStatus(status);
    }

    @Override
    public Flux<Voting> findByCityAndStatus(String city, VotingStatus status) {
        return votingRepository.findAllByCityAndVotingStatus(city, status);
    }

    private Mono<VotingGroup> updateVotingGroupByDateGroup(String name) {
        Flux<VotingGroup> generalGroup = votingGroupRepository.findAll().flatMap(list->{
            list.setIsActive(false);
            return votingGroupRepository.save(list);
        });
        Mono<VotingGroup> singleGroup = votingGroupRepository.findByName(name).flatMap(list->{
            list.setIsActive(true);
            return votingGroupRepository.save(list);
        });
        return generalGroup.then(singleGroup);
    }
    private Flux<Voting> updateStatus(Date date){
        return votingRepository.findAll().flatMap(result->{
            if(result.getDate().before(date)){
                result.setVotingStatus(VotingStatus.COMPLETED);
            }
            return votingRepository.save(result);
        });
    }
    private Flux<Voting> updateVotingStatus(Date date){
        return votingRepository.findAllByDateBetween(dateTime.minusDays(date),date).flatMap(result->{
            result.setIsActive(true);
            return votingRepository.save(result);
        });
    }
    @Override
    public Flux<Voting> findAll() {
        return votingRepository.findAll();
    }

    @Override
    public Mono<Voting> save(Voting voting) {
        voting.setIsActive(false);
        voting.setVotingStatus(VotingStatus.PENDING);
        return votingRepository.save(voting);
    }

    @Override
    public Mono<Voting> update(Voting voting, String id) {
        return votingRepository.findById(id).flatMap(result->{
            result.setDescription(voting.getDescription());
            result.setDate(voting.getDate());
            result.setCity(voting.getCity());
            result.setVotingStatus(voting.getVotingStatus());
            return votingRepository.save(result);
        });
    }

    @Override
    public Mono<String> delete(String id) {
        return votingRepository.deleteById(id).thenReturn("Id eliminado:"+id);
    }

    @Scheduled(cron = "0 0/5 * * * ?",zone = "America/Lima")
    public void updateVotingGroup(){
        LocalDate localDate = LocalDate.now(ZoneId.of("America/Lima"));
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        ZonedDateTime zone = ZonedDateTime.ofInstant(Instant.now(),ZoneId.of("America/Lima"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(zone.format(formatter));
        updateVotingGroupByDateGroup(dateTime.compareHours(time)).subscribeOn(Schedulers.immediate()).subscribe();
        updateVotingStatus(date).subscribeOn(Schedulers.immediate()).subscribe();
        updateStatus(date).subscribeOn(Schedulers.immediate()).subscribe();
    }
}
