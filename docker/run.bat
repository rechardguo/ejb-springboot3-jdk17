zzz
DEL /F .\ejb-web\deployments\*.war
COPY /Y ..\ejb-web\target\ejbweb.war .\ejb-web\deployments\ejbweb.war
::docker build -t ejbdemo .
::docker stop ejbdemo
::docker rm ejbdemo
::docker run -p 8180:8080 -p 11111:10000 -d --name ejbdemo ejbdemo
call docker-compose -p ejbdemo-docker up --build -d