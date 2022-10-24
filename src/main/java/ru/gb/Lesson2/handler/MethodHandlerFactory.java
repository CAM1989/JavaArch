package ru.gb.Lesson2.handler;

import ru.gb.Lesson2.HttpServer;
import ru.gb.Lesson2.service.SocketService;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class MethodHandlerFactory {
    public static MethodHandler create(SocketService socketService, String www) {
        SortedMap<Integer, Class<MethodHandler>> mapClasses = new TreeMap<>();
        String packageName = "ru.gb.Lesson2.handler";
        ClassLoader classLoader = HttpServer.class.getClassLoader();
        String packagePath = packageName.replace('.', '/');
        URL urls = classLoader.getResource(packagePath);
        File folder = new File(urls.getPath());
        File[] classes = folder.listFiles();
        for (File aClass : classes) {
            int index = aClass.getName().indexOf(".");
            String className = aClass.getName().substring(0, index);
            String classNamePath = packageName + "." + className;
            Class<?> repoClass = null;
            try {
                repoClass = Class.forName(classNamePath);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Annotation[] annotations = repoClass.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == Handler.class) {
                    mapClasses.put(repoClass.getAnnotation(Handler.class).order(), (Class<MethodHandler>) repoClass);
                }
            }
        }
        return creatHandler(mapClasses.size(), mapClasses, socketService, www, 0);
    }

    private static MethodHandler creatHandler(int size, SortedMap<Integer, Class<MethodHandler>> mapClasses, SocketService socketService, String www, int index) {
        if (size == 0) {
            return null;
        } else {
            try {
                String method = mapClasses.get(index).getSimpleName().split("M")[0];
                return mapClasses.get(index).getConstructor(String.class, MethodHandler.class, SocketService.class, String.class).newInstance(method, creatHandler(size - 1, mapClasses, socketService, www, ++index), socketService, www);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}