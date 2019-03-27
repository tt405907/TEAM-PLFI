package commun;

import static commun.DessinUtils.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commun.Dessin;
import commun.Forme;
import commun.Point;

public class FormeMatcherCercleTest {
	private FormeMatcher fm;
	
	@Before
	public void setup() {
		fm = new FormeMatcher();
	}

	@Test
	public void cercleParfait() {
		Dessin cercle;
		Point[] pointsCercle = new Point[180];
		for (int i = 0; i < 180; i++) {
			pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 180.0) * 50 + 125,
					(float) Math.sin(i * 2 * Math.PI / 180.0) * 50 + 125);
		}
		cercle = new Dessin(pointsCercle);
		assertEquals(Forme.ROND, fm.identify(cercle));
	}

	@Test
	public void translationCercle() {
		Dessin cercle;
		Point[] pointsCercle = new Point[180];
		for (int i = 0; i < 180; i++) {
			pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 180.0) * 50 + 125,
					(float) Math.sin(i * 2 * Math.PI / 180.0) * 50 + 125);
		}
		cercle = new Dessin(pointsCercle);
		//Translate dans pleins de directions
		for (int x = -200; x <= 200; x += 200) {
			for (int y = -200; y <= 200; y += 200) {
				assertEquals(Forme.ROND, fm.identify(translate(cercle, x, y)));
			}
		}
	}

	@Test
	public void testPratique(){
		Dessin cercle1 = new Dessin(new Point[] {new Point(537.9199, 160.23047), new Point(533.6246, 160.23047), new Point(527.6339, 160.23145), new Point(522.1151, 159.68848), new Point(517.748, 160.23047), new Point(509.90283, 161.781), new Point(503.00623, 165.56671), new Point(489.08658, 169.92688), new Point(473.68588, 176.44135), new Point(460.35913, 187.44934), new Point(445.96457, 198.45117), new Point(435.605, 209.7873), new Point(426.1731, 220.05219), new Point(416.31927, 231.98279), new Point(406.87192, 245.32758), new Point(400.0824, 261.95605), new Point(394.80927, 282.474), new Point(390.57272, 304.15558), new Point(387.14865, 328.7525), new Point(388.08038, 354.1068), new Point(391.01382, 378.8874), new Point(395.2116, 400.01532), new Point(400.89014, 416.6933), new Point(410.9924, 431.8672), new Point(424.79773, 443.85565), new Point(442.20288, 453.7093), new Point(463.535, 462.55664), new Point(488.4611, 471.08258), new Point(514.6464, 477.68433), new Point(543.45685, 481.54474), new Point(569.5928, 482.71582), new Point(595.01733, 475.93726), new Point(623.30634, 465.61548), new Point(648.289, 450.25787), new Point(673.2059, 430.32196), new Point(695.4261, 405.16608), new Point(713.2033, 381.10712), new Point(726.3586, 354.9765), new Point(737.0478, 330.42047), new Point(741.1884, 305.65405), new Point(740.9921, 283.72345), new Point(736.323, 265.22186), new Point(727.87506, 248.04193), new Point(714.4842, 229.06726), new Point(697.9098, 212.7431), new Point(676.6109, 198.35028), new Point(655.2093, 185.435), new Point(630.07166, 175.45282), new Point(604.8125, 168.94086), new Point(579.0534, 166.20306), new Point(554.5662, 166.19067), new Point(536.0888, 168.32568), new Point(522.1151, 173.10059), new Point(521.83594, 173.23828), new Point(521.83594, 173.23828)});

		Dessin cercle2 = new Dessin(new Point[] {new Point(964.27734, 83.80957), new Point(964.1455, 83.80957), new Point(963.22266, 83.80957), new Point(957.98535, 80.58081), new Point(952.5073, 77.36157), new Point(948.5232, 74.65619), new Point(944.28906, 72.99213), new Point(940.1433, 71.36334), new Point(937.6814, 71.34375), new Point(936.3502, 71.34375), new Point(934.244, 71.86847), new Point(933.1942, 72.93872), new Point(930.8153, 74.5791), new Point(926.09424, 77.27185), new Point(919.9788, 81.10101), new Point(914.253, 85.40076), new Point(909.5659, 90.1969), new Point(907.13464, 91.90137), new Point(906.26953, 92.75244), new Point(904.9776, 95.15521), new Point(903.926, 97.8103), new Point(901.31555, 102.14551), new Point(898.1853, 105.41559), new Point(896.5632, 110.77362), new Point(895.4782, 112.456116), new Point(895.9863, 116.1864), new Point(896.7817, 119.05682), new Point(897.5326, 123.22803), new Point(897.83203, 129.17981), new Point(897.83203, 135.1427), new Point(898.3452, 139.5473), new Point(899.41406, 143.39496), new Point(900.4368, 144.47986), new Point(904.0283, 148.57764), new Point(902.8113, 148.81726), new Point(903.86365, 150.44086), new Point(907.04803, 152.09485), new Point(908.6333, 156.39825), new Point(910.9426, 158.52948), new Point(918.6176, 163.4519), new Point(920.2741, 166.67273), new Point(924.6116, 168.83142), new Point(928.11, 169.94989), new Point(932.5431, 171.55731), new Point(935.7402, 172.68243), new Point(942.0033, 170.57996), new Point(944.3843, 171.6123), new Point(948.08484, 171.11987), new Point(953.85535, 171.04657), new Point(956.8476, 169.45813), new Point(960.8064, 167.31445), new Point(964.2409, 166.21735), new Point(967.87585, 165.62915), new Point(971.8672, 163.6222), new Point(974.22406, 160.80243), new Point(977.1675, 156.4748), new Point(982.6845, 153.20166), new Point(983.72546, 149.49524), new Point(986.9364, 146.6944), new Point(989.07245, 143.46967), new Point(990.10803, 141.8153), new Point(990.6378, 139.6623), new Point(993.03784, 135.2572), new Point(996.15106, 129.39972), new Point(996.9782, 124.487366), new Point(997.7637, 121.24701), new Point(995.7094, 118.02307), new Point(993.56384, 113.64508), new Point(990.95544, 110.431946), new Point(988.30475, 104.46387), new Point(984.8884, 97.970215), new Point(982.2851, 93.151855), new Point(979.3224, 90.32269), new Point(977.7362, 88.695435), new Point(974.8156, 87.05859), new Point(971.4493, 85.457275), new Point(967.7927, 83.335144), new Point(965.0749, 81.64697), new Point(960.3725, 78.96344), new Point(958.4279, 77.33899), new Point(960.8945, 75.10083), new Point(962.959, 75.67969), new Point(962.959, 75.67969)});

		Dessin cercle3 = new Dessin(new Point[] {new Point(655.2539, 159.14648), new Point(655.2539, 158.05847), new Point(654.7223, 155.87701), new Point(653.92426, 153.70337), new Point(643.32745, 142.82751), new Point(634.0664, 139.59625), new Point(622.56805, 142.34381), new Point(609.2754, 149.44574), new Point(595.4891, 153.76508), new Point(575.3632, 161.85565), new Point(552.9284, 168.36523), new Point(532.36426, 176.49805), new Point(513.8703, 187.36267), new Point(492.85968, 200.53455), new Point(478.28595, 215.01703), new Point(463.71548, 230.81348), new Point(450.37827, 248.03699), new Point(438.4465, 269.84204), new Point(427.41586, 289.81635), new Point(420.8574, 308.16968), new Point(415.31918, 332.00415), new Point(415.5801, 355.39203), new Point(420.54248, 377.39075), new Point(428.4125, 400.623), new Point(437.19473, 420.38086), new Point(446.103, 436.5464), new Point(457.1864, 450.6776), new Point(472.9768, 464.76044), new Point(493.36246, 478.38177), new Point(516.2518, 491.90332), new Point(544.03357, 505.07532), new Point(573.77985, 514.07544), new Point(605.475, 518.46655), new Point(637.89246, 518.4859), new Point(671.9125, 515.7964), new Point(704.2461, 507.66516), new Point(732.93567, 492.37097), new Point(757.1519, 471.75787), new Point(776.7725, 448.07574), new Point(793.6215, 420.4903), new Point(806.89874, 394.3022), new Point(817.4198, 368.33655), new Point(825.8685, 341.19104), new Point(825.3175, 315.72583), new Point(818.738, 289.25128), new Point(807.1044, 264.24658), new Point(792.1252, 239.39984), new Point(773.94855, 215.5603), new Point(752.8308, 194.93536), new Point(732.03345, 177.61841), new Point(709.57935, 163.48792), new Point(687.42694, 152.10309), new Point(667.3751, 143.96771), new Point(646.174, 140.73297), new Point(623.8023, 141.81024), new Point(603.6944, 146.19073), new Point(585.793, 154.3382), new Point(578.5254, 157.52051), new Point(578.5254, 157.52051)});

		Dessin cercle4 = new Dessin(new Point[] {new Point(316.17188, 168.90234), new Point(316.45114, 168.90234), new Point(317.235, 170.00366), new Point(317.22656, 170.53357), new Point(317.22656, 171.63312), new Point(317.22656, 172.4253), new Point(315.88693, 176.534), new Point(314.30634, 183.05536), new Point(312.4726, 189.0047), new Point(312.74445, 192.20709), new Point(311.95312, 195.9917), new Point(312.74088, 200.8664), new Point(314.86346, 207.96674), new Point(317.4786, 212.76392), new Point(320.13953, 218.24207), new Point(322.74805, 223.0213), new Point(326.43048, 226.88287), new Point(330.92178, 229.59338), new Point(335.7064, 232.32715), new Point(341.48834, 235.5694), new Point(348.6249, 237.19629), new Point(356.70444, 238.8078), new Point(366.81845, 238.81934), new Point(376.01312, 237.73694), new Point(384.20355, 234.4823), new Point(391.26688, 230.71216), new Point(399.22452, 226.35559), new Point(407.4516, 219.2633), new Point(414.0138, 211.14838), new Point(419.51053, 203.61694), new Point(424.01703, 195.45306), new Point(427.46265, 188.37036), new Point(429.81326, 181.91309), new Point(430.6292, 174.76666), new Point(429.54892, 168.33734), new Point(427.7104, 161.8728), new Point(424.82, 154.83508), new Point(420.01697, 148.24243), new Point(414.77277, 142.33313), new Point(410.37817, 138.0553), new Point(405.53918, 133.11652), new Point(400.749, 130.39557), new Point(395.76456, 129.33691), new Point(391.87833, 128.26129), new Point(386.54852, 127.706055), new Point(382.60803, 127.16626), new Point(377.0325, 127.15808), new Point(370.15988, 127.72046), new Point(363.34085, 129.34467), new Point(357.03537, 131.50641), new Point(349.6823, 135.28357), new Point(342.04184, 138.53424), new Point(335.35526, 142.38562), new Point(328.7776, 147.26044), new Point(324.0887, 154.79486), new Point(320.1362, 160.75659), new Point(316.68362, 167.31482), new Point(314.57684, 174.90161), new Point(312.2273, 181.33038), new Point(308.5079, 187.36005), new Point(308.5254, 187.33008)});

		assertEquals(Forme.ROND, fm.identify(cercle1));
		assertEquals(Forme.ROND, fm.identify(cercle2));
		assertEquals(Forme.ROND, fm.identify(cercle3));
		assertEquals(Forme.ROND, fm.identify(cercle4));
	}
}
