$(document).ready(function(){
    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "manage/deleteInstoreSheet.action?pageType=checkInByTime&instoreSheetId="+index;
    });

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkInByTime.action",
            data:{
                start:$("#start").val(),
                end:$("#end").val()
            }
        });
        location.href = "manage/checkInByTime2.jsp";
    });
});