import { hacerFetch } from '../utils/apiUtils.js'

// Obtención de la id del músculo y parametros 
const parametros = new URLSearchParams(window.location.search)
const idMusculo = parametros.get('id')
const accion = parametros.get('accion')

// Localización del formulario, titulo y botón de formulario
const form = document.getElementById('formMusculo')
const titulo = document.getElementById('tituloFormularioMusculo')
const botonFormulario = document.getElementById('botonFormularioMusculo')

// ================ FUNCIONES =============== //

// Función que permite cargar en el formulario datos previos, para no reescribir (datos del usuario)
async function cargarDatosFormularioMusculo() {

    // Realizamos petición GET para obtener un musculo específico
    const musculo = await hacerFetch(`GET`, `/musculos/${idMusculo}`)

    // Rellenamos el formulario con los datos
    form.nombre.value = musculo.nombre
    form.frecuenciaRecomendada.value = musculo.frecuenciaRecomendada
    form.minVolumen.value = musculo.minVolumenSemanal
    form.maxVolumen.value = musculo.maxVolumenSemanal
    form.imagenMusculo.value = musculo.urlImagen
}

function crearMusculoDesdeFormulario() {
    let datosMusculo = {
        nombre: form.nombre.value,
        frecuenciaRecomendada: Number(form.frecuenciaRecomendada.value),
        minVolumenSemanal: Number(form.minVolumen.value),
        maxVolumenSemanal: Number(form.maxVolumen.value),
        urlImagen: form.imagenMusculo.value
    }
    return datosMusculo
}

// ======== MAIN ========= //
// zona donde se ubicará el botón de ir hacia atrás y creación del botón
const zonaBotonParaAtras = document.getElementById("zonaBotonAtras")
zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="musculos.html">Volver</a>`

// Comprueba si se quiere editar el músculo o crear uno nuevo - Para cargar los datos
// CAMBIA LOS TÍTULOS Y REDIRECCIONES DE BOTONES
// QUIERE EDITAR
if (accion === 'editar') {
    await cargarDatosFormularioMusculo()
    titulo.textContent = "EDITAR MÚSCULO"
    zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="musculoDetalle.html?id=${idMusculo}">Volver</a>`
    botonFormulario.textContent = "Actualizar"
    // QUIERE CREAR
} else {
    titulo.textContent = "CREAR MÚSCULO"
    botonFormulario.textContent = "Crear"
}

// Abdorción de datos
document.getElementById('formMusculo').onsubmit = async (elemento) => {

    elemento.preventDefault()
    // Obtenemos el formulario que se ha enviado
    const form = elemento.target;
    let musculo = crearMusculoDesdeFormulario()

    // ERA CREAR 
    if (accion === "crear") {
        // Crea al músculo
        await hacerFetch(`POST`, `/musculos`, musculo)
        window.location.href = `musculos.html`
        // ERA EDITAR
    } else {
        musculo.id = idMusculo
        // Petición put para que se guarden los cambios y vuelta al detalle
        await hacerFetch(`PUT`, `/musculos/${idMusculo}`, musculo)
        window.location.href = `musculoDetalle.html?id=${idMusculo}`
    }
}

