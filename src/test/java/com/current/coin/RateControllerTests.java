package com.current.coin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rate.coin.controller.RateController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { com.rate.coin.RateApplication.class })
public class RateControllerTests {

    @Autowired
    private RateController rateController;

    @Test
    void testCallCoindesk() throws Exception {

	String actual = rateController.callCoindesk();
	String expected = "Success";
	Assertions.assertTrue(actual.contains(expected));

    }

    @Test
    void testCallCoindeskAndConvert() throws Exception {

	String actual = rateController.callCoindeskAndConvert();
	String expected = "Success";
	Assertions.assertTrue(actual.contains(expected));

    }

    @Test
    void testQueryCurrency() throws Exception {

	String actual = rateController.queryCurrency("USD");
	String expected = "Success";
	Assertions.assertTrue(actual.contains(expected));

    }

    @Test
    void testInsertCurrency() throws Exception {

	String actual = rateController.insertCurrency("TEST", "TEST");
	String expected = "Success";
	Assertions.assertTrue(actual.contains(expected));

    }

    @Test
    void testUpdatetCurrency() throws Exception {

	String actual = rateController.updatetCurrency("TEST", "TEST");
	String expected = "Success";
	Assertions.assertTrue(actual.contains(expected));

    }

    @Test
    void testDeleteCurrency() throws Exception {

	String actual = rateController.deleteCurrency("TEST");
	String expected = "Success";
	Assertions.assertTrue(actual.contains(expected));

    }
}
