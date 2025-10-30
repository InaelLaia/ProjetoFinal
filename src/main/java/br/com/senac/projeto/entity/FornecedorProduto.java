package br.com.senac.projeto.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FORNECEDORPRODUTO")
public class FornecedorProduto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "FORNECEDORID", nullable = false)
    private Integer fornecedorId;
    
    @Column(name = "PRODUTOID", nullable = false)
    private Integer produtoId;

    public FornecedorProduto() {}
    
    public FornecedorProduto(Integer fornecedorId, Integer produtoId) {
        this.fornecedorId = fornecedorId;
        this.produtoId = produtoId;
    }

    public Integer getId() { 
        return id; 
    }
    
    public void setId(Integer id) { 
        this.id = id; 
    }
    
    public Integer getFornecedorId() { 
        return fornecedorId; 
    }
    
    public void setFornecedorId(Integer fornecedorId) { 
        this.fornecedorId = fornecedorId; 
    }
    
    public Integer getProdutoId() { 
        return produtoId; 
    }
    
    public void setProdutoId(Integer produtoId) { 
        this.produtoId = produtoId; 
    }
}
