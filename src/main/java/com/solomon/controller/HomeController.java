package com.solomon.controller;

/**
 * Created by Antonella on 7/26/17.
 */


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
// step 1 : import sql package
import java.sql.*;
import java.util.ArrayList;



// remember to replace the variables for your connection in each of the controller methods
// where applicable
@Controller
public class HomeController {
    @RequestMapping("/updateStuff")
    // this example shows how to update a row in a table
    public String update(Model model) throws ClassNotFoundException, SQLException {

        String query = "Update contact set " + "contact_id = ?," + "name = ?,"
        + "email = ?," + "address = ?, " + "telephone=?" + "where contact_id= ?";

        // step 2
        Class.forName("com.mysql.jdbc.Driver");

        // step 3
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/****", "****", "****");

        // step 4
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, 41234);
        ps.setString(2, "AntonellaSol");
        ps.setString(3, "test@gmail.com");
        ps.setString(4, "1570 Grand");
        ps.setString(5, "3134078679");
        ps.setInt(6,412);

        ps.executeUpdate();


        // step 7
        ps.close();
        con.close();

        return "update";
    }




    @RequestMapping("/deleteStuff")
    // this example shows how to delete an item from the database
    public String delete(Model model) throws ClassNotFoundException, SQLException {

        //String query = "Update contact set " + "contact_id = ?," + "name = ?,"
        //+ "email = ?" + "address = ?, " + "telephone=?" + "where name= ?";
        String query = "DELETE from contact where contact_id=?";
        // step 2
        Class.forName("com.mysql.jdbc.Driver");

        // step 3
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/****", "****", "****");

        // step 4
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, 412);

        ps.executeUpdate();


        // step 7
        ps.close();
        con.close();

        return "delete";
    }


    @RequestMapping("/addStuff")
    // this example shows how to add an item to a table
    public String add(Model model) throws ClassNotFoundException, SQLException {


        String query = "INSERT INTO contact values(?,?,?,?,?)";
        // step 2
        Class.forName("com.mysql.jdbc.Driver");

        // step 3
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/****", "****", "****");

        // step 4
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, 412);
        ps.setString(2, "Antonella");
        ps.setString(3, "test@gmail.com");
        ps.setString(4, "1570 Grand");
        ps.setString(5, "3134078679");


        ps.executeUpdate();


        // step 7
        ps.close();
        con.close();

        return "add";
    }

    @RequestMapping("/jdbc")
    // this example shows multiple rows being returned from our table
    public String dbReturn(Model model) throws ClassNotFoundException, SQLException {


        // step 2
        Class.forName("com.mysql.jdbc.Driver");

        // step 3
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/****", "****", "****");

        //step 4


        Statement st = con.createStatement();

        //step 5

        ResultSet rs = st.executeQuery("select * from Customers");

        ArrayList<String> list = new ArrayList<String>();

        //step 6
        while (rs.next()) {
            String custName = rs.getString(1);
            String compName = rs.getString(2);
            String contName = rs.getString(3);

            // populate data into the arraylist
            list.add(custName + ", " + compName + ", " + contName);
        }

        model.addAttribute("dbResult", list);

        // step 7
        rs.close();
        con.close();

        return "jdbc";
    }


    @RequestMapping("/")
    // this example only shows one row from the table returned
    public ModelAndView helloWorld() throws ClassNotFoundException, SQLException {

        // prep for step 3 -- this is optional because we can assign directly in the connection
        String url = "jdbc:mysql://localhost:3306/****";
        String userName = "****";
        String password = "****";
        String query = "select * from products";

        // step 2: Load and Register the driver
        Class.forName("com.mysql.jdbc.Driver");

        // step 3: create connection
        Connection con = DriverManager.getConnection(url, userName, password);

        // step 4: create statement
        Statement st = con.createStatement();

        // step 5 (optional): retrieve results / execute query
        ResultSet rs = st.executeQuery(query);

        // step 6 (optional) : process results
        // we need to use the next() to move past the column headers for the first row of data in our table
        rs.next();

        // the get methods that are used to assign variables here are referencing the table column index
        // the index starts at 1 not 0
        String productID = rs.getString(1);
        String prodName = rs.getString(2);
        String supplierID = rs.getString(3);

        String printResult = productID + " " + prodName + " " + supplierID;

        // step 7 (optional) :
        rs.close();
        con.close();


        return new
                ModelAndView("welcome", "message", printResult);

    }

}
