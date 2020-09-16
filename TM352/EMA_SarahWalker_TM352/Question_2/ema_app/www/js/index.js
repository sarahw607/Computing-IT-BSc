
function isValidOUCU(userName) {
    let pattern = /^[A-Za-z]+[0-9]+$/
    return pattern.test(userName);
}

function get_field_value(fieldName) {
    return value = $('#' + fieldName).val();
}

// /**
//  * This is the main class
//  */
var app = {
    initialize: function () {
        this.bindEvents();
    },
    bindEvents: function () {
        document.addEventListener('deviceready', this.onDeviceReady, false); // ensures device ready before trying to show map
    },
    onDeviceReady: function () {
        app.receivedEvent('deviceready');
    },

    receivedEvent: function (id) {
        function Sales() {
            var map;
            let widgets;
            let currentWidgetIndex;
            let orderId;
            let client;
            let lat;
            let long;
            let subtotal = 0;
            let vat = 0;
            let total = 0;

            function initializeData() {
                let onSuccess = function (position) {
                    lat = position.coords.latitude;
                    long = position.coords.longitude;
                    initialiseMap()
                }
                let onError = function () {
                    alert('Could not retrieve location');
                }
                navigator.geolocation.getCurrentPosition(onSuccess, onError, { enableHighAccuracy: true });
            }
            function initialiseMap() {
                {
                    var div = document.getElementById("map_canvas");
                    div.width = window.innerWidth - 20;
                    div.height = window.innerHeight * 0.8 - 40;
                    map = plugin.google.maps.Map.getMap(div);
                    map.setVisible(false);
                    map.addEventListener(plugin.google.maps.event.MAP_READY, onMapReady, false);
                    function onMapReady() {
                        plugin.google.maps.Map.setDiv(div);
                        var currentLocation = new plugin.google.maps.LatLng(lat, long);
                        map.setZoom(15);
                        map.setCenter(currentLocation);
                        map.refreshLayout();
                    }
                };
            }
            function loadWidgets() {
                let oucu = get_field_value('oucu');
                let password = get_field_value('password');
                let path = 'http://137.108.93.222/openstack/api/widgets?OUCU=' + oucu + '&password=' + password;
                $.get(path,
                    function (data) {
                        var obj = $.parseJSON(data);
                        if (obj.status == "fail") {
                            alert(obj.data[0].reason);
                        } else {
                            if (obj.data.length > 0) {
                                widgets = obj.data;
                                if (obj.data) {
                                    currentWidgetIndex = 0;
                                    displayWidget(obj.data[0])
                                }
                            }
                        }
                    });

            }
            function displayWidget(widget) {
                let imgElement = document.getElementById("widgetImg");
                let descElement = document.getElementById("widgetDesc");
                let costElement = document.getElementById("widgetCost");
                let prevBtn = document.getElementById('previousBtn');
                let nextBtn = document.getElementById('nextBtn');
                imgElement.setAttribute('src', widget.url);
                descElement.innerHTML = 'Description: ' + widget.description;
                costElement.innerHTML = 'Asking Price: ' + widget.pence_price + ' p';
                if (currentWidgetIndex === 0) {
                    prevBtn.disabled = true;
                } else {
                    prevBtn.disabled = false;
                }
                if (currentWidgetIndex === widgets.length - 1) {
                    nextBtn.disabled = true;
                } else {
                    nextBtn.disabled = false;
                }
            }
            this.nextWidget = function () {
                if (currentWidgetIndex !== widgets.length - 1) {
                    currentWidgetIndex = currentWidgetIndex + 1;
                    displayWidget(widgets[currentWidgetIndex]);
                }
            }
            this.prevWidget = function () {
                if (currentWidgetIndex !== 0) {
                    currentWidgetIndex = currentWidgetIndex - 1;
                    displayWidget(widgets[currentWidgetIndex]);
                }
            }
            this.loadContent = function () {
                let userName = get_field_value('oucu');
                if (isValidOUCU(userName)) {
                    loadWidgets();
                    getClient();
                    $("#hidden-content").show();
                } else {
                    alert('Please enter valid user id');
                }
            }
            this.addToOrder = function () {
                let userName = get_field_value('oucu');
                let password = get_field_value('password');
                let price = get_field_value('agreedPrice');
                let amount = get_field_value('amount');
                let currentWidgetId = widgets[currentWidgetIndex].id;
                $.post('http://137.108.93.222/openstack/api/order_items?OUCU=' + userName + '&password=' + password, {
                    OUCU: userName,
                    password: password,
                    order_id: orderId,
                    widget_id: currentWidgetId,
                    number: amount,
                    pence_price: price
                },
                    function (data) {
                        var obj = $.parseJSON(data);
                        if (obj.status != "success") {
                            alert(obj.message);
                        } else {
                            displayOrderItem()
                            mapOrderLocations();
                        }
                    });
            }
            function createOrder() {
                let oucu = get_field_value('oucu')
                let password = get_field_value('password');
                $.post('http://137.108.93.222/openstack/api/orders?oucu=' + oucu + '&password=' + password, {
                    OUCU: oucu,
                    password: password,
                    client_id: client.id,
                    latitude: lat,
                    longitude: long
                }, function (data) {
                    var obj = $.parseJSON(data);
                    if (obj.status == "fail" || obj.status == "error") {
                        alert('Add order failed');
                    } else {
                        orderId = obj.data[0].id;
                    }
                });
            }
            function getClient() {
                let oucu = get_field_value('oucu');
                let clientId = get_field_value('clientId');
                let password = get_field_value('password');
                let path = 'http://137.108.93.222/openstack/api/clients/' + clientId +
                    '?OUCU=' + oucu + '&password=' + password;
                $.get(path,
                    function (data) {
                        var obj = $.parseJSON(data);
                        if (obj.status == "fail") {
                            alert('get client ' + obj.data[0].reason);
                        } else {
                            client = obj.data[0];
                            setClientText();
                            createOrder()
                        }
                    });
            }
            function loadWidgets() {
                let oucu = get_field_value('oucu');
                let password = get_field_value('password');
                let path = 'http://137.108.93.222/openstack/api/widgets?OUCU=' + oucu + '&password=' + password;
                $.get(path,
                    function (data) {
                        var obj = $.parseJSON(data);
                        if (obj.status == "fail") {
                            alert(obj.data[0].reason);
                        } else {
                            if (obj.data.length > 0) {
                                widgets = obj.data;
                                if (obj.data) {
                                    currentWidgetIndex = 0;
                                    displayWidget(obj.data[0])
                                }
                            }
                        }
                    });

            }
            function displayOrderItem() {
                let widget = widgets[currentWidgetIndex];
                let price = get_field_value('agreedPrice');
                let amount = get_field_value('amount');

                let totalPrice = (price * amount);
                let itemVat = ((totalPrice / 100) * 20)

                subtotal += totalPrice;
                vat += itemVat ;
                total += (totalPrice + itemVat) ;
                document.getElementById("subtotal").innerHTML = 'Sub Total: ' + Math.round(subtotal) + 'p';
                document.getElementById("vat").innerHTML = 'VAT: ' + Math.round(vat)+ 'p';
                document.getElementById("total").innerHTML = 'Total: ' + Math.round(total)+ 'p';

                document.getElementById("widget_cost").insertAdjacentHTML('beforeend', '<p>' + totalPrice + 'p</p>');
                document.getElementById("widget_names").insertAdjacentHTML('beforeend', '<p>' + widget.description + 'p</p>');
            }

            function setClientText() {
                let time = new Date().toLocaleTimeString();
                document.getElementById("clientAddr").innerHTML =
                    'Order for: ' + client.name + ' at ' + client.address
                    + '<p>Latitude: ' + lat + '</p>'
                    + '<p>Longitude: ' + long + '</p> <p>Time: ' + time + '</p>';
            }

            function mapOrderLocations() {
               $("#map_canvas").show();
                let userName = get_field_value('oucu');
                let password = get_field_value('password');
                let path = 'http://137.108.93.222/openstack/api/orders?OUCU=' + userName + '&password=' + password;
                $.get(path,
                    function (data) {
                        var obj = $.parseJSON(data);;
                        if (obj.status == "fail") {
                            alert(data.message);
                        } else {
                            for (let i = 0; i <= obj.data.length - 1; i++) {
                                var meetingLocation = new plugin.google.maps.LatLng(obj.data[i].latitude, obj.data[i].longitude);
                                map.addMarker({
                                    'position': meetingLocation,
                                    'title': "Order no:" + obj.data[i].id
                                }, function (marker) {
                                    marker.showInfoWindow()
                                });
                            }
                        }
                    });
             }
            initializeData()
        }
        this.sales = new Sales();
    }
};
app.initialize();