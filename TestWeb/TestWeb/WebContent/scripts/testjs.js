/*
Test Javascript code.
 */

var date_compute;

function set_date(year_var, month_var) {
	var d = new Date();
	d.setFullYear(year_var);
	d.setMonth(month_var);

	document.getElementById("date_output").value = d;
}
