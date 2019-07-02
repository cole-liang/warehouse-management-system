$(document).ready(function(){

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkProductById.action",
            data:{
                productId:$("#productIdInput").val()
            }
        });
        location.href = "product/checkProductById2.jsp";
    });
});