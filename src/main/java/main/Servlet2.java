package main;

import com.to.AProduct;
import com.to.hibernate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author m-w-n
 */
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet
{

    List<AProduct> products = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            hibernate h = new hibernate(req.getRealPath("/resources/hibernate.cfg.xml"));
            List<AProduct> products = new ArrayList<>();
            products = h.GetProductList();
            req.setAttribute("products", products);
//        resp.sendRedirect("/views/ProductList.jsp");
            req.getRequestDispatcher("/views/ProductList.jsp").forward(req, resp);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }
}
