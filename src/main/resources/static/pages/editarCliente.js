import { hacerFetch } from '../utils/apiUtils.js'

// Obtención de la id del cliente
const parametros = new URLSearchParams(window.location.search)
const idCliente = parametros.get('id')

// ================ FUNCIONES =============== //

// Función que permite cargar en el formulario datos previos, para no reescribir (datos del usuario)
async function cargarDatosClienteEnFormulario() {

    // Realizamos petición GET para obtener un pizzas específico
    const cliente = await hacerFetch(`GET`, `/clientes/${idCliente}`)

    // Rellenamos el formulario con los datos
    const form = document.getElementById('formEditar')
    form.nombre.value = cliente.nombre 
    form.edad.value = cliente.edad 
    form.generosPosibles.value = cliente.genero 
    form.pesoActual.value = cliente.pesoActual 
    form.pesoObjetivo.value = cliente.pesoObjetivo 
    form.avatar.value = cliente.urlImagen 
}

// ======== MAIN ========= //
// zona donde se ubicará el botón de ir hacia atrás y creación del botón
const zonaBotonParaAtras = document.getElementById("zonaBotonAtras")
zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="clienteDetalle.html?id=${idCliente}">Volver</a>`

// Abdorción de datos
document.getElementById('formEditar').onsubmit = async (elemento) => {
    elemento.preventDefault()

    // Obtenemos el formulario que se ha enviado
    const form = elemento.target;

    // Convertimos el formulario a un objeto
    const clienteEditado = {
        nombre: form.nombre.value, 
        edad: Number(form.edad.value), 
        genero: form.genero.value,
        pesoActual: Number(form.pesoActual.value),
        pesoObjetivo: Number(form.pesoObjetivo.value), 
        urlImagen: form.avatar.value
    }

    // Petición put para que se guarden los cambios y vuelta al detalle
    await hacerFetch(`PUT`, `/cliente/${idCliente}`)
    window.location = `detalleCliente.html?id=${idCliente}`;
}

cargarDatosClienteEnFormulario()

