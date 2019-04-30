## Spring Boot MVC using io.fabric8 docker maven plugin

Use io.fabric8 docker plugin to be able to create integration test using a docker file. 
Docker image will contain our jar file generate by maven and will start boot service using java -ver (see Dockerfile)

     # Start with a base image containing Java runtime
     FROM openjdk:8-jdk-alpine

     # Add Maintainer Info
     MAINTAINER cromero_el_madero <cromeromadero@gmail.com>

     # Make port 8080 available to the world outside this container
     EXPOSE 8080

     # The application's jar file
     ARG JAR_FILE=target/rest-demo-docker-0.0.3-SNAPSHOT.jar

    # Add the application's jar to the container
    ADD ${JAR_FILE} rest-demo-docker.jar
     
    CMD java -jar rest-demo-docker.jar



## io.fabric8 docker plugin configuration

Configure docker maven plugin mapping 8080 docker host to 8080. Run image in container
waiting a 200 http code from http://localhost:8080/greeting 
        
				<groupId>io.fabric8</groupId>
				<artifactId>docker-maven-plugin</artifactId>
				<version>0.30.0</version>

				<configuration>
					<verbose>true</verbose>
					<images>
						<image>
							<name>${docker.name}</name>
							<run>
								<ports>
									<port>8080:8080</port>
								</ports>
								<wait>
									<!-- Check for this URL to return a 200 return code .... -->
									<url>http://localhost:8080/greeting</url>
									<time>120000</time>
								</wait>
							</run>
						</image>
					</images>
				
Configure execution of docker maven plugin in pre-integration-test phase building docker image and starting
container. After this integration test will be run.  post-integration-test will run stop goal.
					
                </configuration>
				<executions>
					<execution>
						<id>container-start</id>
						<phase>pre-integration-test</phase>
						<goals>
							<goal>stop</goal>
							<goal>build</goal>
							<goal>start</goal>
						</goals>
					</execution>
					<execution>
						<id>container-stop</id>
						<phase>post-integration-test</phase>
						<goals>
							<goal>stop</goal>
						</goals>
					</execution>
					
