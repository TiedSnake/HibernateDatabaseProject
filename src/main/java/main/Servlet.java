package main;

import com.to.AProduct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author m-w-n
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet
{
    List<AProduct> products = new ArrayList<>();
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

    public void hibernateGet(HttpServletRequest req)
    {
        try
        {
            Configuration config = new Configuration().configure(new File(req.getRealPath("/resources/hibernate.cfg.xml")));
            config.addAnnotatedClass(AProduct.class);
//            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure(new File(req.getRealPath("hibernate.cfg.xml"))).build();
StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
//            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
//            SessionFactory factory = meta.getSessionFactoryBuilder().build();
            SessionFactory factory = config.buildSessionFactory(builder.build());
            Session session = factory.openSession();
            products = session.createQuery("from AProduct").list();
            session.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void hibernateSend(HttpServletRequest req, AProduct product)
    {
        try
        {
            Configuration config = new Configuration().configure(new File(req.getRealPath("/resources/hibernate.cfg.xml")));
            config.addAnnotatedClass(AProduct.class);
//            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure(new File(req.getRealPath("hibernate.cfg.xml"))).build();
StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
//            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
//            SessionFactory factory = meta.getSessionFactoryBuilder().build();
            SessionFactory factory = config.buildSessionFactory(builder.build());
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            session.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getParameter("Register") != null)
        {
//            Configuration config = new Configuration().configure(new File(req.getRealPath("/resources/hibernate.cfg.xml")));
//            config.addAnnotatedClass(AProduct.class);
////            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure(new File(req.getRealPath("hibernate.cfg.xml"))).build();
//StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
////            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
////            SessionFactory factory = meta.getSessionFactoryBuilder().build();
//            SessionFactory factory = config.buildSessionFactory(builder.build());
//            Session session = factory.openSession();
//            Transaction transaction = session.beginTransaction();
            HttpSession ssession = req.getSession();
            AProduct product = new AProduct();
            product.setPrID(Integer.parseInt(req.getParameter("prID")));
            product.setPrName(req.getParameter("prName"));
            product.setProductCategory(req.getParameter("ProductCategory"));
            product.setPrPrice(Float.parseFloat(req.getParameter("prPrice")));
//            session.save(product);
//            transaction.commit();
//            products = session.createQuery("from AProduct").list();
            if (exist(products, product))
            {
                req.setAttribute("success", null);
//                req.setAttribute("success", null);
//                req.setAttribute("fail", null);
                req.setAttribute("color", "red");
                req.setAttribute("message", "There is already a registered product with this ID");
                req.getRequestDispatcher("/views/Register_Product.jsp").forward(req, resp);
            } else
            {
                hibernateSend(req, product);
//                req.setAttribute("success", null);
//                req.setAttribute("fail", null);
//                req.setAttribute("success", "Product has been added");
                req.setAttribute("color", "blue");
                req.setAttribute("message", "Product has been added");
                req.getRequestDispatcher("/views/Register_Product.jsp").forward(req, resp);
            }
        }
        if (req.getParameter("ProductList") != null)
        {
            hibernateGet(req);
            req.setAttribute("products", products);
            req.getRequestDispatcher("/views/ProductList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }
}