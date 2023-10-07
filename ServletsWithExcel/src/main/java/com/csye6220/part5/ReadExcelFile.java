package com.csye6220.part5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Sandhya
 */
@WebServlet (name="ReadExcelFile",urlPatterns={"/excel.xls"})
public class ReadExcelFile extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        handleExcelFile(request, response);
    }
    
    private void handleExcelFile(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException{
        
        response.setContentType("text/html");
        
        //calling path from config file
        File filePath = new File(request.getServletContext().getRealPath("/resources/store.xls"));
        
        FileInputStream fis = new FileInputStream(filePath);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<table style = 'border: 4px solid black;padding: 0.5rem;'>");
        out.println("<tbody>");
        out.println("<h1 style='text-align:center'>Excel File Data</h1>");
        
        //sheet->row->cell
        int lastSheetNumber = wb.getNumberOfSheets();
        for(int i=0; i<=lastSheetNumber-1; i++){
            
            HSSFSheet sheet = wb.getSheetAt(i);
                    
            int lastRowNumber = sheet.getLastRowNum();
            for(int j=0; j<=lastRowNumber; j++){
                HSSFRow row = sheet.getRow(j);
                
                int noOfColums = sheet.getRow(j).getLastCellNum();
                out.println("<tr>");
                for(int k=0; k<noOfColums; k++){
                    if(row.getCell(k) != null){
                    String col = row.getCell(k).toString();
                    out.println("<td>" + col + "</td>");
                    }else{}
                }
                out.println("</tr>");
            }
        }
        
        out.println("</tbody>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
        
        fis.close();
    }
    
}
