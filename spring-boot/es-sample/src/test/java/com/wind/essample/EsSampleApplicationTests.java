package com.wind.essample;

import com.wind.essample.entity.Article;
import com.wind.essample.service.IArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsSampleApplicationTests {

	@Autowired
	private IArticleService articleService;

	@Test
	public void testSave(){

		Article article = new Article();
		article.setId(1L);
		article.setTitle("springboot integreate elasticsearch");
		article.setAbstracts("springboot integreate elasticsearch is very easy");
		article.setTutorial("test");
		article.setAuthor("wind");
		article.setContent("elasticsearch based on lucene,"
				+ "spring-data-elastichsearch based on elaticsearch"
				+ ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
		article.setClickCount(1L);
		articleService.save(article);
	}

	@Test
	public void testFindByCondition(){

		List<Article> articleList = articleService.findByCondition("wind");
		System.out.println(articleList);
	}

}
