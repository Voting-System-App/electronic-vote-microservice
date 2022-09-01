package com.demo.app.vote.repositories;

import com.demo.app.vote.entities.VotingDetail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingDetailRepository extends ReactiveMongoRepository<VotingDetail,String> {
}
