package br.com.conta.model;

public class Cliente extends GenericModel{
    private String numeroDocumento;
    private String numeroCliente;
    private Pessoa idPessoa;

    public Cliente(String numeroDocumento, String numeroCliente, Pessoa idPessoa) {
        this.numeroDocumento = numeroDocumento;
        this.numeroCliente = numeroCliente;
        this.idPessoa = idPessoa;
    }

    public Cliente(Integer id, String numeroDocumento, String numeroCliente, Pessoa idPessoa) {
        super.setId(id);
        this.numeroDocumento = numeroDocumento;
        this.numeroCliente = numeroCliente;
        this.idPessoa = idPessoa;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + this.getId() + "\'" +
                "numeroDocumento='" + numeroDocumento + '\'' +
                ", numeroCliente='" + numeroCliente + '\'' +
                ", idPessoa=" + idPessoa +
                '}';
    }
}
