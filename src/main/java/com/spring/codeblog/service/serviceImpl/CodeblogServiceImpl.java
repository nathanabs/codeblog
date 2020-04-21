package com.spring.codeblog.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeblogRepository;
import com.spring.codeblog.service.CodeblogService;

@Service
public class CodeblogServiceImpl implements CodeblogService {

	@Autowired
	CodeblogRepository codeblogRep;
	
	@Override
	public List<Post> findAll() {
		return codeblogRep.findAll();
	}

	@Override
	public Post findById(Long id) {
		return codeblogRep.findById(id).get();
	}

	@Override
	public Post save(Post post) {
		return codeblogRep.save(post);
	}

	@Override
	public List<Post> findByOrderByIdDesc() {
		return codeblogRep.findByOrderByIdDesc();
	}

}
