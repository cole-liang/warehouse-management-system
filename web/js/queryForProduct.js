$(document).ready(function(){
    var currentIndex;
    var originId;
    var proNameStatus = 0;
    var modelStatus = 0;
    var priceStatus = 0;
    var unitStatus = 0;
    var supplierStatus = 0;

    $("#outer1").hide();

    $(".details").click(function(){
        proNameStatus = 1;
        modelStatus = 1;
        priceStatus = 1;
        unitStatus = 1;
        supplierStatus = 1;
        currentIndex = $(this).parent().parent().index();
        originId = $(this).parent().prevAll().eq(5).html();
        $(".validateProInfo").html("");
        $("#outer1").show();
        $("#supplier").val($(this).parent().prevAll().eq(0).html());
        $("#price").val($(this).parent().prevAll().eq(1).html());
        $("#unit").val($(this).parent().prevAll().eq(2).html());
        $("#model").val($(this).parent().prevAll().eq(3).html());
        $("#productName").val($(this).parent().prevAll().eq(4).html());
        $("#productId").val($(this).parent().prevAll().eq(5).html());
    });

    $("#price").blur(function(){
        priceStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $(".validateProInfo").html("请填入数据");
        }else{
            if($.isNumeric(data)){
                if(data <= 0){
                    $(".validateProInfo").html("请输入大于0的数字！");
                }else {
                    $(".validateProInfo").html("");
                    priceStatus = 1;
                }
            }else {
                $(".validateProInfo").html("请输入数字！");
            }
        }
    });

    $("#productName").blur(function(){
        proNameStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $(".validateProInfo").html("请填入数据");
        }else{
            proNameStatus = 1;
            $(".validateProInfo").html("");
        }
    });

    $("#unit").blur(function(){
        unitStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $(".validateProInfo").html("请填入数据");
        }else{
            unitStatus = 1;
            $(".validateProInfo").html("");
        }
    });

    $("#model").blur(function(){
        modelStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $(".validateProInfo").html("请填入数据");
        }else{
            modelStatus = 1;
            $(".validateProInfo").html("");
        }
    });

    $("#supplier").blur(function(){
        supplierStatus = 0;
        var data = $(this).val();
        if(data == ""){
            $(".validateProInfo").html("请填入数据");
        }else{
            supplierStatus = 1;
            $(".validateProInfo").html("");
        }
    });

    $("#inputOk1").click(function(){
        if(proNameStatus == 1 && modelStatus == 1 && priceStatus == 1 && unitStatus == 1 && supplierStatus == 1){
            $(".validateProInfo").html("");
            $.ajax({
                type: "post",
                url: "ajax/modifyProduct.action",
                async:false,
                data: {
                    originId:originId,
                    //modifiedId: $("#productId").val(),
                    productName: $("#productName").val(),
                    model:$("#model").val(),
                    unit:$("#unit").val(),
                    price:$("#price").val(),
                    supplier:$("#supplier").val()
                }
            });
            $("table").find("tr").eq(currentIndex).find("td").eq(0).html($("#productId").val());
            $("table").find("tr").eq(currentIndex).find("td").eq(1).html($("#productName").val());
            $("table").find("tr").eq(currentIndex).find("td").eq(2).html($("#model").val());
            $("table").find("tr").eq(currentIndex).find("td").eq(3).html($("#unit").val());
            $("table").find("tr").eq(currentIndex).find("td").eq(4).html($("#price").val());
            $("table").find("tr").eq(currentIndex).find("td").eq(5).html($("#supplier").val());
            $("#outer1").hide();
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

    $("#cancel1").click(function(){
        proNameStatus = 0;
        modelStatus = 0;
        priceStatus = 0;
        unitStatus = 0;
        supplierStatus = 0;
        $("#outer1").hide();
    });

    $(".closeInput").click(function(){
        proNameStatus = 0;
        modelStatus = 0;
        priceStatus = 0;
        unitStatus = 0;
        supplierStatus = 0;
        $("#outer1").hide();
    });

    $(".reset").click(function(){
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
    });
});