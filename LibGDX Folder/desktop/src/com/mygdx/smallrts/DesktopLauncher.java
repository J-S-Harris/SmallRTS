package com.mygdx.smallrts;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import screensEtc.GameObject;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("SmallRTS");
		
		int width = 1000;
		int height = 600;
		
		config.setWindowedMode(width, height);
		new Lwjgl3Application(new GameObject(width, height), config);
	}
}
