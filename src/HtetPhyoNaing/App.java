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
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author GIC
 */
public class App extends javax.swing.JFrame {
    private Point mouseDownCompCoords = null;
    private DefaultTableCellRenderer tableCellRendererForJp;
    private DefaultTableCellRenderer tableCellRendererForRomoji;
    private DefaultTableCellRenderer tableCellRendererForMm;
    private Font customFont;
    private Font jpFont;
    private Font myanmarFont;
    private Font titleFont;
    private Font subTitleFont;
    private final Practice practicePanel;
    
    private final Color VERY_DARK_GREEN = new Color(0,153,0);

    /**
     * Creates new form App
     * @throws java.io.IOException
     * @throws java.awt.FontFormatException
     */
    public App() throws IOException, FontFormatException {
        initComponents();
        
        this.setShape(new RoundRectangle2D.Double(0, 0, 1000, 620, 20, 20));
        
        practicePanel = new Practice();
        tabbedPaneVocabulary.addTab("Practice", practicePanel);
        
        createFonts();
        showButtonsOnTableRowSelected();
        setUpTable(tableVocabularies);
        setUpFonts();
        panelVersionMoreInfo.setVisible(false);
    }
    
    private void createFonts() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("HtetPhyoNaing/Resources/Fonts/cerebrisans-regular.ttf")).deriveFont(14f);
            jpFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("HtetPhyoNaing/Resources/Fonts/MHGKyokashotaiTHK-Light.ttf")).deriveFont(22f);
            myanmarFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("HtetPhyoNaing/Resources/Fonts/Pyidaungsu-1.8_regular.ttf")).deriveFont(15f);
            
            titleFont = customFont.deriveFont(18f);
            subTitleFont = customFont.deriveFont(13f);
        } catch (IOException | FontFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void setUpTable(JTable table) {
        // hide id column
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
        
        // disable table to be edited
        table.setDefaultEditor(Object.class, null);
        
        // custom table cell renderer for Japanese column
        tableCellRendererForJp = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setFont(getJpFont());
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                return this;
            }
        };
        
        // custom table cell renderer for romoji column
        tableCellRendererForRomoji = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setFont(customFont);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                return this;
            }
        };
        
        // custom table cell renderer for Myanmar column
        tableCellRendererForMm = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setFont(getMyanmarFont());
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                return this;
            }
        };
        
        // apply custom table cell renderers to table
        table.getColumnModel().getColumn(1).setCellRenderer(tableCellRendererForJp);
        table.getColumnModel().getColumn(2).setCellRenderer(tableCellRendererForRomoji);
        table.getColumnModel().getColumn(3).setCellRenderer(tableCellRendererForMm);
    }
    
    private void setUpFonts() {
        // List page
        btnSearch.setFont(customFont);
        btnUpdateVocabulary.setFont(customFont);
        btnDeleteVocabulary.setFont(customFont);
        btnFavoriteVocabulary.setFont(customFont);
        // Vocabulary Page
        btnGoVocabulary.setFont(customFont);
        btnImportCSV.setFont(customFont);
        
        // List Page
        cboxLessonsInListPage.setFont(customFont);
        cboxSearchBox.setFont(customFont);
        // Vocabulary Page
        cboxLessonsInCreateUpdateVocabularyPage.setFont(customFont);
        // Faborites Page
        cboxFavoriteLessonsInFavoritePage.setFont(customFont);
        
        // Vocabulary Page
        checkBoxMarkAsFavoriteInVocabularyPage.setFont(customFont);
        
        // List Page
        labelTotalRowsInListPage.setFont(customFont);
        labelFilterByLessonInListPage.setFont(customFont);
        labelSearchInAllLessonsInListPage.setFont(customFont);
        
        // Vocabulary Page
        labelTitleCreateUpdateInVocabularyPage.setFont(titleFont);
        labelChooseLessonInVocabularyPage.setFont(customFont);
        labelHiraganaKatakanaInVocabularyPage.setFont(customFont);
        labelRomajiInVocabularyPage.setFont(customFont);
        labelMeaningInVocabularyPage.setFont(customFont);
        labelIdInVocabularyPage.setFont(customFont);
        labelVocabularyIdInVocabularyPage.setFont(customFont);
        labelImportVocabularyInVocabularyPage.setFont(titleFont);
        labelCSVImportPathInVocabularyPage.setFont(customFont);
        labelCsvInVocabularyPage.setFont(customFont);
        labelVocabularyCreatedOrUpdatedMessageInVocabularyPage.setFont(customFont);
        // Favorites Page
        labelTotalRowsInFavoritePage.setFont(customFont);
        labelFilterByLessonInFavoritePage.setFont(customFont);
        // App
        labelDeveloperMail.setFont(subTitleFont);
        labelAppVersion.setFont(subTitleFont);
        labelAppInfoLanguage.setFont(customFont);
        labelAppInfoUIComponents.setFont(customFont);
        labelAppInfoDevelopedTime.setFont(customFont);
        labelAppInfoAbout.setFont(customFont);
        labelAppInfoAck.setFont(customFont);
        labelAppInfoAckBody.setFont(customFont);
        
        // App
        tabbedPaneVocabulary.setFont(customFont);
        
        // Vocabulary Page
        txtVocabularyNameInVocabularyPage.setFont(getJpFont());
        txtVocabularyMeaningInVocabularyPage.setFont(getMyanmarFont());
        txtSearchBoxInListPage.setFont(customFont);
        txtRequiredNameInVocabularyPage.setFont(subTitleFont);
        txtRequiredRomajiInVocabularyPage.setFont(subTitleFont);
        txtRequiredMeaningInVocabularyPage.setFont(subTitleFont);
        
        // Practice Page
        practicePanel.getBtnPracticeVoca().setFont(customFont);
        
        practicePanel.getCboxPracticeChooseAllOrFav().setFont(customFont);
        practicePanel.getCboxPracticeFromLesson().setFont(customFont);
        practicePanel.getCboxPracticeLessonTo().setFont(customFont);
        practicePanel.getCboxPracticeFromWantToSee().setFont(customFont);
        
        practicePanel.getLabelPracticeChooseTypeOfPractice().setFont(customFont);
        practicePanel.getLabelPracticeChooseTypeOfPracticeFrom().setFont(customFont);
        practicePanel.getLabelPracticeChooseTypeOfPracticeTo().setFont(customFont);
        practicePanel.getLabelPracticeTotalRows().setFont(customFont);
        practicePanel.getLabelPracticeIWantToSee().setFont(customFont);
        practicePanel.getLabelPracticeVocaJPAnswer().setFont(getJpFont());
        practicePanel.getLabelPracticeVocaMMAnswer().setFont(getMyanmarFont());
        practicePanel.getLabelPracticeTableDesc().setFont(customFont);
    }

    private void clearLabelsAndTextFieldsInCreateVoca() {
        getLabelVocabularyId().setVisible(false);
        getTxtRequiredName().setText("");
        getTxtRequiredRomaji().setText("");
        getTxtRequiredMeaning().setText("");
        getTxtVocabularyName().setText("");
        getTxtVocabularyRomaji().setText("");
        getTxtVocabularyMeaning().setText("");
        getMsgCreateVocabulary().setText("");
    }
        
    private void showButtonsOnTableRowSelected() {
        btnUpdateVocabulary.setVisible(false);
        btnDeleteVocabulary.setVisible(false);
        btnFavoriteVocabulary.setVisible(false);
                    
        ListSelectionModel listSelectionModel = tableVocabularies.getSelectionModel();
        listSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            if(!listSelectionModel.isSelectionEmpty()) {
                
                // if a table row is selected, these following buttons are visible.
                btnUpdateVocabulary.setVisible(true);
                btnDeleteVocabulary.setVisible(true);
                btnFavoriteVocabulary.setVisible(true);
                
                // get id of selected row to determine it is favorite or not, for the state of favorite button
                int selectedRow = tableVocabularies.getSelectedRow();
                TableModel tableModel = tableVocabularies.getModel();
                Integer id = (Integer) tableModel.getValueAt(selectedRow, 0);
                
                Vocabulary vocabulary = new Vocabulary();
                boolean isFavorite = vocabulary.isFavorite(id);
                
                // change state of favorite button
                if (isFavorite) {
                    btnFavoriteVocabulary.setText("Favorited");
                    btnFavoriteVocabulary.setIcon(new ImageIcon(getClass().getClassLoader().getResource("HtetPhyoNaing/Resources/Images/img-favoried.png")));
                } else {
                    btnFavoriteVocabulary.setText("Favorite");
                    btnFavoriteVocabulary.setIcon(new ImageIcon(getClass().getClassLoader().getResource("HtetPhyoNaing/Resources/Images/img-btn-favorite.png")));
                }
                
            }
        });
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
        labelDeveloperMail = new javax.swing.JLabel();
        labelAppVersion = new javax.swing.JLabel();
        btnCloseWindow = new javax.swing.JLabel();
        btnMinimizeWindow = new javax.swing.JLabel();
        tabbedPaneVocabulary = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        txtSearchBoxInListPage = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVocabularies = new javax.swing.JTable();
        cboxLessonsInListPage = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        cboxSearchBox = new javax.swing.JComboBox<>();
        labelTotalRowsInListPage = new javax.swing.JLabel();
        btnClearSearchText = new javax.swing.JButton();
        btnDeleteVocabulary = new javax.swing.JButton();
        btnUpdateVocabulary = new javax.swing.JButton();
        labelFilterByLessonInListPage = new javax.swing.JLabel();
        labelSearchInAllLessonsInListPage = new javax.swing.JLabel();
        btnFavoriteVocabulary = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        cboxFavoriteLessonsInFavoritePage = new javax.swing.JComboBox<>();
        labelFilterByLessonInFavoritePage = new javax.swing.JLabel();
        labelTotalRowsInFavoritePage = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableFavoriteVocabularies = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        labelChooseLessonInVocabularyPage = new javax.swing.JLabel();
        txtVocabularyNameInVocabularyPage = new javax.swing.JTextField();
        labelHiraganaKatakanaInVocabularyPage = new javax.swing.JLabel();
        labelRomajiInVocabularyPage = new javax.swing.JLabel();
        labelMeaningInVocabularyPage = new javax.swing.JLabel();
        btnGoVocabulary = new javax.swing.JButton();
        txtVocabularyRomajiInVocabularyPage = new javax.swing.JTextField();
        cboxLessonsInCreateUpdateVocabularyPage = new javax.swing.JComboBox<>();
        labelTitleCreateUpdateInVocabularyPage = new javax.swing.JLabel();
        labelVocabularyCreatedOrUpdatedMessageInVocabularyPage = new javax.swing.JLabel();
        txtRequiredNameInVocabularyPage = new javax.swing.JLabel();
        txtVocabularyMeaningInVocabularyPage = new javax.swing.JTextField();
        txtRequiredMeaningInVocabularyPage = new javax.swing.JLabel();
        labelIdInVocabularyPage = new javax.swing.JLabel();
        labelVocabularyIdInVocabularyPage = new javax.swing.JLabel();
        checkBoxMarkAsFavoriteInVocabularyPage = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        labelImportVocabularyInVocabularyPage = new javax.swing.JLabel();
        btnImportCSV = new javax.swing.JButton();
        labelCSVImportPathInVocabularyPage = new javax.swing.JLabel();
        txtRequiredRomajiInVocabularyPage = new javax.swing.JLabel();
        labelCsvInVocabularyPage = new javax.swing.JLabel();
        panelVersionMoreInfo = new javax.swing.JPanel();
        labelAppInfoLanguage = new javax.swing.JLabel();
        labelAppInfoDevelopedTime = new javax.swing.JLabel();
        labelAppInfoAbout = new javax.swing.JLabel();
        labelAppInfoUIComponents = new javax.swing.JLabel();
        labelAppInfoAck = new javax.swing.JLabel();
        labelAppInfoAckBody = new javax.swing.JLabel();

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
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(94, 0, 126));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/logo.png"))); // NOI18N

        labelTitle.setBackground(new java.awt.Color(63, 63, 63));
        labelTitle.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 36)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle.setText("Foreword");

        jPanel3.setBackground(new java.awt.Color(126, 63, 155));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(240, 52));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVocabulary.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        btnVocabulary.setForeground(new java.awt.Color(255, 255, 255));
        btnVocabulary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/listing.png"))); // NOI18N
        btnVocabulary.setText("Vocabulary");
        jPanel3.add(btnVocabulary, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        labelDeveloperMail.setForeground(new java.awt.Color(204, 255, 255));
        labelDeveloperMail.setText("mr.htetphyonaing@gmail.com");

        labelAppVersion.setForeground(new java.awt.Color(255, 255, 255));
        labelAppVersion.setText("Version 1.0.0");
        labelAppVersion.setToolTipText("");
        labelAppVersion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelAppVersion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAppVersionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelAppVersionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelAppVersionMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitle)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel2))))
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(labelAppVersion))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(labelDeveloperMail))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(labelTitle))
                    .addComponent(jLabel2))
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(328, 328, 328)
                .addComponent(labelAppVersion)
                .addGap(6, 6, 6)
                .addComponent(labelDeveloperMail))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 233, 680));

        btnCloseWindow.setForeground(new java.awt.Color(51, 51, 255));
        btnCloseWindow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/close.png"))); // NOI18N
        btnCloseWindow.setToolTipText("close");
        btnCloseWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCloseWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseWindowMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseWindowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseWindowMouseExited(evt);
            }
        });
        jPanel1.add(btnCloseWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, -1, -1));

        btnMinimizeWindow.setForeground(new java.awt.Color(51, 51, 255));
        btnMinimizeWindow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/img-btn-minimize_1.png"))); // NOI18N
        btnMinimizeWindow.setToolTipText("minimize");
        btnMinimizeWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimizeWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeWindowMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizeWindowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizeWindowMouseExited(evt);
            }
        });
        jPanel1.add(btnMinimizeWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, -1, -1));

        tabbedPaneVocabulary.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPaneVocabulary.setToolTipText("");
        tabbedPaneVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabbedPaneVocabulary.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        tabbedPaneVocabulary.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneVocabularyStateChanged(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtSearchBoxInListPage.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtSearchBoxInListPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBoxInListPageActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/img-btn-search.png"))); // NOI18N
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
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVocabularies.setAlignmentY(0.3F);
        tableVocabularies.setFocusable(false);
        tableVocabularies.setGridColor(new java.awt.Color(243, 235, 245));
        tableVocabularies.setRowHeight(27);
        tableVocabularies.setSelectionBackground(new java.awt.Color(237, 226, 240));
        tableVocabularies.setSelectionForeground(new java.awt.Color(94, 0, 126));
        tableVocabularies.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableVocabularies);

        cboxLessonsInListPage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxLessonsInListPage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Lesson 16", "Lesson 17", "Lesson 18", "Lesson 19", "Lesson 20", "Lesson 21", "Lesson 22", "Lesson 23", "Lesson 24", "Lesson 25" }));
        cboxLessonsInListPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxLessonsInListPageActionPerformed(evt);
            }
        });
        cboxLessonsInListPage.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboxLessonsInListPagePropertyChange(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cboxSearchBox.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxSearchBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Romaji", "Japanese" }));
        cboxSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxSearchBoxActionPerformed(evt);
            }
        });

        labelTotalRowsInListPage.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        labelTotalRowsInListPage.setText("jLabel1");

        btnClearSearchText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/close.png"))); // NOI18N
        btnClearSearchText.setToolTipText("Clear search box text");
        btnClearSearchText.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClearSearchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSearchTextActionPerformed(evt);
            }
        });

        btnDeleteVocabulary.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        btnDeleteVocabulary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/img-btn-delete.png"))); // NOI18N
        btnDeleteVocabulary.setText("Delete");
        btnDeleteVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteVocabulary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteVocabularyActionPerformed(evt);
            }
        });

        btnUpdateVocabulary.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        btnUpdateVocabulary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/img-btn-update.png"))); // NOI18N
        btnUpdateVocabulary.setText("Edit");
        btnUpdateVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateVocabulary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateVocabularyActionPerformed(evt);
            }
        });

        labelFilterByLessonInListPage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelFilterByLessonInListPage.setForeground(new java.awt.Color(94, 0, 126));
        labelFilterByLessonInListPage.setText("Filter by Lesson");
        labelFilterByLessonInListPage.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        labelSearchInAllLessonsInListPage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelSearchInAllLessonsInListPage.setForeground(new java.awt.Color(94, 0, 126));
        labelSearchInAllLessonsInListPage.setText("Search in All Lessons");
        labelSearchInAllLessonsInListPage.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        btnFavoriteVocabulary.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        btnFavoriteVocabulary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/img-btn-favorite.png"))); // NOI18N
        btnFavoriteVocabulary.setText("Favorite");
        btnFavoriteVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFavoriteVocabulary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFavoriteVocabularyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(labelTotalRowsInListPage, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboxLessonsInListPage, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(labelFilterByLessonInListPage, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelSearchInAllLessonsInListPage, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(cboxSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(txtSearchBoxInListPage, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(btnFavoriteVocabulary, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnUpdateVocabulary)
                            .addGap(7, 7, 7)
                            .addComponent(btnDeleteVocabulary))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(278, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(labelTotalRowsInListPage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(cboxSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFilterByLessonInListPage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSearchInAllLessonsInListPage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxLessonsInListPage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchBoxInListPage, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFavoriteVocabulary)
                    .addComponent(btnUpdateVocabulary)
                    .addComponent(btnDeleteVocabulary))
                .addContainerGap(171, Short.MAX_VALUE))
        );

        tabbedPaneVocabulary.addTab("List", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        cboxFavoriteLessonsInFavoritePage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxFavoriteLessonsInFavoritePage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Lesson 16", "Lesson 17", "Lesson 18", "Lesson 19", "Lesson 20", "Lesson 21", "Lesson 22", "Lesson 23", "Lesson 24", "Lesson 25" }));
        cboxFavoriteLessonsInFavoritePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxFavoriteLessonsInFavoritePageActionPerformed(evt);
            }
        });
        cboxFavoriteLessonsInFavoritePage.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboxFavoriteLessonsInFavoritePagePropertyChange(evt);
            }
        });

        labelFilterByLessonInFavoritePage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelFilterByLessonInFavoritePage.setForeground(new java.awt.Color(94, 0, 126));
        labelFilterByLessonInFavoritePage.setText("Filter by Lesson");
        labelFilterByLessonInFavoritePage.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        labelTotalRowsInFavoritePage.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        labelTotalRowsInFavoritePage.setText("jLabel1");

        tableFavoriteVocabularies.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableFavoriteVocabularies.setModel(new javax.swing.table.DefaultTableModel(
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
        tableFavoriteVocabularies.setAlignmentY(0.3F);
        tableFavoriteVocabularies.setGridColor(new java.awt.Color(243, 235, 245));
        tableFavoriteVocabularies.setRowHeight(27);
        tableFavoriteVocabularies.setSelectionBackground(new java.awt.Color(237, 226, 240));
        tableFavoriteVocabularies.setSelectionForeground(new java.awt.Color(94, 0, 126));
        tableFavoriteVocabularies.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tableFavoriteVocabularies);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labelTotalRowsInFavoritePage, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(447, 447, 447)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cboxFavoriteLessonsInFavoritePage, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(labelFilterByLessonInFavoritePage))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(277, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTotalRowsInFavoritePage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labelFilterByLessonInFavoritePage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxFavoriteLessonsInFavoritePage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
        );

        tabbedPaneVocabulary.addTab("Favorites", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelChooseLessonInVocabularyPage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelChooseLessonInVocabularyPage.setForeground(new java.awt.Color(94, 0, 126));
        labelChooseLessonInVocabularyPage.setText("Choose Lesson");
        jPanel8.add(labelChooseLessonInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        txtVocabularyNameInVocabularyPage.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtVocabularyNameInVocabularyPage.setMinimumSize(new java.awt.Dimension(6, 31));
        txtVocabularyNameInVocabularyPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyNameInVocabularyPageActionPerformed(evt);
            }
        });
        jPanel8.add(txtVocabularyNameInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 284, 40));

        labelHiraganaKatakanaInVocabularyPage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelHiraganaKatakanaInVocabularyPage.setForeground(new java.awt.Color(94, 0, 126));
        labelHiraganaKatakanaInVocabularyPage.setText("Vocabulary in Hiragana/Katakana");
        jPanel8.add(labelHiraganaKatakanaInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        labelRomajiInVocabularyPage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelRomajiInVocabularyPage.setForeground(new java.awt.Color(94, 0, 126));
        labelRomajiInVocabularyPage.setText("Romaji");
        jPanel8.add(labelRomajiInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, -1, -1));

        labelMeaningInVocabularyPage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelMeaningInVocabularyPage.setForeground(new java.awt.Color(94, 0, 126));
        labelMeaningInVocabularyPage.setText("Meaning");
        jPanel8.add(labelMeaningInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        btnGoVocabulary.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        btnGoVocabulary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/img-btn-go.png"))); // NOI18N
        btnGoVocabulary.setText("Go");
        btnGoVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoVocabulary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoVocabularyActionPerformed(evt);
            }
        });
        jPanel8.add(btnGoVocabulary, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 90, 40));

        txtVocabularyRomajiInVocabularyPage.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtVocabularyRomajiInVocabularyPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyRomajiInVocabularyPageActionPerformed(evt);
            }
        });
        jPanel8.add(txtVocabularyRomajiInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 287, 40));

        cboxLessonsInCreateUpdateVocabularyPage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxLessonsInCreateUpdateVocabularyPage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Lesson 16", "Lesson 17", "Lesson 18", "Lesson 19", "Lesson 20", "Lesson 21", "Lesson 22", "Lesson 23", "Lesson 24", "Lesson 25" }));
        cboxLessonsInCreateUpdateVocabularyPage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cboxLessonsInCreateUpdateVocabularyPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxLessonsInCreateUpdateVocabularyPageActionPerformed(evt);
            }
        });
        jPanel8.add(cboxLessonsInCreateUpdateVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 94, 32));

        labelTitleCreateUpdateInVocabularyPage.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        labelTitleCreateUpdateInVocabularyPage.setText("Create/Update Vocabulary");
        jPanel8.add(labelTitleCreateUpdateInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 26, -1, -1));

        labelVocabularyCreatedOrUpdatedMessageInVocabularyPage.setBackground(new java.awt.Color(0, 204, 51));
        labelVocabularyCreatedOrUpdatedMessageInVocabularyPage.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        labelVocabularyCreatedOrUpdatedMessageInVocabularyPage.setForeground(new java.awt.Color(0, 204, 51));
        jPanel8.add(labelVocabularyCreatedOrUpdatedMessageInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 246, 29));

        txtRequiredNameInVocabularyPage.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredNameInVocabularyPage.setForeground(new java.awt.Color(255, 51, 51));
        jPanel8.add(txtRequiredNameInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 86, 15));

        txtVocabularyMeaningInVocabularyPage.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtVocabularyMeaningInVocabularyPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyMeaningInVocabularyPageActionPerformed(evt);
            }
        });
        jPanel8.add(txtVocabularyMeaningInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 284, 40));

        txtRequiredMeaningInVocabularyPage.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredMeaningInVocabularyPage.setForeground(new java.awt.Color(255, 51, 51));
        jPanel8.add(txtRequiredMeaningInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 86, 14));

        labelIdInVocabularyPage.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        labelIdInVocabularyPage.setText("Id:");
        jPanel8.add(labelIdInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 28, 38));

        labelVocabularyIdInVocabularyPage.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        labelVocabularyIdInVocabularyPage.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel8.add(labelVocabularyIdInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 36, 38));

        checkBoxMarkAsFavoriteInVocabularyPage.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxMarkAsFavoriteInVocabularyPage.setForeground(new java.awt.Color(94, 0, 126));
        checkBoxMarkAsFavoriteInVocabularyPage.setText("Mark as favorite");
        checkBoxMarkAsFavoriteInVocabularyPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxMarkAsFavoriteInVocabularyPageActionPerformed(evt);
            }
        });
        jPanel8.add(checkBoxMarkAsFavoriteInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 139, 31));
        jPanel8.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 769, 10));

        labelImportVocabularyInVocabularyPage.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        labelImportVocabularyInVocabularyPage.setText("Bulk Create Vocabulary");
        jPanel8.add(labelImportVocabularyInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        btnImportCSV.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        btnImportCSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HtetPhyoNaing/Resources/Images/img-btn-import-csv.png"))); // NOI18N
        btnImportCSV.setText("Import CSV");
        btnImportCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImportCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportCSVActionPerformed(evt);
            }
        });
        jPanel8.add(btnImportCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, 40));

        labelCSVImportPathInVocabularyPage.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelCSVImportPathInVocabularyPage.setMaximumSize(new java.awt.Dimension(400, 0));
        labelCSVImportPathInVocabularyPage.setPreferredSize(new java.awt.Dimension(200, 0));
        jPanel8.add(labelCSVImportPathInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 461, 430, 20));

        txtRequiredRomajiInVocabularyPage.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredRomajiInVocabularyPage.setForeground(new java.awt.Color(255, 51, 51));
        jPanel8.add(txtRequiredRomajiInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 86, 15));

        labelCsvInVocabularyPage.setForeground(new java.awt.Color(0, 153, 51));
        jPanel8.add(labelCsvInVocabularyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 444, 250, 20));

        tabbedPaneVocabulary.addTab("Vocabulary", jPanel8);

        jPanel1.add(tabbedPaneVocabulary, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));
        tabbedPaneVocabulary.getAccessibleContext().setAccessibleName("List");

        panelVersionMoreInfo.setBackground(new java.awt.Color(126, 63, 155));

        labelAppInfoLanguage.setForeground(new java.awt.Color(255, 255, 255));
        labelAppInfoLanguage.setText("Language: Java");

        labelAppInfoDevelopedTime.setForeground(new java.awt.Color(255, 255, 255));
        labelAppInfoDevelopedTime.setText("Developed time: 2 weeks");

        labelAppInfoAbout.setForeground(new java.awt.Color(255, 255, 255));
        labelAppInfoAbout.setText("About: Japanese Vocabulary Trainer");

        labelAppInfoUIComponents.setForeground(new java.awt.Color(255, 255, 255));
        labelAppInfoUIComponents.setText("UI Components: Swing");

        labelAppInfoAck.setForeground(new java.awt.Color(255, 255, 255));
        labelAppInfoAck.setText("Acknowledgement:");

        labelAppInfoAckBody.setForeground(new java.awt.Color(255, 255, 255));
        labelAppInfoAckBody.setText("Thanks a million to Tutor Ma Nyunt Nyunt Hlaing, Ma Su Su Lin and my sister Hnin Hnin for data entry.");

        javax.swing.GroupLayout panelVersionMoreInfoLayout = new javax.swing.GroupLayout(panelVersionMoreInfo);
        panelVersionMoreInfo.setLayout(panelVersionMoreInfoLayout);
        panelVersionMoreInfoLayout.setHorizontalGroup(
            panelVersionMoreInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVersionMoreInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVersionMoreInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAppInfoLanguage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAppInfoDevelopedTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAppInfoAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAppInfoUIComponents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAppInfoAck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAppInfoAckBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelVersionMoreInfoLayout.setVerticalGroup(
            panelVersionMoreInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVersionMoreInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAppInfoLanguage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAppInfoUIComponents)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAppInfoDevelopedTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAppInfoAbout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAppInfoAck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAppInfoAckBody)
                .addGap(34, 34, 34))
        );

        jPanel1.add(panelVersionMoreInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 386, 680, 170));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
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

    private void tabbedPaneVocabularyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneVocabularyStateChanged
        Vocabulary vocabulary;
        
        switch (getTabbedPaneVocabulary().getSelectedIndex()) {
            case 0:
                int selectedLesson = cboxLessonsInListPage.getSelectedIndex() + 1;
                if ("".equals(txtSearchBoxInListPage.getText())) {
                    new Vocabulary().repaintTable(tableVocabularies, getLabelTotalRows(), "ASC", selectedLesson);
                    setUpTable(tableVocabularies);
                }
                break;
            case 1:
                int selectedFavLesson = cboxFavoriteLessonsInFavoritePage.getSelectedIndex();
                vocabulary = new Vocabulary();
                vocabulary.repaintFavTable(tableFavoriteVocabularies, labelTotalRowsInFavoritePage, selectedFavLesson);
                setUpTable(tableFavoriteVocabularies);
                break;
            case 2:
                labelIdInVocabularyPage.setVisible(false);
                getLabelVocabularyId().setVisible(false);
                getTxtVocabularyName().requestFocus();
                labelCSVImportPathInVocabularyPage.setText("");
                labelCsvInVocabularyPage.setText("");
                break;
            case 3:
                int fromLesson = practicePanel.getCboxPracticeFromLesson().getSelectedIndex();
                int toLesson = practicePanel.getCboxPracticeLessonTo().getSelectedIndex();
                practicePanel.cboxPracticeChooseAllorFav();
                practicePanel.getCboxPracticeFromLesson().setSelectedIndex(fromLesson);
                practicePanel.getCboxPracticeLessonTo().setSelectedIndex(toLesson);
                break;
            default:
                break;
        }

        labelIdInVocabularyPage.setVisible(false);
        clearLabelsAndTextFieldsInCreateVoca();
    }//GEN-LAST:event_tabbedPaneVocabularyStateChanged

    
    private void checkBoxMarkAsFavoriteInVocabularyPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxMarkAsFavoriteInVocabularyPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxMarkAsFavoriteInVocabularyPageActionPerformed

    private void txtVocabularyMeaningInVocabularyPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyMeaningInVocabularyPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyMeaningInVocabularyPageActionPerformed

    private void cboxLessonsInCreateUpdateVocabularyPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxLessonsInCreateUpdateVocabularyPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxLessonsInCreateUpdateVocabularyPageActionPerformed

    private void txtVocabularyRomajiInVocabularyPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyRomajiInVocabularyPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyRomajiInVocabularyPageActionPerformed

    private void btnGoVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoVocabularyActionPerformed
        String id =  getLabelVocabularyId().getText();
        String name = getTxtVocabularyName().getText();
        String romaji = getTxtVocabularyRomaji().getText();
        String meaning = getTxtVocabularyMeaning().getText();
        String lessonId = getCboxLessonInCreateVocabulary().getSelectedIndex() + 1 + "";
        Boolean isFavorite = checkBoxMarkAsFavoriteInVocabularyPage.isSelected();

        if ("".equals(name)) getTxtRequiredName().setText("Required");
        else getTxtRequiredName().setText("");

        if ("".equals(romaji)) getTxtRequiredRomaji().setText("Required");
        else getTxtRequiredRomaji().setText("");

        if ("".equals(meaning)) getTxtRequiredMeaning().setText("Required");
        else getTxtRequiredMeaning().setText("");

        if (!("".equals(name)) && !("".equals(romaji)) && !("".equals(meaning))) {
            Vocabulary vocabulary;
            // Create
            if ("".equals(id)) {
                vocabulary = new Vocabulary(name, romaji, meaning, lessonId, isFavorite);
                if (vocabulary.insert())
                getMsgCreateVocabulary().setText("Successfully added new vocabulary.");
            }
            // Update
            else {
                vocabulary = new Vocabulary(name, romaji, meaning, lessonId, isFavorite);
                if (vocabulary.update(id))
                getMsgCreateVocabulary().setText("Successfully updated the vocabulary.");
            }
        }
    }//GEN-LAST:event_btnGoVocabularyActionPerformed

    private void txtVocabularyNameInVocabularyPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyNameInVocabularyPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyNameInVocabularyPageActionPerformed

    private void btnMinimizeWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeWindowMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeWindowMouseClicked

    private void btnCloseWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseClicked
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_btnCloseWindowMouseClicked

    private void btnFavoriteVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFavoriteVocabularyActionPerformed
        int selectedRow = tableVocabularies.getSelectedRow();
        TableModel tableModel = tableVocabularies.getModel();
        Integer id = (Integer) tableModel.getValueAt(selectedRow, 0);

        Vocabulary vocabulary = new Vocabulary();
        boolean isFavorite = vocabulary.isFavorite(id);
        boolean isToggledFavorite = vocabulary.toggleFavorite(isFavorite, id);

        if(isToggledFavorite) {
            if (isFavorite) {
                btnFavoriteVocabulary.setText("Favorite");
                btnFavoriteVocabulary.setIcon(new ImageIcon(getClass().getClassLoader().getResource("HtetPhyoNaing/Resources/Images/img-btn-favorite.png")));
            } else {
                btnFavoriteVocabulary.setText("Favorited");
                btnFavoriteVocabulary.setIcon(new ImageIcon(getClass().getClassLoader().getResource("HtetPhyoNaing/Resources/Images/img-favoried.png")));
            }
        }
    }//GEN-LAST:event_btnFavoriteVocabularyActionPerformed

    // Update Vocabulary
    private void btnUpdateVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateVocabularyActionPerformed
        int selectedRow = getTableVocabularies().getSelectedRow();

        TableModel tableModel = getTableVocabularies().getModel();
        Integer id = (Integer) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String romaji = (String) tableModel.getValueAt(selectedRow, 2);
        String meaning = (String) tableModel.getValueAt(selectedRow, 3);
        
        String favoriteText = btnFavoriteVocabulary.getText();
        Boolean isFavorite = false;
        if ("Favorited".equals(favoriteText)) isFavorite = true;
        
        getTabbedPaneVocabulary().setSelectedIndex(2);

        getTxtVocabularyName().setText(name);
        getTxtVocabularyRomaji().setText(romaji);
        getTxtVocabularyMeaning().setText(meaning);
        getCboxLessonInCreateVocabulary().setSelectedIndex(getCboxLessonList().getSelectedIndex());
        labelIdInVocabularyPage.setVisible(true);
        getLabelVocabularyId().setVisible(true);
        getLabelVocabularyId().setText(id.toString());
        checkBoxMarkAsFavoriteInVocabularyPage.setSelected(isFavorite);
    }//GEN-LAST:event_btnUpdateVocabularyActionPerformed

    private void btnDeleteVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteVocabularyActionPerformed
        int selectedLesson = getCboxLessonList().getSelectedIndex() + 1;
        int selectedRow = getTableVocabularies().getSelectedRow();

        TableModel tableModel = getTableVocabularies().getModel();
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
            vocabulary.repaintTable(getTableVocabularies(), getLabelTotalRows(), "ASC", selectedLesson);

            setUpTable(tableVocabularies);
        }
    }//GEN-LAST:event_btnDeleteVocabularyActionPerformed

    private void btnClearSearchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSearchTextActionPerformed
        getTxtSearchBox().setText("");
        int selectedLesson = cboxLessonsInListPage.getSelectedIndex() + 1;
        
        new Vocabulary().repaintTable(tableVocabularies, getLabelTotalRows(), "ASC", selectedLesson);
        setUpTable(tableVocabularies);
    }//GEN-LAST:event_btnClearSearchTextActionPerformed

    private void cboxSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxSearchBoxActionPerformed
        txtSearchBoxInListPage.setText("");
        int lessonId = cboxLessonsInListPage.getSelectedIndex() + 1;
        int searchKey = cboxSearchBox.getSelectedIndex(); // 0 - Romaji, 1 - Jp
        
        txtSearchBoxInListPage.requestFocus();
        
        if (searchKey == 0) txtSearchBoxInListPage.setFont(customFont);
        else if (searchKey == 1) txtSearchBoxInListPage.setFont(getJpFont());
                
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintTable(tableVocabularies, labelTotalRowsInListPage, "ASC", lessonId);
        
        setUpTable(tableVocabularies);
    }//GEN-LAST:event_cboxSearchBoxActionPerformed

    private void cboxLessonsInListPagePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboxLessonsInListPagePropertyChange

    }//GEN-LAST:event_cboxLessonsInListPagePropertyChange

    private void cboxLessonsInListPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxLessonsInListPageActionPerformed
        int selectedLesson = getCboxLessonList().getSelectedIndex() + 1;

        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintTable(getTableVocabularies(), getLabelTotalRows(), "ASC", selectedLesson);

        setUpTable(tableVocabularies);
    }//GEN-LAST:event_cboxLessonsInListPageActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        int searchKey = getCboxSearchBox().getSelectedIndex();
        String searchValue = getTxtSearchBox().getText();

        if ("".equals(searchValue)) {
            getTxtSearchBox().requestFocus();
            return;
        }

        String targetColumn = "";
        if (searchKey == 0) targetColumn = "romaji";
        else if (searchKey == 1) targetColumn = "name";

        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintTable(getTableVocabularies(), getLabelTotalRows(), targetColumn, searchValue);

        setUpTable(tableVocabularies);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchBoxInListPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBoxInListPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchBoxInListPageActionPerformed

    private void cboxFavoriteLessonsInFavoritePagePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboxFavoriteLessonsInFavoritePagePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxFavoriteLessonsInFavoritePagePropertyChange

    private void cboxFavoriteLessonsInFavoritePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxFavoriteLessonsInFavoritePageActionPerformed
        int selectedLesson = cboxFavoriteLessonsInFavoritePage.getSelectedIndex();
        
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintFavTable(tableFavoriteVocabularies, labelTotalRowsInFavoritePage, selectedLesson);
        
        setUpTable(tableFavoriteVocabularies);
    }//GEN-LAST:event_cboxFavoriteLessonsInFavoritePageActionPerformed

    private void btnCloseWindowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseEntered
        btnCloseWindow.setIcon(new ImageIcon(getClass().getClassLoader().getResource("HtetPhyoNaing/Resources/Images/img-btn-close.png")));
    }//GEN-LAST:event_btnCloseWindowMouseEntered

    private void btnCloseWindowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseExited
        btnCloseWindow.setIcon(new ImageIcon(getClass().getClassLoader().getResource("HtetPhyoNaing/Resources/Images/close.png")));
    }//GEN-LAST:event_btnCloseWindowMouseExited

    private void btnMinimizeWindowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeWindowMouseEntered
       btnMinimizeWindow.setIcon(new ImageIcon(getClass().getClassLoader().getResource("HtetPhyoNaing/Resources/Images/img-btn-minimize.png")));
    }//GEN-LAST:event_btnMinimizeWindowMouseEntered

    private void btnMinimizeWindowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeWindowMouseExited
       btnMinimizeWindow.setIcon(new ImageIcon(getClass().getClassLoader().getResource("HtetPhyoNaing/Resources/Images/img-btn-minimize_1.png")));
    }//GEN-LAST:event_btnMinimizeWindowMouseExited

    private void btnImportCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportCSVActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        
        String filePath = file.getAbsolutePath();
        if(filePath.length() > 50) labelCSVImportPathInVocabularyPage.setText("..." + filePath.substring(filePath.length() - 50));
        else labelCSVImportPathInVocabularyPage.setText(filePath);
        
        labelCSVImportPathInVocabularyPage.setForeground(Color.BLACK);
        labelCsvInVocabularyPage.setText("Reading and importing data from...");
        labelCsvInVocabularyPage.setForeground(VERY_DARK_GREEN);
        
        try {            
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            String firstRow;
            String[] headers;
            String[] values;
            int totalRows = 0;
            
            // header row is ignored
            firstRow = br.readLine();
            
            // check header row exists
            headers = firstRow.split(",");
            if (headers.length != 4
                || !"Vocabulary".equals(headers[0])
                || !"Romaji".equals(headers[1])
                || !"Meaning".equals(headers[2])
                || !"Lesson".equals(headers[3])) {
                labelCSVImportPathInVocabularyPage.setText("Something is not right! Please try again later.");
                labelCSVImportPathInVocabularyPage.setForeground(Color.red);
                labelCSVImportPathInVocabularyPage.setFont(customFont);
                labelCsvInVocabularyPage.setText("");
                return;
            }
            
            // read from second line
            while((line = br.readLine()) != null) {
                values = line.split(",");
                
                Vocabulary vocabulary = new Vocabulary(values[0], values[1], values[2], values[3], false);
                vocabulary.insert();
                
                totalRows++;
            }
            
            // assumed importing is successfully completed
            labelCsvInVocabularyPage.setText("");
            labelCSVImportPathInVocabularyPage.setText("Successfully imported " + totalRows + " rows!");
            labelCSVImportPathInVocabularyPage.setForeground(VERY_DARK_GREEN);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImportCSVActionPerformed

    private void labelAppVersionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAppVersionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_labelAppVersionMouseClicked

    private void labelAppVersionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAppVersionMouseEntered
        panelVersionMoreInfo.setVisible(true);
    }//GEN-LAST:event_labelAppVersionMouseEntered

    private void labelAppVersionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAppVersionMouseExited
        panelVersionMoreInfo.setVisible(false);
    }//GEN-LAST:event_labelAppVersionMouseExited
    
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
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    new App().setVisible(true);
                } catch (IOException | FontFormatException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearSearchText;
    private javax.swing.JLabel btnCloseWindow;
    private javax.swing.JButton btnDeleteVocabulary;
    private javax.swing.JButton btnFavoriteVocabulary;
    private javax.swing.JButton btnGoVocabulary;
    private javax.swing.JButton btnImportCSV;
    private javax.swing.JLabel btnMinimizeWindow;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateVocabulary;
    private javax.swing.JLabel btnVocabulary;
    private javax.swing.JComboBox<String> cboxFavoriteLessonsInFavoritePage;
    private javax.swing.JComboBox<String> cboxLessonsInCreateUpdateVocabularyPage;
    private javax.swing.JComboBox<String> cboxLessonsInListPage;
    private javax.swing.JComboBox<String> cboxSearchBox;
    private javax.swing.JCheckBox checkBoxMarkAsFavoriteInVocabularyPage;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelAppInfoAbout;
    private javax.swing.JLabel labelAppInfoAck;
    private javax.swing.JLabel labelAppInfoAckBody;
    private javax.swing.JLabel labelAppInfoDevelopedTime;
    private javax.swing.JLabel labelAppInfoLanguage;
    private javax.swing.JLabel labelAppInfoUIComponents;
    private javax.swing.JLabel labelAppVersion;
    private javax.swing.JLabel labelCSVImportPathInVocabularyPage;
    private javax.swing.JLabel labelChooseLessonInVocabularyPage;
    private javax.swing.JLabel labelCsvInVocabularyPage;
    private javax.swing.JLabel labelDeveloperMail;
    private javax.swing.JLabel labelFilterByLessonInFavoritePage;
    private javax.swing.JLabel labelFilterByLessonInListPage;
    private javax.swing.JLabel labelHiraganaKatakanaInVocabularyPage;
    private javax.swing.JLabel labelIdInVocabularyPage;
    private javax.swing.JLabel labelImportVocabularyInVocabularyPage;
    private javax.swing.JLabel labelMeaningInVocabularyPage;
    private javax.swing.JLabel labelRomajiInVocabularyPage;
    private javax.swing.JLabel labelSearchInAllLessonsInListPage;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelTitleCreateUpdateInVocabularyPage;
    private javax.swing.JLabel labelTotalRowsInFavoritePage;
    private javax.swing.JLabel labelTotalRowsInListPage;
    private javax.swing.JLabel labelVocabularyCreatedOrUpdatedMessageInVocabularyPage;
    private javax.swing.JLabel labelVocabularyIdInVocabularyPage;
    private javax.swing.JPanel panelVersionMoreInfo;
    private javax.swing.JTabbedPane tabbedPaneVocabulary;
    private javax.swing.JTable tableFavoriteVocabularies;
    private javax.swing.JTable tableVocabularies;
    private javax.swing.JLabel txtRequiredMeaningInVocabularyPage;
    private javax.swing.JLabel txtRequiredNameInVocabularyPage;
    private javax.swing.JLabel txtRequiredRomajiInVocabularyPage;
    private javax.swing.JTextField txtSearchBoxInListPage;
    private javax.swing.JTextField txtVocabularyMeaningInVocabularyPage;
    private javax.swing.JTextField txtVocabularyNameInVocabularyPage;
    private javax.swing.JTextField txtVocabularyRomajiInVocabularyPage;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the btnDeleteVocabulary
     */
    public javax.swing.JButton getBtnDeleteVocabulary() {
        return btnDeleteVocabulary;
    }

    /**
     * @return the btnGoVocabulary
     */
    public javax.swing.JButton getBtnGoVocabulary() {
        return btnGoVocabulary;
    }

    /**
     * @return the btnSearch
     */
    public javax.swing.JButton getBtnSearch() {
        return btnSearch;
    }

    /**
     * @return the btnUpdateVocabulary
     */
    public javax.swing.JButton getBtnUpdateVocabulary() {
        return btnUpdateVocabulary;
    }

    /**
     * @return the btnVocabulary
     */
    public javax.swing.JLabel getBtnVocabulary() {
        return btnVocabulary;
    }

    /**
     * @return the cboxLessonInCreateVocabulary
     */
    public javax.swing.JComboBox<String> getCboxLessonInCreateVocabulary() {
        return cboxLessonsInCreateUpdateVocabularyPage;
    }

    /**
     * @return the cboxLessonList
     */
    public javax.swing.JComboBox<String> getCboxLessonList() {
        return cboxLessonsInListPage;
    }

    /**
     * @return the cboxSearchBox
     */
    public javax.swing.JComboBox<String> getCboxSearchBox() {
        return cboxSearchBox;
    }

    /**
     * @return the labelChooseLessonInVocaCreate
     */
    public javax.swing.JLabel getLabelChooseLessonInVocaCreate() {
        return labelChooseLessonInVocabularyPage;
    }

    /**
     * @return the labelCreateUpdateVocabulary
     */
    public javax.swing.JLabel getLabelCreateUpdateVocabulary() {
        return labelTitleCreateUpdateInVocabularyPage;
    }

    /**
     * @return the labelFilterByLessonInVocaList
     */
    public javax.swing.JLabel getLabelFilterByLessonInVocaList() {
        return labelFilterByLessonInListPage;
    }

    /**
     * @return the labelMeaningInVocaCreate
     */
    public javax.swing.JLabel getLabelMeaningInVocaCreate() {
        return labelMeaningInVocabularyPage;
    }

    /**
     * @return the labelRomajiInVocaCreate
     */
    public javax.swing.JLabel getLabelRomajiInVocaCreate() {
        return labelRomajiInVocabularyPage;
    }

    /**
     * @return the labelSearchInAllLessonsInVocaList
     */
    public javax.swing.JLabel getLabelSearchInAllLessonsInVocaList() {
        return labelSearchInAllLessonsInListPage;
    }

    /**
     * @return the labelTotalRows
     */
    public javax.swing.JLabel getLabelTotalRows() {
        return labelTotalRowsInListPage;
    }

    /**
     * @return the labelVocaInHiraganaKatakanaInVocaCreate
     */
    public javax.swing.JLabel getLabelVocaInHiraganaKatakanaInVocaCreate() {
        return labelHiraganaKatakanaInVocabularyPage;
    }

    /**
     * @return the labelVocabularyId
     */
    public javax.swing.JLabel getLabelVocabularyId() {
        return labelVocabularyIdInVocabularyPage;
    }

    /**
     * @return the msgCreateVocabulary
     */
    public javax.swing.JLabel getMsgCreateVocabulary() {
        return labelVocabularyCreatedOrUpdatedMessageInVocabularyPage;
    }

    /**
     * @return the tabbedPaneVocabulary
     */
    public javax.swing.JTabbedPane getTabbedPaneVocabulary() {
        return tabbedPaneVocabulary;
    }

    /**
     * @return the tableVocabularies
     */
    public javax.swing.JTable getTableVocabularies() {
        return tableVocabularies;
    }

    /**
     * @return the txtRequiredMeaning
     */
    public javax.swing.JLabel getTxtRequiredMeaning() {
        return txtRequiredMeaningInVocabularyPage;
    }

    /**
     * @return the txtRequiredName
     */
    public javax.swing.JLabel getTxtRequiredName() {
        return txtRequiredNameInVocabularyPage;
    }

    /**
     * @return the txtRequiredRomaji
     */
    public javax.swing.JLabel getTxtRequiredRomaji() {
        return txtRequiredRomajiInVocabularyPage;
    }

    /**
     * @return the txtSearchBox
     */
    public javax.swing.JTextField getTxtSearchBox() {
        return txtSearchBoxInListPage;
    }

    /**
     * @return the txtVocabularyMeaning
     */
    public javax.swing.JTextField getTxtVocabularyMeaning() {
        return txtVocabularyMeaningInVocabularyPage;
    }

    /**
     * @return the txtVocabularyName
     */
    public javax.swing.JTextField getTxtVocabularyName() {
        return txtVocabularyNameInVocabularyPage;
    }

    /**
     * @return the txtVocabularyRomaji
     */
    public javax.swing.JTextField getTxtVocabularyRomaji() {
        return txtVocabularyRomajiInVocabularyPage;
    }
    
     /**
     * @return the jpFont
     */
    public Font getJpFont() {
        return jpFont;
    }

    /**
     * @param jpFont the jpFont to set
     */
    public void setJpFont(Font jpFont) {
        this.jpFont = jpFont;
    }

    /**
     * @return the myanmarFont
     */
    public Font getMyanmarFont() {
        return myanmarFont;
    }

    /**
     * @param myanmarFont the myanmarFont to set
     */
    public void setMyanmarFont(Font myanmarFont) {
        this.myanmarFont = myanmarFont;
    }
}
