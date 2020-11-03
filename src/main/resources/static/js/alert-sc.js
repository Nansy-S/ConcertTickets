
//покупка билетов

$('#buttonBuyTicketFan').on('click', function(){
    $('.id').val(1234);
    $('.buyTconId').val($('.cId').val());
    $('#buyFan').val("фан-зона");
    $('.buyPrice').val($('#buyFPrice').text());
    setTimeout(swal({
        title: "Покупка прошла успешно.",
        text: "Вы купили билет в фан-зону.",
        icon: "success",
        button: "ОК",
        timer: 16000000,
    }), 16000000);
});

$('#buttonBuyTicketDancefloor').on('click', function(){
    $('.id').val(1234);
    $('.buyTconId').val($('.cId').val());
    $('#buyDancefloor').val("танцпол");
    $('.buyPrice').val($('#buyDfPrice').text());
    setTimeout(swal({
        title: "Покупка прошла успешно.",
        text: "Вы купили билет на танцпол.",
        icon: "success",
        button: "ОК",
        timer: 16000000,
    }), 16000000);
});

$('#buttonBuyTicketTribune').on('click', function(){
    $('.id').val(1234);
    $('.buyTconId').val($('.cId').val());
    $('#buyTribune').val("трибуны");
    $('.buyPrice').val($('#buyTrPrice').text());
    setTimeout(swal({
        title: "Покупка прошла успешно.",
        text: "Вы купили билет на трибуны.",
        icon: "success",
        button: "ОК",
        timer: 16000000,
    }), 16000000);
});

// добавление инфы

$('#addArtist').on('click', function(){
    if($('#hasErrorsAddArtist').val() == false) {
        setTimeout(swal({
            title: "Артист успешно добавлен.",
            icon: "success",
            button: "ОК",
            timer: 16000000,
        }), 16000000);
    }
});

$('#addInfoConcertAlert').on('click', function(){
    if($('#hasErrorsAddConcert').val() == false) {
        setTimeout(swal({
            title: "Концерт успешно добавлен.",
            icon: "success",
            button: "ОК",
            timer: 16000000,
        }), 16000000);
    }
});

$('#addInfoLocation').on('click', function(){
    if($('#hasErrorsAddLocation').val() == false) {
        setTimeout(swal({
            title: "Площадка успешно добавлена.",
            icon: "success",
            button: "ОК",
            timer: 16000000,
        }), 16000000);
    }
});

$('#addUserApp').on('click', function(){

    let userType = $("#userTypeAdd").val();
    if (userType === "Менеджер") {
        $('#telC').val("11-111-11-11");
        $('#email').val("gmail@gmail.com");
    } else {
        if (userType === "Покупатель") {
            $('#telM').val("11-111-11-11");
        }
        else if (userType === "Администратор"){
            $('#telM').val("11-111-11-11");
            $('#telC').val("11-111-11-11");
            $('#email').val("gmail@gmail.com");
        }
    }

    if($('#hasErrorsAddUser').val() == false) {
        setTimeout(swal({
            title: "Пользователь успешно добавлен.",
            icon: "success",
            button: "ОК",
            timer: 16000000,
        }), 16000000);
    }
});

$('#registrationClient').on('click', function(){
    if($('#hasErrorsRegistr').val() == false) {
        setTimeout(swal({
            title: "Регистрация прошла успешно.",
            icon: "success",
            button: "ОК",
            timer: 16000000,
        }), 16000000);
    }
});

// удаление

$('#deleteArtist').on('click', function(){
    setTimeout(swal({
        title: "Артист успешно удален.",
        icon: "success",
        button: "ОК",
        timer: 16000000,
    }), 16000000);
});

$('#deleteConcert').on('click', function(){
    setTimeout(swal({
        title: "Концерт успешно удален.",
        icon: "success",
        button: "ОК",
        timer: 16000000,
    }), 16000000);
});

$('#deleteLoc').on('click', function(){
    setTimeout(swal({
        title: "Площадка успешно удалена.",
        icon: "success",
        button: "ОК",
        timer: 16000000,
    }), 16000000);
});

$('#deleteUserD').on('click', function(){
    setTimeout(swal({
        title: "Пользователь успешно удален.",
        icon: "success",
        button: "ОК",
        timer: 16000000,
    }), 16000000);
});

//редактирование
$('.save').on('click', function(){
    setTimeout(swal({
        title: "Информация отредактирована.",
        icon: "success",
        button: "ОК",
        timer: 16000000,
    }), 16000000);
});