$(document).ready(function () {
    $('.row-delete-button').each(function (index, element) {
        popup = $('#row-delete-popover-template').children('form').clone();
        popup.find('input[name=rowId]').val($(element).data('rowid'));
        $(element).popover({
            content: popup,
            placement: 'top',
            html: true
        });
        $(element).on('click', function (event) {
            event.preventDefault();
        });
    });
});