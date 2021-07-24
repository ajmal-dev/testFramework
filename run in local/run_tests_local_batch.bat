call cd ..
call mvn clean package
call java -cp ".\target\classes;.\target\libs\*" org.testng.TestNG -testnames "PVT" testng.xml
call mvn clean