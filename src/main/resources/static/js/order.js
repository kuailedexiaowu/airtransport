/**
 * Created by kj on 2017/2/8.
 */
function tabelbuilder(data) {
    $("#table").append("<table id='order'><thead><tr><th><input id='leader' type='checkbox'></th>"+
        "<th>编号</th><th>发件人</th><th>收件人</th><th>舱号</th><th>状态</th><th>创建时间</th></tr></thead><tbody></tbody></table>");
    for (var i=0;i<data.list.length;i++){
        $("tbody").append("<tr></tr>")

        $("tr:last").append("<td><input type='checkbox'></td>");
        $("tr:last").append("<td>"+data.list[i].id+"</td>");
        $("tr:last").append("<td>"+data.list[i].sname+"</td>");
        $("tr:last").append("<td>"+data.list[i].rname+"</td>");
        $("tr:last").append("<td>"+data.list[i].cabin_id+"</td>");
        $("tr:last").append("<td>"+data.list[i].status+"</td>");
        $("tr:last").append("<td>"+format(data.list[i].create_time)+"</td>");

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
        $("tr:last").append("<td>"+data.list[i].sname+"</td>");
        $("tr:last").append("<td>"+data.list[i].rname+"</td>");
        $("tr:last").append("<td>"+data.list[i].cabin_id+"</td>");
        $("tr:last").append("<td>"+data.list[i].status+"</td>");
        $("tr:last").append("<td>"+format(data.list[i].create_time)+"</td>");

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

function order(no,size) {
    $.post({
        url:"/airtransport/order/selectall",
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
                    order2(page,2)
                }
            }
            $('#paginator').bootstrapPaginator(op);

        },
        error:function (data) {
            alert(data.pageSize+"错误");
        }
    });
}

function order2(no,size) {
    $.post({
        url:"/airtransport/order/selectall",
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
                    order2(page,2)
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
        $.post({
            url:"/airtransport/order/findbyid",
            async:true,
            dataType:'json',
            data:{id:trele},
            success:function (data) {
                $("#sname").val(data.sname);
                $("#scode").val(data.scode);
                $("#stel").val(data.stel);
                $("#saddress").val(data.saddress)
                $("#rname").val(data.rname);
                $("#rcode").val(data.rcode);
                $("#rtel").val(data.rtel);
                $("#raddress").val(data.raddress)
                $("#good").val(data.type);
                $("#size").val(data.size)
                $("#weight").val(data.weight);
                $("#cabin_id").val(data.cabin_id)
                $("#pay").val(data.pay);
                $("#status").val(data.status);

            },
            error:function () {
                alert("fail")
            }
        })

    }
}

function updatesub() {
    var trnum=$(":input:checkbox:checked").parent().parent().index();
    var trele=$("#table").find('tr').eq(trnum+1).find("td").eq(1).text();
    var orderUpdateIn={
        orderId:trele,
        sname:$("#sname").val(),
        stel:$("#stel").val(),
        scode:$("#scode").val(),
        saddress:$("#saddress").val(),
        rname:$("#rname").val(),
        rtel:$("#rtel").val(),
        rcode:$("#rcode").val(),
        raddress:$("#raddress").val(),
        type:$("#good").val(),
        weight:$("#weight").val(),
        size:$("#size").val(),
        cabin_id:$("#cabin_id").val(),
        status:$("#status").val(),
        pay:$("#pay").val()
    };
    $.post({
        url:"/airtransport/order/updateorder",
        async:true,
        dataType:'json',
        data:JSON.stringify(orderUpdateIn),
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
    $.post({
        url:"/airtransport/cabin/listid",
        async:true,
        dataType:'json',
        success:function (data) {
            $("#cabin_id").children().remove();
            for(var i=0;i<data.length;i++){
                $("#cabin_id2").append("<option>"+data[i].id+"</option>");
            }
        },
        error:function () {
            alert("fail")
        }
    });
    $("#add").attr("data-target","#addModel").attr("data-toggle","modal");
}

function addsub() {
    var orderAddIn = {
        sname:$("#sname2").val().trim(),
        stel:$("#stel2").val().trim(),
        scode:$("#scode2").val().trim(),
        saddress:$("#saddress2").val().trim(),

        rname:$("#rname2").val().trim(),
        rtel:$("#rtel2").val().trim(),
        rcode:$("#rcode2").val().trim(),
        raddress:$("#raddress2").val().trim(),
        
        type:$("#good2").val().trim(),
        size:$("#size2").val().trim(),
        weight:$("#weight2").val().trim(),

        cabin_id:$("#cabin_id2").val().trim(),
        pay:$("#pay2").val().trim(),
        status:$("#status2").val().trim()
    };
    $.post({
        url: "/airtransport/order/addorder",
        async: true,
        dataType: 'json',
        data: JSON.stringify(orderAddIn),
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
        url: "/airtransport/order/deleteorder",
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

function detail() {

    if($("input:checkbox:checked").length==0) {
            alert("您还没有选择");
        return ;
    }
    else if($("input:checkbox:checked:not(#leader)").length>1) {
            alert("目前版本只支持单一选择");
        return ;
    }
    else{
        $("#detail").attr("data-target","#detailModel").attr("data-toggle","modal");
        var trnum=$(":input:checkbox:checked").parent().parent().index();
        var trele=$("#table").find('tr').eq(trnum+1).find("td").eq(1).text();
        $.post({
            url:"/airtransport/order/findbyid",
            async:true,
            dataType:'json',
            data:{id:trele},
            success:function (data) {
                $("#sname3").val(data.sname);
                $("#scode3").val(data.scode);
                $("#stel3").val(data.stel);
                $("#saddress3").val(data.saddress)
                $("#rname3").val(data.rname);
                $("#rcode3").val(data.rcode);
                $("#rtel3").val(data.rtel);
                $("#raddress3").val(data.raddress)
                $("#good3").val(data.type);
                $("#size3").val(data.size)
                $("#weight3").val(data.weight);
                $("#cabin_id3").val(data.cabin_id)
                $("#pay3").val(data.pay);
                $("#status3").val(data.status);

            },
            error:function () {
                alert("fail")
            }
        })

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