package com.nava.cep.mock;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.nava.cep.service.ICepService;

import static org.assertj.core.api.Assertions.assertThat;

@Import(WireMockServerConfig.class)
@SpringBootTest(properties = "spring.profiles.active=test")
public class CepIntegrationTest {

    @Autowired
    private ICepService cepService;

    @Test
    void deveBuscarCepDoMock() {
        // Chama o mock configurado
        Map<String, Object> dados = cepService.buscarCep("18040260");

        // Valida o retorno formatado com traço
        assertThat(dados.get("cep")).isEqualTo("18040-260");
        assertThat(dados.get("logradouro")).isEqualTo("Rua Bernardino Telles de Medeiros");
        assertThat(dados.get("complemento")).isEqualTo("");
        assertThat(dados.get("bairro")).isEqualTo("Vila São João");
        assertThat(dados.get("localidade")).isEqualTo("Sorocaba");
        assertThat(dados.get("uf")).isEqualTo("SP");

        assertThat(dados.get("unidade")).isEqualTo("");
        assertThat(dados.get("estado")).isEqualTo("São Paulo");
        assertThat(dados.get("regiao")).isEqualTo("Sudeste");
        assertThat(dados.get("ibge")).isEqualTo("3552205");
        assertThat(dados.get("gia")).isEqualTo("6695");
        assertThat(dados.get("ddd")).isEqualTo("15");
        assertThat(dados.get("siafi")).isEqualTo("7145");
    }

    @Test
    void deveRetornarErroQuandoCepInvalido() {
        // CEP que gera erro no mock
        Map<String, Object> dadosErro = cepService.buscarCep("99999999");

        assertThat(dadosErro).isNotNull();
        assertThat(dadosErro.get("status")).isEqualTo("erro");
        assertThat(dadosErro.get("mensagem")).isEqualTo("CEP não encontrado ou inválido");
        assertThat(dadosErro.get("cep")).isEqualTo("99999999");
    }
}
