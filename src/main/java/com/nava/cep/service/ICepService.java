package com.nava.cep.service;

import java.util.Map;

public interface ICepService {
    Map<String, Object> buscarCep(String cep);
}