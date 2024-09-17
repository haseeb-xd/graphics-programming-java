package org.haseeb.simulation.window;

import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.opengl.GL11.*;

public class Window {

    private  Window windowInstance = null;
    private long window;

   public Window getWindowInstance() {
        if (windowInstance == null) {
            windowInstance = new Window();
        }
        return windowInstance;
    }

    public void run() {
        init();
        loop();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private void init(){

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);


        window = glfwCreateWindow(800, 600, "Hello World", MemoryUtil.NULL, MemoryUtil.NULL);

        if (window == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Set the current OpenGL context
        glfwMakeContextCurrent(window);


        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);


        GL.createCapabilities();

        glfwSetFramebufferSizeCallback(window, this::setFrameBufferSizeCallback);

        //Enable depth testing for 3D
        glEnable(GL_DEPTH_TEST);



    }

    public void loop(){
        while (!glfwWindowShouldClose(window)) {

            glClearColor(0.2f, 0.3f, 0.3f, 1.0f);

            // Set the clear color
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Poll for window events
            glfwPollEvents();

            // Double buffer swap
            glfwSwapBuffers(window);
        }
    }

    public void setFrameBufferSizeCallback(long window, int width, int height) {
        glViewport(0, 0, width, height);
    }

}
