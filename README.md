# 새로운 게시판서비스

## src12 - 게시글 리스트출력 test
  - MapperTests에 목록출력용 메서드 testSelectList() 작성
 
## src11 - 게시글 삭제 test
  - MapperTests에 삭제용 메서드 testOfDelete() 작성
  - delete_yn을 'Y'로 바꿔줌으로써 데이터는 남아있지만 조회는 할 수 없는 조건으로 만들어 준다

## src10 - 게시글 수정 test
  - MapperTests에 수정용 메서드 testOfUpdate() 작성
  - setIdx값까지 넘겨줘야 해당 idx의 데이터가 덮어씌워진다

## src09 - 게시글 조회 test
 - MapperTests에 조회용 메서드 testOfSelectDetail() 작성
 - Jackson 라이브러리는 객체를 넘겨주면 JSON 문자열로 바꿔주는 기능을 수행한다

## src08 - 게시글 등록 test
 - test용 MapperTests 작성
 - 파일 실행 후 DB확인

## src07 - DBConfiguration 수정
 - DBConfiguration이 XML Mapper에 접근할 수 있는 경로 지정 (.setMapperLocations())
 - 클래스 패키지 경로 지정 (.setTypeAliasesPackage())
 - mybatis.configuration으로 시작하는 모든 설정을 객체로 등록

## src06 - BoardMapper.xml 작성
 - src/main/resources 폴더에 mapper 패키지 생성
 - SQL쿼리문을 수행할 .xml파일 작성
 
## src05 - BoardMapper 작성
 - com.boardService.mapper 패키지 생성
 - SQL쿼리를 호출할 메서드의 모음인 BoardMapper 인터페이스 생성

## src04 - BoardDTO 작성
 - com.boardService.domain 패키지 생성
 - 기본요소를 정의하는 BoardDTO 생성
 - BoardDTO에 getter, setter 생성

## src03 - Test Sources 작성
 - src/test/java폴더의 com.boardService 패키지의 BoardServiceApplicationTests.java 내용 작성
 - JUnit으로 실행 후 정상작동하는지 확인

## src02 - DBConfiguration 작성
 - DB와 관련된 객체를 생성
 
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