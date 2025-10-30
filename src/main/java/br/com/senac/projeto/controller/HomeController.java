package br.com.senac.projeto.controller;

import br.com.senac.projeto.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private MovimentacaoService movimentacaoService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movimentacoes", movimentacaoService.listarTodasMovimentacoes());
        return "index";
    }
}
