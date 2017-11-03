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
      <a href="/">Главная</a>&nbsp;|&nbsp;<a href="basket">Корзина${basketCapacity}</a>
      <h2><strong>Каталог товаров:</strong></h2>
  </div>
  <div>
      <table width="100%">
          <tr align="left" valign="top"><td width="30%">
              <form:form action="search" method="POST" commandName="tire">
              <table border="0">
                  <tr>
                      <td>Ширина</td>
                      <td>
                          <form:select path="width">
                              <form:option value="0" label="Не важно"/>
                              <form:options items="${widthList}"/>
                          </form:select>
                      </td>
                  </tr>
                  <tr>
                      <td>Профиль</td>
                      <td>
                          <form:select path="percentHeight">
                              <form:option value="0" label="Не важно"/>
                              <form:options items="${heightList}"/>
                          </form:select>
                      </td>
                  </tr>
                  <tr>
                      <td>Радиус</td>
                      <td>
                          <form:select path="radius">
                              <form:option value="0" label="Не важно"/>
                              <form:options items="${radiusList}"/>
                          </form:select>
                      </td>
                  </tr>
                  <tr>
                      <td>Марка</td>
                      <td>
                          <form:select path="brand">
                              <form:option value="" label="Не важно"/>
                              <form:options items="${brandList}"/>
                          </form:select>
                      </td>
                  </tr>
                  <tr>
                      <td>Зимние</td>
                      <td>
                          <form:select path="isWinter">
                              <form:option value="" label="Не важно"/>
                              <form:option value="1" label="Да"/>
                              <form:option value="0" label="Нет"/>
                          </form:select>
                      </td>
                  </tr>
                  <tr>
                      <td colspan="2">
                          <input type="submit" name="action" value="Сбросить фильтр" />
                          <input type="submit" name="action" value="Найти" />
                      </td>
                  </tr>
              </table>
              </form:form>
          </td>
          <td>
              <table border="1" width="100%">
                  <th>Ширина, мм</th>
                  <th>Профиль, %</th>
                  <th>Радиус</th>
                  <th>Марка</th>
                  <th>Зимние</th>
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
                          <td><a href="get?id=${tire.getId()}">В корзину</a></td>
                      </tr>
                  </c:forEach>
              </table>
          </td></tr>
      </table>
  </div>
</body>
</html>
