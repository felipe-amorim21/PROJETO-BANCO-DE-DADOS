package br.com.conta.model;

public class Cobranca extends GenericModel{
    private String mesReferencia;
    private String anoReferencia;
    private Tarifa idTarifa;
    private Medicao idMedicao;

    public Cobranca(String mesReferencia, String anoReferencia, Tarifa idTarifa, Medicao idMedicao) {
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.idTarifa = idTarifa;
        this.idMedicao = idMedicao;
    }

    public Cobranca(Integer id, String mesReferencia, String anoReferencia, Tarifa idTarifa, Medicao idMedicao) {
        super.setId(id);
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.idTarifa = idTarifa;
        this.idMedicao = idMedicao;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(String anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public Tarifa getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Tarifa idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Medicao getIdMedicao() {
        return idMedicao;
    }

    public void setIdMedicao(Medicao idMedicao) {
        this.idMedicao = idMedicao;
    }

    @Override
    public String toString() {
        return "Cobranca{" +
                "id='" + this.getId() + "\'" +
                "mesReferencia='" + mesReferencia + '\'' +
                ", anoReferencia='" + anoReferencia + '\'' +
                ", idTarifa=" + idTarifa +
                ", idMedicao=" + idMedicao +
                '}';
    }
}
