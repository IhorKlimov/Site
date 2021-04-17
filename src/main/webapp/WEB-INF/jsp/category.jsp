<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Store Database</title>
    <link rel="stylesheet" type="text/css" href="../css/mdb.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body>
<%@include file="header.jspf" %>
<section>
    <table class="movies-table">
        <thead>
        <tr>
            <c:if test="${parentCategory == null}">
                <th>Categories</th>
            </c:if>
            <c:if test="${parentCategory != null}">
                <th>Sub-categories</th>
            </c:if>
            <th>Number of items</th>
            <c:if test="${!empty user}">
                <th>Actions</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:if test="${categories == null}">
            <tr>
                <td colspan="100%">Nothing yet</td>
            </tr>
        </c:if>
        <c:forEach var="category" items="${categories}">
            <tr>
                <c:if test="${!empty user && category.categoryId == param.editCategoryId}">
                    <td colspan="100%">
                        <form style="width: 100%;" action="editCategory" method="POST">
                            <div class="username">Edit category</div>
                            <c:if test="${parentCategory != null}">
                                <input type="hidden" name="parentCategoryId" value="${parentCategory.categoryId}"/>
                            </c:if>
                            <input type="hidden" name="categoryId" value="${category.categoryId}"/>
                            <textarea class="boxsizingBorder" name="text">${category.title}</textarea>
                            <input type="submit" value="Save"/>
                        </form>
                    </td>
                </c:if>
                <c:if test="${empty user || category.categoryId != param.editCategoryId}">
                    <td>
                        <a href="category?categoryId=${category.categoryId}">${category.title}</a>
                    </td>
                    <td>${category.getNumberOfItems()}</td>
                    <c:if test="${!empty user}">
                        <td>
                            <form method="GET" style="display: contents">
                                <c:if test="${parentCategory != null}">
                                    <input type="hidden" name="categoryId" value="${parentCategory.categoryId}"/>
                                </c:if>
                                <input type="hidden" name="editCategoryId" value="${category.categoryId}"/>
                                <button type="submit"><i class="fa fa-edit"></i></button>
                            </form>
                            <form action="deleteCategory" method="POST" style="display: contents">
                                <input type="hidden" name="categoryId" value="${category.categoryId}"/>
                                <button type="submit"><i class="fa fa-trash"></i></button>
                            </form>
                        </td>
                    </c:if>
                </c:if>
            </tr>
        </c:forEach>
        <c:if test="${!empty user}">
            <tr style="margin-top: 30px">
                <td colspan="100%">
                    <form style="width: 100%; margin-top: 10px; margin-bottom: 10px" action="createCategory"
                          method="POST">
                        <div class="username">Create category</div>
                        <input type="hidden" name="categoryId" value="${parentCategory.categoryId}"/>
                        <textarea class="boxsizingBorder" name="text" placeholder="Enter title..."></textarea>
                        <input type="submit" value="Create"/>
                    </form>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <c:if test="${parentCategory != null}">
        <table class="movies-table" style="margin-top: 50px">
            <thead>
            <tr>
                <th>Items</th>
                <c:if test="${!empty user}">
                    <th>Actions</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${parentCategory.items == null || parentCategory.items.isEmpty()}">
                <tr>
                    <td colspan="100%">Nothing yet</td>
                </tr>
            </c:if>
            <c:forEach var="item" items="${parentCategory.items}">
                <tr>
                    <c:if test="${!empty user && item.itemId == param.editItemId}">
                        <td colspan="100%">
                            <form style="width: 100%;" action="editItem" method="POST">
                                <div class="username">Edit item</div>
                                <c:if test="${parentCategory != null}">
                                    <input type="hidden" name="categoryId" value="${parentCategory.categoryId}"/>
                                </c:if>
                                <input type="hidden" name="itemId" value="${item.itemId}"/>
                                <textarea class="boxsizingBorder" name="maker">${item.maker}</textarea>
                                <textarea class="boxsizingBorder" name="model">${item.model}</textarea>
                                <textarea class="boxsizingBorder" name="description">${item.description}</textarea>
                                <input type="submit" value="Save"/>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${empty user || item.itemId != param.editItemId}">
                        <td>
                            <a href="item?itemId=${item.itemId}">${item.maker} ${item.model}. ${item.description}</a>
                        </td>
                        <c:if test="${!empty user}">
                            <td>
                                <form method="GET" style="display: contents">
                                    <c:if test="${parentCategory != null}">
                                        <input type="hidden" name="categoryId" value="${parentCategory.categoryId}"/>
                                    </c:if>
                                    <input type="hidden" name="editItemId" value="${item.itemId}"/>
                                    <button type="submit"><i class="fa fa-edit"></i></button>
                                </form>
                                <form action="deleteItem" method="POST" style="display: contents">
                                    <input type="hidden" name="itemId" value="${item.itemId}"/>
                                    <button type="submit"><i class="fa fa-trash"></i></button>
                                </form>
                            </td>
                        </c:if>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${!empty user}">
                <tr>
                    <td colspan="100%">
                        <form style="width: 100%;  margin-top: 10px; margin-bottom: 10px" action="createItem"
                              method="POST">
                            <div class="username">Create item</div>
                            <input type="hidden" name="categoryId" value="${parentCategory.categoryId}"/>
                            <textarea class="boxsizingBorder" name="maker" placeholder="Enter maker..."></textarea>
                            <textarea class="boxsizingBorder" name="model" placeholder="Enter model..."></textarea>
                            <textarea class="boxsizingBorder" name="description"
                                      placeholder="Enter description..."></textarea>
                            <input type="submit" value="Create"/>
                        </form>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </c:if>
</section>
<br>
<%@include file="footer.jspf" %>
</body>
</html>
