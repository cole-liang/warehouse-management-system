$(document).ready(function(){
    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "outstore/deleteOutstoreSheet.action?pageType=checkOutByTime&outstoreSheetId="+index;
    });

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkOutByTime.action",
            data:{
                start:$("#start").val(),
                end:$("#end").val()
            }
        });
        location.href = "outstore/checkOutByTime2.jsp";
    });
});