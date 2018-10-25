$(document).ready(
    function () {
        update();
        getStats();
        var el = $("#content").emojioneArea();


        $("#tweetForm").submit(function (event) {

            event.preventDefault();
            ajaxPost();


        });

        function getStats() {
            $.ajax({
                type: "GET",
                url: "getTweetCount",
                success: function (result) {


                    console.log("Success: ", result);
                    $('#tweetCount').prepend(result.tweets);
                    $('#folCount').prepend(result.followers);


                },
                error: function (e) {

                    console.log("ERROR: ", e);
                }

            });
        }

        function update() {

            $.ajax({
                type: "GET",
                url: "getTweet",
                success: function (result) {


                    console.log("Success: ", result);
                    $('#contentResult').text(result.content)


                },
                error: function (e) {

                    console.log("ERROR: ", e);
                }

            });
        }

        function ajaxPost() {


            var formData = {

                content: $("#content").val()

            };


            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "postTweet",
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function (result) {
                    el[0].emojioneArea.setText(''); // clear input
                    update();
                    console.log(result);
                },
                error: function (e) {
                    alert("Error!");
                    console.log("ERROR: ", e);
                }
            });

        }


    }
);