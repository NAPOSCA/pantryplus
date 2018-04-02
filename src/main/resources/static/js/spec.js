"use strict";
describe("shopping.js", () => {
	let container, toggleItemsDisplaySpy;
	beforeEach(() => {
		//declare Spies
		toggleItemsDisplaySpy = spyOn(window, "toggleItemsDisplay").and.callThrough();
		//create fake container
		container = document.createElement("div");
		container.classList.add("container");
		container.innerHTML = `
		<div class="category-items">
			<section class="category">
				<h2>Personal Hygiene</h2>
			</section>
			<ul class="items visible">
				<li class="item">Soap</li>
				<li class="item">Shampoo</li>
				<li class="item">Three-in-one</li>
			</ul>
		</div>`;
		document.body.append(container);
	});
	afterEach(() => {
		container.remove();
	});
	it("toggleItemsDisplay()", () => {

	});
});