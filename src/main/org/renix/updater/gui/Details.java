/*
 *
 * This file is part of Jupidator.
 *
 * Jupidator is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2.
 *
 *
 * Jupidator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jupidator; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

package org.renix.updater.gui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import com.alee.laf.scroll.WebScrollPane;

/**
 *
 * @author teras
 */
public class Details extends WebScrollPane {

    public Details(Component view) {
        super(view);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1434601170936252157L;

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.width = 400;
        if (d.height < 120)
            d.height = 120;
        if (d.height > 240)
            d.height = 240;
        return d;
    }
}
