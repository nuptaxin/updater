package org.renix.updater.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import org.renix.updater.UpdaterMain;

import com.alee.extended.progress.WebStepProgress;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.rootpane.WebFrame;

/**
 * 更新器主界面
 * 
 * @author renzx
 */
public class UpdaterFrame extends WebFrame {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3349148414668844595L;
    private static UpdaterFrame uFrame = null;
    /**
     * 下一步按钮
     */
    private WebButton nextBtn;
    private WebPanel BarPanel;
    private WebPanel ButtonPanel;
    private WebPanel CommandP;
    private WebPanel DetailedP;
    /**
     * 详情按钮
     */
    private WebButton DetailsB;
    private WebLabel IconL;
    private WebButton InfoB;
    private WebPanel InfoHolderP;
    private WebLabel InfoL;
    private WebButton LaterB;
    private WebPanel MainPanel;
    private WebLabel NewVerL;
    private WebProgressBar PBar;
    private WebLabel PrevL;
    private WebPanel ProgressP;
    private WebLabel RelNotesL;
    private WebButton SkipB;
    private WebButton UpdateB;
    private WebLabel VersInfoL;
    private WebPanel WebPanel1;
    private WebPanel WebPanel3;
    private WebPanel WebPanel4;
    private WebPanel WebPanel5;

    /**
     * 新增-有步骤的总进度条
     */
    private WebStepProgress wsp;

    private static final BufferedImage sysicon;
    //
    // Updater callback;
    JEditorPane InfoPane;
    Details ChangeLogP;
    BufferedImage icon;

    static {
        BufferedImage img = null;
        try {
            img = ImageIO.read(UpdaterFrame.class.getResource("img/package.png"));
        } catch (IOException ex) {
        }
        sysicon = img;
    }

    /**
     * Creates new SwingDialog
     */
    private UpdaterFrame() {
        super();
        // 是否设置为无边框模式？
//        this.setUndecorated(true);
        initComponents();
        InfoPane = new JEditorPane();
        InfoPane.setEditable(false);
        ChangeLogP = new Details();
        ChangeLogP.setViewportView(InfoPane);
        DetailedP.add(ChangeLogP, BorderLayout.CENTER);
        LaterB.requestFocus();
    }

