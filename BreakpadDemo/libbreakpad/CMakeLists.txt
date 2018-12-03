# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(ENABLE_INPROCESS ON)
set(ENABLE_OUTOFPROCESS ON)
set(ENABLE_LIBCORKSCREW ON)
set(ENABLE_LIBUNWIND ON)
set(ENABLE_LIBUNWINDSTACK ON)
set(ENABLE_CXXABI ON)
set(ENABLE_STACKSCAN ON)

if (${ENABLE_INPROCESS})
    add_definitions(-DENABLE_INPROCESS)
endif ()
if (${ENABLE_OUTOFPROCESS})
    add_definitions(-DENABLE_OUTOFPROCESS)
endif ()

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Werror=implicit-function-declaration")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 ")

#head file path
INCLUDE_DIRECTORIES(
         src/main/cpp/breakpad
         src/main/cpp/breakpad/common/android/include
)


set(BREAKPAD_ROOT src/main/cpp/breakpad)

file(GLOB BREAKPAD_SOURCES_COMMON
        ${BREAKPAD_ROOT}/client/linux/crash_generation/crash_generation_client.cc
        ${BREAKPAD_ROOT}/client/linux/dump_writer_common/thread_info.cc
        ${BREAKPAD_ROOT}/client/linux/dump_writer_common/ucontext_reader.cc
        ${BREAKPAD_ROOT}/client/linux/handler/exception_handler.cc
        ${BREAKPAD_ROOT}/client/linux/handler/minidump_descriptor.cc
        ${BREAKPAD_ROOT}/client/linux/log/log.cc
        ${BREAKPAD_ROOT}/client/linux/microdump_writer/microdump_writer.cc
        ${BREAKPAD_ROOT}/client/linux/minidump_writer/linux_dumper.cc
        ${BREAKPAD_ROOT}/client/linux/minidump_writer/linux_ptrace_dumper.cc
        ${BREAKPAD_ROOT}/client/linux/minidump_writer/minidump_writer.cc
        ${BREAKPAD_ROOT}/client/minidump_file_writer.cc
        ${BREAKPAD_ROOT}/common/convert_UTF.c
        ${BREAKPAD_ROOT}/common/md5.cc
        ${BREAKPAD_ROOT}/common/string_conversion.cc
        ${BREAKPAD_ROOT}/common/linux/elfutils.cc
        ${BREAKPAD_ROOT}/common/linux/file_id.cc
        ${BREAKPAD_ROOT}/common/linux/guid_creator.cc
        ${BREAKPAD_ROOT}/common/linux/linux_libc_support.cc
        ${BREAKPAD_ROOT}/common/linux/memory_mapped_file.cc
        ${BREAKPAD_ROOT}/common/linux/safe_readlink.cc

        )

file(GLOB BREAKPAD_ASM_SOURCE ${BREAKPAD_ROOT}/common/android/breakpad_getcontext.S
        )

set_source_files_properties(${BREAKPAD_ASM_SOURCE} PROPERTIES LANGUAGE C)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        breakpad-native
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        src/main/cpp/native-lib.cpp
        ${BREAKPAD_SOURCES_COMMON}
        ${BREAKPAD_ASM_SOURCE}
        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        breakpad-native

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib}
        )