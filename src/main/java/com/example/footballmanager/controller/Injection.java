package com.example.footballmanager.controller;

import java.math.BigDecimal;
import com.example.footballmanager.exeption.CustomException;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.service.PlayerService;
import com.example.footballmanager.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Injection {
    private final PlayerService playerService;
    private final TeamService teamService;

    public Injection(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping("/inject")
    public String inject(){
        try {
            Player playerLiver1 = new Player();
            playerLiver1.setFirstName("Мохаммед");
            playerLiver1.setLastName("Салах");
            playerLiver1.setExperience(9);
            playerLiver1.setAge(29);
            Player playerLiver2 = new Player();
            playerLiver2.setFirstName("Диогу");
            playerLiver2.setLastName("Жота");
            playerLiver2.setExperience(2);
            playerLiver2.setAge(25);
            Team team = new Team();
            team.setName("Ливерпуль");
            team.setBudget(BigDecimal.valueOf(1_000_000));
            team.setCountry("Великобритания");
            team.setTown("Ливерпуль");
            team.setTransferCosts(5);
            team.getPlayers().add(playerLiver1);
            team.getPlayers().add(playerLiver2);

            Player playerManchester = new Player();
            playerManchester.setFirstName("Криштиану");
            playerManchester.setLastName("Роналду");
            playerManchester.setExperience(15);
            playerManchester.setAge(36);
            Player playerManchester1 = new Player();
            playerManchester1.setFirstName("Поль");
            playerManchester1.setLastName("Погба");
            playerManchester1.setExperience(8);
            playerManchester1.setAge(28);
            Team team1 = new Team();
            team.setName("Манчестер Юнайтед");
            team.setBudget(BigDecimal.valueOf(2_000_000));
            team.setCountry("Великобритания");
            team.setTown("Траффорд, Большой Манчестер");
            team.setTransferCosts(2);
            team.getPlayers().add(playerManchester);
            team.getPlayers().add(playerManchester1);

            playerService.add(playerLiver1);
            playerService.add(playerLiver2);
            playerService.add(playerManchester);
            playerService.add(playerManchester1);
            teamService.add(team);
            teamService.add(team1);
            return "data created";
        } catch (Exception e) {
            throw new CustomException("Can`t inset initial data", e);
        }
    }
}
