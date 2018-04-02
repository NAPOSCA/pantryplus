function initialize() {
	const manyCategoryItemsDivs = document.querySelectorAll(".category-items");
	for (var i = 0; i < manyCategoryItemsDivs.length; i++) {
		const categoryDiv = manyCategoryItemsDivs[i];
		categoryDiv.addEventListener("click", () => {
			toggleItemsDisplay();
		});
	}
	console.log(manyCategoryItemsDivs)
}

function toggleItemsDisplay() {}