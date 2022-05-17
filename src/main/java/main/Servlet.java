package main;

import com.to.AProduct;
import com.to.hibernate;
import java.io.IOException;
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
@WebServlet("/Servlet")
public class Servlet extends HttpServlet
{

    public boolean exist(List<AProduct> products, AProduct product)
    {
        boolean b = false;
        if (products.isEmpty())
        {
            b = false;
        } else
        {
            for (AProduct p : products)
            {
                if (p.getPrID() == product.getPrID())
                {
                    b = true;
                }
            }
        }
        return b;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getParameter("Register") != null)
        {
            try
            {
                hibernate h = new hibernate(req.getRealPath("/resources/hibernate.cfg.xml"));
                AProduct product = new AProduct();
                product.setPrID(Integer.parseInt(req.getParameter("prID")));
                product.setPrName(req.getParameter("prName"));
                product.setProductCategory(req.getParameter("ProductCategory"));
                product.setPrPrice(Float.parseFloat(req.getParameter("prPrice")));
                List<AProduct> products = new ArrayList<>();
                products = h.GetProductList();
                if (exist(products, product))
                {
                    req.setAttribute("color", "red");
                    req.setAttribute("message", "There is already a registered product with this ID");
                    req.getRequestDispatcher("/views/Register_Product.jsp").forward(req, resp);
                } else
                {
                    h.addProduct(product);
                    req.setAttribute("color", "blue");
                    req.setAttribute("message", "Product has been added");
                    req.getRequestDispatcher("/views/Register_Product.jsp").forward(req, resp);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }else
        {
            req.getRequestDispatcher("/views/Register_Product.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }
}
