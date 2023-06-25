package br.com.conta.model;

public class TimeRota extends GenericModel{
    private Funcionario idFuncionario;
    private TarefaRota idTarefaRota;

    public TimeRota(Funcionario idFuncionario, TarefaRota idTarefaRota) {
        this.idFuncionario = idFuncionario;
        this.idTarefaRota = idTarefaRota;
    }

    public TimeRota(Integer id, Funcionario idFuncionario, TarefaRota idTarefaRota) {
        super.setId(id);
        this.idFuncionario = idFuncionario;
        this.idTarefaRota = idTarefaRota;
    }

    public Funcionario getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionario idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public TarefaRota getIdTarefaRota() {
        return idTarefaRota;
    }

    public void setIdTarefaRota(TarefaRota idTarefaRota) {
        this.idTarefaRota = idTarefaRota;
    }

    @Override
    public String toString() {
        return "TimeRota{" +
                "id='" + this.getId() + "\'" +
                "idFuncionario=" + idFuncionario +
                ", idTarefaRota=" + idTarefaRota +
                '}';
    }
}
