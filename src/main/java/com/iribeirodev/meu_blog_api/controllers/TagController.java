package com.iribeirodev.meu_blog_api.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iribeirodev.meu_blog_api.services.TagService;

@RestController
public class TagController {
	@Autowired
	private TagService tagService;
	
	@GetMapping("/tags/current")
	public CompletableFuture<ResponseEntity<List<String>>> getCurrentTags() {
	    return tagService.findCurrentTags()
						.thenApply(t -> ResponseEntity.ok(t))
						.exceptionally(ex -> ResponseEntity.badRequest().build());
	}
}
