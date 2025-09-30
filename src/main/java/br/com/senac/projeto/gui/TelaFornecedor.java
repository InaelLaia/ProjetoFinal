package br.com.senac.projeto.gui;

import br.com.senac.projeto.persistencia.FornecedorDao;
import br.com.senac.projeto.persistencia.FornecedorProduto;
import br.com.senac.projeto.persistencia.FornecedorProdutoDao;
import br.com.senac.projeto.persistencia.FornecedorProdutosViewDao;
import br.com.senac.projeto.persistencia.ProdutoDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaFornecedor extends javax.swing.JFrame {

    private void preencherBoxFornecedor() {
        FornecedorDao fornecedorDao = new FornecedorDao();
        List<String> fornecedor = fornecedorDao.listarNomes();

        for (String nome : fornecedor) {
            boxFornecedor.addItem(nome);
        }
        boxFornecedor.setSelectedIndex(-1);
        boxFornecedor.setSelectedItem(null);
    }
    
    private void preencherBoxProduto() {
        ProdutoDao produtoDAO = new ProdutoDao();
        List<String> produtos = produtoDAO.listarNomes();
        
        boxProduto.removeAllItems();
        
        for (String nome : produtos){
            if (!ProdutoNaTabela(nome)){
                boxProduto.addItem(nome);
            }
        }
    }
    
    private boolean ProdutoNaTabela(String nome){
        
        DefaultTableModel model = (DefaultTableModel) tblProdutos.getModel();
        
        for (int i = 0; i < model.getRowCount(); i++){
            String nomeNaTabela = (String) model.getValueAt(i, 0);
            if (nomeNaTabela.equals(nome)){
                return true;
            }
        }
        return false;  
    }
    
    public void preencheTabela() {
        try {
            String itemSelecionado = (String) boxFornecedor.getSelectedItem();
            FornecedorProdutosViewDao produtoDAO = new FornecedorProdutosViewDao();
            List<Object[]> produtos = produtoDAO.listarDadosProdutosPorFornecedor(itemSelecionado);

            String columns[] = {"Nome", "Marca", "Preço"};
            String dados[][] = new String[produtos.size()][columns.length];

            int i = 0;
            for (Object[] d : produtos) {
                dados[i] = new String[]{
                    (String) d[0],
                    (String) d[1],
                    String.valueOf(d[2])
                };
                i++;
            }
            
            DefaultTableModel model = new DefaultTableModel(dados, columns);
            tblProdutos.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public TelaFornecedor() {
        initComponents();
        preencherBoxFornecedor();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        boxFornecedor = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        boxProduto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Gerenciar Fornecedores");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        boxFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        boxFornecedor.setSelectedIndex(-1);
        boxFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxFornecedorActionPerformed(evt);
            }
        });

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Marca", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProdutos);

        btnAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdicionar.setText("ADICIONAR");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        boxProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        boxProduto.setSelectedIndex(-1);
        boxProduto.setEnabled(false);
        boxProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(85, 85, 85))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVoltar)
                        .addGap(29, 29, 29)
                        .addComponent(btnExcluir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(419, 419, 419)
                        .addComponent(boxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(boxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(10, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdicionar)
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(boxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(boxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdicionar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(btnExcluir))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        
        FornecedorProduto relacao = new FornecedorProduto();
        FornecedorProdutoDao dao = new FornecedorProdutoDao();
        
        try{
            
            FornecedorDao fornecedor = new FornecedorDao();
            String nomeFornecedor = (String) boxFornecedor.getSelectedItem();
            int idFornecedor = fornecedor.obterIdPorNome(nomeFornecedor);
            
            ProdutoDao produto = new ProdutoDao();
            String nomeProduto = (String) boxProduto.getSelectedItem();
            int idProduto = produto.obterIdPorNome(nomeProduto);
            
            relacao.setFornecedorId(idFornecedor);
            relacao.setProdutoId(idProduto);
            
            dao.save(relacao);
            
            JOptionPane.showMessageDialog(this, "Cadastro Realizado com sucesso!");
            
            boxFornecedorActionPerformed(evt);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Não foi possível adicionar o produto!" + e.getMessage());
        }           
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        TelaInicial inicio = new TelaInicial();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try{
            if(tblProdutos.getSelectedRow() >= 0 ){
                String nome = (String)tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0);
                
                int resposta = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir esse produto " + nome + "?");
                
                if(resposta == 0){
                    
                    FornecedorDao fornecedor = new FornecedorDao();
                    String nomeFornecedor = (String) boxFornecedor.getSelectedItem();
                    int idFornecedor = fornecedor.obterIdPorNome(nomeFornecedor);
                    
                    ProdutoDao produto = new ProdutoDao();
                    int linha = tblProdutos.getSelectedRow();
                    int idProduto = -1;
                    if (linha != -1){
                        String nomeProduto = (String) tblProdutos.getValueAt(linha, 0);
                        idProduto = produto.obterIdPorNome(nomeProduto);
                    }
                    
                    FornecedorProdutoDao produtoDao = new FornecedorProdutoDao();
                    produtoDao.excluirPorFornecedorEProduto(idFornecedor, idProduto);
                    JOptionPane. showMessageDialog(this, "Registro excluído com sucesso");
                    boxFornecedorActionPerformed(evt);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Não foi possível excluir o item selecionado:\n" + e.getMessage());
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void boxFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxFornecedorActionPerformed
        if (boxFornecedor.getSelectedItem() != null) {
            boxProduto.setEnabled(true);
            preencheTabela();
            preencherBoxProduto();
        }
    }//GEN-LAST:event_boxFornecedorActionPerformed

    private void boxProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxProdutoActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaFornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxFornecedor;
    private javax.swing.JComboBox<String> boxProduto;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutos;
    // End of variables declaration//GEN-END:variables
}
