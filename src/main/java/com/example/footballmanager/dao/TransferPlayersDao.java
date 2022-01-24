package com.example.footballmanager.dao;

import java.util.List;
import com.example.footballmanager.model.TransferPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferPlayersDao extends JpaRepository<TransferPlayer, Long> {
    @Query("FROM TransferPlayer tp WHERE tp.team.id = ?1")
    List<TransferPlayer> getAllByTeamId(Long id);
}
