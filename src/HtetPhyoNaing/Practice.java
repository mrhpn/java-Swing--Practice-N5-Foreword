/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtetPhyoNaing;

import HtetPhyoNaing.Model.Vocabulary;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author GIC
 */
public class Practice extends javax.swing.JPanel {

    /**
     * @return the labelPracticeTotalRows
     */
    public javax.swing.JLabel getLabelPracticeTotalRows() {
        return labelPracticeTotalRows;
    }

    /**
     * @param labelPracticeTotalRows the labelPracticeTotalRows to set
     */
    public void setLabelPracticeTotalRows(javax.swing.JLabel labelPracticeTotalRows) {
        this.labelPracticeTotalRows = labelPracticeTotalRows;
    }

    /**
     * @return the tablePracticeVoca
     */
    public javax.swing.JTable getTablePracticeVoca() {
        return tablePracticeVoca;
    }

    /**
     * @param tablePracticeVoca the tablePracticeVoca to set
     */
    public void setTablePracticeVoca(javax.swing.JTable tablePracticeVoca) {
        this.tablePracticeVoca = tablePracticeVoca;
    }

    /**
     * @return the cboxPracticeFromWantToSee
     */
    public javax.swing.JComboBox<String> getCboxPracticeFromWantToSee() {
        return cboxPracticeFromWantToSee;
    }

    /**
     * @param cboxPracticeFromWantToSee the cboxPracticeFromWantToSee to set
     */
    public void setCboxPracticeFromWantToSee(javax.swing.JComboBox<String> cboxPracticeFromWantToSee) {
        this.cboxPracticeFromWantToSee = cboxPracticeFromWantToSee;
    }

    /**
     * @return the labelPracticeIWantToSee
     */
    public javax.swing.JLabel getLabelPracticeIWantToSee() {
        return labelPracticeIWantToSee;
    }

    /**
     * @param labelPracticeIWantToSee the labelPracticeIWantToSee to set
     */
    public void setLabelPracticeIWantToSee(javax.swing.JLabel labelPracticeIWantToSee) {
        this.labelPracticeIWantToSee = labelPracticeIWantToSee;
    }

    /**
     * @return the btnPracticeVoca
     */
    public javax.swing.JButton getBtnPracticeVoca() {
        return btnPracticeVoca;
    }

    /**
     * @param btnPracticeVoca the btnPracticeVoca to set
     */
    public void setBtnPracticeVoca(javax.swing.JButton btnPracticeVoca) {
        this.btnPracticeVoca = btnPracticeVoca;
    }

    /**
     * @return the cboxPracticeChooseAllOrFav
     */
    public javax.swing.JComboBox<String> getCboxPracticeChooseAllOrFav() {
        return cboxPracticeChooseAllOrFav;
    }

    /**
     * @param cboxPracticeChooseAllOrFav the cboxPracticeChooseAllOrFav to set
     */
    public void setCboxPracticeChooseAllOrFav(javax.swing.JComboBox<String> cboxPracticeChooseAllOrFav) {
        this.cboxPracticeChooseAllOrFav = cboxPracticeChooseAllOrFav;
    }

    /**
     * @return the cboxPracticeFromLesson
     */
    public javax.swing.JComboBox<String> getCboxPracticeFromLesson() {
        return cboxPracticeFromLesson;
    }

    /**
     * @param cboxPracticeFromLesson the cboxPracticeFromLesson to set
     */
    public void setCboxPracticeFromLesson(javax.swing.JComboBox<String> cboxPracticeFromLesson) {
        this.cboxPracticeFromLesson = cboxPracticeFromLesson;
    }

    /**
     * @return the cboxPracticeLessonTo
     */
    public javax.swing.JComboBox<String> getCboxPracticeLessonTo() {
        return cboxPracticeLessonTo;
    }

    /**
     * @param cboxPracticeLessonTo the cboxPracticeLessonTo to set
     */
    public void setCboxPracticeLessonTo(javax.swing.JComboBox<String> cboxPracticeLessonTo) {
        this.cboxPracticeLessonTo = cboxPracticeLessonTo;
    }

    /**
     * @return the labelPracticeChooseTypeOfPractice
     */
    public javax.swing.JLabel getLabelPracticeChooseTypeOfPractice() {
        return labelPracticeChooseTypeOfPractice;
    }

    /**
     * @param labelPracticeChooseTypeOfPractice the labelPracticeChooseTypeOfPractice to set
     */
    public void setLabelPracticeChooseTypeOfPractice(javax.swing.JLabel labelPracticeChooseTypeOfPractice) {
        this.labelPracticeChooseTypeOfPractice = labelPracticeChooseTypeOfPractice;
    }

    /**
     * @return the labelPracticeChooseTypeOfPracticeFrom
     */
    public javax.swing.JLabel getLabelPracticeChooseTypeOfPracticeFrom() {
        return labelPracticeChooseTypeOfPracticeFrom;
    }

    /**
     * @param labelPracticeChooseTypeOfPracticeFrom the labelPracticeChooseTypeOfPracticeFrom to set
     */
    public void setLabelPracticeChooseTypeOfPracticeFrom(javax.swing.JLabel labelPracticeChooseTypeOfPracticeFrom) {
        this.labelPracticeChooseTypeOfPracticeFrom = labelPracticeChooseTypeOfPracticeFrom;
    }

