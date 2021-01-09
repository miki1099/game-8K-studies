package gui;

import gameLogic.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Gui game panel main game view
 * @author mikig
 */
public class GamePanel extends javax.swing.JPanel {
    /** Game logic instance */
    private GameLogic gameLogic;
    /** List where are climber 1 icons in JLabels */
    private List<JLabel> climber1Icons;
    /** List where are climber 2 icons in JLabels */
    private List<JLabel> climber2Icons;
    /** Map with sites JLabel and it's parameters left */
    private Map<JLabel, SiteParameters> sitesWithParametersMapLeft;
    /** Map with sites JLabel and it's parameters right */
    private Map<JLabel, SiteParameters> sitesWithParametersMapRight;
    /** Checks if climber is clicked to move */
    private boolean isGoingToMoveClimber;
    /** Label where is climber witch want to move */
    private JLabel currentPositionReadyToMove;
    /** Confirm takt other method can change items list */
    private boolean isReadyToChange;
    /** Is game lost */
    private boolean isGameLost;
    /** Alive climber 1 icon */
    private final Icon ICON_CLIMBER_1 = new ImageIcon(getClass().getResource("/gui/Graphics/climber.png"));
    /** Alive climber 2 icon */
    private final Icon ICON_CLIMBER_2 = new ImageIcon(getClass().getResource("/gui/Graphics/climber (1).png"));
    /** Dead climber 1 icon */
    private final Icon ICON_CLIMBER_1_DEAD = new ImageIcon(getClass().getResource("/gui/Graphics/climber dead.png"));
    /** Dead climber 2 icon */
    private final Icon ICON_CLIMBER_2_DEAD = new ImageIcon(getClass().getResource("/gui/Graphics/climber (1) dead.png"));
    /** Weather icon sunny */
    private final Icon ICON_SUN = new ImageIcon(getClass().getResource("/gui/Graphics/sunny.png"));
    /** Weather icon clouds */
    private final Icon ICON_CLOUDS = new ImageIcon(getClass().getResource("/gui/Graphics/clouds.png"));
    /** Weather icon snow */
    private final Icon ICON_SNOW = new ImageIcon(getClass().getResource("/gui/Graphics/snow.png"));
    /** Items icon backpack */
    private final Icon ICON_BACKPACK = new ImageIcon(getClass().getResource("/gui/Graphics/backpack.png"));
    /** Items icon tent */
    private final Icon ICON_TENT = new ImageIcon(getClass().getResource("/gui/Graphics/camping.png"));

    /**
     *
     * Creates new form GamePanel
     */
    public GamePanel() {
        initComponents();
        gamePrepare();
        constructGameLogicInstance();
        itemListUpdate();
        updateClimbersStatus();
        updateWeather();
        updatePredictedImpact();
    }

    /**
     *
     * Creates sites set current climbers positions
     */
    private void constructGameLogicInstance(){
        List<JLabel> currentPosLabels = new ArrayList<>();
        List<SiteParameters> siteParameters = new ArrayList<>();
        currentPosLabels.add(baseLeft);
        currentPosLabels.add(baseRight);
        siteParameters.add(new SiteParameters((short) 0, Routes.E,Routes.D,Routes.C));
        siteParameters.add(new SiteParameters((short) 0, Routes.E,Routes.D,Routes.C));
        gameLogic = GameLogic.getInstance(currentPosLabels, siteParameters);
    }

    /**
     *
     * Mapping sites, adds climber icons to list
     */
    private void gamePrepare(){
        isGameLost = false;
        climber1Icons = new ArrayList<>();
        climber2Icons = new ArrayList<>();
        sitesWithParametersMapLeft = new HashMap<>();
        sitesWithParametersMapRight = new HashMap<>();

        isGoingToMoveClimber = false;

        climber1Icons.add(climber1Icon);
        climber1Icons.add(climber1Icon1);
        climber1Icons.add(climber1Icon2);
        climber2Icons.add(climber2Icon);
        climber2Icons.add(climber2Icon1);
        climber2Icons.add(climber2Icon2);
        //mapping left sides
        siteMappingMethod(sitesWithParametersMapLeft, baseLeft, secondSiteLeft, thirdSiteCRouteLeft,
                fourthSiteCRouteLeft, fifthSiteCRouteLeft, topSiteLeft, thirdSiteLeft, fourthSiteDRouteLeft,
                fifthSiteDRouteLeft, fourthSiteERouteLeft, fifthSiteERouteLeft);
        //mapping right sides
        siteMappingMethod(sitesWithParametersMapRight, baseRight, secondSiteRight, thirdSiteCRouteRight,
                fourthSiteCRouteRight, fifthSiteCRouteRight, topSiteRight, thirdSiteRight, fourthSiteDRouteRight,
                fifthSiteDRouteRight, fourthSiteERouteRight, fifthSiteERouteRight);


    }

