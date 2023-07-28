package com.boardService.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // 이 클래스를 자바 기반의 설정파일로 지정
@PropertySource("classpath:/application.properties") // 이 클래스에서 참조할 properties 파일위치 지정
public class DBConfiguration {
	
	// ApplicationContexts는 객체의 생성&소멸을 관리
	@Autowired // Bean으로 등록된 객체를 클래스에 전달할 때 사용
	private ApplicationContext applicationContext;
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari") // spring.datasource.hikari를 매핑해서 메서드 호출을 간결하게 만든다
	public HikariConfig hikariConfig() {
		return new HikariConfig(); // connection pool 라이브러리
	}
	
	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.getObject();
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		return factoryBean.getObject();
	}
	
	// DB커넥션과 SQL실행을 제어하는 객체 생성
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
