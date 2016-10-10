package org.renix.updater.gui;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;

/**
 * @ClassName: AboutDialog
 * @Description: 更新器信息对话框
 * @author renzx
 * @date 2016年10月10日
 */
class AboutDialog extends WebDialog {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8107917872560814237L;

    private WebButton closeBtn;
    private WebLabel updaterTag;
    private WebLabel aboutLabel;
    private WebLabel versionLabel;
    private WebLabel contentLabel1;
    private WebLabel contentLabel2;
    private WebLabel contentLabel3;
    private WebPanel pageEndPanel;
    private WebPanel parentPanel;
    private WebPanel centerPanel;
    private WebPanel centerSouthPanel;
    private WebPanel centerNorthPanel;

    public AboutDialog(UpdaterFrame updaterFrame) {
        super(updaterFrame, true);
        initComponents();
        setLocationRelativeTo(updaterFrame);
    }

    /**
     * 初始化About界面
     */
    private void initComponents() {

        parentPanel = new WebPanel();
        aboutLabel = new WebLabel();
        pageEndPanel = new WebPanel();
        closeBtn = new WebButton();
        centerPanel = new WebPanel();
        centerSouthPanel = new WebPanel();
        versionLabel = new WebLabel();
        updaterTag = new WebLabel();
        centerNorthPanel = new WebPanel();
        contentLabel1 = new WebLabel();
        contentLabel2 = new WebLabel();
        contentLabel3 = new WebLabel();

        setAlwaysOnTop(true);// 是否置于顶层
        setUndecorated(true);// 是否隐藏窗口边框

        parentPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED),
                javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        parentPanel.setLayout(new java.awt.BorderLayout());

        aboutLabel.setFont(aboutLabel.getFont().deriveFont(
                aboutLabel.getFont().getStyle() | java.awt.Font.BOLD,
                aboutLabel.getFont().getSize() + 2));
        aboutLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aboutLabel.setText("关于更新器");
        parentPanel.add(aboutLabel, java.awt.BorderLayout.PAGE_START);

        pageEndPanel.setLayout(new java.awt.BorderLayout());

        closeBtn.setText("谢谢使用");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseBActionPerformed(evt);
            }
        });
        pageEndPanel.add(closeBtn, java.awt.BorderLayout.EAST);

        parentPanel.add(pageEndPanel, java.awt.BorderLayout.PAGE_END);

        centerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0));
        centerPanel.setLayout(new java.awt.BorderLayout(0, 8));

        centerSouthPanel.setLayout(new java.awt.BorderLayout());

        versionLabel.setText("版本:");
        centerSouthPanel.add(versionLabel, java.awt.BorderLayout.WEST);

        updaterTag.setText("V1.0.0.1");
        updaterTag.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 0));
        centerSouthPanel.add(updaterTag, java.awt.BorderLayout.CENTER);

        centerPanel.add(centerSouthPanel, java.awt.BorderLayout.SOUTH);

        centerNorthPanel.setLayout(new javax.swing.BoxLayout(centerNorthPanel,
                javax.swing.BoxLayout.Y_AXIS));

        contentLabel1.setText(("Java更新器是基于JDK6+可自动更新应用程序的升级软件"));
        centerNorthPanel.add(contentLabel1);

        contentLabel2.setText((""));
        centerNorthPanel.add(contentLabel2);

        contentLabel3.setText(("源码下载: https://estsvn.com:8443/svn/est-report/trunk/src/Updater"));
        centerNorthPanel.add(contentLabel3);

        centerPanel.add(centerNorthPanel, java.awt.BorderLayout.NORTH);

        parentPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(parentPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseBActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CloseBActionPerformed
        setVisible(false);
    }// GEN-LAST:event_CloseBActionPerformed
     // Variables declaration - do not modify//GEN-BEGIN:variables



    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        new AboutDialog(UpdaterFrame.getInstance()).setVisible(true);
    }
}
