/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.DanhMucBean;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.*;
import project_qlbh.*;

/**
 *
 * @author Administrator
 */
public class ChuyenManHinh {

    private JPanel root;
    private String kindSelected = "";

    private List<DanhMucBean> listItem = null;

    public ChuyenManHinh(JPanel pnlRoot) {
        this.root = pnlRoot;
    }

    public void setView(JPanel pnlItem, JLabel lblItem) {
        kindSelected = "Trang_Chu";
        pnlItem.setBackground(new Color(96, 100, 90));
        lblItem.setBackground(new Color(96, 100, 90));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChinhJPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getLabel().addMouseListener(new LabelEvent(item.getKind(), item.getPanel(), item.getLabel()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;

        private JPanel pnlItem;
        private JLabel lblItem;

        public LabelEvent(String kind, JPanel pnlItem, JLabel lblItem) {
            this.kind = kind;
            this.pnlItem = pnlItem;
            this.lblItem = lblItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "Hoc_Sinh":
                    node = new Bang_HocSinh();
                    break;
                case "Thong_Ke":
                    node = new Bang_ThongKe_HocSinh();
                    break;
                case "Lop_Hoc":
                    node = new Bang_LopHoc();
                    break;
                case "Khoa_Hoc":
                    node = new Bang_KhoaHoc();
                    break;
                default:
                    node = new TrangChinhJPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            pnlItem.setBackground(new Color(23, 23, 23));
            lblItem.setBackground(new Color(23, 23, 23));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            pnlItem.setBackground(new Color(218, 0, 55));
            lblItem.setBackground(new Color(218, 0, 55));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                pnlItem.setBackground(new Color(68, 68, 68));
                lblItem.setBackground(new Color(68, 68, 68));
            }
        }

        private void setChangeBackground(String kind) {
            for (DanhMucBean item : listItem) {
                if (item.getKind().equalsIgnoreCase(kind)) {
                    item.getLabel().setBackground(new Color(218, 0, 55));
                    item.getPanel().setBackground(new Color(218, 0, 55));
                } else {
                    item.getLabel().setBackground(new Color(68, 68, 68));
                    item.getPanel().setBackground(new Color(68, 68, 68));
                }
            }
        }

    }

}
