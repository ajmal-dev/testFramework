cd ..
clean package
java -cp "./target/Test-SNAPSHOT.jar:./target/libs/*" org.testng.TestNG testng.xml
mvn clean