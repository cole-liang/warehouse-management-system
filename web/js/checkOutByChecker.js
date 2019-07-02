$(document).ready(function(){
    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "outstore/deleteOutstoreSheet.action?pageType=checkOutByChecker&outstoreSheetId="+index;
    });

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkOutByChecker.action",
            data:{
                checker:$("#checker").val()
            }
        });
        location.href = "outstore/checkOutByChecker2.jsp";
    });
});