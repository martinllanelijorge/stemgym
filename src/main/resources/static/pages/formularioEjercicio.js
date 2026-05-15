import { hacerFetch } from '../utils/apiUtils.js'

// Obtención de la id del cliente y parametros 
const parametros = new URLSearchParams(window.location.search)
const idEjercicio = parametros.get('id')

// Localización del formulario, titulo y botón de formulario
const form = document.getElementById('formEjercicio')
const titulo = document.getElementById('tituloFormularioEjercicio')
const botonFormulario = document.getElementById('botonFormularioEjercicio')

// Cargamos los músculos para la elección singular y múltiple
const musculos = await hacerFetch(`GET`, `/musculos`)

// Función que crea un elemento con estructura
function nuevoElemento(elemento, estructura) {
    let nuevoElemento = document.createElement(elemento);
    nuevoElemento.innerHTML = estructura
    return nuevoElemento
}

// Función para crear la estructura desplegable de todos los músculos, selección única
function crearEstructuraSelectMusculoPrincipal(){
    let estructura = `<select class="desplegable" name="musculoPrincipal" id="musculoPrincipal" required>`
    // Recorre todos los músculos
    for (const musculo of musculos) {
        estructura += `<option value="${musculo.id}">${musculo.nombre}</option>`
    }
    estructura += `</select>`
    return estructura
}

// Nueva función: genera un contenedor con checkboxes independientes
function crearEstructuraCheckboxesMusculosSecundarios(){
    let estructura = `<div class="contenedor-checkbox" id="contenedorMusculosSecundarios">`
    for (const musculo of musculos) {
        estructura += `
        <div class="opcion-checkbox">
            <input type="checkbox" name="musculosSecundarios" id=secundario_${musculo.id} value=${musculo.id}>
            <label for=secundario_${musculo.id}>${musculo.nombre}</label>
        </div>`
    }
    estructura += `</div>`
    return estructura
}

// ======== MAIN ========= //
// zona donde se ubicará el botón de ir hacia atrás y creación del botón
const zonaBotonParaAtras = document.getElementById("zonaBotonAtras")
zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="ejercicios.html">Volver</a>`



// Se añaden las 2 nuevas secciones del formulario, para las elecciones de músculo principal y secundarios
let labelMusculoPrincipal = nuevoElemento('label', `<label class="formulario-label" for="musculoPrincipal">MÚSCULO PRINCIPAL</label>`)
let inputMusculoPrincipal = nuevoElemento('select', crearEstructuraSelectMusculoPrincipal())
let labelMusculosSecundarios = nuevoElemento('label', `<label class="formulario-label" for="musculosSecundarios">MÚSCULOS SECUNDARIOS (opcional)</label>`)
let inputMusculosSecundarios = nuevoElemento('div', crearEstructuraCheckboxesMusculosSecundarios())

// Insertamos en el formulario la nueva estructura
form.insertBefore(labelMusculoPrincipal, botonFormulario)
form.insertBefore(inputMusculoPrincipal, botonFormulario)
form.insertBefore(labelMusculosSecundarios, botonFormulario)
form.insertBefore(inputMusculosSecundarios, botonFormulario)

// Abdorción de datos
document.getElementById('formEjercicio').onsubmit = async (elemento) => {

    elemento.preventDefault()
    // Obtenemos el formulario que se ha enviado
    const form = elemento.target;
    let ejercicio = crearEjercicioDesdeFormulario()

    // ERA CREAR CLIENTE
    if (accion === "crear") {
        // Crea al cliente
        await hacerFetch(`POST`, `/ejercicios`, ejercicio)
        window.location.href = `ejercicios.html`
        // ERA EDITAR CLIENTE
    } else {
        ejercicio.id = idEjercicio
        // Petición put para que se guarden los cambios y vuelta al detalle
        await hacerFetch(`PUT`, `/ejercicios/${idEjercicio}`, ejercicio)
        window.location.href = `ejercicioDetalle.html?id=${idEjercicio}`
    }
}

// Comprueba si se quiere editar el ejercicio o crear uno nuevo - Para cargar los datos
// CAMBIA LOS TÍTULOS Y REDIRECCIONES DE BOTONES
// QUIERE EDITAR
if (accion === 'editar') {
    cargarDatosEjercicioEnFormulario()
    titulo.textContent = "EDITAR EJERCICIO"
    zonaBotonParaAtras.innerHTML = `<a class="btn-verde" href="ejercicioDetalle.html?id=${idEjercicio}">Volver</a>`
    botonFormulario.textContent = "Actualizar"
    // QUIERE CREAR
} else {
    titulo.textContent = "CREAR EJERCICIO"
    botonFormulario.textContent = "Crear"
}