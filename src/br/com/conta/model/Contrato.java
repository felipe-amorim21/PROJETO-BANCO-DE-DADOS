package br.com.conta.model;

import java.util.Date;

public class Contrato extends GenericModel{
    private Date dataInicio;
    private Date dataCriacao;
    private Cliente idCliente;
    private Classe idClasse;
    private Medidor idMedidor;

    public Contrato(Date dataInicio, Date dataCriacao, Cliente idCliente, Classe idClasse, Medidor idMedidor) {
        this.dataInicio = dataInicio;
        this.dataCriacao = dataCriacao;
        this.idCliente = idCliente;
        this.idClasse = idClasse;
        this.idMedidor = idMedidor;
    }

    public Contrato(Integer id, Date dataInicio, Date dataCriacao, Cliente idCliente, Classe idClasse, Medidor idMedidor) {
        super.setId(id);
        this.dataInicio = dataInicio;
        this.dataCriacao = dataCriacao;
        this.idCliente = idCliente;
        this.idClasse = idClasse;
        this.idMedidor = idMedidor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Classe getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Classe idClasse) {
        this.idClasse = idClasse;
    }

    public Medidor getIdMedidor() {
        return idMedidor;
    }

    public void setIdMedidor(Medidor idMedidor) {
        this.idMedidor = idMedidor;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id='" + this.getId() + "\'" +
                "dataInicio=" + dataInicio +
                ", dataCriacao=" + dataCriacao +
                ", idCliente=" + idCliente +
                ", idClasse=" + idClasse +
                ", idMedidor=" + idMedidor +
                '}';
    }
}
