# Read Me First
Consider the followings
* This application runs on Java 17
* You can run application via command-line by using **mvn spring-boot:run**
* Application runs on https with a self-signed certificate on port 8090
* You must first start docker containers for PostgreSQL and Keycloak servers (see instructions below)
* It has been decided to have configurables bank account types. All related operations are on /bank-accounts-types endpoint
* Database DDL can be viewed on file **ddl-to-use.sql**



## Using Docker to simplify development (optional)

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


## Getting token from Keycloak Server
* Keycloak server will be available at http://localhost:9080/. Use admin/admin as credentials
* You will need to access Admin console and create the realm **Baobab** and the client **banking**.
* The root url for **banking** keycloak client must be set to https://localhost:8090
```
curl -i -X POST \
-H "Content-Type:application/x-www-form-urlencoded" \
-d "client_id=banking" \
-d "username=user" \
-d "password=user" \
-d "grant_type=password" \
'http://localhost:9080/realms/baobab/protocol/openid-connect/token'
```


## Sample Requests
Find below sample requests to use api endpoints. Be sure to replace token by token got from Keycloak server first

* keyCloakLogin (to get a token from Keycloak Server)
```
  curl -i -X POST \
  -H "Content-Type:application/x-www-form-urlencoded" \
  -d "client_id=banking" \
  -d "username=user" \
  -d "password=user" \
  -d "grant_type=password" \
  'http://localhost:9080/realms/baobab/protocol/openid-connect/token'
```

* register a new bank customer
```
curl -i -X POST \
   -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJJel91eFBGLVVrQ21qemg3SEo2M3dSaWdsTHVlWDk4MFRNSzVLdkdOQ0VnIn0.eyJleHAiOjE2ODA1NTMzNjEsImlhdCI6MTY4MDU1MzA2MSwianRpIjoiNzQzNGViN2QtN2ZlNy00MGU1LTgzYTgtMGJmNmI0YjkzNjUyIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDgwL3JlYWxtcy9iYW9iYWIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNmIwZTc0MTUtOWFiYi00NzhiLTkwYjUtM2ExNTZmYzcxNDRiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYmFua2luZyIsInNlc3Npb25fc3RhdGUiOiI0NThjOTIyYy02MWI4LTQ3N2MtOGVjNy0zNzBlZmY5NjY2NmYiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlJPTEVfVVNFUiIsIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1iYW9iYWIiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjQ1OGM5MjJjLTYxYjgtNDc3Yy04ZWM3LTM3MGVmZjk2NjY2ZiIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlciIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiJ9.YL2ELe3dVCf5j4PYAhVbBZIRpl6bDrt2-84yNIL3e7MBAMlACui5rCjS2DhalYms_w9tU3WSuvQs22--29E55WrJ_CV-j_6UEHai04aD2FigoZm9aS0K22hjJgMVspOAbdvVThViFZZd6iDnhvOvf64jcdcXy45Wu0QGAAI0Hkqqq-X_LRVmoM3Gg9NvOolzMyjoR-twGo5pGicw1OYoNaT9_Qthr_3zawHkvCq9LSs-pI-tQ3C36tqshcw_TsnAKP3ZM5PEPmBFJ5dS4HHuj16Ddtj7DtEPynv2n7ngkiupJ0VOMB28UW-tFPbnbzUzN2P7o7JpvPIP9P2YMRXzJg" \
   -H "Content-Type:application/json" \
   -d \
'{
  "name" : "MBODJ",
  "surname" : "Latyr",
  "msisdn" : "766752975",
  "email" : "latyr@esp.sn"
}' \
 'http://localhost:8090/api/customers'
```

* update bank customer info
```
curl -i -X PUT \
   -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJJel91eFBGLVVrQ21qemg3SEo2M3dSaWdsTHVlWDk4MFRNSzVLdkdOQ0VnIn0.eyJleHAiOjE2ODA1NTMzNjEsImlhdCI6MTY4MDU1MzA2MSwianRpIjoiNzQzNGViN2QtN2ZlNy00MGU1LTgzYTgtMGJmNmI0YjkzNjUyIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDgwL3JlYWxtcy9iYW9iYWIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNmIwZTc0MTUtOWFiYi00NzhiLTkwYjUtM2ExNTZmYzcxNDRiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYmFua2luZyIsInNlc3Npb25fc3RhdGUiOiI0NThjOTIyYy02MWI4LTQ3N2MtOGVjNy0zNzBlZmY5NjY2NmYiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlJPTEVfVVNFUiIsIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1iYW9iYWIiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjQ1OGM5MjJjLTYxYjgtNDc3Yy04ZWM3LTM3MGVmZjk2NjY2ZiIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlciIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiJ9.YL2ELe3dVCf5j4PYAhVbBZIRpl6bDrt2-84yNIL3e7MBAMlACui5rCjS2DhalYms_w9tU3WSuvQs22--29E55WrJ_CV-j_6UEHai04aD2FigoZm9aS0K22hjJgMVspOAbdvVThViFZZd6iDnhvOvf64jcdcXy45Wu0QGAAI0Hkqqq-X_LRVmoM3Gg9NvOolzMyjoR-twGo5pGicw1OYoNaT9_Qthr_3zawHkvCq9LSs-pI-tQ3C36tqshcw_TsnAKP3ZM5PEPmBFJ5dS4HHuj16Ddtj7DtEPynv2n7ngkiupJ0VOMB28UW-tFPbnbzUzN2P7o7JpvPIP9P2YMRXzJg" \
   -H "Content-Type:application/json" \
   -d \
'{
  "id":"1402",
  "name" : "MBODJ",
  "surname" : "Papa Latyr",
  "msisdn" : "766752975",
  "email" : "pmbodj@yahoo.fr"
}' \
 'http://localhost:8090/api/customers/1402'
```

