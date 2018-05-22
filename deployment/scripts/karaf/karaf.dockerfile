FROM openjdk:8-jdk
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

RUN apt-get update \
    && apt-get -y install maven \
    && apt-get -y install netcat \
    && apt-get -y install net-tools \
    && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

ENV KARAF_VERSION=4.0.9

RUN wget http://archive.apache.org/dist/karaf/${KARAF_VERSION}/apache-karaf-${KARAF_VERSION}.tar.gz; \
    mkdir /opt/karaf; \
    tar --strip-components=1 -C /opt/karaf -xzf apache-karaf-${KARAF_VERSION}.tar.gz; \
    rm apache-karaf-${KARAF_VERSION}.tar.gz; \
    mkdir /deploy; \
    sed -i 's/^\(felix\.fileinstall\.dir\s*=\s*\).*$/\1\/deploy/' /opt/karaf/etc/org.apache.felix.fileinstall-deploy.cfg

WORKDIR /opt/karaf

#RUN chmod 755 /opt; \
#    sed -i "21s/out/stdout/" /opt/karaf/etc/org.ops4j.pax.logging.cfg;

VOLUME ["/root/.m2/repository", "/opt/karaf/data/log"]

EXPOSE 1099 3306 5005 8101 8181 44444

CMD tail -f /dev/null