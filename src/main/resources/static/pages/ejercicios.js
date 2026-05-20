import { hacerFetch } from '../utils/apiUtils.js'

// Contenedores
const zonaTarjetaEjercicios = document.getElementById("listaEjercicios")
const buscadorEjercicios = document.getElementById("buscador")
const btnCrearEjercicio = document.getElementById("crearEjercicio")
const main = document.querySelector("main")
const contenedorEjercicios = document.getElementById("contenedorEjercicios")

// Lista de ejercicios de la api y de músculos
const ejercicios = await hacerFetch("GET", "/ejercicios")
const musculos = await hacerFetch("GET", "/musculos")

// Obtención de la id del ejercicio y parametros
const parametros = new URLSearchParams(window.location.search)
const ejercicioEliminado = parametros.get('ejercicioEliminado')

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

// Función para agregar un mensaje de éxito eliminado para ejercicio
function agregarMensajeExitoEliminado() {
    const mensajeEliminado = document.createElement('p')
    main.insertBefore(mensajeEliminado, contenedorEjercicios)
    mensajeEliminado.innerHTML = `<p class="exito">✅<strong> ÉXITO</strong><br>El ejercicio se eliminó con éxito</p>`
}

// ================ MAIN =================== //
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

// Deshabilita el botón de crear ejercicio si no hay músculos
if (musculos.length < 1) {
    btnCrearEjercicio.classList.add("deshabilitado")
    btnCrearEjercicio.removeAttribute("href")
    btnCrearEjercicio.title = "No puedes crear ejercicios si no existen músculos"
}

// Agrega el mensaje de éxito si se eliminó un ejercicio
if (ejercicioEliminado) {
    agregarMensajeExitoEliminado()
    // Quita el parámetro de ejercicio eliminado
    history.replaceState(null, '', window.location.pathname)
}