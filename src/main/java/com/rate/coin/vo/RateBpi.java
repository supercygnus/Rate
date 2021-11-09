package com.rate.coin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateBpi {
    
    @JsonProperty("USD")
    private Coin USD;
    @JsonProperty("GBP")
    private Coin GBP;
    @JsonProperty("EUR")
    private Coin EUR;

    public Coin getUSD() {
	return USD;
    }

    public void setUSD(Coin uSD) {
	USD = uSD;
    }

    public Coin getGBP() {
	return GBP;
    }

    public void setGBP(Coin gBP) {
	GBP = gBP;
    }

    public Coin getEUR() {
	return EUR;
    }

    public void setEUR(Coin eUR) {
	EUR = eUR;
    }

}
