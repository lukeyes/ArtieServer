package com.lukeyes.artie.panels;

import com.lukeyes.artie.Data;

import javax.swing.*;
import java.awt.*;

public class HistoryPanel {
    public static JPanel create() {
        JPanel historyPanel = new JPanel(new BorderLayout());

        JList<String> list = new JList<>();
        list.setModel(Data.getInstance().getHistoryModel());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.getVerticalScrollBar().addAdjustmentListener(
                e -> e.getAdjustable().setValue(e.getAdjustable().getMaximum()));

        JLabel label = new JLabel("History");
        historyPanel.add(label, BorderLayout.NORTH);

        historyPanel.add(listScroller, BorderLayout.CENTER);

        return historyPanel;
    }
}
