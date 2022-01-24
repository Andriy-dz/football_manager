package com.example.footballmanager.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.example.footballmanager.dao.PlayerDao;
import com.example.footballmanager.dao.TeamDao;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.model.TransferPlayer;
import com.example.footballmanager.service.TeamService;
import com.example.footballmanager.service.TransferPlayersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl implements TeamService {
    private static final Integer AMOUNT_PRE_MONTH = 100_000;
    private final TransferPlayersService transferPlayersService;
    private final TeamDao teamDao;
    private final PlayerDao playerDao;

    public TeamServiceImpl(TeamDao teamDao, PlayerDao playerDao, TransferPlayersService transferPlayersService, PlayerDao playerDao1) {
        this.teamDao = teamDao;
        this.transferPlayersService = transferPlayersService;
        this.playerDao = playerDao1;
    }

    @Override
    public Team add(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team update(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team get(Long id) {
        return teamDao.getById(id);
    }

    @Override
    public List<Team> getAll() {
        return teamDao.findAll();
    }

    @Override
    public void delete(Long id) {
        teamDao.deleteById(id);
    }

    @Transactional
    @Override
    public void transferPlayer(Long teamId, Long playerId, Long buyingTeamId) {
        Player player = playerDao.getById(playerId);
        Team buyingTeam = get(buyingTeamId);
        Team playerTeam = get(teamId);

        BigDecimal price = checkCalculation(player.getExperience(),
                player.getAge(), playerTeam.getTransferCosts());

        playerTeam.setBudget(playerTeam.getBudget().add(price));
        playerTeam.getPlayers().remove(player);
        buyingTeam.setBudget(buyingTeam.getBudget().subtract(price));
        buyingTeam.getPlayers().add(player);

        TransferPlayer transferPlayer = new TransferPlayer();
        transferPlayer.setPlayer(player);
        transferPlayer.setTeam(playerTeam);
        transferPlayer.setCost(price);

        transferPlayersService.add(transferPlayer);
        teamDao.save(playerTeam);
        teamDao.save(buyingTeam);
    }

    private BigDecimal checkCalculation(Integer experiencePlayer, Integer agePlayer, Integer transferCosts) {
        BigDecimal transfer = BigDecimal.valueOf(experiencePlayer)
                .multiply(BigDecimal.valueOf(AMOUNT_PRE_MONTH))
                .divide(BigDecimal.valueOf(agePlayer), RoundingMode.CEILING);
        BigDecimal commission = transfer
                .divide(BigDecimal.valueOf(100), RoundingMode.CEILING)
                .multiply(BigDecimal.valueOf(transferCosts));
        return transfer.add(commission);
    }
}
