$(document).ready(function(){
 
    activeItem = $("#accordion li:first");
    $(activeItem).addClass('active');
 
    $("#accordion li").hover(function(){
        $(activeItem).animate({width: "50px"}, {duration:300, queue:false});
        $(this).animate({width: "450px"}, {duration:300, queue:false});
        activeItem = this;
    });
 
});