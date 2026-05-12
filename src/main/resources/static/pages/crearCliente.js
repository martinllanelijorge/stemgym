import { hacerFetch } from '../utils/apiUtils.js'

// zona donde se ubicará el botón de ir hacia atrás y creación del botón
const zonaBotonParaAtras = document.getElementById("zonaBotonAtras")
zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="clientes.html">Volver</a>`

// Abdorción de datos desde el formulario
document.getElementById('formCrearCliente').onsubmit = async (elemento) => {

    elemento.preventDefault()
    // Obtenemos el formulario que se ha enviado
    const form = elemento.target;

    // Convertimos el formulario a un objeto
    const clienteEditado = {
        nombre: form.nombre.value,
        edad: Number(form.edad.value),
        genero: form.generosPosibles.value,
        pesoActual: Number(form.pesoActual.value),
        pesoObjetivo: Number(form.pesoObjetivo.value),
        urlImagen: form.avatar.value
    }

    // Petición put para que se guarden los cambios y vuelta al detalle
    await hacerFetch(`POST`, `/clientes`, clienteEditado)
    window.location.href = `clientes.html`
}