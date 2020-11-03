
// авторизация в меню
$(document).ready(function() {
    if($(".userType").text().localeCompare("") != 0) {
        $("#authorization").hide();
        $("#signOut").show();
    }
    else {
        $("#authorization").show();
        $("#signOut").hide();
    }

    if($(".userType").text().localeCompare("Покупатель") == 0)
        $(".userLink").attr("th:href", "@{'/infoClient/'+${currentUser.userId}}")
    if($(".userType").text().localeCompare("Менеджер") == 0)
        $(".userLink").attr("th:href", "@{'/infoManager/'+${currentUser.userId}}")
});

$('.buttonClean').on('click', function(){
    $('.nameArtist').val("");
    $('.musicStyle').val("не выбрано");
    $('.country').val("");
    $('.foundationYear').val("");
    $('.selectArtist').val(1);
    $('.selectLocation').val(1);
    $('.dateAddConcert').val("2020-05-10");
    $('.time').val("19:00");
    $('.number').val("0");
    $('.price').val("0.0");
    $('.locationName').val("");
    $('.address').val("");
    $('.nameUser').val("");
    $('#selectLocation').val("не выбрано");
    $('#dateFilter').val("");
    $('.artistCountry').val("не выбрано");
});

$('#buttonCleanFilter').on('click', function(){
    $('#musicStyleF').val("не выбрано");
    $('#artistCountryF').val("не выбрано");
    $('#selectLocation').val("не выбрано");
    $('#dateFilter').val("");
});


//добавление пользователя
$('#userTypeAdd').on('click', function() {
    let userType = $("#userTypeAdd").val();
    if (userType.localeCompare("Менеджер") == 0) {
        $("#addClient").hide();
        $("#addManager").show();
        $("#addManagerClientPhone").show();
        $('#telM').val("");
    } else {
        $("#addManager").hide();
        if (userType.localeCompare("Покупатель") == 0) {
            $('#email').val("");
            $("#addManagerClientPhone").show();
            $("#addClient").show();
        } else {
            $("#addClient").hide();
            $("#addManager").hide();
            $("#addManagerClientPhone").hide();
    }}
    if(userType.localeCompare("Администратор") == 0) {
        $("#addClient").hide();
        $("#addManager").hide();
        $("#addManagerClientPhone").hide();
    }
});

let flagManager = 0;
let flagClient = 0;
let flagAdmin = 0;

$('#clickManagerInfoList').on('click', function() {
    if (flagManager == 0) {
        $("#managerInfoList").show();
        $("#clientInfoList").hide();
        $("#adminInfoList").hide();
        flagManager = 1;
        flagClient = 0;
        flagAdmin = 0;
    } else {
        $("#managerInfoList").hide();
        flagManager = 0;
    }
});

$('#clickClientInfoList').on('click', function() {
    if (flagClient == 0) {
        $("#clientInfoList").show();
        $("#managerInfoList").hide();
        $("#adminInfoList").hide();
        flagManager = 0;
        flagClient = 1;
        flagAdmin = 0;
    } else {
        $("#clientInfoList").hide();
        flagClient = 0;
    }
});

$('#clickAdminInfoList').on('click', function() {
    if (flagAdmin == 0) {
        $("#adminInfoList").show();
        $("#clientInfoList").hide();
        $("#managerInfoList").hide();
        flagManager = 0;
        flagClient = 0;
        flagAdmin = 1;
    } else {
        $("#adminInfoList").hide();
        flagAdmin = 0;
    }
});


//редактирование пользователя
$(document).ready(function () {
    if ($("#editTypeUser").val().localeCompare("Менеджер") == 0){
        $("#managerEditField").show();
    }
    else {
        $("#managerEditField").hide();
    }
});

$(document).ready(function () {
    if ($("#editTypeUser").val().localeCompare("Покупатель") == 0){
        $("#clientEditField").show();
    }
    else {
        $("#clientEditField").hide();
    }
});

$(document).ready(function () {
    if ($("#editTypeUser").val().localeCompare("Покупатель") == 0 || $("#editTypeUser").val().localeCompare("Менеджер") == 0){
        $("#ManagerClientPhoneEditField").show();
    }
    else {
        $("#ManagerClientPhoneEditField").hide();
    }
});

//загрузка файла
(function() {
    'use strict';
    $('.mandinnen-domeeting').each(function() {
        var $input = $(this),
            $label = $input.next('.js-sanedemandin'),
            labelVal = $label.html();
        $input.on('change', function(element) {
            var dsunoemanek = '';
            if (element.target.value) dsunoemanek = element.target.value.split('\\').pop();
            dsunoemanek ? $label.addClass('has-file').find('.js-dsunoemanek').html(dsunoemanek) : $label.removeClass('has-file').html(labelVal);
        });
    });
})();

