var slider = document.getElementById("hourRange");
var output = document.getElementById("maxHours");
output.innerHTML = slider.value; // Display the default slider value

// Update the current slider value (each time you drag the slider handle)
slider.oninput = function() {
    output.innerHTML = this.value;
}

var slider1 = document.getElementById("priceRange");
var output1 = document.getElementById("maxPrice");
output1.innerHTML = slider1.value; // Display the default slider value

// Update the current slider value (each time you drag the slider handle)
slider1.oninput = function() {
    output1.innerHTML = this.value;
}