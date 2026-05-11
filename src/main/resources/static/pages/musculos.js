import { hacerFetch } from '../utils/apiUtils.js'

// Lugar donde se va a almacenar la lista
const zonaTarjetasMusculos = document.getElementById("listaMusculos")
// Lista de músculos de la api
const musculos = await hacerFetch("GET", "/musculos")
// Buscador de músculos
const buscadorMusculos = document.getElementById("buscador")

// Construcción de la lista en el html
zonaTarjetasMusculos.innerHTML = ""

// Función para compara el cliente con lo buscado en el buscador
function mostrarPorNombreBuscador(textoBusqueda, elementos) {
    for (let elemento of document.querySelectorAll(elementos)) {
        // Se extrae el texto de cada fila y se pone en minúscula para comparar
        let nombre = elemento.querySelector("p")
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

// Añade los clientes en lista al cargar la página
if (musculos.length === 0) {
    zonaTarjetasMusculos.innerHTML = '<li class="mensaje-error-api">No se han encontrado músculos</li>'
} else {
    for (let musculo of musculos) {
        zonaTarjetasMusculos.innerHTML += `
        <li class="tarjeta">
            <img src=${musculo.urlImagen}>
            <p>${musculo.nombre}</p>
            <a href="musculoDetalle.html?id=${musculo.id}" class="btn-ver-mas">Ver más</a>
        </li>
    `
    }
}

// Acción del buscador por nombre
buscadorMusculos.addEventListener("input", function () {
    const busquedaActual = this.value.toLowerCase();
    mostrarPorNombreBuscador(busquedaActual, "#listaMusculos li")
});