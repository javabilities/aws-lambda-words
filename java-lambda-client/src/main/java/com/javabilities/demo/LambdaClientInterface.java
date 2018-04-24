package com.javabilities.demo;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LambdaClientInterface {
    private static final Logger logger = LogManager.getLogger(LambdaClientInterface.class);

    private final RandomWordService randomWordService = LambdaInvokerFactory.builder()
            .lambdaClient(AWSLambdaClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .withCredentials(new ProfileCredentialsProvider("default"))
                    .build())
            .build(RandomWordService.class);

    private final ReverseWordService reverseWordService = LambdaInvokerFactory.builder()
            .lambdaClient(AWSLambdaClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .withCredentials(new ProfileCredentialsProvider("default"))
                    .build())
            .build(ReverseWordService.class);

    private final UpperWordService upperWordService = LambdaInvokerFactory.builder()
            .lambdaClient(AWSLambdaClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .withCredentials(new ProfileCredentialsProvider("default"))
                    .build())
            .build(UpperWordService.class);

    private LambdaClientInterface() {
    }

    private String getRandomWord() {
        return randomWordService.randomWord().getWord();
    }

    private String doReverseWord(String word) {
        WordInput wordInput = new WordInput(word);
        return reverseWordService.reverseWord(wordInput).getWord();
    }

    private String doUpperWord(String word) {
        WordInput wordInput = new WordInput(word);
        return upperWordService.upperWord(wordInput).getWord();
    }

    public static void main(String[] args) {
        LambdaClientInterface client = new LambdaClientInterface();
        String randomWord = client.getRandomWord();
        String reverseWord = client.doReverseWord(randomWord);
        String upperWord = client.doUpperWord(reverseWord);

        logger.info("Got random word: " + randomWord);
        logger.info("Reverse of random word is: " + reverseWord);
        logger.info("Upper of reverse-random word is: " + upperWord);
    }
}
