package controls.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import card.MonsterCard;
import card.SpellCard;
import card.TrapCard;

public class DbControls {
    public static void addMonsterCard(MonsterCard monsterCard) {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO `album`(`name`, `set`, `edition`, `language`,"
                + "`cardType`, `rarity`, `type`,`summMethod`, `attribute`, `level`,"
                + " `atk`, `def` ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            try {
                preparedStatement = MyConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, monsterCard.getName());
                preparedStatement.setString(2, monsterCard.getSet());
                preparedStatement.setString(3, monsterCard.getEdition());
                preparedStatement.setString(4, monsterCard.getLanguage());
                preparedStatement.setString(5, "Monster card");
                preparedStatement.setString(6, monsterCard.getRarity());
                preparedStatement.setString(7, monsterCard.getType());
                preparedStatement.setString(8, monsterCard.getSummMethod());
                preparedStatement.setString(9, monsterCard.getAtribute());
                preparedStatement.setString(10, monsterCard.getLevel());
                preparedStatement.setString(11, monsterCard.getAtk());
                preparedStatement.setString(12, monsterCard.getDef());
                
                if (preparedStatement.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Card saved.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    }
    
    public static void editMonsterCard(MonsterCard monsterCard, String cell) {
        PreparedStatement preparedStatement;
        String query = "UPDATE `album` SET `name` ='" + monsterCard.getName() + "'"
                + " , `rarity` ='" + monsterCard.getRarity() + "'"
                + " , `edition` ='" + monsterCard.getEdition() + "'"
                + " , `set`='" + monsterCard.getSet() + "'"
                + " , `language`='" + monsterCard.getLanguage() + "'"
                + " , `type`='" + monsterCard.getType() + "'"
                + " , `summMethod`='" + monsterCard.getSummMethod() + "'"
                + " , `attribute`='" + monsterCard.getAtribute() + "'"
                + " , `level`='" + monsterCard.getLevel() + "'"
                + " , `atk`='" + monsterCard.getAtk() + "'"
                + " , `def`='" + monsterCard.getDef() + "'"
                + " WHERE `id`=" + cell;
        
        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Change saved.");     
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }  
    }

    public static void addTrapCard(TrapCard trapCard) {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO `album`(`name`, `rarity`, `edition`, `set`,"
                    + " `language`, `type`, `cardType`) VALUES (?,?,?,?,?,?,?)";

            try {
                preparedStatement = MyConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, trapCard.getName());
                preparedStatement.setString(2, trapCard.getRarity());
                preparedStatement.setString(3, trapCard.getEdition());
                preparedStatement.setString(4, trapCard.getSet());
                preparedStatement.setString(5, trapCard.getLanguage());
                preparedStatement.setString(6, trapCard.getType());
                preparedStatement.setString(7, "Spell card");

                if (preparedStatement.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Card saved.");
                }

            } catch (SQLException ex) {
                Logger.getLogger(DbControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    }
    
    public static void editTrapCard(TrapCard trapCard, String cell) {
        PreparedStatement preparedStatement;
        String query = "UPDATE `album` SET `name` ='" + trapCard.getName() + "'"
                + ", `rarity` ='" + trapCard.getRarity() + "'"
                + ", `edition` ='" + trapCard.getEdition()+"'"
                + ", `set`='" + trapCard.getSet() + "' "
                + ", `language`='" + trapCard.getLanguage() + "'"
                + ", `type`='" + trapCard.getType() + "'"
                + " WHERE `id`=" + cell;
        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Change saved.");           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static void addSpellCard(SpellCard spellCard) {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO `album`(`name`, `rarity`, `edition`, `set`,"
                    + " `language`, `type`, `cardType`) VALUES (?,?,?,?,?,?,?)";

            try {
                preparedStatement = MyConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, spellCard.getName());
                preparedStatement.setString(2, spellCard.getRarity());
                preparedStatement.setString(3, spellCard.getEdition());
                preparedStatement.setString(4, spellCard.getSet());
                preparedStatement.setString(5, spellCard.getLanguage());
                preparedStatement.setString(6, spellCard.getType());
                preparedStatement.setString(7, "Spell card");

                if (preparedStatement.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Card saved.");
                }

            } catch (SQLException ex) {
                Logger.getLogger(DbControls.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    }
    
    public static void editSpellCard(SpellCard spellCard, String cell) {
        PreparedStatement preparedStatement;
        String query = "UPDATE `album` SET `name` ='" + spellCard.getName() + "'"
                + ", `rarity` ='" + spellCard.getRarity() + "'"
                + ", `edition` ='" + spellCard.getEdition()+"'"
                + ", `set`='" + spellCard.getSet() + "' "
                + ", `language`='" + spellCard.getLanguage() + "'"
                + ", `type`='" + spellCard.getType() + "'"
                + " WHERE `id`=" + cell;
        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Change saved.");           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    /* Method deleteCard will delete data for selected card by ID of the card */
    public static void deleteCard(String cell) {
        PreparedStatement preparedStatement;
        String query = "DELETE FROM `album` WHERE `id` = " + cell; // cell represents table block where card ID is found
        
        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Card was removed.");            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
