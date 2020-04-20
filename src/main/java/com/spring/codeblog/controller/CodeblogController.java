package com.spring.codeblog.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;

@Controller
public class CodeblogController {
	@Autowired
	CodeblogService codeblogService;
	
	@GetMapping("/posts")
	public ModelAndView getPosts() {
		ModelAndView mv = new ModelAndView("posts");
		
		List<Post> posts = codeblogService.findAll();
		mv.addObject("posts", posts);
		
		return mv;
	}
	@GetMapping("/posts/{id}")
	public ModelAndView getPostById(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("postDetails");
		Post post = codeblogService.findById(id);
		mv.addObject("post", post);
		
		return mv;
	}
	
	@GetMapping("/newPost")
	public String getPostForm() {
		
		return "postForm";
	}
	
	@PostMapping("/newPost")
	public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
		
		attributes.addFlashAttribute("message", "Verifique se os campos obrigatórios foram preenchidos.");
		
		if(result.hasErrors()) {
			return "redirect:/newPost";
		}
		
		post.setDate(LocalDate.now());
		
		codeblogService.save(post);
		
		return "redirect:/posts";
	}
}
