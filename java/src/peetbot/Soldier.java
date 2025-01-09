package peetbot;

import battlecode.common.*;

public class Soldier extends Bunny {
    

    Soldier(RobotController rc) {
        super(rc);
    }

    public void run() throws GameActionException {
        // Sense information about all visible nearby tiles.
        MapInfo[] nearbyTiles = this.rc.senseNearbyMapInfos();
        // Search for a nearby ruin to complete.
        MapInfo curRuin = null;
        for (MapInfo tile : nearbyTiles){
            if (tile.hasRuin()){
                curRuin = tile;
            }
        }
        if (curRuin != null){
            MapLocation targetLoc = curRuin.getMapLocation();
            Direction dir = this.rc.getLocation().directionTo(targetLoc);
            if (this.rc.canMove(dir))
                this.rc.move(dir);
            // Mark the pattern we need to draw to build a tower here if we haven't already.
            MapLocation shouldBeMarked = curRuin.getMapLocation().subtract(dir);
            if (this.rc.senseMapInfo(shouldBeMarked).getMark() == PaintType.EMPTY && this.rc.canMarkTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc)){
                this.rc.markTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc);
                System.out.println("Trying to build a tower at " + targetLoc);
            }
            // Fill in any spots in the pattern with the appropriate paint.
            for (MapInfo patternTile : this.rc.senseNearbyMapInfos(targetLoc, 8)){
                if (patternTile.getMark() != patternTile.getPaint() && patternTile.getMark() != PaintType.EMPTY){
                    boolean useSecondaryColor = patternTile.getMark() == PaintType.ALLY_SECONDARY;
                    if (this.rc.canAttack(patternTile.getMapLocation()))
                        this.rc.attack(patternTile.getMapLocation(), useSecondaryColor);
                }
            }
            // Complete the ruin if we can.
            if (this.rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc)){
                this.rc.completeTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc);
                this.rc.setTimelineMarker("Tower built", 0, 255, 0);
                System.out.println("Built a tower at " + targetLoc + "!");
            }
        }

        // Move and attack randomly if no objective.
        Direction dir = directions[rng.nextInt(directions.length)];
        MapLocation nextLoc = this.rc.getLocation().add(dir);
        if (this.rc.canMove(dir)){
            this.rc.move(dir);
        }
        // Try to paint beneath us as we walk to avoid paint penalties.
        // Avoiding wasting paint by re-painting our own tiles.
        MapInfo currentTile = this.rc.senseMapInfo(this.rc.getLocation());
        if (!currentTile.getPaint().isAlly() && this.rc.canAttack(this.rc.getLocation())){
            this.rc.attack(this.rc.getLocation());
        }
    }

}
