/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_tbd1;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Date;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victo
 */
public class Log_In extends javax.swing.JFrame {

    int idpv = 0;
    int cv = 0;
    int modify = 0;
    int popup = 0;

    Admin admin = null;
    static Random rand = new Random();
    String username = "";
    String password = "";
    String currentusername = null;
    int currentid = 0;
    //para numeros de identidad que se necesiten almacenar
    int numA = 0;
    int numV = 0;
    int numC = 0;

    /**
     * Creates new form Login
     */
    public Log_In() {

        initComponents();

        this.setLocationRelativeTo(null);
        this.setTitle("Teoría de Base de Datos I");
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("pics/Tiger.jpg"));
        this.setIconImage(icono);
        this.pack();
        MainScreen();
        admin = new Admin();

        //text fields
        tf_imgV.setEnabled(false);
        tf_imgM.setEnabled(false);
        //Cosas del Sign
        //Cosas del MainScreen_Vendedor
        MainScreen_Others.setLocationRelativeTo(null);
        MainScreen_Others.pack();
        MainScreen_Others.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        MainScreen_Others.setLocationRelativeTo(this);
        //Cosas del MainScreen_Comprador
        MainScreen_Agente.setLocationRelativeTo(null);
        MainScreen_Agente.pack();
        MainScreen_Agente.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        MainScreen_Agente.setLocationRelativeTo(this);
        //Cosas del MainScreen_Admin
        MainScreen_Admin.setLocationRelativeTo(null);
        MainScreen_Admin.pack();
        MainScreen_Admin.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        MainScreen_Admin.setLocationRelativeTo(this);
        //"botones"
        PropCompradas.setVisible(false);
        PropMarket.setVisible(false);
        Prop_en_mercado.setVisible(false);
        PropSold.setVisible(false);
        reset();

        //logouts
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                admin.logout();

