package compiler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class UI extends JFrame {
//************************** Components Sizeing ********************************

    final private Insets outerMargin = new Insets(4, 8, 4, 8);

    final private Dimension error_listDimention = new Dimension(0, 150);
    final private Dimension compile_btnDimention = new Dimension(0, 50);

    final private int innerHorizontalGap = 8;
    final private int innerVerticalGap = 8;
//******************************************************************************
//***************************** Global Area ************************************
    final private JButton compile_button = new JButton("Start Compilation!");
    final private JTextArea code_textArea = new JTextArea();
    final private JList error_list = new JList();

    final private String tableHeader[] = {"Lexeme", "Token Class"};
    final private JTable token_Table = new JTable();
    
    private ActionListener buttonListener;
//******************************************************************************

    public UI() {
        super("Compiler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

//************************** left panel drawing ********************************
        JPanel left_panel = new JPanel();
        left_panel.setLayout(new BorderLayout(innerHorizontalGap, innerVerticalGap));

        JScrollPane scrollPane1 = new JScrollPane(code_textArea);
        scrollPane1.setBorder(new TitledBorder("JASON Code"));
        left_panel.add(scrollPane1, BorderLayout.CENTER);

        compile_button.setPreferredSize(compile_btnDimention);
        left_panel.add(compile_button, BorderLayout.SOUTH);
        //************** Constrains *********************
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = outerMargin;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        //***********************************************
        add(left_panel, constraints);
//******************************************************************************
//************************** Right panel drawing *******************************
        JPanel right_panel = new JPanel();
        right_panel.setLayout(new BorderLayout(innerHorizontalGap, innerVerticalGap));

        DefaultTableModel tableModel = new DefaultTableModel(tableHeader,0);
        token_Table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(token_Table);
        scrollPane.setBorder(new TitledBorder("Token Classes"));
        right_panel.add(scrollPane, BorderLayout.CENTER);

        error_list.setPreferredSize(error_listDimention);
        JScrollPane error_scrollPane = new JScrollPane(error_list);
        error_scrollPane.setBorder(new TitledBorder("Error list"));

        right_panel.add(error_scrollPane, BorderLayout.SOUTH);
        //************** Constrains *********************
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 2;
        constraints.weighty = 1;
        //***********************************************
        add(right_panel, constraints);
//******************************************************************************

        setSize(1280, 720);
        setVisible(true);
    }

    void setActionListener(ActionListener listener) {
        buttonListener = listener;
        compile_button.addActionListener(buttonListener);
    }

    void setLexemeTable(Object [][] data) {
        DefaultTableModel tableModel = new DefaultTableModel(data, tableHeader);
        token_Table.setModel(tableModel);
    }

    void setErrorList(Object [] errorList) {
        error_list.setListData(errorList);
    }
    
    void setLexemeTable(ArrayList<ArrayList<String>> data) {
        //******************* Convert ArrayList to 2D Array ********************
        Object[][] ScannerData = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            ArrayList<String> row = data.get(i);
            ScannerData[i] = row.toArray();
        }
        //**********************************************************************
        DefaultTableModel tableModel = new DefaultTableModel(ScannerData, tableHeader);
        token_Table.setModel(tableModel);
    }

    void setErrorList(ArrayList<String> errorList) {
        error_list.setListData(errorList.toArray());
    }

    String getCode() {
        return code_textArea.getText();
    }
}
