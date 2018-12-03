### BreakPad Demo

#### Crash Log

```
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
