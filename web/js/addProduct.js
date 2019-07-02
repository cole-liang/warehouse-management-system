$(document).ready(function(){
    var proIdStatus = 0;
    var proNameStatus = 0;
    var modelStatus = 0;
    var priceStatus = 0;
    var unitStatus = 0;
    var supplierStatus = 0;

    $(".reset").click(function(){
        proIdStatus = 0;
        proNameStatus = 0;
        modelStatus = 0;
        priceStatus = 0;
        unitStatus = 0;
        supplierStatus = 0;
        $("#supplier").val("");
        $("#price").val("");
        $("#unit").val("");
        $("#model").val("");
        $("#productName").val("");
        $("#productId").val("");
    });

    $("#cancel1").click(function(){
        location.href = "product/productManager.action";
    });

    $("#productId").blur(function(){
        $(".validateProInfo").html("");
        proIdStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $("#validateProId").html("请填入数据");
        }else{
            if($.isNumeric(data)){
                if(data <= 0){
                    $("#validateProId").html("请输入大于0的数字！");
                }else if(parseInt(data) == data){
                    $.ajax({
                        type: "post",
                        url: "ajax/validateProAdd.action",
                        data: {
                            productId:$("#productId").val()
                        },
                        async:false,
                        dataType: "text",
                        success: function (data) {
                            if(data == '"success"'){
                                $("#validateProId").html("");
                                proIdStatus = 1;
                            }else{
                                $("#validateProId").html("材料编号已存在，不能添加！");
                            }
                        }
                    });
                }else{
                    $("#validateProId").html("请输入整数！");
                }
            }else {
                $("#validateProId").html("请输入数字！");
            }
        }
    });

    $("#price").blur(function(){
        $(".validateProInfo").html("");
        priceStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $("#validatePrice").html("请填入数据");
        }else{
            if($.isNumeric(data)){
                if(data <= 0){
                    $("#validatePrice").html("请输入大于0的数字！");
                }else {
                    $("#validatePrice").html("");
                    priceStatus = 1;
                }
            }else {
                $("#validatePrice").html("请输入数字！");
            }
        }
    });

    $("#productName").blur(function(){
        $(".validateProInfo").html("");
        proNameStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $("#validateProName").html("请填入数据");
        }else{
            proNameStatus = 1;
            $("#validateProName").html("");
        }
    });

    $("#unit").blur(function(){
        $(".validateProInfo").html("");
        unitStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $("#validateUnit").html("请填入数据");
        }else{
            unitStatus = 1;
            $("#validateUnit").html("");
        }
    });

    $("#model").blur(function(){
        $(".validateProInfo").html("");
        modelStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $("#validateModel").html("请填入数据");
        }else{
            modelStatus = 1;
            $("#validateModel").html("");
        }
    });

    $("#supplier").blur(function(){
        $(".validateProInfo").html("");
        supplierStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $("#validateSupplier").html("请填入数据");
        }else{
            supplierStatus = 1;
            $("#validateSupplier").html("");
        }
    });

    $("#inputOk1").click(function(){
        if(proIdStatus == 1 && proNameStatus == 1 && modelStatus == 1 && priceStatus == 1 && unitStatus == 1 && supplierStatus == 1){
            $(".validateProInfo").html("");
            location.href = "product/addProduct.action?productId="+$("#productId").val()+"&productName="+$("#productName").val()+
            "&model="+$("#model").val()+"&unit="+$("#unit").val()+"&price="+$("#price").val()+"&supplier="+$("#supplier").val();
        }else if(proIdStatus == 0){
            $("#productId").focus();
            $(".validateProInfo").html("请填入正确的材料编号！");
        }else if(proNameStatus == 0){
            $("#productName").focus();
            $(".validateProInfo").html("请填入材料名称！");
        }
        else if(modelStatus == 0){
            $("#model").focus();
            $(".validateProInfo").html("请填入材料规格！");
        }
        else if(priceStatus == 0){
            $("#price").focus();
            $(".validateProInfo").html("请填入正确的材料价格！");
        }
        else if(unitStatus == 0){
            $("#unit").focus();
            $(".validateProInfo").html("请填入材料单位！");
        }
        else if(supplierStatus == 0){
            $("#supplier").focus();
            $(".validateProInfo").html("请填入材料供应商！");
        }
    });
});