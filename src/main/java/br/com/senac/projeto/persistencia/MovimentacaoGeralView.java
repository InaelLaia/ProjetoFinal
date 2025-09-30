package br.com.senac.projeto.persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MovimentacaoGeralView {
    
    @Id
    private Integer id;
    private String Produto;
    private String Deposito;
    private Double preco;
    private Integer quantidade;
    private String tipo;

    public MovimentacaoGeralView() {
    }

    // Apenas getters para views

    public Integer getId() {
        return id;
    }

    public String getProduto() {
        return Produto;
    }

    public String getDeposito() {
        return Deposito;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getTipo() {
        return tipo;
    }
    
}