import { hacerFetch } from '../utils/apiUtils.js'

// Zona donde se pondrán los datos del cliente
const zonaDatosCliente = document.getElementById("zonaCliente")
const zonaListaRutinas = document.getElementById("listaRutinas")

// Obtención de la id del cliente
const parametros = new URLSearchParams(window.location.search)
const idCliente = parametros.get('id')

// Uso de fetch para obtención de los datos del cliente
const cliente = await hacerFetch(`GET`, `/clientes/${idCliente}`)
const listaRutinas = await hacerFetch(`GET`, `/clientes/${idCliente}`)

// Función para decorar en forma de emoticono si se cumplió el objetivo
function visualizarObjetivoCumplido(objetivoCumplido){
    let visualizacionObejtivo = "❌"
    if (objetivoCumplido) {
        visualizacionObejtivo = "✅"
    }
    return visualizacionObejtivo
}

// Zona de datos del cliente - No incluye rutina
zonaDatosCliente.innerHTML = `
    <img src=${cliente.urlImagen}/>
    <div>
        <h2>${cliente.nombre}</h2>
        <p><strong>Edad: </strong>${cliente.edad} años</p>
        <p><strong>Género: </strong>${cliente.genero}</p>
        <p><strong>Peso actual: </strong>${cliente.pesoActual} kg</p>
        <p><strong>Objetivo: </strong>${cliente.pesoObjetivo} kg</p>
        <p><strong>Objetivo alcanzado: </strong>${visualizarObjetivoCumplido(cliente.pesoObjetivo)}</p>
    </div>`

// Rutina del cliente
for (let rutina of listaRutinas) {
    zonaListaRutinas.innerHTML += `<li>${rutina.nombre}</li>`
}

