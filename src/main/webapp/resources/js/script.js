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
	
	var data = {
		    labels: ["8312131 : Chelsea Lamp TBL AN", "February", "March", "April", "May", "June", "July", "August"],
		    datasets: [
		        {
		            data: [0, 2, -1, 1, 0, 3, 1, 0]
		        }
		    ]
		};

		var options = {
		    scaleBeginAtZero: false,
		    responsive: true
		};

		var ctx = document.getElementById("myChart").getContext("2d");
		var myBarChart = new Chart(ctx).Bar(data, options);
	
});