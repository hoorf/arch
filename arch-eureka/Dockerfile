FROM java:8u111-jdk
ENV ARTIFACTID arch-eureka
ENV ARTIFACTVERSION 1.0-SNAPSHOT
ENV HOME_PATH /home

ADD /target/$ARTIFACTID-$ARTIFACTVERSION.jar $HOME_PATH/eureka.jar

WORKDIR $HOME_PATH

ENTRYPOINT ["java","-jar","eureka.jar"]
