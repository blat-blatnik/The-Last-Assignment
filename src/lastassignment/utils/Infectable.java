package lastassignment.utils;

/**
 * @author Jana
 * @version 1.0
 *
 * Infectables are susceptible to the virus, they can contract it and spread it around.
 */
public interface Infectable {

    /**
     * Should determine whether the Infectable is currently infected.
     * @return true iff infected.
     */
    boolean isInfected();

    /**
     * Should infect this Infectable.
     */
    void getInfected();
}
