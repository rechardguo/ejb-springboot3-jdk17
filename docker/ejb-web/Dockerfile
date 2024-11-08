FROM eap80-jdk17
#FROM eap74-jdk17
ENV DEBUG=true
ENV DEBUG_PORT=10000
ENV USE_JREBEL=true

# copy arthas
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
COPY ./arthas.properties /opt/arthas

USER root

WORKDIR /opt/wildfly/

COPY ./deployments /opt/wildfly/standalone/deployments  
COPY ./modules/ modules/
COPY ./configuration /opt/wildfly/standalone/configuration/
COPY ./bin /opt/wildfly/bin
COPY jrebel.jar /jrebel.jar
COPY libjrebel64.so /libjrebel64.so
#COPY jboss-eap-7.4.16-patch.zip /opt

#RUN /opt/wildfly/bin/jboss-cli.sh "patch apply /opt/jboss-eap-7.4.16-patch.zip --override-all"
#RUN /opt/wildfly/bin/jboss-cli.sh --file=/opt/wildfly/docs/examples/enable-elytron-se17.cli

CMD /opt/wildfly/bin/standalone.sh -b 0.0.0.0 