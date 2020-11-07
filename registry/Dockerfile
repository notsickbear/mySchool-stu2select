### 基础镜像
FROM goodrainapps/maven:jdk8-alpine
#作者，这里是改的这个guox的，就不改名字了
MAINTAINER guox <guox@goodrain.com>

#系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

# package
COPY . /app
WORKDIR /app
RUN mvn -s maven-settings.xml -B -DskipTests=true -Dmaven.repo.local=/tmp/cache clean install
#暴露8080端口
CMD ["java", "-Xmx200m","-Dserver.port=${PORT}","-jar", "/app/target/registry.jar"]
#暴露端口
EXPOSE 8761
