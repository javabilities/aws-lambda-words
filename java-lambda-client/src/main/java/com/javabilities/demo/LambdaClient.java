package com.javabilities.demo;

import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;

public class LambdaClient {
    final RandomWordService randomWordService = LambdaInvokerFactory.builder()
            .lambdaClient(AWSLambdaClientBuilder.defaultClient())
            .build(RandomWordService.class);

    final ReverseWordService reverseWordService = LambdaInvokerFactory.builder()
            .lambdaClient(AWSLambdaClientBuilder.defaultClient())
            .build(ReverseWordService.class);

    final UpperWordService upperWordService = LambdaInvokerFactory.builder()
            .lambdaClient(AWSLambdaClientBuilder.defaultClient())
            .build(UpperWordService.class);

    public String getRandomWord() {
        WordInput wordInput = new WordInput("");
        String randomWord = randomWordService.randomWord().getWord();
        return randomWord;
    }

    public String doReverseWord(String word) {
        WordInput wordInput = new WordInput(word);
        String reverseWord = reverseWordService.reverseWord(wordInput).getWord();
        return reverseWord;
    }

    public String doUpperWord(String word) {
        WordInput wordInput = new WordInput(word);
        String upperWord = upperWordService.upperWord(wordInput).getWord();
        return upperWord;
    }

    public static void main(String[] args) {
        LambdaClient client = new LambdaClient();
        String randomWord = client.getRandomWord();
        System.out.println("Got random word: " + randomWord);
        String reverseWord = client.doReverseWord(randomWord);
        System.out.println("Reverse of random word is: " + reverseWord);
        String upperWord = client.doUpperWord(reverseWord);
        System.out.println("Upper of reverse-random word is: " + upperWord);
    }

    public LambdaClient() {
    }
}
