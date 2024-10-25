package com.iribeirodev.meu_blog_api.responses;

public class PublicacaoResponse {
    private String titulo;
    private String detalhe;
    private String url;
    private String dataPublicacao;
    private String dataRevisao;
    private String tipoPublicacao;
    private String tags;
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDetalhe() {
        return detalhe;
    }
    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    public String getDataRevisao() {
        return dataRevisao;
    }
    public void setDataRevisao(String dataRevisao) {
        this.dataRevisao = dataRevisao;
    }
    public String getTipoPublicacao() {
        return tipoPublicacao;
    }
    public void setTipoPublicacao(String tipoPublicacao) {
        this.tipoPublicacao = tipoPublicacao;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
}
