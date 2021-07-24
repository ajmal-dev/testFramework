pwd
cd ..
mvn clean
mvn clean package
docker build . -t registry.gitlab.com/hgc-global-communications/testcasecontainerimage:latest
cd ./shellscripts/
yes | ./runtest.sh
cd ..
mvn clean