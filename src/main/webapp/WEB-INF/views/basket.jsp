<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Корзина - Шины для машины</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    <link rel="icon" href="/img/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon"/>
</head>

<body>
  <div>
      <a href="/">Главная</a>&nbsp;|&nbsp;<a href="catalog">Каталог</a>
      <h2><strong>Товары в корзине:</strong></h2>
  </div>
  <div>
      ${emptyBasket}
      <c:if test="${emptyBasket == ''}">
          <div>
              <table border="1" align="center">
                  <th>Ширина, мм</th>
                  <th>Профиль, %</th>
                  <th>Радиус</th>
                  <th>Марка</th>
                  <th>Зимние</th>
                  <th>Количество</th>
                  <th></th>
                  <c:forEach items="${tireList}" var="tire">
                      <tr>
                          <td>${tire.getWidth()}</td>
                          <td>${tire.getPercentHeight()}</td>
                          <td>${tire.getRadius()}<c:out value="''"/></td>
                          <td>${tire.getBrand()}</td>
                          <td>
                              <c:if test="${tire.getIsWinter() == true}">
                                  <c:out value="Да"/>
                              </c:if>
                          </td>
                          <td><input type="text" readonly value="4" id="q_${tire.getId()}" name="q_${tire.getId()}"></td>
                          <td><a href="del?id=${tire.getId()}">Удалить</a></td>
                      </tr>
                  </c:forEach>
              </table>
          </div>
          <br>
          <div>
              <a href="clear">Очистить корзину</a>&nbsp;|&nbsp;<a href="order">Оформить заказ</a>
          </div>
      </c:if>
  </div>
</body>
</html>
