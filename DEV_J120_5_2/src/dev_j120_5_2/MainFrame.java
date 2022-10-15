/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j120_5_2;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class MainFrame extends JFrame {

    File fileDir;
    private static List<String> primaryList = new ArrayList<>();
    private static String separator = ",";
    private static String titleTable = "";
    private Object[] columnHeaders;
    private Object[][] rowData;

    public MainFrame(){
        setTitle("Просмотр CSV-файлов");
        setBounds(300,150,600,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
    }

    private void init() {
        addMenu();
    }


    private void addMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Open dir");
        menuBar.add(menu);
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            chooseDir();
        });
    }

    private void chooseDir() {
        JFileChooser chooser = new JFileChooser();
        int ch = chooser.showDialog(this, "Upload a file");
        if(ch == JFileChooser.APPROVE_OPTION){
            fileDir = chooser.getSelectedFile();
            try {
                primaryList = FileReader.fileReader(fileDir.toPath());
            } catch (IOException | NullPointerException | SecurityException ex) {
                JOptionPane.showMessageDialog(this,
                        "The file is missing or access to it is prohibited.", "Error. file access error.",
                        JOptionPane.ERROR_MESSAGE);
            }
            titleTable = fileDir.getName();
            TableData tableData = new TableData(separator);
            columnHeaders = tableData.columnHeadersMaker();
            rowData = tableData.rowDataMaker();
            new TableFrame(rowData, columnHeaders).setVisible(true);
        }
    }

    public static String getTitleTable() {
        return titleTable;
    }
    public static List<String> getPrimaryList() {
        return primaryList;
    }
}
