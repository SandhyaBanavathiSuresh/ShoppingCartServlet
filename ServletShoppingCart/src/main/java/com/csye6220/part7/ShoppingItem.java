package com.csye6220.part7;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sandhya
 */
public class ShoppingItem extends HttpServlet {

    protected void handleItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");

            out.println("<head>");
            out.println("<style>");
            out.println(".hidden {");
            out.println("display: none;}");
            out.println(".main{");
            out.println("display: flex;}");
            out.println("</style>");
            out.println("</head>");

            out.println("<body>");
            out.println("<form method='POST'>");

            out.println("<h1>Lets begin Shopping</h1>");
            out.println("<div class='main'>");

            out.println("<div style='margin-right: 2rem'>");
            out.println("<h4>Categories</h4>");
            out.println("<ul>");
            out.println("<li onclick='showBooks()'>Books</li>");
            out.println("<li onclick='showMusic()'>Music</li>");
            out.println("<li onclick='showComputer()'>Computer</li>");
            out.println("</ul>");
            out.println("</div>");

            out.println("<div id='books' class='hidden'>");
            out.println("<div>");
            out.println("<h4>Shop for Books</h4>");
            out.println("<hr>");
            out.println("<input type='checkbox' name='books' value='Java'>Java");
            out.println("<input type='checkbox' name='books' value='Oracle'>Oracle");
            out.println("<input type='checkbox' name='books' value='JavaScript'>JavaScript");
            out.println("<input type='checkbox' name='books' value='React'>React");
            out.println("<br>");
            out.println("<input type='submit' value='Add to cart'>");
            out.println("</div>");
            out.println("</div>");

            out.println("<div id='music' class='hidden'>");
            out.println("<div>");
            out.println("<h4>Shop for Music</h4>");
            out.println("<hr>");
            out.println("<input type='checkbox' name='music' value='Music 1'>Music 1");
            out.println("<input type='checkbox' name='music' value='Music 2'>Music 2");
            out.println("<input type='checkbox' name='music' value='Music 3'>Music 3");
            out.println("<input type='checkbox' name='music' value='Music 4'>Music 4");
            out.println("<br>");
            out.println("<input type='submit' value='Add to cart'>");
            out.println("</div>");
            out.println("</div>");

            out.println("<div id='computer' class='hidden'>");
            out.println("<div>");
            out.println("<h4>Shop for Computer</h4>");
            out.println("<hr>");
            out.println("<input type='checkbox' name='computer' value='Apple Mac Book'>Apple Mac book");
            out.println("<input type='checkbox' name='computer' value='HP'>HP");
            out.println("<input type='checkbox' name='computer' value='Dell'>Dell");
            out.println("<input type='checkbox' name='computer' value='Lenovo'>Lenovo");
            out.println("<br>");
            out.println("<input type='submit' value='Add to cart'>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");

            //to get all selected checkbox values
            String[] selectedBooks = request.getParameterValues("books");
            String[] selectedMusic = request.getParameterValues("music");
            String[] selectedComputer = request.getParameterValues("computer");

            //creating a session to store all the selected items
            HttpSession session = request.getSession();

            //creating a list of items selected
            List<String> cartItems = (List<String>) session.getAttribute("cartItems");

            if (cartItems == null) {
                cartItems = new ArrayList<>();
            }

            if (selectedBooks != null) {
                for (String book : selectedBooks) {
                    cartItems.add("Books: " + book);
                }
            }

            if (selectedMusic != null) {
                for (String music : selectedMusic) {
                    cartItems.add("Musics: " + music);
                }
            }

            if (selectedComputer != null) {
                for (String computer : selectedComputer) {
                    cartItems.add("Computers: " + computer);
                }
            }

            // Handle removal of items
            String[] itemsToRemove = request.getParameterValues("removeItem");
            if (itemsToRemove != null) {
                for (String item : itemsToRemove) {
                    cartItems.remove(item);
                }
            }

            //setting the items into session
            session.setAttribute("cartItems", cartItems);

            out.println("<div id='cart'>");
            out.println("<h2>Shopping Cart List</h2>");

            // Displaying selected items from the session
            if (!cartItems.isEmpty()) {
                out.println("<ul>");
                for (String item : cartItems) {
                    out.println("<li>" + item + " <button type='submit' name='removeItem' value='" + item + "'>Remove</button></li>");
                }
                out.println("</ul>");
            } else {
                out.println("<p>Your cart is empty.</p>");
            }
            out.println("Click on categories to continue shopping");

            out.println("</div>");
            out.println("</form>");

            out.println("<script>");
            out.println("function showBooks() {");
            out.println("document.getElementById('books').style.display = 'block';");
            out.println("document.getElementById('music').style.display = 'none';");
            out.println("document.getElementById('computer').style.display = 'none';");
            out.println("}");

            out.println("function showMusic() {");
            out.println("document.getElementById('books').style.display = 'none';");
            out.println("document.getElementById('music').style.display = 'block';");
            out.println("document.getElementById('computer').style.display = 'none';");
            out.println("}");

            out.println("function showComputer() {");
            out.println("document.getElementById('books').style.display = 'none';");
            out.println("document.getElementById('music').style.display = 'none';");
            out.println("document.getElementById('computer').style.display = 'block';");
            out.println("}");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleItem(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleItem(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
