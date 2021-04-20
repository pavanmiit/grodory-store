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
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/admin"})
public class admin extends HttpServlet {

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
           Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/shoping","root","");
           PreparedStatement stmt;
           ResultSet rs;
           HttpSession hs=request.getSession(false); 
           if(hs==null) 
           {
               response.sendRedirect("login");
           }
           else
           {
               String s=hs.getAttribute("UTYPE").toString();
               if(!s.equals("ADMINISTRATOR"))
                   response.sendRedirect("login");
           }
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrator Module</title>");            
            out.println("</head>");
            out.println("<body>");
            out.write("<table width='100%'><tr><th><a href='admin'>Home</a></th><th><a href='acategories'>Categories</a></th><th><a href='aproducts'>Products</a></th><th><a href='amembers'>Members</a></th><th><a href='logout'>Logout</a></th></tr></table><hr>");
            stmt=con.prepareStatement("Select Orders.*,addresses.* from orders,addresses where  orders.aid=addresses.aid");
            rs=stmt.executeQuery();
            out.write("<table align='center'>");
            out.write("<tr><th>Order ID</th><th>Order Date</th><th>Email</th><th>Total Amount</th><th>Address</th><th>State</th><th>City</th><th>Pincode</th><th>Mobile</th></tr>");
            while(rs.next())
            {
                out.write("<tr>");
                out.write("<td>"+rs.getString("oid")+"</td>");
                out.write("<td>"+rs.getString("odate")+"</td>");
                out.write("<td>"+rs.getString("Email")+"</td>");
                out.write("<td>"+rs.getString("totalamt")+"</td>");
                out.write("<td>"+rs.getString("address")+"</td>");
                out.write("<td>"+rs.getString("state")+"</td>");
                out.write("<td>"+rs.getString("city")+"</td>");
                out.write("<td>"+rs.getString("pincode")+"</td>");
                out.write("<td>"+rs.getString("mobile")+"</td>");
                out.write("</tr>");
            }
            out.write("</table><hr>");
            out.println("</body>");
            out.println("</html>");
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
