/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j120_5_2;

/**
 *
 * @author USER
 */
import javax.swing.*;
import java.awt.*;

public class TableFrame extends JFrame {
    private final Object[] columnHeaders;
    private final Object[][] rowData;


    public TableFrame(Object[][] rowData, Object[] columnHeaders) {
        super(MainFrame.getTitleTable());
        this.rowData = rowData;
        this.columnHeaders = columnHeaders;

        init();
    }

    private void init() {
        setBounds(300,150,780,300);
        JTable table = new JTable(rowData, columnHeaders);
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for(int i = 0; i < columnHeaders.length; i++) {
            table.getColumnModel().getColumn(i);
            table.getColumnModel().getColumn(i).setMinWidth(150);
        }
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setRowHeight(27);
        add(scrollPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
