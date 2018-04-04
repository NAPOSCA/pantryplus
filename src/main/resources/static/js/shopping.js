function initialize() {
	const manyCategoryItemsDivs = document.querySelectorAll(".category-items");
	for (let i = 0; i < manyCategoryItemsDivs.length; i++) {
		const categoryDiv = manyCategoryItemsDivs[i];
		const categorySection = categoryDiv.querySelector(".category");
		const items = categoryDiv.querySelector(".items");
		categorySection.addEventListener("click", () => {
			toggleVisibility(items);
		});
	}

	const buttons = document.querySelectorAll("button.add");
	for(let i = 0; i < buttons.length; i++) {
		const button = buttons[i];
		button.addEventListener("click", () => {
			addToCart(1);
		});
	}
}

function toggleVisibility(items) {
	items.classList.toggle("hidden");
	items.classList.toggle("visible");
}

function addToCart(productId) {}