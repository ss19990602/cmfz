<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function () {
        $("#users").jqGrid({
            styleUI: "Bootstrap",
            url: "${pageContext.request.contextPath}/PictureController/select",
            datatype: "json",
            colNames: ["id", "编号", "状态", "简介", "出版日期", "图片名字", "图片"],
            autowidth: true,
            caption: "轮播图详情页",
            pager: "#page",
            rowNum: 2,
            viewrecords: true,
            rowList: [2, 4, 6],
            editurl: "${pageContext.request.contextPath}/PictureController/edit",
            cellEdit: false,
            toolbar: [true, "top"],
            multiselect: true,
            colModel: [
                {
                    name: "id", hidden: true
                }, {
                    name: "number", editable: true
                }, {
                    name: "state", editable: true,
                    edittype: "select",
                    editoptions: {value: "可用:可用;不可用:不可用"}

                }, {
                    name: "describe", editable: true
                }, {
                    name: "date"
                }, {
                    name: "pictureName", editable: true,
                }, {
                    name: "src", edittype: "file", editable: true,
                    formatter: function (a) {
                        return "<img style='width:100px;height:50px' src='${pageContext.request.contextPath}/img/" + a + "'/>"
                    }
                }
            ]
        }).jqGrid('navGrid', '#page',
            {//页面的几个按钮
                search: false
            }, {
                //在编辑之后的的额外操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#number").attr("readonly", true);
                    obj.find("#src").attr("disabled", true);
                    obj.find("#describe").attr("readonly", true);
                    obj.find("#pictureName").attr("readonly", true);
                }
            }, {
                //在添加数据之前或之后
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var id = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/PictureController/update",
                        fileElementId: "src",
                        data: {id: id},
                        success: function (data) {
                            $("#users").trigger("reloadGrid")
                        }
                    })
                }
            }, {
                //再删除之后的操作
            });
    });

    function dc() {
        $.ajax({
            url: "${pageContext.request.contextPath}/PictureController/dc",
            type: "post"
        })
    }
</script>
<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">图片列表</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/PictureController/dc">导出图片信息</a></li>
    </ul>

</div>
<table id="users"></table>
<div id="page"></div>