package br.com.senac.projeto.persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FornecedorProdutosView {
    
    @Id
    private Integer id;
    private String nomeFornecedor;
    private String nomeProduto;
    private String marcaProduto;
    private Double precoProduto;

    public FornecedorProdutosView() {
    }

    // Apenas getters para views
    public Integer getId() {
        return id;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getMarcaProduto() {
        return marcaProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }
}