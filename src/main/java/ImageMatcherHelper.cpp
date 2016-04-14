#include <jni.h>
#include <stdio.h>

double* calculate(int *obs,int ow, int oh, int *mod, int mw, int mh){
       double bestX = -1;
       double bestY = -1;
       /*
        for (int x = 0; x < Borram.this.getWidth(); x++) {
            for (int y = 0; y < Borram.this.getHeight(); y++) {
                int catchedFirstColor = rgbData[(y*this.getWidth())+x];
                for( int i=0; i<models.length; i++){
                    Borram model = models[i];

                    if(x == 1340 && y == 682){
                            int qqqq = 0;
                    }

                    float index = Borram.this.compareOwnToModelAtPoint(x, y, model, catchedFirstColor);
                    if (index > 0) {
                        model.maxMatchingPixels = 1;
                        model.finalX = x;
                        model.finalY = y;
                    }
                }
            }
        }

        for(Borram modelto0: models){
            if(modelto0.maxMatchingPixels == 0){
                modelto0.finalX = -1;
                modelto0.finalY = -1;
            }
        }


*/


    int argb = obs[0];
    int r = (argb)&0xFF;
    int g = (argb>>8)&0xFF;
    int b = (argb>>16)&0xFF;
    int a = (argb>>24)&0xFF;

    printf("---> r %d g %d b %d as %d\n",r,g,b,a);
    printf("---> ow %d oh %d mw %d mh %d\n",ow,oh,mw,mh);

    return NULL;
}