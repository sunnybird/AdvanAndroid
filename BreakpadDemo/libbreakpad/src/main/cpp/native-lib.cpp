#include <jni.h>
#include <string>
#include <android/log.h>

#include "client/linux/handler/exception_handler.h"
#include "client/linux/handler/minidump_descriptor.h"

#define TAG "breakpad"

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)

 bool DumpCallback(const google_breakpad::MinidumpDescriptor& descriptor,
                  void* context, bool succeeded) {
	LOGD("Dump path: %s\n", descriptor.path());
	return succeeded;
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_libbreakpad_BreakPadManager_initBrakPad(JNIEnv *env, jobject instance, jstring path_) {
	const char *path = env->GetStringUTFChars(path_, 0);
	
	google_breakpad::MinidumpDescriptor descriptor(path);
	static google_breakpad::ExceptionHandler eh(descriptor, NULL, DumpCallback, NULL, true, -1);
	
	env->ReleaseStringUTFChars(path_, path);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_libbreakpad_BreakPadManager_testBreak(JNIEnv *env, jobject instance) {
	volatile int* a = reinterpret_cast<volatile int*>(NULL);
	*a = 1;
}


