/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtetPhyoNaing;

import HtetPhyoNaing.Model.Vocabulary;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.Highlighter;

/**
 *
 * @author GIC
 */
public class App extends javax.swing.JFrame {
    private Point mouseDownCompCoords = null;
    private DefaultTableCellRenderer r;
    private DefaultTableCellRenderer header;
    private Font myanmarFont;

    /**
     * Creates new form App
     */
    public App() throws IOException, FontFormatException {
        initComponents();
        this.setShape(new RoundRectangle2D.Double(0, 0, 1000, 630, 20, 20));
        
        tableVocabularies.getColumnModel().getColumn(0).setMinWidth(0);
        tableVocabularies.getColumnModel().getColumn(0).setMaxWidth(0);
        tableVocabularies.getColumnModel().getColumn(0).setWidth(0);
        
        btnUpdateVocabulary.setVisible(false);
        btnDeleteVocabulary.setVisible(false);
                    
        ListSelectionModel listSelectionModel = tableVocabularies.getSelectionModel();
        listSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            if(!listSelectionModel.isSelectionEmpty()) {
                btnUpdateVocabulary.setVisible(true);
                btnDeleteVocabulary.setVisible(true);
            } else {
                btnUpdateVocabulary.setVisible(false);
                btnDeleteVocabulary.setVisible(false);
            }
        });
        tableVocabularies.getColumnModel().getColumn(0).setMinWidth(50);
        tableVocabularies.getColumnModel().getColumn(0).setMaxWidth(60);
   
        Font jpFont;
        Font customFont;
        try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\cerebrisans-regular.ttf")).deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            
            Font titleFont = customFont.deriveFont(18f);
            
            jpFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\MS Gothic.ttf")).deriveFont(17f);
           
            myanmarFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\Pyidaungsu-1.8_regular.ttf")).deriveFont(15f);
            
            tabbedPaneVocabulary.setFont(customFont);
            labelTotalRows.setFont(customFont);
            labelFilterByLessonInVocaList.setFont(customFont);
            cboxLessonList.setFont(customFont);
            labelSearchInAllLessonsInVocaList.setFont(customFont);
            cboxSearchBox.setFont(customFont);
            labelCreateUpdateVocabulary.setFont(titleFont);
            btnSearch.setFont(customFont);
            btnUpdateVocabulary.setFont(customFont);
            btnDeleteVocabulary.setFont(customFont);
            labelChooseLessonInVocaCreate.setFont(customFont);
            cboxLessonInCreateVocabulary.setFont(customFont);
            labelVocaInHiraganaKatakanaInVocaCreate.setFont(customFont);
            labelRomajiInVocaCreate.setFont(customFont);
            labelPronounciationInVocaCreate.setFont(customFont);
            labelMeaningInVocaCreate.setFont(customFont);
            btnGoVocabulary.setFont(customFont);
            txtVocabularyName.setFont(jpFont);
            labelId.setFont(customFont);
            labelVocabularyId.setFont(customFont);            
            msgCreateVocabulary.setFont(customFont);
            
            tableVocabularies.setFont(myanmarFont);
            
            r = new DefaultTableCellRenderer() {
                 Font mjpFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\MS Gothic.ttf")).deriveFont(17f);

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                            row, column);
                    setFont(mjpFont);
                    return this;
                }
            };
            
            tableVocabularies.getColumnModel().getColumn(1).setCellRenderer(r);
            
            txtSearchBox.setFont(jpFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnVocabulary = new javax.swing.JLabel();
        btnCloseWindow = new javax.swing.JLabel();
        btnMinimizeWindow = new javax.swing.JLabel();
        tabbedPaneVocabulary = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        txtSearchBox = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVocabularies = new javax.swing.JTable();
        cboxLessonList = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        cboxSearchBox = new javax.swing.JComboBox<>();
        labelTotalRows = new javax.swing.JLabel();
        btnClearSearchText = new javax.swing.JButton();
        btnDeleteVocabulary = new javax.swing.JButton();
        btnUpdateVocabulary = new javax.swing.JButton();
        labelFilterByLessonInVocaList = new javax.swing.JLabel();
        labelSearchInAllLessonsInVocaList = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        labelChooseLessonInVocaCreate = new javax.swing.JLabel();
        txtVocabularyName = new javax.swing.JTextField();
        labelVocaInHiraganaKatakanaInVocaCreate = new javax.swing.JLabel();
        labelRomajiInVocaCreate = new javax.swing.JLabel();
        labelMeaningInVocaCreate = new javax.swing.JLabel();
        btnGoVocabulary = new javax.swing.JButton();
        txtVocabularyRomaji = new javax.swing.JTextField();
        txtVocabularyJpMm = new javax.swing.JTextField();
        labelPronounciationInVocaCreate = new javax.swing.JLabel();
        cboxLessonInCreateVocabulary = new javax.swing.JComboBox<>();
        labelCreateUpdateVocabulary = new javax.swing.JLabel();
        msgCreateVocabulary = new javax.swing.JLabel();
        txtRequiredName = new javax.swing.JLabel();
        txtRequiredRomaji = new javax.swing.JLabel();
        txtRequiredPronounciation = new javax.swing.JLabel();
        txtVocabularyMeaning = new javax.swing.JTextField();
        txtRequiredMeaning = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        labelVocabularyId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Foreword");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jPanel2.setBackground(new java.awt.Color(94, 0, 126));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/win (1).png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 48, -1, -1));

        labelTitle.setBackground(new java.awt.Color(63, 63, 63));
        labelTitle.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 36)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle.setText("Foreword");
        jPanel2.add(labelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jPanel3.setBackground(new java.awt.Color(126, 63, 155));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(240, 52));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVocabulary.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        btnVocabulary.setForeground(new java.awt.Color(255, 255, 255));
        btnVocabulary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/listing.png"))); // NOI18N
        btnVocabulary.setText("Vocabulary");
        jPanel3.add(btnVocabulary, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 240, 52));

        btnCloseWindow.setForeground(new java.awt.Color(51, 51, 255));
        btnCloseWindow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/img-btn-close_1.png"))); // NOI18N
        btnCloseWindow.setToolTipText("close");
        btnCloseWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCloseWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseWindowMouseClicked(evt);
            }
        });

        btnMinimizeWindow.setForeground(new java.awt.Color(51, 51, 255));
        btnMinimizeWindow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/img-btn-minimize_1.png"))); // NOI18N
        btnMinimizeWindow.setToolTipText("minimize");
        btnMinimizeWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimizeWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeWindowMouseClicked(evt);
            }
        });

        tabbedPaneVocabulary.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPaneVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabbedPaneVocabulary.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        tabbedPaneVocabulary.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneVocabularyStateChanged(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtSearchBox.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBoxActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/img-btn-search.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.setMaximumSize(new java.awt.Dimension(101, 35));
        btnSearch.setMinimumSize(new java.awt.Dimension(101, 35));
        btnSearch.setPreferredSize(new java.awt.Dimension(101, 35));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tableVocabularies.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableVocabularies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Name", "Romaji", "Jp-Mm", "Meaning"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVocabularies.setAlignmentY(0.3F);
        tableVocabularies.setGridColor(new java.awt.Color(204, 204, 204));
        tableVocabularies.setRowHeight(27);
        tableVocabularies.setSelectionBackground(new java.awt.Color(237, 226, 240));
        tableVocabularies.setSelectionForeground(new java.awt.Color(94, 0, 126));
        jScrollPane1.setViewportView(tableVocabularies);
        if (tableVocabularies.getColumnModel().getColumnCount() > 0) {
            tableVocabularies.getColumnModel().getColumn(0).setHeaderValue("No");
            tableVocabularies.getColumnModel().getColumn(1).setHeaderValue("Name");
            tableVocabularies.getColumnModel().getColumn(2).setHeaderValue("Romaji");
            tableVocabularies.getColumnModel().getColumn(3).setHeaderValue("Jp-Mm");
            tableVocabularies.getColumnModel().getColumn(4).setHeaderValue("Meaning");
        }

        cboxLessonList.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxLessonList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Lesson 16", "Lesson 17", "Lesson 18", "Lesson 19", "Lesson 20", "Lesson 21", "Lesson 22", "Lesson 23", "Lesson 24", "Lesson 25" }));
        cboxLessonList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxLessonListActionPerformed(evt);
            }
        });
        cboxLessonList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboxLessonListPropertyChange(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cboxSearchBox.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxSearchBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Romaji", "Name" }));
        cboxSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxSearchBoxActionPerformed(evt);
            }
        });

        labelTotalRows.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        labelTotalRows.setText("jLabel1");

        btnClearSearchText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/img-btn-close.png"))); // NOI18N
        btnClearSearchText.setToolTipText("Clear search box text");
        btnClearSearchText.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClearSearchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSearchTextActionPerformed(evt);
            }
        });

        btnDeleteVocabulary.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        btnDeleteVocabulary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/img-btn-delete.png"))); // NOI18N
        btnDeleteVocabulary.setText("Delete");
        btnDeleteVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteVocabulary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteVocabularyActionPerformed(evt);
            }
        });

        btnUpdateVocabulary.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        btnUpdateVocabulary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/img-btn-update.png"))); // NOI18N
        btnUpdateVocabulary.setText("Edit");
        btnUpdateVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateVocabulary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateVocabularyActionPerformed(evt);
            }
        });

        labelFilterByLessonInVocaList.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelFilterByLessonInVocaList.setForeground(new java.awt.Color(94, 0, 126));
        labelFilterByLessonInVocaList.setText("Filter by Lesson");
        labelFilterByLessonInVocaList.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        labelSearchInAllLessonsInVocaList.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelSearchInAllLessonsInVocaList.setForeground(new java.awt.Color(94, 0, 126));
        labelSearchInAllLessonsInVocaList.setText("Search in All Lessons");
        labelSearchInAllLessonsInVocaList.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(527, 527, 527)
                            .addComponent(btnUpdateVocabulary)
                            .addGap(6, 6, 6)
                            .addComponent(btnDeleteVocabulary))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelTotalRows, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(220, 220, 220)
                            .addComponent(labelFilterByLessonInVocaList, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelSearchInAllLessonsInVocaList, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(218, 218, 218)
                            .addComponent(cboxLessonList, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cboxSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnClearSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, 78))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFilterByLessonInVocaList, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSearchInAllLessonsInVocaList))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxLessonList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboxSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelTotalRows, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateVocabulary)
                    .addComponent(btnDeleteVocabulary))
                .addContainerGap())
        );

        tabbedPaneVocabulary.addTab("List", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 658, Short.MAX_VALUE)
        );

        tabbedPaneVocabulary.addTab("Favorites", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        labelChooseLessonInVocaCreate.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelChooseLessonInVocaCreate.setText("Choose Lesson");

        txtVocabularyName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtVocabularyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyNameActionPerformed(evt);
            }
        });

        labelVocaInHiraganaKatakanaInVocaCreate.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelVocaInHiraganaKatakanaInVocaCreate.setText("Vocabulary in Hiragana/Katakana");

        labelRomajiInVocaCreate.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelRomajiInVocaCreate.setText("Romaji");

        labelMeaningInVocaCreate.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelMeaningInVocaCreate.setText("Meaning");

        btnGoVocabulary.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        btnGoVocabulary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/img-btn-go.png"))); // NOI18N
        btnGoVocabulary.setText("Go");
        btnGoVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoVocabulary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoVocabularyActionPerformed(evt);
            }
        });

        txtVocabularyRomaji.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtVocabularyRomaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyRomajiActionPerformed(evt);
            }
        });

        txtVocabularyJpMm.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtVocabularyJpMm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyJpMmActionPerformed(evt);
            }
        });

        labelPronounciationInVocaCreate.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelPronounciationInVocaCreate.setText("Pronounciation");

        cboxLessonInCreateVocabulary.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxLessonInCreateVocabulary.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Lesson 16", "Lesson 17", "Lesson 18", "Lesson 19", "Lesson 20", "Lesson 21", "Lesson 22", "Lesson 23", "Lesson 24", "Lesson 25" }));
        cboxLessonInCreateVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboxLessonInCreateVocabulary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxLessonInCreateVocabularyActionPerformed(evt);
            }
        });

        labelCreateUpdateVocabulary.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        labelCreateUpdateVocabulary.setText("Create/Update Vocabulary");

        msgCreateVocabulary.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        msgCreateVocabulary.setForeground(new java.awt.Color(0, 153, 102));

        txtRequiredName.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredName.setForeground(new java.awt.Color(255, 51, 51));

        txtRequiredRomaji.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredRomaji.setForeground(new java.awt.Color(255, 51, 51));

        txtRequiredPronounciation.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredPronounciation.setForeground(new java.awt.Color(255, 51, 51));

        txtVocabularyMeaning.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtVocabularyMeaning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyMeaningActionPerformed(evt);
            }
        });

        txtRequiredMeaning.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredMeaning.setForeground(new java.awt.Color(255, 51, 51));

        labelId.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        labelId.setText("Id:");

        labelVocabularyId.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        labelVocabularyId.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCreateUpdateVocabulary)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(msgCreateVocabulary, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnGoVocabulary))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtVocabularyJpMm, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelPronounciationInVocaCreate)
                                .addComponent(txtRequiredPronounciation, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelMeaningInVocaCreate)
                                .addComponent(txtVocabularyMeaning, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtRequiredMeaning, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cboxLessonInCreateVocabulary, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelChooseLessonInVocaCreate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelVocaInHiraganaKatakanaInVocaCreate)
                                .addComponent(txtVocabularyName, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtRequiredName, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelRomajiInVocaCreate)
                            .addComponent(txtVocabularyRomaji, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRequiredRomaji, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelVocabularyId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelCreateUpdateVocabulary)
                .addGap(18, 18, 18)
                .addComponent(labelChooseLessonInVocaCreate)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxLessonInCreateVocabulary, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(labelVocaInHiraganaKatakanaInVocaCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVocabularyName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRequiredName, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(labelRomajiInVocaCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVocabularyRomaji, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRequiredRomaji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPronounciationInVocaCreate)
                            .addComponent(labelMeaningInVocaCreate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtVocabularyMeaning, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(txtVocabularyJpMm))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRequiredPronounciation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRequiredMeaning, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGoVocabulary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(msgCreateVocabulary, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelVocabularyId, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(295, Short.MAX_VALUE))
        );

        tabbedPaneVocabulary.addTab("Vocabulary", jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPaneVocabulary)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMinimizeWindow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCloseWindow)
                        .addGap(80, 80, 80))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMinimizeWindow)
                    .addComponent(btnCloseWindow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPaneVocabulary, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );

        tabbedPaneVocabulary.getAccessibleContext().setAccessibleName("List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point currCoords = evt.getLocationOnScreen();
        this.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mouseDownCompCoords = evt.getPoint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        mouseDownCompCoords = null;
    }//GEN-LAST:event_formMouseReleased

    private void btnCloseWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseClicked
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_btnCloseWindowMouseClicked

    private void btnMinimizeWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeWindowMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeWindowMouseClicked

    private void tabbedPaneVocabularyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneVocabularyStateChanged
        if(tabbedPaneVocabulary.getSelectedIndex() == 0) {
            new Vocabulary().repaintTable(tableVocabularies, labelTotalRows, "ASC", 1);
            tableVocabularies.getColumnModel().getColumn(0).setMinWidth(0);
            tableVocabularies.getColumnModel().getColumn(0).setMaxWidth(0);
            tableVocabularies.getColumnModel().getColumn(0).setWidth(0);
            
            tableVocabularies.setFont(myanmarFont);
            tableVocabularies.getColumnModel().getColumn(1).setCellRenderer(r);
        } else if (tabbedPaneVocabulary.getSelectedIndex() == 2) {
            labelId.setVisible(false);
            labelVocabularyId.setVisible(false);
            txtVocabularyName.requestFocus();
        }
        
        labelId.setVisible(false);
        labelVocabularyId.setVisible(false);
        txtRequiredName.setText("");
        txtRequiredRomaji.setText("");
        txtRequiredPronounciation.setText("");
        txtRequiredMeaning.setText("");
        txtVocabularyName.setText("");
        txtVocabularyRomaji.setText("");
        txtVocabularyJpMm.setText("");
        txtVocabularyMeaning.setText("");
    }//GEN-LAST:event_tabbedPaneVocabularyStateChanged

    private void txtVocabularyMeaningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyMeaningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyMeaningActionPerformed

    private void cboxLessonInCreateVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxLessonInCreateVocabularyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxLessonInCreateVocabularyActionPerformed

    private void txtVocabularyJpMmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyJpMmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyJpMmActionPerformed

    private void txtVocabularyRomajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyRomajiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyRomajiActionPerformed

    private void btnGoVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoVocabularyActionPerformed
        String id =  labelVocabularyId.getText();
        String name = txtVocabularyName.getText();
        String romaji = txtVocabularyRomaji.getText();
        String jp_mm = txtVocabularyJpMm.getText();
        String meaning = txtVocabularyMeaning.getText();
        String lessonId = cboxLessonInCreateVocabulary.getSelectedIndex() + 1 + "";

        if ("".equals(name)) txtRequiredName.setText("Required");
        else txtRequiredName.setText("");
        
        if ("".equals(romaji)) txtRequiredRomaji.setText("Required");
        else txtRequiredRomaji.setText("");
        
        if ("".equals(jp_mm)) txtRequiredPronounciation.setText("Required");
        else txtRequiredPronounciation.setText("");
        
        if ("".equals(meaning)) txtRequiredMeaning.setText("Required");
        else txtRequiredMeaning.setText("");
        
        if (!("".equals(name)) && !("".equals(romaji)) && !("".equals(jp_mm)) && !("".equals(meaning))) {
            Vocabulary vocabulary;
            // Create
            if ("".equals(id)) {
                vocabulary = new Vocabulary(name, romaji, jp_mm, meaning, lessonId);
                if (vocabulary.insert())
                    msgCreateVocabulary.setText("Successfully added new vocabulary.");
            } 
            // Update
            else {
                vocabulary = new Vocabulary(name, romaji, jp_mm, meaning, lessonId);
                if (vocabulary.update(id))
                    msgCreateVocabulary.setText("Successfully updated the vocabulary.");
            } 
        }
    }//GEN-LAST:event_btnGoVocabularyActionPerformed

    private void txtVocabularyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyNameActionPerformed

    // Update Vocabulary
    private void btnUpdateVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateVocabularyActionPerformed
        int selectedRow = tableVocabularies.getSelectedRow();

        TableModel tableModel = tableVocabularies.getModel();
        Integer id = (Integer) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String romaji = (String) tableModel.getValueAt(selectedRow, 2);
        String jpMm = (String) tableModel.getValueAt(selectedRow, 3);
        String meaning = (String) tableModel.getValueAt(selectedRow, 4);

        tabbedPaneVocabulary.setSelectedIndex(2);

        txtVocabularyName.setText(name);
        txtVocabularyRomaji.setText(romaji);
        txtVocabularyJpMm.setText(jpMm);
        txtVocabularyMeaning.setText(meaning);
        cboxLessonInCreateVocabulary.setSelectedIndex(cboxLessonList.getSelectedIndex());
        labelId.setVisible(true);
        labelVocabularyId.setVisible(true);
        labelVocabularyId.setText(id.toString());
    }//GEN-LAST:event_btnUpdateVocabularyActionPerformed

    private void btnDeleteVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteVocabularyActionPerformed
        int selectedLesson = cboxLessonList.getSelectedIndex() + 1;
        int selectedRow = tableVocabularies.getSelectedRow();

        TableModel tableModel = tableVocabularies.getModel();
        Integer id = (Integer) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Do you want to delete the select row?\n The operation cannot be undone once it is succeeded!",
            "Are you sure?",
            JOptionPane.YES_NO_CANCEL_OPTION
        );
        if (confirm == 0) {
            new Vocabulary().delete(id);
            Vocabulary vocabulary = new Vocabulary();
            vocabulary.repaintTable(tableVocabularies, labelTotalRows, "ASC", selectedLesson);
            
            tableVocabularies.getColumnModel().getColumn(0).setMinWidth(0);
            tableVocabularies.getColumnModel().getColumn(0).setMaxWidth(0);
            tableVocabularies.getColumnModel().getColumn(0).setWidth(0);

            tableVocabularies.setFont(myanmarFont);
            tableVocabularies.getColumnModel().getColumn(1).setCellRenderer(r);
        }
    }//GEN-LAST:event_btnDeleteVocabularyActionPerformed

    private void btnClearSearchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSearchTextActionPerformed
        txtSearchBox.setText("");
    }//GEN-LAST:event_btnClearSearchTextActionPerformed

    private void cboxSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxSearchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxSearchBoxActionPerformed

    private void cboxLessonListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboxLessonListPropertyChange

    }//GEN-LAST:event_cboxLessonListPropertyChange

    private void cboxLessonListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxLessonListActionPerformed
        int selectedLesson = cboxLessonList.getSelectedIndex() + 1;

        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintTable(tableVocabularies, labelTotalRows, "ASC", selectedLesson);

        tableVocabularies.getColumnModel().getColumn(0).setMinWidth(0);
        tableVocabularies.getColumnModel().getColumn(0).setMaxWidth(0);
        tableVocabularies.getColumnModel().getColumn(0).setWidth(0);
        
        tableVocabularies.setFont(myanmarFont);
        tableVocabularies.getColumnModel().getColumn(1).setCellRenderer(r);
    }//GEN-LAST:event_cboxLessonListActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        int searchKey = cboxSearchBox.getSelectedIndex();
        String searchValue = txtSearchBox.getText();
        
        if ("".equals(searchValue)) {
            txtSearchBox.requestFocus();
            return;
        }

        String targetColumn = "";
        if (searchKey == 0) targetColumn = "romaji";
        else if (searchKey == 1) targetColumn = "name";

        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintTable(tableVocabularies, labelTotalRows, targetColumn, searchValue);

        tableVocabularies.getColumnModel().getColumn(0).setMinWidth(0);
        tableVocabularies.getColumnModel().getColumn(0).setMaxWidth(0);
        tableVocabularies.getColumnModel().getColumn(0).setWidth(0);
        
        tableVocabularies.setFont(myanmarFont);
        tableVocabularies.getColumnModel().getColumn(1).setCellRenderer(r);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchBoxActionPerformed

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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels(); 
                try {
                    UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    new App().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FontFormatException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearSearchText;
    private javax.swing.JLabel btnCloseWindow;
    private javax.swing.JButton btnDeleteVocabulary;
    private javax.swing.JButton btnGoVocabulary;
    private javax.swing.JLabel btnMinimizeWindow;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateVocabulary;
    private javax.swing.JLabel btnVocabulary;
    private javax.swing.JComboBox<String> cboxLessonInCreateVocabulary;
    private javax.swing.JComboBox<String> cboxLessonList;
    private javax.swing.JComboBox<String> cboxSearchBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelChooseLessonInVocaCreate;
    private javax.swing.JLabel labelCreateUpdateVocabulary;
    private javax.swing.JLabel labelFilterByLessonInVocaList;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelMeaningInVocaCreate;
    private javax.swing.JLabel labelPronounciationInVocaCreate;
    private javax.swing.JLabel labelRomajiInVocaCreate;
    private javax.swing.JLabel labelSearchInAllLessonsInVocaList;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelTotalRows;
    private javax.swing.JLabel labelVocaInHiraganaKatakanaInVocaCreate;
    private javax.swing.JLabel labelVocabularyId;
    private javax.swing.JLabel msgCreateVocabulary;
    private javax.swing.JTabbedPane tabbedPaneVocabulary;
    private javax.swing.JTable tableVocabularies;
    private javax.swing.JLabel txtRequiredMeaning;
    private javax.swing.JLabel txtRequiredName;
    private javax.swing.JLabel txtRequiredPronounciation;
    private javax.swing.JLabel txtRequiredRomaji;
    private javax.swing.JTextField txtSearchBox;
    private javax.swing.JTextField txtVocabularyJpMm;
    private javax.swing.JTextField txtVocabularyMeaning;
    private javax.swing.JTextField txtVocabularyName;
    private javax.swing.JTextField txtVocabularyRomaji;
    // End of variables declaration//GEN-END:variables
}
