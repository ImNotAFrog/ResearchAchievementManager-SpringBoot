package edu.swjtuhc.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.swjtuhc.mapper.ThesisMapper;
import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.service.ThesisService;
import edu.swjtuhc.utils.IdWorker;

@Service
public class ThesisServiceImpl implements ThesisService{

	@Autowired
	ThesisMapper thesisMapper;
	
	@Override
	public Long createThesis(String account) {
		// TODO Auto-generated method stub
		Thesis thesis = new Thesis();
		thesis.setUploader(account);
		thesis.setUploadDate(new Date());
		thesis.settId(IdWorker.getInstance().nextId());
		thesisMapper.createThesis(thesis);
		return thesis.gettId();
	}

	@Override
	public Thesis getThesisById(Long tId) {
		// TODO Auto-generated method stub
		return thesisMapper.getThesisById(tId);
	}

	@Override
	public Integer updateThesis(Thesis thesis) {
		// TODO Auto-generated method stub
		thesis.setUploadDate(new Date());
		return thesisMapper.updateThesis(thesis);
	}

	@Override
	public Integer deleteThesis(Long tId) {
		// TODO Auto-generated method stub
		return thesisMapper.deleteThesisById(tId);
	}

}
