package br.com.senac.projeto.persistencia;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//Teste das Funções Básicas do BaseDAO
public class BaseDAOTest {

    private ProdutoDao produtoDao;
    private Produto produtoTeste;

    @Before
    public void setUp() {
        produtoDao = new ProdutoDao();
        produtoTeste = new Produto();
        produtoTeste.setNome("ProdutosTestes");
        produtoTeste.setMarca("MarcasTestes");
        produtoTeste.setPreco(100.0);
    }

    @After
    public void tearDown() {
        // Limpeza dos dados de teste se necessário
    }

    @Test
    public void testSave() {
        produtoDao.save(produtoTeste);
        assertNotNull("ID deve ser gerado após save", produtoTeste.getId());
        produtoDao.delete(produtoTeste.getId());
    }

    @Test
    public void testFindById() {
        produtoDao.save(produtoTeste);
        Integer id = produtoTeste.getId();

        Produto produtoRecuperado = produtoDao.findById(id).orElse(null);
        assertNotNull("Produto deve ser encontrado por ID", produtoRecuperado);
        assertEquals("Nomes devem ser iguais", "ProdutosTestes", produtoRecuperado.getNome());
        produtoDao.delete(produtoTeste.getId());
    }

    @Test
    public void testUpdate() {
        produtoDao.save(produtoTeste);
        produtoTeste.setPreco(150.0);
        produtoDao.update(produtoTeste);

        Produto produtoAtualizado = produtoDao.findById(produtoTeste.getId()).orElse(null);
        assertEquals("Preço deve ser atualizado", 150.0, produtoAtualizado.getPreco(), 0.001);
        produtoDao.delete(produtoTeste.getId());
    }

    @Test
    public void testFindAll() {
        int tamanhoInicial = produtoDao.findAll().size();
        produtoDao.save(produtoTeste);

        int tamanhoFinal = produtoDao.findAll().size();
        assertTrue("Lista deve conter mais elementos após inserção",
                tamanhoFinal > tamanhoInicial);
        produtoDao.delete(produtoTeste.getId());
    }

    @Test
    public void testDelete() {
        produtoDao.save(produtoTeste);
        Integer id = produtoTeste.getId();

        produtoDao.delete(id);
        assertFalse("Produto não deve existir após delete",
        produtoDao.findById(id).isPresent());
    }

}
