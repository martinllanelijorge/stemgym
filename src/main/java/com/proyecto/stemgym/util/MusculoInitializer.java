package com.proyecto.stemgym.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proyecto.stemgym.controller.MusculoController;
import com.proyecto.stemgym.entity.Musculo;

import jakarta.transaction.Transactional;

/**
 * Clase donde tiene lugar la inicialización de los músculos base que aparecerán
 * en la API
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Component
public class MusculoInitializer {

    @Autowired
    private MusculoController musculoController;

    /**
     * Método para inicializar los músculos de la API
     * <p>
     * Este método hace que la API comience con una serie de músculos predefinidos
     * </p>
     * 
     * @see Musculo
     * @sice 1.0
     */
    @Transactional
    public void inicializarMusculos() {

        // Creación de los músculos iniciales de la DB
        Musculo pectorales = new Musculo("Pectorales", 2, 12, 20,
                "https://m.media-amazon.com/images/I/61WNnhYl2CL._AC_UF894,1000_QL80_.jpg");
        Musculo dorsales = new Musculo("Dorsales", 2, 14, 22,
                "https://www.labolsadelcorredor.com/wp-content/uploads/2020/03/dorsales-e1584814066729.jpg");
        Musculo trapecio = new Musculo("Trapecio", 2, 14, 22,
                "https://www.ericfavre.com/lifestyle/es/wp-content/uploads/sites/8/2021/09/trapeze.jpg");
        Musculo deltoides = new Musculo("Deltoides", 2, 12, 20,
                "https://previews.123rf.com/images/woodoo007/woodoo0071510/woodoo007151000245/47041842-deltoid-muscles-anatomy-map.jpg");
        Musculo triceps = new Musculo("Tríceps", 2, 10, 14,
                "https://www.cambiatufisico.com/wp-content/uploads/anatomia-triceps.jpg");
        Musculo biceps = new Musculo("Bíceps", 2, 14, 20,
                "https://www.rehabmypatient.com/media/uploads/articles/Biceps_Brachii_Tendinopathy.jpg");
        Musculo cuadriceps = new Musculo("Cuádriceps", 2, 12, 18,
                "https://www.escuelaculturismonatural.com/wp-content/uploads/elementor/thumbs/Quadriceps-muscles-e1660481667161-pt94479g84lgtxwid7byvpxbpswzq758ltel76ee9k.png");
        Musculo isquios = new Musculo("Isquios", 2, 12, 16,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa2WcLNTEgJpnOPOzAl2U474BrRIpEm1AkJA&s");
        Musculo gluteos = new Musculo("Gluteos", 2, 12, 16,
                "https://somasalud.es/wp-content/uploads/2019/03/musculo-gluteo.jpg");
        Musculo abdomen = new Musculo("Abdomen", 3, 12, 16,
                "https://edpmadrid.com/wp-content/uploads/2021/08/334-h2.jpg");

        // Guardado de los musculos en la DB
        musculoController.crearNuevoMusculo(pectorales);
        musculoController.crearNuevoMusculo(dorsales);
        musculoController.crearNuevoMusculo(trapecio);
        musculoController.crearNuevoMusculo(deltoides);
        musculoController.crearNuevoMusculo(triceps);
        musculoController.crearNuevoMusculo(biceps);
        musculoController.crearNuevoMusculo(cuadriceps);
        musculoController.crearNuevoMusculo(isquios);
        musculoController.crearNuevoMusculo(gluteos);
        musculoController.crearNuevoMusculo(abdomen);
    }

}
