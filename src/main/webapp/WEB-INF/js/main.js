function changeDelivery (type) {
    var addrObj = $("#address");
    if (type == 0) {
        addrObj.val("");
        addrObj.attr("disabled","disabled");
    }
    else
        addrObj.removeAttr("disabled");
}

function submitOrder () {
    var fio = $("#fio");
    var phone = $("#phone");
    var email = $("#email");
    var delivery = $("#delivery");
    var address = $("#address");

    if (fio.val() == "") {
        alert('Поле "ФИО" не заполнено');
        fio.focus();
        return;
    }
    if (phone.val() == "") {
        alert('Поле "Телефон" не заполнено');
        phone.focus();
        return;
    }
    if (email.val() == "") {
        alert('Поле "Адрес электронной почты" не заполнено');
        email.focus();
        return;
    }
    if (delivery.val() > 0 && address.val() == "") {
        alert('Поле "Адрес доставки" не заполнено');
        address.focus();
        return;
    }

    var phone_pattern = /^[1-9]{1}-[\d]{3}-[\d]{3}-[\d]{2}-[\d]{2}$/;
    if (!phone_pattern.test(phone.val())) {
        alert('Введен некорректный номер мобильного телефона');
        phone.focus();
        return;
    }
    var adr_pattern = /^[\w]{1}[\w-\.]*@[\w-]+\.[a-z]{2,3}$/i;
    if (!adr_pattern.test(email.val())) {
        alert('Введен некорректный адрес электронной почты');
        email.focus();
        return;
    }

    $("#frmOrder").submit();
}

function CheckBasket () {
    var checkPattern = /^[0-9]+$/;
    var badValue = false;
    $("input[type='text'][name='tireCnt']").map(function() {
        if (!badValue && (!checkPattern.test($(this).val()) || $(this).val() == 0)) {
            alert("Введено некорректное количество товара");
            $(this).focus();
            $(this).select();
            badValue = true;
        }
    });
    if (!badValue) $('#frmBasket').submit();
}