/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var directionDisplay;
var directionsService = new google.maps.DirectionsService();
var map;
function onStart()
{
    var file, n;
    file = window.location.pathname;
    n = file.lastIndexOf('/');
    if (n >= 0)
    {
        file = file.substring(n + 1);
    }
    //alert(file);
    if (file.indexOf("qDirections") !== -1 || file.indexOf("editPlannedTrip") !== -1)
    {
        reCalcRoute();
    }
    if (file.indexOf("qEditCurrentTrip") !== -1)
    {
        places();
    }

}
function initialize() {
    directionsDisplay = new google.maps.DirectionsRenderer();
    var chicago = new google.maps.LatLng(41.850033, -87.6500523);
    var mapOptions = {
        zoom: 6,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        center: chicago
    }
    map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
    directionsDisplay.setMap(map);
}
function places()
{
    var input = /** @type {HTMLInputElement} */(document.getElementById('poi'));
    var searchBox = new google.maps.places.SearchBox(input);
    var markers = [];
    google.maps.event.addListener(searchBox, 'places_changed', function() {
        var places = searchBox.getPlaces();

        for (var i = 0, marker; marker = markers[i]; i++) {
            marker.setMap(null);
        }

        markers = [];
        var bounds = new google.maps.LatLngBounds();
        for (var i = 0, place; place = places[i]; i++) {
            createPhotoMarker(place)

            markers.push(marker);

            bounds.extend(place.geometry.location);

        }

        map.fitBounds(bounds);
    });

    google.maps.event.addListener(map, 'bounds_changed', function() {
        var bounds = map.getBounds();
        searchBox.setBounds(bounds);
    });
}
function createPhotoMarker(place) {
    var photos = place.photos;
    if (!photos) {
        return;
    }

    var marker = new google.maps.Marker({
        map: map,
        position: place.geometry.location,
        title: place.name,
        icon: photos[0].getUrl({'maxWidth': 35, 'maxHeight': 35})
    });
}
function calcRoute() {
    var start = document.getElementById('startLocation').value;
    var end = document.getElementById('endLocation').value;
    var waypts = [];
    var waypoint;
    var wrows = document.getElementsByName("waypoint");
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': start}, function(results, status) {

        if (status == google.maps.GeocoderStatus.OK) {

            var hidden = document.getElementById("hiddenD");
            hidden.innerHTML = ""
            hidden.innerHTML += '<input type="hidden" name="startLatitude" value="' + results[0].geometry.location.kb + '" />';
            hidden.innerHTML += '<input type="hidden" name="startLongitude" value="' + results[0].geometry.location.lb + '" />';
            // do something with the geocoded result
            //
            // results[0].geometry.location.latitude
            // results[0].geometry.location.longitude
        } else {
            alert("Sorry, we couldn't find a location for " + start + ".  Could you be more specific?");
        }
    });
    for (var i = 0; i < wrows.length; i++)
    {
        waypoint = document.getElementById('waypoint[' + i + ']').value;
        geocoder.geocode({'address': waypoint}, function(results, status) {

            if (status == google.maps.GeocoderStatus.OK) {

                var hidden = document.getElementById("hiddenD");
                hidden.innerHTML += '<input type="hidden" name="waypointLatitude" id="waypointLatitude" value="' + results[0].geometry.location.kb + '" />';
                hidden.innerHTML += '<input type="hidden" name="waypointLongitude" id="waypointLongitud" value="' + results[0].geometry.location.lb + '" />';

            }
        });

        if ((waypoint !== null) && (waypoint !== "")) {
            waypts.push({
                location: waypoint,
                stopover: true});
        }
    }
    var request = {
        origin: start,
        destination: end,
        waypoints: waypts,
        optimizeWaypoints: false,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };
    directionsService.route(request, function(response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
            var route = response.routes[0];
            var summaryPanel = document.getElementById('directions_panel');
            summaryPanel.innerHTML = '';
            // For each route, display summary information.
            for (var i = 0; i < route.legs.length; i++) {
                var routeSegment = i + 1;
                summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment + '</b><br>';
                summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
                summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
                summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
            }
        }
    });
    geocoder.geocode({'address': end}, function(results, status) {

        if (status == google.maps.GeocoderStatus.OK) {
            var hidden = document.getElementById("hiddenD");
            hidden.innerHTML += '<input type="hidden" name="endLatitude" value="' + results[0].geometry.location.kb + '" />';
            hidden.innerHTML += '<input type="hidden" name="endLongitude" value="' + results[0].geometry.location.lb + '" />';
            // do something with the geocoded result
            //
            // results[0].geometry.location.latitude
            // results[0].geometry.location.longitude
        } else {
            alert("Sorry, we couldn't find a location for " + end + ".  Could you be more specific?");
        }
    });
}

