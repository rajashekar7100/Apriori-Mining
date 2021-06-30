/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rajashekar
 */
public class RandomTrans extends HttpServlet {

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
            

            for (int i = 0; i < 100; i++) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Raj:1521:xe", "E-store", "oracle");
            if (con != null) {
                //out.println("Connected");

            }
                int n = (int) (Math.random() * 4) + 1;
                out.println(n);
                List<String> eletronics = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    int x = (int) (Math.random() * 14) + 1;
                    if (x == 1) {
                        eletronics.add("224");
                    } else if (x == 2) {
                        eletronics.add("334");
                    } else if (x == 3) {
                        eletronics.add("333");
                    } else if (x == 4) {
                        eletronics.add("111");
                    } else if (x == 5) {
                        eletronics.add("222");
                    } else if (x == 6) {
                        eletronics.add("999");
                    } else if (x == 7) {
                        eletronics.add("223");
                    } else if (x == 8) {
                        eletronics.add("777");
                    } else if (x == 9) {
                        eletronics.add("444");
                    } else if (x == 10) {
                        eletronics.add("555");
                    } else if (x == 11) {
                        eletronics.add("666");
                    } else if (x == 12) {
                        eletronics.add("888");
                    } else if (x == 13) {
                        eletronics.add("889");
                    } else if (x == 14) {
                        eletronics.add("998");
                    }
                }
                out.println(eletronics);
                //String eletronics[] = request.getParameterValues("electronics");
                int Total_sales = 0;
                if (eletronics != null) {
                    //String customer_id = request.getParameter("CUST_ID");
                    HttpSession session = request.getSession(true);
                    int r = (int) (Math.random() * 14) + 1;
                    String customer_id="21";
                    if (r == 1) {
                        customer_id = "21";
                    } else if (r == 2) {
                        customer_id = "121";
                    } else if (r == 3) {
                        customer_id = "51";
                    } else if (r == 4) {
                        customer_id = "58";
                    } else if (r == 5) {
                        customer_id = "81";
                    } else if (r == 6) {
                        customer_id = "82";
                    } else if (r == 7) {
                        customer_id = "83";
                    } else if (r == 8) {
                        customer_id = "84";
                    } else if (r == 9) {
                        customer_id = "85";
                    } else if (r == 10) {
                        customer_id = "86";
                    } else if (r == 11) {
                        customer_id = "87";
                    } else if (r == 12) {
                        customer_id = "88";
                    } else if (r == 13) {
                        customer_id = "89";
                    } else if (r == 14) {
                        customer_id = "90";
                    }
                    if (customer_id != null) {
                        out.println("customer_id: " + customer_id);
                    }
                    for (String sale : eletronics) {
                        out.println("In A:"+sale);
                        try (Statement st = con.createStatement()) {
                            out.println("In B:");
                            String sql = "SELECT * FROM items where ITEM_ID= '" + sale + "'";
                            out.println("In C:");
                            // out.println(sql);
                            ResultSet rs = st.executeQuery(sql);
                            out.println("In D:");
                            
                            while (rs.next()) {
                                out.println("In Total:"+Total_sales);
                                Total_sales += rs.getInt("Item_price");
                            }
                            out.println("In E:");
                            rs.close();
                        }
                    }
                    out.println("After adding");
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
                    out.println("After next 2");
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
                } else {
                    out.println("No Transaction has been made");
                }
            }
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
            Logger.getLogger(RandomTrans.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RandomTrans.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RandomTrans.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RandomTrans.class.getName()).log(Level.SEVERE, null, ex);
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
