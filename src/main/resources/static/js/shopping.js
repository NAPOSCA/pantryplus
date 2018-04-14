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

function request(method, path, productId) {
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let cart = JSON.parse(xhr.response);
			const lineItem = getLineItemByProductId(cart, productId);
			let quantity = 0;
			if(lineItem) {
				quantity = lineItem.quantity;
			}
			updateProductQuantity(productId, quantity);
			setTotalCouponsUsed(cart.couponsUsed);
			setTotalMeatUsed(cart.meatPoundsUsed);
		}
	};
	xhr.open(method, path, true);
	xhr.send();
}

function getLineItemByProductId(cart, productId) {
	const lineItems = cart.lineItems;
	for (var i = 0; i < lineItems.length; i++) {
		const lineItem = lineItems[i];
		const product = lineItem.product;
		const id = product.id;
		if(id === productId) {
			return lineItem;
		}
	}
}

function updateProductQuantity(productId, quantity) {
	const interface = document.querySelector(`div#product-${productId}`);
	const value = interface.querySelector("span.value");
	value.innerText = quantity;
}

function addToCart(productId) {
	request("PATCH", `/carts/${cartId}/items/${productId}?increase=true`, productId);
}

function removeFromCart(productId) {
	request("PATCH", `/carts/${cartId}/items/${productId}?increase=false`, productId);
}

function createLineItem(productId) {
	request("POST", `/carts/${cartId}/items/${productId}?dichotomous=true`, productId);
}

function removeItemFromCart(productId) {
	request("DELETE", `/carts/${cartId}/items/${productId}`, productId);
}

function updateTotals(response) {
	if(response.cart) {
		const couponsUsed = parseInt(response.cart.couponsUsed);
		setTotalCouponsUsed(couponsUsed);
		const meatUsed = parseInt(response.cart.meatPoundsUsed);
		setTotalMeatUsed(meatUsed);
	} else {
		if(response.product.cost) {
			const cost = response.product.cost;
			const pageCouponsUsed = getTotalCouponsUsedFromPage();
			const serverCouponsUsed = pageCouponsUsed - cost;
			setTotalCouponsUsed(serverCouponsUsed);
		} else {
			const pageMeatUsed = getTotalMeatUsedFromPage();
			const serverMeatUsed = pageMeatUsed - 1;
			setTotalMeatUsed(serverMeatUsed);
		}
	}
}

function setTotalCouponsUsed(couponsUsed) {
	const couponsUsedSpan = document.querySelector("span.coupon-used");
	couponsUsedSpan.innerText = couponsUsed;
}

function setTotalMeatUsed(meatUsed) {
	const meatUsedSpan = document.querySelector("span.meat-used");
	meatUsedSpan.innerText = meatUsed;
}

function getTotalCouponsUsedFromPage() {
	const couponsUsedSpan = document.querySelector("span.coupon-used");
	return parseInt(couponsUsedSpan.innerText);
}

function getTotalMeatUsedFromPage() {
	const meatUsedSpan = document.querySelector("span.meat-used");
	return parseInt(meatUsedSpan.innerText);
}