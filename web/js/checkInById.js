$(document).ready(function(){

    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "manage/deleteInstoreSheet.action?pageType=checkInById&instoreSheetId="+index;
    });

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkInById.action",
            data:{
                instoreSheetId:$("#instoreSheetId").val()
            }
        });
        location.href = "manage/checkInById2.jsp";
    });
});