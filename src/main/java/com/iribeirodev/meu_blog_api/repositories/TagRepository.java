package com.iribeirodev.meu_blog_api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.iribeirodev.meu_blog_api.models.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
	@Query(nativeQuery = true, value="""
			SELECT t.id, t.nome 
			FROM tags t 
			INNER JOIN tagspublicacoes tp 
			   ON tp.id_tag = t.id 
			INNER JOIN publicacoes p 
			   ON tp.id_publicacao = p.id
			  AND p.ativo = 1
			ORDER BY t.nome
			""")
	List<Tag> findCurrentTags();
}
