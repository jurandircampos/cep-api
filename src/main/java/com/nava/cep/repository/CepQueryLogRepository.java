package com.nava.cep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nava.cep.entity.CepQueryLog;

public interface CepQueryLogRepository extends JpaRepository<CepQueryLog, Long> {
}
