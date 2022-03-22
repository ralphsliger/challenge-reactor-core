package com.example.demo.controllers;

import com.example.demo.models.Player;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class PlayerRestController {

    @Autowired
    PlayerService playerService;

    /*
        http://localhost:8080/players

        http://localhost:8080/players?nationality=Italy
    */
    @GetMapping("/players")
    public Flux<Player> getPlayersOrGetByNationality(@RequestParam(value= "nationality", required = false) Optional<String> nationality){
        return nationality.isPresent() ? playerService.getPlayersByNationalitySortedByWins(nationality.get()) : playerService.getPlayers();
    }

    /*
        http://localhost:8080/players/:age/:club
    */
    @GetMapping("/players/{age}/{club}")
    public Flux<Player> getPlayerByAgeAndClub(@PathVariable(value= "age") Integer age, @PathVariable(value= "club") String club){
        return playerService.getPlayersByAgeAndClub(age, club);
    }










}
