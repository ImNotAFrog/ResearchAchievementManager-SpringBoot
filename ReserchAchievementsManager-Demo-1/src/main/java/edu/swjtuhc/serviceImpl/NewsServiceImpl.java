package edu.swjtuhc.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.swjtuhc.mapper.NewsMapper;
import edu.swjtuhc.model.News;
import edu.swjtuhc.model.PagingRequestMsg;
import edu.swjtuhc.service.NewsService;
import edu.swjtuhc.utils.IdWorker;
@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsMapper newsMapper;
	@Override
	public Long createNews(String account) {
		// TODO Auto-generated method stub
		News n = new News();
		n.setnId(IdWorker.getInstance().nextId());
		n.setUploadDate(new Date());
		n.setUploader(account);
		n.setState(1);
		if(newsMapper.createNews(n)==1) {
			return n.getnId();
		}
		return -1L;
	}

	@Override
	public Integer updateNews(News news) {
		// TODO Auto-generated method stub
		news.setUploadDate(new Date());
		return newsMapper.updateNews(news);
	}
	
	

	@Override
	public Integer deleteNews(Long nId) {
		// TODO Auto-generated method stub
		return newsMapper.deleteNews(nId);
	}

	@Override
	public News getNewsById(Long nId) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsById(nId);
	}

	@Override
	public List<News> getNewsList(PagingRequestMsg msg) {
		// TODO Auto-generated method stub
		msg.setStart((msg.getPage()-1)*msg.getLimit());
		return newsMapper.getNewsList(msg);
	}

	@Override
	public Integer getStateById(Long nId) {
		// TODO Auto-generated method stub
		News n = newsMapper.getNewsById(nId);		
		return n.getState();
	}

}
