import { eliminarFetch, hacerFetch } from '../utils/apiUtils.js'

// Zona donde se pondrán los datos de la rutina
const tituloRutina = document.getElementById("tituloRutina")
const frecuenciaRutina = document.getElementById("frecuenciaRutina")
const listaEjercicios = document.getElementById("listaEjercicios")
const main = document.querySelector("main")

// Obtención de la id de la rutina y parametros
const parametros = new URLSearchParams(window.location.search)
const idRutina = parametros.get('id')

// Lista de rutinas de la api
const rutina = await hacerFetch("GET", `/rutinas/${idRutina}`)

// ============== MAIN ================= //
// Colocación de los datos en cada parte del html
tituloRutina.textContent = rutina.nombre
frecuenciaRutina.textContent = `Cantidad de veces a repetir por semana: ${rutina.frecuenciaSemanal}`

// Se crea la zona de los botones y se añade antes de las listas de rutinas
const zonaBotonesEditarBorrar = document.createElement('div')
zonaBotonesEditarBorrar.innerHTML = `  
    <div class="botones-editar-borrar">
        <a class="btn btn-editar" href="formularioRutina.html?id=${rutina.id}&accion=editar">Editar</a>
        <button class="btn btn-borrar" id="eliminarEjercicio">Borrar</button>
    </div>`

main.insertBefore(zonaBotonesEditarBorrar, listaEjercicios)

// De cada ejercicio se crea una tarjeta
for (const ejercicio of rutina.ejercicios) {
    listaEjercicios.innerHTML += `
    <li class="tarjeta">
        <img src=${ejercicio.urlImagen}>
        <h2>${ejercicio.nombre}</h2>
        <a href="ejercicioDetalle.html?id=${ejercicio.id}&origen=rutina&rutinaId=${idRutina}" class="btn-ver-mas">Ver más</a>
     </li>`
    
}