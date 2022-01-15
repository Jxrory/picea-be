FROM openjdk:8-jre-alpine

ENV TZ=Asia/Shanghai \
    SLEEP=0 \
    JAVA_OPTS="-Dspring.profiles.active=dk" \
    JAR_NAME="picea-0.0.1-SNAPSHOT.jar" \
    JAR_PATH="/application"

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD echo "The application will start in ${SLEEP}s..." && \
    sleep ${SLEEP} && \
    java ${JAVA_OPTS} -jar ${JAR_PATH}/${JAR_NAME}

EXPOSE 7423

ADD ./target/${JAR_NAME} ${JAR_PATH}/${JAR_NAME}

VOLUME [ "${JAR_PATH}/logs", "${JAR_PATH}/conf", "${JAR_PATH}/data"]
