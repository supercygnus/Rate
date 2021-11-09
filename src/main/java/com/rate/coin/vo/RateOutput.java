package com.rate.coin.vo;

import java.util.List;

public class RateOutput {

    private String updateTime;

    private List<CurrencyOutput> currencyOutputList;

    public String getUpdateTime() {
	return updateTime;
    }

    public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
    }

    public List<CurrencyOutput> getCurrencyOutputList() {
	return currencyOutputList;
    }

    public void setCurrencyOutputList(List<CurrencyOutput> currencyOutputList) {
	this.currencyOutputList = currencyOutputList;
    }

}
