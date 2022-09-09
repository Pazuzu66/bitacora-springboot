package com.bitacora.bitacora.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bitacora.bitacora.documents.Bitacora;

public interface BitacoraDAO extends ReactiveMongoRepository<Bitacora,String>{
    
}
