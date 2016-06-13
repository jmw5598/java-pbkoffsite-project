$(document).ready(function () {
	
	(function ($) {

        $('#item-filter').keyup(function () {

            var rex = new RegExp($(this).val(), 'i');
            $('.item-searchable').hide();
            $('.item-searchable').filter(function () {
                return rex.test($(this).text());
            }).show();

        })

    }(jQuery));
	
});