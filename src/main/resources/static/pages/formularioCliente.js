import { hacerFetch } from '../utils/apiUtils.js'

// Obtención de la id del cliente y parametros 
const parametros = new URLSearchParams(window.location.search)
const idCliente = parametros.get('id')
const accion = parametros.get('accion')

// Localización del formulario, titulo y botón de formulario
const form = document.getElementById('formCliente')
const titulo = document.getElementById('tituloFormularioCliente')
const botonFormulario = document.getElementById('botonFormularioCliente')

// ================ FUNCIONES =============== //

// Función que permite cargar en el formulario datos previos, para no reescribir (datos del usuario)
async function cargarDatosClienteEnFormulario() {

    // Realizamos petición GET para obtener un músculo específico
    const cliente = await hacerFetch(`GET`, `/clientes/${idCliente}`)

    // Rellenamos el formulario con los datos
    form.nombre.value = cliente.nombre
    form.edad.value = cliente.edad
    form.generosPosibles.value = cliente.genero
    form.pesoActual.value = cliente.pesoActual
    form.pesoObjetivo.value = cliente.pesoObjetivo
    form.avatar.value = cliente.urlImagen
}

// Función que recupera los datos del formulario y crea un cliente
function crearClienteDesdeFormulario() {
    let datosCliente = {
        nombre: form.nombre.value,
        edad: Number(form.edad.value),
        genero: form.generosPosibles.value,
        pesoActual: Number(form.pesoActual.value),
        pesoObjetivo: Number(form.pesoObjetivo.value),
        urlImagen: form.avatar.value
    }
    return datosCliente
}

// ======== MAIN ========= //
// zona donde se ubicará el botón de ir hacia atrás y creación del botón
const zonaBotonParaAtras = document.getElementById("zonaBotonAtras")
zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="clientes.html">Volver</a>`

// Comprueba si se quiere editar el ususario o crear uno nuevo - Para cargar los datos
// CAMBIA LOS TÍTULOS Y REDIRECCIONES DE BOTONES
// QUIERE EDITAR
if (accion === 'editar') {
    await cargarDatosClienteEnFormulario()
    titulo.textContent = "EDITAR CLIENTE"
    zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="clienteDetalle.html?id=${idCliente}">Volver</a>`
    botonFormulario.textContent = "Actualizar"
    // QUIERE CREAR
} else {
    titulo.textContent = "CREAR CLIENTE"
    botonFormulario.textContent = "Crear"
}

// Abdorción de datos
document.getElementById('formCliente').onsubmit = async (elemento) => {

    elemento.preventDefault()
    // Obtenemos el formulario que se ha enviado
    const form = elemento.target;
    let cliente = crearClienteDesdeFormulario()

    // ERA CREAR CLIENTE
    if (accion === "crear") {
        // Crea al cliente
        await hacerFetch(`POST`, `/clientes`, cliente)
        window.location.href = `clientes.html`
        // ERA EDITAR CLIENTE
    } else {
        cliente.id = idCliente
        // Petición put para que se guarden los cambios y vuelta al detalle
        await hacerFetch(`PUT`, `/clientes/${idCliente}`, cliente)
        window.location.href = `clienteDetalle.html?id=${idCliente}`
    }
}




