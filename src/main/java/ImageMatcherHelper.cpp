#include <jni.h>
#include <stdio.h>

double* calculate(int* obs,int ow, int oh, int* mod, int mw, int mh){
   double bestX = -99;
   double bestY = -99;

    for (int x = 0; x < ow; x++) {
        for (int y = 0; y < oh; y++) {
            if(x == 1340 && y == 682){
            }
            //float index = Borram.this.compareOwnToModelAtPoint(x, y, model, catchedFirstColor);
            if (1 > 0) {
                bestX = x;
                bestY = y;
            }
        }
    }

    int argb = obs[0];
    int r = (argb)&0xFF;
    int g = (argb>>8)&0xFF;
    int b = (argb>>16)&0xFF;
    int a = (argb>>24)&0xFF;
    printf("---> r %d g %d b %d as %d\n",r,g,b,a);
    printf("---> ow %d oh %d mw %d mh %d\n",ow,oh,mw,mh);


    static double xy[2] ;//= new int[2];
    xy[0] = bestX;
    xy[1] = bestY;

    return xy;
}