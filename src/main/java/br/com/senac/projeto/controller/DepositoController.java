package br.com.senac.projeto.controller;

import br.com.senac.projeto.persistencia.Deposito;
import br.com.senac.projeto.persistencia.DepositoDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepositoController {
    
    private DepositoDao depositoDao = new DepositoDao();
    
    @GetMapping("/deposito")
    public String deposito(Model model) {
        model.addAttribute("depositos", depositoDao.listarNomes());
        return "deposito";
    }
    
    @GetMapping("/adicionar-deposito")
    public String adicionarDeposito() {
        return "adicionar-deposito";
    }
    
    @PostMapping("/adicionar-deposito")
    public String salvarDeposito(
            @RequestParam("nome") String nome,
            @RequestParam("telefone") String telefone,
            @RequestParam("rua") String rua,
            @RequestParam("numero") String numero,
            @RequestParam("bairro") String bairro,
            @RequestParam("cidade") String cidade,
            @RequestParam("uf") String uf) {
        
        try {
            Deposito deposito = new Deposito();
            deposito.setNome(nome);
            deposito.setTelefone(telefone);
            deposito.setRua(rua);
            deposito.setNumero(numero);
            deposito.setBairro(bairro);
            deposito.setCidade(cidade);
            deposito.setUf(uf);
            
            depositoDao.save(deposito);
            
        } catch (Exception e) {
            System.err.println("Erro ao salvar depósito: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "redirect:/deposito";
    }
}