var map;
var geo;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 53.9000000, lng: 27.5666700},
        zoom: 12
    });
}


document.querySelector('table').onclick = (event) => {
    let cell = event.target;
    if (cell.tagName.toLowerCase() != 'td')
        return;
    let i = cell.parentNode.rowIndex;
    let j = cell.cellIndex;
    var row = $("table tr").eq(i);
    var c = $("td", row).eq(j);
    showAddress(c.text());
}

function showAddress(address) {
    geo = new google.maps.Geocoder();
 //   var address = td.getElementById("address").innerHTML;
 //   alert(address);
    geo.geocode({'address' : address}, function (results, status) {
        if(status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);
            var marker =  new google.maps.Marker({
                map: map,
                position:results[0].geometry.location
            });
            map.setOptions({zoom: 16});
        }
        else {
            alert('Not valid address');
        }
    });
}