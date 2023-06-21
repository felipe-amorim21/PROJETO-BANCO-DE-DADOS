package br.com.conta.model;

import java.util.Date;

public class TarefaRota extends GenericModel{
    private String observacao;
    private Date dataInicio;
    private Date dataFim;
    private String tarefaRotacol;
    private Rota idRota;

    public TarefaRota(String observacao, Date dataInicio, Date dataFim, String tarefaRotacol, Rota idRota) {
        this.observacao = observacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tarefaRotacol = tarefaRotacol;
        this.idRota = idRota;
    }

    public TarefaRota(Integer id, String observacao, Date dataInicio, Date dataFim, String tarefaRotacol, Rota idRota) {
        super.setId(id);
        this.observacao = observacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tarefaRotacol = tarefaRotacol;
        this.idRota = idRota;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public String getTarefaRotacol() {
        return tarefaRotacol;
    }

    public void setTarefaRotacol(String tarefaRotacol) {
        this.tarefaRotacol = tarefaRotacol;
    }

    public Rota getIdRota() {
        return idRota;
    }

    public void setIdRota(Rota idRota) {
        this.idRota = idRota;
    }

    @Override
    public String toString() {
        return "TarefaRota{" +
                "id='" + this.getId() + "\'" +
                "observacao='" + observacao + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", tarefaRotacol='" + tarefaRotacol + '\'' +
                ", idRota=" + idRota +
                '}';
    }
}
