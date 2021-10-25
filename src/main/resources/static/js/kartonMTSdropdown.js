


$('#selectVlasnik').on('change', function() {
    $('#selectOrgJed').val(0);
 $('option[ojVlasnikId]').css("display", "none");
$('option[ojVlasnikId="' + $(this).val() + '"]').css("display", "block");
});




$('#selectGrupa').on('change', function() {
$('#selectVrsta').val(0);
$('#selectPodvrsta').val(0);
$('option[grupaId]').css("display", "none");
$('option[grupaId="' + $(this).val() + '"]').css("display", "block");
$('option[vrstaId]').css("display", "none");
});



$('#selectVrsta').on('change', function() {
$('#selectPodvrsta').val(0);
$('option[vrstaId]').css("display", "none");
$('option[vrstaId="' + $(this).val() + '"]').css("display", "block");
});


$('#selectZanimanje').on('change', function() {
$('#selectPodzanimanje').val(0);
$('option[zanimanjeId]').css("display", "none");
$('option[zanimanjeId="' + $(this).val() + '"]').css("display", "block");
});