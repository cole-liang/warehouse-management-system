/*-------------------------------Time Gadget--------------------------------------*/
function startTime()
{
    var today=new Date();
    var year = today.getFullYear();
    var month = today.getMonth()+1;
    var day = today.getDate();
    var weekdayTemp = today.getDay();
    var weekday;
    var h=today.getHours();
    var m=today.getMinutes();
    var s=today.getSeconds();
    // add a zero in front of numbers<10
    m=checkTime(m);
    s=checkTime(s);
    if(weekdayTemp == 0){
        weekday = "星期日";
    }else if(weekdayTemp == 1){
        weekday = "星期一";
    }else if(weekdayTemp == 2){
        weekday = "星期二";
    }else if(weekdayTemp == 3){
        weekday = "星期三";
    }else if(weekdayTemp == 4){
        weekday = "星期四";
    }else if(weekdayTemp == 5){
        weekday = "星期五";
    }else if(weekdayTemp == 6){
        weekday = "星期六";
    }
    $("#timeGadget").html("今天是"+year+"年"+month+"月"+day+"日 "+weekday+" "+h+":"+m+":"+s);
    t=setTimeout('startTime()',500);
}

function checkTime(i)
{
    if (i<10) {
        i="0" + i;
    }
    return i;
}
/*-------------------------------Time Gadget--------------------------------------*/

$(document).ready(function(){
    /*-------------------------------TEST--------------------------------------*/
    //$("#InstoreManager").click(function(){
    //    $("#content").attr("src","manage/addInstoreSheet.jsp?username="+$("#username").val());
    //});
    /*-------------------------------TEST--------------------------------------*/

    startTime();

    /*------------------------------------Instore-----------------------------------*/
    $("#InstoreManager").click(function(){
        $("#content").attr("src","manage/instoreManager.action?username="+$("#username").val());
    });

    $("#addInstoreInfo").click(function(){
       $("#content").attr("src","manage/addInstoreSheet.jsp?username="+$("#username").val());
    });

    $("#checkInById").click(function(){
        $("#content").attr("src","manage/checkInById.jsp?username="+$("#username").val());
    });

    $("#checkInByTime").click(function(){
        $("#content").attr("src","manage/checkInByTime.jsp?username="+$("#username").val());
    });

    $("#checkInByDept").click(function(){
        $("#content").attr("src","manage/checkInByDept.jsp?username="+$("#username").val());
    });

    $("#checkInByChecker").click(function(){
        $("#content").attr("src","manage/checkInByChecker.jsp?username="+$("#username").val());
    });
    /*------------------------------------Instore-----------------------------------*/

    /*------------------------------------Outstore-----------------------------------*/
    $("#OutstoreManager").click(function(){
        $("#content").attr("src","outstore/outstoreManager.action?username="+$("#username").val());
    });

    $("#addOutstoreInfo").click(function(){
        $("#content").attr("src","outstore/addOutstoreSheet.jsp?username="+$("#username").val());
    });

    $("#checkOutById").click(function(){
        $("#content").attr("src","outstore/checkOutById.jsp?username="+$("#username").val());
    });

    $("#checkOutByTime").click(function(){
        $("#content").attr("src","outstore/checkOutByTime.jsp?username="+$("#username").val());
    });

    $("#checkOutByDept").click(function(){
        $("#content").attr("src","outstore/checkOutByDept.jsp?username="+$("#username").val());
    });

    $("#checkOutByChecker").click(function(){
        $("#content").attr("src","outstore/checkOutByChecker.jsp?username="+$("#username").val());
    });
    /*------------------------------------Outstore-----------------------------------*/

    /*------------------------------------Product-----------------------------------*/
    $("#ProductManager").click(function(){
        $("#content").attr("src","product/productManager.action");
    });
    $("#addProductInfo").click(function(){
        $("#content").attr("src","product/addProduct.jsp");
    });
    $("#checkProductById").click(function(){
        $("#content").attr("src","product/checkProductById.jsp");
    });
    $("#checkProductByName").click(function(){
        $("#content").attr("src","product/checkProductByName.jsp");
    });
    /*------------------------------------Product-----------------------------------*/

    /*------------------------------------StoreAmount-----------------------------------*/
    $("#StoreAmountManager").click(function(){
        $("#content").attr("src","storeAmount/storeAmountManager.action");
    });

    $("#checkStoreById").click(function(){
        $("#content").attr("src","storeAmount/checkStoreById.jsp");
    });

    $("#checkStoreByTime").click(function(){
        $("#content").attr("src","storeAmount/checkStoreByTime.jsp");
    });

    $("#checkStoreByName").click(function(){
        $("#content").attr("src","storeAmount/checkStoreByName.jsp");
    });
    /*------------------------------------StoreAmount-----------------------------------*/

    $("div#choice1").click(function(){
        $("#InstoreManager").css("border-radius","0px").css("border-top-right-radius","25px").css("border-top-left-radius","25px");
        $("#panel1").css("border-bottom-right-radius","25px").css("border-bottom-left-radius","25px");
        $("#panel1").slideDown("slow");
    });

    $("div#choice1").mouseleave(function(){
        $("#panel1").slideUp("slow",function(){
            $("#InstoreManager").css("border-radius","25px").css("border-bottom","solid 1px #c3c3c3");
        });
    });

    $("div#choice2").click(function(){
        $("#OutstoreManager").css("border-radius","0px").css("border-top-right-radius","25px").css("border-top-left-radius","25px");
        $("#panel2").css("border-bottom-right-radius","25px").css("border-bottom-left-radius","25px");
        $("#panel2").slideDown("slow");
    });

    $("div#choice2").mouseleave(function(){
        $("#panel2").slideUp("slow",function(){
            $("#OutstoreManager").css("border-radius","25px").css("border-bottom","solid 1px #c3c3c3");
        });
    });

    $("div#choice3").click(function(){
        $("#ProductManager").css("border-radius","0px").css("border-top-right-radius","25px").css("border-top-left-radius","25px");
        $("#panel3").css("border-bottom-right-radius","25px").css("border-bottom-left-radius","25px");
        $("#panel3").slideDown("slow");
    });

    $("div#choice3").mouseleave(function(){
        $("#panel3").slideUp("slow",function(){
            $("#ProductManager").css("border-radius","25px").css("border-bottom","solid 1px #c3c3c3");
        });
    });

    $("div#choice4").click(function(){
        $("#StoreAmountManager").css("border-radius","0px").css("border-top-right-radius","25px").css("border-top-left-radius","25px");
        $("#panel4").css("border-bottom-right-radius","25px").css("border-bottom-left-radius","25px");
        $("#panel4").slideDown("slow");
    });

    $("div#choice4").mouseleave(function(){
        $("#panel4").slideUp("slow",function(){
            $("#StoreAmountManager").css("border-radius","25px").css("border-bottom","solid 1px #c3c3c3");
        });
    });
});

//function init() {
//    document.getElementById("container").width = window.innerWidth
//    || document.documentElement.clientWidth
//    || document.body.clientWidth;
//    document.getElementById("container").height = window.innerHeight
//    || document.documentElement.clientHeight
//    || document.body.clientHeight;
//}





