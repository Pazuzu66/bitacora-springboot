package com.bitacora.bitacora.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacora.bitacora.documents.Bitacora;
import com.bitacora.bitacora.repositories.BitacoraDAO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BitacoraServiceImpl  implements BitacoraService{

    @Autowired
    private BitacoraDAO bitacoraDAO;

    @Override
    public Flux<Bitacora> findAll() {        
        return bitacoraDAO.findAll();
    }

    @Override
    public Mono<Bitacora> save(Bitacora bitacora) {        
        return bitacoraDAO.save(bitacora);
    }
    
}
