function initializeUserForm() {
	const familySizeMenu = document.querySelector(".familySizeMenu");
	const schoolKidsMenu = document.querySelector(".schoolKidsMenu");
	const schoolKidsOptions = document.querySelectorAll(".schoolKidsMenu option")

	familySizeMenu.addEventListener("change", () => {
		const currentFamilySize = familySizeMenu.value;
		for (let i = 1; i < schoolKidsOptions.length; i++) {
			const currentOption = schoolKidsOptions[i];
			currentOption.classList.remove("hidden");
			if (currentOption.value > currentFamilySize)  {
				currentOption.classList.add("hidden");				
			}
		}
	})
}
