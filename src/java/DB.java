/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/DB"})
public class DB extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        try{
                Class.forName("org.gjt.mm.mysql.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1","root","");
                PreparedStatement stmt;
                stmt=con.prepareStatement("Create Database shoping");
                stmt.executeUpdate();
                stmt=con.prepareStatement("use shoping");
                stmt.executeUpdate();
                stmt=con.prepareStatement("Create Table Users(ulogin varchar(30) Primary Key,upass varchar(20),utype varchar(20))");
                stmt.executeUpdate();
                stmt=con.prepareStatement("Insert into users values('admin','admin123','administrator')");
                stmt.executeUpdate();
                stmt=con.prepareStatement("Create Table Members(Email varchar(30) Primary Key,name varchar(20),mobile varchar(10),SQuestion varchar(30),SAnswer varchar(30))");
                stmt.executeUpdate();
                stmt=con.prepareStatement("Create Table Addresses(aid int Primary Key,Email varchar(30),Address varchar(100),State varchar(20),City varchar(20),PinCode varchar(20),Mobile varchar(10))");
                stmt.executeUpdate();
                stmt=con.prepareStatement("Create Table Categories(CID int Primary Key,Name varchar(20),Description varchar(50),Photo varchar(20))");
                stmt.executeUpdate();
                stmt=con.prepareStatement("Create Table Products(PID int Primary Key,CName varchar(20),Name varchar(20),Description varchar(50),Price int,Photo varchar(20))");
                stmt.executeUpdate();
                stmt=con.prepareStatement("Create Table Orders(OID int Primary Key,Odate date,Email varchar(30),AID int,TotalAmt int)");
                stmt.executeUpdate();
                stmt=con.prepareStatement("Create Table OrderDetails(OID int,PID int,Name varchar(30),Qty int,price int,amt int)");
                stmt.executeUpdate();
                PrintWriter out=response.getWriter();
                out.write("Done");
        }catch(Exception ee){}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
