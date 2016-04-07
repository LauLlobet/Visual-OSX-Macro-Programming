package logic.features.util;


import java.util.Arrays;

public class ClusterColider {
    private static ClusterColider instance = new ClusterColider();

    private static boolean[][] colisions;

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
    }

    public static boolean areColided(int a,int b){
            try{
                return colisions[a][b] || colisions[b][a];
            }catch(Exception e){
                e.printStackTrace();
            }
        return false;
    }
}