* create new bank account type for bank accounts
```
curl -i -X POST \
   -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJJel91eFBGLVVrQ21qemg3SEo2M3dSaWdsTHVlWDk4MFRNSzVLdkdOQ0VnIn0.eyJleHAiOjE2ODA2NjAzMjAsImlhdCI6MTY4MDY2MDAyMCwianRpIjoiN2E0YjI0NTYtYzE2Zi00OGU4LWIyZDYtYzgwNDY3OTI4N2ZmIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDgwL3JlYWxtcy9iYW9iYWIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNmIwZTc0MTUtOWFiYi00NzhiLTkwYjUtM2ExNTZmYzcxNDRiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYmFua2luZyIsInNlc3Npb25fc3RhdGUiOiJmMzJkMjI4Ni1lZTE1LTQ5NDEtYmI4MC1lZDRhOTBkYWJiZjciLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlJPTEVfVVNFUiIsIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1iYW9iYWIiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6ImYzMmQyMjg2LWVlMTUtNDk0MS1iYjgwLWVkNGE5MGRhYmJmNyIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlciIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiJ9.vw4ht9-e0OC4Ib2FKNNtUhzK01OFVqZEv3P_lc7y4rJ92C1unLC9_qw5NNOWMd2Rh1zEpYsJmEklJ0WMySd78dufSAjvp2MBKK_phs9pXaaFvAWaKK-hkXpZz4EO4OdlrsUZPUxMX8nflpwBIU9mLnCOybyNgyFVMQk8slXDd8YdGrOFBiy36XcspNUj5kfuG2rgwntinnBgyrTeXQYq0IKAaxKyzpRcozt80f7YZ2f_eAxHQpBX1At5py0Cx1xY4vDuTEHKcS4EjRCinLglwTtIVJI2buPoSpMXEJqwKP1kw4V57vDZhAgZCd4TqJPmETtTwQsrfBn_gwBSiCj-FA" \
   -H "Content-Type:application/json" \
   -d \
'{
  "type": "COURANT",
  "description": "Compte courant"
}' \
 'https://localhost:8090/api/bank-accounts-types'
```

* create new bank account for bank customer
```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "accountId":"123456",
  "balance": "0",
  "bankAccountType" : {
  "id" : 2,
  "type" : "EPARGNE",
  "description": "Compte epargne"},
"owner" : {
  "id":1402,
  "name":"MBODJ",
  "surname":"Papa Latyr",
  "msisdn":"766752975",
  "email":"latyr@esp.sn"}
}' \
 'https://localhost:8090/api/bank-accounts'
```

* deposit money into a bank account
```
curl -i -X GET \
   -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJJel91eFBGLVVrQ21qemg3SEo2M3dSaWdsTHVlWDk4MFRNSzVLdkdOQ0VnIn0.eyJleHAiOjE2ODA2NjY0MDgsImlhdCI6MTY4MDY2NjEwOCwianRpIjoiN2Q5OTcwMDEtNmFhZS00MGM5LWFmYzAtODE4YTUxYjM0MWQxIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDgwL3JlYWxtcy9iYW9iYWIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNmIwZTc0MTUtOWFiYi00NzhiLTkwYjUtM2ExNTZmYzcxNDRiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYmFua2luZyIsInNlc3Npb25fc3RhdGUiOiIzODZhZThmMC0xODhhLTRhMzQtYTk1ZC03NWJiZTE0YzM3MTQiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlJPTEVfVVNFUiIsIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1iYW9iYWIiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjM4NmFlOGYwLTE4OGEtNGEzNC1hOTVkLTc1YmJlMTRjMzcxNCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlciIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiJ9.bhVEAtvR3dSl5eyrpNEyDwxaezAVls0kWMlsUxmVsP-bU6T8cvugO3ZvJeIKXfAnrmMa8995bHipTmmV8ZteIUQgmv4_gpHlTunRm59xpCeoakQHVEo7vMVJVkZC-hAQzWg62yrYThVk27PlTP1VGnCc55zso11RMxxDzSKNykJaZswWSe6w4dwJIgkYZ-nPtI6PZrZ0B7njRJ1BJMn3bP47IzIAHLj30CjQoVg5dRBLuYWqbGOuQH1i8n9lEZj5IuhJSitRW4kBhFVxGDxVJKv4U4p3NBF0m-y-Dk51V5-l2wyzLctLA9Gdva1mzMl2tuEwRxn3GoY5N6iCAERv_Q" \
 'https://localhost:8090/api/bank-accounts/1451/deposit/1000'
```

