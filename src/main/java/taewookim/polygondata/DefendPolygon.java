package taewookim.polygondata;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import taewookim.ItemDisplayPlugin;
import taewookim.collisiondetector.PolygonCollisionDetector;
import util.PolygonDetectorBuilder;
import util.TriangleMath;

import java.util.Random;

public class DefendPolygon {

    private final static double[] polygon;

    static {
        double sqrt10 = 1d/Math.sqrt(10);
        polygon = new double[]{
                -TriangleMath.cos(315), -TriangleMath.cos(315), 0.2f,
                sqrt10, 3*sqrt10, 0.2f,
                3*sqrt10, sqrt10, 0.2f
        };
    }

    public static PolygonCollisionDetector getPolygon(Player p) {
        PolygonDetectorBuilder builder = new PolygonDetectorBuilder();
        Location ploc = p.getLocation();
        double yaw = ploc.getYaw();
        double pitch = ploc.getPitch();
        double roll = new Random().nextDouble()*360;
        Vector loc = ploc.add(0, 1.5, 0).toVector();
        for(int i = 0; i<6; i++) {
            int a = i*3;
            builder.addPoint(AttackPolygon.rotate(polygon[a], polygon[a+1], polygon[a+2], yaw, pitch, roll).add(loc));
        }
        return builder.build();
    }

}
