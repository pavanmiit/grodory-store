/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package p1;

/**
 *
 * @author Administrator
 */
public class MyClass {
    public static String getDate()
    {
        java.util.Date dd=new java.util.Date();
        int y=dd.getYear()+1900;
        int m=dd.getMonth()+1;
        int d=dd.getDate();
        String s=y+"-"+m+"-"+d;
        return s;
    }
}
