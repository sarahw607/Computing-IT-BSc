/**
 * Utility to get default value from the field name if it was undefined or empty
 * @param {type} fieldName
 * @param {type} defaultValue
 * @returns {jQuery}
 */
function isLetter(str) {
    return str.length === 1 && str.match(/[a-z]/i);
}
function isNumber(str) {
    return str.length === 1 && str.match(/[0-9]/);
}

function get_name_value(fieldName, defaultValue) {
    var value = $('#' + fieldName).val();
    if (value == "") {
        value = defaultValue;
        $('#' + fieldName).val(value);
    }
    if (fieldName == "name") {
        if (!(isLetter(value.charAt(0)) && isNumber(value.charAt(value.length - 1)))) {
            alert("Please enter the correct value");
            return "";
        }
    }
    return value;
}


/**
 /**
 * This is the main class
 */
var app = {
    initialize: function () {
        this.getGoing();
    },
    getGoing: function () {

        function SimpleApp() {

            /**
             * function to get goordinates for location input
             */
            function getCoords(loc) {
                // alert("in getcoords");
                var osm_param = "http://nominatim.openstreetmap.org/search/" + loc + "?format=json&countrycode=gb";
                // alert("OSM_param is: " + osm_param);
                $.get(osm_param,
                        function (data) {
                            var lat = data[0].lat;
                            var lon = data[0].lon;
                            var longLat = "Latitude= " + lat + "; longitude= " + lon;
                            // Can either use getElementById (note the case in Id ...) as in TMA03 updateMap() ...
                            // .... or use jQuery construct with $('#reply').    
                            // Try them by commenting them out alternately one of the following two lines.  
                            // If you leave them both in, the second will overwrite the first.
                            document.getElementById("reply").innerHTML = "getElement...:  The location you entered has the coordinates: " + longLat;
                            //  $('#reply').text("jQuery: The location you entered has coordinates: " + loc);
                            // alert("done?");
                        });
            }

            /**
             * Callback function to get coordinate
             */
            this.coords = function () {
                // alert("in coords");
                var loc = get_name_value("where", 'oops');
                getCoords(loc);
            };

            /**
             * Callback function for yes
             */
            this.yes = function () {
                alert("Goody!");
                console.log("success");
            };
            /**
             * Callback function for no
             */
            this.no = function () {
                alert("Oops ..");
            };

            //   alert("in JS");


        }
        this.mysimpleApp = new SimpleApp();
    }
};
app.initialize();
