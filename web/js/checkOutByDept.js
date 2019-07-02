$(document).ready(function(){
    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "outstore/deleteOutstoreSheet.action?pageType=checkOutByDept&outstoreSheetId="+index;
    });

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkOutByDept.action",
            data:{
                department:$("#department").val()
            }
        });
        location.href = "outstore/checkOutByDept2.jsp";
    });
});