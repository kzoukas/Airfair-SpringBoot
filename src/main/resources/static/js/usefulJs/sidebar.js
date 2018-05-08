var mySlider = $("#ex2").slider();
// $("#departureTime1Filter").val(mySlider.get);
// $("#ex2").on("slide", function(slideEvt) {
//     $("#departureTime1Filter").val(slideEvt.value[0]).html();
//     $("#departureTime2Filter").val(slideEvt.value[1]).html();
// });
// mySlider.on('slide', function (value) { document.getElementById("arrivalTime1Filter").innerHTML = value.value[0];
//     document.getElementById("arrivalTime1Filter").value = value.value[0];
//     document.getElementById("arrivalTime2Filter").innerHTML = value.value[1];
//     document.getElementById("arrivalTime2Filter").value = value.value[1];
// })
mySlider.on('slide', function (value) { document.getElementById("departureTime1Filter").value = value.value[0];
    document.getElementById("departureTime2Filter").value = value.value[1];
})
var mySlider2 = $("#ex1").slider();
// $("#ex1").on("slide", function(slideEvt2) {
//     $("#arrivalTime1Filter").val(slideEvt2.value[0]);
//     $("#arrivalTime2Filter").val(slideEvt2.value[1]);
// });
mySlider2.on('slide', function (value) { document.getElementById("arrivalTime1Filter").innerHTML = value.value[0];
    document.getElementById("arrivalTime1Filter").value = value.value[0];
    document.getElementById("arrivalTime2Filter").innerHTML = value.value[1];
    document.getElementById("arrivalTime2Filter").value = value.value[1];
})

var slider = document.getElementById("hourRange");
var output = document.getElementById("maxHours");
output.innerHTML = slider.value; // Display the default slider value
slider.oninput = function () {
    output.innerHTML = this.value;
}

var slider1 = document.getElementById("priceRange");
var output1 = document.getElementById("maxPrice");
output1.innerHTML = slider1.value; // Display the default slider value
slider1.oninput = function () {
    output1.innerHTML = this.value;
}

var sliderConnectingTime = document.getElementById("connectingHoursRange");
var outputConnectingTime = document.getElementById("connectingHours");
outputConnectingTime.innerHTML = sliderConnectingTime.value; // Display the default slider value
sliderConnectingTime.oninput = function () {
    outputConnectingTime.innerHTML = this.value;
}

function a_onClick() {
    $('#logoId').children().fadeToggle();
}
// Instantiate a slider

