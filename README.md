# 프로젝트 소개

## 프로젝트명
**MSA를 이용한 호텔 예약 서비스**

---

## 팀원
- **이상엽**
- **김선영**
- **김민지**

---

## 프로젝트 개요
MSA(Microservice Architecture)를 활용하여 호텔 예약 서비스를 구축한 프로젝트입니다.

---

## 팀원 역할 분담
- **이상엽**: Booking Service
- **김선영**: Hotel Service 
- **김민지**: User Service, UserAdmin, Hotel Service, BookingSerivce, Front Server

---

## 기술 스택
- **Frontend**: Thymeleaf
- **Backend**: Spring Boot, Java, MySQL

---

## 요구사항 명세서
![image](https://github.com/user-attachments/assets/70d3a814-e046-4a33-9c1b-242ef7689c22)

[+] 클라이언트의 맞춤형 관리 기능 옵션 추가

![image](https://github.com/user-attachments/assets/b19ddf67-5a69-45f9-a96b-b557b584eb77)





---

## 화면 설계서



<details>
<summary><strong>1. 메인 페이지</strong></summary>

#### 1.1 전체화면

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main                                   |
| 요구사항 ID  | AC-US-001, HO-DA-002                   |
| 설명         | 호텔 리스트를 조회할 수 있으며 반응형을 지원한다. |

![Animation](https://github.com/user-attachments/assets/046705f0-4282-4020-9b06-e4e3bc92b856)




#### 1.2 조건 검색 (미완성)

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main                                   |
| 요구사항 ID  | HO-DA-002, AC-AD-005            |
| 설명         | 호텔명과 지역, 옵션별로 검색할 수 있다. |

![Animation1](https://github.com/user-attachments/assets/1497bc8d-71a4-4291-b5d3-24da7cb24f10)



#### 1.3 호텔 예약

| 항목         | 내용                                              |
|--------------|---------------------------------------------------|
| 경로         | main > hotel_card                                 |
| 요구사항 ID  | AC-US-005, HO-BO-002, HO-BO-003                   |
| 설명         | 호텔 항목을 누르면 모달이 나타나서 예약할 수 있다. |


![Animation2](https://github.com/user-attachments/assets/4225b69c-7f29-4b5a-9a66-992065566b32)


</details>




<details>
<summary><strong>2. 로그인/ 회원가입</strong></summary>

#### 2.1 로그인(사용자)

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main > user_login                            |
| 요구사항 ID  | AC-US-001                               |
| 설명         | 일반 사용자 로그인이 가능하다.            |

![KakaoTalk_20250105_151734976-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/33358b2c-b08e-42e7-9377-390e9631653c)




### 2.2 로그인(관리자)

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main > amdin_login                      |
| 요구사항 ID  | AC-AD-001                               |
| 설명         | 관리자 로그인이 가능하다.                |

![Animation3](https://github.com/user-attachments/assets/ac4a04c8-f509-4dde-92e3-46a6ebaa2bec)




### 2.3 회원가입

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main > register, main > login > register      |
| 요구사항 ID  | AC-US-002, AC-AD-002                  |
| 설명         | 사용자를 추가 할 수 있다. |

![Animation4](https://github.com/user-attachments/assets/8970279d-f115-4dc0-92af-b3efb3eb38a3)

</details>


<details>
<summary><strong>3. 마이페이지</strong></summary>

### 3.1 마이페이지

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main                                             |
| 요구사항 ID  | AC-US-001, HO-DA-002                             |
| 설명         | 호텔 리스트를 조회할 수 있으며 반응형을 지원한다. |

![Animation5](https://github.com/user-attachments/assets/4c75f6f6-9d29-41f7-9ed6-015a51121221)



### 3.2 나의 예약현황

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main > login > mypage                   |
| 요구사항 ID  | AC-US-001, HO-DA-002                    |
| 설명         | 호텔 리스트를 조회할 수 있으며 반응형을 지원한다. |

![Animation6](https://github.com/user-attachments/assets/f648b92c-75ce-4a9e-b160-d7012ec4b7a3)

</details>



<details>
<summary><strong>4. 관리자</strong></summary>

### 4.1 회원관리

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main                                   |
| 요구사항 ID  | AC-US-001, HO-DA-002                   |
| 설명         | 호텔 리스트를 조회할 수 있으며 반응형을 지원한다. |


![Animation7](https://github.com/user-attachments/assets/ac9a0c2d-1928-45a1-83fe-61ecf27e0f99)




### 4.2 회원추가

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main > join                                  |
| 요구사항 ID  | AC-US-001, HO-DA-002                   |
| 설명         | 호텔 리스트를 조회할 수 있으며 반응형을 지원한다. |

![Animation8](https://github.com/user-attachments/assets/4d4d85b9-db28-4137-b718-0c9b5b0eae58)




### 4.2 회원삭제

| 항목         | 내용                                    |
|--------------|-----------------------------------------|
| 경로         | main                           |
| 요구사항 ID  | AC-US-001, HO-DA-002                    |
| 설명         | 호텔 리스트를 조회할 수 있으며 반응형을 지원한다. |

![Animation9](https://github.com/user-attachments/assets/3167fc99-c561-497b-a854-20d0a053b70c)




</details>


---

