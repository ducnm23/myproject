$("#button_search").click(()=>{
    $("#limit").val($("#select__length").val());
    $("#form_submit").submit();
});
$("#button_clear").click(()=>{
    $("#limit").val($("#select__length").val());
    $("#search").val("");
    $("#form_submit").submit();
});
$("#select__length").change(()=>{
    $("#limit").val($("#select__length").val());
    $("#form_submit").submit();
});
$("#checkAll").click(()=>{
    if( $('#checkAll').is(":checked") ){
        $('td input[type=checkbox]').attr('checked', true);
    }else{
        $('td input[type=checkbox]').attr('checked', false);
    }
})