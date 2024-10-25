package com.iribeirodev.meu_blog_api.models;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

@Entity
@Table(name = "publicacoes")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipopublicacao", nullable = false)
    private TipoPublicacao tipoPublicacao;

    @Column(length = 255)
    private String titulo;

    @Column(length = 150)
    private String tags;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDateTime dataPublicacao;

    @Column(name = "data_revisao")
    private LocalDateTime dataRevisao;

    @Column(nullable = false)
    private boolean ativo;

    @Lob
    @Column(nullable = false)
    private String texto;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public TipoPublicacao getTipoPublicacao() {
        return tipoPublicacao;
    }
    public void setTipoPublicacao(TipoPublicacao tipoPublicacao) {
        this.tipoPublicacao = tipoPublicacao;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    public LocalDateTime getDataRevisao() {
        return dataRevisao;
    }
    public void setDataRevisao(LocalDateTime dataRevisao) {
        this.dataRevisao = dataRevisao;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
}
