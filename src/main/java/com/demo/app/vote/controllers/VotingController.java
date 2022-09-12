package com.demo.app.vote.controllers;

import com.demo.app.vote.entities.Voting;
import com.demo.app.vote.entities.VotingGroup;
import com.demo.app.vote.services.VotingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "Voting Api", description = "Api for testing the endpoint for voting assignation")
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @GetMapping
    public ResponseEntity<Flux<Voting>> findAll(){
        Flux<Voting> voting = votingService.findAll();
        return ResponseEntity.ok(voting);
    }
    @GetMapping("/groups")
    public ResponseEntity<Flux<VotingGroup>> findAllGroups(){
        Flux<VotingGroup> groups = votingService.findAllGroups();
        return ResponseEntity.ok(groups);
    }
    @GetMapping("/group/{name}/status")
    public Mono<Boolean> findGroupStatus(@PathVariable String name){
        return votingService.isActiveVote(name);
    }
    @PostMapping
    public ResponseEntity<Mono<Voting>> saveVoting(@RequestBody Voting voting){
        return ResponseEntity.ok(votingService.save(voting));
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Voting>> updateVoting(@RequestBody Voting voting, @PathVariable String id){
        return votingService.update(voting,id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<String>> delete(@PathVariable String id){
        return ResponseEntity.ok(votingService.delete(id));
    }
}
