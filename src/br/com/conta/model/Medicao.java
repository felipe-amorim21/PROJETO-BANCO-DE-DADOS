package br.com.conta.model;

import java.util.Date;

public class Medicao extends GenericModel{
    private String mes;
    private String ano;
    private Date dataMedicao;
    private String valor;
    private Medidor idMedidor;
    private TimeRota idTimeRota;

    public Medicao(String mes, String ano, Date dataMedicao, String valor, Medidor idMedidor, TimeRota idTimeRota) {
        this.mes = mes;
        this.ano = ano;
        this.dataMedicao = dataMedicao;
        this.valor = valor;
        this.idMedidor = idMedidor;
        this.idTimeRota = idTimeRota;
    }

    public Medicao(Integer id, String mes, String ano, Date dataMedicao, String valor, Medidor idMedidor, TimeRota idTimeRota) {
        super.setId(id);
        this.mes = mes;
        this.ano = ano;
        this.dataMedicao = dataMedicao;
        this.valor = valor;
        this.idMedidor = idMedidor;
        this.idTimeRota = idTimeRota;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Date getDataMedicao() {
        return dataMedicao;
    }

    public void setDataMedicao(Date dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Medidor getIdMedidor() {
        return idMedidor;
    }

    public void setIdMedidor(Medidor idMedidor) {
        this.idMedidor = idMedidor;
    }

    public TimeRota getIdTimeRota() {
        return idTimeRota;
    }

    public void setIdTimeRota(TimeRota idTimeRota) {
        this.idTimeRota = idTimeRota;
    }

    @Override
    public String toString() {
        return "Medicao{" +
                "id='" + this.getId() + "\'" +
                "mes='" + mes + '\'' +
                ", ano='" + ano + '\'' +
                ", dataMedicao=" + dataMedicao +
                ", valor='" + valor + '\'' +
                ", idMedidor=" + idMedidor +
                ", idTimeRota=" + idTimeRota +
                '}';
    }
}