function reCalcRoute() {
    var start = document.getElementById('startLocation').value;
    var end = document.getElementById('endLocation').value;
    var waypts = [];
    var waypoint;
    var wrows = document.getElementsByName("waypoint");
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': start}, function(results, status) {

        if (status == google.maps.GeocoderStatus.OK) {

            var hidden = document.getElementById("hiddenD");
            hidden.innerHTML = ""
            hidden.innerHTML += '<input type="hidden" name="startLatitude" value="' + results[0].geometry.location.kb + '" />';
            hidden.innerHTML += '<input type="hidden" name="startLongitude" value="' + results[0].geometry.location.lb + '" />';
        } else {
            alert("Sorry, we couldn't find a location for " + start + ".  Could you be more specific?");
        }
    });

    //////////////////
    for (var i = 0; i < wrows.length; i++)
    {
        waypoint = wrows[i].value;
        geocoder.geocode({'address': waypoint}, function(results, status) {

            if (status == google.maps.GeocoderStatus.OK) {
                var hidden = document.getElementById("hiddenD");
                hidden.innerHTML += '<input type="hidden" name="waypointLatitude" id="waypointLatitude[' + i + ']" value="' + results[0].geometry.location.kb + '" />';
                hidden.innerHTML += '<input type="hidden" name="waypointLongitude" id="waypointLongitude[' + i + ']" value="' + results[0].geometry.location.lb + '" />';
            }
        });

        if ((waypoint !== null) && (waypoint !== "")) {
            waypts.push({
                location: waypoint,
                stopover: true});
        }
        else {
            wrows[i].parentNode.removeChild(wrows[i]);
        }
    }

    //////////////////
    var request = {
        origin: start,
        destination: end,
        waypoints: waypts,
        optimizeWaypoints: false,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };
    directionsService.route(request, function(response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setPanel(document.getElementById('directions_panel'));
            directionsDisplay.setDirections(response);

//            var route = response.routes[0];
//            var summaryPanel = document.getElementById('directions_panel');
//            summaryPanel.innerHTML = '';
//            // For each route, display summary information.
//            for (var i = 0; i < route.legs.length; i++) {
//                var routeSegment = i + 1;
//                summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment + '</b><br>';
//                summaryPanel.innerHTML += route.legs[i].duration.text + "<br>";
//                summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
//                summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
//                summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
//            }

        }
    });


    geocoder.geocode({'address': end}, function(results, status) {

        if (status == google.maps.GeocoderStatus.OK) {
            var hidden = document.getElementById("hiddenD");
            hidden.innerHTML += '<input type="hidden" name="endLatitude" value="' + results[0].geometry.location.kb + '" />';
            hidden.innerHTML += '<input type="hidden" name="endLongitude" value="' + results[0].geometry.location.lb + '" />';
            // do something with the geocoded result
            //
            // results[0].geometry.location.latitude
            // results[0].geometry.location.longitude
        } else {
            alert("Sorry, we couldn't find a location for " + end + ".  Could you be more specific?");
        }
    });
}

function displayMap() {
    var start = document.getElementById("startLocation");
    var end = document.getElementById("endLocation");
    //alert(start.innerHTML);
    var waypts = [];
    var waypoint;
    var wrows = document.getElementsByName("waypoint");


    //////////////////
    for (var i = 0; i < wrows.length; i++)
    {
        waypoint = wrows[i].text;
        //alert(waypoint);
        if ((waypoint !== null) && (waypoint !== "")) {
            waypts.push({
                location: waypoint,
                stopover: true});

        } else {
            wrows[i].parentNode.removeChild(wrows[i]);
        }
    }

    //////////////////
    var request = {
        origin: start.innerHTML,
        destination: end.innerHTML,
        waypoints: waypts,
        optimizeWaypoints: false,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };
    directionsService.route(request, function(response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
            var route = response.routes[0];
            var summaryPanel = document.getElementById('directions_panel');
            summaryPanel.innerHTML = '';
            // For each route, display summary information.
            for (var i = 0; i < route.legs.length; i++) {
                var routeSegment = i + 1;
                summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment + '</b><br>';
                summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
                summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
                summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
            }
        }
    });
}