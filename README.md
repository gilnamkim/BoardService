# 새로운 게시판서비스

## src01 - application.properties 설정
	- jdbc-url / 데이터베이스의 주소
	- username / MySQL을 설치하는 과정에서 입력했던 아이디
	- password / MySQL을 설치하는 과정에서 입력했던 패스워드
	- connection-test-query / 커넥션 확인을 위한 SQL 쿼리문

## src00 - 새로운 spring project 생성

<폴더구조>

```
	src/
		main/
			java/
				자바 소스파일(.java)을 두는 폴더
			resources
				자바 프로그램 설정파일(.xml, .properties 등)을 두는 폴더
		test/
			java/
				테스트 관련 자바 소스파일을 두는 폴더
			resources
				테스트 관련 설정파일 두는 폴더

```