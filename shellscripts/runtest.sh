docker network create --subnet=172.18.0.0/16 container-network
docker run -d -p 4444:4444 --net=container-network --name=chromedebug selenium/standalone-chrome-debug:latest
docker run -d -p 5555:5555 --net=container-network --name=firefoxdebug selenium/standalone-firefox-debug:latest
docker run --net=container-network --name=testcontainer registry.gitlab.com/hgc-global-communications/testcasecontainerimage:latest
docker kill chromedebug
docker kill firefoxdebug
docker kill testcontainer
docker container prune
docker rmi registry.gitlab.com/hgc-global-communications/testcasecontainerimage:latest
