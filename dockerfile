FROM openjdk:8-jre-slim
ARG test_name
ENV testname=$test_name
RUN mkdir results
RUN mkdir results/API_TEST_RESULTS
RUN mkdir results/UI_TEST_RESULTS
RUN mkdir results/bdd
WORKDIR /usr/share/tag
# Add the jar with all the dependencies
ADD  target/testFramework-1.0-SNAPSHOT.jar testFramework-1.0-SNAPSHOT.jar
ADD  target/libs libs
# Add the suite xmls
ADD testng.xml testng.xml
# Command line to execute the test
ENTRYPOINT java -cp testFramework-1.0-SNAPSHOT.jar:libs/* org.testng.TestNG -testnames "$testname" testng.xml