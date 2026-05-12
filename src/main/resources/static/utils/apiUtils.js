import { API_CONFIG } from '../config/apiConfig.js';

/**
 * Función para hacer cualquier tipo de fetch sobre la api de stemgym
 * @param {('GET'|'POST'|'PUT')} metodo - Método http de la petición
 * @param {string} ruta - Ruta del endpoint
 * @param {Object|null} datos 
 * @returns devuelve la petición realizada
 */
export async function hacerFetch(metodo = 'GET', ruta, datos = null) {
    let url = `${API_CONFIG.apiUrl}${ruta}`;
    let respuesta = {};

    // Si es POST O PUT
    if (metodo === 'POST' || metodo === 'PUT') {
        respuesta = await fetch(url, {
            method: metodo,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        // Es GET
    } else {
        respuesta = await fetch(url, { method: metodo });
    }

    return await respuesta.json();
}

/**
 * Función para eliminar un objeto de la api
 * @param {string} url ruta del objeto que se va a eliminar
 */
export async function eliminarFetch(url) {
    await fetch(`${API_CONFIG.apiUrl}${url}`, {
        method: 'DELETE'
    });
}