package com.nava.cep.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class CepClient {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> buscarCep(String cep) {
        try {
            //Faz a requisição ao ViaCEP
            Map<String, Object> response = restTemplate.getForObject(VIA_CEP_URL, Map.class, cep);

            //Se o ViaCEP retornou {"erro": true}, devolve como está
            if (response != null && response.containsKey("erro")) {
                return response;
            }

            //Se veio null (por algum motivo), tratamos como erro
            if (response == null) {
                Map<String, Object> erro = new HashMap<>();
                erro.put("erro", true);
                erro.put("mensagem", "Resposta vazia do ViaCEP");
                return erro;
            }

            //Caso contrário, tudo certo
            return response;

        } catch (RestClientException e) {
            // Em caso de erro de rede, timeout, 404, etc.
            Map<String, Object> erro = new HashMap<>();
            erro.put("erro", true);
            erro.put("mensagem", "Falha ao consultar o serviço ViaCEP: " + e.getMessage());
            return erro;
        }
    }
}

