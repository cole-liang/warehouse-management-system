$(document).ready(function(){

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkStoreById.action",
            data:{
                storeAmountId:$("#storeAmountId").val()
            }
        });
        location.href = "storeAmount/checkStoreById2.jsp";
    });
});