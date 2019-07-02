$(document).ready(function(){

    $(".details").click(function(){
        var index = $(this).parent().prevAll().eq(5).html();
        location.href = "manage/showInstoreDetails.action?instoreSheetId="+index+"&username="+$("#username").val()+"&userLevel="+$("userLevel").val();
    });

});