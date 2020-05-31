# Jenkins

## Linux
```yaml
version: '3'
services:
  jenkins:
    image: jenkinsci/blueocean
    restart: always
    container_name: jenkins
    ports:
      - '8080:8080'
    volumes:
      - './jenkins-data:/var/jenkins_home'
      - '/var/run/docker.sock:/var/run/docker.sock'

```

## Windows
```yaml
version: '3'
services:
  jenkins:
    image: jenkinsci/blueocean
    restart: always
    container_name: jenkins
    ports:
      - '8080:8080'
#    volumes:
#      - './jenkins-data:/var/jenkins_home'

```