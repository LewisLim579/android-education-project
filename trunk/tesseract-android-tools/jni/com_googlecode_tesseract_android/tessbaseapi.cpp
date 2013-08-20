/*
 * Copyright 2011, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <stdio.h>
#include <malloc.h>
#include "android/bitmap.h"
#include "common.h"
#include "baseapi.h"
#include "allheaders.h"

static jfieldID field_mNativeData;

struct native_data_t {
  tesseract::TessBaseAPI api;
  PIX *pix;
  void *data;
  bool debug;

  native_data_t() {
    pix = NULL;
    data = NULL;
    debug = false;
  }
};

static inline native_data_t * get_native_data(JNIEnv *env, jobject object) {
  return (native_data_t *) (env->GetIntField(object, field_mNativeData));
}

#ifdef __cplusplus
extern "C" {
#endif

jint JNI_OnLoad(JavaVM* vm, void* reserved) {
  JNIEnv *env;

  if (vm->GetEnv((void**) &env, JNI_VERSION_1_6) != JNI_OK) {
    LOGE("Failed to get the environment using GetEnv()");
    return -1;
  }

  return JNI_VERSION_1_6;
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeClassInit(JNIEnv* env, jclass clazz) {
  field_mNativeData = env->GetFieldID(clazz, "mNativeData", "I");
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeConstruct(JNIEnv* env, jobject object) {
  native_data_t *nat = new native_data_t;

  if (nat == NULL) {
    LOGE("%s: out of memory!", __FUNCTION__);
    return;
  }

  env->SetIntField(object, field_mNativeData, (jint) nat);
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeFinalize(JNIEnv* env, jobject object) {
  native_data_t *nat = get_native_data(env, object);

  // Since Tesseract doesn't take ownership of the memory, we keep a pointer in the native
  // code struct. We need to free that pointer when we release our instance of Tesseract or
  // attempt to set a new image using one of the nativeSet* methods.
  if (nat->data != NULL)
    free(nat->data);
  else if (nat->pix != NULL)
    pixDestroy(&nat->pix);
  nat->data = NULL;
  nat->pix = NULL;

  if (nat != NULL)
    delete nat;
}

jboolean Java_com_googlecode_tesseract_android_TessBaseAPI_nativeInit(JNIEnv *env, jobject thiz,
                                                                      jstring dir, jstring lang) {
  native_data_t *nat = get_native_data(env, thiz);

  const char *c_dir = env->GetStringUTFChars(dir, NULL);
  const char *c_lang = env->GetStringUTFChars(lang, NULL);

  jboolean res = JNI_TRUE;

  if (nat->api.Init(c_dir, c_lang)) {
    LOGE("Could not initialize Tesseract API with language=%s!", c_lang);
    res = JNI_FALSE;
  } else {
    LOGI("Initialized Tesseract API with language=%s", c_lang);
  }

  env->ReleaseStringUTFChars(lang, c_dir);
  env->ReleaseStringUTFChars(lang, c_lang);

  return res;
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeSetImageBytes(JNIEnv *env,
                                                                           jobject thiz,
                                                                           jbyteArray data,
                                                                           jint width, jint height,
                                                                           jint bpp, jint bpl) {
  jbyte *data_array = env->GetByteArrayElements(data, NULL);
  int count = env->GetArrayLength(data);
  unsigned char* imagedata = (unsigned char *) malloc(count * sizeof(unsigned char));

  // This is painfully slow, but necessary because we don't know
  // how many bits the JVM might be using to represent a byte
  for (int i = 0; i < count; i++) {
    imagedata[i] = (unsigned char) data_array[i];
  }

  env->ReleaseByteArrayElements(data, data_array, JNI_ABORT);

  native_data_t *nat = get_native_data(env, thiz);
  nat->api.SetImage(imagedata, (int) width, (int) height, (int) bpp, (int) bpl);

  // Since Tesseract doesn't take ownership of the memory, we keep a pointer in the native
  // code struct. We need to free that pointer when we release our instance of Tesseract or
  // attempt to set a new image using one of the nativeSet* methods.
  if (nat->data != NULL)
    free(nat->data);
  else if (nat->pix != NULL)
    pixDestroy(&nat->pix);
  nat->data = imagedata;
  nat->pix = NULL;
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeSetImagePix(JNIEnv *env, jobject thiz,
                                                                         jint nativePix) {
  PIX *pixs = (PIX *) nativePix;
  PIX *pixd = pixClone(pixs);

  native_data_t *nat = get_native_data(env, thiz);
  nat->api.SetImage(pixd);

  // Since Tesseract doesn't take ownership of the memory, we keep a pointer in the native
  // code struct. We need to free that pointer when we release our instance of Tesseract or
  // attempt to set a new image using one of the nativeSet* methods.
  if (nat->data != NULL)
    free(nat->data);
  else if (nat->pix != NULL)
    pixDestroy(&nat->pix);
  nat->data = NULL;
  nat->pix = pixd;
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeSetRectangle(JNIEnv *env,
                                                                          jobject thiz, jint left,
                                                                          jint top, jint width,
                                                                          jint height) {
  native_data_t *nat = get_native_data(env, thiz);

  nat->api.SetRectangle(left, top, width, height);
}

jstring Java_com_googlecode_tesseract_android_TessBaseAPI_nativeGetUTF8Text(JNIEnv *env,
                                                                            jobject thiz) {
  native_data_t *nat = get_native_data(env, thiz);
  char *text = nat->api.GetUTF8Text();
  jstring result = env->NewStringUTF(text);

  free(text);

  return result;
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeStop(JNIEnv *env, jobject thiz) {
  native_data_t *nat = get_native_data(env, thiz);

  // TODO How do we stop without a monitor?!
}

jint Java_com_googlecode_tesseract_android_TessBaseAPI_nativeMeanConfidence(JNIEnv *env,
                                                                            jobject thiz) {
  native_data_t *nat = get_native_data(env, thiz);

  return (jint) nat->api.MeanTextConf();
}

jintArray Java_com_googlecode_tesseract_android_TessBaseAPI_nativeWordConfidences(JNIEnv *env,
                                                                                  jobject thiz) {
  native_data_t *nat = get_native_data(env, thiz);

  int *confs = nat->api.AllWordConfidences();

  if (confs == NULL) {
    return NULL;
  }

  int len, *trav;
  for (len = 0, trav = confs; *trav != -1; trav++, len++)
    ;

  LOG_ASSERT((confs != NULL), "Confidence array has %d elements", len);

  jintArray ret = env->NewIntArray(len);

  LOG_ASSERT((ret != NULL), "Could not create Java confidence array!");

  env->SetIntArrayRegion(ret, 0, len, confs);

  delete[] confs;

  return ret;
}

jboolean Java_com_googlecode_tesseract_android_TessBaseAPI_nativeSetVariable(JNIEnv *env,
                                                                             jobject thiz,
                                                                             jstring var,
                                                                             jstring value) {
  native_data_t *nat = get_native_data(env, thiz);

  const char *c_var = env->GetStringUTFChars(var, NULL);
  const char *c_value = env->GetStringUTFChars(value, NULL);

  jboolean set = nat->api.SetVariable(c_var, c_value) ? JNI_TRUE : JNI_FALSE;

  env->ReleaseStringUTFChars(var, c_var);
  env->ReleaseStringUTFChars(value, c_value);

  return set;
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeClear(JNIEnv *env, jobject thiz) {
  native_data_t *nat = get_native_data(env, thiz);

  nat->api.Clear();

  // Call between pages or documents etc to free up memory and forget adaptive data.
  nat->api.ClearAdaptiveClassifier();

  // Since Tesseract doesn't take ownership of the memory, we keep a pointer in the native
  // code struct. We need to free that pointer when we release our instance of Tesseract or
  // attempt to set a new image using one of the nativeSet* methods.
  if (nat->data != NULL)
    free(nat->data);
  else if (nat->pix != NULL)
    pixDestroy(&nat->pix);
  nat->data = NULL;
  nat->pix = NULL;
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeEnd(JNIEnv *env, jobject thiz) {
  native_data_t *nat = get_native_data(env, thiz);

  nat->api.End();

  // Since Tesseract doesn't take ownership of the memory, we keep a pointer in the native
  // code struct. We need to free that pointer when we release our instance of Tesseract or
  // attempt to set a new image using one of the nativeSet* methods.
  if (nat->data != NULL)
    free(nat->data);
  else if (nat->pix != NULL)
    pixDestroy(&nat->pix);
  nat->data = NULL;
  nat->pix = NULL;
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeSetDebug(JNIEnv *env, jobject thiz,
                                                                      jboolean debug) {
  native_data_t *nat = get_native_data(env, thiz);

  nat->debug = (debug == JNI_TRUE) ? TRUE : FALSE;
}

void Java_com_googlecode_tesseract_android_TessBaseAPI_nativeSetPageSegMode(JNIEnv *env,
                                                                            jobject thiz, jint mode) {
  native_data_t *nat = get_native_data(env, thiz);

  nat->api.SetPageSegMode((tesseract::PageSegMode) mode);
}

#ifdef __cplusplus
}
#endif
