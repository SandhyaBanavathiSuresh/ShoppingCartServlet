package com.csye6220.part4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sandhya
 */
@WebServlet(name = "readcsv", urlPatterns = {"/part4csv.xls"})
public class ReadCSV extends HttpServlet {

    private void handleCSV(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {

        Class.forName("org.relique.jdbc.csv.CsvDriver");
            String pathCsv = request.getServletContext().getRealPath("/resources");
//            String pathCsv = "C:\\Users\\Sandhya\\Desktop\\Northeastern\\Sem 3\\EnterpriseSoftwareDesign";
            Connection con = DriverManager.getConnection("jdbc:relique:csv:" + pathCsv);
            
            response.setContentType("text/html");
            
            Statement smt = con.createStatement();
            String csvname = request.getParameter("csvname");
            ResultSet result = smt.executeQuery("SELECT * from " + csvname);

        try {

            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<h1 style = 'text-align: center'>PARKING FACILITIES DATA</h1>");
            out.println("<table style = 'border: 4px solid black;margin: 2rem;padding: 0.5rem;'>");
            
            ResultSetMetaData data = result.getMetaData();
                int columnNo = data.getColumnCount();
                for(int i = 1; i <= columnNo; i++){
                    out.println("<th>" +data.getColumnName(i)+ "</th>");
            }
            while (result.next()) {
                out.println("<tr>");
                
                for (int i = 1; i <= columnNo; i++) {
                    out.println("<td style = 'border: 1px solid black'> " + result.getString(i) + "</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</html>");
            out.println("</body>");
        } catch (IOException ex) {

        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            handleCSV(request, response);
        } catch (ClassNotFoundException | SQLException | IOException e) {
        }

    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            handleCSV(request, response);
        } catch (ClassNotFoundException | SQLException | IOException e) {
        }
    }
}
