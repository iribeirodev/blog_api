package com.iribeirodev.meu_blog_api.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.iribeirodev.meu_blog_api.responses.PublicacaoResponse;
import com.iribeirodev.meu_blog_api.responses.PublicacaoTextoResponse;
import com.iribeirodev.meu_blog_api.services.PublicacaoService;

@RestController
public class PublicacaoController {
    @Autowired
    private PublicacaoService publicacaoService;

	@GetMapping("/publicacoes/current")
	public CompletableFuture<ResponseEntity<List<PublicacaoResponse>>> getCurrentPublications() {
        return publicacaoService.findCurrentPublications()
                            .thenApply(p -> ResponseEntity.ok(p))
                            .exceptionally(ex -> ResponseEntity.badRequest().build());
	}

    @GetMapping("/publicacoes/bytag/{tag}")
    public CompletableFuture<ResponseEntity<List<PublicacaoResponse>>> getCurrentPublicationsByTag(@PathVariable String tag) {
        return publicacaoService.findCurrentPublicationsByTag(tag)
            .thenApply(publicacoes -> ResponseEntity.ok(publicacoes))
            .exceptionally(ex -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/publicacoes/bytype/{publicationType}")
    public CompletableFuture<ResponseEntity<List<PublicacaoResponse>>> getCurrentPublicationsByType(@PathVariable String publicationType) {
        return publicacaoService.findCurrentPublicationsByType(publicationType)
            .thenApply(publicacoes -> ResponseEntity.ok(publicacoes))
            .exceptionally(ex -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/publicacoes/byurl/{url}")
    public CompletableFuture<ResponseEntity<PublicacaoTextoResponse>> getPublicationByURL(@PathVariable String url) {
        return publicacaoService.findCurrentPublicationByURL(url)
                    .thenApply(publicacao -> ResponseEntity.ok(publicacao))
                    .exceptionally(ex -> ResponseEntity.badRequest().build());
    }

}
