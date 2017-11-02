<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Каталог - Шины для машины</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    <link rel="icon" href="/img/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon"/>
</head>

<body>
  <div>
      <a href="/">Главная</a>&nbsp;|&nbsp;<a href="basket">Корзина</a>
      <h2><strong>Каталог товаров:</strong></h2>
  </div>
  <div>
      <table border="1">
          <th>ID</th>
          <th>Ширина</th>
          <th>Высота профиля, %</th>
          <th>Радиус</th>
          <th>Марка</th>
          <th>Зимние</th>
          <c:forEach items="${tireList}" var="tire">
              <tr>
                  <td>${tire.id}</td>
                  <td>${tire.width}</td>
                  <td>${tire.percentHeight}</td>
                  <td>${tire.radius}</td>
                  <td>${tire.brand}</td>
                  <td>
                      <c:if test="${tire.isWinter = 1}">
                        Да
                      </c:if>
                  </td>
              </tr>
          </c:forEach>
      </table>
  </div>
</body>
</html>
