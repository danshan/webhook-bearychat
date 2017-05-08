FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD webhook-bearychat.jar app.jar
RUN sh -c 'touch /app.jar'
ADD startup.sh startup.sh
RUN chmod +x startup.sh
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "/startup.sh" ]
