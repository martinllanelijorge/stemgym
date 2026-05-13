import { eliminarFetch, hacerFetch } from '../utils/apiUtils.js'

// Localizaciones en el DOM
const tituloEjercicio = document.getElementById('tituloEjercicio')
const zonaDetalleEjercicio = document.getElementById('zonaDetalleEjercicio')
const zonaMusculoPrincipal = document.getElementById('zonaMusculoPrincipal')
const listaMusculosSecundarios = document.getElementById('listaMusculosSecundarios')
const zonaVideoEjercicio = document.getElementById('zonaVideoEjercicio')
const btnVolver = document.getElementById('volver')

// Obtención de la id del ejercicio y parametros
const parametros = new URLSearchParams(window.location.search)
const idEjercicio = parametros.get('id')
const origen = parametros.get('origen')
const idMusculo = parametros.get('musculoId')

// Obtención de datos FETCH
const ejercicio = await hacerFetch(`GET`, `/ejercicios/${idEjercicio}`)
const musculoPrincipal = ejercicio.musculoPrincipal // No será nunca array
const musculosSecundarios = ejercicio.musculosSecundarios // Es un array

// ============= FUNCIONES =============== //
async function eliminarEjercicio(id){
    await eliminarFetch(`/musculos/${id}`)
}

//============== MAIN =============== //

// MODIFICAR TITULO
tituloEjercicio.textContent = ejercicio.nombre.toUpperCase()

// DESPLIEGUE DE DATOS DEL EJERCICIO
zonaDetalleEjercicio.innerHTML = `
    <img src=${ejercicio.urlImagen} />
    <p>${ejercicio.descripcion}. </p>
    <p><strong>Materiales:</strong> ${ejercicio.material}. </p>
    <div class="botones-editar-borrar">
        <a class="btn btn-editar" href="formularioEjercicio.html?id=${ejercicio.id}&accion=editar">Editar</a>
        <button class="btn btn-borrar" id="eliminarEjercicio">Borrar</button>
    </div>`

// MÚSCULO PRINCIPAL - TARJETA
zonaMusculoPrincipal.innerHTML = `<h2>MÚSCULO PRINCIPAL:</h2>
    <div class="tarjeta-pequenia">
        <img/ src=${musculoPrincipal.urlImagen} >
        <h2>${musculoPrincipal.nombre}</h2>
        <a href="musculoDetalle.html?id=${musculoPrincipal.id}&origen=ejercicio&ejercicioId=${idEjercicio}" class="btn-ver-mas">Ver más</a>
    </div>`

// MÚSCULOS SECUNDARIOS - TARJETAS
if (musculosSecundarios.length > 0) {
    listaMusculosSecundarios.innerHTML = `<h2>MÚSCULOS SECUNDARIOS:</h2>`
    // CREA UN UL PARA LA LISTA DE EJERCICIOS
    let listaEjercicios = document.createElement("ul")
    listaMusculosSecundarios.appendChild(listaEjercicios)

    // Añade cada músculo secundario a la lista de ejercicios
    for (const musculo of musculosSecundarios) {
        listaEjercicios.innerHTML += `
        <li class="tarjeta-pequenia">
            <img/ src=${musculo.urlImagen} >
            <h2>${musculo.nombre}</h2>
            <a href="musculoDetalle.html?id=${musculo.id}&origen=ejercicio&ejercicioId=${idEjercicio}" class="btn-ver-mas">Ver más</a>
        </li>
            `
    }
}

// ZONA VIDEO EJERCICIO
zonaVideoEjercicio.innerHTML = `
<h2>MIRA ESTE VÍDEO CON LA EXPLICACIÓN</h2>
<iframe width="560" height="315" src=${ejercicio.urlVideo} title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>`

// ======== ELIMINAR UN EJERCICIO =============== //
document.getElementById('eliminarEjercicio').addEventListener('click', async () => {
    // Pregunta al ususario por confirmación
    if (confirm(`¿Está seguro de que desea eliminar a ${ejercicio.nombre} de la base de datos ? `)) {
        await eliminarEjercicio(idEjercicio)
        // Redirige a la web de musculos
        window.location.href = "ejercicios.html";
    }
});


// BOTÓN VOLVER - VUELVE AL MUSCULO SI VENIA DEL MUSCULO
if (origen === "musculo") {
    btnVolver.href = `musculoDetalle.html?id=${idMusculo}`
}
