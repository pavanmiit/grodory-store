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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/basket"})
public class basket extends HttpServlet {

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
                  //  link2="<a href='profile'>My Profile</a>";
                }
                PrintWriter out=response.getWriter();
                response.setContentType("text/html");
                out.write("<html><body>");
                out.write("<img src='banner.JPG' width='100%' height='250px'>");
                out.write("<hr><table width='100%'><tr><td><a href='index'>Home</a></td><td><a href='categories'>Categories</a></td><td><a href='products'>Products</a></td><td><a href='contactus'>Contact Us</a></td><td><a href='aboutus'>About Us</a></td><td>"+link1+"</td><td><a href='basket'><img src='cart.gif'></a></td></tr></table><hr>");
                Cookie[] ck=request.getCookies();    
                String val="";
                if(ck!=null)
                {
                    for(int i=0;i<ck.length;i++)
                    {
                        String nm=ck[i].getName();
                        if(nm.equals("BASKET"))
                        {
                            val=ck[i].getValue();
                        }
                    }
                }
                if(!val.equals(""))
                {
                    String[] pids=val.split("#");
                    out.write("<table align='center' width='400'>");
                    out.write("<tr><th>S.No.</th><th>Name</th><th>Details</th><th>Price</th><th>Photo</th></tr>");
                    for(int i=0;i<pids.length;i++)
                    {
                        stmt=con.prepareStatement("Select * from products where pid=?");
                        stmt.setString(1,pids[i]);
                        rs=stmt.executeQuery();
                        out.write("<tr>");
                        if(rs.next())
                        {
                            String s1=rs.getString(1);
                            String s2=rs.getString(2);
                            String s3=rs.getString(3);
                            String s4=rs.getString(4);
                            String s5=rs.getString(5);
                            String s6=rs.getString(6);
                            out.write("<td>"+(i+1)+"</td>");
                            out.write("<td>"+s3+"</td>");
                            out.write("<td>"+s4+"</td>");
                            out.write("<td>Rs "+s5+"/-</td>");
                            out.write("<td><img src='items/"+s2+"/"+s6+"' width='50'></td>");                            
                            out.write("<td></td>");
                        }
                        out.write("</tr>");
                    }
                    out.write("<tr>");
                    out.write("<th colspan='5'>");
                    if(hs==null)
                    {
                        out.write("<h4><a href='login'>Login to Continue</a></h4>");
                    }
                    else
                    {
                        out.write("<form method='post'><input type='submit' name='b1' value='Process the Order'></form>");
                    }
                    out.write("</th>");
                    out.write("</tr>");
                    out.write("</table>");
                    out.write("<hr>");
                    if(request.getParameter("b1")!=null)
                    {
                        stmt=con.prepareStatement("select * from addresses where email=?");
                        stmt.setObject(1,hs.getAttribute("ULOGIN"));
                        rs=stmt.executeQuery();
                        out.write("<table align='center'>");
                        boolean address=false;
                        while(rs.next())
                        {
                            address=true;
                            out.write("<tr>");
                            out.write("<td>"+rs.getString(3)+"</td>");
                            out.write("<td>"+rs.getString(4)+"</td>");
                            out.write("<td>"+rs.getString(5)+"</td>");
                            out.write("<td>"+rs.getString(6)+"</td>");
                            out.write("<td>"+rs.getString(7)+"</td>");
                            out.write("<td><a href='orderfinal?aid="+rs.getString(1)+"'>Select this Address</a></td>");
                            out.write("</tr>");
                        }
                        if(!address)
                        {
                            out.write("<a href='#' onClick=\"javascript:window.open('newaddress','','width=300,height=400')\">Enter Address</a>");
                        }
                        out.write("</table>");
                    }
                }
                else
                {
                    out.write("<center>Your Cart is Empty</center>");
                }
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
