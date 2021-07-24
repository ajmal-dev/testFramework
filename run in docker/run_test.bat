call cd ..
call mvn clean package
call docker build . -t registry.gitlab.com/hgc-global-communications/testcasecontainerimage:latest
call cd ./batch files/
call echo y | runtestwin.bat
