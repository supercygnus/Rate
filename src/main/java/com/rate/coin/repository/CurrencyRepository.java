package com.rate.coin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rate.coin.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    public List<Currency> findByCode(String code);

    @Modifying
    @Query("delete from Currency c where c.code=:code")
    public void deleteByCode(@Param("code") String code);

}
