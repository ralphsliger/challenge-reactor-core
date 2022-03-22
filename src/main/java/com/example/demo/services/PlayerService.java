package com.example.demo.services;

import com.example.demo.CsvUtilFile;
import com.example.demo.models.Player;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class PlayerService {

    public Flux<Player> getPlayers(){
        return Flux.fromStream(CsvUtilFile.getPlayers().parallelStream());
    }

    public Flux<Player> getPlayersByAgeAndClub(Integer age, String club){
       return Flux.fromStream(CsvUtilFile.getPlayers().parallelStream()).filter( player -> player.age >= age).filter(player -> player.club.equals(club)).distinct();
    }

    public Flux<Player> getPlayersByNationalitySortedByWins(String nationality){
        return Flux.fromStream(CsvUtilFile.getPlayers().parallelStream()).filter(player -> player.national.equals(nationality)).sort((a,player)-> player.winners).distinct();
    }




}
