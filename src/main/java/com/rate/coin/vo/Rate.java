package com.rate.coin.vo;

public class Rate {

    private String disclaimer;
    private String chartName;
    private RateTime time;
    private RateBpi bpi;

    public String getDisclaimer() {
	return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
	this.disclaimer = disclaimer;
    }

    public String getChartName() {
	return chartName;
    }

    public void setChartName(String chartName) {
	this.chartName = chartName;
    }

    public RateTime getTime() {
	return time;
    }

    public void setTime(RateTime time) {
	this.time = time;
    }

    public RateBpi getBpi() {
	return bpi;
    }

    public void setBpi(RateBpi bpi) {
	this.bpi = bpi;
    }

}
