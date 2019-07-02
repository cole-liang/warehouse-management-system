$(document).ready(function(){
    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "manage/deleteInstoreSheet.action?pageType=checkInByDept&instoreSheetId="+index;
    });

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkInByDept.action",
            data:{
                department:$("#department").val()
            }
        });
        location.href = "manage/checkInByDept2.jsp";
    });
});