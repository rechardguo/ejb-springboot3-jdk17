FROM mariadb:10.3

LABEL maintainer="rechard"
USER root
COPY ./localtime /etc/localtime
RUN  echo 'Asia/Shanghai' >/etc/timezone

#slow log path
RUN mkdir -p /var/mysql/
RUN chmod 777 /var/mysql

COPY ./init.sql /docker-entrypoint-initdb.d/init.sql

COPY ./my.cnf /etc/mysql/my.cnf
RUN chmod 644 /etc/mysql/my.cnf