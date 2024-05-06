# Courier Tracking Project

Bu proje, Java 17 ve Spring Boot 3 kullanılarak geliştirilmiş basit bir RESTful servis örneğidir. Projede, POST ve GET isteklerinden oluşan dört adet servis bulunmaktadır. Aynı zamanda Swagger, API belgelerini otomatik olarak oluşturmak için kullanılmıştır.

## Gereksinimler

- Java 17 JDK yüklü olmalıdır.
- Apache Maven yüklü olmalıdır.

## Running the application locally
```shell
mvn spring-boot:run
```
## Swagger
Projenin çalıştırılmasıyla birlikte Swagger UI otomatik olarak başlatılacaktır. API belgelerine erişmek için aşağıdaki URL'yi ziyaret edin:t

[Swagger UI](http://localhost:8081/swagger-ui/index.html#/)

## Database
Bu projede H2 daatabase kullanıldığı için projeye ayağa çalıştırıldıktan sonra otomatik olarak tablolar oluşacaktır, verileri kontrol edebilmek için aşağıdaki linkten h2 console a erişim sağlayabilirsiniz application.properties dosyası içeriisnde bağlantı bilgileri yer almaktadır.

[H2 Console](http://localhost:8081/h2-console)

## Kullanım
```
Proje çalıştırılırken data.sql dosyasına eklediğim sorgular ile otomatik olarak kullanılacak olan tablolar ve kayıtlar oluşturulacaktır, bu sebeple proje çalıştıktan sonra isterseniz get servislerini çalıştırarak diretk incelemeleri yapabilirsiniz.

Proje 4 tablodan oluşmaktadır, kuryelerin , mağazaların, mağaza hareketlerinin ve kurye hareketlerinin tutulduğu tablolar.

Hareket kayıt etmek için servise istek atılacaktır servis içerisinde yapılan kontroller sayesinde hareketlerin tamamı courier_tracking tablosuna kayıt atılacak eğer bu giriş hareketi ise store_log tablosuna kayıt atılacaktır.
```

```
Kuryelerin hareket kayıtlarını tutumak için kullanılan servis isteğidir.

http://localhost:8081/api/v1/save

{
    "courierId":12345,
    "lat":40.98931970276002,
    "lng":29.14672662129282
} 

Kuryelerin toplam aldığı mesafe bilgilerinin verildiği servistir.

http://localhost:8081/api/v1/distance/12345

Mağazaların listelenebildiği servistir.

http://localhost:8081/api/v1/store

Mağaza giriş hareketlerinin listelenebildiği servistir.

http://localhost:8081/api/v1/storelog


```
