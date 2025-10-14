package com.nava.cep.controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nava.cep.service.ICepService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cep")
@RequiredArgsConstructor
public class CepController {

    private final ICepService cepService;

    @GetMapping("/{cep}")
    public ResponseEntity<Map<String, Object>> buscarCep(@PathVariable String cep) {
        Map<String, Object> result = cepService.buscarCep(cep);

        // Se o serviço retornou um erro controlado, devolve 400 (Bad Request)
        if ("erro".equalsIgnoreCase((String) result.get("status"))) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(result);
        }

        // Caso contrário, retorna 200 (OK)
        return ResponseEntity.ok(result);
    }
}