window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    const navbox = document.querySelector(".navbox");
    const logo = document.querySelector("#logo");

    if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
        navbox.classList.add("scrolled");
        if (logo) logo.src = "../images/logo2.png";
    } else {
        navbox.classList.remove("scrolled");
        if (logo) logo.src = "../images/logo.png";
    }
}
