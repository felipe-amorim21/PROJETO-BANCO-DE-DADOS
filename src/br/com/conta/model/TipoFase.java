package br.com.conta.model;

public class TipoFase extends GenericModel {
    private String descricao;
    private String observacao;

    public TipoFase(String descricao) {
        this.descricao = descricao;
    }

    public TipoFase(String descricao, String observacao) {
        this.descricao = descricao;
        this.observacao = observacao;
    }

    public TipoFase(Integer id, String descricao) {
        this.descricao = descricao;
        super.setId(id);
    }

    public TipoFase(Integer id, String descricao, String observacao) {
        super.setId(id);
        this.descricao = descricao;
        this.observacao = observacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Tipo_fase{" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + "\'" +
                '}';
    }
}
