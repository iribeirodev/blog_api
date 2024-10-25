package com.iribeirodev.meu_blog_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iribeirodev.meu_blog_api.repositories.PublicacaoRepository;

@Service
public class TagService {
	@Autowired
	private PublicacaoRepository publicacaoRepository;
	
	/**
	 * Obtém o nome das tags utilizadas.
	 * @return CompletableFuture<List<string>>
	 */
	public CompletableFuture<List<String>> findCurrentTags() {
		List<String> tagsPublicacoes = publicacaoRepository.findTagsByAtivo();
		List<String> individualTags = new ArrayList<>();

		for (String tags: tagsPublicacoes) {
			String[] tagsArray = tags.split(",");
			for (String tag: tagsArray) {
				individualTags.add(tag.trim());
			}
		}

		return CompletableFuture.completedFuture(individualTags);
	}
}
