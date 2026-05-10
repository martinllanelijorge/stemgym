import { hacerFetch } from '../utils/apiUtils.js';

// Lugar donde se va a almacenar la lista
const zonaListaClientes = document.getElementById("listaClientes")
// Lista de clientes de la api
const clientes = await hacerFetch("GET", "/clientes")
// Buscador de clientes
const buscadorCliente = document.getElementById("buscadorCliente")

// Construcción de la lista en el html
zonaListaClientes.innerHTML = ""


// Función para compara el cliente con lo buscado en el buscador
function mostrarClienteBuscador(textoBusqueda) {
    for (let fila of document.querySelectorAll("#listaClientes li")) {
        // Se extrae el texto de cada fila y se pone en minúscula para comparar
        let nombre = fila.querySelector("span")
        nombre = nombre.textContent.toLowerCase()

        // EL NOMBRE COMIENZA POR LO ESCRITO EN EL BUSCADOR - SE MUESTRA
        if (nombre.startsWith(textoBusqueda)) {
            fila.style.display = ""
        // EL NOMBRE NO COMIENZA POR LO ESCRITO - NO SE MUESTRA
        } else {
            fila.style.display = "none"
        }
    }
}

// Añade los clientes en lista al cargar la página
if (clientes.length === 0) {
    zonaListaClientes.innerHTML = '<li class="mensaje-error-api">No se han encontrado clientes</li>'
} else {
    for (let cliente of clientes) {
        zonaListaClientes.innerHTML += `
        <li>
            <span>${cliente.nombre}</span>
            <a href="cliente.html?id=${cliente.id}" class="btn-ver-mas">Ver más</a>
        </li>
    `
    }
}

// Acción del buscador por nombre
buscadorCliente.addEventListener("input", function () {
    const busquedaActual = this.value.toLowerCase();
    mostrarClienteBuscador(busquedaActual)
});

