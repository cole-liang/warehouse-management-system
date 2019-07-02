$(document).ready(function(){

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkStoreByTime.action",
            data:{
                start:$("#start").val(),
                end:$("#end").val()
            }
        });
        location.href = "storeAmount/checkStoreByTime2.jsp";
    });
});