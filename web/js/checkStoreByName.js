$(document).ready(function(){

    $("#check").click(function(){
        $.ajax({
            type:"post",
            async:false,
            url:"ajax/checkStoreByName.action",
            data:{
                name:$("#name").val()
            }
        });
        location.href = "storeAmount/checkStoreByName2.jsp";
    });
});