package com.javabilities.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpperWord {
    static final Logger logger = LogManager.getLogger(UpperWord.class);

    public static Response handler(Request request) {
        logger.info("word to upper-case: " + request.getWord());
        String upperWord = request.getWord().toUpperCase();
        logger.info("upper-cased word: " + upperWord);
        return new Response(upperWord);
    }
}
