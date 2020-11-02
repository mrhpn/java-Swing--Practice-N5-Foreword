/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtetPhyoNaing;

import HtetPhyoNaing.Model.Vocabulary;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private Font leadFont;
    private final Practice practicePanel;
    
    private Color VERY_DARK_GREEN = new Color(0,153,0);

    /**
     * Creates new form App
     */
    public App() throws IOException, FontFormatException {
        initComponents();
        
        this.setShape(new RoundRectangle2D.Double(0, 0, 1000, 620, 20, 20));
        
        practicePanel = new Practice();
        tabbedPaneVocabulary.addTab("Practice", practicePanel);
        
        configureShowOnTableRowSelectionButtons();
        setUpTable();
        setUpFonts();
        panelVersionMoreInfo.setVisible(false);
    }
    
    private void configureShowOnTableRowSelectionButtons() {
        btnUpdateVocabulary.setVisible(false);
        btnDeleteVocabulary.setVisible(false);
        btnFavoriteVocabulary.setVisible(false);
                    
        ListSelectionModel listSelectionModel = tableVocabularies.getSelectionModel();
        listSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            if(!listSelectionModel.isSelectionEmpty()) {
                btnUpdateVocabulary.setVisible(true);
                btnDeleteVocabulary.setVisible(true);
                btnFavoriteVocabulary.setVisible(true);
                
                int selectedRow = tableVocabularies.getSelectedRow();
                TableModel tableModel = tableVocabularies.getModel();
                Integer id = (Integer) tableModel.getValueAt(selectedRow, 0);
                
                Vocabulary vocabulary = new Vocabulary();
                boolean isFavorite = vocabulary.isFavorite(id);
                if (isFavorite) {
                    btnFavoriteVocabulary.setText("Favorited");
                    btnFavoriteVocabulary.setIcon(new ImageIcon("src\\HtetPhyoNaing\\Resources\\Images\\img-favoried.png"));
                } else {
                    btnFavoriteVocabulary.setText("Favorite");
                    btnFavoriteVocabulary.setIcon(new ImageIcon("src\\HtetPhyoNaing\\Resources\\Images\\img-btn-favorite.png"));
                }
                
            } else {
                btnUpdateVocabulary.setVisible(false);
                btnDeleteVocabulary.setVisible(false);
                btnFavoriteVocabulary.setVisible(false);
            }
        });
    }
    
    private void setUpTable() {
        tableVocabularies.getColumnModel().getColumn(0).setMinWidth(0);
        tableVocabularies.getColumnModel().getColumn(0).setMaxWidth(0);
        tableVocabularies.getColumnModel().getColumn(0).setWidth(0);
        
        try {
            customFont = Font.createFont(
                    Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\cerebrisans-regular.ttf")).deriveFont(14f);
            jpFont = Font.createFont(
                    Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\MHGKyokashotaiTHK-Light.ttf")).deriveFont(22f);
            myanmarFont = Font.createFont(
                    Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\Pyidaungsu-1.8_regular.ttf")).deriveFont(15f);
            
            titleFont = customFont.deriveFont(18f);
            subTitleFont = customFont.deriveFont(13f);
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            
            jpFont.deriveFont(Font.BOLD);
            
            tableCellRendererForJp = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setFont(jpFont);
                    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                    return this;
                }
            };
            
            tableCellRendererForRomoji = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setFont(customFont);
                    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                    return this;
                }
            };
            
            tableCellRendererForMm = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setFont(myanmarFont);
                    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                    return this;
                }
            };
            
            tableVocabularies.getColumnModel().getColumn(1).setCellRenderer(tableCellRendererForJp);
            tableVocabularies.getColumnModel().getColumn(2).setCellRenderer(tableCellRendererForRomoji);
            tableVocabularies.getColumnModel().getColumn(3).setCellRenderer(tableCellRendererForMm);
            
            txtSearchBox.setFont(jpFont);
        } catch (IOException | FontFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void setUpFavTable() {
        tableFavoriteVocabularies.getColumnModel().getColumn(0).setMinWidth(0);
        tableFavoriteVocabularies.getColumnModel().getColumn(0).setMaxWidth(0);
        tableFavoriteVocabularies.getColumnModel().getColumn(0).setWidth(0);
        
        try {
            customFont = Font.createFont(
                    Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\cerebrisans-regular.ttf")).deriveFont(14f);
            jpFont = Font.createFont(
                    Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\MHGKyokashotaiTHK-Light.ttf")).deriveFont(22f);
            myanmarFont = Font.createFont(
                    Font.TRUETYPE_FONT, new File("src\\HtetPhyoNaing\\Resources\\Fonts\\Pyidaungsu-2.5.3_Regular.ttf")).deriveFont(15f);
            
            titleFont = customFont.deriveFont(18f);
            subTitleFont = customFont.deriveFont(13f);
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            
            tableCellRendererForJp = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setFont(jpFont);
                    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                    return this;
                }
            };
            
            tableCellRendererForRomoji = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setFont(customFont);
                    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                    return this;
                }
            };
            
            tableCellRendererForMm = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setFont(myanmarFont);
                    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                    return this;
                }
            };
            
            tableFavoriteVocabularies.getColumnModel().getColumn(1).setCellRenderer(tableCellRendererForJp);
            tableFavoriteVocabularies.getColumnModel().getColumn(2).setCellRenderer(tableCellRendererForRomoji);
            tableFavoriteVocabularies.getColumnModel().getColumn(3).setCellRenderer(tableCellRendererForMm);
        } catch (IOException | FontFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void setUpFonts() {
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
        btnFavoriteVocabulary.setFont(customFont);
        labelChooseLessonInVocaCreate.setFont(customFont);
        cboxLessonInCreateVocabulary.setFont(customFont);
        labelVocaInHiraganaKatakanaInVocaCreate.setFont(customFont);
        labelRomajiInVocaCreate.setFont(customFont);
        // labelPronounciationInVocaCreate.setFont(customFont);
        labelMeaningInVocaCreate.setFont(customFont);
        btnGoVocabulary.setFont(customFont);
        txtVocabularyName.setFont(jpFont);
        labelId.setFont(customFont);
        labelVocabularyId.setFont(customFont);            
        msgCreateVocabulary.setFont(customFont);
        checkBoxMarkAsFavorite.setFont(customFont);
        labelTotalRowsFav.setFont(customFont);
        labelFilterByLessonInFavVocaList.setFont(customFont);
        labelImportVocabulary.setFont(titleFont);
        btnImportCSV.setFont(customFont);
        labelCSVImportPath.setFont(customFont);
        cboxFavLessonList.setFont(customFont);
        txtVocabularyMeaning.setFont(myanmarFont);
        labelDeveloperMail.setFont(subTitleFont);
        labelAppVersion.setFont(subTitleFont);
        txtSearchBox.setFont(customFont);
        
        txtRequiredName.setFont(subTitleFont);
        txtRequiredRomaji.setFont(subTitleFont);
        txtRequiredMeaning.setFont(subTitleFont);
        
        labelCsv.setFont(customFont);
        
        labelAppInfoLanguage.setFont(customFont);
        labelAppInfoUIComponents.setFont(customFont);
        labelAppInfoDevelopedTime.setFont(customFont);
        labelAppInfoAbout.setFont(customFont);
        labelAppInfoAck.setFont(customFont);
        labelAppInfoAckBody.setFont(customFont);
        
        practicePanel.getLabelPracticeChooseTypeOfPractice().setFont(customFont);
        practicePanel.getLabelPracticeChooseTypeOfPracticeFrom().setFont(customFont);
        practicePanel.getLabelPracticeChooseTypeOfPracticeInfoMsg().setFont(customFont);
        practicePanel.getLabelPracticeChooseTypeOfPracticeTo().setFont(customFont);
        practicePanel.getLabelPracticeTotalRows().setFont(customFont);
        practicePanel.getCboxPracticeChooseAllOrFav().setFont(customFont);
        practicePanel.getCboxPracticeFromLesson().setFont(customFont);
        practicePanel.getCboxPracticeLessonTo().setFont(customFont);
        practicePanel.getBtnPracticeVoca().setFont(customFont);
        practicePanel.getLabelPracticeIWantToSee().setFont(customFont);
        practicePanel.getCboxPracticeFromWantToSee().setFont(customFont);
        practicePanel.getLabelPracticeVocaJPAnswer().setFont(jpFont);
        practicePanel.getLabelPracticeVocaMMAnswer().setFont(myanmarFont);
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
        btnFavoriteVocabulary = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        cboxFavLessonList = new javax.swing.JComboBox<>();
        labelFilterByLessonInFavVocaList = new javax.swing.JLabel();
        labelTotalRowsFav = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableFavoriteVocabularies = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        labelChooseLessonInVocaCreate = new javax.swing.JLabel();
        txtVocabularyName = new javax.swing.JTextField();
        labelVocaInHiraganaKatakanaInVocaCreate = new javax.swing.JLabel();
        labelRomajiInVocaCreate = new javax.swing.JLabel();
        labelMeaningInVocaCreate = new javax.swing.JLabel();
        btnGoVocabulary = new javax.swing.JButton();
        txtVocabularyRomaji = new javax.swing.JTextField();
        cboxLessonInCreateVocabulary = new javax.swing.JComboBox<>();
        labelCreateUpdateVocabulary = new javax.swing.JLabel();
        msgCreateVocabulary = new javax.swing.JLabel();
        txtRequiredName = new javax.swing.JLabel();
        txtVocabularyMeaning = new javax.swing.JTextField();
        txtRequiredMeaning = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        labelVocabularyId = new javax.swing.JLabel();
        checkBoxMarkAsFavorite = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        labelImportVocabulary = new javax.swing.JLabel();
        btnImportCSV = new javax.swing.JButton();
        labelCSVImportPath = new javax.swing.JLabel();
        txtRequiredRomaji = new javax.swing.JLabel();
        labelCsv = new javax.swing.JLabel();
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

        txtSearchBox.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBoxActionPerformed(evt);
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
        jScrollPane1.setViewportView(tableVocabularies);

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
        cboxSearchBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Romaji", "Japanese" }));
        cboxSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxSearchBoxActionPerformed(evt);
            }
        });

        labelTotalRows.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        labelTotalRows.setText("jLabel1");

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

        labelFilterByLessonInVocaList.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelFilterByLessonInVocaList.setForeground(new java.awt.Color(94, 0, 126));
        labelFilterByLessonInVocaList.setText("Filter by Lesson");
        labelFilterByLessonInVocaList.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        labelSearchInAllLessonsInVocaList.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelSearchInAllLessonsInVocaList.setForeground(new java.awt.Color(94, 0, 126));
        labelSearchInAllLessonsInVocaList.setText("Search in All Lessons");
        labelSearchInAllLessonsInVocaList.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

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
                                .addComponent(labelTotalRows, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboxLessonList, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(labelFilterByLessonInVocaList, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelSearchInAllLessonsInVocaList, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(cboxSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(labelTotalRows, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(cboxSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFilterByLessonInVocaList, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSearchInAllLessonsInVocaList))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxLessonList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        cboxFavLessonList.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxFavLessonList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Lesson 16", "Lesson 17", "Lesson 18", "Lesson 19", "Lesson 20", "Lesson 21", "Lesson 22", "Lesson 23", "Lesson 24", "Lesson 25" }));
        cboxFavLessonList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxFavLessonListActionPerformed(evt);
            }
        });
        cboxFavLessonList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboxFavLessonListPropertyChange(evt);
            }
        });

        labelFilterByLessonInFavVocaList.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelFilterByLessonInFavVocaList.setForeground(new java.awt.Color(94, 0, 126));
        labelFilterByLessonInFavVocaList.setText("Filter by Lesson");
        labelFilterByLessonInFavVocaList.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        labelTotalRowsFav.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        labelTotalRowsFav.setText("jLabel1");

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
        jScrollPane3.setViewportView(tableFavoriteVocabularies);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labelTotalRowsFav, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(447, 447, 447)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cboxFavLessonList, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(labelFilterByLessonInFavVocaList))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(277, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTotalRowsFav, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labelFilterByLessonInFavVocaList, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxFavLessonList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
        );

        tabbedPaneVocabulary.addTab("Favorites", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelChooseLessonInVocaCreate.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelChooseLessonInVocaCreate.setForeground(new java.awt.Color(94, 0, 126));
        labelChooseLessonInVocaCreate.setText("Choose Lesson");
        jPanel8.add(labelChooseLessonInVocaCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        txtVocabularyName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtVocabularyName.setMinimumSize(new java.awt.Dimension(6, 31));
        txtVocabularyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyNameActionPerformed(evt);
            }
        });
        jPanel8.add(txtVocabularyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 284, 40));

        labelVocaInHiraganaKatakanaInVocaCreate.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelVocaInHiraganaKatakanaInVocaCreate.setForeground(new java.awt.Color(94, 0, 126));
        labelVocaInHiraganaKatakanaInVocaCreate.setText("Vocabulary in Hiragana/Katakana");
        jPanel8.add(labelVocaInHiraganaKatakanaInVocaCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        labelRomajiInVocaCreate.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelRomajiInVocaCreate.setForeground(new java.awt.Color(94, 0, 126));
        labelRomajiInVocaCreate.setText("Romaji");
        jPanel8.add(labelRomajiInVocaCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, -1, -1));

        labelMeaningInVocaCreate.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelMeaningInVocaCreate.setForeground(new java.awt.Color(94, 0, 126));
        labelMeaningInVocaCreate.setText("Meaning");
        jPanel8.add(labelMeaningInVocaCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

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

        txtVocabularyRomaji.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtVocabularyRomaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyRomajiActionPerformed(evt);
            }
        });
        jPanel8.add(txtVocabularyRomaji, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 287, 40));

        cboxLessonInCreateVocabulary.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        cboxLessonInCreateVocabulary.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5", "Lesson 6", "Lesson 7", "Lesson 8", "Lesson 8", "Lesson 9", "Lesson 10", "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 14", "Lesson 15", "Lesson 16", "Lesson 17", "Lesson 18", "Lesson 19", "Lesson 20", "Lesson 21", "Lesson 22", "Lesson 23", "Lesson 24", "Lesson 25" }));
        cboxLessonInCreateVocabulary.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cboxLessonInCreateVocabulary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxLessonInCreateVocabularyActionPerformed(evt);
            }
        });
        jPanel8.add(cboxLessonInCreateVocabulary, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 94, 32));

        labelCreateUpdateVocabulary.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        labelCreateUpdateVocabulary.setText("Create/Update Vocabulary");
        jPanel8.add(labelCreateUpdateVocabulary, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 26, -1, -1));

        msgCreateVocabulary.setBackground(new java.awt.Color(0, 204, 51));
        msgCreateVocabulary.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        msgCreateVocabulary.setForeground(new java.awt.Color(0, 204, 51));
        jPanel8.add(msgCreateVocabulary, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 246, 29));

        txtRequiredName.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredName.setForeground(new java.awt.Color(255, 51, 51));
        jPanel8.add(txtRequiredName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 86, 15));

        txtVocabularyMeaning.setFont(new java.awt.Font("Pyidaungsu", 0, 14)); // NOI18N
        txtVocabularyMeaning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVocabularyMeaningActionPerformed(evt);
            }
        });
        jPanel8.add(txtVocabularyMeaning, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 284, 40));

        txtRequiredMeaning.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredMeaning.setForeground(new java.awt.Color(255, 51, 51));
        jPanel8.add(txtRequiredMeaning, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 86, 14));

        labelId.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        labelId.setText("Id:");
        jPanel8.add(labelId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 28, 38));

        labelVocabularyId.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        labelVocabularyId.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel8.add(labelVocabularyId, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 36, 38));

        checkBoxMarkAsFavorite.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxMarkAsFavorite.setForeground(new java.awt.Color(94, 0, 126));
        checkBoxMarkAsFavorite.setText("Mark as favorite");
        checkBoxMarkAsFavorite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxMarkAsFavoriteActionPerformed(evt);
            }
        });
        jPanel8.add(checkBoxMarkAsFavorite, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 139, 31));
        jPanel8.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 769, 10));

        labelImportVocabulary.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        labelImportVocabulary.setText("Bulk Create Vocabulary");
        jPanel8.add(labelImportVocabulary, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

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

        labelCSVImportPath.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        labelCSVImportPath.setMaximumSize(new java.awt.Dimension(400, 0));
        labelCSVImportPath.setPreferredSize(new java.awt.Dimension(200, 0));
        jPanel8.add(labelCSVImportPath, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 461, 430, 20));

        txtRequiredRomaji.setFont(new java.awt.Font("Century", 2, 12)); // NOI18N
        txtRequiredRomaji.setForeground(new java.awt.Color(255, 51, 51));
        jPanel8.add(txtRequiredRomaji, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 86, 15));

        labelCsv.setForeground(new java.awt.Color(0, 153, 51));
        jPanel8.add(labelCsv, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 444, 250, 20));

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
                int selectedLesson = cboxLessonList.getSelectedIndex() + 1;
                if ("".equals(txtSearchBox.getText())) {
                    new Vocabulary().repaintTable(tableVocabularies, getLabelTotalRows(), "ASC", selectedLesson);
                    setUpTable();
                }
                break;
                //haha
            case 1:
                int selectedFavLesson = cboxFavLessonList.getSelectedIndex();
                vocabulary = new Vocabulary();
                vocabulary.repaintFavTable(tableFavoriteVocabularies, labelTotalRowsFav, selectedFavLesson);
                setUpFavTable();
                break;
            case 2:
                labelId.setVisible(false);
                getLabelVocabularyId().setVisible(false);
                getTxtVocabularyName().requestFocus();
                labelCSVImportPath.setText("");
                labelCsv.setText("");
                break;
            default:
                break;
        }

        labelId.setVisible(false);
        clearLabelsAndTextFieldsInCreateVoca();
    }//GEN-LAST:event_tabbedPaneVocabularyStateChanged

    
    private void checkBoxMarkAsFavoriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxMarkAsFavoriteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxMarkAsFavoriteActionPerformed

    private void txtVocabularyMeaningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyMeaningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyMeaningActionPerformed

    private void cboxLessonInCreateVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxLessonInCreateVocabularyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxLessonInCreateVocabularyActionPerformed

    private void txtVocabularyRomajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyRomajiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyRomajiActionPerformed

    private void btnGoVocabularyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoVocabularyActionPerformed
        String id =  getLabelVocabularyId().getText();
        String name = getTxtVocabularyName().getText();
        String romaji = getTxtVocabularyRomaji().getText();
        String meaning = getTxtVocabularyMeaning().getText();
        String lessonId = getCboxLessonInCreateVocabulary().getSelectedIndex() + 1 + "";
        Boolean isFavorite = checkBoxMarkAsFavorite.isSelected();

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

    private void txtVocabularyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVocabularyNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVocabularyNameActionPerformed

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
                btnFavoriteVocabulary.setIcon(new ImageIcon("src\\HtetPhyoNaing\\Resources\\Images\\img-btn-favorite.png"));
            } else {
                btnFavoriteVocabulary.setText("Favorited");
                btnFavoriteVocabulary.setIcon(new ImageIcon("src\\HtetPhyoNaing\\Resources\\Images\\img-favoried.png"));
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
        labelId.setVisible(true);
        getLabelVocabularyId().setVisible(true);
        getLabelVocabularyId().setText(id.toString());
        checkBoxMarkAsFavorite.setSelected(isFavorite);
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

            setUpTable();
        }
    }//GEN-LAST:event_btnDeleteVocabularyActionPerformed

    private void btnClearSearchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSearchTextActionPerformed
        getTxtSearchBox().setText("");
        int selectedLesson = cboxLessonList.getSelectedIndex() + 1;
        
        new Vocabulary().repaintTable(tableVocabularies, getLabelTotalRows(), "ASC", selectedLesson);
        setUpTable();
    }//GEN-LAST:event_btnClearSearchTextActionPerformed

    private void cboxSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxSearchBoxActionPerformed
        txtSearchBox.setText("");
        int lessonId = cboxLessonList.getSelectedIndex() + 1;
        int searchKey = cboxSearchBox.getSelectedIndex(); // 0 - Romaji, 1 - Jp, 2 - Mm
        
        txtSearchBox.requestFocus();
        
        if (searchKey == 0) txtSearchBox.setFont(new Font("src\\HtetPhyoNaing\\Resources\\Fonts\\cerebrisans-regular.ttf", 1, 20));
        else if (searchKey == 1) txtSearchBox.setFont(new Font("src\\HtetPhyoNaing\\Resources\\FontsMHGKyokashotaiTHK-Light.ttf", 1, 20));
                
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintTable(tableVocabularies, labelTotalRows, "ASC", lessonId);
        
        setUpTable();
    }//GEN-LAST:event_cboxSearchBoxActionPerformed

    private void cboxLessonListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboxLessonListPropertyChange

    }//GEN-LAST:event_cboxLessonListPropertyChange

    private void cboxLessonListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxLessonListActionPerformed
        int selectedLesson = getCboxLessonList().getSelectedIndex() + 1;

        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintTable(getTableVocabularies(), getLabelTotalRows(), "ASC", selectedLesson);

        setUpTable();
    }//GEN-LAST:event_cboxLessonListActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        int searchKey = getCboxSearchBox().getSelectedIndex();
        String searchValue = getTxtSearchBox().getText();
        
        System.out.println(searchValue);

        if ("".equals(searchValue)) {
            getTxtSearchBox().requestFocus();
            return;
        }

        String targetColumn = "";
        if (searchKey == 0) targetColumn = "romaji";
        else if (searchKey == 1) targetColumn = "name";

        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintTable(getTableVocabularies(), getLabelTotalRows(), targetColumn, searchValue);

        setUpTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchBoxActionPerformed

    private void cboxFavLessonListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboxFavLessonListPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxFavLessonListPropertyChange

    private void cboxFavLessonListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxFavLessonListActionPerformed
        int selectedLesson = cboxFavLessonList.getSelectedIndex();
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.repaintFavTable(tableFavoriteVocabularies, labelTotalRowsFav, selectedLesson);
        setUpFavTable();
    }//GEN-LAST:event_cboxFavLessonListActionPerformed

    private void btnCloseWindowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseEntered
        btnCloseWindow.setIcon(new ImageIcon("src\\HtetPhyoNaing\\Resources\\Images\\img-btn-close.png"));
    }//GEN-LAST:event_btnCloseWindowMouseEntered

    private void btnCloseWindowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseWindowMouseExited
        btnCloseWindow.setIcon(new ImageIcon("src\\HtetPhyoNaing\\Resources\\Images\\close.png"));
    }//GEN-LAST:event_btnCloseWindowMouseExited

    private void btnMinimizeWindowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeWindowMouseEntered
       btnMinimizeWindow.setIcon(new ImageIcon("src\\HtetPhyoNaing\\Resources\\Images\\img-btn-minimize.png"));
    }//GEN-LAST:event_btnMinimizeWindowMouseEntered

    private void btnMinimizeWindowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeWindowMouseExited
       btnMinimizeWindow.setIcon(new ImageIcon("src\\HtetPhyoNaing\\Resources\\Images\\img-btn-minimize_1.png"));
    }//GEN-LAST:event_btnMinimizeWindowMouseExited

    private void btnImportCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportCSVActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        
        String filePath = file.getAbsolutePath();
        if(filePath.length() > 50) {
            labelCSVImportPath.setText("..." + filePath.substring(filePath.length() - 50));
        } else {
            labelCSVImportPath.setText(filePath);
        }
        labelCSVImportPath.setForeground(Color.BLACK);
        labelCsv.setText("Reading and importing data from...");
        labelCsv.setForeground(VERY_DARK_GREEN);
        
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
                labelCSVImportPath.setText("Something is not right! Please try again later.");
                labelCSVImportPath.setForeground(Color.red);
                labelCSVImportPath.setFont(customFont);
                labelCsv.setText("");
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
            labelCsv.setText("");
            labelCSVImportPath.setText("Successfully imported " + totalRows + " rows!");
            labelCSVImportPath.setForeground(VERY_DARK_GREEN);
            
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
    private javax.swing.JButton btnFavoriteVocabulary;
    private javax.swing.JButton btnGoVocabulary;
    private javax.swing.JButton btnImportCSV;
    private javax.swing.JLabel btnMinimizeWindow;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateVocabulary;
    private javax.swing.JLabel btnVocabulary;
    private javax.swing.JComboBox<String> cboxFavLessonList;
    private javax.swing.JComboBox<String> cboxLessonInCreateVocabulary;
    private javax.swing.JComboBox<String> cboxLessonList;
    private javax.swing.JComboBox<String> cboxSearchBox;
    private javax.swing.JCheckBox checkBoxMarkAsFavorite;
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
    private javax.swing.JLabel labelCSVImportPath;
    private javax.swing.JLabel labelChooseLessonInVocaCreate;
    private javax.swing.JLabel labelCreateUpdateVocabulary;
    private javax.swing.JLabel labelCsv;
    private javax.swing.JLabel labelDeveloperMail;
    private javax.swing.JLabel labelFilterByLessonInFavVocaList;
    private javax.swing.JLabel labelFilterByLessonInVocaList;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelImportVocabulary;
    private javax.swing.JLabel labelMeaningInVocaCreate;
    private javax.swing.JLabel labelRomajiInVocaCreate;
    private javax.swing.JLabel labelSearchInAllLessonsInVocaList;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelTotalRows;
    private javax.swing.JLabel labelTotalRowsFav;
    private javax.swing.JLabel labelVocaInHiraganaKatakanaInVocaCreate;
    private javax.swing.JLabel labelVocabularyId;
    private javax.swing.JLabel msgCreateVocabulary;
    private javax.swing.JPanel panelVersionMoreInfo;
    private javax.swing.JTabbedPane tabbedPaneVocabulary;
    private javax.swing.JTable tableFavoriteVocabularies;
    private javax.swing.JTable tableVocabularies;
    private javax.swing.JLabel txtRequiredMeaning;
    private javax.swing.JLabel txtRequiredName;
    private javax.swing.JLabel txtRequiredRomaji;
    private javax.swing.JTextField txtSearchBox;
    private javax.swing.JTextField txtVocabularyMeaning;
    private javax.swing.JTextField txtVocabularyName;
    private javax.swing.JTextField txtVocabularyRomaji;
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
        return cboxLessonInCreateVocabulary;
    }

    /**
     * @return the cboxLessonList
     */
    public javax.swing.JComboBox<String> getCboxLessonList() {
        return cboxLessonList;
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
        return labelChooseLessonInVocaCreate;
    }

    /**
     * @return the labelCreateUpdateVocabulary
     */
    public javax.swing.JLabel getLabelCreateUpdateVocabulary() {
        return labelCreateUpdateVocabulary;
    }

    /**
     * @return the labelFilterByLessonInVocaList
     */
    public javax.swing.JLabel getLabelFilterByLessonInVocaList() {
        return labelFilterByLessonInVocaList;
    }

    /**
     * @return the labelMeaningInVocaCreate
     */
    public javax.swing.JLabel getLabelMeaningInVocaCreate() {
        return labelMeaningInVocaCreate;
    }

    /**
     * @return the labelRomajiInVocaCreate
     */
    public javax.swing.JLabel getLabelRomajiInVocaCreate() {
        return labelRomajiInVocaCreate;
    }

    /**
     * @return the labelSearchInAllLessonsInVocaList
     */
    public javax.swing.JLabel getLabelSearchInAllLessonsInVocaList() {
        return labelSearchInAllLessonsInVocaList;
    }

    /**
     * @return the labelTotalRows
     */
    public javax.swing.JLabel getLabelTotalRows() {
        return labelTotalRows;
    }

    /**
     * @return the labelVocaInHiraganaKatakanaInVocaCreate
     */
    public javax.swing.JLabel getLabelVocaInHiraganaKatakanaInVocaCreate() {
        return labelVocaInHiraganaKatakanaInVocaCreate;
    }

    /**
     * @return the labelVocabularyId
     */
    public javax.swing.JLabel getLabelVocabularyId() {
        return labelVocabularyId;
    }

    /**
     * @return the msgCreateVocabulary
     */
    public javax.swing.JLabel getMsgCreateVocabulary() {
        return msgCreateVocabulary;
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
        return txtRequiredMeaning;
    }

    /**
     * @return the txtRequiredName
     */
    public javax.swing.JLabel getTxtRequiredName() {
        return txtRequiredName;
    }

    /**
     * @return the txtRequiredRomaji
     */
    public javax.swing.JLabel getTxtRequiredRomaji() {
        return txtRequiredRomaji;
    }

    /**
     * @return the txtSearchBox
     */
    public javax.swing.JTextField getTxtSearchBox() {
        return txtSearchBox;
    }

    /**
     * @return the txtVocabularyMeaning
     */
    public javax.swing.JTextField getTxtVocabularyMeaning() {
        return txtVocabularyMeaning;
    }

    /**
     * @return the txtVocabularyName
     */
    public javax.swing.JTextField getTxtVocabularyName() {
        return txtVocabularyName;
    }

    /**
     * @return the txtVocabularyRomaji
     */
    public javax.swing.JTextField getTxtVocabularyRomaji() {
        return txtVocabularyRomaji;
    }
}
