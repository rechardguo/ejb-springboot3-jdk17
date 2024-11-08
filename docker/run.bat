call D:\dev-tools\apache-maven-3.9.7\bin\mvn clean package -Dmaven.test.skip=true -f ../pom.xml
DEL /F .\ejb-web\deployments\*.war
COPY /Y ..\ejb-web\target\ejbweb.war .\ejb-web\deployments\ejbweb.war
::docker build -t ejbdemo .
::docker stop ejbdemo
::docker rm ejbdemo
::docker run -p 8180:8080 -p 11111:10000 -d --name ejbdemo ejbdemo
call docker-compose -p ejbdemo-docker up --build -d