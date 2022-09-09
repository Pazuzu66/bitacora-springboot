package com.bitacora.bitacora.services;

import com.bitacora.bitacora.documents.Bitacora;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BitacoraService {
    public Flux<Bitacora> findAll();
    public Mono<Bitacora> save(Bitacora bitacora);    
}
