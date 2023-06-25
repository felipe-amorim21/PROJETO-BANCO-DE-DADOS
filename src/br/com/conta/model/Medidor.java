package br.com.conta.model;

public class Medidor extends GenericModel{
    private String descricao;
    private Poste idPoste;
    private Rota idRota;

    public Medidor(String descricao, Poste idPoste, Rota idRota) {
        this.descricao = descricao;
        this.idPoste = idPoste;
        this.idRota = idRota;
    }

    public Medidor(Integer id, String descricao, Poste idPoste, Rota idRota) {
        super.setId(id);
        this.descricao = descricao;
        this.idPoste = idPoste;
        this.idRota = idRota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Poste getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Poste idPoste) {
        this.idPoste = idPoste;
    }

    public Rota getIdRota() {
        return idRota;
    }

    public void setIdRota(Rota idRota) {
        this.idRota = idRota;
    }

    @Override
    public String toString() {
        return "Medidor{" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + '\'' +
                ", id_poste=" + idPoste +
                ", id_rota=" + idRota +
                '}';
    }
}
