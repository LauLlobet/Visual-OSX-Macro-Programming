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
        //hashset = new HashSet<String>();
        // prevent external instantiation of a singleton.

    }

    public static void reserveColisions() {
        if(colisions == null){
            colisions = new boolean[size][size];
        }
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
        doZero(next);
        return next;
    }

    public static void doColideTargetWithSource(int target, int source){
        colisions[target][source] = true;
        colisionsHash[target].add(source);
        colisionsHash[target].addAll(colisionsHash[source]);
        doOr(target,source);
    }

    private static void doOr(int source, int target) {
        for(int i=0;i< source;i++){
            colisions[target][i] = colisions[target][i] || colisions[source][i];
        }
    }

    private static void doZero(int cluster) {
        for(int i=0;i< size;i++){
            colisions[cluster][i] = false;
        }
        colisionsHash[cluster].clear();
    }

    public static boolean areColided(int a,int newc){
            return colisionsHash[a].contains(newc);
    }
}