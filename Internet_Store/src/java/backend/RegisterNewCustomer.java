/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rajashekar
 */
public class RegisterNewCustomer extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Raj:1521:xe", "E-store", "oracle");
             Statement st = con.createStatement();

            if (con != null) {
                out.println("Connected");

            }
            out.println("eXCESS");
            //String CUST_ID  = request.getParameter("Cust_ID");
            //out.println("1");
            String CUST_FIRSTNAME = request.getParameter("firstname");
            out.println("2");
            String CUST_LASTNAME = request.getParameter("lastname");
            out.println("3");
            String CUST_PHNO = request.getParameter("Phno");
            out.println("4");
            String CUST_ADDRESS = request.getParameter("Address");
            out.println("5");
            String CUST_CARDNO = request.getParameter("CardNo");
            out.println("6");
            String CUST_EMAIL = request.getParameter("Email");
            out.println("7");
            String CUST_PASSWORD = request.getParameter("password");
            out.println("eXCESS");
            String CUST_AGE = request.getParameter("age");
            out.println(CUST_AGE);
            out.println(CUST_FIRSTNAME + " " + CUST_LASTNAME + CUST_PHNO + CUST_ADDRESS + CUST_CARDNO + CUST_EMAIL + CUST_PASSWORD + CUST_AGE);
            out.println("Inserting records into the table...");
           
            String sql = " INSERT INTO customers (CUST_FIRSTNAME, CUST_LASTNAME, CUST_PHNO, CUST_ADDRESS, CUST_CARDNO, CUST_EMAIL, CUST_PASSWORD, AGE)"
                    + " values ('" + CUST_FIRSTNAME + "', '" + CUST_LASTNAME + "', " + CUST_PHNO + ", '" + CUST_ADDRESS + "', " + CUST_CARDNO + ", '" + CUST_EMAIL + "', '" + CUST_PASSWORD + "', " + CUST_AGE + ")";
            st.executeQuery(sql);
            out.println(sql);
            
            con.close();

            /* TODO output your page here. You may use following sample code. */
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterNewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterNewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterNewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterNewCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
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
