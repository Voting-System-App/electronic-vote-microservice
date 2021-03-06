package com.demo.app.vote.controllers;

import com.demo.app.vote.entities.VotingDate;
import com.demo.app.vote.entities.VotingGroup;
import com.demo.app.vote.services.VotingDateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "Voting Date Api", description = "Api for testing the endpoint for date assignation")
@RequestMapping("/date")
public class VotingDateController {

    private final VotingDateService votingDateService;

    public VotingDateController(VotingDateService votingDateService) {
        this.votingDateService = votingDateService;
    }

    @GetMapping
    public ResponseEntity<Flux<VotingDate>> findAll(){
        Flux<VotingDate> dates = votingDateService.findAll();
        return ResponseEntity.ok(dates);
    }
    @PostMapping
    public ResponseEntity<Mono<VotingDate>> saveDate(@RequestBody VotingDate votingDate){
        return ResponseEntity.ok(votingDateService.save(votingDate));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Mono<VotingDate>> updateDate(@RequestBody VotingDate votingDate,@PathVariable String id){
        return ResponseEntity.ok(votingDateService.update(votingDate,id));
    }
}
