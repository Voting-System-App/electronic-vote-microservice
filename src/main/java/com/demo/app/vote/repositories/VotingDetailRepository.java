package com.demo.app.vote.repositories;

import com.demo.app.vote.entities.VotingDetail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VotingDetailRepository extends ReactiveMongoRepository<VotingDetail,String> {
    Flux<VotingDetail> findAllByCandidateList_Id(String id);
    Flux<VotingDetail> findAllByCandidateList_PoliticalParty_Id(String id);
    Flux<VotingDetail> findAllByVoter_City(String name);
    Flux<VotingDetail> findAllByVoting_Id(String id);
}
