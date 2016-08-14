package org.renix.updater.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.joda.time.base.BaseSingleFieldPeriod;
import org.renix.updater.UpdaterMain;
import org.renix.updater.bean.Version;

import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.list.WebList;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;



/**
 * 版本选择对话框
 * @author renzx
 */
class VersionDialog extends WebDialog {
    
    private static Map<String, Version> versionMap = UpdaterMain.up.getVersionMap();
    private String selectedRelease = null;
    private String baseRelease = null;
//    static{
//        Version v = new Version();
//        v.setRelease(0);
//        v.setTag("0.0.0.1");
//        v.setDesp("init");
//        versionMap.put(v.getTag(), v);
//        Version v1 = new Version();
//        v1.setRelease(0);
//        v1.setTag("0.0.0.2");
//        v1.setDesp("init2");
//        versionMap.put(v1.getTag(), v1);
//        Version v2 = new Version();
//        v2.setRelease(0);
//        v2.setTag("0.0.0.3");
//        v2.setDesp("init3");
//        versionMap.put(v2.getTag(), v2);
//    }
    

    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5838484324952130551L;
    /**
     * 确认按钮
     */
    private WebButton confirmBtn;
    /**
     * 自动选择按钮
     */
    private WebButton autoBtn;
    /**
     * 标题label
     */
    private WebLabel titleLabel;
    
    private WebLabel contentLabel;
    private WebComboBox versionBox;
    
    private WebPanel pageEndPanel;
    
    private WebPanel parentPanel;
    private WebPanel centerPanel;
    private WebPanel centerSouthPanel;
    private WebPanel centerNorthPanel;

    public VersionDialog(UpdaterFrame updaterFrame) {
        super(updaterFrame, true);
        initComponents();
        setLocationRelativeTo(updaterFrame);
    }

    /**
     * 初始化About界面
     */
    private void initComponents() {

        parentPanel = new WebPanel();
        titleLabel = new WebLabel();
        pageEndPanel = new WebPanel();
        confirmBtn = new WebButton();
        autoBtn = new WebButton();
        centerPanel = new WebPanel();
        centerSouthPanel = new WebPanel();
        centerNorthPanel = new WebPanel();
        contentLabel = new WebLabel();
        //将结果排序，默认使用release从小到大排序
        Map<Integer,String> sortMap = new TreeMap<Integer,String>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                return o1-o2;
            }});
        for (String tag : versionMap.keySet()) {
            sortMap.put(versionMap.get(tag).getRelease(), tag);
        }
        versionBox = new WebComboBox(sortMap.values().toArray());
        selectedRelease = versionBox.getSelectedItem().toString();
        baseRelease = versionBox.getSelectedItem().toString();
        versionBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRelease = versionBox.getSelectedItem().toString();
            }
        });

        setAlwaysOnTop(true);//是否置于顶层
        setUndecorated(true);//是否隐藏窗口边框

        parentPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED),
                javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        parentPanel.setLayout(new java.awt.BorderLayout());

        titleLabel.setFont(titleLabel.getFont().deriveFont(
                titleLabel.getFont().getStyle() | java.awt.Font.BOLD, titleLabel.getFont().getSize() + 2));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("版本选择器");
        parentPanel.add(titleLabel, java.awt.BorderLayout.PAGE_START);

        pageEndPanel.setLayout(new java.awt.BorderLayout());

        confirmBtn.setText("确认");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });
        autoBtn.setText("系统自动选择");
        autoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoBtnActionPerformed(evt);
            }
        });
        pageEndPanel.add(confirmBtn, java.awt.BorderLayout.EAST);
        pageEndPanel.add(autoBtn, java.awt.BorderLayout.WEST);

        parentPanel.add(pageEndPanel, java.awt.BorderLayout.PAGE_END);

        centerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0));
        centerPanel.setLayout(new java.awt.BorderLayout(0, 8));

        centerSouthPanel.setLayout(new java.awt.BorderLayout());



        centerPanel.add(centerSouthPanel, java.awt.BorderLayout.NORTH);
        centerPanel.add(versionBox, java.awt.BorderLayout.SOUTH);

        centerNorthPanel.setLayout(new javax.swing.BoxLayout(centerNorthPanel, javax.swing.BoxLayout.Y_AXIS));

        contentLabel.setText("系统无法识别当前程序版本，请选择:");
        centerNorthPanel.add(contentLabel);


        centerPanel.add(centerNorthPanel, java.awt.BorderLayout.NORTH);

        parentPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(parentPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CloseBActionPerformed
        UpdaterMain.watcher.WriteLocalVersion(versionMap.get(selectedRelease));
        setVisible(false);
        
    }// GEN-LAST:event_CloseBActionPerformed
    private void autoBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CloseBActionPerformed
        UpdaterMain.watcher.WriteLocalVersion(versionMap.get(baseRelease));
        setVisible(false);
    }// GEN-LAST:event_CloseBActionPerformed
     // Variables declaration - do not modify//GEN-BEGIN:variables

    

    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
         new VersionDialog(UpdaterFrame.getInstance()).setVisible(true);
    }
}
