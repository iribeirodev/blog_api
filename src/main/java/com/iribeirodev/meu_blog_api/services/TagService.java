package com.iribeirodev.meu_blog_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iribeirodev.meu_blog_api.models.Tag;
import com.iribeirodev.meu_blog_api.repositories.TagRepository;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;
	
	/**
	 * Obtém o nome de todas as tags.
	 * @return List<string>
	 */
	public List<String> findAllTags() {
		List<Tag> tags = tagRepository.findAll();
		return tags.stream().map(Tag::getNome)
				.collect(Collectors.toList());
	}
	
	/**
	 * Obtém o nome das tags utilizadas.
	 * @return List<string>
	 */
	public List<String> findCurrentTags() {
		List<Tag> tags = tagRepository.findCurrentTags();
		return tags.stream().map(Tag::getNome)
				.collect(Collectors.toList());
	}
}
