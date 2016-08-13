package org.renix.updater.gui;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;



/**
 * 更新器信息对话框
 * @author renzx
 */
class AboutDialog extends WebDialog {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8107917872560814237L;
    
    private WebButton CloseB;
    private WebLabel VersionL;
    private WebLabel jLabel1;
    private WebLabel jLabel2;
    private WebLabel jLabel4;
    private WebLabel jLabel5;
    private WebLabel jLabel6;
    private WebPanel jPanel1;
    private WebPanel jPanel2;
    private WebPanel jPanel3;
    private WebPanel jPanel4;
    private WebPanel jPanel5;

    public AboutDialog(UpdaterFrame updaterFrame) {
        super(updaterFrame, true);
        initComponents();
        setLocationRelativeTo(updaterFrame);
    }

    /**
     * 初始化About界面
     */
    private void initComponents() {

        jPanel2 = new WebPanel();
        jLabel1 = new WebLabel();
        jPanel1 = new WebPanel();
        CloseB = new WebButton();
        jPanel3 = new WebPanel();
        jPanel4 = new WebPanel();
        jLabel2 = new WebLabel();
        VersionL = new WebLabel();
        jPanel5 = new WebPanel();
        jLabel4 = new WebLabel();
        jLabel5 = new WebLabel();
        jLabel6 = new WebLabel();

        setAlwaysOnTop(true);//是否置于顶层
        setUndecorated(true);//是否隐藏窗口边框

        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED),
                javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(jLabel1.getFont().deriveFont(
                jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize() + 2));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("关于更新器");
        jPanel2.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        CloseB.setText("谢谢使用");
        CloseB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseBActionPerformed(evt);
            }
        });
        jPanel1.add(CloseB, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0));
        jPanel3.setLayout(new java.awt.BorderLayout(0, 8));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("版本:");
        jPanel4.add(jLabel2, java.awt.BorderLayout.WEST);

        VersionL.setText("V1.0.0.1");
        VersionL.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 0));
        jPanel4.add(VersionL, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        jLabel4.setText(("Jupidator is a library for automatic updating of applications."));
        jPanel5.add(jLabel4);

        jLabel5.setText(("It is open source under the LGPL license."));
        jPanel5.add(jLabel5);

        jLabel6.setText(("More info can be found in: http://jupidator.sourceforge.net"));
        jPanel5.add(jLabel6);

        jPanel3.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

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
