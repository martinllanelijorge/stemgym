import { hacerFetch } from '../utils/apiUtils.js';

// Lugar donde se va a almacenar la lista
const zonaListaClientes = document.getElementById("listaClientes")
// Lista de clientes de la api
const clientes = await hacerFetch("GET", "/clientes")

// Construcción de la lista en el html
zonaListaClientes.innerHTML = ""

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