    /**
     * @return the labelPracticeChooseTypeOfPracticeInfoMsg
     */
    public javax.swing.JLabel getLabelPracticeChooseTypeOfPracticeInfoMsg() {
        return labelPracticeChooseTypeOfPracticeInfoMsg;
    }

    /**
     * @param labelPracticeChooseTypeOfPracticeInfoMsg the labelPracticeChooseTypeOfPracticeInfoMsg to set
     */
    public void setLabelPracticeChooseTypeOfPracticeInfoMsg(javax.swing.JLabel labelPracticeChooseTypeOfPracticeInfoMsg) {
        this.labelPracticeChooseTypeOfPracticeInfoMsg = labelPracticeChooseTypeOfPracticeInfoMsg;
    }

    /**
     * @return the labelPracticeChooseTypeOfPracticeTo
     */
    public javax.swing.JLabel getLabelPracticeChooseTypeOfPracticeTo() {
        return labelPracticeChooseTypeOfPracticeTo;
    }

    /**
     * @param labelPracticeChooseTypeOfPracticeTo the labelPracticeChooseTypeOfPracticeTo to set
     */
    public void setLabelPracticeChooseTypeOfPracticeTo(javax.swing.JLabel labelPracticeChooseTypeOfPracticeTo) {
        this.labelPracticeChooseTypeOfPracticeTo = labelPracticeChooseTypeOfPracticeTo;
    }
    
    private Font jpFont;
    private Font myanmarFont;
    private DefaultTableCellRenderer tableCellRenderer;

    /**
     * Creates new form Practice
     */
    public Practice() {
        initComponents();
        this.setSize(820, 658);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboxPracticeChooseAllOrFav = new javax.swing.JComboBox<>();
        labelPracticeChooseTypeOfPractice = new javax.swing.JLabel();
        labelPracticeChooseTypeOfPracticeFrom = new javax.swing.JLabel();
        cboxPracticeFromLesson = new javax.swing.JComboBox<>();
        cboxPracticeLessonTo = new javax.swing.JComboBox<>();
        labelPracticeChooseTypeOfPracticeTo = new javax.swing.JLabel();
        labelPracticeChooseTypeOfPracticeInfoMsg = new javax.swing.JLabel();
        btnPracticeVoca = new javax.swing.JButton();
        cboxPracticeFromWantToSee = new javax.swing.JComboBox<>();
        labelPracticeIWantToSee = new javax.swing.JLabel();
        labelPracticeTotalRows = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePracticeVoca = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(820, 658));

