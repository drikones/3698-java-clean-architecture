package br.com.alura.codechella.domain;

/**
 * Classe (Value Object) que representa um endereço. O value object é um objeto
 * que não possui identidade, ou seja, é imutável e comparável apenas por seus
 * atributos.
 */
public class Endereco {
    private String cep;
    private Integer numero;
    private String complemento;

    public Endereco(String cep, Integer numero, String complemento) {
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }


}