    /**
     *
     * Method that create Map os sites with their parameters
     */
    private void siteMappingMethod(Map<JLabel, SiteParameters> sitesWithParametersMap, JLabel base, JLabel secondSite,
                                   JLabel thirdSiteCRoute, JLabel fourthSiteCRoute, JLabel fifthSiteCRoute,
                                   JLabel topSite, JLabel thirdSite, JLabel fourthSiteDRoute, JLabel fifthSiteDRoute,
                                   JLabel fourthSiteERoute, JLabel fifthSiteERoute) {
        sitesWithParametersMap.put(base, new SiteParameters((short) 0, Routes.E,Routes.D,Routes.C));
        sitesWithParametersMap.put(secondSite, new SiteParameters((short) 1, Routes.E,Routes.D,Routes.C));
        sitesWithParametersMap.put(thirdSiteCRoute, new SiteParameters((short) 2, Routes.C));
        sitesWithParametersMap.put(fourthSiteCRoute, new SiteParameters((short) 3, Routes.C));
        sitesWithParametersMap.put(fifthSiteCRoute, new SiteParameters((short) 4, Routes.C));
        sitesWithParametersMap.put(topSite, new SiteParameters((short) 5, Routes.E,Routes.D,Routes.C));

        sitesWithParametersMap.put(thirdSite, new SiteParameters((short) 2, Routes.D, Routes.E));
        sitesWithParametersMap.put(fourthSiteDRoute, new SiteParameters((short) 3, Routes.D));
        sitesWithParametersMap.put(fifthSiteDRoute, new SiteParameters((short) 4, Routes.D));

        sitesWithParametersMap.put(fourthSiteERoute, new SiteParameters((short) 3, Routes.E));
        sitesWithParametersMap.put(fifthSiteERoute, new SiteParameters((short) 4, Routes.E));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        climber1Icon1 = new javax.swing.JLabel();
        climber2Icon1 = new javax.swing.JLabel();
        acclimatizationClimber2 = new javax.swing.JProgressBar();
        acclimatizationClimber1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        JPanel baseSite = new JPanel();
        baseLeft = new javax.swing.JLabel();
        baseRight = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        secondSite = new javax.swing.JPanel();
        secondSiteLeft = new javax.swing.JLabel();
        secondSiteRight = new javax.swing.JLabel();
        thirdSiteCRoute = new javax.swing.JPanel();
        thirdSiteCRouteLeft = new javax.swing.JLabel();
        thirdSiteCRouteRight = new javax.swing.JLabel();
        fourthSiteCRoute = new javax.swing.JPanel();
        fourthSiteCRouteLeft = new javax.swing.JLabel();
        fourthSiteCRouteRight = new javax.swing.JLabel();
        fifthSiteCRoute = new javax.swing.JPanel();
        fifthSiteCRouteLeft = new javax.swing.JLabel();
        fifthSiteCRouteRight = new javax.swing.JLabel();
        topSite = new javax.swing.JPanel();
        topSiteLeft = new javax.swing.JLabel();
        topSiteRight = new javax.swing.JLabel();
        thirdSite = new javax.swing.JPanel();
        thirdSiteLeft = new javax.swing.JLabel();
        thirdSiteRight = new javax.swing.JLabel();
        fourthSiteDRoute = new javax.swing.JPanel();
        fourthSiteDRouteLeft = new javax.swing.JLabel();
        fourthSiteDRouteRight = new javax.swing.JLabel();
        fifthSiteDRoute = new javax.swing.JPanel();
        fifthSiteDRouteLeft = new javax.swing.JLabel();
        fifthSiteDRouteRight = new javax.swing.JLabel();
        fourthSiteERoute = new javax.swing.JPanel();
        fourthSiteERouteLeft = new javax.swing.JLabel();
        fourthSiteERouteRight = new javax.swing.JLabel();
        fifthSiteERoute = new javax.swing.JPanel();
        fifthSiteERouteLeft = new javax.swing.JLabel();
        fifthSiteERouteRight = new javax.swing.JLabel();
        climber1Icon = new javax.swing.JLabel();
        climber2Icon = new javax.swing.JLabel();
        itemsList1 = new javax.swing.JComboBox<>();
        itemsList2 = new javax.swing.JComboBox<>();
        weatherIcon = new javax.swing.JLabel();
        tampLabel = new javax.swing.JLabel();
        nextDayButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        dayCounter = new javax.swing.JLabel();
        tempLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        predictedAccImpactClimber2Label = new javax.swing.JLabel();
        predictedAccImpactClimber1Label = new javax.swing.JLabel();
        climber1Icon2 = new javax.swing.JLabel();
        climber2Icon2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1280, 1024));


        climber1Icon1.setIcon(ICON_CLIMBER_1); // NOI18N
        climber1Icon1.setToolTipText("");
        climber2Icon1.setIcon(ICON_CLIMBER_2); // NOI18N
        climber2Icon1.setToolTipText("");

        acclimatizationClimber2.setStringPainted(true);

        acclimatizationClimber1.setStringPainted(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Climber 1");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Climber 2");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Acclimation");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Acclimation");

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(800, 1024));

        baseLeft.setIcon(ICON_CLIMBER_1); // NOI18N
        baseLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        baseLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, baseLeft);
            }
        });

        baseRight.setIcon(ICON_CLIMBER_2); // NOI18N
        baseRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        baseRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt){
                rightSiteMouseClicked(evt, baseRight);
            }
        });

        javax.swing.GroupLayout baseSiteLayout = new javax.swing.GroupLayout(baseSite);
        baseSite.setLayout(baseSiteLayout);
        baseSiteLayout.setHorizontalGroup(
                baseSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(baseSiteLayout.createSequentialGroup()
                                .addComponent(baseLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(baseRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        baseSiteLayout.setVerticalGroup(
                baseSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(baseLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(baseRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Graphics/gameBoard.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(104, 104, 104))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        secondSiteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        secondSiteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, secondSiteLeft);
            }
        });

        secondSiteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        secondSiteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, secondSiteRight);
            }
        });

        javax.swing.GroupLayout secondSiteLayout = new javax.swing.GroupLayout(secondSite);
        secondSite.setLayout(secondSiteLayout);
        secondSiteLayout.setHorizontalGroup(
                secondSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(secondSiteLayout.createSequentialGroup()
                                .addComponent(secondSiteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secondSiteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        secondSiteLayout.setVerticalGroup(
                secondSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(secondSiteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(secondSiteRight, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        thirdSiteCRouteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        thirdSiteCRouteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, thirdSiteCRouteLeft);
            }
        });

        thirdSiteCRouteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        thirdSiteCRouteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, thirdSiteCRouteRight);
            }
        });

        javax.swing.GroupLayout thirdSiteCRouteLayout = new javax.swing.GroupLayout(thirdSiteCRoute);
        thirdSiteCRoute.setLayout(thirdSiteCRouteLayout);
        thirdSiteCRouteLayout.setHorizontalGroup(
                thirdSiteCRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(thirdSiteCRouteLayout.createSequentialGroup()
                                .addComponent(thirdSiteCRouteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thirdSiteCRouteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        thirdSiteCRouteLayout.setVerticalGroup(
                thirdSiteCRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(thirdSiteCRouteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(thirdSiteCRouteRight, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        fourthSiteCRouteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fourthSiteCRouteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, fourthSiteCRouteLeft);
            }
        });

        fourthSiteCRouteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fourthSiteCRouteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, fourthSiteCRouteRight);
            }
        });

        javax.swing.GroupLayout fourthSiteCRouteLayout = new javax.swing.GroupLayout(fourthSiteCRoute);
        fourthSiteCRoute.setLayout(fourthSiteCRouteLayout);
        fourthSiteCRouteLayout.setHorizontalGroup(
                fourthSiteCRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fourthSiteCRouteLayout.createSequentialGroup()
                                .addComponent(fourthSiteCRouteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fourthSiteCRouteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        fourthSiteCRouteLayout.setVerticalGroup(
                fourthSiteCRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fourthSiteCRouteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(fourthSiteCRouteRight, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        fifthSiteCRouteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fifthSiteCRouteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, fifthSiteCRouteLeft);
            }
        });

        fifthSiteCRouteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fifthSiteCRouteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, fifthSiteCRouteRight);
            }
        });

        javax.swing.GroupLayout fifthSiteCRouteLayout = new javax.swing.GroupLayout(fifthSiteCRoute);
        fifthSiteCRoute.setLayout(fifthSiteCRouteLayout);
        fifthSiteCRouteLayout.setHorizontalGroup(
                fifthSiteCRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fifthSiteCRouteLayout.createSequentialGroup()
                                .addComponent(fifthSiteCRouteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fifthSiteCRouteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        fifthSiteCRouteLayout.setVerticalGroup(
                fifthSiteCRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fifthSiteCRouteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(fifthSiteCRouteRight, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        topSiteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        topSiteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, topSiteLeft);
            }
        });

        topSiteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        topSiteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, topSiteRight);
            }
        });

        javax.swing.GroupLayout topSiteLayout = new javax.swing.GroupLayout(topSite);
        topSite.setLayout(topSiteLayout);
        topSiteLayout.setHorizontalGroup(
                topSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(topSiteLayout.createSequentialGroup()
                                .addComponent(topSiteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(topSiteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topSiteLayout.setVerticalGroup(
                topSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(topSiteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(topSiteRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        thirdSiteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        thirdSiteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, thirdSiteLeft);
            }
        });

        thirdSiteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        thirdSiteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, thirdSiteRight);
            }
        });

        javax.swing.GroupLayout thirdSiteLayout = new javax.swing.GroupLayout(thirdSite);
        thirdSite.setLayout(thirdSiteLayout);
        thirdSiteLayout.setHorizontalGroup(
                thirdSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(thirdSiteLayout.createSequentialGroup()
                                .addComponent(thirdSiteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thirdSiteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        thirdSiteLayout.setVerticalGroup(
                thirdSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(thirdSiteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(thirdSiteRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fourthSiteDRouteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fourthSiteDRouteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt,fourthSiteDRouteLeft);
            }
        });

        fourthSiteDRouteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fourthSiteDRouteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, fourthSiteDRouteRight);
            }
        });

        javax.swing.GroupLayout fourthSiteDRouteLayout = new javax.swing.GroupLayout(fourthSiteDRoute);
        fourthSiteDRoute.setLayout(fourthSiteDRouteLayout);
        fourthSiteDRouteLayout.setHorizontalGroup(
                fourthSiteDRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fourthSiteDRouteLayout.createSequentialGroup()
                                .addComponent(fourthSiteDRouteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fourthSiteDRouteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        fourthSiteDRouteLayout.setVerticalGroup(
                fourthSiteDRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fourthSiteDRouteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(fourthSiteDRouteRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fifthSiteDRouteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fifthSiteDRouteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, fifthSiteDRouteLeft);
            }
        });

        fifthSiteDRouteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fifthSiteDRouteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, fifthSiteDRouteRight);
            }
        });

        javax.swing.GroupLayout fifthSiteDRouteLayout = new javax.swing.GroupLayout(fifthSiteDRoute);
        fifthSiteDRoute.setLayout(fifthSiteDRouteLayout);
        fifthSiteDRouteLayout.setHorizontalGroup(
                fifthSiteDRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fifthSiteDRouteLayout.createSequentialGroup()
                                .addComponent(fifthSiteDRouteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fifthSiteDRouteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        fifthSiteDRouteLayout.setVerticalGroup(
                fifthSiteDRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fifthSiteDRouteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(fifthSiteDRouteRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fourthSiteERouteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fourthSiteERouteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, fourthSiteERouteLeft);
            }
        });

        fourthSiteERouteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fourthSiteERouteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, fourthSiteERouteRight);
            }
        });

        javax.swing.GroupLayout fourthSiteERouteLayout = new javax.swing.GroupLayout(fourthSiteERoute);
        fourthSiteERoute.setLayout(fourthSiteERouteLayout);
        fourthSiteERouteLayout.setHorizontalGroup(
                fourthSiteERouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fourthSiteERouteLayout.createSequentialGroup()
                                .addComponent(fourthSiteERouteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fourthSiteERouteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        fourthSiteERouteLayout.setVerticalGroup(
                fourthSiteERouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fourthSiteERouteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(fourthSiteERouteRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fifthSiteERouteLeft.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fifthSiteERouteLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftSiteMouseClicked(evt, fifthSiteERouteLeft);
            }
        });

        fifthSiteERouteRight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fifthSiteERouteRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightSiteMouseClicked(evt, fifthSiteERouteRight);
            }
        });

        javax.swing.GroupLayout fifthSiteERouteLayout = new javax.swing.GroupLayout(fifthSiteERoute);
        fifthSiteERoute.setLayout(fifthSiteERouteLayout);
        fifthSiteERouteLayout.setHorizontalGroup(
                fifthSiteERouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fifthSiteERouteLayout.createSequentialGroup()
                                .addComponent(fifthSiteERouteLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fifthSiteERouteRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        fifthSiteERouteLayout.setVerticalGroup(
                fifthSiteERouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fifthSiteERouteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(fifthSiteERouteRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(baseSite, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(secondSite, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(thirdSiteCRoute, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(fourthSiteCRoute, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(fifthSiteCRoute, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(topSite, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(thirdSite, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(fourthSiteDRoute, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(fifthSiteDRoute, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(fourthSiteERoute, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(fifthSiteERoute, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(402, 402, 402)
                                        .addComponent(baseSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(402, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(369, 369, 369)
                                        .addComponent(secondSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(429, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(142, 142, 142)
                                        .addComponent(thirdSiteCRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(656, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(179, 179, 179)
                                        .addComponent(fourthSiteCRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(619, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(262, 262, 262)
                                        .addComponent(fifthSiteCRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(536, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(272, 272, 272)
                                        .addComponent(topSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(526, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(485, 485, 485)
                                        .addComponent(thirdSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(313, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addContainerGap(495, Short.MAX_VALUE)
                                        .addComponent(fourthSiteDRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(303, 303, 303)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addContainerGap(412, Short.MAX_VALUE)
                                        .addComponent(fifthSiteDRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(386, 386, 386)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addContainerGap(648, Short.MAX_VALUE)
                                        .addComponent(fourthSiteERoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(150, 150, 150)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addContainerGap(603, Short.MAX_VALUE)
                                        .addComponent(fifthSiteERoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(195, 195, 195)))
        );
        jLayeredPane1Layout.setVerticalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(886, 886, 886)
                                        .addComponent(baseSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(88, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(734, 734, 734)
                                        .addComponent(secondSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(234, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addContainerGap(547, Short.MAX_VALUE)
                                        .addComponent(thirdSiteCRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(427, 427, 427)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addContainerGap(377, Short.MAX_VALUE)
                                        .addComponent(fourthSiteCRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(592, 592, 592)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(289, 289, 289)
                                        .addComponent(fifthSiteCRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(685, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(topSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(956, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addContainerGap(520, Short.MAX_VALUE)
                                        .addComponent(thirdSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(454, 454, 454)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addContainerGap(367, Short.MAX_VALUE)
                                        .addComponent(fourthSiteDRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(607, 607, 607)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(215, 215, 215)
                                        .addComponent(fifthSiteDRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(759, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(377, 377, 377)
                                        .addComponent(fourthSiteERoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(597, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(230, 230, 230)
                                        .addComponent(fifthSiteERoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(744, Short.MAX_VALUE)))
        );

        climber1Icon.setIcon(ICON_CLIMBER_1); // NOI18N
        climber1Icon.setToolTipText("");

        climber2Icon.setIcon(ICON_CLIMBER_2); // NOI18N
        climber2Icon.setToolTipText("");

        itemsList1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                thingsList1ItemStateChanged(evt);
            }
        });

        itemsList2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                thingsList2ItemStateChanged(evt);
            }
        });

        weatherIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Graphics/sunny.png"))); // NOI18N

        tampLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tampLabel.setText("Temperature:");

        nextDayButton.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        nextDayButton.setText("Next day");
        nextDayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextDayButtonActionPerformed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Day:");

        dayCounter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dayCounter.setText("1");

        tempLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tempLabel.setText("20");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Impact on acclimation for next day: ");

        predictedAccImpactClimber2Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        predictedAccImpactClimber1Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        climber1Icon2.setIcon(ICON_CLIMBER_1); // NOI18N
        climber1Icon2.setToolTipText("");

        climber2Icon2.setIcon(ICON_CLIMBER_2); // NOI18N
        climber2Icon2.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(128, 128, 128)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel1)
                                                                        .addGap(65, 65, 65)
                                                                        .addComponent(jLabel3))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel2)
                                                                        .addGap(61, 61, 61)
                                                                        .addComponent(jLabel5))))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(64, 64, 64)
                                                        .addComponent(climber1Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGap(92, 92, 92)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(itemsList1, 0, 107, Short.MAX_VALUE)
                                                                                .addComponent(itemsList2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGap(111, 111, 111))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(acclimatizationClimber1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGap(64, 64, 64)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(nextDayButton, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                                                .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(climber1Icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(climber2Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(acclimatizationClimber2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(climber2Icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(weatherIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(101, 101, 101)
                                                                .addComponent(tampLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tempLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(dayCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(climber1Icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(predictedAccImpactClimber1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(predictedAccImpactClimber2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(climber2Icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(28, 28, 28)))))))
                                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(acclimatizationClimber1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(climber1Icon))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(acclimatizationClimber2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(climber2Icon))
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(climber1Icon1)
                                        .addComponent(itemsList1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(climber2Icon1)
                                        .addComponent(itemsList2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(weatherIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tampLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tempLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dayCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(climber1Icon2)
                                        .addComponent(climber2Icon2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(predictedAccImpactClimber1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(nextDayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(predictedAccImpactClimber2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>



    // added methods

    //start update methods block
    /**
     *
     * Update climber icons
     */
    private void updateIcon(Icon icon, List<JLabel> labels, JLabel currPos){
        currPos.setIcon(icon);
        for(JLabel label : labels){
            label.setIcon(icon);
        }
    }

    /**
     *
     * Update climber status chcecks if they are alive and update icons if they are dead
     */
    private void updateClimbersStatus(){
        int counter = 0;
        for(short acclimation : gameLogic.getClimbersAcclimation()){
            if(counter == 0){
                acclimatizationClimber1.setValue(acclimation);
            } else if(counter == 1){
                acclimatizationClimber2.setValue(acclimation);
            }
            counter++;
        }

        counter = 0;
        short deadCounter = 0;
        List<Boolean> climbersAreAliveList = gameLogic.checkClimbersAreAlive();
        for(boolean isAlive : climbersAreAliveList){
            if(!isAlive){
                deadCounter++;
                if(counter == 0){
                    JLabel currPos = gameLogic.getCurrentClimbersPosition().get(counter);
                    updateIcon(ICON_CLIMBER_1_DEAD,  climber1Icons, currPos);
                } else if(counter == 1){
                    JLabel currPos = gameLogic.getCurrentClimbersPosition().get(counter);
                    updateIcon(ICON_CLIMBER_2_DEAD,  climber2Icons, currPos);
                }
                if(deadCounter == climbersAreAliveList.size()){
                    endGameLoose();
                }
            }

            counter++;
        }
    }

    /**
     *
     * Updates climber items list
     */
    private void itemListUpdate() {
        isReadyToChange = false;
        List<Item> items = gameLogic.getItemsList();
        itemsList1.removeAllItems();
        itemsList2.removeAllItems();
        Item buf = items.get(0);
        itemsList1.addItem(buf);
        if(!(buf instanceof BlankItem)){
            itemsList1.addItem(items.get(1));
        }
        buf = items.get(2);
        itemsList2.addItem(items.get(2));
        if(!(buf instanceof BlankItem)){
            itemsList2.addItem(items.get(3));
        }
        isReadyToChange = true;

    }

    /**
     *
     * Update weather and weather icon
     */
    private void updateWeather(){
        WeatherType weatherType = gameLogic.getWeatherType();
        tempLabel.setText(gameLogic.getTemperature()+"");
        switch (weatherType){
            case SUN:
                weatherIcon.setIcon(ICON_SUN);
                break;
            case CLOUDS:
                weatherIcon.setIcon(ICON_CLOUDS);
                break;
            case SNOW:
                weatherIcon.setIcon(ICON_SNOW);
                break;
        }
    }

    /**
     *
     * Update predicted impact for all climbers
     */
    private void updatePredictedImpact(){
        List<Short> nightImpactList = gameLogic.getImpactForNextDay();
        predictedAccImpactClimber1Label.setText(nightImpactList.get(0)+"");
        predictedAccImpactClimber2Label.setText(nightImpactList.get(1)+"");
    }

    /**
     *
     * Update day counter
     */
    private void updateDayCounter(){
        dayCounter.setText(gameLogic.getDay()+"");
    }

    private void updateItemsIcons(){
        List<Item> itemsLeft = gameLogic.getItemsLeft();
        for(Item item : itemsLeft){
            if(item instanceof Backpack){
                item.getActualSite().setIcon(ICON_BACKPACK);
            } else if(item instanceof Tent){
                item.getActualSite().setIcon(ICON_TENT);
            }
        }
    }
    //end update methods block

    //start moving methods block
    /**
     *
     * Makes climber ready to move also visual
     */
    private void makeReadyToMove(JLabel componentClicked){
        if(gameLogic.getCurrentClimbersPosition().contains(componentClicked)){
            isGoingToMoveClimber = true;
            currentPositionReadyToMove = componentClicked;
            componentClicked.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
    }

    /**
     *
     * Make climber un ready to move albo visual
     */
    private void makeUnreadyToMove(){
        isGoingToMoveClimber = false;
        currentPositionReadyToMove.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED));
        currentPositionReadyToMove = null;
    }

    /**
     *
     * Makes move call update icon methods and inform GameLogic class
     */
    private void makeMove(JLabel componentClicked, Map<JLabel, SiteParameters> sitesWithParametersMap){
        short impactBuf = gameLogic.getImpactFromMoving(sitesWithParametersMap, componentClicked, currentPositionReadyToMove);
        if(impactBuf != 127){
            int ans = JOptionPane.showConfirmDialog(this,
                    "Impact on acclimation:\n" + impactBuf,
                    "Acclimation impact", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(ans == JOptionPane.YES_OPTION){
                if(gameLogic.moveClimberAndShowResults(sitesWithParametersMap, componentClicked, currentPositionReadyToMove, impactBuf)){
                    componentClicked.setIcon(currentPositionReadyToMove.getIcon());
                    currentPositionReadyToMove.setIcon(null);
                }
            }
        }
        makeUnreadyToMove();
    }

    /**
     *
     * every JLabel on game board clicked refer to this method it choose what should be done
     */
    private void clickOperation(JLabel componentClicked, Map<JLabel, SiteParameters> sitesWithParametersMap){
        if(isGoingToMoveClimber){
            List<JLabel> positionList = gameLogic.getCurrentClimbersPosition();
            if(gameLogic.getCurrentPositionsItemsLeft().contains(componentClicked) &&
                    !gameLogic.doesClimberCanStandOnItem(currentPositionReadyToMove, componentClicked)){
                makeUnreadyToMove();
            } else if(positionList.contains(componentClicked)){
                makeUnreadyToMove();
            } else {
                makeMove(componentClicked, sitesWithParametersMap);
                updateClimbersStatus();
                updatePredictedImpact();
                itemListUpdate();
                updateItemsIcons();
            }
        } else {
            makeReadyToMove(componentClicked);
        }
    }
    // end moving methods

    //game finished methods
    /**
     *
     * Shows dialog to inform that gamie is lost end exit to menu
     */
    public void endGameLoose(){
        JOptionPane.showMessageDialog(this,
                "Game over\nall climbers are dead",
                "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
        Window.exitToMenu();
        isGameLost = true;
    }

    /**
     *
     * Goes to scoreboard panel
     * if database is not available shows dialog with score and exit to menu
     */
    public void endGameWin(){
        if(Window.isDataBaseConnected){
            Window.scoreBoard(gameLogic.getWinScore());
        } else{
            JOptionPane.showMessageDialog(this,
                    "Database is not available\nyour score is: " + gameLogic.getWinScore(),
                    "YOU WON GAME", JOptionPane.INFORMATION_MESSAGE);
            Window.exitToMenu();
        }

    }
    //end game finished methods

    // invoke move operation
    /**
     *
     * Action listener for right sites
     */
    private void rightSiteMouseClicked(java.awt.event.MouseEvent evt, JLabel componentClicked) {
        clickOperation(componentClicked, sitesWithParametersMapRight);
    }
    // invoke move operation
    /**
     *
     * Action listener for left sites
     */
    private void leftSiteMouseClicked(java.awt.event.MouseEvent evt, JLabel componentClicked) {
        clickOperation(componentClicked, sitesWithParametersMapLeft);
    }

    /**
     *
     * Action listener for next day button runs proper methods
     */
    private void nextDayButtonActionPerformed(java.awt.event.ActionEvent evt) {
        gameLogic.makeNextDay();
        updateWeather();
        updateDayCounter();
        updateClimbersStatus();
        updatePredictedImpact();
        if(gameLogic.isGameReadyToFinish() && !isGameLost){
            endGameWin();
        }


    }

    /**
     *
     * Action listener for exit button ask user by dialog and exit to menu
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int ans = JOptionPane.showConfirmDialog(this,
                "Do you want exit game?\nAll progress will be reset!",
                "Confirm operation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(ans == JOptionPane.YES_OPTION){
            Window.exitToMenu();
        }
    }

    /**
     *
     * Action listener item list climber 1
     */
    private void thingsList1ItemStateChanged(java.awt.event.ItemEvent evt) {
        if(isReadyToChange){
            gameLogic.setItemToClimber((Item) evt.getItem(),0);
            updatePredictedImpact();
        }

    }

    /**
     *
     * Action listener item list climber 2
     */
    private void thingsList2ItemStateChanged(java.awt.event.ItemEvent evt) {
        if(isReadyToChange){
            gameLogic.setItemToClimber((Item) evt.getItem(),1);
            updatePredictedImpact();
        }
    }


    // Variables declaration - do not modify
    /** Climber 1 icon */
    private javax.swing.JLabel climber1Icon;
    /** Climber 1 icon */
    private javax.swing.JLabel climber1Icon1;
    /** Climber 1 icon */
    private javax.swing.JLabel climber1Icon2;
    /** Climber 2 icon */
    private javax.swing.JLabel climber2Icon;
    /** Climber 2 icon */
    private javax.swing.JLabel climber2Icon1;
    /** Climber 2 icon */
    private javax.swing.JLabel climber2Icon2;
    /** Climber 1 acclimation progress bar */
    private javax.swing.JProgressBar acclimatizationClimber1;
    /** Climber 2 acclimation progress bar */
    private javax.swing.JProgressBar acclimatizationClimber2;
    private javax.swing.JLabel baseLeft;
    private javax.swing.JLabel baseRight;
    private javax.swing.JLabel dayCounter;
    /** Exit button */
    private javax.swing.JButton exitButton;
    /** site */
    private javax.swing.JPanel fifthSiteCRoute;
    /** site */
    private javax.swing.JLabel fifthSiteCRouteLeft;
    /** site */
    private javax.swing.JLabel fifthSiteCRouteRight;
    /** site */
    private javax.swing.JPanel fifthSiteDRoute;
    /** site */
    private javax.swing.JLabel fifthSiteDRouteLeft;
    /** site */
    private javax.swing.JLabel fifthSiteDRouteRight;
    /** site */
    private javax.swing.JPanel fifthSiteERoute;
    /** site */
    private javax.swing.JLabel fifthSiteERouteLeft;
    /** site */
    private javax.swing.JLabel fifthSiteERouteRight;
    /** site */
    private javax.swing.JLabel fourthSiteCRouteLeft;
    /** site */
    private javax.swing.JPanel fourthSiteCRoute;
    /** site */
    private javax.swing.JLabel fourthSiteCRouteRight;
    /** site */
    private javax.swing.JPanel fourthSiteDRoute;
    /** site */
    private javax.swing.JLabel fourthSiteDRouteLeft;
    /** site */
    private javax.swing.JLabel fourthSiteDRouteRight;
    /** site */
    private javax.swing.JPanel fourthSiteERoute;
    /** site */
    private javax.swing.JLabel fourthSiteERouteLeft;
    /** site */
    private javax.swing.JLabel fourthSiteERouteRight;
    /** (Generated) */
    private javax.swing.JLabel jLabel1;
    /** (Generated) */
    private javax.swing.JLabel jLabel2;
    /** (Generated) */
    private javax.swing.JLabel jLabel3;
    /** (Generated) */
    private javax.swing.JLabel jLabel4;
    /** (Generated) */
    private javax.swing.JLabel jLabel5;
    /** (Generated) */
    private javax.swing.JLabel jLabel6;
    /** (Generated) */
    private javax.swing.JLabel jLabel7;
    /** (Generated) */
    private javax.swing.JLayeredPane jLayeredPane1;
    /** (Generated) */
    private javax.swing.JPanel jPanel1;
    /** Day button */
    private javax.swing.JButton nextDayButton;
    /** Predicted impact on acclimation for climber 1 */
    private javax.swing.JLabel predictedAccImpactClimber1Label;
    /** Predicted impact on acclimation for climber 2 */
    private javax.swing.JLabel predictedAccImpactClimber2Label;
    /** site */
    private javax.swing.JPanel secondSite;
    /** site */
    private javax.swing.JLabel secondSiteLeft;
    /** site */
    private javax.swing.JLabel secondSiteRight;
    /** (Generated) */
    private javax.swing.JLabel tampLabel;
    /** (Generated) */
    private javax.swing.JLabel tempLabel;
    /** Climber 1 item list */
    private javax.swing.JComboBox<Item> itemsList1;
    /** Climber 1 item list */
    private javax.swing.JComboBox<Item> itemsList2;
    /** site */
    private javax.swing.JPanel thirdSite;
    /** site */
    private javax.swing.JPanel thirdSiteCRoute;
    /** site */
    private javax.swing.JLabel thirdSiteCRouteLeft;
    /** site */
    private javax.swing.JLabel thirdSiteCRouteRight;
    /** site */
    private javax.swing.JLabel thirdSiteLeft;
    /** site */
    private javax.swing.JLabel thirdSiteRight;
    /** site */
    private javax.swing.JPanel topSite;
    /** site */
    private javax.swing.JLabel topSiteLeft;
    /** site */
    private javax.swing.JLabel topSiteRight;
    /** site */
    private javax.swing.JLabel weatherIcon;
    // End of variables declaration
}
