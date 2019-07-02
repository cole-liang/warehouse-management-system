$(document).ready(function(){

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkProductByName.action",
            data:{
                name:$("#searchBox").val()
            }
        });
        location.href = "product/checkProductByName2.jsp";
    });
});