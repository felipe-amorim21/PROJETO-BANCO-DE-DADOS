package br.com.conta.model;

public class Funcionario extends GenericModel{
    private String codigoFuncional;
    private Pessoa idPessoa;

    public Funcionario(String codigoFuncional, Pessoa idPessoa) {
        this.codigoFuncional = codigoFuncional;
        this.idPessoa = idPessoa;
    }

    public Funcionario(Integer id, String codigoFuncional, Pessoa idPessoa) {
        super.setId(id);
        this.codigoFuncional = codigoFuncional;
        this.idPessoa = idPessoa;
    }

    public String getCodigoFuncional() {
        return codigoFuncional;
    }

    public void setCodigoFuncional(String codigoFuncional) {
        this.codigoFuncional = codigoFuncional;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id='" + this.getId() + "\'" +
                "codigoFuncional='" + codigoFuncional + '\'' +
                ", idPessoa=" + idPessoa +
                '}';
    }
}
