package portsim.cargo;

/**
 * This enum describes the possible types BulkCargo can take.
 */
public enum BulkCargoType {
    /** A small hard seed. Harvested for human or animal consumption. */
    GRAIN,

    /** A solid compound, usually ores such as iron ore. */
    MINERALS,

    /** A combustible black or brownish-black used as a fuel source. */
    COAL,

    /** A viscous liquid derived from petroleum, especially for use as a fuel or lubricant. */
    OIL,

    /** Another form of bulk cargo that does not fit into other categories. */
    OTHER
}
