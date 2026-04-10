FROM tomcat:10.1-jdk17

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy latest WAR file
COPY OnlineBookStore.war /usr/local/tomcat/webapps/OnlineBookStore.war

# Expose port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
