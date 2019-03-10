package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends
        JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);

    @Override
    <S extends ExchangeValue> S saveAndFlush(S entity);
}
