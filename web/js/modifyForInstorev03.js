$(document).ready(function(){
    var currentIndex;//当前操作所在行的位置
    var proIdStatus = 0;//判断材料ID是否填写正确，1正确，0不正确
    var amountStatus = 0;//判断材料入库数量是否为正确的数字
    var amountStatus2 = 0;//判断材料入库数量是否造成库存不足现象，1不造成，0造成
    var departmentStatus = 1;
    var editable = 1;//若level为2则对界面的元素可编辑（editable = 1），level为1则不可

    $("#outer1").hide();
    $("#outer2").hide();
    $("#inputOk1").attr("disabled",true);
    $("#inputOk2").attr("disabled",true);
    //$("#save").attr("disabled",true);
    //$("#modify").attr("disabled",true);
    if($("#userLevel").val() == 1){
        editable = 0;
    }
    /*---------------------------------modify--------------------------------*/
    $(".edit").click(function(){
        proIdStatus = 1;
        amountStatus = 1;
        amountStatus2 = 1;
        currentIndex = $(this).parent().parent().index();
        $("#productId1").val("");
        $("#productAmount1").val("");
        $("#inputOk2").attr("disabled",false);
        $(".validateAmount").html("");
        $(".validateProId").html("");
        $("#outer2").show();
        $("#productId2").val($(this).parent().prevAll().eq(7).html());
        $("#productId2").attr("disabled",true);
        $("#productAmount2").val($(this).parent().prevAll().eq(1).html());
        $("#remark2").val($(this).parent().prevAll().eq(0).html());
    });

    $(".delete").click(function(){
        var temp = $(this);
        $.ajax({
            type: "post",
            url: "ajax/validateInProDelete.action",
            data: {
                instoreSheetId:$("#instoreSheetId").val(),
                productId: temp.parent().prevAll().eq(7).html()
                //amount: $(this).parent().prevAll().eq(1).html()
            },
            async:false,
            dataType: "text",
            success: function (data) {
                if (data == '"success"') {
                    temp.parent().parent().remove();
                } else {
                    data = data.replace(/"(.*)"/g,"$1");
                    alert("非法删除操作！库存不足，发生日期为："+data);
                }
            }
        });
    });

    if(editable == 0){
        $(".edit").css("color","grey");
        $(".edit").unbind();
        $(".delete").css("color","grey");
        $(".delete").unbind();
        $("#modify").attr("disabled",true);
        $("#department").attr("disabled",true);
    }

    $("#modify").click(function(){
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
            alert("更改成功！");
            $.ajax({
                type:"post",
                url:"ajax/modifyInstoreSheet.action",
                async:false,
                data:{
                    instoreSheetId:$("#instoreSheetId").val(),
                    username:$("#username").val(),
                    department:$("#department").val(),
                    dataString:str
                }
            });
        }
    });

    /*---------------------------------modify--------------------------------*/

    $("#cancel").click(function(){
        location.href = "manage/instoreManager.action?username="+$("#username").val();
    });

    $(".validateProId").bind("isNum",function(){//验证材料ID是否正确填写
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
                        dataType: "text",
                        async:false,
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
        isEnableOk(proIdStatus,amountStatus);//判断当输入框失去焦点时信息是否都填写正确，正确则enable确定按钮
    });

    $("#productId2").blur(function(){
        $(".validateProId").trigger("isNum");
        isEnableOk2(proIdStatus,amountStatus,amountStatus2);//与上面的区别是多了一个判断参数 因为上面是添加新的材料 因此不会对库存造成任何影响
                                                            // 而这个是修改原来在入库详情就有的材料 可能会出现填写的比原来的入库数量少而造成的库存不足的情况（填写新的数量前该材料曾经出过库！）
    });

    $(".validateAmount").bind("isNum",function(){//验证材料入库数量是否正确填写
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
        amountStatus2 = 0;
        if(proIdStatus == 1 && amountStatus == 1){
            $.ajax({
                type: "post",
                url: "ajax/validateInProModify.action",
                data: {
                    instoreSheetId:$("#instoreSheetId").val(),
                    productId: $("#productId2").val(),
                    amount: $("#productAmount2").val()
                },
                async:false,
                dataType: "text",
                success: function (data) {
                    if (data == '"success"') {
                        amountStatus2 = 1;
                        $(".validateAmount").html("");
                    } else {
                        data = data.replace(/"(.*)"/g,"$1");
                        $(".validateAmount").html("非法修改操作！库存不足，发生日期为："+data);
                    }
                }
            });
        }
        isEnableOk2(proIdStatus,amountStatus,amountStatus2);
    });

    $("#addProduct").click(function(){
        proIdStatus = 0;
        amountStatus = 0;
        amountStatus2 = 0;
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

    if(editable == 0){
        $("#addProduct").css("color","grey");
        $("#addProduct").unbind();
    }

    $("#inputOk1").click(function(){
        if($("#productId1"))
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
                        amountStatus2 = 1;
                        currentIndex = $(this).parent().parent().index();
                        $("#productId1").val("");
                        $("#productAmount1").val("");
                        $("#inputOk2").attr("disabled",false);
                        $(".validateAmount").html("");
                        $(".validateProId").html("");
                        $("#outer2").show();
                        $("#productId2").attr("disabled",false);
                        $("#productId2").val($(this).parent().prevAll().eq(7).html());
                        $("#productAmount2").val($(this).parent().prevAll().eq(1).html());
                        $("#remark2").val($(this).parent().prevAll().eq(0).html());
                    });
                    $("#delete").bind("click",function(){
                        var temp = $(this);
                        $.ajax({
                            type: "post",
                            url: "ajax/validateInProDelete.action",
                            data: {
                                instoreSheetId:$("#instoreSheetId").val(),
                                productId: temp.parent().prevAll().eq(7).html()
                                //amount: $(this).parent().prevAll().eq(1).html()
                            },
                            async:false,
                            dataType: "text",
                            success: function (data) {
                                if (data == '"success"') {
                                    temp.parent().parent().remove();
                                } else {
                                    data = data.replace(/"(.*)"/g,"$1");
                                    alert("非法删除操作！库存不足，发生日期为："+data);
                                }
                            }
                        });
                    });
                    isEditable(editable);
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
                    amountStatus2 = 1;
                    currentIndex = $(this).parent().parent().index();
                    $("#productId1").val("");
                    $("#productAmount1").val("");
                    $("#inputOk2").attr("disabled",false);
                    $(".validateAmount").html("");
                    $(".validateProId").html("");
                    $("#outer2").show();
                    $("#productId2").attr("disabled",false);
                    $("#productId2").val($(this).parent().prevAll().eq(7).html());
                    $("#productAmount2").val($(this).parent().prevAll().eq(1).html());
                    $("#remark2").val($(this).parent().prevAll().eq(0).html());
                });
                $("#delete").bind("click",function(){
                    var temp = $(this);
                    $.ajax({
                        type: "post",
                        url: "ajax/validateInProDelete.action",
                        data: {
                            instoreSheetId:$("#instoreSheetId").val(),
                            productId: temp.parent().prevAll().eq(7).html()
                            //amount: $(this).parent().prevAll().eq(1).html()
                        },
                        async:false,
                        dataType: "text",
                        success: function (data) {
                            if (data == '"success"') {
                                temp.parent().parent().remove();
                            } else {
                                data = data.replace(/"(.*)"/g,"$1");
                                alert("非法删除操作！库存不足，发生日期为："+data);
                            }
                        }
                    });
                });
                isEditable(editable);
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

    //function checkEqual(id){
    //    $("table").find("tr").each(function(){
    //        if($(this).children().first().html() == $("#productId2").val())
    //
    //            });
    //};

    $(".reset").click(function(){
        proIdStatus = 0;
        amountStatus = 0;
        amountStatus2 = 0;
        $("#inputOk1").attr("disabled",true);
        $("#inputOk2").attr("disabled",true);
        $("#productId1").val("");
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

    //$("#dragZone").mousedown(function(){
    //    $("#inputInfo").attr("draggable","true");
    //});
    //
    //$("#dragZone").mouseup(function(){
    //    $("#inputInfo").attr("draggable","false");
    //});

    $("#department").blur(function(){
        if($(this).val() == ""){
            //$("#modify").attr("disabled",true);
            //$("#save").attr("disabled",true);
            departmentStatus = 0;
            $(".validateDept").html("请输入领货学院！");
        }else{
            departmentStatus = 1;
            $(".validateDept").html("");
            //$("#save").attr("disabled",false);
            //$("#modify").attr("disabled",false);
        }
    });


    //$("#save").click(function(){
    //    var str ="";
    //    $("table").find("tr").each(function(){
    //        str += $(this).children().eq(0).html() + ",";
    //        str += $(this).children().eq(6).html() + ",";
    //        str += $(this).children().eq(7).html() + ",";
    //    });
    //    alert("添加成功！");
    //    $.ajax({
    //        type:"post",
    //        url:"ajax/addInstoreSheet.action",
    //        async:false,
    //        data:{
    //            username:$("#username").val(),
    //            department:$("#department").val(),
    //            dataString:str
    //        }
    //    });
    //});
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

function isEnableOk2(proIdStatus,amountStatus,amountStatus2){
    if(proIdStatus == 1 && amountStatus == 1 && amountStatus2 == 1){
        $("#inputOk1").attr("disabled",false);
        $("#inputOk2").attr("disabled",false);
    }else{
        $("#inputOk1").attr("disabled",true);
        $("#inputOk2").attr("disabled",true);
    }
};

function isEditable(editable){
    if(editable == 0){
        $("#edit").css("color","grey");
        $("#edit").unbind();
        $("#delete").css("color","grey");
        $("#delete").unbind();
    }
}