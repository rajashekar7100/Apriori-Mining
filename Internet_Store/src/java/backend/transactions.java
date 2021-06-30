/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rajashekar
 */
@WebServlet(name = "transactions", urlPatterns = {"/transactions"})
public class transactions extends HttpServlet {

    private Object session;

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

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Raj:1521:xe", "E-store", "oracle");
            if (con != null) {
                //out.println("Connected");

            }

            String eletronics[] = request.getParameterValues("electronics");
            int Total_sales = 0;
            if (eletronics != null) {
                //String customer_id = request.getParameter("CUST_ID");
                HttpSession session = request.getSession(true);
                String customer_id = (String) session.getAttribute("customer_id");
                if (customer_id != null) {
                    System.out.println("customer_id: " + customer_id);
                }
                for (String sale : eletronics) {
                    Statement st = con.createStatement();
                    String sql = "SELECT * FROM items where ITEM_ID= '" + sale + "'";
                   // out.println(sql);
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        Total_sales += rs.getInt("Item_price");
                    }
                }
                //out.println(Total_sales);
                //String customer_id = rs.getString("CUST_ID");
                // Iterator iter = eletronics.Iterator();
                //  String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(myTimestamp);
                java.util.Date date = new java.util.Date();
                // out.println("Date :" + date);
               long t = date.getTime();
               java.sql.Date sqlDate = new java.sql.Date(t);
                java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);
                //Calendar calendar = Calendar.getInstance();
                //java.util.Date now = calendar.getTime();
                
                Statement stmt = con.createStatement();
                String trans_sql = "INSERT INTO transactions ( C_ID, TOTAL_SALES, TIME)"
                        + " values ('" + customer_id + "', '" + Total_sales + "', '" + sqlTimestamp + "' )";
                //out.println(trans_sql);
                //PreparedStatement statement = con.prepareStatement(insertsql);
                //statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                //out.println(insertsql);
                stmt.executeQuery(trans_sql);
                trans_sql = "SELECT * FROM transactions WHERE C_ID = '" + customer_id + "' AND TOTAL_SALES = '" + Total_sales + "' AND TIME =  '" + sqlTimestamp + "'";
                //out.println(trans_sql);
                ResultSet rs = stmt.executeQuery(trans_sql);
                String T_ID = "";
                while (rs.next()) {
                    T_ID = rs.getString("T_ID");
                }
                
                for (String sale : eletronics) {
                    Statement st = con.createStatement();
                    trans_sql = "INSERT INTO TRANS_SPEC ( TRANS_ID, ITEM_ID)"
                        + " values ('" + T_ID + "', '" + sale + "')";
                    //out.println(trans_sql);
                    st.executeQuery(trans_sql);
                    
                }
                out.println("Transaction Successful");
                con.close();
            }
            else{
                out.println("No Transaction has been made");
            }

            // st.close();
            /*
             Statement stmt =con.createStatement();
             PreparedStatement ps=con.prepareStatement("INSERT INTO trans_spec (ITEM_ID)values(?)");
             //String query = " INSERT INTO trans_spec (TRANS_ID, ITEM_ID)values(?, ?)";
             for(String val : eletronics){
             ps.setString(2, val);
             ps.execute();
             }*/
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
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
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
