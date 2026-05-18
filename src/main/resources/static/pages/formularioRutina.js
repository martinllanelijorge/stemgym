import { hacerFetch } from '../utils/apiUtils.js'

// Obtención de la id de la rutina y parametros 
const parametros = new URLSearchParams(window.location.search)
const idRutina = parametros.get('idRutina')
const idCliente = parametros.get('idCliente')
const accion = parametros.get('accion')

// Uso de fetch para obtención de los datos de la rutina
const ejerciciosDisponibles = await hacerFetch(`GET`, `/ejercicios`)

// Contenedores del DOM
const form = document.getElementById('formRutina')
const titulo = document.getElementById('tituloFormularioRutina')
const botonFormulario = document.getElementById('botonFormularioRutina')
const zonaRutinaActual = document.getElementById('rutinaActual')
const zonaEjerciciosDisponibles = document.getElementById('ejerciciosDisponibles')

// ============ FUNCIONES =============== //
// Función que crea las tarjetas de ejercicios de la rutina al darle al más
function crearTarjetaEjercicioRutina(ejercicio) {
    const nuevaTarjeta = document.createElement('li')
    nuevaTarjeta.innerHTML = `
        <img src="${ejercicio.urlImagen}">
        <h2>${ejercicio.nombre}</h2>
        <button class="btn-menos">-</button> 
    `
    nuevaTarjeta.className = "tarjeta-pequenia tarjeta-azul"
    return nuevaTarjeta
}
// Función que agrega las tarjetitas de los ejercicios de la rutina (No los disponibles) a la zona de la rutina
function agregarEjercicioAZonaRutina(ejercicio) {
    const nuevaTarjeta = crearTarjetaEjercicioRutina(ejercicio)
    zonaRutinaActual.appendChild(nuevaTarjeta)
}

// ================ MAIN ================== //
// Construcción de las tarjetas de ejercicios disponibles con funcionalidad del botón más
for (const ejercicio of ejerciciosDisponibles) {
    // Creación de las tarjetas de ejercicios
    const li = document.createElement('li')
    li.className = 'tarjeta-pequenia'
    li.innerHTML = `
        <img src="${ejercicio.urlImagen}">
        <h2>${ejercicio.nombre}</h2>
        <button class="btn-mas">+</button>
    `

    // Asignación de la función del botón más
    const botonMas = li.querySelector('.btn-mas')
    botonMas.addEventListener('click', () => {
        agregarEjercicioAZonaRutina(ejercicio)
    })

    // Agrega la tarjeta a la zona de los ejercicios disponibles
    zonaEjerciciosDisponibles.appendChild(li)
}