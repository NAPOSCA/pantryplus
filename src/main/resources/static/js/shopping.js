function initialize() {
	const manyCategoryItemsDivs = document.querySelectorAll(".category-items");
	for (let i = 0; i < manyCategoryItemsDivs.length; i++) {
		const categoryDiv = manyCategoryItemsDivs[i];
		const categorySection = categoryDiv.querySelector(".category");
		const indicators = categorySection.querySelectorAll(".indicator");
		const items = categoryDiv.querySelector(".items");
		categorySection.addEventListener("click", () => {
			toggleVisibility(items);
			indicators.forEach(indicator => {
				toggleVisibility(indicator);
			});
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

	const switchButtons = document.querySelectorAll(".switch__toggle");
	for(let i = 0; i < switchButtons.length; i++) {
		const button = switchButtons[i];
		const productId = parseInt(button.value);
		button.addEventListener("change", () => {
			if(button.checked === true){
				createLineItem(productId);
			} else{
				removeItemFromCart(productId);
			}
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
	xhr.open("PATCH", `/carts/${cartId}/items/${productId}?increase=true`, true);
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
	xhr.open("PATCH", `/carts/${cartId}/items/${productId}?increase=false`, true);
	xhr.send();
}

function updateCartNumber(response) {
	const interface = document.querySelector(`div#product-${response.product.id}`);
	const value = interface.querySelector("span.value");
	value.innerText = response.quantity;
	if(response.cart) {
		const couponsUsed = parseInt(response.cart.couponsUsed);
		setTotalCouponsUsed(couponsUsed);
	} else {
		if(response.product.cost) {
			const cost = response.product.cost;
			const pageCouponsUsed = getTotalCouponsUsedFromPage();
			const serverCouponsUsed = pageCouponsUsed - cost;
			setTotalCouponsUsed(serverCouponsUsed);
		}
	}
}

function setTotalCouponsUsed(couponsUsed) {
	const couponsUsedSpan = document.querySelector("span.coupon-used");
	couponsUsedSpan.innerText = couponsUsed;
}

function getTotalCouponsUsedFromPage() {
	const couponsUsedSpan = document.querySelector("span.coupon-used");
	return couponsUsedSpan.innerText;
}