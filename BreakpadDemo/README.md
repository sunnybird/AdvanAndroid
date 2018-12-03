## BreakPad Demo
### NDK Crash 捕获
参照资料
https://github.com/AndroidAdvanceWithGeektime/Chapter01

#### BreakPad 生成的 dmp 文件分析
使用 PC 端 BreakPad 编译的工具把 dmp 文件转换成文本文件

```bash
/breakpad-master/src/processor/minidump_stackwalk 8cd8a6df-0136-434a-5ec8948b-6de7d263.dmp > crash.txt
```

crash.txt 文件开头如下：

```txt
Operating system: Android
                  0.0.0 Linux 3.18.56+ #1 SMP PREEMPT Thu Oct 12 18:30:37 PDT 2017 i686
CPU: x86
     GenuineIntel family 6 model 70 stepping 1
     4 CPUs

GPU: UNKNOWN

Crash reason:  SIGSEGV /SEGV_MAPERR
Crash address: 0x0
Process uptime: not available

Thread 0 (crashed)
 0  libbreakpad-native.so + 0x228fe
    eip = 0x88aa28fe   esp = 0xbfdf5958   ebp = 0xbfdf5968   ebx = 0x0000000c
    esi = 0x8bc7e7ab   edi = 0xbfdf5b28   eax = 0x00000000   ecx = 0x00590000
    edx = 0xa8e5a000   efl = 0x00010286
    Found by: given as instruction pointer in context
 1  base.odex + 0x9179
    eip = 0x8ba49179   esp = 0xbfdf5970   ebp = 0xbfdf59bc
    Found by: previous frame's frame pointer
 2  0xa8e5a000
 
 ```
#### 定位源码 Crash 位置
使用 NDK 工具 arm-linux-androideabi-addr2line ($NDK_ROOT/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin) 即可查看源码出错的位置

``` bash
arm-linux-androideabi-addr2line -f -C -e libbreakpad-native.so 0x228fe
```



