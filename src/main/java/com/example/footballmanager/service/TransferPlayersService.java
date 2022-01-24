package com.example.footballmanager.service;

import java.util.List;
import com.example.footballmanager.model.TransferPlayer;
import org.springframework.stereotype.Service;

public interface TransferPlayersService {
    TransferPlayer add(TransferPlayer transferPlayers);

    TransferPlayer get(Long id);

    List<TransferPlayer> getAllByTeamId(Long id);

    TransferPlayer update(TransferPlayer transferPlayers);

    void delete(Long id);
}
