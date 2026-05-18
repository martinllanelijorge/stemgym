import { hacerFetch } from '../utils/apiUtils.js'

// Obtención de la id de la rutina y parametros 
const parametros = new URLSearchParams(window.location.search)
const idRutina = parametros.get('idRutina')
const idCliente = parametros.get('idCliente')
const accion = parametros.get('accion')

// Uso de fetch para obtención de los datos de la rutina
const ejerciciosDisponibles = await hacerFetch(`GET`, `/ejercicios`)
const cliente = await hacerFetch(`GET`, `/clientes/${idCliente}`)

// Contenedores del DOM
const form = document.getElementById('formRutina')
const titulo = document.getElementById('tituloFormularioRutina')
const botonFormulario = document.getElementById('botonFormularioRutina')
const zonaRutinaActual = document.getElementById('rutinaActual')
const zonaEjerciciosDisponibles = document.getElementById('ejerciciosDisponibles')

// zona donde se ubicará el botón de ir hacia atrás y creación del botón
const zonaBotonParaAtras = document.getElementById("zonaBotonAtras")
zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="clienteDetalle.html?id=${idCliente}">Volver</a>`

// Array de ejercicios para la rutina
let ejerciciosRutina = []

// ============ FUNCIONES =============== //
// Función que crea las tarjetas de ejercicios de la rutina al darle al más
function crearTarjetaEjercicioRutina(ejercicio) {
    const nuevaTarjeta = document.createElement('li')
    nuevaTarjeta.innerHTML = `
        <img src="${ejercicio.urlImagen}">
        <h2>${ejercicio.nombre}</h2>
        <button class="btn-menos" type="button">-</button> 
    `
    // Agrega el id al dataset para usarlo en la construcción del array posterior
    nuevaTarjeta.dataset.id = ejercicio.id

    // Agrega funcionalidad al botón menos
    const botonMenos = nuevaTarjeta.querySelector('.btn-menos')

    // Elimina la tarjeta si le da al botón menos
    botonMenos.addEventListener('click', () => {
        nuevaTarjeta.remove()
    })

    nuevaTarjeta.className = "tarjeta-pequenia tarjeta-azul"
    return nuevaTarjeta
}
// Función que agrega las tarjetitas de los ejercicios de la rutina (No los disponibles) a la zona de la rutina
function agregarEjercicioAZonaRutina(ejercicio) {
    const nuevaTarjeta = crearTarjetaEjercicioRutina(ejercicio)
    zonaRutinaActual.appendChild(nuevaTarjeta)
}

// Función para agregar los ejercicios al array de ejercicios de la rutina
function agregarEjerciciosAArrayRutina() {
    ejerciciosRutina = []
    // Recorre todos los ejercicios de la caja de la rutina
    for (const ejercicioSeleccionado of zonaRutinaActual.children) {
        // Compara el dataset id y el id del ejercicio y lo agrega si son iguales
        for (const ejercicioDisponible of ejerciciosDisponibles) {
            if (ejercicioDisponible.id == ejercicioSeleccionado.dataset.id) {
                ejerciciosRutina.push(ejercicioDisponible)
            }
        }
    }
}

// Función para crear la rutina desde el formulario
function crearRutinaDesdeFormulario() {
    agregarEjerciciosAArrayRutina()
    let rutina = {
        nombre: form.nombre.value,
        frecuenciaSemanal: Number(form.frecuenciaSemanal.value),
        cliente: cliente,
        ejercicios: ejerciciosRutina
    }

    return rutina
}

// Función para cargar los datos de la rutina editable
async function cargarDatosFormularioRutina() {
    const rutinaAEditar = await hacerFetch(`GET`, `/rutinas/${idRutina}`)
    form.nombre.value = rutinaAEditar.nombre
    form.frecuenciaSemanal.value = rutinaAEditar.frecuenciaSemanal

    // Crea las tarjetitas de los ejercicios que tiene la rutina
    for (const ejercicioDeLaRutina of rutinaAEditar.ejercicios) {
        agregarEjercicioAZonaRutina(ejercicioDeLaRutina)
    }
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
        <button class="btn-mas" type="button">+</button>
    `

    // Asignación de la función del botón más
    const botonMas = li.querySelector('.btn-mas')
    botonMas.addEventListener('click', () => {
        agregarEjercicioAZonaRutina(ejercicio)
    })

    // Agrega la tarjeta a la zona de los ejercicios disponibles
    zonaEjerciciosDisponibles.appendChild(li)
}

// ========= CARGA LOS DATOS DEL FORMULARIO PARA EDITAR LA RUTINA =========== //
if (accion === 'editar') {
    await cargarDatosFormularioRutina()
    titulo.textContent = "EDITAR RUTINA"
    zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="rutinaDetalle.html?id=${idRutina}">Volver</a>`
    botonFormulario.textContent = "Actualizar"
    // QUIERE CREAR
} else {
    titulo.textContent = "CREAR RUTINA"
    botonFormulario.textContent = "Crear"
}


// ============= BOTÓN DE CREAR O ACTUALIZAR ================= //
// Abdorción de datos
document.getElementById('formRutina').onsubmit = async (elemento) => {

    elemento.preventDefault()
    // Obtenemos el formulario que se ha enviado
    const form = elemento.target;
    let rutina = crearRutinaDesdeFormulario()

    // ERA CREAR 
    if (accion === "crear") {
        // Crea la rutina
        await hacerFetch(`POST`, `/rutinas`, rutina)
        window.location.href = `clienteDetalle.html?id=${idCliente}`
        // ERA EDITAR
    } else {
        rutina.id = idRutina
        // Petición put para que se guarden los cambios y vuelta al detalle
        await hacerFetch(`PUT`, `/rutinas/${idRutina}`, rutina)
        window.location.href = `rutinaDetalle.html?id=${idRutina}`
    }
}