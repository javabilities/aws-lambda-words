package com.javabilities.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class ReverseWord {
    public Response reverseWordHandler(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("word to reverse: " + request.getWord());
        String reversedWord = new StringBuilder(request.getWord()).reverse().toString();
        logger.log("reversed word: " + reversedWord);
        return new Response(reversedWord);
    }
}
