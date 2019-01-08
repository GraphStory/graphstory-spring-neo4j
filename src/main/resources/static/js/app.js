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
    var profile = new Vue({
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

if(document.getElementById("profile")){
    var profile = new Vue({
        el: '#timeline',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            save: function (id, e) {

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

if(document.getElementById("productSearch")){
    var profile = new Vue({
        el: '#timeline',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            search: function (id, e) {

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
            },
            goToProduct: function (id, e) {
                e.preventDefault();

                // save product view
            }

        }
    });
}

if(document.getElementById("products")){
    var profile = new Vue({
        el: '#timeline',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            show: function (id, e) {

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
            },
            goToProduct: function (id, e) {
                e.preventDefault();

                // save product view
            },
        }

    });
}

if(document.getElementById("productFinder")){
    var profile = new Vue({
        el: '#timeline',
        delimiters: ['${', '}'],
        data: {
        },
        mounted: function () {
        },
        methods: {
            search: function (id, e) {


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
    var profile = new Vue({
        el: '#timeline',
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
    var profile = new Vue({
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

if(document.getElementById("friendFinder")){
    var profile = new Vue({
        el: '#timeline',
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
            },
            unfollow: function (id, e) {

            }
        }
    });
}

if(document.getElementById("console")){
    var profile = new Vue({
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