/*
Test Javascript code.
 */

var xmlHttp;
var xml_url = 'http://localhost:8080/TestWeb/sample.xml';

function makeAjaxCall() {
	xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = onCallback;
	xmlHttp.open("GET", xml_url, true);
	xmlHttp.send();
}

function onCallback() {
	if (xmlHttp) {
		if (xmlHttp.readyState == 4) { // "4" indicates the end of the request
			if (xmlHttp.status == 200) {// "200" indicates a successful request
				var x = xmlHttp.responseXML.getElementsByTagName("ARTIST");
				txt = "";
				for ( var i = 0; i < x.length; i++) {
					txt = x[i].childNodes[0].nodeValue + "," + txt;
				}
				document.getElementById("ajax_output").value = txt;
			} else { // Something went wrong.
				alert('Error: STATUS - ' + xmlHttp.status);
			}
		}
	} else {
		alert('Error: NULL xmlHttp object');
	}
}