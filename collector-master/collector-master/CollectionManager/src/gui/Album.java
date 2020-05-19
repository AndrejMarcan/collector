/*
 * Copyright (c) ...
 */
package gui;

import card.Card;
import card.Editions;
import card.MonsterCard;
import card.Rarities;
import card.SpellCard;
import card.TrapCard;
import controls.CardCommands;
import controls.EnumPickers;
import controls.WebViewControl;
import controls.db.DbControls;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Album class connects user interface to back end functions and classes to display all cards
 * from database and allows user to to manage his collection or to find his cards on card market
 * website.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class Album extends javax.swing.JFrame {

    /**
     * Creates new form WholeCollection
     */
    public Album() {
        this.initComponents();
        this.setLocationRelativeTo(null);
        controls.db.AlbumCommands.loadAlbum();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlbum = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButtonCheckMarket = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpadateTable = new javax.swing.JButton();
        jButtonSaveChanges = new javax.swing.JButton();
        jButtonLoadInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(51, 51, 51));

        jTableAlbum.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableAlbum.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name", "Set", "Edition", "Rarity", "Language", "card Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAlbum.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTableAlbum);
        jTableAlbum.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 51));
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonCheckMarket.setBackground(new java.awt.Color(51, 51, 51));
        jButtonCheckMarket.setForeground(new java.awt.Color(255, 204, 0));
        jButtonCheckMarket.setText("Check on market");
        jButtonCheckMarket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckMarketActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(51, 51, 51));
        jButtonDelete.setForeground(new java.awt.Color(255, 51, 51));
        jButtonDelete.setText("DELETE");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonUpadateTable.setBackground(new java.awt.Color(51, 51, 51));
        jButtonUpadateTable.setForeground(new java.awt.Color(51, 255, 51));
        jButtonUpadateTable.setText("Update table");
        jButtonUpadateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpadateTableActionPerformed(evt);
            }
        });

        jButtonSaveChanges.setBackground(new java.awt.Color(51, 51, 51));
        jButtonSaveChanges.setForeground(new java.awt.Color(51, 255, 51));
        jButtonSaveChanges.setText("Save Changes");
        jButtonSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveChangesActionPerformed(evt);
            }
        });

        jButtonLoadInfo.setText("Load Full Info");
        jButtonLoadInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButtonCheckMarket)
                .addGap(214, 214, 214)
                .addComponent(jButtonLoadInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                .addComponent(jButtonDelete)
                .addGap(18, 18, 18)
                .addComponent(jButtonSaveChanges)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpadateTable)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButtonCheckMarket)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonUpadateTable)
                    .addComponent(jButtonSaveChanges)
                    .addComponent(jButtonLoadInfo))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /* Method jButtonActionPerformed set button to close album window */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /* Method jButtonDeleteActionPerformed set button to delete selected card from database */
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        int row = jTableAlbum.getSelectedRow();
        String cell = jTableAlbum.getModel().getValueAt(row,0).toString();
        
        controls.db.DbControls.deleteCard(cell);
    }//GEN-LAST:event_jButton_DeleteActionPerformed
    
    /* Method jButtonUpadateTableActionPerformed sets button to update date in table */
    private void jButtonUpadateTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpadateTableActionPerformed
        controls.db.AlbumCommands.loadAlbum();
    }//GEN-LAST:event_jButton_UpadateTableActionPerformed
    
    /* Method jButtonSaveChangesActionPerformed set button to save changes in table and database */
    private void jButtonSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SaveChangesActionPerformed
        int row = jTableAlbum.getSelectedRow();
        String cardType = jTableAlbum.getModel().getValueAt(row,5).toString();
        String cell = jTableAlbum.getModel().getValueAt(row,0).toString();
        
        if (cardType.equalsIgnoreCase("Monster card")) {
            String name = jTableAlbum.getModel().getValueAt(row,1).toString();
            String set = jTableAlbum.getModel().getValueAt(row,2).toString();
            String editionShort = jTableAlbum.getModel().getValueAt(row,3).toString();
            String language = jTableAlbum.getModel().getValueAt(row,4).toString();
            String rarityShort = jTableAlbum.getModel().getValueAt(row,6).toString();      
            String type = jTableAlbum.getModel().getValueAt(row,7).toString();
            String summMethod = jTableAlbum.getModel().getValueAt(row,8).toString();
            String attribute = jTableAlbum.getModel().getValueAt(row,9).toString();
            String level = jTableAlbum.getModel().getValueAt(row,10).toString();
            String atk = jTableAlbum.getModel().getValueAt(row,11).toString();
            String def = jTableAlbum.getModel().getValueAt(row,12).toString();
            
            Rarities rarity = EnumPickers.rarityPicker(rarityShort);	
            Editions edition = EnumPickers.editionPicker(editionShort);
            
            MonsterCard monsterCard = new MonsterCard(name, rarity, edition,set, language,
            					   type,summMethod, attribute, level,
            					   atk, def);
            DbControls.editMonsterCard(monsterCard, cell);
            
        } else if (cardType.equalsIgnoreCase("Spell card")) {
            String name = jTableAlbum.getModel().getValueAt(row,1).toString();
            String set = jTableAlbum.getModel().getValueAt(row,2).toString();
            String editionShort = jTableAlbum.getModel().getValueAt(row,3).toString();
            String language = jTableAlbum.getModel().getValueAt(row,4).toString();
            String rarityShort = jTableAlbum.getModel().getValueAt(row,6).toString();    
            String type = jTableAlbum.getModel().getValueAt(row,7).toString();
            
            Rarities rarity = EnumPickers.rarityPicker(rarityShort);	
            Editions edition = EnumPickers.editionPicker(editionShort);
            
            SpellCard spellCard = new SpellCard(name, rarity, edition, set, language, type);
            DbControls.editSpellCard(spellCard, cell);
            
        } else if (cardType.equalsIgnoreCase("Trap card")) {
            String name = jTableAlbum.getModel().getValueAt(row,1).toString();
            String set = jTableAlbum.getModel().getValueAt(row,2).toString();
            String editionShort = jTableAlbum.getModel().getValueAt(row,3).toString();
            String language = jTableAlbum.getModel().getValueAt(row,4).toString();
            String rarityShort = jTableAlbum.getModel().getValueAt(row,6).toString();     
            String type = jTableAlbum.getModel().getValueAt(row,7).toString();
            
            Rarities rarity = EnumPickers.rarityPicker(rarityShort);	
            Editions edition = EnumPickers.editionPicker(editionShort);
            
            TrapCard trapCard = new TrapCard(name, rarity, edition, set, language, type);        
            DbControls.editTrapCard(trapCard, cell);
        }       
    }//GEN-LAST:event_jButton_SaveChangesActionPerformed

    /* Method jButtonCheckMarketActionPerformed set button to open web page with selected card on card market */
    private void jButtonCheckMarketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CheckMarketActionPerformed
        int row = jTableAlbum.getSelectedRow();
        String cardName = jTableAlbum.getModel().getValueAt(row,1).toString();
        String set = jTableAlbum.getModel().getValueAt(row,2).toString();
        try {
            WebViewControl.openWebPage(cardName, set);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Album.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Album.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_CheckMarketActionPerformed

    /* Method jButtonLoadInfoActionPerformed set button to show card informations in new window */
    private void jButtonLoadInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadInfoActionPerformed
        int row = jTableAlbum.getSelectedRow();
        int cardID = Integer.valueOf(jTableAlbum.getModel().getValueAt(row, 0).toString());
        
        new MonsterDetails().setVisible(true);
    }//GEN-LAST:event_jButton_LoadInfoActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Album().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCheckMarket;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonLoadInfo;
    private javax.swing.JButton jButtonSaveChanges;
    private javax.swing.JButton jButtonUpadateTable;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableAlbum;
    // End of variables declaration//GEN-END:variables
}

