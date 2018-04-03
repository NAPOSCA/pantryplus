function initialize() {
	const manyCategoryItemsDivs = document.querySelectorAll(".category-items");
	for (var i = 0; i < manyCategoryItemsDivs.length; i++) {
		const categoryDiv = manyCategoryItemsDivs[i];
		const items = categoryDiv.querySelector(".items");
		categoryDiv.addEventListener("click", () => {
			toggleVisibility(items);
		});
	}
}

function toggleVisibility(items) {
	items.classList.toggle("hidden");
	items.classList.toggle("visible");
}