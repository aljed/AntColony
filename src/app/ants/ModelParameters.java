package app.ants;

import java.util.Random;

class ModelParameters
{
    static final double PHEROMONE_STRENGTH_CONSTANT = 1000.0;
    static final double PHEROMONE_EVAPORATION_RATE = 0.6;
    static int NUMBER_OF_ANTS = 9;
    static final int LONGEST_PATH_CUT_OFF_POINT = 30;
    static Random randomGenerator = new Random(); // 133742069
}
