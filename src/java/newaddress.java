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
@WebServlet(urlPatterns = {"/newaddress"})
public class newaddress extends HttpServlet {

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
                if(request.getParameter("b1")!=null);
                {
                    stmt=con.prepareStatement("Select count(*)+1 from Addresses");
                    rs=stmt.executeQuery();
                    String aid="";
                    if(rs.next()) aid=rs.getString(1);
                    stmt=con.prepareStatement("Insert into addresses values(?,?,?,?,?,?,?)");
                    stmt.setString(1,aid);
                    stmt.setObject(2,hs.getAttribute("ULOGIN"));
                    stmt.setString(3,request.getParameter("t1"));
                    stmt.setString(4,request.getParameter("t2"));
                    stmt.setString(5,request.getParameter("t3"));
                    stmt.setString(6,request.getParameter("t4"));
                    stmt.setString(7,request.getParameter("t5"));
                    stmt.executeUpdate();
                }   
                PrintWriter out=response.getWriter();
                response.setContentType("text/html");
                out.write("<html><body>");
                out.write("<form method='post'>");
                out.write("<table align='center'>");
                out.write("<tr><td>Address:</td><td><input type='text' name='t1'></td></tr>");
                out.write("<tr><td>State:</td><td><input type='text' name='t2'></td></tr>");
                out.write("<tr><td>City:</td><td><input type='text' name='t3'></td></tr>");
                out.write("<tr><td>Pin Code:</td><td><input type='text' name='t4'></td></tr>");
                out.write("<tr><td>Mobile:</td><td><input type='text' name='t5'></td></tr>");
                out.write("<tr><td></td><td><input type='submit' name='b1' value='Submit'></td></tr>");
                out.write("</table></form>");
                out.write("<a href='#' onClick='javascript:window.close()'>Close</a>");
                out.println("</body>");
                out.println("</html>");
        }catch(Exception ee){System.out.println(ee);}
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
