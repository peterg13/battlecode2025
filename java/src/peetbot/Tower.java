package peetbot;

import java.util.Random;
import battlecode.common.*;

public class Tower {
    
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

    Tower(RobotController rc) {
        this.rc = rc;
    }


    public void run() throws GameActionException{
        Direction dir = directions[rng.nextInt(directions.length)];
        MapLocation nextLoc = this.rc.getLocation().add(dir);

        if (this.rc.canBuildRobot(UnitType.SOLDIER, nextLoc)){
            this.rc.buildRobot(UnitType.SOLDIER, nextLoc);
            System.out.println("BUILT A SOLDIER (new version)");
        }
    }

}
