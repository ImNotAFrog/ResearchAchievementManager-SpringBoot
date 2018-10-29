package edu.swjtuhc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.swjtuhc.model.News;
import edu.swjtuhc.model.PagingRequestMsg;
@Mapper
public interface NewsMapper {
	Long createNews(News news);
	Integer updateNews(News news);
	Integer deleteNews(Long nId);
	News getNewsById(Long nId);
	List<News> getNewsList(PagingRequestMsg msg);
}
