# lalafo-demo

## How to start project

## Add variables to enviroment from application.yaml
```bash
lalafo:
  url: ${LALAFO_URL}
  userAgent: ${LALAFO_USER_AGENT}
  headerAccept: ${LALAFO_HEADER_ACCEPT}
  device: ${LALAFO_DEVICE}
  countryId: ${LALAFO_COUNTRY_ID}
  language: ${LALAFO_LANGUAGE}
  requestId: ${LALAFO_REQUEST_ID}
```

## Then run jar file
```bash
java -jar muratkanov-muratbek-lalafo-0.0.1-SNAPSHOT.jar
```

## Or run through docker
```bash
docker build -t title_of_container .
docker run -p 9090:9090 title_of_container
```