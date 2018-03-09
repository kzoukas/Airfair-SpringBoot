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
