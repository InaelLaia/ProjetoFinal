package br.com.senac.projeto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "MOVIMENTACAO")
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "Depósito é obrigatório")
    @Column(name = "DEPOSITOID", nullable = false)
    private Integer depositoId;
    
    @NotNull(message = "Produto é obrigatório")
    @Column(name = "PRODUTOID", nullable = false)
    private Integer produtoId;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;
    
    @NotNull(message = "Tipo é obrigatório")
    @Column(name = "TIPO", nullable = false)
    private Integer tipo;

    public Movimentacao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(Integer depositoId) {
        this.depositoId = depositoId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
}
