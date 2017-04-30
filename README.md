# aws-lambda-demo
A sample project with 3 Lambda Functions written in Java and a Java Lambda Client

## Build Lambda Functions
gradlew build

The Lambda distributions are in the following directories:
* java-random-word/build/distributions/java-random-word.zip
* java-reverse-word/build/distributions/java-reverse-word.zip
* java-upper-word/build/distributions/java-upper-word.zip

## Build Lambda Client (standalone Java app via shadowJar)
gradlew shadowJar

Run the Client with the command:

java -jar java-lambda-client/build/libs/java-lambda-client-0.1.0-all.jar
