<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/style.css" type="text/css"/>
        <title>Products List</title>
    </head>
    <body>
        <header>
            <a href="Servlet2"><h1>Product List</h1></a>
            <nav>
                <a href="<%=request.getContextPath()%>/views/Register_Product.jsp"><h1>Product Registration</h1></a>
            </nav>
        </header>
        <table>
            <tr>
                <th colspan="7"><h3>Product Details</h3></th>
            </tr>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>Product Price</th>
                <th>Product Registration Date</th>
            </tr>
            <c:forEach items='${products}' var="product">
                <tr>
                    <th>${product.getPrID()}</th>
                    <th>${product.getPrName()}</th>
                    <th>${product.getProductCategory()}</th>
                    <th>${product.getPrPrice()}</th>
                    <th>${product.getPdate()}</th>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>