package peetbot;

import battlecode.common.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


/**
 * RobotPlayer is the class that describes your main robot strategy.
 * The run() method inside this class is like your main function: this is what we'll call once your robot
 * is created!
 */
public class RobotPlayer {
    /**
     * We will use this variable to count the number of turns this robot has been alive.
     * You can use static variables like this to save any information you want. Keep in mind that even though
     * these variables are static, in Battlecode they aren't actually shared between your robots.
     */
    static int turnCount = 0;


    

    

    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        //init our class
        Soldier soldier = null;
        Splasher splasher = null;
        Mopper mopper = null;
        DefenseTower defenseTower = null;
        PaintTower paintTower = null;
        MoneyTower moneyTower = null;

        switch (rc.getType()) {
            case SOLDIER: soldier = new Soldier(rc); break;
            case SPLASHER: splasher = new Splasher(rc); break;
            case MOPPER: mopper = new Mopper(rc); break;
            case LEVEL_ONE_DEFENSE_TOWER: defenseTower = new DefenseTower(rc); break;
            case LEVEL_ONE_MONEY_TOWER: moneyTower = new MoneyTower(rc); break;
            case LEVEL_ONE_PAINT_TOWER: paintTower = new PaintTower(rc); break;
            default: throw new GameActionException(null, "Trying to create a new RC we dont have the type set up for.");
        }

        while (true) {

            turnCount += 1; 

            try {
                switch (rc.getType()){
                    case SOLDIER: 
                        if (soldier != null) {
                            soldier.run();
                            break;
                        }
                        else rc.setIndicatorString("Tried calling soldier but it wasnt created yet."); break;
                    // case SPLASHER: splasher = new Splasher(rc); break;
                    // case MOPPER: mopper = new Mopper(rc); break;
                    // case LEVEL_ONE_DEFENSE_TOWER: defenseTower = new DefenseTower(rc); break;
                    case LEVEL_ONE_MONEY_TOWER: 
                        if (moneyTower != null) {
                            moneyTower.run();
                            break;
                        }
                        else rc.setIndicatorString("Tried calling moneyTower but it wasnt created yet."); break;
                    case LEVEL_ONE_PAINT_TOWER: 
                        if (paintTower != null) {
                            paintTower.run();
                            break;
                        }
                        else rc.setIndicatorString("Tried calling paintTower but it wasnt created yet."); break;
                    default: throw new GameActionException(null,"Trying to run an controller we dont have set up.");
                }
            }
             catch (GameActionException e) {
                System.out.println("GameActionException");
                e.printStackTrace();

            } catch (Exception e) {
                System.out.println("Exception");
                e.printStackTrace();

            } finally {
                Clock.yield();
            }
        }
    }



    /**
     * Run a single turn for a Mopper.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    // public static void runMopper(RobotController rc) throws GameActionException{
    //     // Move and attack randomly.
    //     Direction dir = directions[rng.nextInt(directions.length)];
    //     MapLocation nextLoc = rc.getLocation().add(dir);
    //     if (rc.canMove(dir)){
    //         rc.move(dir);
    //     }
    //     if (rc.canMopSwing(dir)){
    //         rc.mopSwing(dir);
    //         System.out.println("Mop Swing! Booyah!");
    //     }
    //     else if (rc.canAttack(nextLoc)){
    //         rc.attack(nextLoc);
    //     }
    //     // We can also move our code into different methods or classes to better organize it!
    //     updateEnemyRobots(rc);
    // }

    // public static void updateEnemyRobots(RobotController rc) throws GameActionException{
    //     // Sensing methods can be passed in a radius of -1 to automatically 
    //     // use the largest possible value.
    //     RobotInfo[] enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
    //     if (enemyRobots.length != 0){
    //         rc.setIndicatorString("There are nearby enemy robots! Scary!");
    //         // Save an array of locations with enemy robots in them for possible future use.
    //         MapLocation[] enemyLocations = new MapLocation[enemyRobots.length];
    //         for (int i = 0; i < enemyRobots.length; i++){
    //             enemyLocations[i] = enemyRobots[i].getLocation();
    //         }
    //         RobotInfo[] allyRobots = rc.senseNearbyRobots(-1, rc.getTeam());
    //         // Occasionally try to tell nearby allies how many enemy robots we see.
    //         if (rc.getRoundNum() % 20 == 0){
    //             for (RobotInfo ally : allyRobots){
    //                 if (rc.canSendMessage(ally.location, enemyRobots.length)){
    //                     rc.sendMessage(ally.location, enemyRobots.length);
    //                 }
    //             }
    //         }
    //     }
    // }
}
