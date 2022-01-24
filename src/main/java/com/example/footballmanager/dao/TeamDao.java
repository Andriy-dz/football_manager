package com.example.footballmanager.dao;

import com.example.footballmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeamDao extends JpaRepository<Team, Long> {
}
