const inputNombre = document.getElementById("nombre");
const selectNombres = document.getElementById("logueados");

selectNombres.addEventListener("click", (event) => {
    inputNombre.value = event.target.value
})