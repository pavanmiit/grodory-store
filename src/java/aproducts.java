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
@WebServlet(urlPatterns = {"/aproducts"})
public class aproducts extends HttpServlet {

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
           if(request.getParameter("b1")!=null)
           {
               stmt=con.prepareStatement("Select count(*)+1 from Products");
               rs=stmt.executeQuery();
               String pid="";
               if(rs.next())
               {
                   pid=rs.getString(1);
               }
               stmt=con.prepareStatement("Insert into Products values(?,?,?,?,?,?)");
               stmt.setString(1,pid);
               stmt.setString(2,request.getParameter("t1"));
               stmt.setString(3,request.getParameter("t2"));
               stmt.setString(4,request.getParameter("t3"));
               stmt.setString(5,request.getParameter("t4"));
               stmt.setString(6,request.getParameter("t5"));
               stmt.executeUpdate();
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
            out.write("<form method='post'><table align='center'>");
            out.write("<tr><th colspan='2'>Category Details</th></tr>");
            out.write("<tr><td>Category Name:</td><td><select name='t1'>");
            stmt=con.prepareStatement("Select * from categories");
            rs=stmt.executeQuery();
            while(rs.next())
            {
                out.write("<option>"+rs.getString(2)+"</option>");
            }
            out.write("</select></td></tr>");
            out.write("<tr><td>Product Name:</td><td><input type='text' name='t2'></td></tr>");            
            out.write("<tr><td>Description:</td><td><input type='text' name='t3'></td></tr>");
            out.write("<tr><td>Price:</td><td><input type='text' name='t4'></td></tr>");
            out.write("<tr><td>Photo:</td><td><input type='file' name='t5'></td></tr>");
            out.write("<tr><td></td><td><input type='submit' name='b1' value='Save'></td></tr>");            
            out.write("</table></form><hr>");
            stmt=con.prepareStatement("Select * from Products");
            rs=stmt.executeQuery();
            out.write("<table align='center'>");
            out.write("<tr><th>Product ID</th><th>Category ID</th><th>Name</th><th>Details</th><th>Price</th><th>Picture</th></tr>");
            while(rs.next())
            {
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                String s3=rs.getString(3);
                String s4=rs.getString(4);
                String s5=rs.getString(5);
                String s6=rs.getString(6);
                out.write("<tr>");
                out.write("<td>"+s1+"</td>");
                out.write("<td>"+s2+"</td>");
                out.write("<td>"+s3+"</td>");
                out.write("<td>"+s4+"</td>");
                out.write("<td>"+s5+"</td>");
                String ss="items/"+s2+"/"+s6;
                out.write("<td><img src='"+ss+"' width='60px'></td>");
                out.write("</tr>");
            }
            out.write("</table>");
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
