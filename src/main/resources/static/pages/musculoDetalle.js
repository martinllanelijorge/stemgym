import { eliminarFetch, hacerFetch } from '../utils/apiUtils.js'

// Zona donde se pondrán los datos del musculo
const zonaMusculo = document.getElementById("zonaMusculo")
const zonaEjercicios = document.getElementById("ejerciciosParticipa")
const tituloMusculo = document.getElementById("tituloMusculo")

// Obtención de la id del músculo
const parametros = new URLSearchParams(window.location.search)
const idMusculo = parametros.get('id')

// Uso de fetch para obtención de los datos del músculo
const musculo = await hacerFetch(`GET`, `/musculos/${idMusculo}`)
const ejercicios = await hacerFetch(`GET`, `/musculos/${idMusculo}/ejercicios`)

// FUNCIONES
async function eliminarMusculo(id) {
    await eliminarFetch(`/musculos/${id}`)
}

// ============== MAIN ============= //
// Zona de datos del músculo 
tituloMusculo.textContent = musculo.nombre.toUpperCase()
zonaMusculo.innerHTML = `
    <div>
        <img src=${musculo.urlImagen} />
        <table class="tabla">
            <thead>
                <tr>
                    <th>Frecuencia Recomendada</th>
                    <th>MIN - MAX Volumen Semanal</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${musculo.frecuenciaRecomendada}</td>
                    <td>${musculo.minVolumenSemanal} - ${musculo.maxVolumenSemanal}</td>
                </tr>
            </tbody>
        </table>
        <div class="botones-editar-borrar">
            <a class="btn btn-editar" href="formularioMusculo.html?id=${musculo.id}&accion=editar">Editar</a>
            <button class="btn btn-borrar" id="eliminarMusculo">Borrar</button>
        </div>
    </div>`

// Zona de los ejercicios
zonaEjercicios.innerHTML = `<h3>EJERCICIOS PRINCIPALES PARA TRABAJAR ${musculo.nombre.toUpperCase()}:</h3> <div id="contenedorEjercicios"></div>`

// Ubicación donde irán las tarjetas de los músculos
let listaEjercicios = document.createElement("ul");
let zonaEjerciciosPrincipales = document.getElementById("contenedorEjercicios")
zonaEjerciciosPrincipales.appendChild(listaEjercicios)

// No hay ejercicios donde el músculo sea el principal
if (ejercicios.length === 0) {
    listaEjercicios.innerHTML = `<a class="advertencia">⚠️<strong> Advertencia</strong><br>El músculo "${musculo.nombre}" aun no tiene asociado ningún ejercicio como músculo principal</a>`
    // Agrega los ejercicios como tarjeta
} else {
    // Añade tarjetas de ejercicios en lista
    for (let ejercicio of ejercicios) {
        listaEjercicios.innerHTML += `
        <li class="tarjeta-pequenia">
            <img src=${ejercicio.urlImagen}>
            <h2>${ejercicio.nombre}</h2>
            <a href="ejercicioDetalle.html?id=${ejercicio.id}" class="btn-ver-mas">Ver más</a>
        </li>
    `
    }
}

// Eliminar músculo
// Eliminar músculo de la db
document.getElementById('eliminarMusculo').addEventListener('click', async () => {
    // Pregunta al ususario por confirmación
    if (confirm(`¿Está seguro de que desea eliminar a ${musculo.nombre} de la base de datos?`)) {
        await eliminarMusculo(idMusculo)
        // Redirige a la web de musculos
        window.location.href = "musculos.html";
    }
});

