package com.example.libbreakpad;


public class BreakPadManager {

  private static BreakPadManager sBreakPadManager;

  public static BreakPadManager getInstance(){
     if (sBreakPadManager == null){
         synchronized (BreakPadManager.class){
             if (sBreakPadManager == null){
                 sBreakPadManager = new BreakPadManager();
             }
         }
     }
     return sBreakPadManager;
  }


  static {
    System.loadLibrary("breakpad-native");
  }


  public native  void initBrakPad(String path);


  public native void testBreak();
}
