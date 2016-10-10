package org.renix.updater.gui;

import java.awt.Component;
import java.awt.Dimension;

import com.alee.laf.scroll.WebScrollPane;


/**
 * @ClassName: Details
 * @Description: 版本详情弹出框
 * @author renzx
 * @date 2016年10月10日
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
