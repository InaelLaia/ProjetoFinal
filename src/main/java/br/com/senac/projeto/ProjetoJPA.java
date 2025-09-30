/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.senac.projeto;

import br.com.senac.projeto.gui.TelaInicial;

/**
 *
 * @author inael
 */
public class ProjetoJPA {
    
   

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
              public void run() {
                  new TelaInicial().setVisible(true);
            }
        });
    }
}
