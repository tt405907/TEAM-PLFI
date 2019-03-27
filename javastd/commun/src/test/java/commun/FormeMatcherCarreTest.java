package commun;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import commun.Dessin;
import commun.Forme;
import commun.Point;

public class FormeMatcherCarreTest {
    private FormeMatcher fm;

    @Before
    public void setup() {
        fm = new FormeMatcher();
    }

    @Test
    public void carreParfait() {
        Dessin carre = new Dessin(new Point[]{new Point(100, 100), new Point(300, 100), new Point(300, 300), new Point(100, 300)});

        assertEquals(Forme.CARRE, fm.identify(carre));
    }

    @Test
    public void directionTrace() {
        //Trace le carré parfait en partant de coins différents en tournant dans une direction différente
        List<Point> points = new ArrayList<>();

        points.add(new Point(500, 200));
        points.add(new Point(600, 200));
        points.add(new Point(600, 300));
        points.add(new Point(500, 300));

        //Sens horaire
        //Haut gauche
        assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
        //Haut droit
        points.add(points.remove(0));
        assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
        //Bas droit
        points.add(points.remove(0));
        assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
        //Bas gauche
        points.add(points.remove(0));
        assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));

        //Sens anti-horaire
        points.add(points.remove(0));
        Collections.reverse(points);
        //Haut gauche
        assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
        //Bas gauche
        points.add(points.remove(0));
        assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
        //Bas droit
        points.add(points.remove(0));
        assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
        //Haut droit
        points.add(points.remove(0));
        assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
    }

    @Test
    public void translation() {
        Dessin carre = new Dessin(new Point[]{new Point(200, 200), new Point(400, 200), new Point(400, 400), new Point(200, 400)});

        //Translate dans pleins de directions
        for (int x = -210; x <= 210; x += 70) {
            for (int y = -210; y <= 210; y += 70) {
                assertEquals(Forme.CARRE, fm.identify(DessinUtils.translate(carre, x, y)));
            }
        }

    }

    @Test
    public void compose() {
        //Un carré parfait dont les segments sont composés de plusieurs points
        List<Point> points = new ArrayList<>();

        float xStart = 500, yStart = 150, steplen = 9, steps = 14;

        //Haut gauche -> haut droit
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart + steplen * i, yStart));
        }
        //Haut droit -> bas droit
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart + steplen * steps, yStart + steplen * i));
        }
        //Bas droit -> bas gauche
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart + steplen * (steps - i), yStart + steplen * steps));
        }
        //Bas gauche -> haut gauche
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart, yStart + steplen * (steps - i)));
        }

        assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
    }

    @Test
    public void redimension() {
        //Différentes tailles de carrés
        for (int taille = 40; taille <= 200; taille += 40) {
            Point[] p = new Point[]{new Point(100, 100), new Point(100 + taille, 100), new Point(100 + taille, 100 + taille), new Point(100, 100 + taille)};
            assertEquals(Forme.CARRE, fm.identify(new Dessin(p)));
        }
    }

    @Test
    public void rotation() {
        Dessin carre = new Dessin(new Point[]{new Point(200, 200), new Point(400, 200), new Point(400, 400), new Point(200, 400)});
        Point centre = new Point(300, 300);

        //Rotationne dans plein d'angles
        for (int theta = 0; theta < 20; theta++) {
            double angle = theta * Math.PI / (2 * 20);
            assertEquals(Forme.CARRE, fm.identify(DessinUtils.rotate(carre, centre, angle)));
        }

    }

    @Test
    public void composeTremblant() {
        //Même carré que compose
        //Va bouger chaque point aléatoirement pour simuler un dessin "tremblant"
        List<Point> points = new ArrayList<>();

        float xStart = 500, yStart = 150, steplen = 20, steps = 10;

        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart + steplen * i, yStart));
        }
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart + steplen * steps, yStart + steplen * i));
        }
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart + steplen * (steps - i), yStart + steplen * steps));
        }
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart, yStart + steplen * (steps - i)));
        }

        Dessin carre = Dessin.fromList(points);
        //Graine fixe arbitraire pour que le test soit consistant
        Random rand = new Random(180114041513L);

        //Beaucoup d'essais
        for (int i = 0; i < 100; i++) {
            //TODO: Améliorer reconnaissance de forme
            //assertEquals(Forme.CARRE, fm.identify(DessinUtils.randomTranslate(carre, rand, 10)));
        }
    }

    @Test
    public void testPratique() {
        Dessin carre1 = new Dessin(new Point[]{new Point(476.48438, 197.62793), new Point(477.2783, 197.08002), new Point(477.2754, 196.27295), new Point(477.2754, 194.91193), new Point(475.42346, 191.6596), new Point(475.94934, 192.1922), new Point(476.22943, 194.9538), new Point(476.75067, 198.71997), new Point(476.48615, 201.41638), new Point(477.53906, 206.29364), new Point(478.30496, 209.48291), new Point(478.32678, 213.31854), new Point(478.59607, 219.82098), new Point(479.11044, 228.46313), new Point(477.81967, 239.80762), new Point(477.79755, 254.36627), new Point(475.97025, 272.3459), new Point(475.1687, 288.2182), new Point(474.1172, 301.64215), new Point(473.85074, 313.59308), new Point(473.0613, 327.0296), new Point(472.00873, 341.7143), new Point(472.00687, 355.22656), new Point(471.4746, 367.2044), new Point(470.95804, 376.828), new Point(470.42398, 388.30096), new Point(469.89987, 398.02997), new Point(469.89258, 408.91437), new Point(469.89258, 418.6587), new Point(470.15314, 428.45898), new Point(470.933, 438.1552), new Point(472.52362, 446.37122), new Point(474.09595, 454.43738), new Point(475.423, 460.45978), new Point(478.3117, 463.1994), new Point(480.4351, 465.3631), new Point(482.02148, 466.18506), new Point(483.34976, 466.45605), new Point(483.60446, 467.0), new Point(484.65662, 465.91516), new Point(486.50494, 465.91406), new Point(488.87344, 465.37207), new Point(491.81467, 465.91406), new Point(495.9593, 466.4485), new Point(502.5796, 466.45605), new Point(510.98236, 466.99805), new Point(520.75793, 468.6192), new Point(532.16235, 468.62402), new Point(549.0218, 469.16772), new Point(566.4696, 468.62402), new Point(583.3781, 469.1731), new Point(600.698, 467.53735), new Point(618.3075, 467.5418), new Point(639.02924, 462.60083), new Point(652.618, 463.2041), new Point(664.19635, 463.2041), new Point(676.2552, 462.6621), new Point(688.3959, 462.12012), new Point(697.6797, 461.03943), new Point(705.61993, 459.95148), new Point(710.59644, 458.86816), new Point(715.1133, 457.78418), new Point(718.2818, 456.68958), new Point(719.86005, 456.7002), new Point(721.69147, 456.7002), new Point(723.54016, 456.7002), new Point(724.86694, 456.7002), new Point(727.22424, 456.1706), new Point(728.26996, 455.6306), new Point(729.3363, 455.6162), new Point(729.85614, 455.6162), new Point(729.61487, 455.6162), new Point(729.47754, 455.6162), new Point(728.8176, 451.8208), new Point(729.08203, 450.72784), new Point(728.8226, 449.66296), new Point(729.60956, 447.48596), new Point(729.87305, 444.78162), new Point(731.44946, 439.95605), new Point(733.0275, 431.2757), new Point(735.3927, 419.94348), new Point(737.5189, 404.67377), new Point(738.83435, 391.70148), new Point(740.1501, 379.78784), new Point(741.2132, 367.24365), new Point(742.25977, 352.72168), new Point(742.5267, 337.5481), new Point(742.5293, 323.39972), new Point(742.5293, 311.99005), new Point(742.2681, 303.35254), new Point(741.47314, 295.17444), new Point(738.8215, 285.3631), new Point(736.99365, 277.3067), new Point(735.3977, 265.29144), new Point(731.70605, 256.12653), new Point(730.67236, 248.61511), new Point(730.1381, 241.54626), new Point(729.3476, 233.96887), new Point(728.81525, 227.39886), new Point(727.76447, 222.026), new Point(726.44086, 217.67249), new Point(724.8322, 213.29462), new Point(724.33575, 209.55048), new Point(724.0709, 207.37823), new Point(723.805, 205.74298), new Point(723.28156, 204.67444), new Point(722.486, 203.58405), new Point(721.96173, 203.58984), new Point(720.38086, 203.58984), new Point(718.5226, 202.49939), new Point(716.147, 203.05823), new Point(714.05176, 202.50537), new Point(710.0881, 202.50916), new Point(705.33136, 200.8761), new Point(699.524, 200.33789), new Point(690.93726, 198.13373), new Point(682.3257, 196.53406), new Point(674.18713, 195.45294), new Point(663.883, 193.27716), new Point(653.8438, 191.1018), new Point(644.30396, 189.47076), new Point(634.1812, 188.41644), new Point(621.62726, 186.77496), new Point(609.8567, 185.69983), new Point(598.76105, 184.07123), new Point(588.80786, 183.53607), new Point(578.29987, 182.45605), new Point(567.1974, 182.45215), new Point(553.678, 181.91016), new Point(540.303, 182.45123), new Point(529.8142, 183.52911), new Point(519.63354, 184.63019), new Point(511.05908, 186.2461), new Point(499.35345, 186.2461), new Point(490.1763, 186.7851), new Point(482.34717, 186.78809), new Point(475.42877, 186.78809), new Point(472.28058, 187.32495), new Point(470.94434, 187.33008), new Point(472.79297, 187.33008), new Point(472.79297, 187.33008)});

        Dessin carre2 = new Dessin(new Point[]{new Point(589.8633, 180.82617), new Point(590.3974, 180.82617), new Point(591.18164, 180.82617), new Point(591.709, 181.37921), new Point(592.1045, 182.18115), new Point(591.1739, 191.26758), new Point(591.45105, 203.73071), new Point(590.905, 212.36823), new Point(590.9145, 222.62366), new Point(591.1785, 231.28937), new Point(591.43787, 239.29987), new Point(591.709, 247.4447), new Point(593.0239, 260.9571), new Point(595.1411, 274.07605), new Point(596.4484, 287.5396), new Point(598.7992, 300.42816), new Point(600.6514, 313.4762), new Point(603.0185, 331.26678), new Point(604.3409, 347.52618), new Point(604.10156, 362.79834), new Point(605.4037, 375.3097), new Point(605.94727, 388.21765), new Point(608.0269, 401.73517), new Point(609.3639, 413.28363), new Point(609.88794, 421.94348), new Point(610.4253, 428.99536), new Point(610.4297, 434.39294), new Point(610.69336, 438.21576), new Point(610.95703, 439.8767), new Point(611.2207, 441.50952), new Point(611.2207, 443.14075), new Point(611.2207, 443.6856), new Point(611.2207, 444.50537), new Point(611.7428, 445.84955), new Point(612.14355, 446.67334), new Point(612.4038, 447.2083), new Point(613.0548, 447.74542), new Point(616.76324, 447.48633), new Point(621.7438, 447.48633), new Point(629.9358, 448.02765), new Point(640.7374, 448.5689), new Point(652.60236, 448.5703), new Point(667.13434, 448.5703), new Point(685.99603, 449.1065), new Point(706.87164, 449.1123), new Point(732.7284, 448.02832), new Point(762.83844, 447.49432), new Point(799.0081, 442.5965), new Point(829.927, 434.5127), new Point(852.45184, 429.06543), new Point(875.07166, 424.1958), new Point(897.14795, 423.6458), new Point(918.79114, 421.4536), new Point(927.0907, 420.38837), new Point(931.8457, 419.57373), new Point(931.0512, 421.47778), new Point(930.0049, 422.0127), new Point(928.6789, 421.4707), new Point(926.8151, 420.92017), new Point(925.4892, 419.28326), new Point(924.4688, 416.61108), new Point(924.72656, 415.01434), new Point(924.73157, 408.94318), new Point(924.9926, 398.11554), new Point(924.4629, 386.18134), new Point(923.65344, 372.52704), new Point(923.1445, 358.59845), new Point(920.7911, 341.93494), new Point(918.67914, 325.64313), new Point(916.0095, 308.6385), new Point(915.5078, 293.169), new Point(914.183, 275.17346), new Point(912.87244, 252.49457), new Point(911.26544, 237.62128), new Point(910.2237, 226.8852), new Point(909.16296, 217.62457), new Point(908.38544, 208.53491), new Point(907.3172, 200.26544), new Point(905.74133, 191.11938), new Point(904.9508, 187.32776), new Point(903.6232, 183.50665), new Point(899.94196, 180.28204), new Point(899.94507, 177.59668), new Point(895.9327, 175.94037), new Point(891.7933, 174.87183), new Point(889.6577, 174.32227), new Point(886.5072, 173.78027), new Point(884.3833, 173.78027), new Point(880.9745, 173.24426), new Point(876.7577, 173.23828), new Point(871.7655, 172.1612), new Point(865.7232, 171.08044), new Point(859.31396, 170.52832), new Point(851.4712, 169.99182), new Point(842.62726, 169.98633), new Point(831.96466, 169.98633), new Point(820.2469, 169.98633), new Point(808.42096, 169.98633), new Point(794.6835, 169.98633), new Point(777.3231, 169.44434), new Point(756.556, 168.90533), new Point(741.70624, 170.53302), new Point(729.7021, 171.60364), new Point(716.47107, 172.69629), new Point(702.5894, 173.77112), new Point(689.4786, 174.8476), new Point(677.55206, 176.47485), new Point(666.39166, 179.18152), new Point(655.778, 183.53717), new Point(643.16974, 185.69727), new Point(633.78296, 187.33978), new Point(622.76227, 188.41992), new Point(616.9878, 188.41406), new Point(613.5603, 188.95605), new Point(610.16644, 188.95605), new Point(608.8489, 188.95605), new Point(608.7158, 188.95605), new Point(608.56744, 189.50482), new Point(608.584, 189.49805)});

        Dessin carre3 = new Dessin(new Point[]{new Point(473.05664, 109.2832), new Point(473.05664, 109.825195), new Point(473.59155, 110.92474), new Point(474.37976, 112.00293), new Point(475.44458, 113.09247), new Point(479.93857, 117.986145), new Point(486.58737, 125.616516), new Point(493.6531, 133.17365), new Point(502.7591, 141.37189), new Point(512.46265, 149.4884), new Point(521.96643, 158.74646), new Point(531.4382, 166.8349), new Point(539.63574, 175.55292), new Point(547.7841, 186.43457), new Point(555.13416, 197.1969), new Point(562.5599, 208.13177), new Point(569.69324, 217.83508), new Point(577.87695, 228.15192), new Point(586.509, 237.82416), new Point(595.73804, 248.11768), new Point(605.9022, 260.77368), new Point(614.5132, 271.47083), new Point(623.3644, 282.74036), new Point(631.5014, 292.9959), new Point(639.2379, 301.78723), new Point(644.73914, 308.23102), new Point(648.16046, 314.18927), new Point(654.2146, 321.76392), new Point(657.91205, 329.92432), new Point(659.22284, 332.6125), new Point(660.5555, 334.79047), new Point(661.05096, 337.4696), new Point(661.58813, 340.19702), new Point(657.01746, 342.36096), new Point(654.67487, 344.5503), new Point(652.87585, 346.68097), new Point(650.181, 349.43762), new Point(646.7195, 353.2793), new Point(643.31415, 358.15338), new Point(637.1949, 363.57977), new Point(631.977, 369.51526), new Point(624.52435, 374.96484), new Point(616.20917, 380.8377), new Point(606.0311, 391.88287), new Point(593.89465, 407.0937), new Point(579.8573, 418.95117), new Point(566.6438, 429.82196), new Point(555.0026, 437.22137), new Point(544.64624, 446.4953), new Point(536.45276, 451.91888), new Point(524.5387, 460.08142), new Point(514.45636, 466.99573), new Point(503.4348, 475.28326), new Point(493.76434, 478.46973), new Point(485.14532, 481.10895), new Point(479.00958, 485.5213), new Point(471.93616, 489.2558), new Point(464.62003, 494.09717), new Point(457.40573, 499.0501), new Point(451.96878, 503.30615), new Point(446.92886, 506.58215), new Point(444.021, 508.76404), new Point(441.36884, 510.38165), new Point(439.29935, 512.53534), new Point(437.4443, 514.1628), new Point(437.06543, 514.15137), new Point(435.87714, 515.781), new Point(435.4834, 516.59033), new Point(434.0314, 516.8613), new Point(432.84668, 517.6743), new Point(431.3885, 517.4033), new Point(428.98172, 514.0999), new Point(424.51642, 510.33496), new Point(419.49295, 505.44373), new Point(412.8645, 499.45154), new Point(404.67508, 492.40192), new Point(397.09854, 484.32202), new Point(388.55356, 473.3089), new Point(380.3867, 461.41217), new Point(370.93805, 450.10858), new Point(359.40265, 439.34576), new Point(343.0567, 422.54443), new Point(328.87476, 405.8097), new Point(315.6009, 388.8966), new Point(304.3606, 374.38605), new Point(294.57516, 361.88165), new Point(283.4967, 348.3266), new Point(272.11343, 333.63495), new Point(263.96167, 323.3661), new Point(258.34903, 316.77795), new Point(254.42542, 313.02374), new Point(252.33688, 310.3297), new Point(252.36328, 310.909), new Point(250.77554, 308.7246), new Point(251.17676, 309.54932), new Point(251.43665, 310.08356), new Point(252.36328, 310.3623), new Point(252.89178, 310.3623), new Point(254.47266, 310.3623), new Point(255.7955, 310.3623), new Point(256.86774, 309.26318), new Point(259.24295, 304.35065), new Point(263.75787, 297.26385), new Point(272.5016, 287.48645), new Point(288.38873, 271.1731), new Point(304.50967, 256.75958), new Point(318.35446, 244.7027), new Point(331.48178, 232.8421), new Point(343.12726, 220.33429), new Point(354.06155, 208.23291), new Point(363.73938, 194.7135), new Point(370.01062, 186.1911), new Point(376.55023, 177.57721), new Point(385.59882, 166.10052), new Point(396.79855, 155.93909), new Point(408.93146, 148.87793), new Point(419.23846, 142.90247), new Point(428.72263, 134.25903), new Point(437.53265, 127.08856), new Point(443.54465, 121.187256), new Point(448.82278, 114.664795), new Point(451.18945, 111.9751), new Point(453.02493, 110.905396), new Point(454.60318, 109.822754), new Point(456.72537, 108.18799), new Point(460.15744, 105.47101), new Point(462.24057, 103.86615), new Point(464.35483, 102.23773), new Point(465.15433, 101.13721), new Point(463.56445, 102.7793), new Point(463.56445, 102.7793)});

        Dessin carre4 = new Dessin(new Point[]{new Point(793.9453, 145.59668), new Point(794.20953, 145.59668), new Point(794.7363, 146.685), new Point(795.275, 147.7763), new Point(796.6247, 149.4491), new Point(800.79675, 156.4303), new Point(805.332, 163.00726), new Point(811.901, 169.4657), new Point(819.7936, 176.49573), new Point(830.6766, 184.13354), new Point(842.0013, 192.79639), new Point(852.38477, 203.75342), new Point(860.75226, 212.37012), new Point(865.9752, 217.18304), new Point(870.942, 222.56732), new Point(875.69714, 226.3628), new Point(877.5464, 229.64062), new Point(879.1154, 232.88269), new Point(880.695, 236.1195), new Point(881.7556, 238.2851), new Point(882.26764, 240.4773), new Point(883.06866, 242.6225), new Point(882.01074, 245.86725), new Point(880.4117, 249.70856), new Point(878.0406, 252.93756), new Point(876.46967, 258.89685), new Point(873.5668, 265.38977), new Point(869.58777, 271.93616), new Point(865.62506, 277.88733), new Point(861.68445, 284.38446), new Point(856.43286, 290.31226), new Point(850.3153, 296.87506), new Point(844.29333, 302.78687), new Point(839.25244, 309.35657), new Point(833.18896, 313.64996), new Point(828.71405, 318.5332), new Point(824.2347, 323.94965), new Point(820.03937, 332.5996), new Point(814.7653, 339.10028), new Point(810.02734, 343.4261), new Point(806.8741, 347.20868), new Point(806.4697, 348.84375), new Point(804.4891, 351.01495), new Point(804.0967, 351.8247), new Point(803.44995, 352.35394), new Point(802.1059, 351.5537), new Point(797.0685, 350.4613), new Point(790.22815, 347.20557), new Point(784.1415, 345.0301), new Point(778.3398, 339.04218), new Point(770.66864, 335.79816), new Point(763.6927, 330.46075), new Point(755.61456, 324.38672), new Point(748.7877, 319.52112), new Point(743.012, 315.20355), new Point(737.9856, 310.84827), new Point(733.52716, 306.53003), new Point(729.0473, 301.10876), new Point(722.6901, 297.8615), new Point(715.90076, 292.47827), new Point(712.4577, 288.6678), new Point(709.0966, 286.53912), new Point(707.7343, 285.44067), new Point(706.4237, 284.36102), new Point(705.6254, 283.2766), new Point(705.35175, 282.72113), new Point(705.08636, 282.1787), new Point(704.2969, 281.36572), new Point(704.29333, 280.018), new Point(706.66724, 274.61285), new Point(707.99866, 270.2282), new Point(710.36926, 263.195), new Point(715.36786, 255.62494), new Point(721.703, 247.48578), new Point(728.38043, 238.69214), new Point(736.1239, 228.26605), new Point(744.37915, 216.59216), new Point(756.6318, 202.90448), new Point(767.12195, 191.5766), new Point(777.8458, 180.8421), new Point(785.4767, 172.18878), new Point(791.09705, 165.05896), new Point(794.47485, 162.39667), new Point(795.25726, 159.70825), new Point(795.52734, 158.87549), new Point(795.791, 159.68848), new Point(796.9647, 158.88428), new Point(797.1083, 158.60449), new Point(798.6938, 157.52051), new Point(799.2201, 158.61005), new Point(797.37305, 160.23047), new Point(797.37305, 160.23047)});

        assertEquals(Forme.CARRE, fm.identify(carre1));
        assertEquals(Forme.CARRE, fm.identify(carre2));
        assertEquals(Forme.CARRE, fm.identify(carre3));
        assertEquals(Forme.CARRE, fm.identify(carre4));
    }

}