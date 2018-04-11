function initialize() {
	const familySizeMenu = document.querySelector(".familySizeMenu");
	const schoolKidsMenu = document.querySelector(".schoolKidsMenu");
	const schoolKidsOptions = document.querySelectorAll(".schoolKidsMenu option")

	familySizeMenu.addEventListener("change", () => {
		const currentFamilySize = familySizeMenu.value;
		for (let i = 1; i < schoolKidsOptions.length; i++) {
			const currentOption = schoolKidsOptions[i];
			if (currentOption.value > currentFamilySize) {
				toggleVisibility(currentOption);
			}
		}
	})
}

function toggleVisibility(items) {
	items.classList.toggle("hidden");
}