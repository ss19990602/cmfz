<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div id="main" style="width: 1200px;height:400px;"></div>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>
<script type="text/javascript">

    var days = new Array();
    //当天
    var Date8 = new Date();
    var Date9 = new Date(Date8.getTime()).toLocaleDateString();
    //前一天
    var Date7 = new Date(Date8.getTime() - 24 * 60 * 60 * 1000).toLocaleDateString();
    //前两天
    var Date6 = new Date(Date8.getTime() - 48 * 60 * 60 * 1000).toLocaleDateString();
    //前三天
    var Date5 = new Date(Date8.getTime() - 72 * 60 * 60 * 1000).toLocaleDateString();
    //前四天
    var Date4 = new Date(Date8.getTime() - 96 * 60 * 60 * 1000).toLocaleDateString();
    //前五天
    var Date3 = new Date(Date8.getTime() - 120 * 60 * 60 * 1000).toLocaleDateString();
    //前六天
    var Date2 = new Date(Date8.getTime() - 144 * 60 * 60 * 1000).toLocaleDateString();
    //前七天
    var Date1 = new Date(Date8.getTime() - 168 * 60 * 60 * 1000).toLocaleDateString();
    days.push(Date2, Date3, Date4, Date5, Date6, Date7, Date9);

    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户注册走势图'
        },
        tooltip: {},
        legend: {
            data: ['注册数据']
        },
        xAxis: {
            data: ["六天前", "五天前", "四天前", "三天前", "两天前", "昨天", "今天"]
        },
        yAxis: {},
        series: [{
            name: '注册数据',
            type: 'line',
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.ajax({
        url: "${pageContext.request.contextPath}/UserController/query",
        datatype: "json",
        type: "POST",
        success: function (da) {
            console.log(da);
            myChart.setOption({
                series: [{data: da}],
                xAxis: {data: days}
            });
        }
    })

</script>