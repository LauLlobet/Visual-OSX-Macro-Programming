package logic.features.util;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClusterColider {
    private static ClusterColider instance = new ClusterColider();

    private static boolean[][] colisions;
    private static HashSet<Integer>[] colisionsHash;

    private static int next = 0;
    static int size = 10000;

    private  ClusterColider() {
        size = 10000;
        reserveColisions();
    }

    public static void reserveColisions() {
        if(colisionsHash == null){
            colisionsHash = new HashSet[size];
            for(int i=0;i<size;i++){
                colisionsHash[i] = new HashSet<Integer>();
            }
        }
        next = 0;
    }

    public static ClusterColider getInstance() {
        if(instance == null){
            instance = new ClusterColider();
        }
        return instance;
    }

    public static int getNextSequence() {
        next++;
        doClear(next);
        return next;
    }

    public static void doColideTargetWithSource(int target, int source){
        colisionsHash[target].add(source);
        colisionsHash[target].addAll(colisionsHash[source]);
    }

    private static void doClear(int cluster) {
        colisionsHash[cluster].clear();
    }

    public static boolean areColided(int a,int newc){
            return colisionsHash[a].contains(newc);
    }
}