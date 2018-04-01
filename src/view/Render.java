package view;

import objects.Renderable;

import java.io.Console;

class Render {

    static void render(Console console, Renderable obj) {

        String objType = obj.getClass().getName();

        console.format("╞═════════════════ %s Contains ═════════════════╡%n", obj.toString());
        for (Renderable listObject : obj.getObjList()) {

            console.format("%" + (25 + listObject.getDisplayName().length()/2) + "s%n", listObject.getDisplayName());

        }

        console.format("%n");

    }



}
