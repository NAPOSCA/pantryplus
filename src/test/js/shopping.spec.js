describe("shopping.js", () => {
	let container, toggleVisibilitySpy;
	beforeEach(() => {
		//declare Spies
		toggleVisibilitySpy = spyOn(window, "toggleVisibility").and.callThrough();
		//create fake container
		container = document.createElement("div");
		container.classList.add("container");
		container.innerHTML = `
		<div class="category-items">
			<section class="category">
				<h2>Personal Hygiene</h2>
			</section>
			<ul class="items hidden">
				<li class="item">Soap</li>
				<li class="item">Shampoo</li>
				<li class="item">Three-in-one</li>
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
});