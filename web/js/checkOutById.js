$(document).ready(function(){

    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "outstore/deleteOutstoreSheet.action?pageType=checkOutById&outstoreSheetId="+index;
    });

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkOutById.action",
            data:{
                outstoreSheetId:$("#outstoreSheetId").val()
            }
        });
        location.href = "outstore/checkOutById2.jsp";
    });
});