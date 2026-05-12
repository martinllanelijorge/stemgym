import { eliminarFetch, hacerFetch } from '../utils/apiUtils.js'

// Zona donde se pondrán los datos del musculo
const zonaMusculo = document.getElementById("zonaMusculo")
const zonaEjercicios = document.getElementById("ejerciciosParticipa")

// Obtención de la id del músculo
const parametros = new URLSearchParams(window.location.search)
const idMusculo = parametros.get('id')

// Uso de fetch para obtención de los datos del músculo
const musculo = await hacerFetch(`GET`, `/musculos/${idMusculo}`)
const ejercicios = await hacerFetch(`GET`, `/musculos/${idMusculo}/ejercicios`)

// ============== MAIN ============= //
// Zona de datos del músculo 
zonaMusculo.innerHTML = `
    <h2>${musculo.nombre}</h2>
    <div>
        <img src=${musculo.urlImagen}/>
        <table>
            <thead>
                <tr>
                    <th>Frecuencia Recomendada</th>
                    <th>MIN - MAX Volumen Semanal</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${musculo.frecuenciaRecomendada}</td>
                    <td>${musculo.minVolumenSemanal} - ${maxVolumenSemanal}</td>
                </tr>
            </tbody>
        </table>
    </div>`
