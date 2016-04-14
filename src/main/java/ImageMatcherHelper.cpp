#include <jni.h>
#include <stdio.h>

double* calculate(int *obs,int *mod){

    int argb = obs[0];
    int r = (argb)&0xFF;
    int g = (argb>>8)&0xFF;
    int b = (argb>>16)&0xFF;
    int a = (argb>>24)&0xFF;

    printf("---> r %d g %d b %d as %d\n",r,g,b,a);

    return NULL;
}