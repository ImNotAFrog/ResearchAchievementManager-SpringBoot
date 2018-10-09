package edu.swjtuhc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.swjtuhc.mapper.ThesisMapper;
import edu.swjtuhc.model.Thesis;
import edu.swjtuhc.service.ThesisService;

@Service
public class ThesisServiceImpl implements ThesisService{

	@Autowired
	ThesisMapper thesisMapper;
	
	@Override
	public Integer createThesis(Thesis thesis) {
		// TODO Auto-generated method stub
		
		return thesisMapper.createThesis(thesis);
	}

}
