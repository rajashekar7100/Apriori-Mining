/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.*;
import java.sql.*;

class Row {

    Set<Integer> itemset;
    int support;

    Row() {
        itemset = new HashSet<>();
        support = -1;
    }

    Row(Set<Integer> s) {
        itemset = s;
        support = -1;
    }

    Row(Set<Integer> s, int i) {
        itemset = s;
        support = i;
    }
}

public class apriori {

    static Set<Row> c;
    static Set<Row> l;
    static int d[][];
    static int min_support;

    public static void main(String args[]) throws Exception {
        getDatabase();
        c = new HashSet<>();
        l = new HashSet<>();
        Scanner scan = new Scanner(System.in);
        int i, j, m, n;
        System.out.println("Enter the minimum support (as an integer value):");
        min_support = scan.nextInt();
        Set<Integer> candidate_set = new HashSet<>();
        for (i = 0; i < d.length; i++) {

            System.out.println("Transaction Number: " + (i + 1) + ":");
            for (j = 0; j < d[i].length; j++) {
                System.out.print("Item number " + (j + 1) + " = ");
                System.out.println(d[i][j]);
                candidate_set.add(d[i][j]);
            }
        }

        Iterator<Integer> iterator = candidate_set.iterator();
        while (iterator.hasNext()) {
            Set<Integer> s = new HashSet<>();
            s.add(iterator.next());
            Row t = new Row(s, count(s));
            c.add(t);
        }

        prune();
        generateFrequentItemsets();
    }

    static int count(Set<Integer> s) {
        int i, j, k;
        int support = 0;
        int counts;
        boolean containsElement;
        for (i = 0; i < d.length; i++) {
            counts = 0;
            Iterator<Integer> iterator = s.iterator();
            while (iterator.hasNext()) {
                int element = iterator.next();
                containsElement = false;
                for (k = 0; k < d[i].length; k++) {
                    if (element == d[i][k]) {
                        containsElement = true;
                        counts++;
                        break;
                    }
                }
                if (!containsElement) {
                    break;
                }
            }
            if (counts == s.size()) {
                support++;
            }
        }
        return support;
    }

    static void prune() throws ClassNotFoundException, SQLException, IOException {
        l.clear();
        Iterator<Row> iterator = c.iterator();
        while (iterator.hasNext()) {
            Row t = iterator.next();
            if (t.support >= min_support) {
                l.add(t);
            }
        }
        System.out.println(" ITEM PAIRS:L ");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Raj:1521:xe", "E-store", "oracle");
        // BufferedWriter out = new BufferedWriter(new FileWriter("c://output.txt"));
        BufferedWriter bw = null;
        // File file = new File("E:\\DM\\Freqlist.txt");
        //FileWriter fileWriter = new FileWriter(file);
        // bw = new BufferedWriter(fileWriter);
        //PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Row t : l) {
            System.out.println(t.itemset + " : " + t.support);

            for (int item_id : t.itemset) {
                Statement st = con.createStatement();
                String sql = "SELECT item_name from items WHERE item_id=" + item_id;
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                String s = rs.getString("item_name");
                //System.out.println("Example one dude:"+s);
                System.out.println(s);
            }
        }

       // fileWriter.flush();
        //fileWriter.close();bw.close();
    }

    static void generateFrequentItemsets() throws ClassNotFoundException, SQLException, IOException {
        boolean toBeContinued = true;
        int element = 0;
        int size = 1;
        Set<Set> candidate_set = new HashSet<>();
        while (toBeContinued) {
            candidate_set.clear();
            c.clear();
            Iterator<Row> iterator = l.iterator();
            while (iterator.hasNext()) {
                Row t1 = iterator.next();
                Set<Integer> temp = t1.itemset;
                Iterator<Row> it2 = l.iterator();
                while (it2.hasNext()) {
                    Row t2 = it2.next();
                    Iterator<Integer> it3 = t2.itemset.iterator();
                    while (it3.hasNext()) {
                        try {
                            element = it3.next();
                        } catch (ConcurrentModificationException e) {
                            
                            break;
                        }
                        temp.add(element);
                        if (temp.size() != size) {
                            Integer[] int_arr = temp.toArray(new Integer[0]);
                            Set<Integer> temp2 = new HashSet<>();
                            for (Integer x : int_arr) {
                                temp2.add(x);
                            }
                            candidate_set.add(temp2);
                            temp.remove(element);
                        }
                    }
                }
            }
            Iterator<Set> candidate_set_iterator = candidate_set.iterator();
            while (candidate_set_iterator.hasNext()) {
                Set s = candidate_set_iterator.next();
                
                c.add(new Row(s, count(s)));
            }
            prune();
            if (l.size() <= 1) {
                toBeContinued = false;
            }
            size++;
        }
        System.out.println("\n FINAL UNIQUE LISTS ");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Raj:1521:xe", "E-store", "oracle");
        File file = new File("E:\\DM\\Freqlist.txt");
        PrintWriter printWriter = new PrintWriter(file);
        for (Row t : l) {
            printWriter.println(t.itemset + " : " + t.support);
            for (int item_id : t.itemset) {
                Statement st = con.createStatement();
                String sql = "SELECT item_name from items WHERE item_id=" + item_id;
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                String s = rs.getString("item_name");
                printWriter.println(s);
                printWriter.close();
            }
        }
    }

    static void getDatabase() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Raj:1521:xe", "E-store", "oracle");
        if (con != null) {
            out.println("Connected");

        }
        Statement s = con.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM trans_spec");
        Map<Integer, List<Integer>> m = new HashMap<>();
        List<Integer> temp;
        while (rs.next()) {
            int Trans_no = Integer.parseInt(rs.getString(1));
            int Item_no = Integer.parseInt(rs.getString(2));
            temp = m.get(Trans_no);
            if (temp == null) {
                temp = new LinkedList<>();
            }
            temp.add(Item_no);
            m.put(Trans_no, temp);
        }

        Set<Integer> keyset = m.keySet();
        d = new int[keyset.size()][];
        Iterator<Integer> iterator = keyset.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            temp = m.get(iterator.next());
            Integer[] int_arr = temp.toArray(new Integer[0]);
            d[count] = new int[int_arr.length];
            for (int i = 0; i < d[count].length; i++) {
                d[count][i] = int_arr[i].intValue();
            }
            count++;
        }
    }
}
