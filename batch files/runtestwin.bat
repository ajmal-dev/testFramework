call docker network create --subnet=172.18.0.0/16 container-network
call docker run -d -p 4444:4444 --net=container-network --name=chromedebug selenium/standalone-chrome-debug:latest
call docker run -d -p 5555:5555 --net=container-network --name=firefoxdebug selenium/standalone-firefox-debug:latest
call docker run  --net=container-network --name=testcontainer registry.gitlab.com/hgc-global-communications/testcasecontainerimage:latest
call docker kill firefoxdebug
call docker kill chromedebug
call docker kill testcontainer
call echo y|docker container prune
call docker rmi registry.gitlab.com/hgc-global-communications/testcasecontainerimage:latest