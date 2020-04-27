# spring-boot-basic-docker


A basic Spring Boot Project with Spring Security and JWT running into a Docker container and a MySQL container.

The init_script.sql creates the user_account, role and user_account_role tables, inserts the roles 'ADMIN' and 'USER' and create an 'admin' user with '123456' password.


### 1.- Create the App Image


```
mvn compile jib:dockerBuild 
```

### 2.- Create the MySQL and App containers


```
docker-compose up --no-start
```

### 3.- Copy the database init script into the docker_mysql_entrypoint volume


```
sudo cp init_script.sql /var/lib/docker/volumes/docker_mysql_entrypoint/_data/
```

# 4.- Run the services


```
docker-compose up
```






