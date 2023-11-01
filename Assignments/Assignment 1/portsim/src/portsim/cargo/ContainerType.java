package portsim.cargo;

/**
 * This enum describes the possible types Containers can take.
 */
public enum ContainerType {
    /**
     * Open-top shipping containers have the goods are loaded in through the top by crane or other
     * top loading machinery.
    */
    OPEN_TOP,

    /** Another form of shipping container that does not fit into other categories. */
    OTHER,

    /**
     * Reefer containers are big fridges that are used to transport temperature controlled cargoes
     * such as fruits, meat, fish, seafood.
    */
    REEFER,

    /**
     * A large standardized shipping container, designed and built for intermodal freight transport.
    */
    STANDARD,

    /** Tank containers can be used for food grade liquid storage and transport. */
    TANKER
}