        cboxPracticeChooseAllOrFav.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxPracticeChooseAllOrFav.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Vocabularies", "Only Favorites" }));
        cboxPracticeChooseAllOrFav.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboxPracticeChooseAllOrFav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxPracticeChooseAllOrFavActionPerformed(evt);
            }
        });

        labelPracticeChooseTypeOfPractice.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelPracticeChooseTypeOfPractice.setText("Choose type of practice");

        labelPracticeChooseTypeOfPracticeFrom.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelPracticeChooseTypeOfPracticeFrom.setText("From");

        cboxPracticeFromLesson.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxPracticeFromLesson.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Lesson 16", "Lesson 17", "Lesson 18", "Lesson 19", "Lesson 20", "Lesson 21", "Lesson 22", "Lesson 23", "Lesson 24", "Lesson 25" }));
        cboxPracticeFromLesson.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboxPracticeFromLesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxPracticeFromLessonActionPerformed(evt);
            }
        });

        cboxPracticeLessonTo.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxPracticeLessonTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Lesson 16", "Lesson 17", "Lesson 18", "Lesson 19", "Lesson 20", "Lesson 21", "Lesson 22", "Lesson 23", "Lesson 24", "Lesson 25" }));
        cboxPracticeLessonTo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboxPracticeLessonTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxPracticeLessonToActionPerformed(evt);
            }
        });

        labelPracticeChooseTypeOfPracticeTo.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelPracticeChooseTypeOfPracticeTo.setText("To");

        labelPracticeChooseTypeOfPracticeInfoMsg.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelPracticeChooseTypeOfPracticeInfoMsg.setText("If you choose same lesson for both range, you will be practicing only for that lesson.");

        btnPracticeVoca.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        btnPracticeVoca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/img-btn-go.png"))); // NOI18N
        btnPracticeVoca.setText("Start");
        btnPracticeVoca.setBorder(null);
        btnPracticeVoca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPracticeVoca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPracticeVocaActionPerformed(evt);
            }
        });

        cboxPracticeFromWantToSee.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxPracticeFromWantToSee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Myanmar Meaning", "Japanese Word" }));
        cboxPracticeFromWantToSee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboxPracticeFromWantToSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxPracticeFromWantToSeeActionPerformed(evt);
            }
        });

        labelPracticeIWantToSee.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelPracticeIWantToSee.setText("I want to see");

        labelPracticeTotalRows.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelPracticeTotalRows.setText("Total Rows: 0");

        tablePracticeVoca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablePracticeVoca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablePracticeVoca.setAlignmentY(0.3F);
        tablePracticeVoca.setGridColor(new java.awt.Color(204, 204, 204));
        tablePracticeVoca.setRowHeight(27);
        tablePracticeVoca.setSelectionBackground(new java.awt.Color(237, 226, 240));
        tablePracticeVoca.setSelectionForeground(new java.awt.Color(94, 0, 126));
        tablePracticeVoca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePracticeVocaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePracticeVoca);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPracticeChooseTypeOfPracticeInfoMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxPracticeFromLesson, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPracticeChooseTypeOfPracticeFrom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxPracticeLessonTo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPracticeChooseTypeOfPracticeTo))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboxPracticeFromWantToSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPracticeVoca, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelPracticeIWantToSee)))
                    .addComponent(labelPracticeChooseTypeOfPractice, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxPracticeChooseAllOrFav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPracticeTotalRows, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(labelPracticeChooseTypeOfPractice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxPracticeChooseAllOrFav, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelPracticeChooseTypeOfPracticeFrom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxPracticeFromLesson, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelPracticeChooseTypeOfPracticeTo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxPracticeLessonTo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelPracticeIWantToSee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxPracticeFromWantToSee, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPracticeVoca, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addComponent(labelPracticeChooseTypeOfPracticeInfoMsg)
                .addGap(27, 27, 27)
                .addComponent(labelPracticeTotalRows)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboxPracticeChooseAllOrFavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxPracticeChooseAllOrFavActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxPracticeChooseAllOrFavActionPerformed

    private void cboxPracticeFromLessonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxPracticeFromLessonActionPerformed
//        int from = cboxPracticeFromLesson.getSelectedIndex(); // 0 - Select(return)
//        if (from == 0) btnPracticeVoca.setEnabled(false);
    }//GEN-LAST:event_cboxPracticeFromLessonActionPerformed

    private void cboxPracticeLessonToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxPracticeLessonToActionPerformed
//       int to = cboxPracticeLessonTo.getSelectedIndex(); // 0 - Select(return)
//       if (to == 0) btnPracticeVoca.setEnabled(false);
    }//GEN-LAST:event_cboxPracticeLessonToActionPerformed

    private void btnPracticeVocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPracticeVocaActionPerformed
        int practiceType = cboxPracticeChooseAllOrFav.getSelectedIndex(); // 0 - All, 1 - Fav
        int from = cboxPracticeFromLesson.getSelectedIndex() + 1;
        int to = cboxPracticeLessonTo.getSelectedIndex() + 1;
        int seen = cboxPracticeFromWantToSee.getSelectedIndex(); // 0 - myanmar, 1 - japanese
        
        tablePracticeVoca.setVisible(true);
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.generate(from, to, tablePracticeVoca, labelPracticeTotalRows);
        setUpPracticeTable();   
        
        if (seen == 0) {
            
        }
    }//GEN-LAST:event_btnPracticeVocaActionPerformed

    private void cboxPracticeFromWantToSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxPracticeFromWantToSeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxPracticeFromWantToSeeActionPerformed

    private void tablePracticeVocaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePracticeVocaMouseClicked
        int row = tablePracticeVoca.rowAtPoint(evt.getPoint());
        int col = tablePracticeVoca.columnAtPoint(evt.getPoint());
        
        System.out.println(row);
        System.out.println(col);
    }//GEN-LAST:event_tablePracticeVocaMouseClicked

    private void setUpPracticeTable() {        
        try {
            jpFont = Font.createFont(
                    Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\MS Gothic.ttf")).deriveFont(20f);
            myanmarFont = Font.createFont(
                    Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\Pyidaungsu-1.8_regular.ttf")).deriveFont(15f);
            
            tableCellRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setFont(jpFont);
                    return this;
                }
            };
            
            // set fonts for table column except Jp character column
            tablePracticeVoca.setFont(myanmarFont);
//            tableFavoriteVocabularies.setFont(myanmarFont);
            
            // set font for table column which is Jp characters exist
            tablePracticeVoca.getColumnModel().getColumn(0).setCellRenderer(tableCellRenderer);
//            tableFavoriteVocabularies.getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
             
        } catch (IOException | FontFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPracticeVoca;
    private javax.swing.JComboBox<String> cboxPracticeChooseAllOrFav;
    private javax.swing.JComboBox<String> cboxPracticeFromLesson;
    private javax.swing.JComboBox<String> cboxPracticeFromWantToSee;
    private javax.swing.JComboBox<String> cboxPracticeLessonTo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelPracticeChooseTypeOfPractice;
    private javax.swing.JLabel labelPracticeChooseTypeOfPracticeFrom;
    private javax.swing.JLabel labelPracticeChooseTypeOfPracticeInfoMsg;
    private javax.swing.JLabel labelPracticeChooseTypeOfPracticeTo;
    private javax.swing.JLabel labelPracticeIWantToSee;
    private javax.swing.JLabel labelPracticeTotalRows;
    private javax.swing.JTable tablePracticeVoca;
    // End of variables declaration//GEN-END:variables
}
