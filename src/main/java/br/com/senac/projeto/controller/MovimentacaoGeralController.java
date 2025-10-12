package br.com.senac.projeto.controller;

import br.com.senac.projeto.persistencia.FornecedorDao;
import br.com.senac.projeto.persistencia.MovimentacaoGeralViewDao;
import br.com.senac.projeto.persistencia.MovimentacaoGeralView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MovimentacaoGeralController {

    private MovimentacaoGeralViewDao movimentacaoDao = new MovimentacaoGeralViewDao();

    @GetMapping("/")
    public String index(Model model) {
        List<MovimentacaoGeralView> movimentacoes = movimentacaoDao.listarTodas();
        model.addAttribute("movimentacoes", movimentacoes);
        return "index";
    }
}
