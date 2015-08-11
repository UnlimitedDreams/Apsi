/*
 * The MIT License
 *
 * Copyright 2015 Miguel Angel Lemos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import Entity.Actividades;
import dao.UsuarioImple;
import Entity.Rol;
import static java.lang.System.out;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author usuario
 */
public class prueba {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TreeMap x = new UsuarioImple().cargarActividades("-1");
        for (Iterator it = x.entrySet().iterator(); it.hasNext();) {
            Map.Entry me = (Map.Entry) it.next();
            BigDecimal key = (BigDecimal) me.getKey();
            Actividades value = (Actividades) x.get(key);
            out.println("Rol: " + value.getDescripcion()+ "<br>");
        }
    }
}
