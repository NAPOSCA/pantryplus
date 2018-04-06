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

	const addButtons = document.querySelectorAll("button.add");
	for(let i = 0; i < addButtons.length; i++) {
		const button = addButtons[i];
		const productId = parseInt(button.value);
		button.addEventListener("click", () => {
			addToCart(productId);
		});
	}

	const removeButtons = document.querySelectorAll("button.remove");
	for(let i = 0; i < removeButtons.length; i++) {
		const button = removeButtons[i];
		const productId = parseInt(button.value);
		button.addEventListener("click", () => {
			removeFromCart(productId);
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
			const response = JSON.parse(xhr.response);
			updateCartNumber(response);
		}
	};
	xhr.open("POST", `/cart/items?productId=${productId}&quantity=1`, true);
	xhr.send();
}

function removeFromCart(productId) {
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4 && xhr.status == 200) {
			const response = JSON.parse(xhr.response);
			updateCartNumber(response);
		}
	};
	xhr.open("POST", `/cart/items?productId=${productId}&quantity=-1`, true);
	xhr.send();
}

function updateCartNumber(response) {
	const interface = document.querySelector(`div#product-${response.product.id}`);
	const value = interface.querySelector("span.value");
	value.innerText = response.quantity;
}