package plfi.plfi;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import commun.Dessin;
import commun.Forme;
import commun.Point;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

@LargeTest

public class TrainingEnigmeTest {
    private static final String PACKAGE_NAME = "plfi.plfi";
    @Rule
    public IntentsTestRule<TrainingEnigme> mainActivityRule = new IntentsTestRule<>(TrainingEnigme.class);


    @Test
    public void boutonRetourTrainingEnigme() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Revenir en haut de la page"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_enigme),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        intended(AllOf.allOf(hasComponent(hasShortClassName(".MainActivity")), toPackage(PACKAGE_NAME)));
    }


    @Test
    public void gommeTrainingEnigme() {

        Point[] pointsCercle = new Point[180];
        for (int i = 0; i < 180; i++) {
            pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 180.0) * 50 + 125,
                    (float) Math.sin(i * 2 * Math.PI / 180.0) * 50 + 125);
        }
        for (Point p : pointsCercle)
            mainActivityRule.getActivity().gamePrint.getPoints().add(p);

        assertFalse(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.enigme_new_btn), withContentDescription("New"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        assertTrue(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());

    }

    @Test
    public void enigmeCarreTrainingEnigme() {
        //On teste d'abord avec une énigme de carré en envoyant un carré
        mainActivityRule.getActivity().gameEnigme.makeEnigme(Forme.CARRE);
        //un carré dessiné à la main
        Dessin carre1 = new Dessin(new Point[]{new Point(476.48438, 197.62793), new Point(477.2783, 197.08002), new Point(477.2754, 196.27295), new Point(477.2754, 194.91193), new Point(475.42346, 191.6596), new Point(475.94934, 192.1922), new Point(476.22943, 194.9538), new Point(476.75067, 198.71997), new Point(476.48615, 201.41638), new Point(477.53906, 206.29364), new Point(478.30496, 209.48291), new Point(478.32678, 213.31854), new Point(478.59607, 219.82098), new Point(479.11044, 228.46313), new Point(477.81967, 239.80762), new Point(477.79755, 254.36627), new Point(475.97025, 272.3459), new Point(475.1687, 288.2182), new Point(474.1172, 301.64215), new Point(473.85074, 313.59308), new Point(473.0613, 327.0296), new Point(472.00873, 341.7143), new Point(472.00687, 355.22656), new Point(471.4746, 367.2044), new Point(470.95804, 376.828), new Point(470.42398, 388.30096), new Point(469.89987, 398.02997), new Point(469.89258, 408.91437), new Point(469.89258, 418.6587), new Point(470.15314, 428.45898), new Point(470.933, 438.1552), new Point(472.52362, 446.37122), new Point(474.09595, 454.43738), new Point(475.423, 460.45978), new Point(478.3117, 463.1994), new Point(480.4351, 465.3631), new Point(482.02148, 466.18506), new Point(483.34976, 466.45605), new Point(483.60446, 467.0), new Point(484.65662, 465.91516), new Point(486.50494, 465.91406), new Point(488.87344, 465.37207), new Point(491.81467, 465.91406), new Point(495.9593, 466.4485), new Point(502.5796, 466.45605), new Point(510.98236, 466.99805), new Point(520.75793, 468.6192), new Point(532.16235, 468.62402), new Point(549.0218, 469.16772), new Point(566.4696, 468.62402), new Point(583.3781, 469.1731), new Point(600.698, 467.53735), new Point(618.3075, 467.5418), new Point(639.02924, 462.60083), new Point(652.618, 463.2041), new Point(664.19635, 463.2041), new Point(676.2552, 462.6621), new Point(688.3959, 462.12012), new Point(697.6797, 461.03943), new Point(705.61993, 459.95148), new Point(710.59644, 458.86816), new Point(715.1133, 457.78418), new Point(718.2818, 456.68958), new Point(719.86005, 456.7002), new Point(721.69147, 456.7002), new Point(723.54016, 456.7002), new Point(724.86694, 456.7002), new Point(727.22424, 456.1706), new Point(728.26996, 455.6306), new Point(729.3363, 455.6162), new Point(729.85614, 455.6162), new Point(729.61487, 455.6162), new Point(729.47754, 455.6162), new Point(728.8176, 451.8208), new Point(729.08203, 450.72784), new Point(728.8226, 449.66296), new Point(729.60956, 447.48596), new Point(729.87305, 444.78162), new Point(731.44946, 439.95605), new Point(733.0275, 431.2757), new Point(735.3927, 419.94348), new Point(737.5189, 404.67377), new Point(738.83435, 391.70148), new Point(740.1501, 379.78784), new Point(741.2132, 367.24365), new Point(742.25977, 352.72168), new Point(742.5267, 337.5481), new Point(742.5293, 323.39972), new Point(742.5293, 311.99005), new Point(742.2681, 303.35254), new Point(741.47314, 295.17444), new Point(738.8215, 285.3631), new Point(736.99365, 277.3067), new Point(735.3977, 265.29144), new Point(731.70605, 256.12653), new Point(730.67236, 248.61511), new Point(730.1381, 241.54626), new Point(729.3476, 233.96887), new Point(728.81525, 227.39886), new Point(727.76447, 222.026), new Point(726.44086, 217.67249), new Point(724.8322, 213.29462), new Point(724.33575, 209.55048), new Point(724.0709, 207.37823), new Point(723.805, 205.74298), new Point(723.28156, 204.67444), new Point(722.486, 203.58405), new Point(721.96173, 203.58984), new Point(720.38086, 203.58984), new Point(718.5226, 202.49939), new Point(716.147, 203.05823), new Point(714.05176, 202.50537), new Point(710.0881, 202.50916), new Point(705.33136, 200.8761), new Point(699.524, 200.33789), new Point(690.93726, 198.13373), new Point(682.3257, 196.53406), new Point(674.18713, 195.45294), new Point(663.883, 193.27716), new Point(653.8438, 191.1018), new Point(644.30396, 189.47076), new Point(634.1812, 188.41644), new Point(621.62726, 186.77496), new Point(609.8567, 185.69983), new Point(598.76105, 184.07123), new Point(588.80786, 183.53607), new Point(578.29987, 182.45605), new Point(567.1974, 182.45215), new Point(553.678, 181.91016), new Point(540.303, 182.45123), new Point(529.8142, 183.52911), new Point(519.63354, 184.63019), new Point(511.05908, 186.2461), new Point(499.35345, 186.2461), new Point(490.1763, 186.7851), new Point(482.34717, 186.78809), new Point(475.42877, 186.78809), new Point(472.28058, 187.32495), new Point(470.94434, 187.33008), new Point(472.79297, 187.33008), new Point(472.79297, 187.33008)});
        List<Point> pointsC = carre1.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsC);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.training_check), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        TextView reponseEnigme = mainActivityRule.getActivity().message_Serveur_Reponse;
        ColorDrawable backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        String reponseEnigmeTexte = reponseEnigme.getText().toString();

        //on a dessiné un carré en attendant un carré, on devrait avoir une réponse positive

        assertEquals(" JUSTE", reponseEnigmeTexte);
        assertEquals(Color.GREEN, backgroundReponse.getColor());

        //On teste ensuite avec une énigme de carre en envoyant un triangle

        mainActivityRule.getActivity().gameEnigme.makeEnigme(Forme.CARRE);

        //un triangle dessiné à la main
        Dessin triangle2 = new Dessin(new Point[]{new Point(437.98828, 220.3916), new Point(438.51562, 219.84961), new Point(438.25195, 218.21912), new Point(438.25195, 218.76562), new Point(438.25195, 219.57861), new Point(438.78064, 220.93634), new Point(439.81952, 225.73224), new Point(441.13974, 232.29321), new Point(444.58295, 239.3664), new Point(449.28683, 246.89258), new Point(454.20016, 253.79974), new Point(460.37677, 264.79126), new Point(465.5229, 272.2057), new Point(472.89328, 282.54297), new Point(479.7669, 291.17554), new Point(486.2665, 299.83673), new Point(492.6764, 308.56567), new Point(499.396, 318.1327), new Point(508.2655, 328.6411), new Point(514.7587, 339.792), new Point(519.3762, 349.1853), new Point(522.4679, 358.2727), new Point(525.75085, 367.16138), new Point(529.1385, 375.67938), new Point(533.12683, 385.55426), new Point(536.269, 394.2295), new Point(546.3458, 406.9795), new Point(548.7368, 419.26678), new Point(552.60156, 427.25995), new Point(557.9572, 437.18518), new Point(562.8113, 445.09174), new Point(567.6944, 453.94818), new Point(571.3624, 463.0882), new Point(575.0095, 471.15277), new Point(577.6874, 476.65735), new Point(580.0993, 481.61517), new Point(581.8987, 484.7719), new Point(582.98596, 487.00684), new Point(583.2715, 487.59375), new Point(583.0444, 487.1269), new Point(582.74414, 486.50977), new Point(583.7848, 488.10687), new Point(583.93066, 488.40674), new Point(585.613, 487.60675), new Point(587.7051, 486.01794), new Point(590.11194, 482.7343), new Point(593.2496, 476.3255), new Point(598.1401, 468.30225), new Point(603.13873, 459.64563), new Point(610.60034, 449.227), new Point(619.55396, 438.92157), new Point(628.33026, 427.47046), new Point(641.49316, 411.77716), new Point(653.8396, 397.19763), new Point(664.34454, 384.7827), new Point(675.13904, 370.198), new Point(687.0124, 355.3236), new Point(700.6968, 338.0075), new Point(713.12225, 321.72003), new Point(724.538, 306.4721), new Point(736.22986, 290.09503), new Point(746.20734, 276.0735), new Point(756.03815, 262.39502), new Point(765.9623, 249.16235), new Point(773.53235, 238.42303), new Point(780.3832, 228.69019), new Point(785.96515, 220.48755), new Point(789.4589, 211.24298), new Point(793.16455, 205.7367), new Point(793.9488, 203.57544), new Point(794.209, 202.53119), new Point(794.209, 202.23486), new Point(792.11334, 201.96387), new Point(787.68225, 201.96387), new Point(778.96735, 200.88574), new Point(766.3853, 200.87988), new Point(753.6492, 200.87988), new Point(739.8071, 200.87988), new Point(726.903, 200.3269), new Point(710.6879, 199.7959), new Point(690.40924, 200.33789), new Point(670.5642, 201.39062), new Point(646.5694, 199.84064), new Point(622.2178, 198.72046), new Point(598.68304, 197.09705), new Point(573.8663, 196.00195), new Point(540.91565, 196.53241), new Point(512.396, 197.59644), new Point(485.76505, 199.1817), new Point(465.87482, 200.86774), new Point(452.07138, 202.47528), new Point(439.42496, 204.11902), new Point(433.5651, 205.19843), new Point(429.87335, 206.26953), new Point(429.0394, 206.80902), new Point(434.48813, 206.8418), new Point(434.56055, 206.8418), new Point(434.56055, 206.8418)});
        List<Point> pointsT = triangle2.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsT);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.training_check), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        reponseEnigme = mainActivityRule.getActivity().message_Serveur_Reponse;
        backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        reponseEnigmeTexte = reponseEnigme.getText().toString();

        //on a dessiné un triangle en attendant un carré, on devrait avoir une réponse négative

        assertEquals("  FAUX", reponseEnigmeTexte);
        assertEquals(Color.RED, backgroundReponse.getColor());

        //On teste ensuite avec une énigme de carre en envoyant un rond (l'énigme ne devrait pas avoir changé)

        //un cercle dessiné à la main
        Dessin cercle4 = new Dessin(new Point[]{new Point(316.17188, 168.90234), new Point(316.45114, 168.90234), new Point(317.235, 170.00366), new Point(317.22656, 170.53357), new Point(317.22656, 171.63312), new Point(317.22656, 172.4253), new Point(315.88693, 176.534), new Point(314.30634, 183.05536), new Point(312.4726, 189.0047), new Point(312.74445, 192.20709), new Point(311.95312, 195.9917), new Point(312.74088, 200.8664), new Point(314.86346, 207.96674), new Point(317.4786, 212.76392), new Point(320.13953, 218.24207), new Point(322.74805, 223.0213), new Point(326.43048, 226.88287), new Point(330.92178, 229.59338), new Point(335.7064, 232.32715), new Point(341.48834, 235.5694), new Point(348.6249, 237.19629), new Point(356.70444, 238.8078), new Point(366.81845, 238.81934), new Point(376.01312, 237.73694), new Point(384.20355, 234.4823), new Point(391.26688, 230.71216), new Point(399.22452, 226.35559), new Point(407.4516, 219.2633), new Point(414.0138, 211.14838), new Point(419.51053, 203.61694), new Point(424.01703, 195.45306), new Point(427.46265, 188.37036), new Point(429.81326, 181.91309), new Point(430.6292, 174.76666), new Point(429.54892, 168.33734), new Point(427.7104, 161.8728), new Point(424.82, 154.83508), new Point(420.01697, 148.24243), new Point(414.77277, 142.33313), new Point(410.37817, 138.0553), new Point(405.53918, 133.11652), new Point(400.749, 130.39557), new Point(395.76456, 129.33691), new Point(391.87833, 128.26129), new Point(386.54852, 127.706055), new Point(382.60803, 127.16626), new Point(377.0325, 127.15808), new Point(370.15988, 127.72046), new Point(363.34085, 129.34467), new Point(357.03537, 131.50641), new Point(349.6823, 135.28357), new Point(342.04184, 138.53424), new Point(335.35526, 142.38562), new Point(328.7776, 147.26044), new Point(324.0887, 154.79486), new Point(320.1362, 160.75659), new Point(316.68362, 167.31482), new Point(314.57684, 174.90161), new Point(312.2273, 181.33038), new Point(308.5079, 187.36005), new Point(308.5254, 187.33008)});
        List<Point> pointsCe = cercle4.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsCe);
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.training_check), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        reponseEnigme = mainActivityRule.getActivity().message_Serveur_Reponse;
        backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        reponseEnigmeTexte = reponseEnigme.getText().toString();

        //on a dessiné un rond en attendant un carré, on devrait avoir une réponse négative

        assertEquals("  FAUX", reponseEnigmeTexte);
        assertEquals(Color.RED, backgroundReponse.getColor());

    }

    @Test
    public void enigmeTriangleTrainingEnigme() {
        //On teste d'abord avec une énigme de triangle en envoyant un carré
        mainActivityRule.getActivity().gameEnigme.makeEnigme(Forme.TRIANGLE);
        //un carré dessiné à la main
        Dessin carre1 = new Dessin(new Point[]{new Point(476.48438, 197.62793), new Point(477.2783, 197.08002), new Point(477.2754, 196.27295), new Point(477.2754, 194.91193), new Point(475.42346, 191.6596), new Point(475.94934, 192.1922), new Point(476.22943, 194.9538), new Point(476.75067, 198.71997), new Point(476.48615, 201.41638), new Point(477.53906, 206.29364), new Point(478.30496, 209.48291), new Point(478.32678, 213.31854), new Point(478.59607, 219.82098), new Point(479.11044, 228.46313), new Point(477.81967, 239.80762), new Point(477.79755, 254.36627), new Point(475.97025, 272.3459), new Point(475.1687, 288.2182), new Point(474.1172, 301.64215), new Point(473.85074, 313.59308), new Point(473.0613, 327.0296), new Point(472.00873, 341.7143), new Point(472.00687, 355.22656), new Point(471.4746, 367.2044), new Point(470.95804, 376.828), new Point(470.42398, 388.30096), new Point(469.89987, 398.02997), new Point(469.89258, 408.91437), new Point(469.89258, 418.6587), new Point(470.15314, 428.45898), new Point(470.933, 438.1552), new Point(472.52362, 446.37122), new Point(474.09595, 454.43738), new Point(475.423, 460.45978), new Point(478.3117, 463.1994), new Point(480.4351, 465.3631), new Point(482.02148, 466.18506), new Point(483.34976, 466.45605), new Point(483.60446, 467.0), new Point(484.65662, 465.91516), new Point(486.50494, 465.91406), new Point(488.87344, 465.37207), new Point(491.81467, 465.91406), new Point(495.9593, 466.4485), new Point(502.5796, 466.45605), new Point(510.98236, 466.99805), new Point(520.75793, 468.6192), new Point(532.16235, 468.62402), new Point(549.0218, 469.16772), new Point(566.4696, 468.62402), new Point(583.3781, 469.1731), new Point(600.698, 467.53735), new Point(618.3075, 467.5418), new Point(639.02924, 462.60083), new Point(652.618, 463.2041), new Point(664.19635, 463.2041), new Point(676.2552, 462.6621), new Point(688.3959, 462.12012), new Point(697.6797, 461.03943), new Point(705.61993, 459.95148), new Point(710.59644, 458.86816), new Point(715.1133, 457.78418), new Point(718.2818, 456.68958), new Point(719.86005, 456.7002), new Point(721.69147, 456.7002), new Point(723.54016, 456.7002), new Point(724.86694, 456.7002), new Point(727.22424, 456.1706), new Point(728.26996, 455.6306), new Point(729.3363, 455.6162), new Point(729.85614, 455.6162), new Point(729.61487, 455.6162), new Point(729.47754, 455.6162), new Point(728.8176, 451.8208), new Point(729.08203, 450.72784), new Point(728.8226, 449.66296), new Point(729.60956, 447.48596), new Point(729.87305, 444.78162), new Point(731.44946, 439.95605), new Point(733.0275, 431.2757), new Point(735.3927, 419.94348), new Point(737.5189, 404.67377), new Point(738.83435, 391.70148), new Point(740.1501, 379.78784), new Point(741.2132, 367.24365), new Point(742.25977, 352.72168), new Point(742.5267, 337.5481), new Point(742.5293, 323.39972), new Point(742.5293, 311.99005), new Point(742.2681, 303.35254), new Point(741.47314, 295.17444), new Point(738.8215, 285.3631), new Point(736.99365, 277.3067), new Point(735.3977, 265.29144), new Point(731.70605, 256.12653), new Point(730.67236, 248.61511), new Point(730.1381, 241.54626), new Point(729.3476, 233.96887), new Point(728.81525, 227.39886), new Point(727.76447, 222.026), new Point(726.44086, 217.67249), new Point(724.8322, 213.29462), new Point(724.33575, 209.55048), new Point(724.0709, 207.37823), new Point(723.805, 205.74298), new Point(723.28156, 204.67444), new Point(722.486, 203.58405), new Point(721.96173, 203.58984), new Point(720.38086, 203.58984), new Point(718.5226, 202.49939), new Point(716.147, 203.05823), new Point(714.05176, 202.50537), new Point(710.0881, 202.50916), new Point(705.33136, 200.8761), new Point(699.524, 200.33789), new Point(690.93726, 198.13373), new Point(682.3257, 196.53406), new Point(674.18713, 195.45294), new Point(663.883, 193.27716), new Point(653.8438, 191.1018), new Point(644.30396, 189.47076), new Point(634.1812, 188.41644), new Point(621.62726, 186.77496), new Point(609.8567, 185.69983), new Point(598.76105, 184.07123), new Point(588.80786, 183.53607), new Point(578.29987, 182.45605), new Point(567.1974, 182.45215), new Point(553.678, 181.91016), new Point(540.303, 182.45123), new Point(529.8142, 183.52911), new Point(519.63354, 184.63019), new Point(511.05908, 186.2461), new Point(499.35345, 186.2461), new Point(490.1763, 186.7851), new Point(482.34717, 186.78809), new Point(475.42877, 186.78809), new Point(472.28058, 187.32495), new Point(470.94434, 187.33008), new Point(472.79297, 187.33008), new Point(472.79297, 187.33008)});
        List<Point> pointsC = carre1.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsC);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.training_check), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        TextView reponseEnigme = mainActivityRule.getActivity().message_Serveur_Reponse;
        ColorDrawable backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        String reponseEnigmeTexte = reponseEnigme.getText().toString();

        //on a dessiné un carré en attendant un triangle, on devrait avoir une réponse négative

        assertEquals("  FAUX", reponseEnigmeTexte);
        assertEquals(Color.RED, backgroundReponse.getColor());
        //On teste ensuite avec une énigme de triangle en envoyant un triangle
        //un triangle dessiné à la main
        Dessin triangle2 = new Dessin(new Point[]{new Point(437.98828, 220.3916), new Point(438.51562, 219.84961), new Point(438.25195, 218.21912), new Point(438.25195, 218.76562), new Point(438.25195, 219.57861), new Point(438.78064, 220.93634), new Point(439.81952, 225.73224), new Point(441.13974, 232.29321), new Point(444.58295, 239.3664), new Point(449.28683, 246.89258), new Point(454.20016, 253.79974), new Point(460.37677, 264.79126), new Point(465.5229, 272.2057), new Point(472.89328, 282.54297), new Point(479.7669, 291.17554), new Point(486.2665, 299.83673), new Point(492.6764, 308.56567), new Point(499.396, 318.1327), new Point(508.2655, 328.6411), new Point(514.7587, 339.792), new Point(519.3762, 349.1853), new Point(522.4679, 358.2727), new Point(525.75085, 367.16138), new Point(529.1385, 375.67938), new Point(533.12683, 385.55426), new Point(536.269, 394.2295), new Point(546.3458, 406.9795), new Point(548.7368, 419.26678), new Point(552.60156, 427.25995), new Point(557.9572, 437.18518), new Point(562.8113, 445.09174), new Point(567.6944, 453.94818), new Point(571.3624, 463.0882), new Point(575.0095, 471.15277), new Point(577.6874, 476.65735), new Point(580.0993, 481.61517), new Point(581.8987, 484.7719), new Point(582.98596, 487.00684), new Point(583.2715, 487.59375), new Point(583.0444, 487.1269), new Point(582.74414, 486.50977), new Point(583.7848, 488.10687), new Point(583.93066, 488.40674), new Point(585.613, 487.60675), new Point(587.7051, 486.01794), new Point(590.11194, 482.7343), new Point(593.2496, 476.3255), new Point(598.1401, 468.30225), new Point(603.13873, 459.64563), new Point(610.60034, 449.227), new Point(619.55396, 438.92157), new Point(628.33026, 427.47046), new Point(641.49316, 411.77716), new Point(653.8396, 397.19763), new Point(664.34454, 384.7827), new Point(675.13904, 370.198), new Point(687.0124, 355.3236), new Point(700.6968, 338.0075), new Point(713.12225, 321.72003), new Point(724.538, 306.4721), new Point(736.22986, 290.09503), new Point(746.20734, 276.0735), new Point(756.03815, 262.39502), new Point(765.9623, 249.16235), new Point(773.53235, 238.42303), new Point(780.3832, 228.69019), new Point(785.96515, 220.48755), new Point(789.4589, 211.24298), new Point(793.16455, 205.7367), new Point(793.9488, 203.57544), new Point(794.209, 202.53119), new Point(794.209, 202.23486), new Point(792.11334, 201.96387), new Point(787.68225, 201.96387), new Point(778.96735, 200.88574), new Point(766.3853, 200.87988), new Point(753.6492, 200.87988), new Point(739.8071, 200.87988), new Point(726.903, 200.3269), new Point(710.6879, 199.7959), new Point(690.40924, 200.33789), new Point(670.5642, 201.39062), new Point(646.5694, 199.84064), new Point(622.2178, 198.72046), new Point(598.68304, 197.09705), new Point(573.8663, 196.00195), new Point(540.91565, 196.53241), new Point(512.396, 197.59644), new Point(485.76505, 199.1817), new Point(465.87482, 200.86774), new Point(452.07138, 202.47528), new Point(439.42496, 204.11902), new Point(433.5651, 205.19843), new Point(429.87335, 206.26953), new Point(429.0394, 206.80902), new Point(434.48813, 206.8418), new Point(434.56055, 206.8418), new Point(434.56055, 206.8418)});
        List<Point> pointsT = triangle2.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsT);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.training_check), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        reponseEnigme = mainActivityRule.getActivity().message_Serveur_Reponse;
        backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        reponseEnigmeTexte = reponseEnigme.getText().toString();

        //on a dessiné un triangle en attendant un triangle, on devrait avoir une réponse positive

        assertEquals(" JUSTE", reponseEnigmeTexte);
        assertEquals(Color.GREEN, backgroundReponse.getColor());


        //On teste ensuite avec une énigme de triangle en envoyant un rond (l'énigme ne devrait pas avoir changé)
        mainActivityRule.getActivity().gameEnigme.makeEnigme(Forme.TRIANGLE);

        //un cercle dessiné à la main
        Dessin cercle4 = new Dessin(new Point[]{new Point(316.17188, 168.90234), new Point(316.45114, 168.90234), new Point(317.235, 170.00366), new Point(317.22656, 170.53357), new Point(317.22656, 171.63312), new Point(317.22656, 172.4253), new Point(315.88693, 176.534), new Point(314.30634, 183.05536), new Point(312.4726, 189.0047), new Point(312.74445, 192.20709), new Point(311.95312, 195.9917), new Point(312.74088, 200.8664), new Point(314.86346, 207.96674), new Point(317.4786, 212.76392), new Point(320.13953, 218.24207), new Point(322.74805, 223.0213), new Point(326.43048, 226.88287), new Point(330.92178, 229.59338), new Point(335.7064, 232.32715), new Point(341.48834, 235.5694), new Point(348.6249, 237.19629), new Point(356.70444, 238.8078), new Point(366.81845, 238.81934), new Point(376.01312, 237.73694), new Point(384.20355, 234.4823), new Point(391.26688, 230.71216), new Point(399.22452, 226.35559), new Point(407.4516, 219.2633), new Point(414.0138, 211.14838), new Point(419.51053, 203.61694), new Point(424.01703, 195.45306), new Point(427.46265, 188.37036), new Point(429.81326, 181.91309), new Point(430.6292, 174.76666), new Point(429.54892, 168.33734), new Point(427.7104, 161.8728), new Point(424.82, 154.83508), new Point(420.01697, 148.24243), new Point(414.77277, 142.33313), new Point(410.37817, 138.0553), new Point(405.53918, 133.11652), new Point(400.749, 130.39557), new Point(395.76456, 129.33691), new Point(391.87833, 128.26129), new Point(386.54852, 127.706055), new Point(382.60803, 127.16626), new Point(377.0325, 127.15808), new Point(370.15988, 127.72046), new Point(363.34085, 129.34467), new Point(357.03537, 131.50641), new Point(349.6823, 135.28357), new Point(342.04184, 138.53424), new Point(335.35526, 142.38562), new Point(328.7776, 147.26044), new Point(324.0887, 154.79486), new Point(320.1362, 160.75659), new Point(316.68362, 167.31482), new Point(314.57684, 174.90161), new Point(312.2273, 181.33038), new Point(308.5079, 187.36005), new Point(308.5254, 187.33008)});
        List<Point> pointsCe = cercle4.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsCe);
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.training_check), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        reponseEnigme = mainActivityRule.getActivity().message_Serveur_Reponse;
        backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        reponseEnigmeTexte = reponseEnigme.getText().toString();

        //on a dessiné un rond en attendant un triangle, on devrait avoir une réponse négative

        assertEquals("  FAUX", reponseEnigmeTexte);
        assertEquals(Color.RED, backgroundReponse.getColor());

    }


    @Test
    public void enigmeRondTrainingEnigme() {
        //On teste d'abord avec une énigme de rond en envoyant un carré
        mainActivityRule.getActivity().gameEnigme.makeEnigme(Forme.ROND);
        //un carré dessiné à la main
        Dessin carre1 = new Dessin(new Point[]{new Point(476.48438, 197.62793), new Point(477.2783, 197.08002), new Point(477.2754, 196.27295), new Point(477.2754, 194.91193), new Point(475.42346, 191.6596), new Point(475.94934, 192.1922), new Point(476.22943, 194.9538), new Point(476.75067, 198.71997), new Point(476.48615, 201.41638), new Point(477.53906, 206.29364), new Point(478.30496, 209.48291), new Point(478.32678, 213.31854), new Point(478.59607, 219.82098), new Point(479.11044, 228.46313), new Point(477.81967, 239.80762), new Point(477.79755, 254.36627), new Point(475.97025, 272.3459), new Point(475.1687, 288.2182), new Point(474.1172, 301.64215), new Point(473.85074, 313.59308), new Point(473.0613, 327.0296), new Point(472.00873, 341.7143), new Point(472.00687, 355.22656), new Point(471.4746, 367.2044), new Point(470.95804, 376.828), new Point(470.42398, 388.30096), new Point(469.89987, 398.02997), new Point(469.89258, 408.91437), new Point(469.89258, 418.6587), new Point(470.15314, 428.45898), new Point(470.933, 438.1552), new Point(472.52362, 446.37122), new Point(474.09595, 454.43738), new Point(475.423, 460.45978), new Point(478.3117, 463.1994), new Point(480.4351, 465.3631), new Point(482.02148, 466.18506), new Point(483.34976, 466.45605), new Point(483.60446, 467.0), new Point(484.65662, 465.91516), new Point(486.50494, 465.91406), new Point(488.87344, 465.37207), new Point(491.81467, 465.91406), new Point(495.9593, 466.4485), new Point(502.5796, 466.45605), new Point(510.98236, 466.99805), new Point(520.75793, 468.6192), new Point(532.16235, 468.62402), new Point(549.0218, 469.16772), new Point(566.4696, 468.62402), new Point(583.3781, 469.1731), new Point(600.698, 467.53735), new Point(618.3075, 467.5418), new Point(639.02924, 462.60083), new Point(652.618, 463.2041), new Point(664.19635, 463.2041), new Point(676.2552, 462.6621), new Point(688.3959, 462.12012), new Point(697.6797, 461.03943), new Point(705.61993, 459.95148), new Point(710.59644, 458.86816), new Point(715.1133, 457.78418), new Point(718.2818, 456.68958), new Point(719.86005, 456.7002), new Point(721.69147, 456.7002), new Point(723.54016, 456.7002), new Point(724.86694, 456.7002), new Point(727.22424, 456.1706), new Point(728.26996, 455.6306), new Point(729.3363, 455.6162), new Point(729.85614, 455.6162), new Point(729.61487, 455.6162), new Point(729.47754, 455.6162), new Point(728.8176, 451.8208), new Point(729.08203, 450.72784), new Point(728.8226, 449.66296), new Point(729.60956, 447.48596), new Point(729.87305, 444.78162), new Point(731.44946, 439.95605), new Point(733.0275, 431.2757), new Point(735.3927, 419.94348), new Point(737.5189, 404.67377), new Point(738.83435, 391.70148), new Point(740.1501, 379.78784), new Point(741.2132, 367.24365), new Point(742.25977, 352.72168), new Point(742.5267, 337.5481), new Point(742.5293, 323.39972), new Point(742.5293, 311.99005), new Point(742.2681, 303.35254), new Point(741.47314, 295.17444), new Point(738.8215, 285.3631), new Point(736.99365, 277.3067), new Point(735.3977, 265.29144), new Point(731.70605, 256.12653), new Point(730.67236, 248.61511), new Point(730.1381, 241.54626), new Point(729.3476, 233.96887), new Point(728.81525, 227.39886), new Point(727.76447, 222.026), new Point(726.44086, 217.67249), new Point(724.8322, 213.29462), new Point(724.33575, 209.55048), new Point(724.0709, 207.37823), new Point(723.805, 205.74298), new Point(723.28156, 204.67444), new Point(722.486, 203.58405), new Point(721.96173, 203.58984), new Point(720.38086, 203.58984), new Point(718.5226, 202.49939), new Point(716.147, 203.05823), new Point(714.05176, 202.50537), new Point(710.0881, 202.50916), new Point(705.33136, 200.8761), new Point(699.524, 200.33789), new Point(690.93726, 198.13373), new Point(682.3257, 196.53406), new Point(674.18713, 195.45294), new Point(663.883, 193.27716), new Point(653.8438, 191.1018), new Point(644.30396, 189.47076), new Point(634.1812, 188.41644), new Point(621.62726, 186.77496), new Point(609.8567, 185.69983), new Point(598.76105, 184.07123), new Point(588.80786, 183.53607), new Point(578.29987, 182.45605), new Point(567.1974, 182.45215), new Point(553.678, 181.91016), new Point(540.303, 182.45123), new Point(529.8142, 183.52911), new Point(519.63354, 184.63019), new Point(511.05908, 186.2461), new Point(499.35345, 186.2461), new Point(490.1763, 186.7851), new Point(482.34717, 186.78809), new Point(475.42877, 186.78809), new Point(472.28058, 187.32495), new Point(470.94434, 187.33008), new Point(472.79297, 187.33008), new Point(472.79297, 187.33008)});
        List<Point> pointsC = carre1.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsC);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.training_check), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        TextView reponseEnigme = mainActivityRule.getActivity().message_Serveur_Reponse;
        ColorDrawable backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        String reponseEnigmeTexte = reponseEnigme.getText().toString();

        //on a dessiné un carré en attendant un triangle, on devrait avoir une réponse négative

        assertEquals("  FAUX", reponseEnigmeTexte);
        assertEquals(Color.RED, backgroundReponse.getColor());
        //On teste ensuite avec une énigme de triangle en envoyant un triangle
        //un triangle dessiné à la main
        Dessin triangle2 = new Dessin(new Point[]{new Point(437.98828, 220.3916), new Point(438.51562, 219.84961), new Point(438.25195, 218.21912), new Point(438.25195, 218.76562), new Point(438.25195, 219.57861), new Point(438.78064, 220.93634), new Point(439.81952, 225.73224), new Point(441.13974, 232.29321), new Point(444.58295, 239.3664), new Point(449.28683, 246.89258), new Point(454.20016, 253.79974), new Point(460.37677, 264.79126), new Point(465.5229, 272.2057), new Point(472.89328, 282.54297), new Point(479.7669, 291.17554), new Point(486.2665, 299.83673), new Point(492.6764, 308.56567), new Point(499.396, 318.1327), new Point(508.2655, 328.6411), new Point(514.7587, 339.792), new Point(519.3762, 349.1853), new Point(522.4679, 358.2727), new Point(525.75085, 367.16138), new Point(529.1385, 375.67938), new Point(533.12683, 385.55426), new Point(536.269, 394.2295), new Point(546.3458, 406.9795), new Point(548.7368, 419.26678), new Point(552.60156, 427.25995), new Point(557.9572, 437.18518), new Point(562.8113, 445.09174), new Point(567.6944, 453.94818), new Point(571.3624, 463.0882), new Point(575.0095, 471.15277), new Point(577.6874, 476.65735), new Point(580.0993, 481.61517), new Point(581.8987, 484.7719), new Point(582.98596, 487.00684), new Point(583.2715, 487.59375), new Point(583.0444, 487.1269), new Point(582.74414, 486.50977), new Point(583.7848, 488.10687), new Point(583.93066, 488.40674), new Point(585.613, 487.60675), new Point(587.7051, 486.01794), new Point(590.11194, 482.7343), new Point(593.2496, 476.3255), new Point(598.1401, 468.30225), new Point(603.13873, 459.64563), new Point(610.60034, 449.227), new Point(619.55396, 438.92157), new Point(628.33026, 427.47046), new Point(641.49316, 411.77716), new Point(653.8396, 397.19763), new Point(664.34454, 384.7827), new Point(675.13904, 370.198), new Point(687.0124, 355.3236), new Point(700.6968, 338.0075), new Point(713.12225, 321.72003), new Point(724.538, 306.4721), new Point(736.22986, 290.09503), new Point(746.20734, 276.0735), new Point(756.03815, 262.39502), new Point(765.9623, 249.16235), new Point(773.53235, 238.42303), new Point(780.3832, 228.69019), new Point(785.96515, 220.48755), new Point(789.4589, 211.24298), new Point(793.16455, 205.7367), new Point(793.9488, 203.57544), new Point(794.209, 202.53119), new Point(794.209, 202.23486), new Point(792.11334, 201.96387), new Point(787.68225, 201.96387), new Point(778.96735, 200.88574), new Point(766.3853, 200.87988), new Point(753.6492, 200.87988), new Point(739.8071, 200.87988), new Point(726.903, 200.3269), new Point(710.6879, 199.7959), new Point(690.40924, 200.33789), new Point(670.5642, 201.39062), new Point(646.5694, 199.84064), new Point(622.2178, 198.72046), new Point(598.68304, 197.09705), new Point(573.8663, 196.00195), new Point(540.91565, 196.53241), new Point(512.396, 197.59644), new Point(485.76505, 199.1817), new Point(465.87482, 200.86774), new Point(452.07138, 202.47528), new Point(439.42496, 204.11902), new Point(433.5651, 205.19843), new Point(429.87335, 206.26953), new Point(429.0394, 206.80902), new Point(434.48813, 206.8418), new Point(434.56055, 206.8418), new Point(434.56055, 206.8418)});
        List<Point> pointsT = triangle2.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsT);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.training_check), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        reponseEnigme = mainActivityRule.getActivity().message_Serveur_Reponse;
        backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        reponseEnigmeTexte = reponseEnigme.getText().toString();

        //on a dessiné un triangle en attendant un triangle, on devrait avoir une réponse positive
        assertEquals("  FAUX", reponseEnigmeTexte);
        assertEquals(Color.RED, backgroundReponse.getColor());

        //On teste ensuite avec une énigme de rond en envoyant un rond (l'énigme ne devrait pas avoir changé)

        //un cercle dessiné à la main
        Dessin cercle4 = new Dessin(new Point[]{new Point(316.17188, 168.90234), new Point(316.45114, 168.90234), new Point(317.235, 170.00366), new Point(317.22656, 170.53357), new Point(317.22656, 171.63312), new Point(317.22656, 172.4253), new Point(315.88693, 176.534), new Point(314.30634, 183.05536), new Point(312.4726, 189.0047), new Point(312.74445, 192.20709), new Point(311.95312, 195.9917), new Point(312.74088, 200.8664), new Point(314.86346, 207.96674), new Point(317.4786, 212.76392), new Point(320.13953, 218.24207), new Point(322.74805, 223.0213), new Point(326.43048, 226.88287), new Point(330.92178, 229.59338), new Point(335.7064, 232.32715), new Point(341.48834, 235.5694), new Point(348.6249, 237.19629), new Point(356.70444, 238.8078), new Point(366.81845, 238.81934), new Point(376.01312, 237.73694), new Point(384.20355, 234.4823), new Point(391.26688, 230.71216), new Point(399.22452, 226.35559), new Point(407.4516, 219.2633), new Point(414.0138, 211.14838), new Point(419.51053, 203.61694), new Point(424.01703, 195.45306), new Point(427.46265, 188.37036), new Point(429.81326, 181.91309), new Point(430.6292, 174.76666), new Point(429.54892, 168.33734), new Point(427.7104, 161.8728), new Point(424.82, 154.83508), new Point(420.01697, 148.24243), new Point(414.77277, 142.33313), new Point(410.37817, 138.0553), new Point(405.53918, 133.11652), new Point(400.749, 130.39557), new Point(395.76456, 129.33691), new Point(391.87833, 128.26129), new Point(386.54852, 127.706055), new Point(382.60803, 127.16626), new Point(377.0325, 127.15808), new Point(370.15988, 127.72046), new Point(363.34085, 129.34467), new Point(357.03537, 131.50641), new Point(349.6823, 135.28357), new Point(342.04184, 138.53424), new Point(335.35526, 142.38562), new Point(328.7776, 147.26044), new Point(324.0887, 154.79486), new Point(320.1362, 160.75659), new Point(316.68362, 167.31482), new Point(314.57684, 174.90161), new Point(312.2273, 181.33038), new Point(308.5079, 187.36005), new Point(308.5254, 187.33008)});
        List<Point> pointsCe = cercle4.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsCe);
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.training_check), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        reponseEnigme = mainActivityRule.getActivity().message_Serveur_Reponse;
        backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        reponseEnigmeTexte = reponseEnigme.getText().toString();

        //on a dessiné un rond en attendant un triangle, on devrait avoir une réponse négative
        assertEquals(" JUSTE", reponseEnigmeTexte);
        assertEquals(Color.GREEN, backgroundReponse.getColor());


    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
