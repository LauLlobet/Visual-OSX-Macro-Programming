#include <jni.h>
#include <stdio.h>
#include <ImageMatcherHelper.h>


unsigned char* as_unsigned_char_array(jbyteArray array,JNIEnv *env) {
    int len = env->GetArrayLength (array);
    unsigned char* buf = new unsigned char[len];
    env->GetByteArrayRegion (array, 0, len, reinterpret_cast<jbyte*>(buf));
    return buf;
}