package com.demo.app.vote.controllers;

import com.demo.app.vote.entities.VotingDetail;
import com.demo.app.vote.services.VotingDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "Voting Detail Api", description = "Api for testing the endpoint for voting detail assignation")
@RequestMapping("/vote/detail")
public class VotingDetailController {

    private final VotingDetailService votingDetailService;

    public VotingDetailController(VotingDetailService votingDetailService) {
        this.votingDetailService = votingDetailService;
    }

    @GetMapping
    public ResponseEntity<Flux<VotingDetail>> findAll(){
        Flux<VotingDetail> voting = votingDetailService.findAll();
        return ResponseEntity.ok(voting);
    }
    @PostMapping
    public ResponseEntity<Mono<VotingDetail>> saveVoting(@RequestBody VotingDetail votingDetail){
        return ResponseEntity.ok(votingDetailService.save(votingDetail));
    }

    @GetMapping("/graphic/electoral/voting/{id}")
    public ResponseEntity<Mono<Long>> findAllByVotingId(@PathVariable String id){
        return ResponseEntity.ok(votingDetailService.findAllByVotingId(id));
    }
    @GetMapping("/graphic/candidate/{id}")
    public ResponseEntity<Mono<Long>> findAllByCandidateId(@PathVariable String id){
        return ResponseEntity.ok(votingDetailService.findAllByCandidateListId(id));
    }
    @GetMapping("/graphic/state/{name}")
    public ResponseEntity<Mono<Long>> findAllByCityName(@PathVariable String name){
        return ResponseEntity.ok(votingDetailService.findAllByVoterCityStateName(name));
    }
    @GetMapping("/graphic/political/party/{id}")
    public ResponseEntity<Mono<Long>> findAllByPoliticalParty(@PathVariable String id){
        return ResponseEntity.ok(votingDetailService.findAllByCandidateListPoliticalPartyId(id));
    }
}
