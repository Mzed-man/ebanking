# Read Me First
Consider the followings

* Application runs on https with a self-signed certificate
* BankAccountTypes must be configured first via /bank-accounts-types endpoint


## Using Docker to simplify development (optional)

All needs of this application are satisfied by using Docker

You will find docker images on src/main/docker folder

To start postgresql database in a docker container, run:

```
docker-compose -f src/main/docker/postgresql.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/postgresql.yml down
```

To start keycloak server in a docker container, run:

```
docker-compose -f src/main/docker/keycloak.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/keycloak.yml down
```


For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.
