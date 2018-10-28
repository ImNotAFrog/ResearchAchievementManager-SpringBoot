package edu.swjtuhc.service;

import java.util.List;

import edu.swjtuhc.model.News;
import edu.swjtuhc.model.RequestMsg;

public interface NewsService {
	Long createNews(String account);
	Integer updateNews(News news);
	Integer deleteNews(Long nId);
	News getNewsById(Long nId);
	List<News> getNewsList(RequestMsg msg);
	Integer getStateById(Long nId);
}
