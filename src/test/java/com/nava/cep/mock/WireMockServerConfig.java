package com.nava.cep.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;


@TestConfiguration
public class WireMockServerConfig {

    // Define a porta onde o WireMock será executado
    public static final int WIREMOCK_PORT = 8089;

    @Bean
    public WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(
            WireMockConfiguration.options()
                .port(WIREMOCK_PORT)
                // O WireMock procurará 'mappings' e '__files' dentro deste diretório
                .usingFilesUnderDirectory("src/test/resources") 
        );
        
        wireMockServer.start();
        System.out.println("WireMock iniciado na porta " + WIREMOCK_PORT);
        
        // O Spring gerencia a parada do servidor ao final do teste.
        return wireMockServer;
    }
}