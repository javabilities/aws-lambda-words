package com.javabilities.demo;

import com.amazonaws.services.lambda.invoke.LambdaFunction;

public interface UpperWordService {
    @LambdaFunction(functionName="java-upper-word")
    WordOutput upperWord(WordInput wordInput);
}
