package Clase6.Algoritmos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnvironmentSearch {
    public static void main(String[] args) {
        // Definir una lista de posibles ubicaciones con sus puntuaciones
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("UbicaciÃ³n A", 0.9));
        locations.add(new Location("UbicaciÃ³n B", 0.8));
        locations.add(new Location("UbicaciÃ³n C", 0.7));
        locations.add(new Location("UbicaciÃ³n D", 0.85));
        locations.add(new Location("UbicaciÃ³n E", 0.95));

        // ParÃ¡metros del algoritmo
        int iterations = 1000;
        double temperature = 1.0;
        double coolingRate = 0.003;

        // Estado inicial: una ubicaciÃ³n aleatoria
        Location currentLocation = locations.get(new Random().nextInt(locations.size()));

        // BÃºsqueda por estructura de entornos
        for (int i = 0; i < iterations; i++) {
            Location newLocation = locations.get(new Random().nextInt(locations.size()));

            // Calcular la diferencia de puntuaciÃ³n entre la ubicaciÃ³n actual y la nueva
            double deltaScore = newLocation.score - currentLocation.score;

            // Si la nueva ubicaciÃ³n es mejor o con una probabilidad, muÃ©vete
            if (deltaScore > 0 || Math.random() < Math.exp(deltaScore / temperature)) {
                currentLocation = newLocation;
            }

            // Enfriar la temperatura
            temperature *= (1.0 - coolingRate);
        }

        // Imprimir la ubicaciÃ³n final
        System.out.println("La mejor ubicaciÃ³n es: " + currentLocation.name);
        System.out.println("PuntuaciÃ³n: " + currentLocation.score);
    }
}
