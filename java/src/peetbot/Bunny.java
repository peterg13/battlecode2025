package peetbot;

import java.util.Random;
import battlecode.common.*;

public class Bunny {

    RobotController rc;
    
    static final Random rng = new Random(1337);

    static final Direction[] directions = {
        Direction.NORTH,
        Direction.NORTHEAST,
        Direction.EAST,
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTHWEST,
        Direction.WEST,
        Direction.NORTHWEST,
    };

    Bunny(RobotController rc) {
        this.rc = rc;
    }
    
}
