#include <jni.h>
#include <stdio.h>
#include "logic_imagematching_features_pixelbypixel_ImageMatcher.h"
#include "ImageMatcherHelper.h"

// Implementation of native method sayHello() of HelloJNI class

JNIEXPORT jdoubleArray JNICALL Java_logic_imagematching_features_pixelbypixel_ImageMatcher_searchImageInImage
  (JNIEnv *env, jobject, jintArray obs, jint ow, jint oh, jintArray mod, jint mw, jint mh){


jint *obsInt = (jint *)env->GetIntArrayElements(obs, NULL);
jint *modInt = (jint *)env->GetIntArrayElements(mod, NULL);

calculate(obsInt,ow,oh,modInt,mw,mh);

printf("result...\n");
env->ReleaseIntArrayElements(obs, obsInt, 0 );
/**/

    return NULL;

}