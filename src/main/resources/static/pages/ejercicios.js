import { hacerFetch } from '../utils/apiUtils.js'

// Lugar donde se va a almacenar la lista
const zonaTarjetaEjercicios = document.getElementById("listaEjercicios")
// Lista de ejercicios de la api y de músculos
const ejercicios = await hacerFetch("GET", "/ejercicios")
const musculos = await hacerFetch("GET", "/musculos")

// Buscador de ejercicios
const buscadorEjercicios = document.getElementById("buscador")
// Botón para crear ejercicios
const btnCrearEjercicio = document.getElementById("crearEjercicio")

// Construcción de la lista en el html
zonaTarjetaEjercicios.innerHTML = ""

// Función para compara el ejercicio con lo buscado en el buscador
function mostrarPorNombreBuscador(textoBusqueda, elementos) {
    for (let elemento of document.querySelectorAll(elementos)) {
        // Se extrae el texto de cada fila y se pone en minúscula para comparar
        let nombre = elemento.querySelector("h2")
        nombre = nombre.textContent.toLowerCase()

        // EL NOMBRE COMIENZA POR LO ESCRITO EN EL BUSCADOR - SE MUESTRA
        if (nombre.startsWith(textoBusqueda)) {
            elemento.style.display = ""
        // EL NOMBRE NO COMIENZA POR LO ESCRITO - NO SE MUESTRA
        } else {
            elemento.style.display = "none"
        }
    }
}

// Añade los ejercicios en lista al cargar la página
if (ejercicios.length === 0) {
    zonaTarjetaEjercicios.innerHTML = `<p class="advertencia"><strong>⚠️ Advertencia</strong><br>Aun no hay ejercicios creados</p>`
} else {
    for (let ejercicio of ejercicios) {
        zonaTarjetaEjercicios.innerHTML += `
        <li class="tarjeta">
            <img src=${ejercicio.urlImagen}>
            <h2>${ejercicio.nombre}</h2>
            <a href="ejercicioDetalle.html?id=${ejercicio.id}" class="btn-ver-mas">Ver más</a>
        </li>
    `
    }
}

// Acción del buscador por nombre
buscadorEjercicios.addEventListener("input", function () {
    const busquedaActual = this.value.toLowerCase();
    mostrarPorNombreBuscador(busquedaActual, "#listaEjercicios li")
});

// Si no hay músculos, no puedes crear ejercicios
if (musculos.length < 1) {
    btnCrearEjercicio.classList.add("deshabilitado")
}