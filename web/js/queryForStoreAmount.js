$(document).ready(function(){
    var currentIndex;
    var monthAt;
    var proId;

    $("#outer1").hide();

    $(".details").click(function(){
        currentIndex = $(this).parent().parent().index();
        monthAt = $(this).parent().prevAll().eq(4).html();
        proId = $(this).parent().prevAll().eq(3).html();
        $("#outer1").show();
        $("#remark1").val($(this).parent().prevAll().eq(0).html());
    });

    $("#inputOk1").click(function(){
        $.ajax({
            type: "post",
            url: "ajax/modifyStoreAmount.action",
            async:false,
            data: {
                monthAt: monthAt,
                productId: proId,
                remark: $("#remark1").val()
            }
        });
        $("table").find("tr").eq(currentIndex).find("td").eq(4).html($("#remark1").val());
        $("#outer1").hide();
    });

    $("#cancel1").click(function(){
        $("#outer1").hide();
    });

    $(".closeInput").click(function(){
        $("#outer1").hide();
    });

    $(".reset").click(function(){
        $("#remark1").val("");
    });
});