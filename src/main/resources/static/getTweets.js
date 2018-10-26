$(document).ready(
    function () {
        var t = $('#dataTable');
        var data = [];
        getTweets();
        console.log(data);


        function getTweets() {
            $.ajax({
                type: "GET",
                url: "getTweets",
                dataType: "json",
                success: function (result) {

                    for (var i = 0; i < result.length; i++) {
                        var content = result[i].content;
                        var id = result[i].id;
                        $('#dataTable').append('<tr><td>' + id + '</td><td>' + content + '</td><td><a class="btn btn-primary btn-block" href="/edit/'+id+'">Edit</a></td><td><a class="btn btn-danger btn-block" href="/delete/'+id+'">Delete</a></td></tr>');


                    }

                    console.log("Success: ", result);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                }
            });
        }


    }
);