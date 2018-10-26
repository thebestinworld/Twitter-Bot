$(document).ready(
    function () {

        $("#addTweet").submit(function (event) {

            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

             var formData = {
                content: $("#content").val()
            };


            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "addTweet",
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    window.location = "/database";
                },
                error: function (e) {
                    alert("Error!");
                    console.log("ERROR: ", e);
                }
            });

        }
    }
);