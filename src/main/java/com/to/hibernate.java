/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.to;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class hibernate
{

    Configuration config;

    StandardServiceRegistryBuilder builder;
    SessionFactory factory;
    Session session;
    Transaction transaction;

    public hibernate(String path)
    {
        try
        {
            this.config = new Configuration().configure(new File(path));
            this.config.addAnnotatedClass(AProduct.class);
            this.builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            this.factory = config.buildSessionFactory(builder.build());
            this.session = factory.openSession();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<AProduct> GetProductList()
    {
        List<AProduct> products = new ArrayList<>();
        try
        {
            this.session = factory.openSession();
            products = session.createQuery("from AProduct").list();
            this.session.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return products;
    }

    public void addProduct(AProduct product)
    {
        try
        {
            session = factory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            this.session.save(product);
            this.session.flush();
            transaction.commit();
            session.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
