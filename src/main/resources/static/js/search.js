/**
 * Created by kj on 2017/6/7.
 */
function getOrderInfo() {
    var orderId =$("#orderId").val();
    $.post({
        url:"/airtransport/order/findbyid",
        async:false,
        dataType:'json',
        data:{id:orderId},
        success:function (data) {
            alert("货物当前状态为："+data.status)
            $("#look").attr("data-target","#detailModel").attr("data-toggle","modal");
        },
        error:function () {
            alert("订单号输入有误，请查证后再输")
            $("#look").removeAttr("data-target","#detailModel").removeAttr("data-toggle","modal");
            return;
        }
    })
    $.post({
        url:"/airtransport/path/getpaths",
        async:false,
        dataType:'json',
        data:{id:orderId},
        success:function (data) {
            if(data.length == 0){
                return;
            }
            $("#paths").children().remove();
            $("#paths").append("<label class='col-sm-offset-3 col-sm-4'>"+"站点名称"+"</label>");
            $("#paths").append("<label class='col-sm-4'>"+"到达时间"+"</label>");
            $("#paths").append("<hr class='col-sm-offset-2 col-sm-8'>");
            for(var i=0;i<data.length;i++){
                $("#paths").append("<label class='col-sm-offset-3 col-sm-4'>"+data[i].name+"</label>");
                $("#paths").append("<label class='col-sm-4'>"+format(data[i].arrive_time)+"</label>");
                $("#paths").append("<hr class='col-sm-offset-2 col-sm-8'>");
            }
        },
        error:function () {
        }
    })

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


}