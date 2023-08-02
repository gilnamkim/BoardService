# 새로운 게시판서비스

## src22 - 공통컨트롤러 생성
 - 사용자부터 받은 입력값에 따라 메시지와 리다이렉트를 처리하는 UiUtils를 작성
 - BoardController는 상속받은 UiUtils클래스의 showMessageWithRedirect메서드를 통해 메시지, 리다이렉트URI, HTTP요청메서드, 데이터객체를 model객체에 담는다.
 - 지금은 register와 delete만 수정할 것이기 때문에 데이터객체 파라미터는 null로 전달한다.
 - redirect한 message-redirect.html에서 alert창을 구성하여 화면을 출력한다.

## src21 - 게시글 삭제 처리
 - view.html에서 삭제하기 버튼을 누르면 idx값을 가지고 delete.do를 수행할 메서드를 컨트롤러에 작성한다(deleteBoard메서드).
 - view.html에 confirm함수를 이용하여 게시글 삭제 여부를 확인하는 코드를 작성한다.
 - confirm함수가 true일 경우 html변수에 폼을 생성하고 전달할 파라미터를 담아 폼을 append로 body태그안에 추가해 준다.
 - dataForm.submit()함수로 body에 추가된 폼을 찾아 컨트롤러로 전달한다.

## src20 - 게시글 조회 처리
 - list.html로 띄운 boardList에서 게시글번호를 가지고 view.do를 수행할 openBoardDetail메서드를 작성한다.
 - openBoardDetail메서드는 idx 파라미터값으로 board객체를 가져와서 저장한다음 model객체에 매핑한다.
 - view.html을 생성하여 openBoardDetail에서 담아온 board를 렌더링하는 코드를 작성한다.

## src19 - 게시글 목록 처리
 - BoardController의 registerBoard메서드 리턴값인 redirectURL을 이용하여 리스트객체를 만들어 전달하는 openBoardList메서드를 작성
 - list.html을 생성하여 openBoardList에서 담아온 boardList를 렌더링하는 코드를 작성한다.

## src18 - 게시글 등록 처리
 - write.html에 사용자로부터 입력받은 form 데이터를 javascript로 데이터유효성 검사 후 서버로 전달하는 코드 추가
 - BoardController에서 post방식으로 전달받은 데이터를 처리하는 메서드 추가

## src17 - bootstrap 적용
 - header.html, body.html 작성
 - basic.html을 생성하여 th:fragment 속성을 사용하여 동적으로 처리

## src16 - Model 인터페이스 활용
 - model 인터페이스를 활용하여 model객체에 데이터를 매핑하여 html에 전달
 - thymeleaf라이브러리를 활용하여 컨트롤러에서 받은 model 데이터에 접근한다

## src15 - 화면 처리
 - write.html을 추가한 후 어플리케이션 실행
 - http://localhost:8080/boardService/write.do 를 입력하면 write.html 화면이 나와야 한다
 
## src15-1 에러발생
 - URL을 잘못입력하면 컨트롤러에서 요청에 매칭되는 명령이 없기때문에 404에러가 발생
 - 실수로 'write.html'을 'writer.html'로 파일을 만들었다
 - openBoardWrite의 리턴값이 'boardService/write'인데 write.html 파일이 없으니 에러가 발생

## src14 - 컨트롤러 처리
 - URL로 GET요청이 들어오면 BoardService으로 로직을 수행
 - boardService/write 뷰 템플릿을 반환하여 클라이언트에게 보여주는 역할을 수행

## src13 - 서비스 영역 처리
 - MapperTests에서 잘 작동을 하였으니 이제 메인에서 각 메서드를 구현한다
 - service 인터페이스를 만든 후
 - 인터페이스를 상속받아 메서드를 구현한다

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
		java/자바 소스파일(.java)을 두는 폴더
		resources/자바 프로그램 설정파일(.xml, .properties 등)을 두는 폴더
	test/
		java/테스트 관련 자바 소스파일을 두는 폴더
		resources/테스트 관련 설정파일 두는 폴더

```