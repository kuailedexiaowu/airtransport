/**
 * Created by kj on 2017/1/31.
 */
function tabelbuilder(data) {
    $("#table").append("<table id='client'><thead><tr><th><input id='leader' type='checkbox'></th>"+
        "<th>编号</th><th>姓名</th><th>电话</th><th>邮编</th><th>地址</th></tr></thead><tbody></tbody></table>");
    for (var i=0;i<data.list.length;i++){
        $("tbody").append("<tr></tr>")

        $("tr:last").append("<td><input type='checkbox'></td>");
        $("tr:last").append("<td>"+data.list[i].id+"</td>");
        $("tr:last").append("<td>"+data.list[i].name+"</td>");
        $("tr:last").append("<td>"+data.list[i].tel+"</td>");
        $("tr:last").append("<td>"+data.list[i].code+"</td>");
        $("tr:last").append("<td>"+data.list[i].address+"</td>");

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
        $("tr:last").append("<td>"+data.list[i].id+"</td>");
        $("tr:last").append("<td>"+data.list[i].name+"</td>");
        $("tr:last").append("<td>"+data.list[i].tel+"</td>");
        $("tr:last").append("<td>"+data.list[i].code+"</td>");
        $("tr:last").append("<td>"+data.list[i].address+"</td>");

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

function client(no,size) {
    $.post({
        url:"/airtransport/client/selectall",
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
                    client2(page,2)
                }
            }
            $('#paginator').bootstrapPaginator(op);

        },
        error:function (data) {
            alert(data.pageSize+"错误");
        }
    });
}

function client2(no,size) {
    $.post({
        url:"/airtransport/client/selectall",
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
                    client2(page,2)
                }
            }
            $('#paginator').bootstrapPaginator(op);

        },
        error:function (data) {
            alert(data.pageSize+"错误");
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
        var trnum=$(":input:checkbox:checked").parent().parent().index();
        var trele=$("#table").find('tr').eq(trnum+1).find("td").eq(1).text();
        $("#id").val(trele.trim());
        trele=$("#table").find('tr').eq(trnum+1).find("td").eq(2).text();
        $("#name").val(trele.trim());
        trele=$("#table").find('tr').eq(trnum+1).find("td").eq(3).text();
        $("#tel").val(trele.trim());
        trele=$("#table").find('tr').eq(trnum+1).find("td").eq(4).text();
        $("#code").val(trele.trim());
        trele=$("#table").find('tr').eq(trnum+1).find("td").eq(5).text();
        $("#address").val(trele.trim());
    }
}

function updatesub() {
    var clientUpdateIn={id:$("#id").val(),name:$("#name").val().trim(),tel:$("#tel").val().trim(),code:$("#code").val(),address:$("#address").val()};
    $.post({
        url:"/airtransport/client/updateclient",
        async:true,
        dataType:'json',
        data:JSON.stringify(clientUpdateIn),
        contentType:"application/json",
        success:function (data) {
            alert(data.message)
        },
        error:function () {
            alert("fail")
        }
    })
    
}

function addin() {
    $("#add").attr("data-target","#addModel").attr("data-toggle","modal");
}

function addsub() {
    var clientAddIn = {
        name: $("#name2").val().trim(),
        code: $("#code2").val().trim(),
        tel: $("#tel2").val().trim(),
        address: $("#address2").val().trim()
    };
    $.post({
        url: "/airtransport/client/addclient",
        async: true,
        dataType: 'json',
        data: JSON.stringify(clientAddIn),
        contentType: "application/json",
        success: function (data) {
            alert(data.message)
        },
        error: function () {
            alert("fail")
        }
    })
}

function deletein(){

    if($("input:checkbox:checked").length==0) {
        if($("#update").val("data-target")!=null&&$("#update").val("data-toggle")!=null) {
            $("#update").removeAttr("data-target");
            $("#update").removeAttr("data-toggle");
            alert("您还没有选择");
        }
        else
            alert("您还没有选择");
        return;
    }

    var $ids=$("input:checkbox:checked:not(#leader)");
    var ids=[];
    for(var i=0;i<$ids.length;i++){
        var trnum=$($ids[i]).parent().parent().index().toString();
        trnum++;
        var id=$("#table").find('tr').eq(trnum).find("td").eq(1).text();
        ids.push(id);
    };

   $.post({
        url: "/airtransport/client/deleteclient",
        async: true,
        dataType: 'json',
        data:JSON.stringify(ids),
        contentType:"application/json",
        success: function (data) {
                alert(data.message)
            },
        error: function () {
                alert("fail")
            }
        })
}