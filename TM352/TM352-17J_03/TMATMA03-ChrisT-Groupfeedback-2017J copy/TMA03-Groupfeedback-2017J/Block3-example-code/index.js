var app = {
      // Application Constructor

      ///If you are running logcat from the command line, you can limit to a 
      // certain tag using the following: adb logcat -s "YOURTAG"

      initialize: function() {
          this.bindEvents();
      },
      // Bind Event Listeners
      bindEvents: function() {
          document.addEventListener('deviceready', this.onDeviceReady, false);
      },
      // deviceready Event Handler
      onDeviceReady: function() {
          app.receivedEvent('deviceready');
      },
      // Update DOM on a Received Event
      receivedEvent: function(id) {
          // returns number of millisecs since Unix epoch
          function timestamp() {
              var nowDate = new Date();
              return nowDate.getTime();
          }
          // takes a time in millisecs and returns as minutes:seconds.hundreths
          function formatTime(elapsedTime) {
              var seconds = elapsedTime / 1000; // calculate seconds from milliseconds
              seconds = seconds % 60; // don't display seconds less greater than 60 - we use minutes for that
              seconds = seconds.toFixed(2); // round to 2 decimal places
              var minutes = Math.floor(elapsedTime / 1000 / 60); // calculate mins from millisecs - floor rounds down to nearest integer
              // add a 0 prefix to seconds and minutes from 0-9
              if (seconds < 10) {
                  seconds = "0" + seconds;
              }
              if (minutes < 10) {
                  minutes = "0" + minutes;
              }
              return minutes + ":" + seconds;
          }

          var startTime = null;

          // calculates elapsed time and displays as minutes and seconds
          function timer() {
              var nowTime = timestamp();
              var elapsedTime = nowTime - startTime;
              document.getElementById("stopwatch").innerHTML = formatTime(elapsedTime);
              updateMap();
          }

          function Stopwatch() {
              var nowTime = null;
              var timerId = null;      
              
              // starts the stopwatch
              this.start = function() {
                  if (timerId)
                      clearInterval(timerId);
                  document.getElementById("stopwatch").innerHTML = "00:00:00";
                  startTime = timestamp(); // gets the start time from the timestamp function
                  timerId = setInterval(timer, 1000);
              };
              // stops the stopwatch
              this.stop = function() {
                  clearInterval(timerId);
              };

              this.photo = function() {
                  var cameraSuccess = function(imageData) {
                      var image = document.getElementById('image');
                      image.style.display = "block";
                      image.src = imageData;
                  };
                  var cameraError = function() {
                      console.err("Problem in getting the camera picture");
                  };
                  navigator.camera.getPicture(cameraSuccess, cameraError, {
                      quality: 50
                  });
              };

              updateMap();
          }
          this.myStopwatch = new Stopwatch();
          
          function updateMap() {
          var onSuccess = function(position) {
                var div = document.getElementById("map_canvas");
                div.width = window.innerWidth-20;
                div.height = window.innerHeight*0.8-40;
                var map = plugin.google.maps.Map.getMap(div);
                map.setVisible(false);
                var currentLocation = new plugin.google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                // alert("Current location is " + JSON.stringify(currentLocation));
                
                //GEO location marker
                map.addMarker({
                    'position': currentLocation,
                    'title': "You are here!"
                }, function(marker) {
                    marker.showInfoWindow();
                });
                map.setCenter(currentLocation);
                map.setZoom(15);
                map.refreshLayout();
                map.setVisible(true);
                
                //add marker on click
				map.on(plugin.google.maps.event.MAP_CLICK, function(latLng) {
					map.addMarker({
						'position': latLng,
						'title': "You want to run to here!"
					}, function(marker) {
						marker.showInfoWindow();
				   });
			   });
            };
            function onError(error) {
                alert('code: ' + error.code + '\n' +
                    'message: ' + error.message + '\n');
            }
            navigator.geolocation.getCurrentPosition(onSuccess, onError);
       }
      }
  };

  app.initialize();