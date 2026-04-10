FROM tomcat:10.1-jdk17

COPY OnlineBookStore.war /usr/local/tomcat/webapps/

EXPOSE 8080
