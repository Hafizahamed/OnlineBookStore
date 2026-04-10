FROM tomcat:10.1-jdk17

RUN rm -rf /usr/local/tomcat/webapps/*

COPY OnlineBookStore.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]
