<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Заказ - Шины для машины</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    <link rel="icon" type="image/x-icon" href="/img/favicon.ico"/>
    <link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
    <script type="text/javascript" src="/js/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="/js/main.js"></script>
</head>

<body>
<div>
    <a href="/">Главная</a>&nbsp;|&nbsp;<a href="catalog">Каталог</a>&nbsp;|&nbsp;<a href="basket">Корзина</a>
    <c:if test="${orderId != null}">
        <h2><strong>Заказ оформлен</strong></h2>
    </c:if>
    <c:if test="${orderId == null}">
        <h2><strong>Оформление заказа</strong></h2>
    </c:if>
</div>
<div>
<c:if test="${orderId != null}">
    Благодарим Вас за заказ!!!<br>
    В ближайшее время с Вами свяжется наш менеджер и будет выслана дополнительная информация на указанный адрес электронной почты.<br>
    Номер Вашего заказа: <font color="red">${orderId}</font><br><br>
    <a href="catalog">Перейти к выбору товаров</a>
</c:if>
<c:if test="${orderId == null}">
    <table border="0" width="50%" align="center">
        <c:forEach var="tire" items="${tireList}">
            <tr align="left"><td>
                <c:out value="${tire.get(0).getBrand()} ${tire.get(0).getWidth()}/${tire.get(0).getPercentHeight()} "/>
                <c:out value="R${tire.get(0).getRadius()}"/>
                <c:if test="${tire.get(0).getIsWinter() == true}">
                    <c:out value=" (зимние)"/>
                </c:if>
                <c:out value=" - ${tire.get(1)} шт."/>
            </td></tr>
        </c:forEach>
        <tr align="center"><td>
            <input type="button" value="Вернуться в корзину" onclick="window.location.href='basket';"/>
        </td></tr>
    </table>
    <hr width="70%" align="center">
    <div style="font-size: x-small">Поля, помеченные знаком «<font color="red">*</font>», обязательны для заполнения.</div>
    <form action="take" id="frmOrder" method="post">
        <table border="0" width="50%" align="center" style="min-width: 300px;">
            <tr align="left">
                <td>
                    <font color="red">*</font> Фамилия Имя Отчество:<br>
                    <input type="text" id="fio" name="fio" value=""/>
                </td>
            </tr>
            <tr align="left">
                <td>
                    <font color="red">*</font> Мобильный телефон (в формате X-XXX-XXX-XX-XX):<br>
                    <input type="text" id="phone" name="phone" value=""/>
                </td>
            </tr>
            <tr align="left">
                <td>
                    <font color="red">*</font> Адрес электронной почты:<br>
                    <input type="text" id="email" name="email" value=""/>
                </td>
            </tr>
            <tr align="left">
                <td>
                    <font color="red">*</font> Тип доставки:<br>
                    <select id="delivery" name="delivery" onkeyup="changeDelivery(this.value);" onclick="changeDelivery(this.value);">
                        <option value="0" selected>Самовывоз</option>
                        <option value="1">Почта России</option>
                        <option value="2">ТК ПЭК</option>
                        <option value="3">ТК ЖелДорЭкспедиция</option>
                    </select>
                </td>
            </tr>
            <tr align="left">
                <td>
                    <font color="red">*</font> Адрес доставки:<br>
                    <input type="text" id="address" name="address" value="" disabled/>
                </td>
            </tr>
            <tr align="left">
                <td>
                    <font color="red">*</font> Способ оплаты:<br>
                    <select id="pay" name="pay">
                        <option value="0">Наличными при получении</option>
                        <option value="1" selected>Банковской картой (Visa, Mastercard)</option>
                        <option value="2">Банковским переводом</option>
                    </select>
                </td>
            </tr>
            <tr align="left">
                <td>
                    Комментарий к заказу:<br>
                    <textarea id="comment" name="comment" rows="2" style="height: 100px;"></textarea>
                </td>
            </tr>
            <tr align="center">
                <td>
                    <input id="submitOrderButton" type="button" value="Оформить заказ" onclick="submitOrder();"/>
                </td>
            </tr>
        </table>
    </form>
</c:if>
</div>
</body>
</html>
