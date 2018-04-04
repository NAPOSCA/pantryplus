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
		const productId = parseInt(button.value);
		button.addEventListener("click", () => {
			addToCart(productId);
		});
	}
}

function toggleVisibility(items) {
	items.classList.toggle("hidden");
	items.classList.toggle("visible");
}

function addToCart(productId) {
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.response);
		}
	};
	xhr.open("POST", `/cart/items?productId=${productId}&quantity=1`, true);
	xhr.send();
}

function removeFromCart(productId) {
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.response);
		}
	};
	xhr.open("POST", `/cart/items?productId=${productId}&quantity=-1`, true);
	xhr.send();
}