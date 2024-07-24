# 这是命令
FROM openjdk:8
#ENV timezone
run /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \&& echo 'Asia/Shanghai'>/etc/timezone
#WORKDIR
WORKDIR /app
#add hotel-admin-1.0-SNAPSHOT.jar /hotel-admin-1.0-SNAPSHOT.jar
COPY hotel-admin/target/hotel-admin-1.0-SNAPSHOT.jar app/hotel-admin-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Xmx512m","-XX:-OmitStackTraceInFastThrow","app/hotel-admin-1.0-SNAPSHOT.jar","--logging.file.path=/app"]

