package com.boardService.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class Criteria {
	
	private int currentPageNo; // 현재 페이지 번호
	private int recordsPerPage; // 페이지마다 출력할 데이터 개수
	private int pageSize; // 화면 하단에 출력할 데이터 개수
	private String searchKeyword; //동적 SQL을 처리하면서 원하는 데이터를 찾기위한 검색 키워드
	private String searchType; // 게시글의 제목, 내용, 작성자 중 하나 또는 전체로 LIKE 검색
	
	// 기본값으로 currentPageNo는 1
	// pageSize가 10일경우 : << < 1 2 ... 9 10 > >>
	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 10;
		this.pageSize = 10;
	}
	
	// MySQL에서 LIMIT 구문의 앞부분에 사용되는 메서드
	public int getStartPage() {
		return (currentPageNo - 1) * recordsPerPage;
	}

	/* Getter Setter 구역*/
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	/* toString 구역*/
	
	@Override
	public String toString() {
		return "Criteria [currentPageNo=" + currentPageNo + ", recordsPerPage=" + recordsPerPage + ", pageSize="
				+ pageSize + ", searchKeyword=" + searchKeyword + ", searchType=" + searchType + "]";
	}
	
	// 객체의 pageNo를 이용하여 해당 객체의 요소를 uri로 만들어 리턴
	public String makeQueryString(int pageNo) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("currentPageNo", pageNo)
				.queryParam("recordsPerPage", recordsPerPage)
				.queryParam("pageSize", pageSize)
				.queryParam("searchType", searchType)
				.queryParam("searchKeyword", searchKeyword)
				.build()
				.encode();
		
		return uriComponents.toUriString();
	}
}
