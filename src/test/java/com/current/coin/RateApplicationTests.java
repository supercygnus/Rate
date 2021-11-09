package com.current.coin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RateApplicationTests {

    private static final Logger logger = LogManager.getLogger(RateApplicationTests.class);
	@Test
	void contextLoads() {
	    logger.info("Hellio");
	}

}
