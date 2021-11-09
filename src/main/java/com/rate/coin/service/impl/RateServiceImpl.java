package com.rate.coin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rate.coin.entity.Currency;
import com.rate.coin.repository.CurrencyRepository;
import com.rate.coin.service.RateService;
import com.rate.coin.vo.Coin;
import com.rate.coin.vo.CurrencyOutput;
import com.rate.coin.vo.Rate;
import com.rate.coin.vo.RateOutput;

@Service
public class RateServiceImpl implements RateService {

    private static final Logger logger = LogManager.getLogger(RateServiceImpl.class);

    @Autowired
    private CurrencyRepository currencyRepository;

    public String callCoindesk() throws Exception {
	// 建立CloseableHttpClient
	HttpClientBuilder builder = HttpClientBuilder.create();
	CloseableHttpClient client = builder.build();
	// 執行
	HttpUriRequest httpGet = new HttpGet("https://api.coindesk.com/v1/bpi/currentprice.json");
	CloseableHttpResponse response = client.execute(httpGet);

	Rate r = null;
	ObjectMapper mapper = new ObjectMapper();
	String output = "";
	if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	    // 讀取伺服器返回過來的json字串資料
	    HttpEntity entity = response.getEntity();
	    if (entity != null) {
		String entityStr = EntityUtils.toString(entity, "UTF-8");
		logger.info(entityStr);

		r = mapper.readValue(entityStr, Rate.class);

		logger.info(r.getChartName());
		output = entityStr;
	    }

	} else {
	    throw new Exception("呼叫Coindesk API失敗");
	}

	return output;
    }

    public String callCoindeskAndConvert() throws Exception {
	String coindeskJson = this.callCoindesk();
	ObjectMapper mapper = new ObjectMapper();
	Rate r = mapper.readValue(coindeskJson, Rate.class);

	String updateTime = r.getTime().getUpdated();

	Coin eurCoin = r.getBpi().getEUR();
	Coin usdCoin = r.getBpi().getUSD();
	Coin gbpCoin = r.getBpi().getGBP();

	Map<String, String> currencyMap = new HashMap<>();

	currencyMap.put(eurCoin.getCode(), eurCoin.getRateFloat());
	currencyMap.put(usdCoin.getCode(), usdCoin.getRateFloat());
	currencyMap.put(gbpCoin.getCode(), gbpCoin.getRateFloat());

	List<CurrencyOutput> currencyOutputList = new ArrayList<>();

	for (Map.Entry<String, String> entry : currencyMap.entrySet()) {

	    CurrencyOutput currencyOutput = new CurrencyOutput();

	    String code = entry.getKey();
	    String rate = entry.getValue();

	    Currency currency = this.queryCurrency(code);

	    currencyOutput.setCode(code);
	    currencyOutput.setRate(rate);
	    currencyOutput.setName(currency.getName());

	    currencyOutputList.add(currencyOutput);

	}

	RateOutput rateOutput = new RateOutput();

	rateOutput.setCurrencyOutputList(currencyOutputList);
	rateOutput.setUpdateTime(updateTime);

	String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rateOutput);

	logger.info(jsonStr);

	return jsonStr;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertCurrency(String code, String name) {

	Currency currency = new Currency();
	currency.setCode(code);
	currency.setName(name);

	this.currencyRepository.save(currency);

    }

    public Currency queryCurrency(String code) {

	List<Currency> currencyList = this.currencyRepository.findByCode(code);

	return currencyList.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCurrency(String code, String name) {

	List<Currency> currencyList = this.currencyRepository.findByCode(code);
	Currency currency = currencyList.get(0);
	int id = currency.getId();
	Optional<Currency> optionalCurrency = this.currencyRepository.findById(id);
	currency = optionalCurrency.get();
	currency.setName(name);
	this.currencyRepository.save(currency);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCurrency(String code) {

	this.currencyRepository.deleteByCode(code);
    }

}
