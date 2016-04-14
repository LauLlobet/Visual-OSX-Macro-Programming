#include <stdio.h>
#include "jni_JniSample.h"

JNIEXPORT jint JNICALL Java_jni_JniSample_sayHello (JNIEnv *env, jobject obj) {
  printf("Hello World\n");
  return 0;
}
