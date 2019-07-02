$(document).ready(function(){
    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        $.ajax({
            type: "post",
            url: "ajax/validateInDelete.action",
            data: {
                instoreSheetId:index
            },
            async:false,
            dataType: "text",
            success: function (data) {
                if(data == '"success"'){
                    location.href = "manage/deleteInstoreSheet.action?pageType=instoreManager&instoreSheetId="+index;
                } else {
                    data = data.replace(/"(.*)"/g,"$1");
                    alert("非法删除操作！库存不足，发生日期为："+data+"，请逐一检查该入库表的材料是否可删");
                }
            }
        });
    });
    if($("#userLevel").val() == "1"){
        $(".delete").unbind();
        $(".delete").css("color","grey");
    }
});