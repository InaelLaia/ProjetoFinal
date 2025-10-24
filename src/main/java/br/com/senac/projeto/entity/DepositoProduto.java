package br.com.senac.projeto.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DEPOSITOPRODUTO")
public class DepositoProduto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "DEPOSITOID", nullable = false)
    private Integer depositoId;
    
    @Column(name = "PRODUTOID", nullable = false)
    private Integer produtoId;
    
    @Column(name = "PONTOREPOSICAO", nullable = false)
    private Integer pontoReposicao;
    
    @Column(name = "QUANTIDADEATUAL", nullable = false)
    private Integer quantidadeAtual;

    public DepositoProduto() {}
    
    public DepositoProduto(Integer depositoId, Integer produtoId, Integer pontoReposicao, Integer quantidadeAtual) {
        this.depositoId = depositoId;
        this.produtoId = produtoId;
        this.pontoReposicao = pontoReposicao;
        this.quantidadeAtual = quantidadeAtual;
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
    
    public Integer getPontoReposicao() { 
        return pontoReposicao; 
    }
    
    public void setPontoReposicao(Integer pontoReposicao) { 
        this.pontoReposicao = pontoReposicao; 
    }
    
    public Integer getQuantidadeAtual() { 
        return quantidadeAtual; 
    }
    
    public void setQuantidadeAtual(Integer quantidadeAtual) { 
        this.quantidadeAtual = quantidadeAtual; 
    }
}