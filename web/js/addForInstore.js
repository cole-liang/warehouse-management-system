$(document).ready(function(){
    var currentIndex;
    var proIdStatus = 0;
    var amountStatus = 0;
    var departmentStatus = 0;

    $("#outer1").hide();
    $("#outer2").hide();
    $("#inputOk1").attr("disabled",true);
    $("#inputOk2").attr("disabled",true);
    //$("#save").attr("disabled",true);

    $("#cancel").click(function(){
        location.href = "manage/instoreManager.action?username="+$("#username").val();
    });

    $(".validateProId").bind("isNum",function(){
        proIdStatus = 0;
        var proId;
        if($("#productId1").val() == "" && $("#productId2").val() == ""){
            $(this).html("请输入数据！");
        }else{
            if($("#productId1").val() == ""){
                proId = $("#productId2").val();
            }else{
                proId = $("#productId1").val();
            }
            if($.isNumeric(proId)){
                var flag = 1;
                $("table").find("tr").each(function(){
                   if($(this).children().first().html() == proId){
                       flag = 0;
                       $(".validateProId").html("编号已存在于该入库单中！");
                   }
                });
                if(flag == 1){
                    $.ajax({
                        type: "post",
                        url: "ajax/validateProId.action",
                        data: {
                            productId:proId
                        },
                        async:false,
                        dataType: "text",
                        success: function (data) {
                            if(data == '"success"'){
                                proIdStatus = 1;
                                $(".validateProId").html("");
                            }else{
                                $(".validateProId").html("材料编号不存在，请先添加！");
                            }
                        }
                    });
                }
            }else {
                $(this).html("请输入数字！");
            }
        }
    });

    $("#productId1").blur(function(){
        $(".validateProId").trigger("isNum");
        isEnableOk(proIdStatus,amountStatus);
    });

    $("#productId2").blur(function(){
        $(".validateProId").trigger("isNum");
        isEnableOk(proIdStatus,amountStatus);
    });

    $(".validateAmount").bind("isNum",function(){
        amountStatus = 0;
        var amount;
        if($("#productAmount1").val() == "" && $("#productAmount2").val() == ""){
            $(this).html("请输入数据！");
        }else{
            if($("#productAmount1").val() == ""){
                amount = $("#productAmount2").val();
            }else{
                amount = $("#productAmount1").val();
            }
            if($.isNumeric(amount)){
                if(amount <= 0){
                    $(this).html("请输入大于0的数字！");
                }else{
                    $(this).html("");
                    amountStatus = 1;
                }
            }else {
                $(this).html("请输入数字！");
            }
        }
    });

    $("#productAmount1").blur(function(){
        $(".validateAmount").trigger("isNum");
        isEnableOk(proIdStatus,amountStatus);
    });

    $("#productAmount2").blur(function(){
        $(".validateAmount").trigger("isNum");
        isEnableOk(proIdStatus,amountStatus);
    });

    $("#addProduct").click(function(){
        proIdStatus = 0;
        amountStatus = 0;
        $("#inputOk1").attr("disabled",true);
        $("#outer1").show();
        $("#productId1").val("");
        $("#productAmount1").val("");
        $("#productId2").val("");
        $("#productAmount2").val("");
        $("#remark1").val("");
        $(".validateAmount").html("");
        $(".validateProId").html("");
    });

    $("#inputOk1").click(function(){
        //if($("#productId1"))
        $.ajax({
            type:"post",
            url:"ajax/addInProduct.action",
            data:{
                proId:$("#productId1").val()
            },
            dataType:"json",
            success:function(data){
                var d = eval("("+data+")");
                var re;
                if ($("#remark1").val() == "")
                    re = "无";
                else
                    re = $("#remark1").val();
                $('<tr><td>' + d.productId + '</td><td>' + d.productName + '</td><td>' +
                d.model + '</td><td>' + d.productUnit + '</td><td>' + d.price + '</td><td>' + d.supplier +
                '</td><td>' + $("#productAmount1").val() + '</td><td>' + re +
                '</td><td><a id="edit">编辑</a><a id="delete">删除</a></td></tr>').insertAfter("#tableTitle");
                $("#edit").bind("click",function(){
                    proIdStatus = 1;
                    amountStatus = 1;
                    currentIndex = $(this).parent().parent().index();
                    $("#productId1").val("");
                    $("#productAmount1").val("");
                    $("#inputOk2").attr("disabled",false);
                    $(".validateAmount").html("");
                    $(".validateProId").html("");
                    $("#outer2").show();
                    $("#productId2").val($(this).parent().prevAll().eq(7).html());
                    $("#productAmount2").val($(this).parent().prevAll().eq(1).html());
                    $("#remark2").val($(this).parent().prevAll().eq(0).html());
                });
                $("#delete").bind("click",function(){
                    $(this).parent().parent().remove();
                });
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert("操作失败;"+XMLHttpRequest.responseText);
            }
        });
        $("#outer1").hide();
    });

    $("#inputOk2").click(function(){
        $.ajax({
            type:"post",
            url:"ajax/addInProduct.action",
            data:{
                proId:$("#productId2").val()
            },
            dataType:"json",
            success:function(data){
                var d = eval("("+data+")");
                var re;
                if ($("#remark2").val() == "")
                    re = "无";
                else
                    re = $("#remark2").val();
                $('<tr><td>' + d.productId + '</td><td>' + d.productName + '</td><td>' +
                d.model + '</td><td>' + d.productUnit + '</td><td>' + d.price + '</td><td>' + d.supplier +
                '</td><td>' + $("#productAmount2").val() + '</td><td>' + re +
                '</td><td><a id="edit">编辑</a><a id="delete">删除</a></td></tr>').insertAfter("#tableTitle").parent().children().eq(currentIndex+1).remove();
                $("#edit").bind("click",function(){
                    proIdStatus = 1;
                    amountStatus = 1;
                    currentIndex = $(this).parent().parent().index();
                    $("#productId1").val("");
                    $("#productAmount1").val("");
                    $("#inputOk2").attr("disabled",false);
                    $(".validateAmount").html("");
                    $(".validateProId").html("");
                    $("#outer2").show();
                    $("#productId2").val($(this).parent().prevAll().eq(7).html());
                    $("#productAmount2").val($(this).parent().prevAll().eq(1).html());
                    $("#remark2").val($(this).parent().prevAll().eq(0).html());
                });
                $("#delete").bind("click",function(){
                    $(this).parent().parent().remove();
                });

            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert("操作失败;"+XMLHttpRequest.responseText);
            }
        });
        $("#outer2").hide();
    });

    $(".reset").click(function(){
        proIdStatus = 0;
        amountStatus = 0;
        $("#inputOk1").attr("disabled",true);
        $("#inputOk2").attr("disabled",true);
        $("#productId1").val("");
        $("#productId2").val("");
        $("#productAmount1").val("");
        $("#remark1").val("");
        $("#productAmount2").val("");
        $("#remark2").val("");
        $(".validateAmount").html("");
        $(".validateProId").html("");
    });

    $("#cancel1").click(function(){
        $("#outer1").hide();
    });

    $("#cancel2").click(function(){
        $("#outer2").hide();
    });

    $(".closeInput").click(function(){
        $("#outer1").hide();
        $("#outer2").hide();
    });

    $("#department").blur(function(){
        if($(this).val() == ""){
            //$("#save").attr("disabled",true);
            departmentStatus = 0;
            $(".validateDept").html("请输入领货学院！");
        }else{
            departmentStatus = 1;
            $(".validateDept").html("");
            //$("#save").attr("disabled",false);
        }
    });


    $("#save").click(function(){
        if(departmentStatus == 0){
            alert("请输入领货学院！");
            $("#department").focus();
        }else{
            var str ="";
            $("table").find("tr").each(function(){
                str += $(this).children().eq(0).html() + ",";
                str += $(this).children().eq(6).html() + ",";
                str += $(this).children().eq(7).html() + ",";
            });
            alert("添加成功！");
            $.ajax({
                type:"post",
                url:"ajax/addInstoreSheet.action",
                async:false,
                data:{
                    username:$("#username").val(),
                    department:$("#department").val(),
                    dataString:str
                }
            });
        }
    });
});

function isEnableOk(proIdStatus,amountStatus){
    if(proIdStatus == 1 && amountStatus == 1){
        $("#inputOk1").attr("disabled",false);
        $("#inputOk2").attr("disabled",false);
    }else{
        $("#inputOk1").attr("disabled",true);
        $("#inputOk2").attr("disabled",true);
    }
};