                System.exit(0);
            }
        });
        MainScreen_Admin.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                admin.logout();
                System.exit(0);
            }
        });
        MainScreen_Agente.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                admin.logout();
                System.exit(0);
            }
        });
        MainScreen_Others.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                admin.logout();
                System.exit(0);
            }
        });

        mi_modify.setText("Modificar");
        mi_delete.setText("Borrar");

    }

    public void reset() {
        pn_menuCrud.setVisible(false);
        tb_admin.setVisible(false);
        //Panels Escondidos
        Bitacora_Panel.setVisible(false);
        panelVistas2.setVisible(false);
        panel_agentes.setVisible(false);
        panel_propEnMercadoCliente.setVisible(false);
        panel_propVendida.setVisible(false);
        panel_propMercadoVendedor.setVisible(false);
        panel_pBought.setVisible(false);
        //labels
        //Panels Escondidos
        Layer.setVisible(true);
        Layer.setVisible(true);
        Layer.setVisible(true);

    }

    public void setupImg(JFrame window, char c, int id) {
        String path = null;
        jd_propImage.pack();
        jd_propImage.setLocationRelativeTo(window);
        if (c == 'v') {
            path = admin.getimgpathv(id);
        } else {
            path = admin.getimgpathm(id);
        }

        if (path == null) {
            path = "./src/imagenes_casas/imgDefault.png";
        } else {
            File file = new File(path);
            if (!file.exists()) {
                path = "./src/imagenes_casas/imgDefault.png";
            }
        }
        Image img = Toolkit.getDefaultToolkit().createImage(path).getScaledInstance(444, 273, 0);
        this.lb_img.setIcon(new ImageIcon(img));
        jd_propImage.setVisible(true);
    }

    public void jd_createClienteReset() {
        modify = 0;
        tf_identidadCliente.setEnabled(true);
        lb_clienteTitulo.setText("Crear");
        bt_crearCliente.setText("Crear Cliente");
        tf_identidadCliente.setText("");
        tf_nombreCliente.setText("");
        tf_direccionCliente.setText("");
        tf_celularCliente.setText("");

    }

    public void jd_crearAgenteReset() {
        lb_cAgente.setText("Crear");
        tf_identidad.setText("");
        tf_nombre.setText("");
        tf_direccion.setText("");
        tf_celular.setText("");
        tf_oficina.setText("");
        bt_crearAgente.setText("Crear Agente");
        tf_identidad.setEnabled(true);
    }

    public void jd_createpmReset() {
        tf_nombreMerc.setText("");
        tf_CiudadMerc.setText("");
        tf_direccionMerc.setText("");
        tf_caracteriticasMerc.setText("");
        tf_precioMerc.setText("");

        ResultSet ra = admin.getAgentes();
        ResultSet rv = admin.getVendedores();

        DefaultComboBoxModel ma = (DefaultComboBoxModel) cb_agentem.getModel();
        DefaultComboBoxModel mv = (DefaultComboBoxModel) cb_vendedorm.getModel();

        ma.removeAllElements();
        mv.removeAllElements();

        //reseteo de labels 
        lb_pm.setText("CREAR");
        bt_crearPropiedadVendida.setText("Crear Propiedad");

        lb_am.setVisible(true);
        lb_vm.setVisible(true);
        cb_agentem.setVisible(true);
        cb_vendedorm.setVisible(true);

        try {

            while (ra.next()) {

                ma.addElement(ra.getInt(1));
            }

            while (rv.next()) {
                mv.addElement(rv.getInt(1));
            }

            cb_agentem.setModel(ma);
            cb_vendedorm.setModel(mv);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void jd_createpvReset() {
        lb_pv.setText("Crear");
        tf_nombreMerc1.setText("");
        tf_CiudadMerc1.setText("");
        tf_direccionMerc1.setText("");
        tf_caracteriticasMerc1.setText("");
        tf_precioMerc1.setText("");
        tf_comision.setText("");
        sp_cantiHabi.setValue(0);

        ResultSet ra = admin.getAgentes();
        ResultSet rv = admin.getVendedores();
        ResultSet rc = admin.getCompradores();

        DefaultComboBoxModel ma = (DefaultComboBoxModel) cb_agente.getModel();
        DefaultComboBoxModel mv = (DefaultComboBoxModel) cb_vendedor.getModel();
        DefaultComboBoxModel mc = (DefaultComboBoxModel) cb_comprador.getModel();

        ma.removeAllElements();
        mv.removeAllElements();
        mc.removeAllElements();

        //reseteo de labels 
        bt_crearPropiedadVendida.setText("Crear Propiedad");

        lb_a.setVisible(true);
        lb_v.setVisible(true);
        lb_c.setVisible(true);
        cb_agente.setVisible(true);
        cb_vendedor.setVisible(true);
        cb_comprador.setVisible(true);
        try {

            while (ra.next()) {

                ma.addElement(ra.getInt(1));
            }

            while (rv.next()) {
                mv.addElement(rv.getInt(1));
            }

            while (rc.next()) {
                mc.addElement(rc.getInt(1));
            }

            cb_agente.setModel(ma);
            cb_vendedor.setModel(mv);
            cb_comprador.setModel(mc);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void jd_crearagenteReset() {
        //lb_tituloAgente.setText("CREAR AGENTE");
        bt_crearAgente.setText("Crear Agente");
        tf_identidad.setText("");
        tf_nombre.setText("");
        tf_direccion.setText("");
        tf_celular.setText("");
        tf_oficina.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainScreen_Admin = new javax.swing.JFrame();
        FullBack = new javax.swing.JPanel();
        pn_menuCrud = new javax.swing.JPanel();
        bt_admin = new javax.swing.JButton();
        btn_pMercado = new javax.swing.JButton();
        btn_pVenta = new javax.swing.JButton();
        btn_agentes = new javax.swing.JButton();
        btn_vendedor = new javax.swing.JButton();
        btn_vendedor1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tb_admin = new javax.swing.JTabbedPane();
        pn_compradores = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_compradores = new javax.swing.JTable();
        bt_addComprador = new javax.swing.JButton();
        pn_vendedor = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_vendedores = new javax.swing.JTable();
        bt_addVendedor = new javax.swing.JButton();
        pn_agentes = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_agentes = new javax.swing.JTable();
        bt_addAgente = new javax.swing.JButton();
        pn_pVenta = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_propiedadesv = new javax.swing.JTable();
        bt_addPVendida = new javax.swing.JButton();
        pn_pMercado = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_pMercado = new javax.swing.JTable();
        bt_pMercado = new javax.swing.JButton();
        pn_admins = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_admins = new javax.swing.JTable();
        bt_addAdmin = new javax.swing.JButton();
        Bitacora_Panel = new javax.swing.JPanel();
        backArrow2 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabla_bitacora = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        Bita = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        panelVistas2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        backArrow3 = new javax.swing.JLabel();
        tabbedVistas = new javax.swing.JTabbedPane();
        vista5 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbVentasPrecio = new javax.swing.JTable();
        btnVentasPrecio = new javax.swing.JButton();
        vista6 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbVentasCaracteristicas = new javax.swing.JTable();
        btnVentasCaracteristicas = new javax.swing.JButton();
        vista1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbVentasAgente = new javax.swing.JTable();
        btnCargarVentasAgente = new javax.swing.JButton();
        vista4 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbVentasUbicacion = new javax.swing.JTable();
        btnVentasUbicacion = new javax.swing.JButton();
        vista3 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbComprasComprador = new javax.swing.JTable();
        btnComprasComprador = new javax.swing.JButton();
        vista2 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbVentasVendedor = new javax.swing.JTable();
        btnVentasVendedor = new javax.swing.JButton();
        vista8 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbPromedioAnual = new javax.swing.JTable();
        btnValorVentaAnio = new javax.swing.JButton();
        yearTextField = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        vista7 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tablaAgenteGoat = new javax.swing.JTable();
        btnventaAnual = new javax.swing.JButton();
        yearTextField1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        Line = new javax.swing.JPanel();
        Layer = new javax.swing.JPanel();
        Indicator = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BitacoraPanel = new javax.swing.JPanel();
        Bitacora = new javax.swing.JLabel();
        OptionPanel = new javax.swing.JPanel();
        Options = new javax.swing.JLabel();
        MantenimientoPanel = new javax.swing.JPanel();
        Mantenimiento = new javax.swing.JLabel();
        ReportesPanel = new javax.swing.JPanel();
        Reportes = new javax.swing.JLabel();
        ExitPanel = new javax.swing.JPanel();
        Exit2 = new javax.swing.JLabel();
        Real3 = new javax.swing.JLabel();
        State = new javax.swing.JLabel();
        ListPanel = new javax.swing.JLabel();
        SmallHouse = new javax.swing.JLabel();
        Administrador = new javax.swing.JLabel();
        MainScreen_Others = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        panel_pBought = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        backArrow8 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tb_pBought = new javax.swing.JTable();
        panel_propEnMercadoCliente = new javax.swing.JPanel();
        backArrow5 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tc_propMercado = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        panel_propVendida = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        backArrow6 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        pvtable_vendedores = new javax.swing.JTable();
        panel_propMercadoVendedor = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        backArrow7 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        pvtable_mercado = new javax.swing.JTable();
        Line1 = new javax.swing.JPanel();
        Layer1 = new javax.swing.JPanel();
        Indicator1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        OptionVendedor = new javax.swing.JPanel();
        OptionsVend = new javax.swing.JLabel();
        ExitPanel1 = new javax.swing.JPanel();
        Exit3 = new javax.swing.JLabel();
        PropMarket = new javax.swing.JLabel();
        PropCompradas = new javax.swing.JLabel();
        PropSold = new javax.swing.JLabel();
        Prop_en_mercado = new javax.swing.JLabel();
        Real4 = new javax.swing.JLabel();
        State1 = new javax.swing.JLabel();
        ListPanel1 = new javax.swing.JLabel();
        SmallHouse1 = new javax.swing.JLabel();
        Administrador1 = new javax.swing.JLabel();
        MainScreen_Agente = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        panel_agentes = new javax.swing.JPanel();
        backArrow4 = new javax.swing.JLabel();
        tabbedAgentes = new javax.swing.JTabbedPane();
        pn_pmAgentes = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tb_propmAgentes = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        pn_pvAgentes1 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tb_propvAgentes = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        Line2 = new javax.swing.JPanel();
        Layer2 = new javax.swing.JPanel();
        Indicator2 = new javax.swing.JLabel();
        panel_agente_menu = new javax.swing.JPanel();
        OptionPanel2 = new javax.swing.JPanel();
        Options2 = new javax.swing.JLabel();
        Propiedades_Mercado = new javax.swing.JPanel();
        PropMercado1 = new javax.swing.JLabel();
        ExitPanel2 = new javax.swing.JPanel();
        Exit4 = new javax.swing.JLabel();
        PropiedadesVendidas = new javax.swing.JPanel();
        PropVendidas = new javax.swing.JLabel();
        Real5 = new javax.swing.JLabel();
        State2 = new javax.swing.JLabel();
        ListPanel2 = new javax.swing.JLabel();
        SmallHouse2 = new javax.swing.JLabel();
        Administrador2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jd_createAdmin = new javax.swing.JDialog();
        pn_createAdmin = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_password = new javax.swing.JTextField();
        bt_crearAdmin = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jd_createAgente = new javax.swing.JDialog();
        pn_createAgente = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lb_cAgente = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_identidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_nombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tf_direccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_celular = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_oficina = new javax.swing.JTextField();
        bt_crearAgente = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jd_createCliente = new javax.swing.JDialog();
        pn_createAgente1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tf_identidadCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tf_nombreCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tf_direccionCliente = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tf_celularCliente = new javax.swing.JTextField();
        bt_crearCliente = new javax.swing.JButton();
        rbtnComprador = new javax.swing.JRadioButton();
        rbtnVendedor = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        lb_clienteTitulo = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        btng_tipoCliente = new javax.swing.ButtonGroup();
        jd_createPropiedad = new javax.swing.JDialog();
        pn_createAdmin1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        tf_nombreMerc = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tf_CiudadMerc = new javax.swing.JTextField();
        bt_crearPropiedadMerc = new javax.swing.JButton();
        tf_direccionMerc = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tf_caracteriticasMerc = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tf_precioMerc = new javax.swing.JTextField();
        sp_propv = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        dc_fppm = new com.toedter.calendar.JDateChooser();
        cb_agentem = new javax.swing.JComboBox<>();
        cb_vendedorm = new javax.swing.JComboBox<>();
        lb_am = new javax.swing.JLabel();
        lb_vm = new javax.swing.JLabel();
        lb_pm = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        tf_imgM = new javax.swing.JTextField();
        bt_imgM = new javax.swing.JButton();
        lb_am1 = new javax.swing.JLabel();
        jd_createPropiedadVendida = new javax.swing.JDialog();
        pn_createAdmin2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        tf_nombreMerc1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tf_CiudadMerc1 = new javax.swing.JTextField();
        bt_crearPropiedadVendida = new javax.swing.JButton();
        tf_direccionMerc1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tf_caracteriticasMerc1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        tf_precioMerc1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        dc_ventapv = new com.toedter.calendar.JDateChooser();
        dc_comprapv = new com.toedter.calendar.JDateChooser();
        sp_cantiHabi = new javax.swing.JSpinner();
        cb_agente = new javax.swing.JComboBox<>();
        cb_vendedor = new javax.swing.JComboBox<>();
        cb_comprador = new javax.swing.JComboBox<>();
        lb_a = new javax.swing.JLabel();
        lb_v = new javax.swing.JLabel();
        lb_c = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        tf_comision = new javax.swing.JTextField();
        lb_pv = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        tf_imgV = new javax.swing.JTextField();
        lb_a1 = new javax.swing.JLabel();
        bt_imgV = new javax.swing.JButton();
        popupCrud = new javax.swing.JPopupMenu();
        mi_modify = new javax.swing.JMenuItem();
        mi_delete = new javax.swing.JMenuItem();
        jd_propImage = new javax.swing.JDialog();
        pn_propImage = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lb_img = new javax.swing.JLabel();
        bt_closeImage = new javax.swing.JButton();
        Fondo = new javax.swing.JPanel();
        Real_Estate = new javax.swing.JPanel();
        Dreams = new javax.swing.JLabel();
        Discover = new javax.swing.JLabel();
        Real = new javax.swing.JLabel();
        Estate = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        DeNuevo = new javax.swing.JLabel();
        Bienvenido = new javax.swing.JLabel();
        User = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        Pass = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        LogIN = new javax.swing.JLabel();
        Log = new javax.swing.JButton();
        Exit = new javax.swing.JLabel();
        Mostrar_Pass = new javax.swing.JCheckBox();
        Login_Background = new javax.swing.JLabel();
        Lines1 = new javax.swing.JLabel();
        Lines2 = new javax.swing.JLabel();
        Lines3 = new javax.swing.JLabel();
        Lines4 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        MainScreen_Admin.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FullBack.setBackground(new java.awt.Color(255, 255, 255));
        FullBack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pn_menuCrud.setBackground(new java.awt.Color(102, 204, 0));
        pn_menuCrud.setForeground(new java.awt.Color(0, 0, 0));

        bt_admin.setBackground(new java.awt.Color(0, 100, 0));
        bt_admin.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        bt_admin.setForeground(new java.awt.Color(255, 255, 255));
        bt_admin.setText("ADMINS");
        bt_admin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_admin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_admin.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bt_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_adminMouseClicked(evt);
            }
        });
        bt_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_adminActionPerformed(evt);
            }
        });

        btn_pMercado.setBackground(new java.awt.Color(0, 100, 0));
        btn_pMercado.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        btn_pMercado.setForeground(new java.awt.Color(255, 255, 255));
        btn_pMercado.setText("PROP. MERCADO");
        btn_pMercado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_pMercado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pMercado.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_pMercado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pMercadoMouseClicked(evt);
            }
        });

        btn_pVenta.setBackground(new java.awt.Color(0, 100, 0));
        btn_pVenta.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        btn_pVenta.setForeground(new java.awt.Color(255, 255, 255));
        btn_pVenta.setText("PROP. VENDIDAS");
        btn_pVenta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_pVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pVenta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_pVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pVentaMouseClicked(evt);
            }
        });

        btn_agentes.setBackground(new java.awt.Color(0, 100, 0));
        btn_agentes.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        btn_agentes.setForeground(new java.awt.Color(255, 255, 255));
        btn_agentes.setText("AGENTES");
        btn_agentes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_agentes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_agentes.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_agentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_agentesMouseClicked(evt);
            }
        });
        btn_agentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agentesActionPerformed(evt);
            }
        });

        btn_vendedor.setBackground(new java.awt.Color(0, 100, 0));
        btn_vendedor.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        btn_vendedor.setForeground(new java.awt.Color(255, 255, 255));
        btn_vendedor.setText("VENDEDORES");
        btn_vendedor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_vendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_vendedor.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_vendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_vendedorMouseClicked(evt);
            }
        });

        btn_vendedor1.setBackground(new java.awt.Color(0, 100, 0));
        btn_vendedor1.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        btn_vendedor1.setForeground(new java.awt.Color(255, 255, 255));
        btn_vendedor1.setText("COMPRADORES");
        btn_vendedor1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_vendedor1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_vendedor1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_vendedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_vendedor1MouseClicked(evt);
            }
        });
        btn_vendedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vendedor1ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/arrow-symbol (black).png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_menuCrudLayout = new javax.swing.GroupLayout(pn_menuCrud);
        pn_menuCrud.setLayout(pn_menuCrudLayout);
        pn_menuCrudLayout.setHorizontalGroup(
            pn_menuCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_menuCrudLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(bt_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_vendedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_agentes, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_pVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_pMercado, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        pn_menuCrudLayout.setVerticalGroup(
            pn_menuCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_menuCrudLayout.createSequentialGroup()
                .addGroup(pn_menuCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_menuCrudLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(pn_menuCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_agentes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_vendedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_pVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_pMercado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pn_menuCrudLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        FullBack.add(pn_menuCrud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 110));

        pn_compradores.setBackground(new java.awt.Color(255, 255, 255));

        tb_compradores.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        tb_compradores.setModel(new tableModel2(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "noIdentidad", "nombre", "direccion", "celular"
            }
        ));
        tb_compradores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_compradoresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_compradores);

        bt_addComprador.setBackground(new java.awt.Color(204, 255, 0));
        bt_addComprador.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        bt_addComprador.setForeground(new java.awt.Color(51, 51, 51));
        bt_addComprador.setText("AÑADIR CLIENTE");
        bt_addComprador.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bt_addComprador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_addComprador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addCompradorMouseClicked(evt);
            }
        });
        bt_addComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addCompradorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_compradoresLayout = new javax.swing.GroupLayout(pn_compradores);
        pn_compradores.setLayout(pn_compradoresLayout);
        pn_compradoresLayout.setHorizontalGroup(
            pn_compradoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_compradoresLayout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_addComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        pn_compradoresLayout.setVerticalGroup(
            pn_compradoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_compradoresLayout.createSequentialGroup()
                .addGroup(pn_compradoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_compradoresLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(bt_addComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_compradoresLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        tb_admin.addTab("tab2", pn_compradores);

        pn_vendedor.setBackground(new java.awt.Color(255, 255, 255));

        tb_vendedores.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        tb_vendedores.setModel(new tableModel2(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "noIdentidad", "nombre", "direccion", "celular"
            }
        ));
        tb_vendedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_vendedoresMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_vendedores);

        bt_addVendedor.setBackground(new java.awt.Color(204, 255, 0));
        bt_addVendedor.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        bt_addVendedor.setForeground(new java.awt.Color(51, 51, 51));
        bt_addVendedor.setText("Añadir Cliente");
        bt_addVendedor.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bt_addVendedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_addVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addVendedorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_vendedorLayout = new javax.swing.GroupLayout(pn_vendedor);
        pn_vendedor.setLayout(pn_vendedorLayout);
        pn_vendedorLayout.setHorizontalGroup(
            pn_vendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_vendedorLayout.createSequentialGroup()
                .addContainerGap(283, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_addVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        pn_vendedorLayout.setVerticalGroup(
            pn_vendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_vendedorLayout.createSequentialGroup()
                .addGroup(pn_vendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_vendedorLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(bt_addVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_vendedorLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        tb_admin.addTab("tab3", pn_vendedor);

        pn_agentes.setBackground(new java.awt.Color(255, 255, 255));

        tb_agentes.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        tb_agentes.setModel(new tableModel2(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "noIdentidad", "nombre", "direccion", "celular", "telefono oficina"
            }
        ));
        tb_agentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_agentesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_agentes);

        bt_addAgente.setBackground(new java.awt.Color(204, 255, 0));
        bt_addAgente.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        bt_addAgente.setForeground(new java.awt.Color(51, 51, 51));
        bt_addAgente.setText("AÑADIR AGENTE");
        bt_addAgente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bt_addAgente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_addAgente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addAgenteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_agentesLayout = new javax.swing.GroupLayout(pn_agentes);
        pn_agentes.setLayout(pn_agentesLayout);
        pn_agentesLayout.setHorizontalGroup(
            pn_agentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_agentesLayout.createSequentialGroup()
                .addContainerGap(284, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_addAgente, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        pn_agentesLayout.setVerticalGroup(
            pn_agentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_agentesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(bt_addAgente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_agentesLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        tb_admin.addTab("tab4", pn_agentes);

        pn_pVenta.setBackground(new java.awt.Color(255, 255, 255));

        tb_propiedadesv.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        tb_propiedadesv.setModel(new tableModel2(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "nombre", "ciudad", "direccion", "dormitorios", "caracteristicas", "precio", "f. publicacion", "f. venta", "id agente", "id vendedor", "id comprador", "comision"
            }
        ));
        tb_propiedadesv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_propiedadesvMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_propiedadesv);

        bt_addPVendida.setBackground(new java.awt.Color(204, 255, 0));
        bt_addPVendida.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        bt_addPVendida.setForeground(new java.awt.Color(51, 51, 51));
        bt_addPVendida.setText("AÑADIR PROPIEDAD VENDIDA");
        bt_addPVendida.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bt_addPVendida.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_addPVendida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addPVendidaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_pVentaLayout = new javax.swing.GroupLayout(pn_pVenta);
        pn_pVenta.setLayout(pn_pVentaLayout);
        pn_pVentaLayout.setHorizontalGroup(
            pn_pVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_pVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_pVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1113, Short.MAX_VALUE)
                    .addGroup(pn_pVentaLayout.createSequentialGroup()
                        .addGap(0, 774, Short.MAX_VALUE)
                        .addComponent(bt_addPVendida, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
        );
        pn_pVentaLayout.setVerticalGroup(
            pn_pVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_pVentaLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(bt_addPVendida, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        tb_admin.addTab("tab5", pn_pVenta);

        pn_pMercado.setBackground(new java.awt.Color(255, 255, 255));

        tb_pMercado.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        tb_pMercado.setModel(new tableModel2(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "nombre", "ciudad", "direccion", "dormitorios", "caracteristicas", "precio", "f. publicacion", "id agente", "id vendedor"
            }
        ));
        tb_pMercado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pMercadoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tb_pMercado);

        bt_pMercado.setBackground(new java.awt.Color(204, 255, 0));
        bt_pMercado.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        bt_pMercado.setForeground(new java.awt.Color(51, 51, 51));
        bt_pMercado.setText("AÑADIR PROPIEDAD MERCADO");
        bt_pMercado.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bt_pMercado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_pMercado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_pMercadoMouseClicked(evt);
            }
        });
        bt_pMercado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pMercadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_pMercadoLayout = new javax.swing.GroupLayout(pn_pMercado);
        pn_pMercado.setLayout(pn_pMercadoLayout);
        pn_pMercadoLayout.setHorizontalGroup(
            pn_pMercadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pMercadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_pMercadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_pMercado, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        pn_pMercadoLayout.setVerticalGroup(
            pn_pMercadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pMercadoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(bt_pMercado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_admin.addTab("tab6", pn_pMercado);

        pn_admins.setBackground(new java.awt.Color(255, 255, 255));

        tb_admins.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        tb_admins.setModel(new tableModel2(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "nombre de usuario", "password", "id"
            }
        ));
        tb_admins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_adminsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_admins);

        bt_addAdmin.setBackground(new java.awt.Color(204, 255, 0));
        bt_addAdmin.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        bt_addAdmin.setForeground(new java.awt.Color(51, 51, 51));
        bt_addAdmin.setText("AÑADIR ADMINISTRADOR");
        bt_addAdmin.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bt_addAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addAdminMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_adminsLayout = new javax.swing.GroupLayout(pn_admins);
        pn_admins.setLayout(pn_adminsLayout);
        pn_adminsLayout.setHorizontalGroup(
            pn_adminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_adminsLayout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addGroup(pn_adminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_adminsLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_adminsLayout.createSequentialGroup()
                        .addComponent(bt_addAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );
        pn_adminsLayout.setVerticalGroup(
            pn_adminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_adminsLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(bt_addAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tb_admin.addTab("tab1", pn_admins);

        FullBack.add(tb_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1170, 600));

        Bitacora_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Bitacora_Panel.setForeground(new java.awt.Color(255, 255, 255));
        Bitacora_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backArrow2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/arrow-symbol (black).png"))); // NOI18N
        backArrow2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backArrow2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backArrow2MouseClicked(evt);
            }
        });
        Bitacora_Panel.add(backArrow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 70));

        tabla_bitacora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "idOperacion", "username", "operacion", "tabla", "id", "fecha", "hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tabla_bitacora);

        Bitacora_Panel.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 1007, 470));

        jPanel8.setBackground(new java.awt.Color(102, 204, 0));
        jPanel8.setForeground(new java.awt.Color(102, 204, 0));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bita.setFont(new java.awt.Font("British Shorthair", 1, 95)); // NOI18N
        Bita.setForeground(new java.awt.Color(0, 0, 0));
        Bita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bita.setText("Bitácora");
        jPanel8.add(Bita, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 450, 150));

        jLabel42.setBackground(new java.awt.Color(0, 102, 0));
        jLabel42.setFont(new java.awt.Font("Neo Sans Std", 1, 50)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 102, 0));
        jLabel42.setText("de Operaciones");
        jPanel8.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 380, -1));

        Bitacora_Panel.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 150));

        FullBack.add(Bitacora_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 680));

        panelVistas2.setBackground(new java.awt.Color(255, 255, 255));
        panelVistas2.setForeground(new java.awt.Color(255, 255, 255));
        panelVistas2.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelVistas2.setMinimumSize(new java.awt.Dimension(1170, 640));
        panelVistas2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 204, 0));

        jLabel44.setFont(new java.awt.Font("British Shorthair", 1, 90)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Vistas");

        backArrow3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/arrow-symbol (black).png"))); // NOI18N
        backArrow3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backArrow3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backArrow3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backArrow3MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(backArrow3)
                .addGap(18, 18, 18)
                .addComponent(jLabel44)
                .addContainerGap(849, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel44)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backArrow3)
                .addGap(53, 53, 53))
        );

        panelVistas2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 150));

        tbVentasPrecio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Precio", "Cantidad"
            }
        ));
        jScrollPane13.setViewportView(tbVentasPrecio);

        btnVentasPrecio.setText("Cargar Ventas por Precio de Propiedad");
        btnVentasPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentasPrecioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout vista5Layout = new javax.swing.GroupLayout(vista5);
        vista5.setLayout(vista5Layout);
        vista5Layout.setHorizontalGroup(
            vista5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista5Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVentasPrecio)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        vista5Layout.setVerticalGroup(
            vista5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vista5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(vista5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnVentasPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedVistas.addTab("VentasxPrecioPropiedad", vista5);

        tbVentasCaracteristicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Propiedad", "Nombre", "Cantidad de Dormitorios", "Tiene Piscina"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(tbVentasCaracteristicas);

        btnVentasCaracteristicas.setText("Cargar Ventas por Caracteristicas");
        btnVentasCaracteristicas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentasCaracteristicasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout vista6Layout = new javax.swing.GroupLayout(vista6);
        vista6.setLayout(vista6Layout);
        vista6Layout.setHorizontalGroup(
            vista6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista6Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnVentasCaracteristicas)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        vista6Layout.setVerticalGroup(
            vista6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(vista6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentasCaracteristicas, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedVistas.addTab("Caracteristicas", vista6);

        tbVentasAgente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero de Identidad", "Nombre", "Cantidad Vendida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbVentasAgente.setColumnSelectionAllowed(true);
        jScrollPane8.setViewportView(tbVentasAgente);

        btnCargarVentasAgente.setText("Cargar las ventas por Agente");
        btnCargarVentasAgente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCargarVentasAgenteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout vista1Layout = new javax.swing.GroupLayout(vista1);
        vista1.setLayout(vista1Layout);
        vista1Layout.setHorizontalGroup(
            vista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnCargarVentasAgente)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        vista1Layout.setVerticalGroup(
            vista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista1Layout.createSequentialGroup()
                .addGroup(vista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vista1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vista1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnCargarVentasAgente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        tabbedVistas.addTab("VentasxAgente", vista1);

        tbVentasUbicacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ciudad", "Cantidad Vendida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tbVentasUbicacion);

        btnVentasUbicacion.setText("Cargar Ventas Por Ubicacion");
        btnVentasUbicacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentasUbicacionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout vista4Layout = new javax.swing.GroupLayout(vista4);
        vista4.setLayout(vista4Layout);
        vista4Layout.setHorizontalGroup(
            vista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vista4Layout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(btnVentasUbicacion)
                .addGap(72, 72, 72))
        );
        vista4Layout.setVerticalGroup(
            vista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista4Layout.createSequentialGroup()
                .addGroup(vista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vista4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnVentasUbicacion))
                    .addGroup(vista4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tabbedVistas.addTab("VentasxUbicación", vista4);

        tbComprasComprador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Numero de Identidad", "Nombre", "Cantidad Comprada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tbComprasComprador);

        btnComprasComprador.setText("Cargar Compras por Comprador");
        btnComprasComprador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnComprasCompradorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout vista3Layout = new javax.swing.GroupLayout(vista3);
        vista3.setLayout(vista3Layout);
        vista3Layout.setHorizontalGroup(
            vista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista3Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnComprasComprador)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        vista3Layout.setVerticalGroup(
            vista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vista3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(vista3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnComprasComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedVistas.addTab("CantidadcomprasxComprador", vista3);

        tbVentasVendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Numero de Identidad", "Nombre", "Cantidad Vendida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tbVentasVendedor);

        btnVentasVendedor.setText("Cargar Ventas por Vendedor");
        btnVentasVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentasVendedorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout vista2Layout = new javax.swing.GroupLayout(vista2);
        vista2.setLayout(vista2Layout);
        vista2Layout.setHorizontalGroup(
            vista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista2Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btnVentasVendedor)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        vista2Layout.setVerticalGroup(
            vista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista2Layout.createSequentialGroup()
                .addGroup(vista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vista2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnVentasVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vista2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        tabbedVistas.addTab("VentasxVendedor", vista2);

        tbPromedioAnual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "noIdentidad", "nombre", "año", "precio promedio", "tiempo promedio (dias)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tbPromedioAnual);
        if (tbPromedioAnual.getColumnModel().getColumnCount() > 0) {
            tbPromedioAnual.getColumnModel().getColumn(0).setResizable(false);
            tbPromedioAnual.getColumnModel().getColumn(1).setResizable(false);
            tbPromedioAnual.getColumnModel().getColumn(2).setResizable(false);
            tbPromedioAnual.getColumnModel().getColumn(3).setResizable(false);
            tbPromedioAnual.getColumnModel().getColumn(4).setResizable(false);
        }

        btnValorVentaAnio.setText("Cargar vista");
        btnValorVentaAnio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnValorVentaAnioMouseClicked(evt);
            }
        });

        jLabel39.setText("Año:");

        javax.swing.GroupLayout vista8Layout = new javax.swing.GroupLayout(vista8);
        vista8.setLayout(vista8Layout);
        vista8Layout.setHorizontalGroup(
            vista8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(vista8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(btnValorVentaAnio))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        vista8Layout.setVerticalGroup(
            vista8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista8Layout.createSequentialGroup()
                .addGroup(vista8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vista8Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnValorVentaAnio))
                    .addGroup(vista8Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        tabbedVistas.addTab("Venta Promedio", vista8);

        tablaAgenteGoat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "noIdentidad", "nombre", "valor total", "año"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(tablaAgenteGoat);
        if (tablaAgenteGoat.getColumnModel().getColumnCount() > 0) {
            tablaAgenteGoat.getColumnModel().getColumn(0).setResizable(false);
            tablaAgenteGoat.getColumnModel().getColumn(1).setResizable(false);
            tablaAgenteGoat.getColumnModel().getColumn(2).setResizable(false);
            tablaAgenteGoat.getColumnModel().getColumn(3).setResizable(false);
        }

        btnventaAnual.setText("Cargar vista");
        btnventaAnual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnventaAnualMouseClicked(evt);
            }
        });

        jLabel40.setText("Año:");

        javax.swing.GroupLayout vista7Layout = new javax.swing.GroupLayout(vista7);
        vista7.setLayout(vista7Layout);
        vista7Layout.setHorizontalGroup(
            vista7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vista7Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(vista7Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addGroup(vista7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addGroup(vista7Layout.createSequentialGroup()
                        .addComponent(yearTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(btnventaAnual)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        vista7Layout.setVerticalGroup(
            vista7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vista7Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jLabel40)
                .addGap(24, 24, 24)
                .addGroup(vista7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnventaAnual))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        tabbedVistas.addTab("Agente con mayor ventas anuales", vista7);

        panelVistas2.add(tabbedVistas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 960, 500));
        tabbedVistas.setVisible(false);

        FullBack.add(panelVistas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Line.setBackground(new java.awt.Color(102, 204, 0));
        Line.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        FullBack.add(Line, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 40));

        Layer.setBackground(new java.awt.Color(102, 204, 0));
        Layer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Indicator.setBackground(new java.awt.Color(0, 0, 0));
        Indicator.setFont(new java.awt.Font("Neo Sans Std", 0, 60)); // NOI18N
        Indicator.setForeground(new java.awt.Color(255, 255, 255));
        Indicator.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Layer.add(Indicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 510, 130));

        FullBack.add(Layer, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 510, 120));

        jPanel3.setBackground(new java.awt.Color(102, 204, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BitacoraPanel.setBackground(new java.awt.Color(102, 204, 0));
        BitacoraPanel.setForeground(new java.awt.Color(102, 255, 51));

        Bitacora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bitacora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/portapapeles.png"))); // NOI18N
        Bitacora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bitacora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BitacoraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BitacoraMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BitacoraMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BitacoraPanelLayout = new javax.swing.GroupLayout(BitacoraPanel);
        BitacoraPanel.setLayout(BitacoraPanelLayout);
        BitacoraPanelLayout.setHorizontalGroup(
            BitacoraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BitacoraPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Bitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        BitacoraPanelLayout.setVerticalGroup(
            BitacoraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BitacoraPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Bitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(BitacoraPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 110, 90));

        OptionPanel.setBackground(new java.awt.Color(102, 204, 0));
        OptionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OptionPanelMouseExited(evt);
            }
        });

        Options.setBackground(new java.awt.Color(255, 255, 255));
        Options.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Options.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Option_Menu.png"))); // NOI18N
        Options.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Options.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OptionsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OptionsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OptionsMouseExited(evt);
            }
        });

        javax.swing.GroupLayout OptionPanelLayout = new javax.swing.GroupLayout(OptionPanel);
        OptionPanel.setLayout(OptionPanelLayout);
        OptionPanelLayout.setHorizontalGroup(
            OptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Options, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        OptionPanelLayout.setVerticalGroup(
            OptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Options, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(OptionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 90));

        MantenimientoPanel.setBackground(new java.awt.Color(102, 204, 0));
        MantenimientoPanel.setForeground(new java.awt.Color(102, 255, 51));

        Mantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Mantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/rebautizar.png"))); // NOI18N
        Mantenimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mantenimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MantenimientoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MantenimientoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MantenimientoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MantenimientoPanelLayout = new javax.swing.GroupLayout(MantenimientoPanel);
        MantenimientoPanel.setLayout(MantenimientoPanelLayout);
        MantenimientoPanelLayout.setHorizontalGroup(
            MantenimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(MantenimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MantenimientoPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        MantenimientoPanelLayout.setVerticalGroup(
            MantenimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(MantenimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MantenimientoPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Mantenimiento)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.add(MantenimientoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 110, 90));

        ReportesPanel.setBackground(new java.awt.Color(102, 204, 0));
        ReportesPanel.setForeground(new java.awt.Color(102, 255, 51));

        Reportes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/buscar.png"))); // NOI18N
        Reportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReportesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReportesMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ReportesPanelLayout = new javax.swing.GroupLayout(ReportesPanel);
        ReportesPanel.setLayout(ReportesPanelLayout);
        ReportesPanelLayout.setHorizontalGroup(
            ReportesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportesPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ReportesPanelLayout.setVerticalGroup(
            ReportesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportesPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(ReportesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 110, 90));

        ExitPanel.setBackground(new java.awt.Color(102, 204, 0));

        Exit2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Exit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/cerrar-sesion.png"))); // NOI18N
        Exit2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Exit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Exit2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Exit2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Exit2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout ExitPanelLayout = new javax.swing.GroupLayout(ExitPanel);
        ExitPanel.setLayout(ExitPanelLayout);
        ExitPanelLayout.setHorizontalGroup(
            ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExitPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exit2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ExitPanelLayout.setVerticalGroup(
            ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExitPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exit2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.add(ExitPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 110, -1));

        FullBack.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 110, 700));

        Real3.setFont(new java.awt.Font("British Shorthair", 0, 150)); // NOI18N
        Real3.setForeground(new java.awt.Color(0, 0, 0));
        Real3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Real3.setText("Real");
        FullBack.add(Real3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 310, 240));

        State.setFont(new java.awt.Font("Neo Sans Std", 1, 120)); // NOI18N
        State.setForeground(new java.awt.Color(0, 0, 0));
        State.setText("Estate");
        FullBack.add(State, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 370, -1));

        ListPanel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ListPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/MainScreen_House2.png"))); // NOI18N
        FullBack.add(ListPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 650, 700));

        SmallHouse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/MainScreen_House2(Small).png"))); // NOI18N
        FullBack.add(SmallHouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 110, 120));

        Administrador.setFont(new java.awt.Font("Neo Sans Std", 1, 80)); // NOI18N
        Administrador.setForeground(new java.awt.Color(204, 204, 204));
        Administrador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Administrador.setText("Administrador");
        Administrador.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        FullBack.add(Administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 590, 110));

        javax.swing.GroupLayout MainScreen_AdminLayout = new javax.swing.GroupLayout(MainScreen_Admin.getContentPane());
        MainScreen_Admin.getContentPane().setLayout(MainScreen_AdminLayout);
        MainScreen_AdminLayout.setHorizontalGroup(
            MainScreen_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FullBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainScreen_AdminLayout.setVerticalGroup(
            MainScreen_AdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FullBack, javax.swing.GroupLayout.PREFERRED_SIZE, 675, Short.MAX_VALUE)
        );

        MainScreen_Others.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_pBought.setBackground(new java.awt.Color(255, 255, 255));
        panel_pBought.setForeground(new java.awt.Color(255, 255, 255));

        jLabel50.setBackground(new java.awt.Color(0, 204, 204));
        jLabel50.setFont(new java.awt.Font("Neo Sans Std", 1, 50)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 204, 204));
        jLabel50.setText("Propiedades Compradas");

        backArrow8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/arrow-symbol (black).png"))); // NOI18N
        backArrow8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backArrow8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backArrow8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backArrow8MouseEntered(evt);
            }
        });

        tb_pBought.setModel(new tableModel2(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "nombre", "ciudad", "direccion", "dormitorios", "caracteristicas", "precio", "f. publicacion", "f. venta", "id agente", "id vendedor", "id comprador", "comision"
            }
        ));
        tb_pBought.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pBoughtMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tb_pBought);

        javax.swing.GroupLayout panel_pBoughtLayout = new javax.swing.GroupLayout(panel_pBought);
        panel_pBought.setLayout(panel_pBoughtLayout);
        panel_pBoughtLayout.setHorizontalGroup(
            panel_pBoughtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pBoughtLayout.createSequentialGroup()
                .addGroup(panel_pBoughtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pBoughtLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backArrow8)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel50))
                    .addGroup(panel_pBoughtLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panel_pBoughtLayout.setVerticalGroup(
            panel_pBoughtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pBoughtLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_pBoughtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backArrow8)
                    .addComponent(jLabel50))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        jPanel2.add(panel_pBought, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        panel_propEnMercadoCliente.setBackground(new java.awt.Color(255, 255, 255));
        panel_propEnMercadoCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backArrow5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/arrow-symbol (black).png"))); // NOI18N
        backArrow5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backArrow5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backArrow5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backArrow5MouseEntered(evt);
            }
        });
        panel_propEnMercadoCliente.add(backArrow5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 70, 60));

        tc_propMercado.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        tc_propMercado.setModel(new tableModel2(
            new Object [][] {

            },
            new String [] {
                "idPropiedad", "nombre", "ciudad", "direccion", "# dormitorios", "caracteristicas", "precio", "fecha publicada", "Agente Id", "Vendedor Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tc_propMercado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tc_propMercadoMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tc_propMercado);

        panel_propEnMercadoCliente.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 980, 450));

        jLabel47.setBackground(new java.awt.Color(0, 204, 204));
        jLabel47.setFont(new java.awt.Font("Neo Sans Std", 1, 50)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 204, 204));
        jLabel47.setText("Propiedades en mercado");
        panel_propEnMercadoCliente.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 670, -1));

        jPanel2.add(panel_propEnMercadoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        panel_propVendida.setBackground(new java.awt.Color(255, 255, 255));

        jLabel48.setBackground(new java.awt.Color(0, 204, 204));
        jLabel48.setFont(new java.awt.Font("Neo Sans Std", 1, 50)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 204, 204));
        jLabel48.setText("Propiedades vendidas");

        backArrow6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/arrow-symbol (black).png"))); // NOI18N
        backArrow6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backArrow6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backArrow6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backArrow6MouseEntered(evt);
            }
        });

        pvtable_vendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idPropiedad", "nombre", "ciudad", "direccion", "# de dormitorios", "caracteristicas", "precio", "publicada", "vendida", "Agente ID", "Vendedor ID", "Comprador ID", "comision"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane19.setViewportView(pvtable_vendedores);

        javax.swing.GroupLayout panel_propVendidaLayout = new javax.swing.GroupLayout(panel_propVendida);
        panel_propVendida.setLayout(panel_propVendidaLayout);
        panel_propVendidaLayout.setHorizontalGroup(
            panel_propVendidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_propVendidaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(backArrow6)
                .addGap(18, 18, 18)
                .addComponent(jLabel48)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_propVendidaLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 1054, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        panel_propVendidaLayout.setVerticalGroup(
            panel_propVendidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_propVendidaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panel_propVendidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backArrow6)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanel2.add(panel_propVendida, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panel_propMercadoVendedor.setBackground(new java.awt.Color(255, 255, 255));

        jLabel49.setBackground(new java.awt.Color(0, 204, 204));
        jLabel49.setFont(new java.awt.Font("Neo Sans Std", 1, 50)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 204, 204));
        jLabel49.setText("Propiedades en mercado");
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });

        backArrow7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/arrow-symbol (black).png"))); // NOI18N
        backArrow7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backArrow7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backArrow7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backArrow7MouseEntered(evt);
            }
        });

        pvtable_mercado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idPropiedad", "nombre", "ciudad", "direccion", "# de dormitorios", "caracteristicas", "precio", "publicada", "Agente ID", "Vendedor ID", "img"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pvtable_mercado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pvtable_mercadoMouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(pvtable_mercado);

        javax.swing.GroupLayout panel_propMercadoVendedorLayout = new javax.swing.GroupLayout(panel_propMercadoVendedor);
        panel_propMercadoVendedor.setLayout(panel_propMercadoVendedorLayout);
        panel_propMercadoVendedorLayout.setHorizontalGroup(
            panel_propMercadoVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_propMercadoVendedorLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(backArrow7)
                .addGap(18, 18, 18)
                .addComponent(jLabel49)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_propMercadoVendedorLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 1054, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        panel_propMercadoVendedorLayout.setVerticalGroup(
            panel_propMercadoVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_propMercadoVendedorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panel_propMercadoVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backArrow7)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanel2.add(panel_propMercadoVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Line1.setBackground(new java.awt.Color(0, 204, 204));
        Line1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(Line1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 40));

        Layer1.setBackground(new java.awt.Color(0, 204, 204));
        Layer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Indicator1.setBackground(new java.awt.Color(0, 0, 0));
        Indicator1.setFont(new java.awt.Font("Neo Sans Std", 0, 60)); // NOI18N
        Indicator1.setForeground(new java.awt.Color(255, 255, 255));
        Indicator1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Layer1.add(Indicator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 510, 130));

        jPanel2.add(Layer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 510, 120));

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        OptionVendedor.setBackground(new java.awt.Color(0, 204, 204));
        OptionVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OptionVendedorMouseExited(evt);
            }
        });

        OptionsVend.setBackground(new java.awt.Color(255, 255, 255));
        OptionsVend.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OptionsVend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Option_Menu.png"))); // NOI18N
        OptionsVend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        OptionsVend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OptionsVendMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OptionsVendMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OptionsVendMouseExited(evt);
            }
        });

        javax.swing.GroupLayout OptionVendedorLayout = new javax.swing.GroupLayout(OptionVendedor);
        OptionVendedor.setLayout(OptionVendedorLayout);
        OptionVendedorLayout.setHorizontalGroup(
            OptionVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionVendedorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(OptionsVend, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        OptionVendedorLayout.setVerticalGroup(
            OptionVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OptionVendedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OptionsVend, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(OptionVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 90));

        ExitPanel1.setBackground(new java.awt.Color(0, 204, 204));

        Exit3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Exit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/cerrar-sesion.png"))); // NOI18N
        Exit3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Exit3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Exit3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Exit3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Exit3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout ExitPanel1Layout = new javax.swing.GroupLayout(ExitPanel1);
        ExitPanel1.setLayout(ExitPanel1Layout);
        ExitPanel1Layout.setHorizontalGroup(
            ExitPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(ExitPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExitPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exit3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ExitPanel1Layout.setVerticalGroup(
            ExitPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(ExitPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExitPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exit3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel4.add(ExitPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 110, -1));

        PropMarket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PropMarket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/portapapeles.png"))); // NOI18N
        PropMarket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PropMarket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PropMarketMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PropMarketMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PropMarketMouseExited(evt);
            }
        });
        jPanel4.add(PropMarket, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 110, 80));

        PropCompradas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PropCompradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/buscar.png"))); // NOI18N
        PropCompradas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PropCompradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PropCompradasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PropCompradasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PropCompradasMouseExited(evt);
            }
        });
        jPanel4.add(PropCompradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 120, 80));

        PropSold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PropSold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/portapapeles.png"))); // NOI18N
        PropSold.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PropSold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PropSoldMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PropSoldMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PropSoldMouseExited(evt);
            }
        });
        jPanel4.add(PropSold, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 110, 70));

        Prop_en_mercado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Prop_en_mercado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/buscar.png"))); // NOI18N
        Prop_en_mercado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Prop_en_mercado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Prop_en_mercadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Prop_en_mercadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Prop_en_mercadoMouseExited(evt);
            }
        });
        jPanel4.add(Prop_en_mercado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 120, 80));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 110, 700));

        Real4.setFont(new java.awt.Font("British Shorthair", 0, 150)); // NOI18N
        Real4.setForeground(new java.awt.Color(0, 0, 0));
        Real4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Real4.setText("Real");
        jPanel2.add(Real4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 310, 240));

        State1.setFont(new java.awt.Font("Neo Sans Std", 1, 120)); // NOI18N
        State1.setForeground(new java.awt.Color(0, 0, 0));
        State1.setText("Estate");
        jPanel2.add(State1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 370, -1));

        ListPanel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ListPanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/MainScreen_House2.png"))); // NOI18N
        jPanel2.add(ListPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 650, 700));

        SmallHouse1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/MainScreen_House2(Small).png"))); // NOI18N
        jPanel2.add(SmallHouse1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 110, 120));

        Administrador1.setFont(new java.awt.Font("Neo Sans Std", 1, 80)); // NOI18N
        Administrador1.setForeground(new java.awt.Color(204, 204, 204));
        Administrador1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Administrador1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(Administrador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 590, 110));

        javax.swing.GroupLayout MainScreen_OthersLayout = new javax.swing.GroupLayout(MainScreen_Others.getContentPane());
        MainScreen_Others.getContentPane().setLayout(MainScreen_OthersLayout);
        MainScreen_OthersLayout.setHorizontalGroup(
            MainScreen_OthersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainScreen_OthersLayout.setVerticalGroup(
            MainScreen_OthersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 675, Short.MAX_VALUE)
        );

        MainScreen_Agente.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        MainScreen_Agente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MainScreen_AgenteMouseClicked(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_agentes.setBackground(new java.awt.Color(255, 255, 255));
        panel_agentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backArrow4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/arrow-symbol (black).png"))); // NOI18N
        backArrow4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backArrow4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backArrow4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backArrow4MouseEntered(evt);
            }
        });
        panel_agentes.add(backArrow4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 70));

        tabbedAgentes.setMaximumSize(new java.awt.Dimension(155, 61));
        tabbedAgentes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedAgentesStateChanged(evt);
            }
        });

        pn_pmAgentes.setBackground(new java.awt.Color(255, 255, 255));

        tb_propmAgentes.setModel(new tableModel2(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "nombre", "ciudad", "direccion", "dormitorios", "caracteristicas", "precio", "f. publicacion", "id agente", "id vendedor"
            }
        ));
        tb_propmAgentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_propmAgentesMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tb_propmAgentes);

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 153, 0));
        jLabel45.setText("Propiedades Asignadas En Mercado");

        javax.swing.GroupLayout pn_pmAgentesLayout = new javax.swing.GroupLayout(pn_pmAgentes);
        pn_pmAgentes.setLayout(pn_pmAgentesLayout);
        pn_pmAgentesLayout.setHorizontalGroup(
            pn_pmAgentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pmAgentesLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(pn_pmAgentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        pn_pmAgentesLayout.setVerticalGroup(
            pn_pmAgentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pmAgentesLayout.createSequentialGroup()
                .addContainerGap(128, Short.MAX_VALUE)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        tabbedAgentes.addTab("Propiedades en mercado", pn_pmAgentes);

        pn_pvAgentes1.setBackground(new java.awt.Color(255, 255, 255));
        pn_pvAgentes1.setForeground(new java.awt.Color(255, 255, 255));
        pn_pvAgentes1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_propvAgentes.setModel(new tableModel2(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "nombre", "ciudad", "direccion", "dormitorios", "caracteristicas", "precio", "f. publicacion", "id agente", "id vendedor"
            }
        ));
        tb_propvAgentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_propvAgentesMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tb_propvAgentes);

        pn_pvAgentes1.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 1000, 630));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 153, 0));
        jLabel46.setText("Propiedades Asignadas Vendidas");
        pn_pvAgentes1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 478, 35));

        tabbedAgentes.addTab("Propiedades vendidas", pn_pvAgentes1);

        panel_agentes.add(tabbedAgentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, -30, 1100, 770));

        jPanel5.add(panel_agentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Line2.setBackground(new java.awt.Color(255, 153, 0));
        Line2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(Line2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 40));

        Layer2.setBackground(new java.awt.Color(255, 153, 0));
        Layer2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Indicator2.setBackground(new java.awt.Color(0, 0, 0));
        Indicator2.setFont(new java.awt.Font("Neo Sans Std", 0, 60)); // NOI18N
        Indicator2.setForeground(new java.awt.Color(255, 255, 255));
        Indicator2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Layer2.add(Indicator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 510, 130));

        jPanel5.add(Layer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 510, 120));

        panel_agente_menu.setBackground(new java.awt.Color(255, 153, 0));
        panel_agente_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        OptionPanel2.setBackground(new java.awt.Color(255, 153, 0));
        OptionPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OptionPanel2MouseExited(evt);
            }
        });

        Options2.setBackground(new java.awt.Color(255, 255, 255));
        Options2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Options2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Option_Menu.png"))); // NOI18N
        Options2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Options2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Options2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Options2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Options2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout OptionPanel2Layout = new javax.swing.GroupLayout(OptionPanel2);
        OptionPanel2.setLayout(OptionPanel2Layout);
        OptionPanel2Layout.setHorizontalGroup(
            OptionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Options2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        OptionPanel2Layout.setVerticalGroup(
            OptionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OptionPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Options2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_agente_menu.add(OptionPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 90));

        Propiedades_Mercado.setBackground(new java.awt.Color(255, 153, 0));
        Propiedades_Mercado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Propiedades_MercadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Propiedades_MercadoMouseExited(evt);
            }
        });

        PropMercado1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PropMercado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/buscar.png"))); // NOI18N
        PropMercado1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PropMercado1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PropMercado1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PropMercado1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PropMercado1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout Propiedades_MercadoLayout = new javax.swing.GroupLayout(Propiedades_Mercado);
        Propiedades_Mercado.setLayout(Propiedades_MercadoLayout);
        Propiedades_MercadoLayout.setHorizontalGroup(
            Propiedades_MercadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Propiedades_MercadoLayout.createSequentialGroup()
                .addComponent(PropMercado1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );
        Propiedades_MercadoLayout.setVerticalGroup(
            Propiedades_MercadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Propiedades_MercadoLayout.createSequentialGroup()
                .addComponent(PropMercado1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        panel_agente_menu.add(Propiedades_Mercado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 110, 90));

        ExitPanel2.setBackground(new java.awt.Color(255, 153, 0));

        Exit4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Exit4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/cerrar-sesion.png"))); // NOI18N
        Exit4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Exit4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Exit4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Exit4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Exit4MouseExited(evt);
            }
        });

        javax.swing.GroupLayout ExitPanel2Layout = new javax.swing.GroupLayout(ExitPanel2);
        ExitPanel2.setLayout(ExitPanel2Layout);
        ExitPanel2Layout.setHorizontalGroup(
            ExitPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(ExitPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExitPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exit4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ExitPanel2Layout.setVerticalGroup(
            ExitPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(ExitPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExitPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Exit4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_agente_menu.add(ExitPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 110, -1));

        PropiedadesVendidas.setBackground(new java.awt.Color(255, 153, 0));
        PropiedadesVendidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PropiedadesVendidasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PropiedadesVendidasMouseExited(evt);
            }
        });

        PropVendidas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PropVendidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/buscar.png"))); // NOI18N
        PropVendidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PropVendidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PropVendidasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PropVendidasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PropVendidasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PropiedadesVendidasLayout = new javax.swing.GroupLayout(PropiedadesVendidas);
        PropiedadesVendidas.setLayout(PropiedadesVendidasLayout);
        PropiedadesVendidasLayout.setHorizontalGroup(
            PropiedadesVendidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PropiedadesVendidasLayout.createSequentialGroup()
                .addComponent(PropVendidas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );
        PropiedadesVendidasLayout.setVerticalGroup(
            PropiedadesVendidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PropiedadesVendidasLayout.createSequentialGroup()
                .addComponent(PropVendidas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        panel_agente_menu.add(PropiedadesVendidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 110, 90));

        jPanel5.add(panel_agente_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 110, 740));

        Real5.setFont(new java.awt.Font("British Shorthair", 0, 150)); // NOI18N
        Real5.setForeground(new java.awt.Color(0, 0, 0));
        Real5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Real5.setText("Real");
        jPanel5.add(Real5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 310, 240));

        State2.setFont(new java.awt.Font("Neo Sans Std", 1, 120)); // NOI18N
        State2.setForeground(new java.awt.Color(0, 0, 0));
        State2.setText("Estate");
        jPanel5.add(State2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 370, -1));

        ListPanel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ListPanel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/MainScreen_House2.png"))); // NOI18N
        jPanel5.add(ListPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 650, 700));

        SmallHouse2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/MainScreen_House2(Small).png"))); // NOI18N
        jPanel5.add(SmallHouse2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 110, 120));

        Administrador2.setFont(new java.awt.Font("Neo Sans Std", 1, 80)); // NOI18N
        Administrador2.setForeground(new java.awt.Color(204, 204, 204));
        Administrador2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Administrador2.setText("Agente");
        Administrador2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(Administrador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 590, 110));

        javax.swing.GroupLayout MainScreen_AgenteLayout = new javax.swing.GroupLayout(MainScreen_Agente.getContentPane());
        MainScreen_Agente.getContentPane().setLayout(MainScreen_AgenteLayout);
        MainScreen_AgenteLayout.setHorizontalGroup(
            MainScreen_AgenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainScreen_AgenteLayout.setVerticalGroup(
            MainScreen_AgenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pn_createAdmin.setBackground(new java.awt.Color(102, 204, 0));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("British Shorthair", 0, 90)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Crear");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 240, -1));

        jLabel2.setBackground(new java.awt.Color(0, 100, 0));
        jLabel2.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 0));
        jLabel2.setText("Nombre de usuario:");
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, 30));

        tf_username.setBackground(new java.awt.Color(255, 255, 255));
        tf_username.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_username.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(tf_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 270, -1));

        jLabel3.setBackground(new java.awt.Color(0, 100, 0));
        jLabel3.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 0));
        jLabel3.setText("Ingrese contraseña:");
        jPanel9.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 200, 30));

        tf_password.setBackground(new java.awt.Color(255, 255, 255));
        tf_password.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_password.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(tf_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 270, -1));

        bt_crearAdmin.setBackground(new java.awt.Color(0, 150, 0));
        bt_crearAdmin.setFont(new java.awt.Font("Neo Sans Std", 1, 24)); // NOI18N
        bt_crearAdmin.setForeground(new java.awt.Color(255, 255, 255));
        bt_crearAdmin.setText("Crear Administrador");
        bt_crearAdmin.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel9.add(bt_crearAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 280, 50));

        jLabel10.setBackground(new java.awt.Color(153, 255, 0));
        jLabel10.setFont(new java.awt.Font("Neo Sans Std", 1, 80)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 204, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Administrador");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 610, -1));

        javax.swing.GroupLayout pn_createAdminLayout = new javax.swing.GroupLayout(pn_createAdmin);
        pn_createAdmin.setLayout(pn_createAdminLayout);
        pn_createAdminLayout.setHorizontalGroup(
            pn_createAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_createAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_createAdminLayout.setVerticalGroup(
            pn_createAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_createAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jd_createAdminLayout = new javax.swing.GroupLayout(jd_createAdmin.getContentPane());
        jd_createAdmin.getContentPane().setLayout(jd_createAdminLayout);
        jd_createAdminLayout.setHorizontalGroup(
            jd_createAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_createAdminLayout.setVerticalGroup(
            jd_createAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pn_createAgente.setBackground(new java.awt.Color(102, 204, 0));
        pn_createAgente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_cAgente.setFont(new java.awt.Font("British Shorthair", 0, 90)); // NOI18N
        lb_cAgente.setForeground(new java.awt.Color(0, 0, 0));
        lb_cAgente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_cAgente.setText("Crear");
        lb_cAgente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(lb_cAgente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 590, -1));

        jLabel17.setBackground(new java.awt.Color(153, 255, 0));
        jLabel17.setFont(new java.awt.Font("Neo Sans Std", 1, 80)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 204, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Agente");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 570, -1));

        jLabel5.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Identidad:");
        jPanel10.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 120, -1));

        tf_identidad.setBackground(new java.awt.Color(255, 255, 255));
        tf_identidad.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        jPanel10.add(tf_identidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 410, -1));

        jLabel6.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nombre:");
        jPanel10.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 90, -1));

        tf_nombre.setBackground(new java.awt.Color(255, 255, 255));
        tf_nombre.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        jPanel10.add(tf_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 420, -1));

        jLabel7.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Direccion:");
        jPanel10.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 100, -1));

        tf_direccion.setBackground(new java.awt.Color(255, 255, 255));
        tf_direccion.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        jPanel10.add(tf_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 260, 410, -1));

        jLabel8.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 204, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Celular:");
        jPanel10.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 300, 90, -1));

        tf_celular.setBackground(new java.awt.Color(255, 255, 255));
        tf_celular.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        jPanel10.add(tf_celular, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 300, 430, -1));

        jLabel9.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Telefono Oficina:");
        jPanel10.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        tf_oficina.setBackground(new java.awt.Color(255, 255, 255));
        tf_oficina.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        jPanel10.add(tf_oficina, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 330, -1));

        bt_crearAgente.setBackground(new java.awt.Color(0, 153, 0));
        bt_crearAgente.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        bt_crearAgente.setForeground(new java.awt.Color(255, 255, 255));
        bt_crearAgente.setText("Crear Agente");
        bt_crearAgente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bt_crearAgente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_crearAgenteMouseClicked(evt);
            }
        });
        jPanel10.add(bt_crearAgente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 240, 40));

        pn_createAgente.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 590, 440));

        jLabel23.setFont(new java.awt.Font("British Shorthair", 0, 90)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Crear");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pn_createAgente.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 240, -1));

        jLabel36.setBackground(new java.awt.Color(153, 255, 0));
        jLabel36.setFont(new java.awt.Font("Neo Sans Std", 1, 80)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 204, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Administrador");
        pn_createAgente.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 610, -1));

        javax.swing.GroupLayout jd_createAgenteLayout = new javax.swing.GroupLayout(jd_createAgente.getContentPane());
        jd_createAgente.getContentPane().setLayout(jd_createAgenteLayout);
        jd_createAgenteLayout.setHorizontalGroup(
            jd_createAgenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAgente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_createAgenteLayout.setVerticalGroup(
            jd_createAgenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAgente, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        pn_createAgente1.setBackground(new java.awt.Color(102, 204, 0));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 0));
        jLabel11.setText("Identidad:");
        jPanel11.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        tf_identidadCliente.setBackground(new java.awt.Color(255, 255, 255));
        tf_identidadCliente.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_identidadCliente.setForeground(new java.awt.Color(102, 102, 102));
        jPanel11.add(tf_identidadCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 380, -1));

        jLabel12.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 204, 0));
        jLabel12.setText("Nombre:");
        jPanel11.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        tf_nombreCliente.setBackground(new java.awt.Color(255, 255, 255));
        tf_nombreCliente.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_nombreCliente.setForeground(new java.awt.Color(102, 102, 102));
        jPanel11.add(tf_nombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 390, -1));

        jLabel13.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 204, 0));
        jLabel13.setText("Direccion:");
        jPanel11.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        tf_direccionCliente.setBackground(new java.awt.Color(255, 255, 255));
        tf_direccionCliente.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_direccionCliente.setForeground(new java.awt.Color(102, 102, 102));
        jPanel11.add(tf_direccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 380, -1));

        jLabel14.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 204, 0));
        jLabel14.setText("Celular:");
        jPanel11.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        tf_celularCliente.setBackground(new java.awt.Color(255, 255, 255));
        tf_celularCliente.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_celularCliente.setForeground(new java.awt.Color(102, 102, 102));
        tf_celularCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_celularClienteActionPerformed(evt);
            }
        });
        jPanel11.add(tf_celularCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 400, -1));

        bt_crearCliente.setBackground(new java.awt.Color(0, 153, 0));
        bt_crearCliente.setFont(new java.awt.Font("Neo Sans Std", 1, 24)); // NOI18N
        bt_crearCliente.setForeground(new java.awt.Color(255, 255, 255));
        bt_crearCliente.setText("Crear Cliente");
        bt_crearCliente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        bt_crearCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_crearClienteMouseClicked(evt);
            }
        });
        bt_crearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_crearClienteActionPerformed(evt);
            }
        });
        jPanel11.add(bt_crearCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 200, 40));

        btng_tipoCliente.add(rbtnComprador);
        rbtnComprador.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        rbtnComprador.setForeground(new java.awt.Color(102, 102, 102));
        rbtnComprador.setText("Comprador");
        rbtnComprador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel11.add(rbtnComprador, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, -1, -1));

        btng_tipoCliente.add(rbtnVendedor);
        rbtnVendedor.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        rbtnVendedor.setForeground(new java.awt.Color(102, 102, 102));
        rbtnVendedor.setText("Vendedor");
        rbtnVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel11.add(rbtnVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, -1, -1));

        jLabel16.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 204, 0));
        jLabel16.setText("Tipo de cliente:");
        jPanel11.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        Regresar.setBackground(new java.awt.Color(0, 153, 0));
        Regresar.setForeground(new java.awt.Color(255, 255, 255));
        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });
        jPanel11.add(Regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        lb_clienteTitulo.setFont(new java.awt.Font("British Shorthair", 0, 90)); // NOI18N
        lb_clienteTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lb_clienteTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_clienteTitulo.setText("Crear");
        lb_clienteTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(lb_clienteTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, -1));

        jLabel37.setBackground(new java.awt.Color(153, 255, 0));
        jLabel37.setFont(new java.awt.Font("Neo Sans Std", 1, 80)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 204, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Cliente");
        jPanel11.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 310, -1));

        javax.swing.GroupLayout pn_createAgente1Layout = new javax.swing.GroupLayout(pn_createAgente1);
        pn_createAgente1.setLayout(pn_createAgente1Layout);
        pn_createAgente1Layout.setHorizontalGroup(
            pn_createAgente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_createAgente1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_createAgente1Layout.setVerticalGroup(
            pn_createAgente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_createAgente1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jd_createClienteLayout = new javax.swing.GroupLayout(jd_createCliente.getContentPane());
        jd_createCliente.getContentPane().setLayout(jd_createClienteLayout);
        jd_createClienteLayout.setHorizontalGroup(
            jd_createClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAgente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_createClienteLayout.setVerticalGroup(
            jd_createClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAgente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pn_createAdmin1.setBackground(new java.awt.Color(102, 204, 0));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setBackground(new java.awt.Color(0, 204, 0));
        jLabel18.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 204, 0));
        jLabel18.setText("Nombre de la Propiedad:");
        jPanel12.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        tf_nombreMerc.setBackground(new java.awt.Color(255, 255, 255));
        tf_nombreMerc.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_nombreMerc.setForeground(new java.awt.Color(0, 0, 0));
        jPanel12.add(tf_nombreMerc, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 390, -1));

        jLabel19.setBackground(new java.awt.Color(0, 204, 0));
        jLabel19.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 204, 0));
        jLabel19.setText("Ciudad: ");
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        tf_CiudadMerc.setBackground(new java.awt.Color(255, 255, 255));
        tf_CiudadMerc.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_CiudadMerc.setForeground(new java.awt.Color(0, 0, 0));
        tf_CiudadMerc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_CiudadMercActionPerformed(evt);
            }
        });
        jPanel12.add(tf_CiudadMerc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 550, -1));

        bt_crearPropiedadMerc.setBackground(new java.awt.Color(0, 153, 0));
        bt_crearPropiedadMerc.setFont(new java.awt.Font("Neo Sans Std", 1, 18)); // NOI18N
        bt_crearPropiedadMerc.setForeground(new java.awt.Color(255, 255, 255));
        bt_crearPropiedadMerc.setText("Crear Propiedad");
        bt_crearPropiedadMerc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_crearPropiedadMercMouseClicked(evt);
            }
        });
        jPanel12.add(bt_crearPropiedadMerc, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 560, 230, 43));

        tf_direccionMerc.setBackground(new java.awt.Color(255, 255, 255));
        tf_direccionMerc.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_direccionMerc.setForeground(new java.awt.Color(0, 0, 0));
        tf_direccionMerc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_direccionMercActionPerformed(evt);
            }
        });
        jPanel12.add(tf_direccionMerc, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 530, -1));

        jLabel20.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 204, 0));
        jLabel20.setText("Direccion: ");
        jPanel12.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel21.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 204, 0));
        jLabel21.setText("Caracteristicas:");
        jPanel12.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 30));

        tf_caracteriticasMerc.setBackground(new java.awt.Color(255, 255, 255));
        tf_caracteriticasMerc.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_caracteriticasMerc.setForeground(new java.awt.Color(0, 0, 0));
        jPanel12.add(tf_caracteriticasMerc, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 480, -1));

        jLabel22.setForeground(new java.awt.Color(0, 204, 0));
        jPanel12.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, -1, -1));

        jLabel24.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 204, 0));
        jLabel24.setText("Precio:");
        jPanel12.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel25.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 204, 0));
        jLabel25.setText("Cantidad de Habitaciones:");
        jPanel12.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        tf_precioMerc.setBackground(new java.awt.Color(255, 255, 255));
        tf_precioMerc.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_precioMerc.setForeground(new java.awt.Color(0, 0, 0));
        jPanel12.add(tf_precioMerc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 560, -1));

        sp_propv.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        sp_propv.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jPanel12.add(sp_propv, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 370, 370, -1));

        jLabel35.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 204, 0));
        jLabel35.setText("Fecha publicada:");
        jPanel12.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));
        jPanel12.add(dc_fppm, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 460, 30));

        cb_agentem.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        cb_agentem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel12.add(cb_agentem, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 207, -1));

        cb_vendedorm.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        cb_vendedorm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel12.add(cb_vendedorm, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, 207, -1));

        lb_am.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        lb_am.setForeground(new java.awt.Color(0, 204, 0));
        lb_am.setText("Imagen:");
        jPanel12.add(lb_am, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, -1, 50));

        lb_vm.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        lb_vm.setForeground(new java.awt.Color(0, 204, 0));
        lb_vm.setText("Vendedor:");
        jPanel12.add(lb_vm, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, -1, 50));

        lb_pm.setFont(new java.awt.Font("British Shorthair", 0, 95)); // NOI18N
        lb_pm.setForeground(new java.awt.Color(0, 0, 0));
        lb_pm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_pm.setText("Crear");
        lb_pm.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel12.add(lb_pm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, -1));

        jLabel41.setBackground(new java.awt.Color(153, 255, 0));
        jLabel41.setFont(new java.awt.Font("Neo Sans Std", 1, 60)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(102, 204, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Propiedad en Mercado");
        jPanel12.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 670, -1));

        tf_imgM.setBackground(new java.awt.Color(255, 255, 255));
        tf_imgM.setForeground(new java.awt.Color(0, 0, 0));
        jPanel12.add(tf_imgM, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, 250, -1));

        bt_imgM.setText("...");
        bt_imgM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_imgMMouseClicked(evt);
            }
        });
        jPanel12.add(bt_imgM, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 530, -1, -1));

        lb_am1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        lb_am1.setForeground(new java.awt.Color(0, 204, 0));
        lb_am1.setText("Agente:");
        jPanel12.add(lb_am1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, -1, 50));

        javax.swing.GroupLayout pn_createAdmin1Layout = new javax.swing.GroupLayout(pn_createAdmin1);
        pn_createAdmin1.setLayout(pn_createAdmin1Layout);
        pn_createAdmin1Layout.setHorizontalGroup(
            pn_createAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_createAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_createAdmin1Layout.setVerticalGroup(
            pn_createAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_createAdmin1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jd_createPropiedadLayout = new javax.swing.GroupLayout(jd_createPropiedad.getContentPane());
        jd_createPropiedad.getContentPane().setLayout(jd_createPropiedadLayout);
        jd_createPropiedadLayout.setHorizontalGroup(
            jd_createPropiedadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_createPropiedadLayout.setVerticalGroup(
            jd_createPropiedadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pn_createAdmin2.setBackground(new java.awt.Color(102, 204, 0));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setBackground(new java.awt.Color(0, 204, 0));
        jLabel26.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 204, 0));
        jLabel26.setText("Nombre de la Propiedad:");
        jPanel13.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        tf_nombreMerc1.setBackground(new java.awt.Color(255, 255, 255));
        tf_nombreMerc1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_nombreMerc1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel13.add(tf_nombreMerc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 360, -1));

        jLabel27.setBackground(new java.awt.Color(0, 204, 0));
        jLabel27.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 204, 0));
        jLabel27.setText("Ciudad: ");
        jPanel13.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        tf_CiudadMerc1.setBackground(new java.awt.Color(255, 255, 255));
        tf_CiudadMerc1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_CiudadMerc1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel13.add(tf_CiudadMerc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 520, -1));

        bt_crearPropiedadVendida.setBackground(new java.awt.Color(0, 153, 0));
        bt_crearPropiedadVendida.setFont(new java.awt.Font("Neo Sans Std", 1, 24)); // NOI18N
        bt_crearPropiedadVendida.setForeground(new java.awt.Color(255, 255, 255));
        bt_crearPropiedadVendida.setText("Crear Propiedad");
        bt_crearPropiedadVendida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_crearPropiedadVendidaMouseClicked(evt);
            }
        });
        bt_crearPropiedadVendida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_crearPropiedadVendidaActionPerformed(evt);
            }
        });
        jPanel13.add(bt_crearPropiedadVendida, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 630, 230, 43));

        tf_direccionMerc1.setBackground(new java.awt.Color(255, 255, 255));
        tf_direccionMerc1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_direccionMerc1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel13.add(tf_direccionMerc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 500, -1));

        jLabel28.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 204, 0));
        jLabel28.setText("Direccion: ");
        jPanel13.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel29.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 204, 0));
        jLabel29.setText("Caracteristicas:");
        jPanel13.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        tf_caracteriticasMerc1.setBackground(new java.awt.Color(255, 255, 255));
        tf_caracteriticasMerc1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_caracteriticasMerc1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel13.add(tf_caracteriticasMerc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 450, -1));

        jLabel30.setForeground(new java.awt.Color(0, 204, 0));
        jPanel13.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, -1, -1));

        jLabel31.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 204, 0));
        jLabel31.setText("Precio:");
        jPanel13.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        jLabel32.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 204, 0));
        jLabel32.setText("Cantidad de Habitaciones:");
        jPanel13.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        tf_precioMerc1.setBackground(new java.awt.Color(255, 255, 255));
        tf_precioMerc1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_precioMerc1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel13.add(tf_precioMerc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 530, -1));

        jLabel33.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 204, 0));
        jLabel33.setText("Fecha de Venta: ");
        jPanel13.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        jLabel34.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 204, 0));
        jLabel34.setText("Fecha de compra: ");
        jPanel13.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, 40));
        jPanel13.add(dc_ventapv, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 440, -1));
        jPanel13.add(dc_comprapv, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 430, 20));

        sp_cantiHabi.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        sp_cantiHabi.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        sp_cantiHabi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel13.add(sp_cantiHabi, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 340, 30));

        cb_agente.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        cb_agente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel13.add(cb_agente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 139, -1));

        cb_vendedor.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        cb_vendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel13.add(cb_vendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 139, -1));

        cb_comprador.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        cb_comprador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_comprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_compradorActionPerformed(evt);
            }
        });
        jPanel13.add(cb_comprador, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, 139, -1));

        lb_a.setBackground(new java.awt.Color(0, 204, 0));
        lb_a.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        lb_a.setForeground(new java.awt.Color(0, 204, 0));
        lb_a.setText("Imagen:");
        jPanel13.add(lb_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 590, -1, 40));

        lb_v.setBackground(new java.awt.Color(0, 204, 0));
        lb_v.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        lb_v.setForeground(new java.awt.Color(0, 204, 0));
        lb_v.setText("Vendedor:");
        jPanel13.add(lb_v, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, -1, 40));

        lb_c.setBackground(new java.awt.Color(0, 204, 0));
        lb_c.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        lb_c.setForeground(new java.awt.Color(0, 204, 0));
        lb_c.setText("Comprador:");
        jPanel13.add(lb_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 520, -1, 40));

        jLabel38.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 204, 0));
        jLabel38.setText("Comision:");
        jPanel13.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        tf_comision.setBackground(new java.awt.Color(255, 255, 255));
        tf_comision.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        tf_comision.setForeground(new java.awt.Color(0, 0, 0));
        jPanel13.add(tf_comision, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 500, -1));

        lb_pv.setFont(new java.awt.Font("British Shorthair", 0, 95)); // NOI18N
        lb_pv.setForeground(new java.awt.Color(0, 0, 0));
        lb_pv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_pv.setText("Crear");
        lb_pv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel13.add(lb_pv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, -1));

        jLabel43.setBackground(new java.awt.Color(153, 255, 0));
        jLabel43.setFont(new java.awt.Font("Neo Sans Std", 1, 60)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(102, 204, 0));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Propiedad Vendida");
        jPanel13.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 560, -1));

        tf_imgV.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.add(tf_imgV, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 600, 290, -1));

        lb_a1.setBackground(new java.awt.Color(0, 204, 0));
        lb_a1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        lb_a1.setForeground(new java.awt.Color(0, 204, 0));
        lb_a1.setText("Agente:");
        jPanel13.add(lb_a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, 40));

        bt_imgV.setText("...");
        bt_imgV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_imgVMouseClicked(evt);
            }
        });
        jPanel13.add(bt_imgV, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 600, -1, -1));

        javax.swing.GroupLayout pn_createAdmin2Layout = new javax.swing.GroupLayout(pn_createAdmin2);
        pn_createAdmin2.setLayout(pn_createAdmin2Layout);
        pn_createAdmin2Layout.setHorizontalGroup(
            pn_createAdmin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_createAdmin2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_createAdmin2Layout.setVerticalGroup(
            pn_createAdmin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_createAdmin2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jd_createPropiedadVendidaLayout = new javax.swing.GroupLayout(jd_createPropiedadVendida.getContentPane());
        jd_createPropiedadVendida.getContentPane().setLayout(jd_createPropiedadVendidaLayout);
        jd_createPropiedadVendidaLayout.setHorizontalGroup(
            jd_createPropiedadVendidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAdmin2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_createPropiedadVendidaLayout.setVerticalGroup(
            jd_createPropiedadVendidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_createAdmin2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mi_modify.setText("jMenuItem1");
        mi_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_modifyActionPerformed(evt);
            }
        });
        popupCrud.add(mi_modify);

        mi_delete.setText("jMenuItem2");
        mi_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_deleteActionPerformed(evt);
            }
        });
        popupCrud.add(mi_delete);

        pn_propImage.setBackground(new java.awt.Color(102, 204, 0));

        jPanel6.setBackground(new java.awt.Color(102, 153, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_img, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        bt_closeImage.setText("Ok");
        bt_closeImage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_closeImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_closeImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_propImageLayout = new javax.swing.GroupLayout(pn_propImage);
        pn_propImage.setLayout(pn_propImageLayout);
        pn_propImageLayout.setHorizontalGroup(
            pn_propImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_propImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pn_propImageLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(bt_closeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(183, Short.MAX_VALUE))
        );
        pn_propImageLayout.setVerticalGroup(
            pn_propImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_propImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_closeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jd_propImageLayout = new javax.swing.GroupLayout(jd_propImage.getContentPane());
        jd_propImage.getContentPane().setLayout(jd_propImageLayout);
        jd_propImageLayout.setHorizontalGroup(
            jd_propImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_propImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_propImageLayout.setVerticalGroup(
            jd_propImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_propImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Real_Estate.setBackground(new java.awt.Color(255, 255, 255));
        Real_Estate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Dreams.setFont(new java.awt.Font("British Shorthair", 0, 80)); // NOI18N
        Dreams.setForeground(new java.awt.Color(0, 0, 0));
        Dreams.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Dreams.setText("dreams");
        Real_Estate.add(Dreams, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 280, -1));

        Discover.setFont(new java.awt.Font("Neo Sans Std", 0, 30)); // NOI18N
        Discover.setForeground(new java.awt.Color(0, 153, 51));
        Discover.setText("Discover the House of your...");
        Real_Estate.add(Discover, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        Real.setFont(new java.awt.Font("British Shorthair", 1, 110)); // NOI18N
        Real.setForeground(new java.awt.Color(0, 0, 0));
        Real.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Real.setText("Real");
        Real_Estate.add(Real, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 240, -1));

        Estate.setFont(new java.awt.Font("Neo Sans Std", 1, 100)); // NOI18N
        Estate.setForeground(new java.awt.Color(102, 204, 0));
        Estate.setText("Estate");
        Real_Estate.add(Estate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        Real_Estate.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 550, 20));

        Fondo.add(Real_Estate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 590, 380));

        DeNuevo.setFont(new java.awt.Font("British Shorthair", 0, 70)); // NOI18N
        DeNuevo.setForeground(new java.awt.Color(51, 153, 0));
        DeNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DeNuevo.setText("de nuevo!");
        Fondo.add(DeNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 380, 120));

        Bienvenido.setBackground(new java.awt.Color(0, 0, 0));
        Bienvenido.setFont(new java.awt.Font("Neo Sans Std", 1, 48)); // NOI18N
        Bienvenido.setForeground(new java.awt.Color(51, 51, 51));
        Bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bienvenido.setText("BIENVENIDO");
        Fondo.add(Bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 390, 70));

        User.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        User.setForeground(new java.awt.Color(51, 51, 51));
        User.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        User.setText("USERNAME");
        Fondo.add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 140, 40));

        Username.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        Username.setText("Ingrese su Usuario");
        Username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UsernameMouseClicked(evt);
            }
        });
        Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameActionPerformed(evt);
            }
        });
        Username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsernameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UsernameKeyReleased(evt);
            }
        });
        Fondo.add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 330, 40));

        Pass.setFont(new java.awt.Font("Neo Sans Std Light", 0, 24)); // NOI18N
        Pass.setForeground(new java.awt.Color(51, 51, 51));
        Pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Pass.setText("PASSWORD");
        Fondo.add(Pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 140, 40));

        jPasswordField1.setFont(new java.awt.Font("Neo Sans Std Light", 0, 18)); // NOI18N
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyReleased(evt);
            }
        });
        Fondo.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 330, 40));

        LogIN.setBackground(new java.awt.Color(0, 0, 0));
        LogIN.setFont(new java.awt.Font("Neo Sans Std", 1, 28)); // NOI18N
        LogIN.setForeground(new java.awt.Color(0, 0, 0));
        LogIN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LogIN.setText("Log In");
        LogIN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Fondo.add(LogIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 420, 100, 50));

        Log.setBackground(new java.awt.Color(102, 204, 0));
        Log.setFont(new java.awt.Font("Neo Sans Std", 1, 24)); // NOI18N
        Log.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Log.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Log.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogMouseClicked(evt);
            }
        });
        Log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogActionPerformed(evt);
            }
        });
        Log.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LogKeyPressed(evt);
            }
        });
        Fondo.add(Log, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, 330, 40));

        Exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/cerrar-sesion.png"))); // NOI18N
        Exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExitMouseExited(evt);
            }
        });
        Fondo.add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 500, -1, -1));

        Mostrar_Pass.setFont(new java.awt.Font("Neo Sans Std Light", 0, 14)); // NOI18N
        Mostrar_Pass.setForeground(new java.awt.Color(102, 102, 102));
        Mostrar_Pass.setText("Mostrar Contraseña");
        Mostrar_Pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar_PassActionPerformed(evt);
            }
        });
        Fondo.add(Mostrar_Pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 370, 150, 30));

        Login_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Login_Back.png"))); // NOI18N
        Login_Background.setOpaque(true);
        Fondo.add(Login_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 390, 400));

        Lines1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Abstract Lines.png"))); // NOI18N
        Fondo.add(Lines1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 660, 100));

        Lines2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Abstract Lines.png"))); // NOI18N
        Fondo.add(Lines2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, 660, 100));

        Lines3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Abstract Lines.png"))); // NOI18N
        Fondo.add(Lines3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-230, 480, 660, 100));

        Lines4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Abstract Lines.png"))); // NOI18N
        Fondo.add(Lines4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-280, 0, 720, 100));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Fondo.png"))); // NOI18N
        Fondo.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 580));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LogActionPerformed

    private void UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameActionPerformed

    }//GEN-LAST:event_UsernameActionPerformed

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked
        jPasswordField1.setText("");
    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void UsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsernameMouseClicked
        Username.setText("");
    }//GEN-LAST:event_UsernameMouseClicked

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Desea salir del Programa?", "WARNING!", JOptionPane.WARNING_MESSAGE);

        if (showConfirmDialog == 0) {
            admin.logout();
            System.exit(0);
        }
    }//GEN-LAST:event_ExitMouseClicked

    private void ExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseEntered

    }//GEN-LAST:event_ExitMouseEntered

    private void ExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseExited

    }//GEN-LAST:event_ExitMouseExited

    private void LogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogMouseClicked
        String user = Username.getText();
        String pass = jPasswordField1.getText();
        PropMarket.setVisible(false);
        PropSold.setVisible(false);
        //Vistas.setVisible(false);
        if (user.equals("")) {
            JOptionPane.showMessageDialog(null, "Inserte su Nombre de Usuario Correctamente");
        } else if (pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Inserte su Contraseña de Usuario Correctamente");
        } else {

            String[] creds = admin.login(user, pass);

            if (creds[1].equals("nada")) {
                JOptionPane.showMessageDialog(this, "Este usuario no existe");
            } else if (creds[0].equals("nada")) {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
            } else {
                currentusername = user;
                currentid = admin.getId(username);

                if (creds[0].equals("admin")) {
                    MainScreen_Admin.setVisible(true);
                    this.setVisible(false);
                } else if (creds[0].equals("comprador")) {
                    cv = 1;
                    MainScreen_Others.setVisible(true);
                    this.setVisible(false);

                } else if (creds[0].equals("vendedor")) {

                    MainScreen_Others.setVisible(true);
                    cv = 2;
                    this.setVisible(false);
                } else if (creds[0].equals("cv")) {

                    MainScreen_Others.setVisible(true);
                    this.setVisible(false);
                    cv = 3;
                } else if (creds[0].equals("agente")) {
                    MainScreen_Agente.setVisible(true);
                    this.setVisible(false);
                }
            }

            //JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorectos \nIntentelo de Nuevo", "Incorrect User or Pass", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_LogMouseClicked

    private void Mostrar_PassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar_PassActionPerformed
        if (Mostrar_Pass.isSelected()) {
            jPasswordField1.setEchoChar((char) 0);
        } else {
            jPasswordField1.setEchoChar('*');
        }
    }//GEN-LAST:event_Mostrar_PassActionPerformed

    private void OptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsMouseClicked
        boolean isVisible = Mantenimiento.isVisible() || Bitacora.isVisible() || Reportes.isVisible();

        Mantenimiento.setVisible(!isVisible);
        Bitacora.setVisible(!isVisible);
        Reportes.setVisible(!isVisible);
        //Vistas.setVisible(!isVisible);
    }//GEN-LAST:event_OptionsMouseClicked

    private void OptionsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsMouseEntered
        OptionPanel.setBackground(new Color(0, 100, 0));
        Indicator.setText("OPCIONES");
    }//GEN-LAST:event_OptionsMouseEntered

    private void OptionsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsMouseExited
        OptionPanel.setBackground(null);
        Indicator.setText("");

    }//GEN-LAST:event_OptionsMouseExited

    private void OptionPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionPanelMouseExited
        //OptionPanel.setBackground(null);
    }//GEN-LAST:event_OptionPanelMouseExited

    private void MantenimientoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MantenimientoMouseEntered
        MantenimientoPanel.setBackground(new Color(0, 100, 0));
        Indicator.setText("MANTENIMIENTO");
    }//GEN-LAST:event_MantenimientoMouseEntered

    private void Exit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit2MouseClicked
        int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Desea salir de la sesion?", "WARNING!", JOptionPane.WARNING_MESSAGE);
        Username.setText("Ingrese su Usuario");
        jPasswordField1.setText("");
        if (showConfirmDialog == 0) {
            this.setVisible(true);
            MainScreen_Admin.setVisible(false);
            reset();
        }
    }//GEN-LAST:event_Exit2MouseClicked

    private void Exit2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit2MouseEntered
        ExitPanel.setBackground(Color.RED);
        Indicator.setText("SALIR");

    }//GEN-LAST:event_Exit2MouseEntered

    private void Exit2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit2MouseExited
        ExitPanel.setBackground(null);
        Indicator.setText("");

    }//GEN-LAST:event_Exit2MouseExited

    private void jPasswordField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String user = Username.getText();
            String pass = jPasswordField1.getText();
            PropMarket.setVisible(false);
            PropSold.setVisible(false);
            //Vistas.setVisible(false);
            if (user.equals("")) {
                JOptionPane.showMessageDialog(null, "Inserte su Nombre de Usuario Correctamente");
            } else if (pass.equals("")) {
                JOptionPane.showMessageDialog(null, "Inserte su Contraseña de Usuario Correctamente");
            } else {

                String[] creds = admin.login(user, pass);

                if (creds[1].equals("nada")) {
                    JOptionPane.showMessageDialog(this, "Este usuario no existe");
                } else if (creds[0].equals("nada")) {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
                } else {
                    currentusername = user;
                    currentid = admin.getId(user);

                    if (creds[0].equals("admin")) {
                        MainScreen_Admin.setVisible(true);
                        this.setVisible(false);
                    } else if (creds[0].equals("comprador")) {
                        cv = 1;
                        MainScreen_Others.setVisible(true);
                        this.setVisible(false);

                    } else if (creds[0].equals("vendedor")) {

                        MainScreen_Others.setVisible(true);
                        cv = 2;
                        this.setVisible(false);
                    } else if (creds[0].equals("cv")) {

                        MainScreen_Others.setVisible(true);
                        this.setVisible(false);
                        cv = 3;
                    } else if (creds[0].equals("agente")) {
                        MainScreen_Agente.setVisible(true);
                        this.setVisible(false);
                    }
                }
            }
        }
    }//GEN-LAST:event_jPasswordField1KeyReleased

    private void MantenimientoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MantenimientoMouseExited
        MantenimientoPanel.setBackground(null);
        Indicator.setText("");
    }//GEN-LAST:event_MantenimientoMouseExited

    private void ReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportesMouseEntered
        ReportesPanel.setBackground(new Color(0, 100, 0));
        Indicator.setText("REPORTES");
    }//GEN-LAST:event_ReportesMouseEntered

    private void ReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportesMouseExited
        ReportesPanel.setBackground(null);
        Indicator.setText("");
    }//GEN-LAST:event_ReportesMouseExited

    private void UsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            jPasswordField1.setText("");
        }
    }//GEN-LAST:event_UsernameKeyPressed

    private void UsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            jPasswordField1.setText("");
        }
    }//GEN-LAST:event_UsernameKeyReleased

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            jPasswordField1.setText("");
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void OptionsVendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsVendMouseClicked
        boolean isVisible = PropMarket.isVisible() || PropCompradas.isVisible();
        if (cv == 1) {
            PropMarket.setVisible(!PropMarket.isVisible());
            PropCompradas.setVisible(!PropCompradas.isVisible());
        } else if (cv == 2) {
            PropSold.setVisible(!PropSold.isVisible());
            Prop_en_mercado.setVisible(!Prop_en_mercado.isVisible());
        } else if (cv == 3) {
            PropMarket.setVisible(!PropMarket.isVisible());
            PropSold.setVisible(!PropSold.isVisible());
            PropCompradas.setVisible(!PropCompradas.isVisible());
            Prop_en_mercado.setVisible(!Prop_en_mercado.isVisible());
        }


    }//GEN-LAST:event_OptionsVendMouseClicked

    private void OptionsVendMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsVendMouseEntered
        OptionVendedor.setBackground(new Color(30, 30, 150));
        Indicator1.setText("OPCIONES");
    }//GEN-LAST:event_OptionsVendMouseEntered

    private void OptionsVendMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsVendMouseExited
        OptionVendedor.setBackground(null);
        Indicator1.setText("");
    }//GEN-LAST:event_OptionsVendMouseExited

    private void OptionVendedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionVendedorMouseExited
        OptionVendedor.setBackground(null);
        Indicator1.setText("");
    }//GEN-LAST:event_OptionVendedorMouseExited

    private void PropMarketMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropMarketMouseEntered
        Indicator1.setText("PROP. MERCADO");
    }//GEN-LAST:event_PropMarketMouseEntered

    private void PropMarketMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropMarketMouseExited
        OptionVendedor.setBackground(null);
        Indicator1.setText("");
    }//GEN-LAST:event_PropMarketMouseExited

    private void PropCompradasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropCompradasMouseEntered

        Indicator1.setText("P. compradas");
    }//GEN-LAST:event_PropCompradasMouseEntered

    private void PropCompradasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropCompradasMouseExited

        Indicator1.setText("");
    }//GEN-LAST:event_PropCompradasMouseExited

    private void Exit3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit3MouseClicked
        int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Desea cerrar sesion?", "WARNING!", JOptionPane.WARNING_MESSAGE);

        if (showConfirmDialog == 0) {
            Username.setText("Ingrese su Usuario");
            jPasswordField1.setText("");
            cv = 0;
            admin.logout();
            this.setVisible(true);
            MainScreen_Others.setVisible(false);
            PropCompradas.setVisible(false);
            PropMarket.setVisible(false);
            Prop_en_mercado.setVisible(false);
            PropSold.setVisible(false);
            reset();
        }
    }//GEN-LAST:event_Exit3MouseClicked

    private void Exit3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit3MouseEntered
        ExitPanel1.setBackground(Color.RED);
        Indicator1.setText("SALIR");
    }//GEN-LAST:event_Exit3MouseEntered

    private void Exit3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit3MouseExited
        ExitPanel1.setBackground(null);
        Indicator1.setText("");
    }//GEN-LAST:event_Exit3MouseExited

    private void Options2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Options2MouseClicked
        PropVendidas.setVisible(!PropVendidas.isVisible());
        PropMercado1.setVisible(!PropMercado1.isVisible());
    }//GEN-LAST:event_Options2MouseClicked

    private void Options2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Options2MouseEntered
        OptionPanel2.setBackground(new Color(204, 85, 0));
        Indicator2.setText("OPCIONES");
    }//GEN-LAST:event_Options2MouseEntered

    private void Options2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Options2MouseExited
        OptionPanel2.setBackground(null);
        Indicator2.setText("");
    }//GEN-LAST:event_Options2MouseExited

    private void OptionPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionPanel2MouseExited
        //OptionPanel.setBackground(null);
    }//GEN-LAST:event_OptionPanel2MouseExited

    private void Propiedades_MercadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Propiedades_MercadoMouseEntered

    }//GEN-LAST:event_Propiedades_MercadoMouseEntered

    private void Propiedades_MercadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Propiedades_MercadoMouseExited

    }//GEN-LAST:event_Propiedades_MercadoMouseExited

    private void Exit4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit4MouseClicked
        int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Desea cerrar sesion?", "WARNING!", JOptionPane.WARNING_MESSAGE);

        if (showConfirmDialog == 0) {
            Username.setText("Ingrese su Usuario");
            jPasswordField1.setText("");
            admin.logout();
            this.setVisible(true);
            MainScreen_Agente.setVisible(false);
            reset();
        }
    }//GEN-LAST:event_Exit4MouseClicked

    private void Exit4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit4MouseEntered
        ExitPanel2.setBackground(Color.RED);
        Indicator2.setText("SALIR");
    }//GEN-LAST:event_Exit4MouseEntered

    private void Exit4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit4MouseExited
        ExitPanel2.setBackground(null);
        Indicator2.setText("");
    }//GEN-LAST:event_Exit4MouseExited

    private void PropSoldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropSoldMouseEntered
        Indicator1.setText("PROP. VENDIDAS");
    }//GEN-LAST:event_PropSoldMouseEntered

    private void PropSoldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropSoldMouseExited
        OptionVendedor.setBackground(null);
        Indicator1.setText("");
    }//GEN-LAST:event_PropSoldMouseExited

    private void bt_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_adminMouseClicked
        tb_admin.setSelectedIndex(5);
    }//GEN-LAST:event_bt_adminMouseClicked
    public void fillTable_pBought() {
        ResultSet rs = admin.getpropiedadescomprador(currentid);
        DefaultTableModel modelo = (DefaultTableModel) tb_pBought.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getFloat(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getDate(9);
                row[9] = rs.getInt(10);
                row[10] = rs.getInt(11);
                row[11] = rs.getInt(12);
                row[12] = rs.getFloat(13);
                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillTable_compradores() {
        ResultSet rs = admin.getCompradores();
        DefaultTableModel modelo = (DefaultTableModel) tb_compradores.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillTable_bitacora() {
        ResultSet rs = admin.getBitacora();
        DefaultTableModel modelo = (DefaultTableModel) tabla_bitacora.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getDate(6);
                row[6] = rs.getTime(7);
                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btn_vendedor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_vendedor1MouseClicked
        fillTable_compradores();
        tb_admin.setSelectedIndex(0);
    }//GEN-LAST:event_btn_vendedor1MouseClicked

    public void fillTable_vendedores() {
        ResultSet rs = admin.getVendedores();
        DefaultTableModel modelo = (DefaultTableModel) tb_vendedores.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btn_vendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_vendedorMouseClicked
        fillTable_vendedores();
        tb_admin.setSelectedIndex(1);
    }//GEN-LAST:event_btn_vendedorMouseClicked

    public void fillTable_agentes() {
        ResultSet rs = admin.getAgentes();
        DefaultTableModel modelo = (DefaultTableModel) tb_agentes.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getInt(5);
                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btn_agentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agentesMouseClicked
        fillTable_agentes();
        tb_admin.setSelectedIndex(2);
    }//GEN-LAST:event_btn_agentesMouseClicked

    public void fillTable_propiedadesv() {
        ResultSet rs = admin.getPropiedadesV();
        DefaultTableModel modelo = (DefaultTableModel) tb_propiedadesv.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getFloat(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getDate(9);
                row[9] = rs.getInt(10);
                row[10] = rs.getInt(11);
                row[11] = rs.getInt(12);
                row[12] = rs.getFloat(13);
                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btn_pVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pVentaMouseClicked

        fillTable_propiedadesv();
        tb_admin.setSelectedIndex(3);
    }//GEN-LAST:event_btn_pVentaMouseClicked
    public void fillTable_pMercado() {
        ResultSet rs = admin.getPropiedadesM();
        DefaultTableModel modelo = (DefaultTableModel) tb_pMercado.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[10];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getFloat(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getInt(9);
                row[9] = rs.getInt(10);

                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillTable_cMercado() {
        ResultSet rs = admin.getPropiedadesM();
        DefaultTableModel modelo = (DefaultTableModel) tc_propMercado.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[10];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getFloat(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getInt(9);
                row[9] = rs.getInt(10);

                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillTable_propmAgentes(int id) {
        ResultSet rs = admin.getpropiedadesmagente(id);
        DefaultTableModel modelo = (DefaultTableModel) tb_propmAgentes.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {

                Object[] row = new Object[10];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getFloat(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getInt(9);
                row[9] = rs.getInt(10);

                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillTable_propvAgentes(int id) {
        ResultSet rs = admin.getpropiedadesvagente(id);
        DefaultTableModel modelo = (DefaultTableModel) tb_propvAgentes.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {

                Object[] row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getFloat(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getDate(9);
                row[9] = rs.getInt(10);
                row[10] = rs.getInt(11);
                row[11] = rs.getInt(12);
                row[12] = rs.getDouble(13);
                //row[14] = rs.getString(15);

                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillTable_propmvendedor(int id) {
        ResultSet rs = admin.getpropiedadesmvendedor(id);
        DefaultTableModel modelo = (DefaultTableModel) pvtable_mercado.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {

                Object[] row = new Object[10];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getFloat(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getInt(9);
                row[9] = rs.getInt(10);

                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillTable_propiedadesvVend() {
        ResultSet rs = admin.getpropiedadesvvendedor(currentid);
        DefaultTableModel modelo = (DefaultTableModel) pvtable_vendedores.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getFloat(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getDate(9);
                row[9] = rs.getInt(10);
                row[10] = rs.getInt(11);
                row[11] = rs.getInt(12);
                row[12] = rs.getFloat(13);
                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btn_pMercadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pMercadoMouseClicked
        fillTable_pMercado();
        tb_admin.setSelectedIndex(4);
    }//GEN-LAST:event_btn_pMercadoMouseClicked

    public void fillTable_admins() {
        ResultSet rs = admin.getAdmins();
        DefaultTableModel modelo = (DefaultTableModel) tb_admins.getModel();
        modelo.setRowCount(0);
        try {
            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(5);
                modelo.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void MantenimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MantenimientoMouseClicked
        reset();
        fillTable_admins();
        Layer.setVisible(false);
        tb_admin.setVisible(true);
        pn_menuCrud.setVisible(true);
    }//GEN-LAST:event_MantenimientoMouseClicked

    private void bt_addCompradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addCompradorMouseClicked
        jd_createClienteReset();
        abrirCrearCliente();
        rbtnComprador.setEnabled(true);
        rbtnComprador.setSelected(true);
        rbtnVendedor.setEnabled(false);
    }//GEN-LAST:event_bt_addCompradorMouseClicked

    private void bt_addVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addVendedorMouseClicked

        jd_createClienteReset();
        abrirCrearCliente();
        rbtnVendedor.setEnabled(true);
        rbtnVendedor.setSelected(true);
        rbtnComprador.setEnabled(false);
    }//GEN-LAST:event_bt_addVendedorMouseClicked

    private void bt_crearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_crearClienteActionPerformed
        if (modify == 0) {
            username = JOptionPane.showInputDialog(this, "Ingrese un nombre de usuario para este cliente");
            password = JOptionPane.showInputDialog(this, "Ingrese una contraseña para este usuario");

            try {

                if (rbtnComprador.isSelected()) {

                    admin.crearusuario(username, Integer.parseInt(tf_identidadCliente.getText()), password, "comprador");
                    if (admin.crearcomprador(Integer.parseInt(tf_identidadCliente.getText()), tf_nombreCliente.getText(), tf_direccionCliente.getText(), Integer.parseInt(tf_direccionCliente.getText()))) {
                        JOptionPane.showMessageDialog(this, "Comprador creado correctamente");
                    }
                    fillTable_compradores();
                } else if (rbtnVendedor.isSelected()) {

                    admin.crearusuario(username, Integer.parseInt(tf_identidadCliente.getText()), password, "vendedor");
                    if (admin.crearvendedor(Integer.parseInt(tf_identidadCliente.getText()), tf_nombreCliente.getText(), tf_direccionCliente.getText(), Integer.parseInt(tf_direccionCliente.getText()))) {
                        JOptionPane.showMessageDialog(this, "Vendedor creado correctamente");
                    }
                    fillTable_vendedores();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Algun dato es incorrecto");
            }

        } else {
            try {
                int id = Integer.parseInt(tf_identidadCliente.getText());
                String name = tf_nombreCliente.getText();
                String direccion = tf_direccionCliente.getText();
                int celular = Integer.parseInt(tf_celularCliente.getText());
                boolean funciono = false;
                if (rbtnComprador.isSelected()) {
                    funciono = admin.modificarcomprador(id, name, direccion, celular);
                    fillTable_compradores();
                } else if (rbtnVendedor.isSelected()) {
                    funciono = admin.modificarvendedor(id, name, direccion, celular);
                    fillTable_vendedores();
                }

                if (funciono) {
                    JOptionPane.showMessageDialog(this, "Cliente creado correctamente");
                    jd_createCliente.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Datos no validos");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Tipo de dato ingresado incorrecto");
            }
        }
    }//GEN-LAST:event_bt_crearClienteActionPerformed
   
    
    private void bt_crearPropiedadMercMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearPropiedadMercMouseClicked
        String nombrePropiedad, ciudad, direccion, caracteristicas, idPropiedad, strPrecio, strCantHabitaciones;
        Date publishDate = new Date();
        String path = tf_imgM.getText();
       
     
        if (modify == 0) {
            try {
                publishDate = dc_fppm.getDate();
                nombrePropiedad = tf_nombreMerc.getText();
                ciudad = tf_CiudadMerc.getText();
                direccion = tf_direccion.getText();
                caracteristicas = tf_caracteriticasMerc.getText();
                strPrecio = tf_precioMerc.getText();
                int habi = (Integer) sp_propv.getValue();
                numA = (Integer) cb_agentem.getSelectedItem();
                numV = (Integer) cb_vendedorm.getSelectedItem();
                double price = Double.parseDouble(tf_precioMerc.getText());
                if (admin.crearpropiedadm(nombrePropiedad, ciudad, direccion, habi, caracteristicas, habi, new java.sql.Date(publishDate.getTime()), numA, numV, path)) {
                    JOptionPane.showMessageDialog(this, "Propiedad ingresada exitosamente");
                    fillTable_pMercado();
                    jd_createPropiedad.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Propiedad no se pudo ingresar");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ingrese tipo de dato correcto");
            }

        } else {
            try {
                publishDate = dc_fppm.getDate();
                nombrePropiedad = tf_nombreMerc.getText();
                ciudad = tf_CiudadMerc.getText();
                direccion = tf_direccionMerc.getText();
                caracteristicas = tf_caracteriticasMerc.getText();
                strPrecio = tf_precioMerc.getText();
                int habi = (Integer) sp_propv.getValue();
                
                double price = Double.parseDouble(tf_precioMerc.getText());
               
                if (admin.modificarpropiedadm(idpv, nombrePropiedad, ciudad, direccion, habi, caracteristicas, habi, new java.sql.Date(publishDate.getTime()), numA, numV, path)) {
                    JOptionPane.showMessageDialog(this, "Propiedad modificada exitosamente");
                    fillTable_pMercado();
                    jd_createPropiedad.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Propiedad no se pudo modificar");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ingrese tipo de dato correcto");
            }
        }

        //admin.crearpropiedadm(Integer.parseInt(idPropiedad), ciudad, ciudad, direccion, Integer.parseInt(strCantHabitaciones), 
        //caracteristicas, Double.parseDouble(strPrecio), (java.sql.Date) publishDate, WIDTH, WIDTH);
    }//GEN-LAST:event_bt_crearPropiedadMercMouseClicked

    private void bt_crearPropiedadVendidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearPropiedadVendidaMouseClicked
        String nombrePropiedad, ciudad, direccion, caracteristicas, idPropiedad, strPrecio, strCantHabitaciones;
        String path = tf_imgV.getText();
       
        
        if (modify == 0) {
            try {
                int agente = (Integer) cb_agente.getSelectedItem();
                int vendedor = (Integer) cb_vendedor.getSelectedItem();
                int comprador = (Integer) cb_comprador.getSelectedItem();
                if (admin.crearpropiedadv(tf_nombreMerc1.getText(), tf_CiudadMerc1.getText(), tf_direccionMerc1.getText(), (Integer) sp_cantiHabi.getValue(), tf_caracteriticasMerc1.getText(), Double.parseDouble(tf_precioMerc1.getText()), new java.sql.Date(dc_ventapv.getDate().getTime()), new java.sql.Date(dc_comprapv.getDate().getTime()), agente, vendedor, comprador, Double.parseDouble(tf_comision.getText()), path)) {
                    JOptionPane.showMessageDialog(this, "Propiedad creada exitosamente");
                    jd_createPropiedadVendida.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un error, operacion fallida");
                }
            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Ingreso un tipo de dato incorrecto. Intentelo de nuevo.");
            }
        } else {
            try {
                if (admin.modificarpropiedadv(idpv, tf_nombreMerc1.getText(), tf_CiudadMerc1.getText(), tf_direccionMerc1.getText(), (Integer) sp_cantiHabi.getValue(), tf_caracteriticasMerc1.getText(), Double.parseDouble(tf_precioMerc1.getText()), new java.sql.Date(dc_ventapv.getDate().getTime()), new java.sql.Date(dc_comprapv.getDate().getTime()), numA, numV, numC, Double.parseDouble(tf_comision.getText()), path)) {
                    JOptionPane.showMessageDialog(this, "Propiedad modificada exitosamente");
                    jd_createPropiedadVendida.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un error, operacion fallida");
                }
            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Ingreso un tipo de dato incorrecto. Intentelo de nuevo.");
            }
        }
        fillTable_propiedadesv();
    }//GEN-LAST:event_bt_crearPropiedadVendidaMouseClicked

    private void bt_pMercadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_pMercadoMouseClicked
        modify = 0;
        jd_createpmReset();
        abrirCrearMercado();
    }//GEN-LAST:event_bt_pMercadoMouseClicked

    private void bt_addPVendidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addPVendidaMouseClicked
        modify = 0;
        jd_createpvReset();
        abrirCrearVendida();
    }//GEN-LAST:event_bt_addPVendidaMouseClicked

    private void MainScreen_AgenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MainScreen_AgenteMouseClicked

    }//GEN-LAST:event_MainScreen_AgenteMouseClicked

    private void LogKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LogKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LogKeyPressed

    private void tb_vendedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_vendedoresMouseClicked
        popup = 3;
        if (SwingUtilities.isRightMouseButton(evt)) {
            popupCrud.show(tb_vendedores, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tb_vendedoresMouseClicked

    private void mi_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_modifyActionPerformed

        if (popup == 1) {
            int row = tb_admins.getSelectedRow();
            if (row != -1) {
                password = JOptionPane.showInputDialog(this, "Ingrese nueva contraseña para este admin");
                if (admin.modificarusuario((String) ((DefaultTableModel) tb_admins.getModel()).getValueAt(0, 0), password)) {
                    JOptionPane.showMessageDialog(this, "Contraseña cambiada exitosamente");
                    fillTable_admins();
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña no se pudo cambiar");
                }
            }
        } else if (popup == 2 || popup == 3) {
            //recuperar valores
            int row = -1;
            if (popup == 2) {

                row = tb_compradores.getSelectedRow();
            } else {
                row = tb_vendedores.getSelectedRow();
            }
            if (row != -1) {
                if (popup == 2) {
                    tf_identidadCliente.setText(Integer.toString((int) ((DefaultTableModel) tb_compradores.getModel()).getValueAt(row, 0)));
                    tf_nombreCliente.setText((String) ((DefaultTableModel) tb_compradores.getModel()).getValueAt(row, 1));
                    tf_direccionCliente.setText((String) ((DefaultTableModel) tb_compradores.getModel()).getValueAt(row, 2));
                    tf_celularCliente.setText(Integer.toString((int) ((DefaultTableModel) tb_compradores.getModel()).getValueAt(row, 3)));
                    tf_identidadCliente.setEnabled(false);

                } else {
                    tf_identidadCliente.setText(Integer.toString((int) ((DefaultTableModel) tb_vendedores.getModel()).getValueAt(row, 0)));
                    tf_nombreCliente.setText((String) ((DefaultTableModel) tb_vendedores.getModel()).getValueAt(row, 1));
                    tf_direccionCliente.setText((String) ((DefaultTableModel) tb_vendedores.getModel()).getValueAt(row, 2));
                    tf_celularCliente.setText(Integer.toString((int) ((DefaultTableModel) tb_vendedores.getModel()).getValueAt(row, 3)));
                    tf_identidadCliente.setEnabled(false);
                }
                modify = 1;
                lb_clienteTitulo.setText("Modificar");
                bt_crearCliente.setText("Modificar");
                if (popup == 3) {
                    abrirCrearCliente();
                    rbtnVendedor.setEnabled(true);
                    rbtnVendedor.setSelected(true);
                    rbtnComprador.setEnabled(false);
                } else {
                    abrirCrearCliente();
                    rbtnComprador.setEnabled(true);
                    rbtnComprador.setSelected(true);
                    rbtnVendedor.setEnabled(false);
                }
            }
        } else if (popup == 4) {
            modify = 1;
            int row = tb_agentes.getSelectedRow();
            if (row != -1) {
                lb_cAgente.setText("Modificar");
                tf_identidad.setText(Integer.toString((Integer) ((DefaultTableModel) tb_agentes.getModel()).getValueAt(row, 0)));
                tf_nombre.setText((String) ((DefaultTableModel) tb_agentes.getModel()).getValueAt(row, 1));
                tf_direccion.setText((String) ((DefaultTableModel) tb_agentes.getModel()).getValueAt(row, 2));
                tf_celular.setText(Integer.toString((Integer) ((DefaultTableModel) tb_agentes.getModel()).getValueAt(row, 3)));
                tf_oficina.setText(Integer.toString((Integer) ((DefaultTableModel) tb_agentes.getModel()).getValueAt(row, 4)));
                abrirCrearAgente();
                bt_crearAgente.setText("Modificar Agente");
                tf_identidad.setEnabled(false);
            }
        } else if (popup == 5) {
            modify = 1;
            lb_pv.setText("Modificar");
            lb_a.setVisible(false);
            lb_v.setVisible(false);
            lb_c.setVisible(false);
            cb_agente.setVisible(false);
            cb_vendedor.setVisible(false);
            cb_comprador.setVisible(false);
            int row = tb_propiedadesv.getSelectedRow();
            if (row != -1) {
                idpv = (Integer) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 0);

                tf_nombreMerc1.setText((String) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 1));
                tf_CiudadMerc1.setText((String) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 2));
                tf_direccionMerc1.setText((String) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 3));
                tf_caracteriticasMerc1.setText((String) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 5));
                tf_precioMerc1.setText(Float.toString((Float) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 6)));
                sp_cantiHabi.setValue(((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 4));

                numA = (Integer) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 9);
                numV = (Integer) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 10);
                numC = (Integer) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 11);

                tf_comision.setText(Float.toString((Float) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 12)));
                bt_crearPropiedadVendida.setText("Modificar Propiedad");
                //lb_cpv.setText("MODIFICAR PROPIEDAD VENDIDA");
                abrirCrearVendida();
            }
        } else if (popup == 6) {
            modify = 1;
            lb_am.setVisible(false);
            lb_vm.setVisible(false);

            cb_agentem.setVisible(false);
            cb_vendedorm.setVisible(false);

            int row = tb_pMercado.getSelectedRow();
            if (row != -1) {
                idpv = (Integer) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 0);

                tf_nombreMerc.setText((String) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 1));
                tf_CiudadMerc.setText((String) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 2));
                tf_direccionMerc.setText((String) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 3));
                tf_caracteriticasMerc.setText((String) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 5));
                tf_precioMerc.setText(Float.toString((Float) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 6)));
                sp_propv.setValue(((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 4));

                numA = (Integer) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 8);
                numV = (Integer) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 9);

                bt_crearPropiedadMerc.setText("Modificar Propiedad");
                lb_pm.setText("Modificar");
                abrirCrearMercado();
            }
        }
    }//GEN-LAST:event_mi_modifyActionPerformed

    private void bt_addCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addCompradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_addCompradorActionPerformed

    private void bt_crearClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_crearClienteMouseClicked

    private void bt_crearPropiedadVendidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_crearPropiedadVendidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_crearPropiedadVendidaActionPerformed

    private void tb_propiedadesvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_propiedadesvMouseClicked

        popup = 5;
        if (SwingUtilities.isRightMouseButton(evt)) {
            popupCrud.show(tb_propiedadesv, evt.getX(), evt.getY());
        } else {
            int row = tb_propiedadesv.getSelectedRow();
            if (row != -1) {
                int id = (Integer) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 0);
                setupImg(MainScreen_Admin, 'v', id);
            }
        }

    }//GEN-LAST:event_tb_propiedadesvMouseClicked

    private void tb_compradoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_compradoresMouseClicked
        popup = 2;
        if (SwingUtilities.isRightMouseButton(evt)) {
            popupCrud.show(tb_compradores, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tb_compradoresMouseClicked

    private void tb_pMercadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pMercadoMouseClicked
        popup = 6;
        if (SwingUtilities.isRightMouseButton(evt)) {
            popupCrud.show(tb_pMercado, evt.getX(), evt.getY());
        } else {
            int row = tb_pMercado.getSelectedRow();
            if (row != -1) {
                int id = (Integer) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 0);
                
                setupImg(MainScreen_Admin, 'm', id);
            }
        }
    }//GEN-LAST:event_tb_pMercadoMouseClicked

    private void bt_crearAgenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearAgenteMouseClicked
        if (modify == 0) {
            username = JOptionPane.showInputDialog(this, "Ingrese un nombre de usuario para este cliente");
            password = JOptionPane.showInputDialog(this, "Ingrese una contraseña para este usuario");

            try {
                admin.crearusuario(username, Integer.parseInt(tf_identidad.getText()), password, "agente");
                if (admin.crearagente(Integer.parseInt(tf_identidad.getText()), tf_nombre.getText(), tf_direccion.getText(), Integer.parseInt(tf_celular.getText()), Integer.parseInt(tf_oficina.getText()))) {
                    JOptionPane.showMessageDialog(this, "Agente agregado exitosamente");
                    fillTable_agentes();
                    jd_createAgente.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo agregar el agente");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Tipo de dato incorrecto");
            }
        } else {
            try {
                if (admin.modificaragente(Integer.parseInt(tf_identidad.getText()), tf_nombre.getText(), tf_direccion.getText(), Integer.parseInt(tf_celular.getText()), Integer.parseInt(tf_oficina.getText()))) {
                    JOptionPane.showMessageDialog(this, "Agente modificado exitosamente");
                    fillTable_agentes();
                    jd_createAgente.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo modificar el agente");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ingrse valores correctos");
            }
        }
    }//GEN-LAST:event_bt_crearAgenteMouseClicked

    private void bt_addAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addAdminMouseClicked
        modify = 0;
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese un numero de identidad para este admin"));
            username = JOptionPane.showInputDialog(this, "Ingrese un nombre de usuario para este admin");
            password = JOptionPane.showInputDialog(this, "Ingrese una contraseña para este admin");
            if (admin.crearusuario(username, id, password, "admin")) {
                JOptionPane.showMessageDialog(this, "Admin creado exitosamente");
                fillTable_admins();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo crear el Admin");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tipo de dato incorrecto en numero de identidad");
        }
    }//GEN-LAST:event_bt_addAdminMouseClicked

    private void tb_adminsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_adminsMouseClicked
        popup = 1;
        if (SwingUtilities.isRightMouseButton(evt)) {
            popupCrud.show(tb_admins, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tb_adminsMouseClicked

    private void bt_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_adminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_adminActionPerformed

    private void btn_vendedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vendedor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_vendedor1ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Layer.setVisible(true);
        tb_admin.setVisible(false);
        pn_menuCrud.setVisible(false);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void bt_pMercadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pMercadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_pMercadoActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        jd_createCliente.setVisible(false);
        rbtnComprador.setSelected(false);
        rbtnVendedor.setSelected(false);
        tf_identidadCliente.setText("");
        tf_nombreCliente.setText("");
        tf_direccionCliente.setText("");
        tf_celularCliente.setText("");
    }//GEN-LAST:event_RegresarActionPerformed

    private void tf_celularClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_celularClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_celularClienteActionPerformed

    private void tf_CiudadMercActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_CiudadMercActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_CiudadMercActionPerformed

    private void tf_direccionMercActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_direccionMercActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_direccionMercActionPerformed

    private void cb_compradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_compradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_compradorActionPerformed

    private void backArrow2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow2MouseClicked
        Layer.setVisible(true);
        Bitacora_Panel.setVisible(false);
    }//GEN-LAST:event_backArrow2MouseClicked

    private void BitacoraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BitacoraMouseExited
        BitacoraPanel.setBackground(null);
        Indicator.setText("");
    }//GEN-LAST:event_BitacoraMouseExited

    private void BitacoraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BitacoraMouseEntered
        BitacoraPanel.setBackground(new Color(0, 100, 0));
        Indicator.setText("BITÁCORA");
    }//GEN-LAST:event_BitacoraMouseEntered

    private void BitacoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BitacoraMouseClicked
        reset();
        fillTable_bitacora();
        Layer.setVisible(false);
        Bitacora_Panel.setVisible(true);
        pn_menuCrud.setVisible(false);
    }//GEN-LAST:event_BitacoraMouseClicked

    private void backArrow3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow3MouseClicked
        Layer.setVisible(true);
        panelVistas2.setVisible(false);
        tabbedVistas.setVisible(false);
        jPanel1.setVisible(false);
    }//GEN-LAST:event_backArrow3MouseClicked

    private void ReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportesMouseClicked
        reset();
        Layer.setVisible(false);
        panelVistas2.setVisible(true);
        tabbedVistas.setVisible(true);
        jPanel1.setVisible(true);

        pn_menuCrud.setVisible(false);
    }//GEN-LAST:event_ReportesMouseClicked

    private void btn_agentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_agentesActionPerformed

    private void btnCargarVentasAgenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCargarVentasAgenteMouseClicked
        try {
            admin.ventasAgente(tbVentasAgente);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCargarVentasAgenteMouseClicked

    private void btnVentasVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasVendedorMouseClicked
        try {
            admin.ventasVendedor(tbVentasVendedor);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnVentasVendedorMouseClicked

    private void btnComprasCompradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprasCompradorMouseClicked
        try {
            admin.comprasComprador(tbComprasComprador);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnComprasCompradorMouseClicked

    private void btnVentasUbicacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasUbicacionMouseClicked
        try {
            admin.ventasUbicacion(tbVentasUbicacion);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnVentasUbicacionMouseClicked

    private void btnVentasPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasPrecioMouseClicked
        try {
            admin.ventasPrecio(tbVentasPrecio);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnVentasPrecioMouseClicked

    private void btnVentasCaracteristicasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasCaracteristicasMouseClicked
        try {
            admin.ventasCaracteristicas(tbVentasCaracteristicas);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnVentasCaracteristicasMouseClicked

    private void mi_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_deleteActionPerformed
        int row = -1;
        if (popup == 1) {

            row = tb_admins.getSelectedRow();
            if (row != -1) {
                if (((String) ((DefaultTableModel) tb_admins.getModel()).getValueAt(row, 0)).equals(currentusername)) {
                    JOptionPane.showMessageDialog(this, "No se pudo borrar el usuario actual");
                } else {
                    if (admin.borraradmin((Integer) ((DefaultTableModel) tb_admins.getModel()).getValueAt(row, 2))) {
                        JOptionPane.showMessageDialog(this, "Administrador borrado exitosamente");
                        fillTable_admins();
                    } else {
                        JOptionPane.showConfirmDialog(this, "Admin no se pudo borrar");
                    }
                }
            }
        } else if (popup == 2) {
            row = tb_compradores.getSelectedRow();
            if (row != -1) {
                if (admin.borrarcomprador((Integer) ((DefaultTableModel) tb_compradores.getModel()).getValueAt(row, 0))) {
                    JOptionPane.showMessageDialog(this, "Comprador borrado exitosamente");
                    fillTable_compradores();
                } else {
                    JOptionPane.showMessageDialog(this, "Comprador no se pudo borrar");
                }
            }
        } else if (popup == 3) {
            row = tb_vendedores.getSelectedRow();
            if (row != -1) {
                if (admin.borrarvendedor((Integer) ((DefaultTableModel) tb_vendedores.getModel()).getValueAt(row, 0))) {
                    JOptionPane.showMessageDialog(this, "Vendedor borrado exitosamente");
                    fillTable_vendedores();
                } else {
                    JOptionPane.showMessageDialog(this, "Vendedor no se pudo borrar");
                }
            }
        } else if (popup == 4) {
            row = tb_agentes.getSelectedRow();
            if (row != -1) {
                if (admin.borraragente((Integer) ((DefaultTableModel) tb_agentes.getModel()).getValueAt(row, 0))) {
                    JOptionPane.showMessageDialog(this, "Agente borrado exitosamente");
                    fillTable_agentes();
                } else {
                    JOptionPane.showMessageDialog(this, "Agente no se pudo borrar");
                }
            }
        } else if (popup == 5) {
            row = tb_propiedadesv.getSelectedRow();
            if (row != -1) {
                if (admin.borrarpropiedadv((Integer) ((DefaultTableModel) tb_propiedadesv.getModel()).getValueAt(row, 0))) {
                    JOptionPane.showMessageDialog(this, "Propiedad vendida borrada exitosamente");
                    fillTable_propiedadesv();
                } else {
                    JOptionPane.showMessageDialog(this, "Propiedad vendida no se pudo borrar");
                }
            }
        } else if (popup == 6) {
            row = tb_pMercado.getSelectedRow();
            if (row != -1) {
                if (admin.borrarpropiedadm((Integer) ((DefaultTableModel) tb_pMercado.getModel()).getValueAt(row, 0))) {
                    JOptionPane.showMessageDialog(this, "Propiedad en mercado borrada exitosamente");
                    fillTable_pMercado();
                } else {
                    JOptionPane.showMessageDialog(this, "Propiedad en mercado no se pudo borrar");
                }
            }
        }
    }//GEN-LAST:event_mi_deleteActionPerformed

    private void bt_addAgenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addAgenteMouseClicked
        modify = 0;
        jd_crearAgenteReset();
        abrirCrearAgente();

    }//GEN-LAST:event_bt_addAgenteMouseClicked

    private void tb_agentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_agentesMouseClicked
        popup = 4;
        if (SwingUtilities.isRightMouseButton(evt)) {
            popupCrud.show(tb_agentes, evt.getX(), evt.getY());
        };
    }//GEN-LAST:event_tb_agentesMouseClicked

    private void PropMercado1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropMercado1MouseEntered
        Propiedades_Mercado.setBackground(new Color(204, 85, 0));
        Indicator2.setText("P. MERCADO");
    }//GEN-LAST:event_PropMercado1MouseEntered

    private void PropMercado1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropMercado1MouseExited
        Propiedades_Mercado.setBackground(null);
        Indicator2.setText("");
    }//GEN-LAST:event_PropMercado1MouseExited

    private void PropVendidasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropVendidasMouseEntered
        PropiedadesVendidas.setBackground(new Color(204, 85, 0));
        Indicator2.setText("P. VENDIDAS");
    }//GEN-LAST:event_PropVendidasMouseEntered

    private void PropVendidasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropVendidasMouseExited
        PropiedadesVendidas.setBackground(null);
        Indicator2.setText("");
    }//GEN-LAST:event_PropVendidasMouseExited

    private void PropiedadesVendidasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropiedadesVendidasMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PropiedadesVendidasMouseEntered

    private void PropiedadesVendidasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropiedadesVendidasMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PropiedadesVendidasMouseExited

    private void PropMercado1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropMercado1MouseClicked
        reset();
        panel_agentes.setVisible(true);
        Layer2.setVisible(false);
        fillTable_propmAgentes(currentid);
        fillTable_propvAgentes(currentid);
        tabbedAgentes.setSelectedIndex(0);
    }//GEN-LAST:event_PropMercado1MouseClicked

    private void backArrow3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_backArrow3MouseEntered

    private void btnValorVentaAnioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnValorVentaAnioMouseClicked
        String input = "";
        if (!yearTextField.getText().isEmpty()) {
            input = yearTextField.getText();
            if (isNumeric(input)) {
                int year = Integer.parseInt(input);
                if (year >= 2000 && year <= 3000) {
                    try {
                        admin.agentePromedioAnual(tbPromedioAnual, year);
                    } catch (SQLException ex) {
                        Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese un año válido");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un año");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un año");
        }
    }//GEN-LAST:event_btnValorVentaAnioMouseClicked

    private void btnventaAnualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnventaAnualMouseClicked
        String input = "";
        if (!yearTextField1.getText().isEmpty()) {
            input = yearTextField1.getText();
            if (isNumeric(input)) {
                int year = Integer.parseInt(input);
                if (year >= 2000 && year <= 3000) {
                    try {
                        admin.agenteValorVenta(tablaAgenteGoat, year);
                    } catch (SQLException ex) {
                        Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese un año válido");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un año");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un año");
        }
    }//GEN-LAST:event_btnventaAnualMouseClicked

    private void backArrow4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow4MouseClicked
        panel_agentes.setVisible(false);
        Layer2.setVisible(true);
    }//GEN-LAST:event_backArrow4MouseClicked

    private void backArrow4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_backArrow4MouseEntered

    private void PropVendidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropVendidasMouseClicked
        reset();
        panel_agentes.setVisible(true);
        Layer2.setVisible(false);
        fillTable_propvAgentes(currentid);
        tabbedAgentes.setSelectedIndex(1);
    }//GEN-LAST:event_PropVendidasMouseClicked

    private void tabbedAgentesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedAgentesStateChanged

    }//GEN-LAST:event_tabbedAgentesStateChanged

    private void backArrow5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow5MouseClicked
        panel_propEnMercadoCliente.setVisible(false);
        Layer1.setVisible(true);
    }//GEN-LAST:event_backArrow5MouseClicked

    private void backArrow5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_backArrow5MouseEntered

    private void tc_propMercadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tc_propMercadoMouseClicked
        if (!SwingUtilities.isRightMouseButton(evt)) {
            int row = tc_propMercado.getSelectedRow();
            if (row != -1) {
                int id = (Integer) ((DefaultTableModel) tc_propMercado.getModel()).getValueAt(row, 0);
                setupImg(MainScreen_Others, 'm', id);
            }
        }
    }//GEN-LAST:event_tc_propMercadoMouseClicked

    private void PropMarketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropMarketMouseClicked
        reset();
        fillTable_cMercado();
        panel_propEnMercadoCliente.setVisible(true);
        Layer1.setVisible(false);
    }//GEN-LAST:event_PropMarketMouseClicked

    private void backArrow6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow6MouseClicked
        panel_propVendida.setVisible(false);
        Layer1.setVisible(true);
    }//GEN-LAST:event_backArrow6MouseClicked

    private void backArrow6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_backArrow6MouseEntered

    private void PropSoldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropSoldMouseClicked
        reset();
        panel_propEnMercadoCliente.setVisible(false);
        fillTable_propiedadesvVend();
        panel_propVendida.setVisible(true);
        Layer1.setVisible(false);
    }//GEN-LAST:event_PropSoldMouseClicked

    private void Prop_en_mercadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prop_en_mercadoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Prop_en_mercadoMouseEntered

    private void Prop_en_mercadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prop_en_mercadoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Prop_en_mercadoMouseExited

    private void backArrow7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow7MouseClicked
        panel_propMercadoVendedor.setVisible(false);
        Layer1.setVisible(true);
    }//GEN-LAST:event_backArrow7MouseClicked

    private void backArrow7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_backArrow7MouseEntered

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked

    }//GEN-LAST:event_jLabel49MouseClicked

    private void Prop_en_mercadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prop_en_mercadoMouseClicked
        reset();
        fillTable_propmvendedor(currentid);
        panel_propMercadoVendedor.setVisible(true);
        Layer1.setVisible(false);
    }//GEN-LAST:event_Prop_en_mercadoMouseClicked

    private void backArrow8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow8MouseClicked
        panel_pBought.setVisible(false);
        Layer1.setVisible(true);
    }//GEN-LAST:event_backArrow8MouseClicked

    private void backArrow8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrow8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_backArrow8MouseEntered

    private void PropCompradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropCompradasMouseClicked
        reset();
        fillTable_pBought();
        panel_pBought.setVisible(true);
        Layer1.setVisible(false);
    }//GEN-LAST:event_PropCompradasMouseClicked

    private void bt_imgVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_imgVMouseClicked
        JFileChooser fc = new JFileChooser();
        FileFilter filtro = new FileNameExtensionFilter("Imagenes",
                "png", "jpg", "jpeg", "gif");
        fc.setFileFilter(filtro);
        File archivo;
        int op = fc.showOpenDialog(this);
        if (op == JFileChooser.APPROVE_OPTION) {
            archivo = fc.getSelectedFile();
            tf_imgV.setText(archivo.getPath());
        }
    }//GEN-LAST:event_bt_imgVMouseClicked

    private void bt_imgMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_imgMMouseClicked
        JFileChooser fc = new JFileChooser();
        FileFilter filtro = new FileNameExtensionFilter("Imagenes",
                "png", "jpg", "jpeg", "gif");
        fc.setFileFilter(filtro);
        File archivo;
        int op = fc.showOpenDialog(this);
        if (op == JFileChooser.APPROVE_OPTION) {
            archivo = fc.getSelectedFile();
            tf_imgM.setText(archivo.getPath());
        }
    }//GEN-LAST:event_bt_imgMMouseClicked

    private void bt_closeImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_closeImageMouseClicked
        jd_propImage.setVisible(false);
    }//GEN-LAST:event_bt_closeImageMouseClicked

    private void tb_propmAgentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_propmAgentesMouseClicked
        if (!SwingUtilities.isRightMouseButton(evt)) {
            int row = tb_propmAgentes.getSelectedRow();
            if (row != -1) {
                int id = (Integer) ((DefaultTableModel) tb_propmAgentes.getModel()).getValueAt(row, 0);
                setupImg(MainScreen_Agente, 'm', id);
            }
        }
    }//GEN-LAST:event_tb_propmAgentesMouseClicked

    private void tb_propvAgentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_propvAgentesMouseClicked
        if (!SwingUtilities.isRightMouseButton(evt)) {
            int row = tb_propvAgentes.getSelectedRow();
            if (row != -1) {
                int id = (Integer) ((DefaultTableModel) tb_propvAgentes.getModel()).getValueAt(row, 0);
                setupImg(MainScreen_Agente, 'v', id);
            }
        }
    }//GEN-LAST:event_tb_propvAgentesMouseClicked

    private void tb_pBoughtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pBoughtMouseClicked
        if (!SwingUtilities.isRightMouseButton(evt)) {
            int row = tb_pBought.getSelectedRow();
            if (row != -1) {
                int id = (Integer) ((DefaultTableModel) tb_pBought.getModel()).getValueAt(row, 0);
                setupImg(MainScreen_Others, 'v', id);
            }
        }
    }//GEN-LAST:event_tb_pBoughtMouseClicked

    private void pvtable_mercadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvtable_mercadoMouseClicked
        if (!SwingUtilities.isRightMouseButton(evt)) {
            int row = pvtable_mercado.getSelectedRow();
            if (row != -1) {
                int id = (Integer) ((DefaultTableModel) pvtable_mercado.getModel()).getValueAt(row, 0);
                setupImg(MainScreen_Others, 'm', id);
            }
        }
    }//GEN-LAST:event_pvtable_mercadoMouseClicked

    public boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * args the command line arguments
     */
    public void MainScreen() {
        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        show();
        setLocationRelativeTo(null);
        this.setTitle("Teoría de Base de Datos I");
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("pics/Tiger.jpg"));
        this.setIconImage(icono);
        this.pack();

        Mantenimiento.setVisible(false);
        Bitacora.setVisible(false);
        Reportes.setVisible(false);

        PropMarket.setVisible(false);
        PropCompradas.setVisible(false);

        //MainScreen_Admin.setExtendedState(MAXIMIZED_BOTH);
    }

