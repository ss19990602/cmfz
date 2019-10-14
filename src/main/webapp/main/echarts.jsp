<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="../js/echarts.min.js"></script>
    <script src="../js/china.js"></script>
    <script src=" http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#v").click(function () {
                var value = $("#f").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/TextController/bo?value=" + value,
                    datatype: "json",
                    type: "POST",
                    success: function () {
                        var goEasy = new GoEasy({
                            appkey: "BC-48a79c8caec74faa96e33004dbe0b253"
                        });
                        goEasy.subscribe({
                            channel: "164channel",
                            onMessage: function (message) {
                                $("#y").append("<h1>" + message.content + "</h1>")
                            }
                        });
                    }
                })
            });
            $("#h").click(function () {
                $("#y").empty();
            })
        })
    </script>
</head>


<body>
<input id="f" type="text">
<button id="v">发送</button>
<button id="h">清空</button>
<div id="y"></div>
</body>
</html>