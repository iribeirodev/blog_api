package com.iribeirodev.meu_blog_api.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iribeirodev.meu_blog_api.repositories.PublicacaoRepository;
import com.iribeirodev.meu_blog_api.responses.PublicacaoResponse;
import com.iribeirodev.meu_blog_api.responses.PublicacaoTextoResponse;

@Service
public class PublicacaoService {
    @Autowired
    private PublicacaoRepository publicacaoRepository;

    /**
     * Obtém as publicações ativas.
     * @return CompletableFuture<List<PublicacaoResponse>>
     */
    public CompletableFuture<List<PublicacaoResponse>> findCurrentPublications() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<PublicacaoResponse> publicacoes = publicacaoRepository.findAll().stream()
            .filter(publicacao -> publicacao.isAtivo()) // Filtra apenas publicações ativas
            .map(publicacao -> {

                PublicacaoResponse response = new PublicacaoResponse();
                response.setTitulo(publicacao.getTitulo());
                response.setUrl(publicacao.getUrl());
                response.setDataPublicacao(publicacao.getDataPublicacao().format(formatter));
                response.setDataRevisao(
                    Optional.ofNullable(publicacao.getDataRevisao())
                            .map(dataRev -> dataRev.format(formatter))
                            .orElse(null)
                );
                response.setTipoPublicacao(publicacao.getTipoPublicacao().getNome());
                response.setTags(publicacao.getTags() != null ? publicacao.getTags() : null);
                response.setDetalhe(setDetail(publicacao.getDataPublicacao(), publicacao.getTipoPublicacao().getNome()));

                return response;
            })
            .collect(Collectors.toList());

        return CompletableFuture.completedFuture(publicacoes);
    }

    public CompletableFuture<PublicacaoTextoResponse> findCurrentPublicationByURL(String url) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        PublicacaoTextoResponse publicacao = publicacaoRepository.findAll().stream()
            .filter(p -> p.getUrl().equals(url) && p.isAtivo())
            .map(p -> {
                PublicacaoTextoResponse response = new PublicacaoTextoResponse();
                response.setTitulo(p.getTitulo());
                response.setUrl(url);
                response.setDataPublicacao(p.getDataPublicacao().format(formatter));
                response.setTags(p.getTags() != null ? p.getTags() : null);
                response.setTexto(p.getTexto());

                return response;
            })
            .findFirst().orElse(null);
            
        return CompletableFuture.completedFuture(publicacao);
    }

    /**
     * Obtém as publicações ativas filtradas por tag.
     * @param tag criteria
     * @return CompletableFuture<List<PUblicacaoResponse>>
     */
    public CompletableFuture<List<PublicacaoResponse>> findCurrentPublicationsByTag(String tag) {
    
        String criteria = tag.toLowerCase();
    
        return findCurrentPublications().thenApply(publicacoes -> 
            publicacoes.stream()
                .filter(publicacao -> publicacao.getTags() != null && 
                    Arrays.stream(publicacao.getTags().split(","))
                        .map(String::trim) // Remove espaços em branco
                        .anyMatch(t -> t.toLowerCase().equals(criteria)))
                .collect(Collectors.toList())
        );
    }

    /**
     * Obtém as publicações ativas filtradas por tipo de publicação.
     * @param publicationType criteria
     * @return CompletableFuture<List<PUblicacaoResponse>>
     */    
    public CompletableFuture<List<PublicacaoResponse>> findCurrentPublicationsByType(String publicationType) {
        String criteria = publicationType.toLowerCase();

        return findCurrentPublications().thenApply(publicacoes -> 
            publicacoes.stream()
                .filter(publicacao -> publicacao.getTipoPublicacao().toLowerCase().equals(criteria))
                .collect(Collectors.toList())
        );
    }

    /**
     * Gera o detalhe baseado na data de publicação.
     * @param dataPublicacao
     * @param tipoPublicacao
     * @return String
     */
    private String setDetail(LocalDateTime dataPublicacao, String tipoPublicacao) {
        long diasDiferenca = ChronoUnit.DAYS.between(dataPublicacao.toLocalDate(), LocalDate.now());

        String detalhe;
        if (diasDiferenca < 30) {
            detalhe = "há " + diasDiferenca + " dias atrás";
        } else if (diasDiferenca < 365) {
            detalhe = "há " + (diasDiferenca / 30) + " meses";
        } else if (diasDiferenca < 730) {
            detalhe = "há 1 ano";
        } else {
            detalhe = "há " + (diasDiferenca / 365) + " anos";
        }

        return "Na seção " + tipoPublicacao + " " + detalhe;
    }

}
