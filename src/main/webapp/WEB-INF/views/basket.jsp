<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Корзина - Шины для машины</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    <link rel="icon" type="image/x-icon" href="/img/favicon.ico"/>
    <link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
    <script type="text/javascript" src="/js/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="/js/main.js"></script>
</head>

<body>
  <div>
      <a href="/">Главная</a>&nbsp;|&nbsp;<a href="catalog">Каталог</a>
      <h2><strong>Товары в корзине:</strong></h2>
  </div>
  <div>
      ${emptyBasket}
      <c:if test="${emptyBasket == ''}">
          <form action="order" method="POST" id="frmBasket">
          <div>
              <table border="1" align="center">
                  <th>Ширина, мм</th>
                  <th>Профиль, %</th>
                  <th>Радиус</th>
                  <th>Марка</th>
                  <th>Зимние</th>
                  <th>Количество</th>
                  <th></th>
                  <c:forEach var="tire" items="${tireList}">
                      <tr>
                          <td>${tire.get(0).getWidth()}</td>
                          <td>${tire.get(0).getPercentHeight()}</td>
                          <td>${tire.get(0).getRadius()}''</td>
                          <td>${tire.get(0).getBrand()}</td>
                          <td>
                              <c:if test="${tire.get(0).getIsWinter() == true}">
                                  <c:out value="Да"/>
                              </c:if>
                          </td>
                          <td>
                              <input type="hidden" id="tireId" name="tireId" value="${tire.get(0).getId()}"/>
                              <input type="text" id="tireCnt" name="tireCnt" value="${tire.get(1)}" style="width: 100px;">
                          </td>
                          <td><a href="del?id=${tire.get(0).getId()}">Удалить</a></td>
                      </tr>
                  </c:forEach>
              </table>
          </div>
          <br>
          <div>
              <a href="clear">Очистить корзину</a>&nbsp;|&nbsp;<a href="#" onclick="CheckBasket();">Перейти к оформлению заказа</a>
          </div>
          </form>
      </c:if>
  </div>
</body>
</html>
