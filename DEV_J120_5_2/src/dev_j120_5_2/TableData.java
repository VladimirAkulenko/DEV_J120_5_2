/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j120_5_2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class TableData {
    private Object[] columnHeaders;
    private Object[][] rowData;
    private final List<String> primaryList;
    private String separator = ",";
    private int maxRowLength = 0;
    private static int quotationMarks = 0;
    private static String cell = "";
    private static StringBuilder sb = new StringBuilder("");

    public TableData (String separator){
        this.separator = separator;
        primaryList = MainFrame.getPrimaryList();
        primaryList.removeIf(x -> (x.equals("")));
    }

    public Object[] columnHeadersMaker(){
        columnHeaders = cellsMaker(primaryList.get(0), separator, maxRowLength);
        return columnHeaders;
    }
    public Object[][] rowDataMaker(){
        primaryList.remove(0);
        Object[] cell;
        Object[] temp = primaryList.toArray();
        rowData = new Object[temp.length][columnHeaders.length];
        try {
            for(int i = 0; i < temp.length; i++) {
                cell = cellsMaker(primaryList.get(i), separator, maxRowLength);
                System.arraycopy(cell, 0, rowData[i], 0, columnHeaders.length);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null,
                    "The file cannot be converted.It is possible that a non-csv file is selected, "
                            + "or the selected file was created in violation of the requirements "
                            + "of the csv standard", "Error. File conversion error.",
                    JOptionPane.ERROR_MESSAGE); }
        return rowData;
    }

    public static Object[] cellsMaker(String line, String separator, int maxRowLength){

        List<String> tempResultList = mainPart(line, separator);
        if(maxRowLength > tempResultList.size())
            for(int i = 0; i<= maxRowLength - tempResultList.size(); i++){
                tempResultList.add("");
            }
        Object[] cells = tempResultList.toArray();
        return cells;
    }

    public static List<String> mainPart(String line, String separator) {
        List<String> tempResultList = new ArrayList<>();
        line = line.trim();
        char[] charArray = line.toCharArray();
        for(int i = 0; i<charArray.length; i++)  {
            if(charArray[i] == '\"' && quotationMarks == 0) {
                quotationMarks = 1;
                i++; }
            if(charArray[i] == '\"' && quotationMarks == 1 && i != charArray.length - 1)
            { if(charArray[i+1] == '\"')
                i++;
            else {
                quotationMarks = 0;
                i++; }
            }
            if(charArray[i] == '\"' && quotationMarks == 1 && i == charArray.length - 1)
            { quotationMarks = 0;
                charArray[i] = Character.MIN_VALUE; }
            if(charArray[i] != separator.charAt(0))
            {sb = sb.append(charArray[i]);
                if(i == charArray.length - 1) {
                    cell = sb.toString();
                    tempResultList.add(cell.trim());
                    sb.delete(0, cell.length()); }
            }
            else if(charArray[i] == separator.charAt(0) && quotationMarks == 0)
            {cell = sb.toString();
                tempResultList.add(cell.trim());
                if(i == charArray.length - 1)
                    tempResultList.add("");
                sb.delete(0, cell.length());
            }
            else if(charArray[i] == separator.charAt(0) && quotationMarks == 1) {
                sb = sb.append(charArray[i]); }
        }

        return tempResultList;
    }
}
