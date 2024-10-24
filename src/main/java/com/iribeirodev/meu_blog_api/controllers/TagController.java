package com.iribeirodev.meu_blog_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iribeirodev.meu_blog_api.infrastructure.handlers.ResponseHandler;
import com.iribeirodev.meu_blog_api.services.TagService;

@RestController
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@GetMapping("/tags/all")
	public <T> ResponseEntity<Map<String, Object>> getAllTags() {
	    List<String> response = tagService.findAllTags();

		return ResponseHandler.generateResponse(HttpStatus.OK, response);

	}
	
	@GetMapping("/tags/current")
	public <T> ResponseEntity<Map<String, Object>> getCurrentTags() {
	    List<String> response = tagService.findCurrentTags();

		return ResponseHandler.generateResponse(HttpStatus.OK, response);
		
	    // return response.isEmpty()
	    //     ? ResponseHandler.generateNotFound("No current tags found")
	    //     : ResponseHandler.generateResponse(HttpStatus.OK, response);
	}
}
