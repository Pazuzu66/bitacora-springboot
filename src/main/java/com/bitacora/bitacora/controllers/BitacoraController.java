package com.bitacora.bitacora.controllers;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.bitacora.bitacora.documents.Bitacora;
import com.bitacora.bitacora.services.BitacoraService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bitacora")
public class BitacoraController {
    @Autowired
    private BitacoraService service;
    
    @GetMapping
    public Mono<ResponseEntity<Flux<Bitacora>>> listBitacora(){
        return Mono.just(
            ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.findAll())
        ).defaultIfEmpty(
            ResponseEntity.noContent().build()
        );
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> saveBitacora(@Valid @RequestBody Mono<Bitacora> bitacoraMono){
        Map<String,Object> response = new HashMap<>();

        return bitacoraMono.flatMap( bitacora -> {
            return service.save(bitacora).map( bi -> {
                response.put("bitacora", bi);
                response.put("mensaje", "Todo Ok");
                response.put("timestamp", new Date());
                response.put("id", bi.getId());
                return ResponseEntity.created(URI.create("/api/bitacora/".concat(bi.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
            });
        }).onErrorResume( error -> {
            return Mono.just(error).cast(WebExchangeBindException.class)
                .flatMap( e -> Mono.just( e.getFieldErrors() ))
                .flatMapMany(Flux::fromIterable)
                .map( fieldError -> "El campo "+ fieldError.getField() + " " + fieldError.getDefaultMessage() )
                .collectList()
                .flatMap(list -> {
                    response.put("errors", list);
                    response.put("timestamp", new Date());
                    response.put("status", HttpStatus.BAD_REQUEST.value());
                    return Mono.just(ResponseEntity.badRequest().body(response));
                });
        });
    }
}
