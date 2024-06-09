package com.fmatheus.app.model.service.impl;


import com.fmatheus.app.model.entity.Client;
import com.fmatheus.app.model.repository.ClientRepository;
import com.fmatheus.app.model.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;


    @Override
    public List<Client> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public <S extends Client> S save(S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

}
