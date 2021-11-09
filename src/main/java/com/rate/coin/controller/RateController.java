package com.rate.coin.controller;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rate.coin.entity.Currency;
import com.rate.coin.service.RateService;
import com.rate.coin.vo.Coin;
import com.rate.coin.vo.Rate;
import com.rate.coin.vo.RateBpi;
import com.rate.coin.vo.RateTime;

@RestController
@RequestMapping("/rate")
public class RateController {

    private static final Logger logger = LogManager.getLogger(RateController.class);

    @Autowired
    private RateService rateService;

    @RequestMapping("/coindeskConvert")
    public String callCoindeskAndConvert() {
	String output = "";

	try {

	    output = this.rateService.callCoindeskAndConvert();

	} catch (Exception e) {
	    output = "Fail" + e.getMessage();
	}
	return "Success" + output;
    }

    @RequestMapping("/coindesk")
    public String callCoindesk() {
	String output = "";

	try {

	    output = this.rateService.callCoindesk();

	} catch (Exception e) {
	    output = "Fail" + e.getMessage();
	}
	return "Success" + output;
    }

    @RequestMapping("/query")
    public String queryCurrency(String code) {
	String currencyName = null;
	try {
	    currencyName = this.rateService.queryCurrency(code).getName();
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    return "Failed";
	}

	return "Success:" + currencyName;
    }

    @RequestMapping("/insert")
    public String insertCurrency(String code, String name) {

	try {
	    this.rateService.insertCurrency(code, name);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    return "Failed";
	}

	return "Success";
    }

    @RequestMapping("/update")
    public String updatetCurrency(String code, String name) {

	try {
	    this.rateService.updateCurrency(code, name);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    return "Failed";
	}

	return "Success";
    }

    @RequestMapping("/delete")
    public String deleteCurrency(String code) {

	try {
	    this.rateService.deleteCurrency(code);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    return "Failed";
	}

	return "Success";
    }

//    @RequestMapping("/")
//    public String testRun() throws ParseException, IOException {
//
//	String s = "{\"time\":{\"updated\":\"Nov 4, 2021 09:20:00 UTC\",\"updatedISO\":\"2021-11-04T09:20:00+00:00\",\"updateduk\":\"Nov 4, 2021 at 09:20 GMT\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"61,720.1167\",\"description\":\"United States Dollar\",\"rate_float\":61720.1167},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"45,234.2415\",\"description\":\"British Pound Sterling\",\"rate_float\":45234.2415},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"53,422.9579\",\"description\":\"Euro\",\"rate_float\":53422.9579}}}";
//
//	ObjectMapper mapper = new ObjectMapper();
//
//	Rate rate = new Rate();
//	rate.setChartName("MAcx");
//	rate.setDisclaimer("dis");
//
//	RateTime rateTime = new RateTime();
//	rateTime.setUpdated("123");
//	rateTime.setUpdatedISO("456");
//	rate.setTime(rateTime);
//
//	RateBpi rateBpi = new RateBpi();
//
//	Coin eur = new Coin();
//	eur.setCode("123");
//	eur.setDescription("desc");
//	eur.setSymbol("$");
//	eur.setRate("2.0148");
//
//	rateBpi.setEUR(eur);
//	rate.setBpi(rateBpi);
//
//	String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rate);
//
//	logger.info(jsonStr);
//
//	Rate r = mapper.readValue(jsonStr, Rate.class);
//
//	logger.info(r.getChartName());
//
//	// 建立CloseableHttpClient
//	HttpClientBuilder builder = HttpClientBuilder.create();
//	CloseableHttpClient client = builder.build();
//	// 執行
//	HttpUriRequest httpGet = new HttpGet("https://api.coindesk.com/v1/bpi/currentprice.json");
//	CloseableHttpResponse response = client.execute(httpGet);
//
//	if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//	    // 讀取伺服器返回過來的json字串資料
//	    HttpEntity entity = response.getEntity();
//	    if (entity != null) {
//		String entityStr = EntityUtils.toString(entity, "UTF-8");
//		logger.info(entityStr);
//
//		r = mapper.readValue(entityStr, Rate.class);
//
//		logger.info(r.getChartName());
//
//	    }
//
//	}
//
//	// rateService.getRate();
//
//	// Currency c = rateService.queryCurrency("USD");
//	// logger.info(c.getName());
//
//	this.rateService.deleteCurrency("USD");
//
//	// this.rateService.updateCurrency("USD", "超級美金");
//
//	return "OK";
//    }
}