* withdraw money into a bank account
```
curl -i -X GET \
   -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJJel91eFBGLVVrQ21qemg3SEo2M3dSaWdsTHVlWDk4MFRNSzVLdkdOQ0VnIn0.eyJleHAiOjE2ODA2NjY0MDgsImlhdCI6MTY4MDY2NjEwOCwianRpIjoiN2Q5OTcwMDEtNmFhZS00MGM5LWFmYzAtODE4YTUxYjM0MWQxIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDgwL3JlYWxtcy9iYW9iYWIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNmIwZTc0MTUtOWFiYi00NzhiLTkwYjUtM2ExNTZmYzcxNDRiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYmFua2luZyIsInNlc3Npb25fc3RhdGUiOiIzODZhZThmMC0xODhhLTRhMzQtYTk1ZC03NWJiZTE0YzM3MTQiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlJPTEVfVVNFUiIsIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1iYW9iYWIiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjM4NmFlOGYwLTE4OGEtNGEzNC1hOTVkLTc1YmJlMTRjMzcxNCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlciIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiJ9.bhVEAtvR3dSl5eyrpNEyDwxaezAVls0kWMlsUxmVsP-bU6T8cvugO3ZvJeIKXfAnrmMa8995bHipTmmV8ZteIUQgmv4_gpHlTunRm59xpCeoakQHVEo7vMVJVkZC-hAQzWg62yrYThVk27PlTP1VGnCc55zso11RMxxDzSKNykJaZswWSe6w4dwJIgkYZ-nPtI6PZrZ0B7njRJ1BJMn3bP47IzIAHLj30CjQoVg5dRBLuYWqbGOuQH1i8n9lEZj5IuhJSitRW4kBhFVxGDxVJKv4U4p3NBF0m-y-Dk51V5-l2wyzLctLA9Gdva1mzMl2tuEwRxn3GoY5N6iCAERv_Q" \
 'https://localhost:8090/api/bank-accounts/1451/withdraw/1000'
```

* get bank account balance
```
curl -i -X GET \
   -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJJel91eFBGLVVrQ21qemg3SEo2M3dSaWdsTHVlWDk4MFRNSzVLdkdOQ0VnIn0.eyJleHAiOjE2ODA2NjY0MDgsImlhdCI6MTY4MDY2NjEwOCwianRpIjoiN2Q5OTcwMDEtNmFhZS00MGM5LWFmYzAtODE4YTUxYjM0MWQxIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDgwL3JlYWxtcy9iYW9iYWIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNmIwZTc0MTUtOWFiYi00NzhiLTkwYjUtM2ExNTZmYzcxNDRiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYmFua2luZyIsInNlc3Npb25fc3RhdGUiOiIzODZhZThmMC0xODhhLTRhMzQtYTk1ZC03NWJiZTE0YzM3MTQiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlJPTEVfVVNFUiIsIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1iYW9iYWIiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjM4NmFlOGYwLTE4OGEtNGEzNC1hOTVkLTc1YmJlMTRjMzcxNCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlciIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiJ9.bhVEAtvR3dSl5eyrpNEyDwxaezAVls0kWMlsUxmVsP-bU6T8cvugO3ZvJeIKXfAnrmMa8995bHipTmmV8ZteIUQgmv4_gpHlTunRm59xpCeoakQHVEo7vMVJVkZC-hAQzWg62yrYThVk27PlTP1VGnCc55zso11RMxxDzSKNykJaZswWSe6w4dwJIgkYZ-nPtI6PZrZ0B7njRJ1BJMn3bP47IzIAHLj30CjQoVg5dRBLuYWqbGOuQH1i8n9lEZj5IuhJSitRW4kBhFVxGDxVJKv4U4p3NBF0m-y-Dk51V5-l2wyzLctLA9Gdva1mzMl2tuEwRxn3GoY5N6iCAERv_Q" \
 'https://localhost:8090/api/bank-accounts/1451/balance'
```