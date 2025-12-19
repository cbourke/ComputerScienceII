package unl.soc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for {@link FloodUtils#floodCapacity floodCapacity}
 *
 */
public class FloodCapacityTests {

    @Test
    void testFloodCapacityEmptyGrid() {
        double[][] elevations = new double[0][0];
        double waterLevel = 10.0;
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertEquals(0.0, result, "The flood capacity of an empty grid should be 0.");
    }

    @Test
    void testFloodCapacitySingleValueAboveWaterLevel() {
        double[][] elevations = {{15.0}};
        double waterLevel = 10.0;
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertEquals(0.0, result, "If the elevation is above the water level, there should be no flood capacity.");
    }

    @Test
    void testFloodCapacitySingleValueBelowWaterLevel() {
        double[][] elevations = {{5.0}};
        double waterLevel = 10.0;
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertEquals(5.0, result, "The flood capacity should be the difference between the water level and elevation.");
    }

    @Test
    void testFloodCapacity2x2GridAllBelowWaterLevel() {
        double[][] elevations = {{2.0, 3.0}, {4.0, 5.0}};
        double waterLevel = 10.0;
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertEquals(26.0, result, 0.00001, "Flood capacity of the grid should be correctly calculated.");
    }

    @Test
    void testFloodCapacity3x3GridWithVaryingElevations() {
        double[][] elevations = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
        double waterLevel = 6.0;
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertEquals(15.0, result, 0.00001, "Flood capacity should correctly sum the differences where elevations are below the water level.");
    }

    @Test
    void testFloodCapacityGridWithNegativeElevations() {
        double[][] elevations = {{-1.0, -2.0}, {-3.0, -4.0}};
        double waterLevel = 0.0;
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertEquals(10.0, result, 0.00001, "Flood capacity should correctly handle negative elevations.");
    }

    @Test
    void testFloodCapacityGridWithMixedElevations() {
        double[][] elevations = {{1.0, 5.0}, {8.0, 10.0}};
        double waterLevel = 7.0;
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertEquals(8.0, result, 0.00001, "Flood capacity should only account for elevations below the water level.");
    }

    @Test
    void testFloodCapacityGridWithZeroElevations() {
        double[][] elevations = {{0.0, 0.0}, {0.0, 0.0}};
        double waterLevel = 5.0;
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertEquals(20.0, result, 0.00001, "Flood capacity of a grid with zero elevations should be correct.");
    }

    @Test
    void testFloodCapacityGridAllAboveWaterLevel() {
        double[][] elevations = {{15.0, 20.0}, {25.0, 30.0}};
        double waterLevel = 10.0;
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertEquals(0.0, result, "If all elevations are above the water level, there should be no flood capacity.");
    }

    @Test
    void testFloodCapacityLargeGrid() {
        double[][] elevations = new double[100][100];
        double waterLevel = 10.0;
        // Fill the grid with random values
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                elevations[i][j] = Math.random() * 20;  // Random elevation between 0 and 20
            }
        }
        double result = FloodUtils.floodCapacity(elevations, waterLevel);
        assertTrue(result >= 0, "Flood capacity for a large grid should be non-negative.");
    }

    //TODO: Add two test cases of your own

}
