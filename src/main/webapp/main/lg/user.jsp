<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div id="main" style="width: 600px;height:400px;"></div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    // 指定图表的配置项和数据
    option = {
        title: {
            text: '用户分布图',
            subtext: '王某出品,必属精品',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['iphone3']
        },
        visualMap: {
            min: 0,
            max: 5,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '用户分布信息',
                type: 'map',
                mapType: 'china',
                /*                data:[
                                    {name:"北京",value:400},
                                    {name:"河南",value:500},
                                    {name:"山西",value:600},
                                    {name:"山东",value:700},
                                    {name:"湖南",value:1000},
                                    {name:"河北",value:1000}
                                ],*/
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                }

            }


        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.ajax({
        url: "${pageContext.request.contextPath}/UserController/select",
        datatype: "json",
        type: "POST",
        success: function (da) {
            myChart.setOption({
                series: [{data: da}]
            });
        }
    })

</script>