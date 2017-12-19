package com.javabilities.demo;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

public class LambdaClientSimple {
    private static final Logger logger = LogManager.getLogger(LambdaClientSimple.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final AWSLambda lambda = AWSLambdaClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(new ProfileCredentialsProvider("default"))
            .build();

    private LambdaClientSimple() {
    }

    private String getRandomWord() {
        InvokeRequest invokeRequest = new InvokeRequest();
        invokeRequest.setFunctionName("java-random-word");
        invokeRequest.setPayload("");
        InvokeResult invokeResult = lambda.invoke(invokeRequest);
        int statusCode = invokeResult.getStatusCode();
        if (statusCode != 200) {
            logger.error("Log Result: " + invokeResult.getLogResult());
            logger.error("Function Error: " + invokeResult.getFunctionError());
            return invokeResult.getFunctionError();
        }
        String payloadString = Charset.forName("UTF-8").decode(invokeResult.getPayload()).toString();
        String randomWord = null;
        try {
            randomWord = mapper.readTree(payloadString).get("word").asText();
        } catch (IOException e) {
            e.printStackTrace();
            return("JSON Parsing Error!");
        }

        return randomWord;
    }

    private String getReverseWord(String inputWord) {
        InvokeRequest invokeRequest = new InvokeRequest();
        invokeRequest.setFunctionName("java-reverse-word");
        invokeRequest.setPayload("{\"word\":\"" + inputWord + "\"}");
        InvokeResult invokeResult = lambda.invoke(invokeRequest);
        int statusCode = invokeResult.getStatusCode();
        if (statusCode != 200) {
            logger.error("Log Result: " + invokeResult.getLogResult());
            logger.error("Function Error: " + invokeResult.getFunctionError());
            return invokeResult.getFunctionError();
        }
        String payloadString = Charset.forName("UTF-8").decode(invokeResult.getPayload()).toString();
        String reverseWord = null;
        try {
            reverseWord = mapper.readTree(payloadString).get("word").asText();
        } catch (IOException e) {
            e.printStackTrace();
            return("JSON Parsing Error!");
        }

        return reverseWord;
    }

    private String getUpperWord(String inputWord) {
        InvokeRequest invokeRequest = new InvokeRequest();
        invokeRequest.setFunctionName("java-upper-word");
        invokeRequest.setPayload("{\"word\":\"" + inputWord + "\"}");
        InvokeResult invokeResult = lambda.invoke(invokeRequest);
        int statusCode = invokeResult.getStatusCode();
        if (statusCode != 200) {
            logger.error("Log Result: " + invokeResult.getLogResult());
            logger.error("Function Error: " + invokeResult.getFunctionError());
            return invokeResult.getFunctionError();
        }
        String payloadString = Charset.forName("UTF-8").decode(invokeResult.getPayload()).toString();
        String upperWord = null;
        try {
            upperWord = mapper.readTree(payloadString).get("word").asText();
        } catch (IOException e) {
            e.printStackTrace();
            return("JSON Parsing Error!");
        }

        return upperWord;
    }

    public static void main(String[] args) {
        LambdaClientSimple client = new LambdaClientSimple();
        String randomWord = client.getRandomWord();
        String reverseWord = client.getReverseWord(randomWord);
        String upperWord = client.getUpperWord(reverseWord);

        logger.info("Got random word: " + randomWord);
        logger.info("Reverse of random word is: " + reverseWord);
        logger.info("Upper of reverse-random word is: " + upperWord);
    }
}
