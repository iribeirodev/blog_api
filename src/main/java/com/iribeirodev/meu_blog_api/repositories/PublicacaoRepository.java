package com.iribeirodev.meu_blog_api.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.iribeirodev.meu_blog_api.models.Publicacao;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {
    @Query("SELECT p.tags FROM Publicacao p WHERE p.ativo = true")
    List<String> findTagsByAtivo();

    Optional<Publicacao> findByUrl(String url);

}