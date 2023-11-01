package portsim.port;

import portsim.cargo.Cargo;

import java.util.ArrayList;
import java.util.List;

/**
 * A place where ships can dock with Quays to load and unload their cargo.
 */
public class Port {
    /** The name of the port. */
    private final String name;

    /** An ArrayList of the Quays at the port. */
    private final ArrayList<Quay> quays;

    /** An ArrayList of the Cargo at the port. */
    private final ArrayList<Cargo> cargo;

    /**
     * Creates a new port with the given name.
     * The list of quays in the port and stored cargo should be initialised as empty lists.
     * @param name Name of the port.
     */
    public Port(String name) {
        this.name = name;

        this.quays = new ArrayList<>();
        this.cargo = new ArrayList<>();
    }

    /**
     * Returns the name of this port.
     * @return port's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of all quays associated with this port.
     * Adding or removing elements from the returned list should not affect the original list.
     * The order in which quays appear in this list should be the same as the order in which they
     * were added by calling addQuay(Quay).
     * @return all quays.
     */
    public List<Quay> getQuays() {
        return quays;
    }

    /**
     * Returns the cargo stored at this port.
     * Adding or removing elements from the returned list should not affect the original list.
     * @return port cargo.
     */
    public List<Cargo> getCargo() {
        return cargo;
    }

    /**
     * Adds a quay to the ports control.
     * @param quay the quay to add.
     */
    public void addQuay(Quay quay) {
        quays.add(quay);
    }
}
