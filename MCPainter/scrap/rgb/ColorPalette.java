/*
 * The MIT License
 *
 * Copyright 2015 SBPrime.
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
package org.primesoft.mcpainter.drawing.dilters;

import java.awt.Color;

/**
 *
 * @author SBPrime
 */
public class ColorPalette implements IColorPalette {

    private final ColorEx[] m_palette;

    public ColorPalette(Color[] pal) {
        m_palette = new ColorEx[pal.length];
        for (int i = 0; i < pal.length; i++) {
            m_palette[i] = new ColorEx(pal[i]);
        }
    }

    @Override
    public ColorEx findClosestColor(ColorEx c) {
        if (c.isTransparent()) {
            return ColorEx.TRANSPARENT;
        }

        double delta = Double.POSITIVE_INFINITY;
        int result = -1;
        for (int i = 0; i < m_palette.length; i++) {
            ColorEx palC = m_palette[i];
            double d = ColorEx.dist(c, palC);
            if (d < delta) {
                result = i;
                delta = d;
            }
        }

        return result != -1 ? m_palette[result] : ColorEx.TRANSPARENT;
    }
}
