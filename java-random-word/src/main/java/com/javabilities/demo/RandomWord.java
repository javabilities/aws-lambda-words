package com.javabilities.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class RandomWord {
    static final Logger logger = LogManager.getLogger(RandomWord.class);

    public Response handler() {
        // Load the word list
        String fileName = "words.txt";
        logger.info("loading " + fileName);
        InputStream is = getClass().getResourceAsStream(fileName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        List<String> wordList = br.lines().collect(Collectors.toList());
        try {
            br.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        logger.info("found " + wordList.size() + " words");
        // Return a random word
        String randomWord = wordList.get(new Random().nextInt(wordList.size()));
        logger.info("random word: " + randomWord);
        return new Response(randomWord);
    }
}
