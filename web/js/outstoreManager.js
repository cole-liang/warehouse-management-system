$(document).ready(function(){
    $(".delete").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "outstore/deleteOutstoreSheet.action?pageType=outstoreManager&outstoreSheetId="+index;
    });
    if($("#userLevel").val() == "1"){
        $(".delete").unbind();
        $(".delete").css("color","grey");
    }
});