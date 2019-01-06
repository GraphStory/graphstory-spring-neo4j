$.ajaxSetup({
    headers: { 'Accept': 'application/json',  'Content-Type': 'application/json' },
    contentType: "application/json",
    dataType: "json"
});

if(document.getElementById("homelogin")){

    var profile = new Vue({
        el: '#homelogin',
        delimiters: ['${', '}'],
        data: {
            username:""
        },
        mounted: function () {
        },
        methods: {
            login: function (e) {

                var u = this.username;
                e.preventDefault();
                $.post("/api/auth/login?username="+this.username,null, function (response) {
                    window.location.href = "/app/timeline";
                }).fail(function(response) {
                    var r = jQuery.parseJSON(response.responseText);
                    alert(r.message);
                });

            }
        }
    });
}