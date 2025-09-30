package br.com.senac.projeto.gui;

import br.com.senac.projeto.persistencia.DepositoDao;
import br.com.senac.projeto.persistencia.DepositoProdutoDao;
import br.com.senac.projeto.persistencia.DepositoProdutosViewDao;
import br.com.senac.projeto.persistencia.ProdutoDao;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TelaDeposito extends javax.swing.JFrame {
    
    private void preencherBoxDeposito() {
        DepositoDao depositoDao = new DepositoDao();
        List<String> depositos = depositoDao.listarNomes();

        for (String nome : depositos) {
            boxDeposito.addItem(nome);
        }
        boxDeposito.setSelectedIndex(-1);
        boxDeposito.setSelectedItem(null);
    }
    
    public void preencheTabela() {
        try {
            String itemSelecionado = (String) boxDeposito.getSelectedItem();
            DepositoProdutosViewDao produtoDAO = new DepositoProdutosViewDao();
            List<Object[]> produtos = produtoDAO.listarDadosProdutosPorDeposito(itemSelecionado);

            String columns[] = {"Nome", "Ponto de Reposição", "Quantidade Atual"};
            String dados[][] = new String[produtos.size()][columns.length];

            int i = 0;
            for (Object[] d : produtos) {
                dados[i] = new String[]{
                    (String) d[0],
                    String.valueOf(d[1]),
                    String.valueOf(d[2])
                };
                i++;
            }
            
            DefaultTableModel model = new DefaultTableModel(dados, columns);
            tblProdutos.setModel(model);
            
            tblProdutos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                    Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    
                    int pontoReposicao = Integer.parseInt((String) table.getValueAt(row, 1));
                    int quantidadeAtual = Integer.parseInt((String) table.getValueAt(row, 2));
                
                
                    if (pontoReposicao >= quantidadeAtual){
                        component.setForeground(Color.RED);
                    } 
                    else{
                        component.setForeground(Color.BLACK);
                    }
                    return component;
                }
            });                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public TelaDeposito() {
        initComponents();
        preencherBoxDeposito();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        boxDeposito = new javax.swing.JComboBox<>();
        btnNovo = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Gerenciar Depositos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        boxDeposito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        boxDeposito.setSelectedIndex(-1);
        boxDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxDepositoActionPerformed(evt);
            }
        });

        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
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

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Ponto de Reposição", "Quantidade Atual"
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(88, 88, 88))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addGap(18, 18, 18)
                        .addComponent(btnVoltar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(18, 18, 18)
                        .addComponent(boxDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(177, 177, 177))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnVoltar)
                    .addComponent(btnExcluir))
                .addGap(27, 27, 27))
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

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        TelaInicial inicio = new TelaInicial();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try{
            if(tblProdutos.getSelectedRow() >= 0 ){
                String nome = (String)tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0);
                
                String qtdAtualStr = (String) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 2);
                
                int qtdAtual = Integer.parseInt(qtdAtualStr);
                
                if (qtdAtual == 0){
                    
                    int resposta = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir esse produto " + nome + "?");
                    
                    if(resposta == 0){
                        
                        DepositoDao deposito = new DepositoDao();
                        String nomeFornecedor = (String) boxDeposito.getSelectedItem();
                        int idDeposito = deposito.obterIdPorNome(nomeFornecedor);
                    
                        ProdutoDao produto = new ProdutoDao();
                        int linha = tblProdutos.getSelectedRow();
                        int idProduto = -1;
                        if (linha != -1){
                            String nomeProduto = (String) tblProdutos.getValueAt(linha, 0);
                            idProduto = produto.obterIdPorNome(nomeProduto);
                        }
                    
                        DepositoProdutoDao produtoDao = new DepositoProdutoDao();
                        produtoDao.excluirPorDepositoEProduto(idDeposito, idProduto);
                        JOptionPane. showMessageDialog(this, "Registro excluído com sucesso");
                        boxDepositoActionPerformed(evt);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Não é possível excluir um produto com quantidade maior que 0.");
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Não foi possível excluir o item selecionado:\n" + e.getMessage());
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        TelaAdicionarDepositos depositos = new TelaAdicionarDepositos();
        depositos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if(boxDeposito.getSelectedItem() != null ){
            DepositoDao deposito = new DepositoDao();
            String nomeFornecedor = (String) boxDeposito.getSelectedItem();
            int idDeposito = deposito.obterIdPorNome(nomeFornecedor);
            TelaAdicionarProdutos produtos = new TelaAdicionarProdutos(idDeposito);
            produtos.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "Selecione um depósito para poder adicionar um produto a ele");
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void boxDepositoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxDepositoActionPerformed
        if (boxDeposito.getSelectedItem() != null) {
            preencheTabela();
        }
    }//GEN-LAST:event_boxDepositoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDeposito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxDeposito;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutos;
    // End of variables declaration//GEN-END:variables
}
