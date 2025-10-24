package br.com.senac.projeto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "DEPOSITO")
public class Deposito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "NOME", unique = true, nullable = false)
    private String nome;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Column(name = "TELEFONE", nullable = false)
    private String telefone;
    
    @NotBlank(message = "Rua é obrigatória")
    @Column(name = "RUA", nullable = false)
    private String rua;
    
    @NotBlank(message = "Número é obrigatório")
    @Column(name = "NUMERO", nullable = false)
    private String numero;
    
    @NotBlank(message = "Bairro é obrigatório")
    @Column(name = "BAIRRO", nullable = false)
    private String bairro;
    
    @NotBlank(message = "Cidade é obrigatória")
    @Column(name = "CIDADE", nullable = false)
    private String cidade;
    
    @NotBlank(message = "UF é obrigatório")
    @Column(name = "UF", nullable = false, length = 2)
    private String uf;

    public Deposito() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
