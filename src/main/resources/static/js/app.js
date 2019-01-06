$(document).ready(function () {
    $(".sidenav").each(function (index) {
        $(this).parent().removeClass('active');
        if (window.location.pathname === $(this).attr('href')) {
            $(this).parent().addClass('active');
        }
    });
});