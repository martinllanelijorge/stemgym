import { eliminarFetch, hacerFetch } from '../utils/apiUtils.js'

// Zona donde se pondrán los datos del cliente
const zonaDatosCliente = document.getElementById("zonaCliente")
const zonaListaRutinas = document.getElementById("listaRutinas")
const zonaNuevaRutina = document.getElementById("zonaNuevaRutina")

// Obtención de la id del cliente
const parametros = new URLSearchParams(window.location.search)
const idCliente = parametros.get('id')

// Uso de fetch para obtención de los datos del cliente
const cliente = await hacerFetch(`GET`, `/clientes/${idCliente}`)
const listaRutinas = await hacerFetch(`GET`, `/clientes/${idCliente}/rutinas`)

// =============== FUNCIONES ================= //
// Función para decorar en forma de emoticono si se cumplió el objetivo
function visualizarObjetivoCumplido(objetivoCumplido) {
    let visualizacionObejtivo = "❌"
    if (objetivoCumplido) {
        visualizacionObejtivo = "✅"
    }
    return visualizacionObejtivo
}
// Función para eliminar un cliente de la bbdd
async function eliminarCliente(id) {
    await eliminarFetch(`/clientes/${id}`)
}

// ============== MAIN ============= //
// Zona de datos del cliente - No incluye rutina
zonaDatosCliente.innerHTML = `
    <img src=${cliente.urlImagen}/>
    <div>
        <h2>${cliente.nombre}</h2>
        <p><strong>Edad: </strong>${cliente.edad} años</p>
        <p><strong>Género: </strong>${cliente.genero}</p>
        <p><strong>Peso actual: </strong>${cliente.pesoActual} kg</p>
        <p><strong>Objetivo: </strong>${cliente.pesoObjetivo} kg</p>
        <p><strong>Objetivo alcanzado: </strong>${visualizarObjetivoCumplido(cliente.objetivoAlcanzado)}</p>
        <div class="botones-editar-borrar">
            <a class="btn btn-editar" href="formularioCliente.html?id=${cliente.id}&accion=editar">Editar</a>
            <button class="btn btn-borrar" id="eliminarCliente">Borrar</button>
        </div>
    </div>`

// Acceso a creación de una nueva rutina
zonaNuevaRutina.innerHTML = `<a class="btn-verde" href="formularioRutina.html?idCliente=${cliente.id}&accion=crear">Crear nueva rutina</a>`

// HAY RUTINA DEL CLIENTE
if (listaRutinas.length > 0) {
    for (let rutina of listaRutinas) {
        zonaListaRutinas.innerHTML += `
    <li class="rutina-en-lista">
        <p>${rutina.nombre}</p>
        <a href="rutinaDetalle.html?id=${rutina.id}" class="btn-ver-mas">Ver más</a>
    </li>`
    }
    // NO HAY RUTINA DEL CLIENTE - MENSAJE DE ADVERTENCIA
} else {
    zonaListaRutinas.innerHTML = `<li class="advertencia">⚠️<strong> Advertencia</strong><br>El cliente aun no tiene rutinas</li>`
}

// Eliminar cliente de la db
document.getElementById('eliminarCliente').addEventListener('click', async () => {
    // Pregunta al ususario por confirmación
    if (confirm(`¿Está seguro de que desea eliminar a ${cliente.nombre} de la base de datos?`)) {
        await eliminarCliente(idCliente)
        // Redirige a la web de clientes        
        window.location.href = "clientes.html?clienteEliminado=true";
    }
});

