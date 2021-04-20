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
@WebServlet(urlPatterns = {"/index"})
public class index extends HttpServlet {

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
                String link1="<a href='login'>Login</a>";
                String link2="<a href='register'>Register</a>";
                if(hs!=null)
                {
                    link1="<a href='logout'>Logout</a>";
                   // link2="<a href='profile'>My Profile</a>";
                }
                PrintWriter out=response.getWriter();
                response.setContentType("text/html");
                out.write("<html><body>");
                out.write("<img src='banner.JPG' width='100%' height='250px'>");
                out.write("<hr><table width='100%'><tr><td><a href='index'>Home</a></td><td><a href='categories'>Categories</a></td><td><a href='products'>Products</a></td><td><a href='contactus'>Contact Us</a></td><td><a href='aboutus'>About Us</a></td><td>"+link1+"</td><td><a href='basket'><img src='cart.gif'></a></td></tr></table><hr>");
                out.write("<img src='p9.jpg' width='33%'><img src='p7.jpg' width='33%'><img src='p8.jpg' width='33%'>");
                out.write("<img src='p4.jpg' width='24%'><img src='p6.jpg' width='24%'><img src='p10.jpg' width='24%'><img src='p11.jpg' width='24%'>");
                out.write("<hr><img src='footer.jpg' width='100%' height='200px'>");
                out.write("</body></html>");
                con.close();
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
