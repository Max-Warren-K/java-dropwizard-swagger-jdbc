FROM maven:latest
#set maven dependency to specific version?

WORKDIR /code

COPY . /code

ARG DB_HOST
ARG DB_PASSWORD
ARG DB_USERNAME
ARG DB_NAME

ENV DB_HOST=academy2020.cpc8rvmbbd9k.eu-west-2.rds.amazonaws.com
ENV DB_PASSWORD=1Password2!
ENV DB_USERNAME=ACADEMY_SYS_USER
ENV DB_NAME=employee_craig

RUN mvn clean install -DskipTests=true

EXPOSE 8080

CMD ["java","-jar", "/code/target/JavaWebService-1.0-SNAPSHOT.jar", "server", "/code/config.yml"]