//    public Cliente crearCliente() {
//        Cliente client = new Cliente();
//
//        String nombre, direccion, noIdentidad, celular, telefonoOficina;
//
//        noIdentidad = tf_identidadCliente.getText();
//        nombre = tf_nombreCliente.getText();
//        direccion = tf_direccionCliente.getText();
//        celular = tf_celularCliente.getText();
//        telefonoOficina = "";
//        try {
//            if (rbtnComprador.isSelected()) {
//                client = new Comprador(nombre, direccion, noIdentidad, celular, telefonoOficina);
//                admin.crearusuario(username, Integer.parseInt(noIdentidad), password, "comprador");
//                admin.crearcomprador(Integer.parseInt(noIdentidad), nombre, direccion, Integer.parseInt(celular));
//                fillTable_compradores();
//            } else if (rbtnVendedor.isSelected()) {
//                client = new Vendedor(nombre, direccion, noIdentidad, celular, telefonoOficina);
//                admin.crearusuario(username, Integer.parseInt(noIdentidad), password, "vendedor");
//                admin.crearvendedor(Integer.parseInt(noIdentidad), nombre, direccion, Integer.parseInt(celular));
//                fillTable_vendedores();
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Algun dato es incorrecto");
//        }
//        return client;
//    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Log_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Log_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Log_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Log_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Log_In().setVisible(true);
            }
        });
    }

    public String idRandom() {
        String num = "";

        for (int i = 0; i < 3; i++) {
            num += rand.nextInt();
        }

        return num;
    }

    boolean isAdmin = false;

    public void abrirCrearMercado() {
        jd_createPropiedad.pack();
        jd_createPropiedad.setLocationRelativeTo(this);
        jd_createPropiedad.setVisible(true);
    }

    public void abrirCrearVendida() {
        jd_createPropiedadVendida.pack();
        jd_createPropiedadVendida.setLocationRelativeTo(this);
        jd_createPropiedadVendida.setVisible(true);
    }

    public void abrirCrearCliente() {
        jd_createCliente.pack();
        jd_createCliente.setLocationRelativeTo(this);
        jd_createCliente.setVisible(true);
    }

    public void abrirCrearAgente() {
        jd_createAgente.pack();
        jd_createAgente.setLocationRelativeTo(this);
        jd_createAgente.setVisible(true);
    }

    public void abrirCrearcAdmin() {
        jd_createAdmin.pack();
        jd_createAdmin.setLocationRelativeTo(this);
        jd_createAdmin.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Administrador;
    private javax.swing.JLabel Administrador1;
    private javax.swing.JLabel Administrador2;
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Bienvenido;
    private javax.swing.JLabel Bita;
    private javax.swing.JLabel Bitacora;
    private javax.swing.JPanel BitacoraPanel;
    private javax.swing.JPanel Bitacora_Panel;
    private javax.swing.JLabel DeNuevo;
    private javax.swing.JLabel Discover;
    private javax.swing.JLabel Dreams;
    private javax.swing.JLabel Estate;
    private javax.swing.JLabel Exit;
    private javax.swing.JLabel Exit2;
    private javax.swing.JLabel Exit3;
    private javax.swing.JLabel Exit4;
    private javax.swing.JPanel ExitPanel;
    private javax.swing.JPanel ExitPanel1;
    private javax.swing.JPanel ExitPanel2;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel FullBack;
    private javax.swing.JLabel Indicator;
    private javax.swing.JLabel Indicator1;
    private javax.swing.JLabel Indicator2;
    private javax.swing.JPanel Layer;
    private javax.swing.JPanel Layer1;
    private javax.swing.JPanel Layer2;
    private javax.swing.JPanel Line;
    private javax.swing.JPanel Line1;
    private javax.swing.JPanel Line2;
    private javax.swing.JLabel Lines1;
    private javax.swing.JLabel Lines2;
    private javax.swing.JLabel Lines3;
    private javax.swing.JLabel Lines4;
    private javax.swing.JLabel ListPanel;
    private javax.swing.JLabel ListPanel1;
    private javax.swing.JLabel ListPanel2;
    private javax.swing.JButton Log;
    private javax.swing.JLabel LogIN;
    private javax.swing.JLabel Login_Background;
    private javax.swing.JFrame MainScreen_Admin;
    private javax.swing.JFrame MainScreen_Agente;
    private javax.swing.JFrame MainScreen_Others;
    private javax.swing.JLabel Mantenimiento;
    private javax.swing.JPanel MantenimientoPanel;
    private javax.swing.JCheckBox Mostrar_Pass;
    private javax.swing.JPanel OptionPanel;
    private javax.swing.JPanel OptionPanel2;
    private javax.swing.JPanel OptionVendedor;
    private javax.swing.JLabel Options;
    private javax.swing.JLabel Options2;
    private javax.swing.JLabel OptionsVend;
    private javax.swing.JLabel Pass;
    private javax.swing.JLabel PropCompradas;
    private javax.swing.JLabel PropMarket;
    private javax.swing.JLabel PropMercado1;
    private javax.swing.JLabel PropSold;
    private javax.swing.JLabel PropVendidas;
    private javax.swing.JLabel Prop_en_mercado;
    private javax.swing.JPanel PropiedadesVendidas;
    private javax.swing.JPanel Propiedades_Mercado;
    private javax.swing.JLabel Real;
    private javax.swing.JLabel Real3;
    private javax.swing.JLabel Real4;
    private javax.swing.JLabel Real5;
    private javax.swing.JPanel Real_Estate;
    private javax.swing.JButton Regresar;
    private javax.swing.JLabel Reportes;
    private javax.swing.JPanel ReportesPanel;
    private javax.swing.JLabel SmallHouse;
    private javax.swing.JLabel SmallHouse1;
    private javax.swing.JLabel SmallHouse2;
    private javax.swing.JLabel State;
    private javax.swing.JLabel State1;
    private javax.swing.JLabel State2;
    private javax.swing.JLabel User;
    private javax.swing.JTextField Username;
    private javax.swing.JLabel backArrow2;
    private javax.swing.JLabel backArrow3;
    private javax.swing.JLabel backArrow4;
    private javax.swing.JLabel backArrow5;
    private javax.swing.JLabel backArrow6;
    private javax.swing.JLabel backArrow7;
    private javax.swing.JLabel backArrow8;
    private javax.swing.JButton bt_addAdmin;
    private javax.swing.JButton bt_addAgente;
    private javax.swing.JButton bt_addComprador;
    private javax.swing.JButton bt_addPVendida;
    private javax.swing.JButton bt_addVendedor;
    private javax.swing.JButton bt_admin;
    private javax.swing.JButton bt_closeImage;
    private javax.swing.JButton bt_crearAdmin;
    private javax.swing.JButton bt_crearAgente;
    private javax.swing.JButton bt_crearCliente;
    private javax.swing.JButton bt_crearPropiedadMerc;
    private javax.swing.JButton bt_crearPropiedadVendida;
    private javax.swing.JButton bt_imgM;
    private javax.swing.JButton bt_imgV;
    private javax.swing.JButton bt_pMercado;
    private javax.swing.JButton btnCargarVentasAgente;
    private javax.swing.JButton btnComprasComprador;
    private javax.swing.JButton btnValorVentaAnio;
    private javax.swing.JButton btnVentasCaracteristicas;
    private javax.swing.JButton btnVentasPrecio;
    private javax.swing.JButton btnVentasUbicacion;
    private javax.swing.JButton btnVentasVendedor;
    private javax.swing.JButton btn_agentes;
    private javax.swing.JButton btn_pMercado;
    private javax.swing.JButton btn_pVenta;
    private javax.swing.JButton btn_vendedor;
    private javax.swing.JButton btn_vendedor1;
    private javax.swing.ButtonGroup btng_tipoCliente;
    private javax.swing.JButton btnventaAnual;
    private javax.swing.JComboBox<String> cb_agente;
    private javax.swing.JComboBox<String> cb_agentem;
    private javax.swing.JComboBox<String> cb_comprador;
    private javax.swing.JComboBox<String> cb_vendedor;
    private javax.swing.JComboBox<String> cb_vendedorm;
    private com.toedter.calendar.JDateChooser dc_comprapv;
    private com.toedter.calendar.JDateChooser dc_fppm;
    private com.toedter.calendar.JDateChooser dc_ventapv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JDialog jd_createAdmin;
    private javax.swing.JDialog jd_createAgente;
    private javax.swing.JDialog jd_createCliente;
    private javax.swing.JDialog jd_createPropiedad;
    private javax.swing.JDialog jd_createPropiedadVendida;
    private javax.swing.JDialog jd_propImage;
    private javax.swing.JLabel lb_a;
    private javax.swing.JLabel lb_a1;
    private javax.swing.JLabel lb_am;
    private javax.swing.JLabel lb_am1;
    private javax.swing.JLabel lb_c;
    private javax.swing.JLabel lb_cAgente;
    private javax.swing.JLabel lb_clienteTitulo;
    private javax.swing.JLabel lb_img;
    private javax.swing.JLabel lb_pm;
    private javax.swing.JLabel lb_pv;
    private javax.swing.JLabel lb_v;
    private javax.swing.JLabel lb_vm;
    private javax.swing.JMenuItem mi_delete;
    private javax.swing.JMenuItem mi_modify;
    private javax.swing.JPanel panelVistas2;
    private javax.swing.JPanel panel_agente_menu;
    private javax.swing.JPanel panel_agentes;
    private javax.swing.JPanel panel_pBought;
    private javax.swing.JPanel panel_propEnMercadoCliente;
    private javax.swing.JPanel panel_propMercadoVendedor;
    private javax.swing.JPanel panel_propVendida;
    private javax.swing.JPanel pn_admins;
    private javax.swing.JPanel pn_agentes;
    private javax.swing.JPanel pn_compradores;
    private javax.swing.JPanel pn_createAdmin;
    private javax.swing.JPanel pn_createAdmin1;
    private javax.swing.JPanel pn_createAdmin2;
    private javax.swing.JPanel pn_createAgente;
    private javax.swing.JPanel pn_createAgente1;
    private javax.swing.JPanel pn_menuCrud;
    private javax.swing.JPanel pn_pMercado;
    private javax.swing.JPanel pn_pVenta;
    private javax.swing.JPanel pn_pmAgentes;
    private javax.swing.JPanel pn_propImage;
    private javax.swing.JPanel pn_pvAgentes1;
    private javax.swing.JPanel pn_vendedor;
    private javax.swing.JPopupMenu popupCrud;
    private javax.swing.JTable pvtable_mercado;
    private javax.swing.JTable pvtable_vendedores;
    private javax.swing.JRadioButton rbtnComprador;
    private javax.swing.JRadioButton rbtnVendedor;
    private javax.swing.JSpinner sp_cantiHabi;
    private javax.swing.JSpinner sp_propv;
    private javax.swing.JTabbedPane tabbedAgentes;
    private javax.swing.JTabbedPane tabbedVistas;
    private javax.swing.JTable tablaAgenteGoat;
    private javax.swing.JTable tabla_bitacora;
    private javax.swing.JTable tbComprasComprador;
    private javax.swing.JTable tbPromedioAnual;
    private javax.swing.JTable tbVentasAgente;
    private javax.swing.JTable tbVentasCaracteristicas;
    private javax.swing.JTable tbVentasPrecio;
    private javax.swing.JTable tbVentasUbicacion;
    private javax.swing.JTable tbVentasVendedor;
    private javax.swing.JTabbedPane tb_admin;
    private javax.swing.JTable tb_admins;
    private javax.swing.JTable tb_agentes;
    private javax.swing.JTable tb_compradores;
    private javax.swing.JTable tb_pBought;
    private javax.swing.JTable tb_pMercado;
    private javax.swing.JTable tb_propiedadesv;
    private javax.swing.JTable tb_propmAgentes;
    private javax.swing.JTable tb_propvAgentes;
    private javax.swing.JTable tb_vendedores;
    private javax.swing.JTable tc_propMercado;
    private javax.swing.JTextField tf_CiudadMerc;
    private javax.swing.JTextField tf_CiudadMerc1;
    private javax.swing.JTextField tf_caracteriticasMerc;
    private javax.swing.JTextField tf_caracteriticasMerc1;
    private javax.swing.JTextField tf_celular;
    private javax.swing.JTextField tf_celularCliente;
    private javax.swing.JTextField tf_comision;
    private javax.swing.JTextField tf_direccion;
    private javax.swing.JTextField tf_direccionCliente;
    private javax.swing.JTextField tf_direccionMerc;
    private javax.swing.JTextField tf_direccionMerc1;
    private javax.swing.JTextField tf_identidad;
    private javax.swing.JTextField tf_identidadCliente;
    private javax.swing.JTextField tf_imgM;
    private javax.swing.JTextField tf_imgV;
    private javax.swing.JTextField tf_nombre;
    private javax.swing.JTextField tf_nombreCliente;
    private javax.swing.JTextField tf_nombreMerc;
    private javax.swing.JTextField tf_nombreMerc1;
    private javax.swing.JTextField tf_oficina;
    private javax.swing.JTextField tf_password;
    private javax.swing.JTextField tf_precioMerc;
    private javax.swing.JTextField tf_precioMerc1;
    private javax.swing.JTextField tf_username;
    private javax.swing.JPanel vista1;
    private javax.swing.JPanel vista2;
    private javax.swing.JPanel vista3;
    private javax.swing.JPanel vista4;
    private javax.swing.JPanel vista5;
    private javax.swing.JPanel vista6;
    private javax.swing.JPanel vista7;
    private javax.swing.JPanel vista8;
    private javax.swing.JTextField yearTextField;
    private javax.swing.JTextField yearTextField1;
    // End of variables declaration//GEN-END:variables
}
