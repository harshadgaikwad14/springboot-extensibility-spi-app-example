# springboot-extensibility-spi-app-example
Extensibility Achieve Using Service Provider Api SpingBoot Application

## Run Jar
	  java -cp core-app-0.0.1-SNAPSHOT.jar
	  -Dloader.path=/C:/Users/harshad.gaikwad/Desktop/jar/
	  org.springframework.boot.loader.PropertiesLauncher
## Post Payload
    POST /coreModels HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    cache-control: no-cache
    Postman-Token: 5531d4fb-2674-4bc0-a9e6-7954b53759fd
    {
        "id": 1,
        "name": "Vivek",
        "extensibleObject": {
            "code": 1,
            "description": "Debit"
        }
    }------WebKitFormBoundary7MA4YWxkTrZu0gW--