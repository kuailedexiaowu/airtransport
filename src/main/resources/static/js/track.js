/**
 * Created by kj on 2017/2/9.
 */
function tabelbuilder(data) {
    $("#table").append("<table id='track'><thead><tr><th><input id='leader' type='checkbox'></th>"+
        "<th>订单号</th><th>机舱号</th><th>当前站点</th><th>到达时间</th><th>已经过站点数</th></tr></thead><tbody></tbody></table>");
    for (var i=0;i<data.list.length;i++){
        $("tbody").append("<tr></tr>")
        $("tr:last").append("<td><input type='checkbox'></td>");
        $("tr:last").append("<td>"+data.list[i].orderId+"</td>");
        $("tr:last").append("<td>"+data.list[i].cabinId+"</td>");
        $("tr:last").append("<td>"+data.list[i].nowName+"</td>");
        $("tr:last").append("<td>"+format(data.list[i].arriveTime)+"</td>");
        $("tr:last").append("<td>"+data.list[i].passCount+"</td>");
    }
    $("table").attr("class","table table-bordered table-hover");
    $("#leader").click(
        function () {
            if($("#leader").is(':checked'))
                $("input:checkbox:not(:checked)").prop("checked",true);
            else
                $("input:checkbox:checked").prop("checked",false);
        })
    $(":input:checkbox:not(:first)").click(
        function () {
            if($(":input:checkbox:not(:first):checked").length==$(":input:checkbox:not(:first)").length)
                $("#leader").prop("checked",true);
            if($(":input:checkbox:not(:first):checked").length!=$(":input:checkbox:not(:first)").length)
                $("#leader").prop("checked",false);
        }
    )
}

function tabelupdate(data) {

    for (var i=0;i<data.list.length;i++){
        $("tbody").append("<tr></tr>")
        $("tr:last").append("<td><input type='checkbox'></td>");
        $("tr:last").append("<td>"+data.list[i].orderId+"</td>");
        $("tr:last").append("<td>"+data.list[i].cabinId+"</td>");
        $("tr:last").append("<td>"+data.list[i].nowName+"</td>");
        $("tr:last").append("<td>"+data.list[i].arriveTime+"</td>");
        $("tr:last").append("<td>"+data.list[i].passCount+"</td>");
    }

    $("table").attr("class","table table-bordered table-hover");
    $("#leader").click(
        function () {
            if($("#leader").is(':checked'))
                $("input:checkbox:not(:checked)").prop("checked",true);
            else
                $("input:checkbox:checked").prop("checked",false);
        })
    $(":input:checkbox:not(:first)").click(
        function () {
            if($(":input:checkbox:not(:first):checked").length==$(":input:checkbox:not(:first)").length)
                $("#leader").prop("checked",true);
            if($(":input:checkbox:not(:first):checked").length!=$(":input:checkbox:not(:first)").length)
                $("#leader").prop("checked",false);
        }
    )
}

function track(no,size) {
    $.post({
        url:"/airtransport/path/listpaths",
        async:true,
        dataType:'json',
        data:{pageNo:no,pageSize:size},
        success:function (data) {
            tabelbuilder(data)
            var op={
                currentPage: data.currentPage,
                totalPages: data.pageCount,
                numberOfPages:4,
                bootstrapMajorVersion:3,
                onPageClicked:function (event, originalEvent, type, page) {
                    $("tbody").children().remove();
                    track2(page,2)
                }
            }
            $('#paginator').bootstrapPaginator(op);
        },
        error:function (data) {
            var response=data.responseText;
            if("indexPage".indexOf(response)){
                window.location.href="airtransport/index.html"
            }
        }
    });
}

function track2(no,size) {
    $.post({
        url:"/airtransport/path/listpaths",
        async:true,
        dataType:'json',
        data:{pageNo:no,pageSize:size},
        success:function (data) {
            tabelupdate(data)
            var op={
                currentPage: data.currentPage,
                totalPages: data.pageCount,
                numberOfPages:4,
                bootstrapMajorVersion:3,
                onPageClicked:function (event, originalEvent, type, page) {
                    $("tbody").children().remove();
                    track2(page,2)
                }
            }
            $('#paginator').bootstrapPaginator(op);
        },
        error:function (data) {
            var response=data.responseText;
            if("indexPage".indexOf(response)){
                window.location.href="airtransport/index.html"
            }
        }
    });
}

