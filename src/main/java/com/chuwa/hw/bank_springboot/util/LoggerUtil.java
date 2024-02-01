package com.chuwa.hw.bank_springboot.util;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoggerUtil {
    private static Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public void myMethod(){
        logger.info("Message log");
        logger.error("Error:");
    }

}
