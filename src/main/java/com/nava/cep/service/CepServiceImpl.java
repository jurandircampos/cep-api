package com.nava.cep.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nava.cep.entity.CepQueryLog;
import com.nava.cep.repository.CepQueryLogRepository;
import com.nava.cep.client.CepClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CepServiceImpl implements ICepService {

    private final CepClient cepClient;
    private final CepQueryLogRepository logRepository;

    @Override
    public Map<String, Object> buscarCep(String cep) {
        Map<String, Object> resultado;

        try {
            Map<String, Object> dados = cepClient.buscarCep(cep);

            Object erro = dados.get("erro");
            boolean cepInvalido = erro != null &&
                    (erro.equals(true) || "true".equalsIgnoreCase(erro.toString()));

            if (cepInvalido) {
                CepQueryLog logErro = CepQueryLog.builder()
                        .cep(cep)
                        .dataConsulta(LocalDateTime.now())
                        .erro("CEP não encontrado ou inválido")
                        .build();
                logRepository.save(logErro);

                // Retorna mensagem de erro controlada
                return Map.of(
                    "status", "erro",
                    "mensagem", "CEP não encontrado ou inválido",
                    "cep", cep
                );
            }

            CepQueryLog log = CepQueryLog.builder()
                    .cep((String) dados.get("cep"))
                    .logradouro((String) dados.get("logradouro"))
                    .bairro((String) dados.get("bairro"))
                    .cidade((String) dados.get("localidade"))
                    .uf((String) dados.get("uf"))
                    .dataConsulta(LocalDateTime.now())
                    .build();

            logRepository.save(log);
            resultado = dados;

        } catch (Exception e) {
            CepQueryLog logErro = CepQueryLog.builder()
                    .cep(cep)
                    .dataConsulta(LocalDateTime.now())
                    .erro(e.getMessage())
                    .build();
            logRepository.save(logErro);

            resultado = Map.of(
                "status", "erro",
                "mensagem", "Erro ao consultar o CEP: " + e.getMessage(),
                "cep", cep
            );
        }

        return resultado;
    }
}
