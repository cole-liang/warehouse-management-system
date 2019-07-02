$(document).ready(function(){

    $(".details").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "outstore/showOutstoreDetails.action?outstoreSheetId="+index+"&username="+$("#username").val()+"&userLevel="+$("userLevel").val();
    });

});