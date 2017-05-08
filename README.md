# Webhook-Bearychat

---

## build

mvn package

## spring-cloud with docker

mvn package docker:build

> package MUST be run before docker:build. Otherwise, it will fail.

## run as docker container

```
docker run --name webhook-bearychat --rm -p 8080:8080 -e JAVA_OPTS="-Dspring.redis.host=10.15.190.189 -Dspring-redis.port=6379" -t danshan/webhook-bearychat
```


