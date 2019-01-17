$.ajaxSetup({
    headers: { 'Accept': 'application/json',  'Content-Type': 'application/json' },
    contentType: "application/json",
    dataType: "json"
});

$(document).ready(function () {
    $(".sidenav").each(function (index) {
        $(this).parent().removeClass('active');
        if (window.location.pathname === $(this).attr('href')) {
            $(this).parent().addClass('active');
        }
    });
});

if(document.getElementById("timeline")){
    var timeline = new Vue({
        el: '#timeline',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            like: function (id, e) {

                e.preventDefault();

                var statusId=id;
                var userId=$("#currentUserId").val();

                if($("#"+id).hasClass( "liked" )){
                    $("#"+id).addClass("btn-outline-primary");
                    $("#"+id).addClass("notlikedyet");
                    $("#"+id).removeClass("btn-primary");
                    $("#"+id).removeClass("liked");
                    $("#"+id).text("Like this!");

                    $.get("/api/status/unlike/"+statusId+"/"+userId, function (response) {
                    }).fail(function(response) {
                        var r = jQuery.parseJSON(response.responseText);
                        alert(r.message);
                    });

                }else{
                    $("#"+id).addClass("btn-primary");
                    $("#"+id).addClass("liked");
                    $("#"+id).removeClass("btn-outline-primary");
                    $("#"+id).removeClass("notlikedyet");
                    $("#"+id).text("You liked this");

                    $.get("/api/status/like/"+statusId+"/"+userId, function (response) {
                    }).fail(function(response) {
                        var r = jQuery.parseJSON(response.responseText);
                        alert(r.message);
                    });
                }
            }
        }
    });
}


if(document.getElementById("productspage")){
    var productspage = new Vue({
        el: '#productspage',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            viewed: function (id, e) {

                e.preventDefault();

                var productId=id;
                var userId=$("#currentUserId").val();

                $.get("/api/product/click/"+productId+"/"+userId, function (response) {
                }).fail(function(response) {
                    var r = jQuery.parseJSON(response.responseText);
                    console.log(r.message);
                });
            }
        }

    });
}





if(document.getElementById("profile")){
    var profile = new Vue({
        el: '#profile',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            save: function (id, e) {

                e.preventDefault();
            }
        }
    });
}

if(document.getElementById("productSearch")){
    var productSearch = new Vue({
        el: '#productSearch',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            search: function (id, e) {

                e.preventDefault();


            },
            goToProduct: function (id, e) {
                e.preventDefault();

                // save product view
            }

        }
    });
}



if(document.getElementById("productFinder")){
    var productFinder = new Vue({
        el: '#productFinder',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            search: function (id, e) {

                e.preventDefault();
            },
            goToProduct: function (id, e) {
                e.preventDefault();

                // save product view
            },
            goToLocation: function (id, e) {

                e.preventDefault();

                // save location view

            }
        }
    });
}

if(document.getElementById("location")){
    var location = new Vue({
        el: '#location',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            goToProduct: function (id, e) {
                e.preventDefault();
                // save product view
            },
        }
    });
}

if(document.getElementById("productDetail")){
    var productDetail = new Vue({
        el: '#productDetail',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            like: function (id, e) {
                e.preventDefault();
            }
        }
    });
}

if(document.getElementById("friendFinder")){
    var friendFinder = new Vue({
        el: '#friendFinder',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            search: function (id, e) {

            },
            follow: function (id, e) {
                e.preventDefault();
            },
            unfollow: function (id, e) {

            }
        }
    });
}

if(document.getElementById("console")){
    var console = new Vue({
        el: '#console',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            show: function (e) {
                e.preventDefault();
                alert('asdf')
            }
        }
    });
}