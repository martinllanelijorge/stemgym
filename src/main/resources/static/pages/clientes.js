import { hacerFetch } from '../utils/apiUtils.js'

// Lugar donde se va a almacenar la lista
const zonaListaClientes = document.getElementById("listaClientes")
// Lista de clientes de la api
const clientes = await hacerFetch("GET", "/clientes")
// Buscador de clientes
const buscadorCliente = document.getElementById("buscador")

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

// Añade los clientes en lista al cargar la página
if (clientes.length === 0) {
    zonaListaClientes.innerHTML = `<li class="advertencia">⚠️<strong> Advertencia</strong><br>No se han encontrado clientes</li>`
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

