package br.com.conta.model;

public class Classe extends GenericModel{
    private String descricao;
    private TipoFase idTipoFase;

    public Classe(String descricao, TipoFase idTipoFase) {
        this.descricao = descricao;
        this.idTipoFase = idTipoFase;
    }

    public Classe(Integer id, String descricao, TipoFase idTipoFase) {
        super.setId(id);
        this.descricao = descricao;
        this.idTipoFase = idTipoFase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoFase getIdTipoFase() {
        return idTipoFase;
    }

    public void setIdTipoFase(TipoFase idTipoFase) {
        this.idTipoFase = idTipoFase;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + '\'' +
                ", idTipoFase=" + idTipoFase +
                '}';
    }
}
