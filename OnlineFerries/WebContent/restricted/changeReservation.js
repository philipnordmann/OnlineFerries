/**
 * 
 */
function validateCabinCount(cabinId, tripId) {
	
	var cabinInput = document.getElementById("cabin_" + cabinId).children.item(0);
	var count = cabinInput.value;
	
	var url = "/OnlineFerries/TripValidator?validator=cabin&cabin="+cabinId+"&trip="+tripId+"&count="+count;
	
	if(window.XMLHttpRequest) req = new XMLHttpRequest();
	else if (window.ActiveXObject){
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("Get",url,true);
	req.onreadystatechange = callbackCabin;
	req.send(null);
}

function validatePassangers(count){
	var inputs = document.getElementById(cabintable).getElementsByTagName("span");
	var string ="";
	var count = (document.getElementsByTagName("select").item(0).value + 1) * 1;
	
	for (var elem in inputs) {
		string += elem.getAttribute(id) + "-" + elem.children.item(0) + ",";
	}
	string += string.slice(0, -1);
	
	var url = "/OnlineFerries/TripValidator?validator=passanger&cabins="+ string + "&count="+count;
	
	if(window.XMLHttpRequest) req = new XMLHttpRequest();
	else if (window.ActiveXObject){
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("Get",url,true);
	req.onreadystatechange = callbackCabin;
	req.send(null);
	
}

function callbackCabin() {
	if(req.readyState == 4 && req.status == 200){
		var b = req.responseText;
		if(b == "false"){
			alert("Cabin already taken");
		}
	}
}

function callbackPassanger() {
	if(req.readyState == 4 && req.status == 200){
		var b = req.responseText;
		if(b == "false"){
			alert("To many passengers");
		}
	}
}

