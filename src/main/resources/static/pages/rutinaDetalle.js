import { eliminarFetch, hacerFetch } from '../utils/apiUtils.js'

// Zona donde se pondrán los datos de la rutina
const tituloRutina = document.getElementById("tituloRutina")
const frecuenciaRutina = document.getElementById("frecuenciaRutina")
const listaEjercicios = document.getElementById("listaEjercicios")
const main = document.querySelector("main")
const zonaBotonImprimir = document.querySelector(".contenedor-boton-imprimir")
const btnVolver = document.getElementById('volver')

// Obtención de la id de la rutina y parametros
const parametros = new URLSearchParams(window.location.search)
const idRutina = parametros.get('id')

// Lista de rutinas de la api
const rutina = await hacerFetch("GET", `/rutinas/${idRutina}`)

// Función para eliminar una rutina
async function eliminarRutina() {
    await eliminarFetch(`/rutinas/${idRutina}`)
}

// ============== MAIN ================= //
// Colocación de los datos en cada parte del html
tituloRutina.textContent = rutina.nombre
frecuenciaRutina.textContent = `Cantidad de veces a repetir por semana: ${rutina.frecuenciaSemanal}`

// Se crea la zona de los botones y se añade antes de las listas de rutinas
const zonaBotonesEditarBorrar = document.createElement('div')
zonaBotonesEditarBorrar.innerHTML = `  
    <div class="botones-editar-borrar">
        <a class="btn btn-editar" href="formularioRutina.html?idCliente=${rutina.cliente.id}&idRutina=${rutina.id}&accion=editar">Editar</a>
        <button class="btn btn-borrar" id="eliminarRutina">Borrar</button>
    </div>`

main.insertBefore(zonaBotonesEditarBorrar, listaEjercicios)

// Comprueba si tiene ejercicios la rutina, y si no lanza un mensaje de alerta
if (rutina.ejercicios.length < 1) {
    listaEjercicios.innerHTML = `<p class="advertencia"><strong>⚠️ Advertencia</strong><br>La rutina aun no tiene ejercicios</p>`
} else {
    // De cada ejercicio se crea una tarjeta
    for (const ejercicio of rutina.ejercicios) {
        listaEjercicios.innerHTML += `
    <li class="tarjeta">
        <img src=${ejercicio.urlImagen}>
        <h2>${ejercicio.nombre}</h2>
        <a href="ejercicioDetalle.html?id=${ejercicio.id}&origen=rutina&rutinaId=${idRutina}" class="btn-ver-mas">Ver más</a>
     </li>`
    }
}


// Acción del botón volver para volver al cliente detalle
btnVolver.href = `clienteDetalle.html?id=${rutina.cliente.id}`

// Creación para el botón imprimir rutina
const btnImprimir = document.createElement('button')
btnImprimir.className = "botonImprimir"
btnImprimir.textContent = "Imprimir Rutina"
zonaBotonImprimir.appendChild(btnImprimir)

// ======== ELIMINAR UNA RUTINA =============== //
document.getElementById('eliminarRutina').addEventListener('click', async () => {
    // Pregunta al ususario por confirmación
    if (confirm(`¿Está seguro de que desea eliminar la rutina ${rutina.nombre} de la base de datos?`)) {
        await eliminarRutina()
        // Redirige a la web de musculos
        window.location.href = `clienteDetalle.html?id=${rutina.cliente.id}`
    }
});

// ======== ACCIÓN DE IMPRIMIR =============== //
btnImprimir.addEventListener('click', () => {
    window.print()
});

