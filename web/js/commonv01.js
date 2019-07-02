$(document).ready(function(){

    /*---------------------------------------table---------------------------------------*/
    var currentPage = 0;
    var pageSize = 5;

    var sumRows = $("#tableTitle").nextAll().length;
    var sumPages = Math.ceil(sumRows/pageSize);

    var colors = ['#deff8e','#b7f1ff'];

    $("#page").html(currentPage+1);
    $("table").bind("paging",function(){
        $("#tableTitle").nextAll().hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();
    });

    $("#prev").bind("click",function(){
        currentPage--;
        if(currentPage < 0)
            currentPage = 0;
        $("#page").html(currentPage+1);
        $("table").trigger("paging");
    });

    $("#next").bind("click",function(){
        currentPage++;
        if(currentPage >= sumPages)
            currentPage = sumPages - 1;
        $("#page").html(currentPage+1);
        $("table").trigger("paging");
    });

    $("table").trigger("paging");

    $("table").find("tr").each(function(i){
        $(this).css("background-color",colors[i%2]);
    });
    /*---------------------------------------table---------------------------------------*/
});