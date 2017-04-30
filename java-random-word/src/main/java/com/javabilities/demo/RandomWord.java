package com.javabilities.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomWord {
    public Response randomWordHandler(Context context) {
        LambdaLogger logger = context.getLogger();
        // Load the word list
        String fileName = "words.txt";
        List<String> wordList = new ArrayList<>();
        logger.log("loading " + fileName);
        InputStream is = getClass().getResourceAsStream(fileName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        wordList = br.lines().collect(Collectors.toList());
        try {
            br.close();
            isr.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        logger.log("found " + wordList.size() + " words");
        // Return a random word
        String randomWord = wordList.get(new Random().nextInt(wordList.size()));
        logger.log("random word: " + randomWord);
        return new Response(randomWord);
    }
}
