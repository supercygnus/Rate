package com.rate.coin.service;

import com.rate.coin.entity.Currency;

public interface RateService {

    public void insertCurrency(String code, String name);

    public Currency queryCurrency(String code);

    public void updateCurrency(String code, String name);

    public void deleteCurrency(String code);
    
    public String callCoindesk() throws Exception;
    
    public String callCoindeskAndConvert() throws Exception;
}
