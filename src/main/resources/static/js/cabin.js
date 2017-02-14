/**
 * Created by kj on 2017/2/7.
 */
function tabelbuilder(data) {
    $("#table").append("<table id='cabin'><thead><tr><th><input id='leader' type='checkbox'></th>"+
        "<th>编号</th><th>出发地</th><th>目的地</th><th>状态</th></tr></thead><tbody></tbody></table>");
    for (var i=0;i<data.list.length;i++){
        $("tbody").append("<tr></tr>")

        $("tr:last").append("<td><input type='checkbox'></td>");
        $("tr:last").append("<td>"+data.list[i].id+"</td>");
        $("tr:last").append("<td>"+data.list[i].start+"</td>");
        $("tr:last").append("<td>"+data.list[i].end+"</td>");
        $("tr:last").append("<td>"+data.list[i].status+"</td>");

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
        $("tr:last").append("<td>"+data.list[i].start+"</td>");
        $("tr:last").append("<td>"+data.list[i].end+"</td>");
        $("tr:last").append("<td>"+data.list[i].status+"</td>");
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

function cabin(no,size) {
    $.post({
        url:"/airtransport/cabin/selectall",
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
                    cabin2(page,2)
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

function cabin2(no,size) {
    $.post({
        url:"/airtransport/cabin/selectall",
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
                    cabin2(page,2)
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
        var trnum=$(":input:checkbox:checked").parent().parent().index();
        var trele=$("#table").find('tr').eq(trnum+1).find("td").eq(1).text();
        $("#id").val(trele.trim());
        trele=$("#table").find('tr').eq(trnum+1).find("td").eq(2).text();
        $("#start").val(trele.trim());
        trele=$("#table").find('tr').eq(trnum+1).find("td").eq(3).text();
        $("#end").val(trele.trim());
        trele=$("#table").find('tr').eq(trnum+1).find("td").eq(4).text();
        $("#status").val(trele.trim());
        
    }
}

function updatesub() {
    var cabinUpdateIn = {id: $("#id").val(), start: $("#start").val(), end: $("#end").val(), status: $("#status").val()};
    $.post({
        url: "/airtransport/cabin/updatecabin",
        async: true,
        dataType: 'json',
        data: JSON.stringify(cabinUpdateIn),
        contentType: "application/json",
        success: function (data) {
            alert(data.message)
        },
        error: function () {
            alert("fail")
        }
    })
}

function addin() {
    $("#add").attr("data-target","#addModel").attr("data-toggle","modal");
}

function addsub() {
    var cabinAddIn = {
        start: $("#start2").val().trim(),
        end: $("#end2").val().trim(),
    };
    $.post({
        url: "/airtransport/cabin/addcabin",
        async: true,
        dataType: 'json',
        data: JSON.stringify(cabinAddIn),
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
        url: "/airtransport/cabin/deletecabin",
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