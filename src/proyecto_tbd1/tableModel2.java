/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_tbd1;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author darie
 */
public class tableModel2 extends DefaultTableModel {
    public tableModel2(){
        super();
    }
    public tableModel2(Object[][] arr, String[] arr2){
        super(arr, arr2);
    }
    //javax.swing.table.DefaultTableModel
    public boolean isCellEditable(int row, int col){
        return false;
    }
}
