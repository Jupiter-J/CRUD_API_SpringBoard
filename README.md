# CRUD_API 게시판 제작



## 제작 기간 & 배경
* 2022-03-14 ~ 2022-03-25
* 멋쟁이 사자의 미션이었던 게시판을 다시 제작하며 API에 대해 공부했다.

## Tech Stack
* SpringBoot, Gradle, RDS(Mysql), H2, JPA
* Swagger, Postman, Api 명세서 제작


## 기능
* 게시판의 User, Board, Post를 나눠 각각의 Create, Read, Put, Delete 기능제작 
* Restful API 
 

### ERD
<img width= "30%" src="https://user-images.githubusercontent.com/73453283/159941957-c65c6864-69ec-476b-a3b3-2cd3d71afbf6.png">


### UML
<img width= "60%" src="https://user-images.githubusercontent.com/73453283/159941840-4939a353-769c-442e-8655-1d6b341bc483.png">

### Swagger
<img width= "80%" src="https://user-images.githubusercontent.com/73453283/159959011-16a18953-ef6d-41d1-80a1-c91a3dee8e26.png">
<img width= "80%" src="https://user-images.githubusercontent.com/73453283/159960003-25b7041a-6ba9-4f63-94ca-eeafbdd8dd79.png">

### Postman
* board

![image](https://user-images.githubusercontent.com/73453283/159961694-7eca0efa-998a-4093-99a0-e10cd954f08a.png)

* User

![image](https://user-images.githubusercontent.com/73453283/159961784-fbdfd2dc-aaaf-44bb-87f8-8979539b863f.png)

* Post

![image](https://user-images.githubusercontent.com/73453283/159961894-d963f0f3-b9ad-46e1-9597-d288b6767b42.png)


### API 명세서
https://www.notion.so/090de07031a64e598a20bd54c68d6aaa




## 에러 내역
* Swagger 3.0.0을 적용하는데 에러가 났다. yml 설정이 문제였음
* 포스트맨을 다루는데 익숙치 않아 생긴 에러가 많았다


## 아쉬웠던 점
처음 제작했던 MVC패턴의 게시판보다 다양한 API를 만들어 보았다.

위의 프로젝트를 3번이상 만들었음에도 4시간이 걸린다. 

validation을 하는 부분이나 객체지향이 많이 부족하다는 것을 느끼고 공부하는 중이다.

데이터베이스 공부와 JPA로 관계 매핑을 하는 부분의 공부가 특히 중요!!


## 다음 목표 
게시판을 확장해서 만들어보기

데이터 베이스를 활용하기 

