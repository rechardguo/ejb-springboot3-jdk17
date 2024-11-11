#!/bin/bash
echo "Current working directory is: $(pwd)"
mvn clean package -Dmaven.test.skip=true --file "../pom.xml"
rm -f ./ejb-web/deployments/*.war
cp ../ejb-web/target/ejbweb.war ./ejb-web/deployments/ejbweb.war
docker-compose -p ejbdemo-docker up --build -d