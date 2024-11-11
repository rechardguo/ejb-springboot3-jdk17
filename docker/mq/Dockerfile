FROM icr.io/ibm-messaging/mq:latest

LABEL maintainer="rechard"

USER root
COPY ./localtime /etc/localtime
RUN  echo 'Asia/Shanghai' >/etc/timezone

# 切换回非特权用户
USER 1001

# 创建队列管理器和队列的脚本
# 这个脚本将在容器启动时运行
COPY create-mq-resources.sh /create-mq-resources.sh


# 定义容器启动时要运行的命令
CMD ["/create-mq-resources.sh"]