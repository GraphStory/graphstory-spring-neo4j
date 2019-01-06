// INSPINIA Landing Page Custom scripts
$(document).ready(function () {

    // Highlight the top nav as scrolling


    // Page scrolling feature
    $('a.page-scroll').bind('click', function(event) {
        var link = $(this);
        $('html, body').stop().animate({
            scrollTop: $(link.attr('href')).offset().top - 70
        }, 500);
        event.preventDefault();
    });

    $('.carousel').carousel({
        interval: false
    });

});

// Activate WOW.js plugin for animation on scrol
new WOW().init();