FROM rechardguo/eap8-jdk17-arthas-jrebel:latest


COPY ./deployments /opt/eap8/standalone/deployments  
COPY ./modules/ /opt/eap8/modules/
COPY ./configuration /opt/eap8/standalone/configuration/


CMD /opt/eap8/bin/standalone.sh -b 0.0.0.0 