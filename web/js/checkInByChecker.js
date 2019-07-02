$(document).ready(function(){
    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "manage/deleteInstoreSheet.action?pageType=checkInByChecker&instoreSheetId="+index;
    });

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkInByChecker.action",
            data:{
                checker:$("#checker").val()
            }
        });
        location.href = "manage/checkInByChecker2.jsp";
    });
});