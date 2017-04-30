package com.javabilities.demo;

import com.amazonaws.services.lambda.invoke.LambdaFunction;

public interface ReverseWordService {
    @LambdaFunction(functionName="java-reverse-word")
    WordOutput reverseWord(WordInput input);
}
