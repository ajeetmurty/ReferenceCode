/*
Test Javascript code.
 */

var xml_url = 'http://localhost:8080/TestWeb/sample.xml';

jQuery(document).ready(function() {
	$(document).ready(function() {
		$("#calljquery").click(function() {
			$.ajax({
				type : "GET",
				url : xml_url,
				dataType : "xml",
				success : function(result) {
					var txt = "";
					$(result).find('ARTIST').each(function() {
						txt = $(this).text() + "," + txt;
					});
					$("textarea#jquery_output").val(txt);
				}
			});
		});
	});
});