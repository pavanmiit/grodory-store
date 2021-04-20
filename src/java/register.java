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
@WebServlet(urlPatterns = {"/register"})
public class register extends HttpServlet {

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
                if(request.getParameter("b1")!=null)
                {
                    String s1=request.getParameter("t1");
                    String s2=request.getParameter("t2");
                    String s3=request.getParameter("t3");
                    String s4=request.getParameter("t4");
                    String s5=request.getParameter("t5");
                    String s6=request.getParameter("t6");
                    String s7=request.getParameter("t7");
                    if(s2.equals(s3)){
                    stmt=con.prepareStatement("Insert into members values(?,?,?,?,?)");
                    stmt.setString(1,s1);
                    stmt.setString(2,s4);
                    stmt.setString(3,s5);
                    stmt.setString(4,s6);
                    stmt.setString(5,s7);
                    stmt.executeUpdate();
                    stmt=con.prepareStatement("Insert into users values(?,?,'member')");
                    stmt.setString(1,s1);
                    stmt.setString(2,s2);
                    stmt.executeUpdate();
                    response.sendRedirect("login");
                }}
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
                out.write("<form method='post'><table align='center'>");
                out.write("<tr><td>Email:</td><td><input type='email' name='t1'></td></tr>");
                out.write("<tr><td>Password:</td><td><input type='password' name='t2'></td></tr>");
                out.write("<tr><td>Re-Password:</td><td><input type='password' name='t3'></td></tr>");
                out.write("<tr><td>Name:</td><td><input type='text' name='t4'></td></tr>");
                out.write("<tr><td>Mobile:</td><td><input type='text' name='t5'></td></tr>");
                out.write("<tr><td>Secret Question:</td><td><Select name='t6'><option>Pet Name?</opton><option>First School Name?</opton><option>Favorite Destination?</opton></select></td></tr>");
                out.write("<tr><td>Answer:</td><td><input type='text' name='t7'></td></tr>");
                out.write("<tr><td></td><td><input type='submit' name='b1' value='Register'></td></tr>");
                out.write("</table></form>");
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
