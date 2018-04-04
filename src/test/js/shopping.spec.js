describe("shopping.js", () => {
	let container, toggleVisibilitySpy, addToCartSpy, removeFromCartSpy;
	beforeEach(() => {
		//declare Spies
		toggleVisibilitySpy = spyOn(window, "toggleVisibility").and.callThrough();
		addToCartSpy = spyOn(window, "addToCart").and.callThrough();
		removeFromCartSpy = spyOn(window, "removeFromCart").and.callThrough();
		//create fake container
		container = document.createElement("div");
		container.classList.add("container");
		container.innerHTML = `
		<div class="category-items">
			<section class="category">
				<h2>Personal Hygiene</h2>
			</section>
			<ul class="items hidden">
				<li class="item">
					<div class="interface">
						<button class="add" value="1"></button>
						<button class="remove" value="1"></button>
					</div>
				</li>
				<li class="item">
					<button class="add" value="2"></button>
				</li>
				<li class="item">
					<button class="add" value="3"></button>
				</li>
			</ul>
		</div>`;
		document.body.append(container);

		initialize();
	});
	afterEach(() => {
		container.remove();
	});
	describe("toggleVisibility()", () => {
		let categoryItems, items, category;
		beforeEach(() => {
			categoryItems = container.querySelector(".category-items");
			items = categoryItems.querySelector(".items");
			category = categoryItems.querySelector(".category");
		});
		it("should be called when a category is clicked", () => {
			category.click();
			expect(toggleVisibilitySpy).toHaveBeenCalled();
		});
		it("should add hidden class and remove visible class", () => {
			items.classList.remove("hidden");
			items.classList.add("visible");
			category.click();
			expect(items.classList.contains("hidden")).toBeTruthy();
			expect(items.classList.contains("visible")).toBeFalsy();
		});
		it("should add visible class and remove hidden class", () => {
			items.classList.remove("visible");
			items.classList.add("hidden");
			category.click();
			expect(items.classList.contains("visible")).toBeTruthy();
			expect(items.classList.contains("hidden")).toBeFalsy();
		});
	});
	describe("addToCart()", () => {
		let button;
		beforeEach(() => {
			button = container.querySelector("button.add");
		});
		it("should be called when button is clicked", () => {
			button.click();
			expect(addToCartSpy).toHaveBeenCalledWith(1);
		});
	})
	describe("removeFromCart(productId)", () => {
		let button;
		beforeEach(() => {
			button = container.querySelector("button.remove");
		});
		it("should be called when button is clicked", () => {
			button.click();
			expect(removeFromCartSpy).toHaveBeenCalledWith(1);
		});
	});
});