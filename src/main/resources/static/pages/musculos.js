import { hacerFetch } from '../utils/apiUtils.js'

// CONTENEDORES
const zonaTarjetasMusculos = document.getElementById("listaMusculos")
const buscadorMusculos = document.getElementById("buscador")
const main = document.querySelector("main")
const contenedorMusculos = document.getElementById("contenedorMusculos")

// Lista de músculos de la api
const musculos = await hacerFetch("GET", "/musculos")

// Parametros URL
const parametros = new URLSearchParams(window.location.search)
const musculoEliminado = parametros.get('musculoEliminado')

// Función para compara el músculo con lo buscado en el buscador
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

// Función para eliminar poner un mensaje de éxito si el músculo fue eliminado
function agregarMensajeExitoMusculoEliminado() {
    const mensajeEliminado = document.createElement('p')
    main.insertBefore(mensajeEliminado, contenedorMusculos)
    mensajeEliminado.innerHTML = `<p class="exito">✅<strong> ÉXITO</strong><br>El músculo se eliminó con éxito</p>`
}

// ============  MAIN ================= //
// Añade los musculos en lista al cargar la página
if (musculos.length === 0) {
    zonaTarjetasMusculos.innerHTML = `<p class="advertencia"><strong>⚠️ Advertencia</strong><br>Aun no hay músculos creados</p>`
} else {
    for (let musculo of musculos) {
        zonaTarjetasMusculos.innerHTML += `
        <li class="tarjeta">
            <img src=${musculo.urlImagen} >
            <h2>${musculo.nombre}</h2>
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

// Agregar mensaje de ÉXITO si eliminó un músculo
if (musculoEliminado) {
    agregarMensajeExitoMusculoEliminado()
    // Quita el parámetro de la url
    history.replaceState(null, '', window.location.pathname)
}