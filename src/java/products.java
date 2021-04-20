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
@WebServlet(urlPatterns = {"/products"})
public class products extends HttpServlet {

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
                String cat=request.getParameter("cat");
                String ss="";
                if(cat!=null)
                {
                    ss=" where cname='"+cat+"'";
                }
                else
                {
                    cat="0";
                }
                stmt=con.prepareStatement("Select count(*) from Products"+ss);
                rs=stmt.executeQuery();
                int rows=0;
                if(rs.next()) rows=rs.getInt(1);
                int n=(int)Math.ceil(rows/3.0);
                stmt=con.prepareStatement("Select * from Products"+ss);
                rs=stmt.executeQuery();
                out.write("<table align='center'>");
                for(int i=1;i<=n;i++)
                {
                    out.write("<tr>");
                    if(rs.next())
                    {
                        String s1=rs.getString(1);
                        String s2=rs.getString(2);
                        String s3=rs.getString(3);
                        String s4=rs.getString(4);
                        String s5=rs.getString(5);
                        String s6=rs.getString(6);
                        out.write("<td style='text-align:center'>");
                        out.write("<img src='items/"+s2+"/"+s6+"' width='300'><br>");
                        out.write(s3+"<br>");
                        out.write(s4+"<br>");
                        out.write("Price: Rs "+s5+"/-<br>");
                        out.write("<a href='addinbasket?pid="+s1+"&cat="+cat+"'><img src='addtocart.jpg' width='150'></a>");
                        out.write("</td>");
                    }
                    else
                    {
                        out.write("<td></td>");
                    }
                    if(rs.next())
                    {
                        String s1=rs.getString(1);
                        String s2=rs.getString(2);
                        String s3=rs.getString(3);
                        String s4=rs.getString(4);
                        String s5=rs.getString(5);
                        String s6=rs.getString(6);
                        out.write("<td style='text-align:center'>");
                        out.write("<img src='items/"+s2+"/"+s6+"' width='300'><br>");
                        out.write(s3+"<br>");
                        out.write(s4+"<br>");
                        out.write("Price: Rs "+s5+"/-<br>");
                        out.write("<a href='addinbasket?pid="+s1+"&cat="+cat+"'><img src='addtocart.jpg' width='150'></a>");
                        out.write("</td>");
                    }
                    else
                    {
                        out.write("<td></td>");
                    }
                    if(rs.next())
                    {
                        String s1=rs.getString(1);
                        String s2=rs.getString(2);
                        String s3=rs.getString(3);
                        String s4=rs.getString(4);
                        String s5=rs.getString(5);
                        String s6=rs.getString(6);
                        out.write("<td style='text-align:center'>");
                        out.write("<img src='items/"+s2+"/"+s6+"' width='300'><br>");
                        out.write(s3+"<br>");
                        out.write(s4+"<br>");
                        out.write("Price: Rs "+s5+"/-<br>");
                        out.write("<a href='addinbasket?pid="+s1+"&cat="+cat+"'><img src='addtocart.jpg' width='150'></a>");
                        out.write("</td>");
                    }
                    else
                    {
                        out.write("<td></td>");
                    }
                    out.write("</tr>");
                }                
                out.write("</table>");
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
