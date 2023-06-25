package br.com.conta.model;

public class Poste extends GenericModel{
    private String codigo;
    private String latitude;
    private String longitude;
    private String observacao;

    public Poste(String codigo) {
        this.codigo = codigo;
    }


    public Poste(String codigo, String latitude, String longitude, String observacao) {
        this.codigo = codigo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.observacao = observacao;
    }

    public Poste(Integer id,String codigo, String latitude, String longitude, String observacao) {
        super.setId(id);
        this.codigo = codigo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.observacao = observacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Poste{" +
                "id='" + this.getId() + "\'" +
                "codigo='" + codigo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
