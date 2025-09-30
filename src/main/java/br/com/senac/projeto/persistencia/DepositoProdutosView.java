package br.com.senac.projeto.persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DepositoProdutosView {
    
    @Id
    private Integer id;
    private String nomeDeposito;
    private String nomeProduto;
    private Integer pontoReposicao;
    private Integer quantidadeAtual;

    public DepositoProdutosView() {
    }

    // Apenas getters para views
    public Integer getId() {
        return id;
    }

    public String getNomeDeposito() {
        return nomeDeposito;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Integer getPontoReposicao() {
        return pontoReposicao;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }
}
