import { hacerFetch } from '../utils/apiUtils.js'

// CONTENEDORES DOM
const main = document.querySelector("main")
const contenedorClientes = document.getElementById("contenedorLista")
const zonaListaClientes = document.getElementById("listaClientes")
const buscadorCliente = document.getElementById("buscador")

// Lista de clientes de la api
const clientes = await hacerFetch("GET", "/clientes")

// Obtención del parametro de cliente eliminado
const parametros = new URLSearchParams(window.location.search)
const clienteEliminado = parametros.get('clienteEliminado')

// Construcción de la lista en el html
zonaListaClientes.innerHTML = ""

// Función para compara el cliente con lo buscado en el buscador
function mostrarPorNombreBuscador(textoBusqueda, elementos) {
    for (let elemento of document.querySelectorAll(elementos)) {
        // Se extrae el texto de cada fila y se pone en minúscula para comparar
        let nombre = elemento.querySelector("p")
        nombre = nombre.textContent.toLowerCase()

        // EL NOMBRE COMIENZA POR LO ESCRITO EN EL BUSCADOR - SE MUESTRA
        if (nombre.startsWith(textoBusqueda)) {
            elemento.style.display = ""
        // EL NOMBRE NO COMIENZA POR LO ESCRITO - NO SE MUESTRA
        } else {
            elemento.style.display = "none"
        }
    }
}
// Crea un mensaje de éxito al borrar el cliente
function aniadirMensajeClienteEliminado(){
    const mensajeEliminado = document.createElement('p')
    main.insertBefore(mensajeEliminado, contenedorClientes)
    mensajeEliminado.innerHTML = `<p class="exito">✅<strong> ÉXITO</strong><br>El cliente se eliminó con éxito</p>`
}
// ============== MAIN ================ //

// Pone mensaje de cliente eliminado si viene de eliminar un cliente
if (clienteEliminado) {
    aniadirMensajeClienteEliminado()
    // Quita el parámetro de cliente eliminado
    history.replaceState(null, '', window.location.pathname)
}

// Añade los clientes en lista al cargar la página
if (clientes.length === 0) {
    zonaListaClientes.innerHTML += `<li class="advertencia">⚠️<strong> Advertencia</strong><br>No se han encontrado clientes</li>`
} else {
    for (let cliente of clientes) {
        zonaListaClientes.innerHTML += `
        <li>
            <p>${cliente.nombre}</p>
            <a href="clienteDetalle.html?id=${cliente.id}" class="btn-ver-mas">Ver más</a>
        </li>
    `
    }
}

// Acción del buscador por nombre
buscadorCliente.addEventListener("input", function () {
    const busquedaActual = this.value.toLowerCase();
    mostrarPorNombreBuscador(busquedaActual, "#listaClientes li")
});

