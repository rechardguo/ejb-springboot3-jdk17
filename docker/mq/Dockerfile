FROM icr.io/ibm-messaging/mq:latest
#FROM ibmcom/mq
LABEL maintainer="rechard"

USER root
COPY ./localtime /etc/localtime
RUN  echo 'Asia/Shanghai' >/etc/timezone

COPY CBMQ.mqsc /etc/mqm/
COPY cbmq.ini /etc/mqm/

USER 1001