function update() {
    if($("input:checkbox:checked").length==0) {
        if($("#update").val("data-target")!=null&&$("#update").val("data-toggle")!=null) {
            $("#update").removeAttr("data-target");
            $("#update").removeAttr("data-toggle");
            alert("您还没有选择");
        }
        else
            alert("您还没有选择");
        return ;
    }
    else if($("input:checkbox:checked:not(#leader)").length>1) {
        if($("#update").val("data-target")!=null&&$("#update").val("data-toggle")!=null) {
            $("#update").removeAttr("data-target");
            $("#update").removeAttr("data-toggle");
            alert("目前版本只支持单一更新");
        }
        else
            alert("目前版本只支持单一更新");
        return ;
    }

    else{
        $("#update").attr("data-target","#updateModel").attr("data-toggle","modal");
    }
}

function updatesub() {
    var trnum=$(":input:checkbox:checked").parent().parent().index();
    var trele=$("#table").find('tr').eq(trnum+1).find("td").eq(1).text();
    var trele2=$("#table").find('tr').eq(trnum+1).find("td").eq(5).text();
    var pathAddIn={
        orderId:trele,
        name:$("#name").val(),
        sort:parseInt(trele2)+1,
        arrive_time:$("#arrive_time").val(),
    };
    $.post({
        url:"/airtransport/path/addpath",
        async:true,
        dataType:'json',
        data:JSON.stringify(pathAddIn),
        contentType:"application/json",
        success:function (data) {
            alert(data.message)
        },
        error:function () {
            alert("fail")
        }
    })

}

function detail(){
    if($("input:checkbox:checked").length==0) {
        alert("您还没有选择");
        return ;
    }
    else if($("input:checkbox:checked:not(#leader)").length>1) {
        alert("目前版本只支持单一更新");
        return ;
    }
    else{
        $("#detail").attr("data-target","#detailModel").attr("data-toggle","modal");
        var trnum=$(":input:checkbox:checked").parent().parent().index();
        var trele=$("#table").find('tr').eq(trnum+1).find("td").eq(1).text();
        $.post({
            url:"/airtransport/path/getpaths",
            async:true,
            dataType:'json',
            data:{id:trele},
            success:function (data) {
                $("#paths").children().remove();
                for(var i=0;i<data.length;i++){
                    $("#paths").append("<label class='col-sm-offset-2 col-sm-4'>"+data[i].id+"</label>");
                    $("#paths").append("<label class='col-sm-2'>"+data[i].name+"</label>");
                    $("#paths").append("<label class='col-sm-4'>"+format(data[i].arrive_time)+"</label>");
                    $("#paths").append("<hr class='col-sm-offset-2 col-sm-8'>");
                }

            },
            error:function () {
                alert("fail")
            }
        })

    }
}

function deletein() {
    if ($("input:checkbox:checked").length == 0) {
        alert("您还没有选择");
        return;
    }
    else if ($("input:checkbox:checked:not(#leader)").length > 1) {
        alert("目前版本只支持单一删除");
        return;
    }
    else {

        var trnum = $(":input:checkbox:checked").parent().parent().index();
        var trele = $("#table").find('tr').eq(trnum + 1).find("td").eq(1).text();
        $.post({
            url:"/airtransport/path/deletepaths",
            async:true,
            dataType:'json',
            data:{id:trele},
            success:function (data) {
                alert(data.message);
            },
            error:function () {
                alert("fail")
            }
        })
    }
}
function addin() {
    $("#add").attr("data-target","#addModel").attr("data-toggle","modal");
    $.post({
        url:"/airtransport/order/listid",
        async:true,
        dataType:'json',
        success:function (data) {
            $("#orderid").children().remove();
            for(var i=0;i<data.length;i++){
                $("#orderid").append("<option>"+data[i].id+"</option>");
            }
        },
        error:function () {
                alert("fail")
        }
    });
    $("#add").attr("data-target","#addModel").attr("data-toggle","modal");
}

function addsub() {
    if($("select").val()==null) {
        alert("您还未选择");
        return null;
    }
    else{
        $.post({
            url:"/airtransport/path/addpaths",
            async:true,
            dataType:'json',
            data:{id:$("#orderid").val()},
            success:function (data) {
                for(var i=0;i<data.length;i++){
                    $("#orderid").append("<option>"+data[i].id+"</option>");
                }
            },
            error:function () {
                alert("fail")
            }
        });
    }
}

function format(timestamp)
{
    //timestamp是整数，否则要parseInt转换,不会出现少个0的情况
    var time = new Date(timestamp);
    var year = time.getFullYear();
    var month = time.getMonth()+1;
    var date = time.getDate();
    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();
    return year+'-'+add0(month)+'-'+add0(date)+' '+add0(hours)+':'+add0(minutes)+':'+add0(seconds);
}
function add0(m){return m<10?'0'+m:m }