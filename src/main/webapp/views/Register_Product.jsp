<%-- 
    Document   : home
    Created on : May 8, 2022, 12:28:48 PM
    Author     : m-w-n
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Product Registration</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/style.css" type="text/css"/>
    </head>
    <body>
        <header>
            <h1>Product Registration</h1>
            <nav>
                <form  action="Servlet" method="post">
                    <input name="ProductList" type="submit" value="Product List">
                </form>
            </nav>
        </header>
        <div>
            <form class="f" action="Servlet" method="post">
                <p>
                    <label path="prID">Product ID:</label>
                    <input path="prID" type="number" name="prID" placeholder="Enter Product ID" required="required"/><br>
                </p>
                <p>
                    <label path="prName">Product Name: </label>
                    <input path="prName" type="text" name="prName" placeholder="Enter Product Name" required="required"/><br> 
                </p >
                <p>
                    <label path="ProductCategory">Category:</label>
                    <select path="ProductCategory" name="ProductCategory">
                        <option value="Computers_Tablets">Computers and Tablets</option>
                        <option value="Appliances">Appliances</option>
                        <option value="Toys_Games_Collectibles">Toys, Games and Collectibles</option>
                    </select><br>
                </p>
                <p  >
                    <label path="prPrice">Product Price: </label>
                    <input path="prPrice" type="number" step="0.01" name="prPrice" placeholder="Enter Product Price" required="required"/><br>
                </p>
                <br>
                <br>
                <input class="submit" name="Register" type="submit" value="Register Product"/>
            </form>
        </div>
        <span style="color:${color}">${message}</span><br/>
    </body>
</html>
