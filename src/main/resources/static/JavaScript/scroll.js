document.addEventListener('scroll', () => {
    const layerBackground = document.querySelector('.layer2');
    const scrollPosition = window.scrollY; // Get vertical scroll position
    const layerHeight = layerBackground.offsetHeight; // Get height of the element
    const layerTop = layerBackground.offsetTop; // Get the distance from the top of the page to .layer2
    const maxScroll = layerHeight * 0.5; // Adjust the multiplier to limit the scroll (you can modify 0.5 for a different effect)

    // Check if the .layer2 is in the viewport (starting the animation only when it's in view)
    if (scrollPosition + window.innerHeight > layerTop) {
        const movement = Math.min((scrollPosition - layerTop) * 0.2, maxScroll); // Adjust the multiplier for speed and limit the movement
        layerBackground.style.backgroundPosition = `center ${movement}px`;
    }
});


document.addEventListener("scroll", () => {
    const layer4 = document.querySelector(".layer4"); // Select the layer4 element
    if (!layer4) return; // Exit if layer4 doesn't exist

    // Get the bounding rectangle of layer4 relative to the viewport
    const rect = layer4.getBoundingClientRect();
    const layerTop = rect.top;
    const layerBottom = rect.bottom;

    // Only apply effects if layer4 is visible within the viewport
    if (layerTop < window.innerHeight && layerBottom > 0) {
		const scrollPosition = window.scrollY - layer4.offsetTop; // Calculate relative scroll position
        
		// Select elements
        const square = layer4.querySelector(".square");
        const triangle = layer4.querySelector(".triangle");
        const yellowCircle = layer4.querySelector(".circle.yellow");
        const blueDotSquare = layer4.querySelector(".squaredot");

        // Adjust the position and rotation based on scroll
        if (square) {
            square.style.transform = `translateY(${scrollPosition * 0.2}px)`; // Moves at half the scroll speed
        }
        if (triangle) {
            triangle.style.transform = `rotate(15deg) translateY(${scrollPosition * 0.1}px)`; // Moves slower and rotates
        }
        if (yellowCircle) {
            yellowCircle.style.transform = `translateY(${-scrollPosition * 0.2}px)`; // Moves in the opposite direction
        }
        if (blueDotSquare) {
            blueDotSquare.style.transform = `translateY(${-scrollPosition * 0.2}px)`; // Moves in the opposite direction
        }
    }
});



