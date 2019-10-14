<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function () {
        $("#users").jqGrid({
            styleUI: "Bootstrap",
            url: "${pageContext.request.contextPath}/AlbumController/selectAll",
            datatype: "json",
            height: "60%",
            colNames: ["id", "标题", "分数", "作者", "播音员", "章节数", "专辑简介", "状态", "发行时间", "上传时间", "专辑图片"],
            autowidth: true,
            caption: "专辑与专辑",
            pager: "#page",
            rowNum: 2,
            viewrecords: true,
            rowList: [2, 4, 6],
            editurl: "${pageContext.request.contextPath}/AlbumController/edit",
            cellEdit: false,
            multiselect: true,
            subGrid: true,
            colModel: [
                {
                    name: "id", hidden: true
                }, {
                    name: "title", editable: true
                }, {
                    name: "score"
                }, {
                    name: "author", editable: true
                }, {
                    name: "announcer", editable: true
                }, {
                    name: "count"
                }, {
                    name: "introduction", editable: true
                }, {
                    name: "state", editable: true,
                    edittype: "select",
                    editoptions: {value: "可用:可用;不可用:不可用"}
                }, {
                    name: "releasetime"
                }, {
                    name: "uploadtime"
                }, {
                    name: "src", edittype: "file", editable: true,
                    formatter: function (a) {
                        return "<img style='width:100px;height:50px' src='${pageContext.request.contextPath}/img/" + a + "'/>"
                    }
                }
            ], subGridRowExpanded: function (subgrid_id, albumId) {
                addSubGrid(subgrid_id, albumId);
            }
        }).jqGrid('navGrid', '#page',
            {//页面的几个按钮
                search: false
            }, {
                //在编辑之后的的额外操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#title").attr("readonly", true);
                    obj.find("#src").attr("disabled", true);
                    obj.find("#author").attr("readonly", true);
                    obj.find("#announcer").attr("readonly", true);
                    obj.find("#introduction").attr("readonly", true);
                }
            }, {
                //在添加数据之前或之后
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var id = response.responseText;
                    $.ajaxFileUpload({
                        fileElementId: "src",
                        url: "${pageContext.request.contextPath}/AlbumController/update",
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

    function addSubGrid(subgrid_id, albumId) {
        var tableid = subgrid_id + "table";
        var div = albumId + "div"
        $("#" + subgrid_id).html(
            "<table id='" + tableid + "'></table>" + "<div id='" + div + "'></div>"
        );
        $("#" + tableid).jqGrid({
            url: "${pageContext.request.contextPath}/ChapterController/selectAll?id=" + albumId,
            datatype: "json",
            autowidth: true,
            pager: "#" + div,
            rowNum: 2,
            editurl: "${pageContext.request.contextPath}/ChapterController/edit?fid=" + albumId,
            viewrecords: true,
            multiselect: true,
            rowList: [2, 4, 6],
            styleUI: "Bootstrap",
            colNames: ["id", "标题", "大小", "时长", "上传时间", "音频", "操作"],
            colModel: [
                {name: "id", hidden: true},
                {name: "title", editable: true},
                {name: "size"},
                {name: "length"},
                {name: "uploadtime"},
                {
                    name: "name", editable: true,
                    edittype: "file",
                    formatter: function (a) {
                        return "<span style='color: red'>" + a + "</span>"
                    }
                },
                {
                    name: "",
                    formatter: function (cellValue, options, rowObject) {
                        return "<a href='${pageContext.request.contextPath}/mp3/" + rowObject.name + "'><span class='glyphicon glyphicon-play-circle'></span></a>" + "                       " +
                            "<a href='${pageContext.request.contextPath}/ChapterController/download?filename=" + rowObject.name + "'><span class='glyphicon glyphicon-download'></span></a>"
                    }
                }
            ]
        }).jqGrid("navGrid", "#" + div,
            {//页面的几个按钮
                search: false
            }, {
                //在编辑之后的的额外操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#name").attr("disabled", true);
                }
            }, {
                //在添加数据之前或之后
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var id = response.responseText;
                    $.ajaxFileUpload({
                        fileElementId: "name",
                        url: "${pageContext.request.contextPath}/ChapterController/update",
                        data: {id: id},
                        success: function (data) {
                            $("#users").trigger("reloadGrid")
                            $("#" + tableid + "").trigger("reloadGrid")
                        }
                    })
                }
            }, {
                //再删除之后的操作
                closeAfterDel: true,
                afterSubmit: function (response) {
                    $("#users").trigger("reloadGrid")
                    $("#" + tableid + "").trigger("reloadGrid")
                }

            });
    }
</script>

<table id="users"></table>
<div id="page"></div>