# Cloning Stage
FROM alpine:3.18 AS clone

RUN apk update && apk add --no-cache git

WORKDIR /gittmp/
RUN git clone https://github.com/aldairjpalma/DadBot.git 
WORKDIR /gittmp/DadBot/
RUN git clone https://github.com/ARGamer36/ARJI.git 
RUN mv /gittmp/DadBot/ARJIpom.xml /gittmp/DadBot/ARJI/pom.xml

#Building  Stage
FROM maven:3.8.3-openjdk-17 AS build
COPY --from=clone /gittmp/DadBot/ARJI/ /tmp/DadBot/ARJI/
COPY --from=clone /gittmp/DadBot/Driver/ /tmp/DadBot/Driver/
COPY --from=clone /gittmp/DadBot/pom.xml /tmp/DadBot/pom.xml
#COPY --from=clone /gittmp/.gitmodules /tmp/.gitmodules

WORKDIR /tmp/DadBot/
RUN mvn clean package

# Run Stage
FROM openjdk:17-alpine
COPY --from=build /tmp/DadBot/Driver/target/*.jar /user/DadBot/Interweb-jar-with-dependencies.jar
RUN mv /user/DadBot/Interweb-jar-with-dependencies.jar /user/DadBot/app.jar
WORKDIR /user/DadBot/
RUN echo "Built on: $(TZ=$TZ date +'%m/%d/%Y %H:%M:%S %Z')" > build_info.txt
CMD ["java", "-jar", "app.jar"]
