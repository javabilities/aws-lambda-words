package com.javabilities.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReverseWord {
    static final Logger logger = LogManager.getLogger(ReverseWord.class);

    public Response handler(Request request) {
        logger.info("word to reverse: " + request.getWord());
        String reversedWord = new StringBuilder(request.getWord()).reverse().toString();
        logger.info("reversed word: " + reversedWord);
        return new Response(reversedWord);
    }
}
