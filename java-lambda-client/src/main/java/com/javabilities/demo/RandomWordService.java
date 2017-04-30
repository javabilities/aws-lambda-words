package com.javabilities.demo;

import com.amazonaws.services.lambda.invoke.LambdaFunction;

public interface RandomWordService {
    @LambdaFunction(functionName="java-random-word")
    WordOutput randomWord();
}
