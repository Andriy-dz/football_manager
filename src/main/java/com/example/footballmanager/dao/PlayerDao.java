package com.example.footballmanager.dao;

import java.util.List;
import com.example.footballmanager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDao extends JpaRepository<Player, Long> {
    @Query("FROM Team t LEFT JOIN FETCH Player p WHERE t.id = ?1")
    List<Player> findAllByTeamId(Long id);
}
