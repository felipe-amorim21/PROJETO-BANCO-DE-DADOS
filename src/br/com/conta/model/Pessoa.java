package br.com.conta.model;

public class Pessoa extends GenericModel{
    private String nome;
    private String cpf;
    private String cnpj;
    private TipoPessoa idTipoPessoa;

    public Pessoa(String nome, String cpf, String cnpj, TipoPessoa idTipoPessoa) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.idTipoPessoa = idTipoPessoa;
    }

    public Pessoa(Integer id,String nome, String cpf, String cnpj, TipoPessoa idTipoPessoa) {
        super.setId(id);
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.idTipoPessoa = idTipoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public TipoPessoa getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(TipoPessoa idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id='" + this.getId() + "\'" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", idTipoPessoa=" + idTipoPessoa +
                '}';
    }
}
