package com.example.footballmanager.service.impl;

import java.util.List;
import com.example.footballmanager.dao.TransferPlayersDao;
import com.example.footballmanager.model.TransferPlayer;
import com.example.footballmanager.service.TransferPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferPlayersServiceImpl implements TransferPlayersService {
    private final TransferPlayersDao transferPlayersDao;

    public TransferPlayersServiceImpl(TransferPlayersDao transferPlayersDao) {
        this.transferPlayersDao = transferPlayersDao;
    }

    @Override
    public TransferPlayer add(TransferPlayer transferPlayers) {
        return transferPlayersDao.save(transferPlayers);
    }

    @Override
    public TransferPlayer get(Long id) {
        return transferPlayersDao.getById(id);
    }

    @Override
    public List<TransferPlayer> getAllByTeamId(Long id) {
        return transferPlayersDao.getAllByTeamId(id);
    }

    @Override
    public TransferPlayer update(TransferPlayer transferPlayers) {
        return transferPlayersDao.save(transferPlayers);
    }

    @Override
    public void delete(Long id) {
        transferPlayersDao.deleteById(id);
    }
}
