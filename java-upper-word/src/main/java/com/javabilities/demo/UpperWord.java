package com.javabilities.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class UpperWord {
    public static Response upperWordHandler(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("word to upper-case: " + request.getWord());
        String upperWord = request.getWord().toUpperCase();
        logger.log("upper-cased word: " + upperWord);
        return new Response(upperWord);
    }
}
