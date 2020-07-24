package com.shenjinxiang.swing.win;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/10 9:50
 */
public class GridBagWindow extends LayoutWindow {

    public GridBagWindow(String title, int width, int height) {
        super(title, width, height);
        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(Color.black);
        jPanel1.setSize(300, 100);
        JPanel jPanel2 = new JPanel();
        jPanel2.setBackground(Color.RED);
        jPanel2.setSize(100, 100);
        JPanel jPanel3 = new JPanel();
        jPanel3.setBackground(Color.CYAN);
        jPanel3.setSize(100, 100);
        JPanel jPanel4 = new JPanel();
        jPanel4.setBackground(Color.orange);
        jPanel4.setSize(300, 100);

        GridBagLayout gridBagLayout = new GridBagLayout();
        this.jFrame.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 100;
        gridBagLayout.setConstraints(jPanel1, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagLayout.setConstraints(jPanel2, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagLayout.setConstraints(jPanel3, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 100;
        gridBagLayout.setConstraints(jPanel4, gridBagConstraints);

        this.jFrame.add(jPanel1);
        this.jFrame.add(jPanel2);
        this.jFrame.add(jPanel3);
        this.jFrame.add(jPanel4);

    }

}
