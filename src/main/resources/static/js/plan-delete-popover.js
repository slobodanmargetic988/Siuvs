$(document).ready(function () {
    $('.row-delete-button').each(function (element) {
        popup = $('#plan-delete-popover-template').children('form').clone();

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