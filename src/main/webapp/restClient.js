function doPost(){
var payload =
	"<client>" +
	"<nom>Martin</nom>" +
	"<prenom>Michel</prenom>" +
    "<adresse>20 quai Leray</adresse>" + 
    "<codePostal>44210</codePostal>" +
    "<ville>Pornic</ville>" +
    "<telephone>0240000000</telephone>" +
    "</client>";
$.ajax({
	type: "POST",
	contentType: "application/xml; charset=utf-8",
    dataType: "xml",
    data: payload,
    url: "http://localhost:8080/ProxiBanquesi_slm_alc/services/gestionclient/client/"
    	
	}).then(function(data) {
		alert("OK");
	});
}