package br.com.conta.model;

import java.util.Date;

public class Tarifa extends GenericModel{
    private String taxa;
    private String lei;
    private Date dataInicio;
    private Date dataFim;
    private Classe idClasse;

    public Tarifa(String taxa, String lei, Date dataInicio, Date dataFim, Classe idClasse) {
        this.taxa = taxa;
        this.lei = lei;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idClasse = idClasse;
    }

    public Tarifa(Integer id, String taxa, String lei, Date dataInicio, Date dataFim, Classe idClasse) {
        super.setId(id);
        this.taxa = taxa;
        this.lei = lei;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idClasse = idClasse;
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;
    }

    public String getLei() {
        return lei;
    }

    public void setLei(String lei) {
        this.lei = lei;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Classe getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Classe idClasse) {
        this.idClasse = idClasse;
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "id='" + this.getId() + "\'" +
                "taxa='" + taxa + '\'' +
                ", lei='" + lei + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", classe=" + idClasse +
                '}';
    }
}