    public static UpdaterFrame getInstance() {
        synchronized (UpdaterFrame.class) {
            if (uFrame == null) {
                uFrame = new UpdaterFrame();
            }
        }

        return uFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wsp = new WebStepProgress("远程", "本地", "对比", "下载", "部署");
        wsp.setSelectedStep(0);
        wsp.setSelectionEnabled(false);
        wsp.setLabelsPosition(WebStepProgress.TRAILING);

        ProgressP = new WebPanel();
        BarPanel = new WebPanel();
        PBar = new WebProgressBar();// 进度条
        PBar.setProgressTopColor(Color.DARK_GRAY);
        ButtonPanel = new WebPanel();
        nextBtn = new WebButton();
        InfoL = new WebLabel();
        MainPanel = new WebPanel();
        InfoHolderP = new WebPanel();
        DetailedP = new WebPanel();
        RelNotesL = new WebLabel();
        WebPanel5 = new WebPanel();
        VersInfoL = new WebLabel();
        WebPanel1 = new WebPanel();
        InfoB = new WebButton();
        NewVerL = new WebLabel();
        IconL = new WebLabel();
        CommandP = new WebPanel();
        WebPanel3 = new WebPanel();
        DetailsB = new WebButton();
        WebPanel4 = new WebPanel();
        LaterB = new WebButton();
        UpdateB = new WebButton();
        SkipB = new WebButton();
        PrevL = new WebLabel();

        ProgressP.setLayout(new BorderLayout());

        BarPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 0));
        BarPanel.setLayout(new BorderLayout());

        PBar.setStringPainted(true);
        BarPanel.add(wsp, BorderLayout.NORTH);
        BarPanel.add(PBar, BorderLayout.CENTER);

        ProgressP.add(BarPanel, BorderLayout.CENTER);

        ButtonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 8));
        ButtonPanel.setLayout(new BorderLayout());

        nextBtn.setText(("下一步"));
        // nextBtn.setActionCommand("cancel");
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ActionBActionPerformed(evt);
            }
        });
        ButtonPanel.add(nextBtn, BorderLayout.CENTER);

        ProgressP.add(ButtonPanel, BorderLayout.EAST);

        InfoL.setText(("正在获取新版本详情……"));
        ProgressP.add(InfoL, BorderLayout.LINE_START);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        MainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        MainPanel.setLayout(new BorderLayout());

        InfoHolderP.setLayout(new BorderLayout());

        DetailedP.setLayout(new BorderLayout());

        RelNotesL.setFont(RelNotesL.getFont().deriveFont(
                RelNotesL.getFont().getStyle() | java.awt.Font.BOLD));
        RelNotesL.setText(("更新说明"));
        RelNotesL.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 4, 0));
        DetailedP.add(RelNotesL, BorderLayout.NORTH);

        InfoHolderP.add(DetailedP, BorderLayout.CENTER);

        WebPanel5.setLayout(new BorderLayout());

        VersInfoL.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 16));
        WebPanel5.add(VersInfoL, BorderLayout.CENTER);

        WebPanel1.setLayout(new BorderLayout());

        InfoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/info.png"))); // NOI18N
        InfoB.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        InfoB.setBorderPainted(false);
        InfoB.setContentAreaFilled(false);
        InfoB.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("img/info_sel.png"))); // NOI18N
        InfoB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                InfoBActionPerformed(evt);
            }
        });
        WebPanel1.add(InfoB, BorderLayout.EAST);

        NewVerL.setFont(NewVerL.getFont().deriveFont(
                NewVerL.getFont().getStyle() | java.awt.Font.BOLD, NewVerL.getFont().getSize() + 1));
        NewVerL.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 4, 0));
        WebPanel1.add(NewVerL, BorderLayout.CENTER);

        WebPanel5.add(WebPanel1, BorderLayout.NORTH);

        InfoHolderP.add(WebPanel5, BorderLayout.NORTH);

        MainPanel.add(InfoHolderP, BorderLayout.CENTER);

        IconL.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        IconL.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 8, 0, 12));
        MainPanel.add(IconL, BorderLayout.WEST);

        CommandP.setLayout(new BorderLayout());

        WebPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12));
        WebPanel3.setLayout(new BorderLayout());

        DetailsB.setText(("详细信息"));
        DetailsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DetailsBActionPerformed(evt);
            }
        });
        WebPanel3.add(DetailsB, BorderLayout.CENTER);

        CommandP.add(WebPanel3, BorderLayout.WEST);

        WebPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 12));
        WebPanel4.setLayout(new BorderLayout());

        LaterB.setText(("下次提醒我"));
        LaterB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LaterBActionPerformed(evt);
            }
        });
        WebPanel4.add(LaterB, BorderLayout.CENTER);

        UpdateB.setText(("立即更新"));
        UpdateB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UpdateBActionPerformed(evt);
            }
        });
        WebPanel4.add(UpdateB, BorderLayout.EAST);

        SkipB.setText(("忽略此版本"));
        SkipB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SkipBActionPerformed(evt);
            }
        });
        WebPanel4.add(SkipB, BorderLayout.WEST);

        CommandP.add(WebPanel4, BorderLayout.EAST);

        PrevL.setForeground(java.awt.Color.red);
        PrevL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PrevL.setText(("WARNING! This update requires elevated privileges!"));
        PrevL.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 12, 4, 12));
        CommandP.add(PrevL, BorderLayout.NORTH);

        MainPanel.add(CommandP, BorderLayout.SOUTH);

        getContentPane().add(MainPanel, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 立即更新按钮监听器
     * 
     * @param evt
     */
    private void UpdateBActionPerformed(ActionEvent evt) {// GEN-FIRST:event_UpdateBActionPerformed
        CommandP.setVisible(false);
        ProgressP.setVisible(true);
        MainPanel.add(ProgressP, BorderLayout.SOUTH);
        UpdaterMain.watcher.step1x();
        InfoL.setText(("获取版本详情……"));
    }// GEN-LAST:event_UpdateBActionPerformed

    private void LaterBActionPerformed(ActionEvent evt) {// GEN-FIRST:event_LaterBActionPerformed
        // callback.actionDefer();
        UpdaterMain.watcher.closeMeAndStarttarget(2);
    }// GEN-LAST:event_LaterBActionPerformed

    private void SkipBActionPerformed(ActionEvent evt) {// GEN-FIRST:event_SkipBActionPerformed
        // callback.actionIgnore();
        //更新本地的xml文档
        UpdaterMain.watcher.WriteLocalVersion(UpdaterMain.localVersion,UpdaterMain.up.getVersion().getRelease());
        UpdaterMain.watcher.closeMeAndStarttarget(2);
    }// GEN-LAST:event_SkipBActionPerformed

    private void ActionBActionPerformed(ActionEvent evt) {// GEN-FIRST:event_ActionBActionPerformed
        nextBtn.setEnabled(false);
        if (UpdaterMain.watcher.currentStep == 1 && UpdaterMain.watcher.percent == 1f) {
            UpdaterMain.watcher.step2x();
            InfoL.setText(("获取本地文件……"));
        } else if (UpdaterMain.watcher.currentStep == 2 && UpdaterMain.watcher.percent == 1f) {
            UpdaterMain.watcher.step3x();
            InfoL.setText(("对比文件差异……"));
        } else if (UpdaterMain.watcher.currentStep == 3 && UpdaterMain.watcher.percent == 1f) {
            UpdaterMain.watcher.step4x();
            InfoL.setText(("下载远程文件……"));
        } else if (UpdaterMain.watcher.currentStep == 4 && UpdaterMain.watcher.percent == 1f) {
            UpdaterMain.watcher.step5x();
            InfoL.setText(("正在部署文件……"));
        }
        if ("完成".equals(nextBtn.getText())) {
            UpdaterMain.watcher.closeMeAndStarttarget(1);
        }
        nextBtn.setEnabled(true);
    }// GEN-LAST:event_ActionBActionPerformed

    private void InfoBActionPerformed(ActionEvent evt) {// GEN-FIRST:event_InfoBActionPerformed
        new AboutDialog(this).setVisible(true);
    }// GEN-LAST:event_InfoBActionPerformed

    private void DetailsBActionPerformed(ActionEvent evt) {// GEN-FIRST:event_DetailsBActionPerformed
        DetailedP.setVisible(!DetailedP.isVisible());
//        pack();
    }// GEN-LAST:event_DetailsBActionPerformed
     // Variables declaration - do not modify//GEN-BEGIN:variables


    // End of variables declaration//GEN-END:variables

    @Override
    public void pack() {
        Image current = icon;
        if (icon == null)
            current = sysicon;
        if (!DetailedP.isVisible())
            current = current.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
        IconL.setIcon(new ImageIcon(current));
        super.pack();
    }

    public static void runUpdaterFrame(final String title, final String version,
            final String verInfo, final String updateInfo) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Install WebLaF as application L&F
                WebLookAndFeel.globalTextFont = new FontUIResource("仿宋", 0, 12);
                WebLookAndFeel.buttonFont = new FontUIResource(new JButton().getFont());
                WebLookAndFeel.labelFont = new FontUIResource(new JLabel().getFont());
                WebLookAndFeel.install();
                UpdaterFrame gui = UpdaterFrame.getInstance();
                gui.NewVerL.setText(version);
                gui.VersInfoL.setText(verInfo);
                gui.setTitle(title);
                gui.InfoPane.setContentType("text/html");
                gui.InfoPane.setText(updateInfo);
                // gui.icon = icon;
                gui.SkipB.setVisible(true);
                gui.PrevL.setVisible(false);
                gui.InfoB.setVisible(true);
                gui.DetailedP.setVisible(false);
                gui.pack();
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
//                gui.setResizable(true);
                gui.setSize(400, 200);
                //如果无法获取本地版本信息，弹出窗口由用户选择
                if(UpdaterMain.localVersion==null){
                    new VersionDialog(UpdaterFrame.getInstance()).setVisible(true);
                }

            }
        });

    }

    public void updateStepProgress(int step) {
        uFrame = getInstance();
        uFrame.wsp.setSelectedStep(step);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println(UpdaterMain.watcher.currentStep + "-"
                        + UpdaterMain.watcher.percent);
                uFrame.wsp.revalidate();
                uFrame.wsp.repaint();
                // 是否自动触发？
                // if(UpdaterMain.watcher.currentStep==1&&UpdaterMain.watcher.percent==1f){
                // UpdaterMain.watcher.step2x();
                // }
                // else if(UpdaterMain.watcher.currentStep==2&&UpdaterMain.watcher.percent==1f){
                // UpdaterMain.watcher.step3x();
                // }
                // else if(UpdaterMain.watcher.currentStep==3&&UpdaterMain.watcher.percent==1f){
                // UpdaterMain.watcher.step4x();
                // }
                // else if(UpdaterMain.watcher.currentStep==4&&UpdaterMain.watcher.percent==1f){
                // UpdaterMain.watcher.step5x();
                // }
                // else if(UpdaterMain.watcher.currentStep==5&&UpdaterMain.watcher.percent==0.5f){
                // UpdaterMain.watcher.step5xx();
                // }
                if (UpdaterMain.watcher.currentStep == 5 && UpdaterMain.watcher.percent == 0.5f) {
                    UpdaterMain.watcher.percent = 0.51f;
                    UpdaterMain.watcher.step5xx();
                }
            }
        });

    }

    /**
     * @param ratio下载速率
     * @param percent百分比
     */
    public void updateProgressBar(String ratio, float percent) {
        uFrame = getInstance();
        uFrame.PBar.setValue(Math.round(percent * 100));
        uFrame.PBar.setToolTipText("当前下载速度: " + ratio);
        // uFrame.PBar.setString(ratio);
        uFrame.PBar.setString(Math.round(percent * 100) + "%");
        System.out.println(percent);
        if (UpdaterMain.watcher.currentStep == 5 && UpdaterMain.watcher.percent == 1f) {
            nextBtn.setText("完成");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        runUpdaterFrame("更新器", "V1.0.0.1", "测试新版本", "新版本说明1");

    }
}