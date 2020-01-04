FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

RUN echo "Asia/Shanghai" > /etc/timezone
RUN mkdir --parents /log/mall/

ENTRYPOINT ["java", "-Xms128m", "-Xmx256m", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/log/mall/", "-XX:+PrintGCDetails", "-XX:+PrintGCDateStamps", "-XX:+PrintHeapAtGC", "-Xloggc:/log/mall/gc.log", "-cp", "app:app/lib/*", "com.mall.gateway.GatewayApplication"]
# simple config
# ENTRYPOINT ["java","-cp","app:app/lib/*","com.mall.user.UserApplication"]