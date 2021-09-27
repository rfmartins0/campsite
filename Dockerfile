From anapsix/alpine-java

VOLUME /tmp
ADD /build/libs/campsite-0.0.1-SNAPSHOT.jar startup.jar
RUN sh -c 'touch /startup.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/;/urandom","-jar","/startup.jar"]