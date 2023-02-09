 const input = document.getElementById("imageArticle");
 const image = document.getElementById("previewImage");
 
 var loadImage = (e) => {
	 const [pic] = e.files
	 if (pic) {
		 image.src = URL.createObjectURL(pic)
	 }
 }
 
 const hamburger = document.getElementById("hamburger");
 const menu = document.getElementById("menu");
 
 hamburger.addEventListener("click", () => {
	 menu.classList.toggle("mobile_menu");
 })
 
// const navbar = document.getElementById("navbar");
// const sticky = navbar.offsetTop;
 
// if (window.pageYOffset >= sticky) {
//	 navbar.classList.add("sticky");
// } else {
//	 navbar.classList.remove("sticky");
// }