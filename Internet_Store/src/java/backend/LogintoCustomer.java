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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.User;

/**
 *
 * @author rajashekar
 */
//WebServlet(name = "LogintoCustomer", urlPatterns = {"/LogintoCustomer"})
public class LogintoCustomer extends HttpServlet {

    boolean login = false;

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
            /* TODO output your page here. You may use following sample code. */
            String CustomerEnteredEmail = request.getParameter("email");
            String CustomerEnteredPassword = request.getParameter("pwd");
            //User user = userDAO.find(CustomerEnteredEmail, CustomerEnteredPassword);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Raj:1521:xe", "E-store", "oracle");
            if (con != null) {
                out.println("Connected");

            }
            Statement st = con.createStatement();
            String sql = "SELECT * FROM customers WHERE CUST_EMAIL = '" + CustomerEnteredEmail + "' AND CUST_PASSWORD = '" + CustomerEnteredPassword + "'";
            out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            //out.println("loggedIn Succesfully");
            //login = rs.first();
            if (rs.next()) {
                out.println("logged in");
                String customer_id = rs.getString("CUST_ID");
                String customer_firstname = rs.getString("CUST_firstname");
                String customer_lastname = rs.getString("CUST_LASTNAME");
                String customer_phonenumber = rs.getString("CUST_PHNO");
                String customer_address = rs.getString("CUST_ADDRESS");
                String customer_card = rs.getString("CUST_CARDNO");
                String customer_email = rs.getString("CUST_EMAIL");
                String customer_age = rs.getString("CUST_AGE");
                out.println(customer_id + "/t" + customer_firstname + "/t" + customer_lastname + "/t" + customer_phonenumber + "/t" + customer_address + "/t" + customer_card + "/t" + customer_email + "/t" + customer_age);
                //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customerhome.jsp");
                // requestDispatcher.forward(request, response);

                request.getSession().setAttribute("customer_id", customer_id);
                request.getSession().setAttribute("customer_firstname", customer_firstname);
                request.getSession().setAttribute("customer_lastname", customer_lastname);
                request.getSession().setAttribute("customer_phonenumber", customer_phonenumber);
                request.getSession().setAttribute("customer_address", customer_address);
                request.getSession().setAttribute("customer_card", customer_card);
                request.getSession().setAttribute("customer_email", customer_email);
                 request.getSession().setAttribute("customer_age", customer_age);
                request.getRequestDispatcher("customerhome.jsp").forward(request, response);

            } else {
                out.println("Not logged in");
            }
            //String name = rs.getString(String CUST_LASTNAME);
            //out.println(name);

            con.close();
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
            Logger.getLogger(LogintoCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LogintoCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LogintoCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LogintoCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
