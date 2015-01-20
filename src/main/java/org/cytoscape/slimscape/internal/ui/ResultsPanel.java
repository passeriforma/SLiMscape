package org.cytoscape.slimscape.internal.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class ResultsPanel extends JPanel {
    JTable table;
    List<String> input;
    String columnNames[];
    Object[][] rowData;

    // Make it generic; it doesnt need to know the contents of the list its been passed, just that the second line is
    // the column names and the ones below are all CSV data.

    public ResultsPanel (List<String> input) {
        // this.input = input;

        setBackground(new Color(238, 238, 238));

        // Get column names from input
        columnNames = new String[]{"", "title"};
        rowData = new Object[][]{{new Boolean(true), "hi"}, {true, "there"}};

        JCheckBox button = new JCheckBox();

        AbstractTableModel model = new AbstractTableModel() {
            public String getColumnName(int col) {
                return columnNames[col].toString();
            }
            public int getRowCount() { return rowData.length; }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) {
                return rowData[row][col];
            }
            public boolean isCellEditable(int row, int col) {
                return col < 1;
            }
            public void setValueAt(Object value, int row, int col) {
                rowData[row][col] = value;
                fireTableCellUpdated(row, col);
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
        };

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Number of rows
        int rows = 3; //TODO: calculate from data

        JScrollPane scroll = new JScrollPane(table); // TODO: Set size parameters for this, as well as scrolling
        table.setFillsViewportHeight(true);

        this.add(scroll);



    }


}
