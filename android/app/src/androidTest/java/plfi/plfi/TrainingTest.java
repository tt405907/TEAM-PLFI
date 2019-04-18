package plfi.plfi;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;
import commun.Dessin;
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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TrainingTest {
    private static final String PACKAGE_NAME = "plfi.plfi";
    @Rule
    public IntentsTestRule<Training> mainActivityRule = new IntentsTestRule<>(Training.class);

    @Test
    public void bouton_retour_Training() {

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Revenir en haut de la page"),
                        childAtPosition(
                                allOf(withId(R.id.my_toolbar_training_rtc),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        intended(AllOf.allOf(hasComponent(hasShortClassName(".MainActivity")), toPackage(PACKAGE_NAME)));
    }

    @Test
    public void bouton_Gomme_Training() {
        // On insert un Point
        mainActivityRule.getActivity().gamePrint.getPoints().add(new Point(5, 6));
        // On vérifie que le canvas est pas vite
        assertFalse(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.new_btn), withContentDescription("New"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));

        appCompatButton2.perform(click());

        // On vérifie que la liste n'est pas vide
        assertTrue(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());
    }


    @Test
    public void envoiCarreTrainingGame() {
        //un carré dessiné à la main
        Dessin carre1 = new Dessin(new Point[]{new Point(476.48438, 197.62793), new Point(477.2783, 197.08002), new Point(477.2754, 196.27295), new Point(477.2754, 194.91193), new Point(475.42346, 191.6596), new Point(475.94934, 192.1922), new Point(476.22943, 194.9538), new Point(476.75067, 198.71997), new Point(476.48615, 201.41638), new Point(477.53906, 206.29364), new Point(478.30496, 209.48291), new Point(478.32678, 213.31854), new Point(478.59607, 219.82098), new Point(479.11044, 228.46313), new Point(477.81967, 239.80762), new Point(477.79755, 254.36627), new Point(475.97025, 272.3459), new Point(475.1687, 288.2182), new Point(474.1172, 301.64215), new Point(473.85074, 313.59308), new Point(473.0613, 327.0296), new Point(472.00873, 341.7143), new Point(472.00687, 355.22656), new Point(471.4746, 367.2044), new Point(470.95804, 376.828), new Point(470.42398, 388.30096), new Point(469.89987, 398.02997), new Point(469.89258, 408.91437), new Point(469.89258, 418.6587), new Point(470.15314, 428.45898), new Point(470.933, 438.1552), new Point(472.52362, 446.37122), new Point(474.09595, 454.43738), new Point(475.423, 460.45978), new Point(478.3117, 463.1994), new Point(480.4351, 465.3631), new Point(482.02148, 466.18506), new Point(483.34976, 466.45605), new Point(483.60446, 467.0), new Point(484.65662, 465.91516), new Point(486.50494, 465.91406), new Point(488.87344, 465.37207), new Point(491.81467, 465.91406), new Point(495.9593, 466.4485), new Point(502.5796, 466.45605), new Point(510.98236, 466.99805), new Point(520.75793, 468.6192), new Point(532.16235, 468.62402), new Point(549.0218, 469.16772), new Point(566.4696, 468.62402), new Point(583.3781, 469.1731), new Point(600.698, 467.53735), new Point(618.3075, 467.5418), new Point(639.02924, 462.60083), new Point(652.618, 463.2041), new Point(664.19635, 463.2041), new Point(676.2552, 462.6621), new Point(688.3959, 462.12012), new Point(697.6797, 461.03943), new Point(705.61993, 459.95148), new Point(710.59644, 458.86816), new Point(715.1133, 457.78418), new Point(718.2818, 456.68958), new Point(719.86005, 456.7002), new Point(721.69147, 456.7002), new Point(723.54016, 456.7002), new Point(724.86694, 456.7002), new Point(727.22424, 456.1706), new Point(728.26996, 455.6306), new Point(729.3363, 455.6162), new Point(729.85614, 455.6162), new Point(729.61487, 455.6162), new Point(729.47754, 455.6162), new Point(728.8176, 451.8208), new Point(729.08203, 450.72784), new Point(728.8226, 449.66296), new Point(729.60956, 447.48596), new Point(729.87305, 444.78162), new Point(731.44946, 439.95605), new Point(733.0275, 431.2757), new Point(735.3927, 419.94348), new Point(737.5189, 404.67377), new Point(738.83435, 391.70148), new Point(740.1501, 379.78784), new Point(741.2132, 367.24365), new Point(742.25977, 352.72168), new Point(742.5267, 337.5481), new Point(742.5293, 323.39972), new Point(742.5293, 311.99005), new Point(742.2681, 303.35254), new Point(741.47314, 295.17444), new Point(738.8215, 285.3631), new Point(736.99365, 277.3067), new Point(735.3977, 265.29144), new Point(731.70605, 256.12653), new Point(730.67236, 248.61511), new Point(730.1381, 241.54626), new Point(729.3476, 233.96887), new Point(728.81525, 227.39886), new Point(727.76447, 222.026), new Point(726.44086, 217.67249), new Point(724.8322, 213.29462), new Point(724.33575, 209.55048), new Point(724.0709, 207.37823), new Point(723.805, 205.74298), new Point(723.28156, 204.67444), new Point(722.486, 203.58405), new Point(721.96173, 203.58984), new Point(720.38086, 203.58984), new Point(718.5226, 202.49939), new Point(716.147, 203.05823), new Point(714.05176, 202.50537), new Point(710.0881, 202.50916), new Point(705.33136, 200.8761), new Point(699.524, 200.33789), new Point(690.93726, 198.13373), new Point(682.3257, 196.53406), new Point(674.18713, 195.45294), new Point(663.883, 193.27716), new Point(653.8438, 191.1018), new Point(644.30396, 189.47076), new Point(634.1812, 188.41644), new Point(621.62726, 186.77496), new Point(609.8567, 185.69983), new Point(598.76105, 184.07123), new Point(588.80786, 183.53607), new Point(578.29987, 182.45605), new Point(567.1974, 182.45215), new Point(553.678, 181.91016), new Point(540.303, 182.45123), new Point(529.8142, 183.52911), new Point(519.63354, 184.63019), new Point(511.05908, 186.2461), new Point(499.35345, 186.2461), new Point(490.1763, 186.7851), new Point(482.34717, 186.78809), new Point(475.42877, 186.78809), new Point(472.28058, 187.32495), new Point(470.94434, 187.33008), new Point(472.79297, 187.33008), new Point(472.79297, 187.33008)});
        List<Point> pointsC = carre1.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsC);
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.save_btn), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        TextView messageDuServeur = mainActivityRule.getActivity().messageServeur;
        String reponseServeur = messageDuServeur.getText().toString();
        ColorDrawable backgroundReponse = (ColorDrawable) messageDuServeur.getBackground();
        Drawable choixServeur = this.mainActivityRule.getActivity().imageViewServeur.getDrawable();
        if (choixServeur.getConstantState() == mainActivityRule.getActivity().getResources().getDrawable(R.drawable.triangle).getConstantState()) {
            assertEquals(Color.RED, backgroundReponse.getColor());
            assertEquals("programme gagne", reponseServeur);
        } else if (choixServeur.getConstantState() == mainActivityRule.getActivity().getResources().getDrawable(R.drawable.cercle).getConstantState()) {
            assertEquals(Color.GREEN, backgroundReponse.getColor());
            assertEquals("client gagne", reponseServeur);
        } else if (choixServeur.getConstantState() == mainActivityRule.getActivity().getResources().getDrawable(R.drawable.carre).getConstantState()) {
            assertEquals(Color.YELLOW, backgroundReponse.getColor());
            assertEquals("egalite", reponseServeur);
        }


    }

    @Test
    public void envoiTriangleTrainingGame() {
        //un triangle dessiné à la main
        Dessin triangle1 = new Dessin(new Point[]{new Point(302.46094, 465.91406), new Point(303.00388, 466.47205), new Point(302.45435, 465.9005), new Point(302.19214, 465.91406), new Point(302.46094, 465.37207), new Point(303.81226, 458.13995), new Point(307.48395, 448.53918), new Point(312.25705, 439.28198), new Point(318.03995, 429.01257), new Point(324.34048, 417.68445), new Point(333.198, 402.14404), new Point(345.66833, 381.41992), new Point(362.25922, 359.9444), new Point(377.52417, 335.51807), new Point(389.0752, 312.80524), new Point(402.7051, 292.41077), new Point(415.13834, 267.8946), new Point(429.04678, 244.74603), new Point(444.85376, 222.73792), new Point(460.2124, 198.57428), new Point(478.26166, 178.45178), new Point(493.66937, 161.26685), new Point(504.79968, 148.73785), new Point(512.6487, 139.04193), new Point(519.1876, 129.3534), new Point(520.78625, 122.81763), new Point(526.1442, 119.01453), new Point(529.6973, 117.9718), new Point(531.6038, 116.862854), new Point(532.8956, 116.87109), new Point(534.22845, 116.87109), new Point(535.01953, 117.68408), new Point(537.55963, 119.51489), new Point(539.4446, 121.14807), new Point(541.301, 124.36322), new Point(543.11774, 128.67053), new Point(545.5211, 136.67639), new Point(547.8618, 146.95679), new Point(551.0947, 159.11346), new Point(554.97205, 169.80835), new Point(560.2273, 182.23682), new Point(566.3765, 197.57568), new Point(574.0323, 212.24274), new Point(579.771, 226.71881), new Point(584.5572, 239.80261), new Point(590.3831, 255.05914), new Point(597.9008, 274.75934), new Point(604.7362, 291.56378), new Point(613.86163, 307.17828), new Point(622.6094, 324.00995), new Point(631.0538, 341.3744), new Point(637.4868, 355.64734), new Point(643.26575, 369.16382), new Point(647.4971, 378.42657), new Point(650.9666, 387.16425), new Point(656.0215, 399.19482), new Point(660.2939, 410.16644), new Point(664.2156, 419.8363), new Point(666.82153, 427.32806), new Point(668.185, 431.26105), new Point(669.4849, 434.45862), new Point(670.0228, 437.74396), new Point(670.41504, 438.54346), new Point(670.5469, 440.96954), new Point(669.80084, 442.58527), new Point(667.94434, 444.72955), new Point(666.5956, 445.8526), new Point(665.0337, 446.92792), new Point(663.44507, 448.01642), new Point(661.6244, 449.08325), new Point(659.5561, 449.62), new Point(656.376, 450.7152), new Point(652.30206, 451.8355), new Point(647.18085, 451.82227), new Point(641.12415, 452.36426), new Point(633.4884, 453.963), new Point(622.59564, 455.0681), new Point(611.57336, 456.69104), new Point(601.09863, 458.29303), new Point(590.4317, 459.94373), new Point(578.6438, 462.06195), new Point(560.58466, 465.7854), new Point(542.9155, 468.58984), new Point(529.3858, 470.22357), new Point(518.17255, 472.36584), new Point(505.32245, 474.5705), new Point(492.768, 476.7235), new Point(478.58917, 478.85254), new Point(465.83682, 479.966), new Point(454.43716, 478.41248), new Point(444.26907, 474.09338), new Point(434.1202, 472.46057), new Point(421.91095, 470.29834), new Point(411.23468, 469.16602), new Point(402.0073, 465.99994), new Point(393.68533, 462.6588), new Point(386.0641, 459.41803), new Point(378.16843, 457.25085), new Point(368.38266, 455.1266), new Point(359.04828, 453.4704), new Point(348.28192, 452.92853), new Point(340.28574, 453.44824), new Point(334.21695, 455.03107), new Point(328.7553, 457.1245), new Point(323.86963, 458.33936), new Point(319.10208, 458.87836), new Point(315.1786, 458.32617), new Point(312.22882, 459.40192), new Point(309.33908, 458.33392), new Point(307.74615, 457.25024), new Point(305.6296, 455.078), new Point(304.83398, 454.26123), new Point(302.7343, 453.99353), new Point(301.95963, 452.90625), new Point(301.41074, 453.99023), new Point(300.21973, 453.99023), new Point(299.33548, 452.90625), new Point(298.50586, 451.28027), new Point(299.29688, 452.90625), new Point(299.29688, 452.90625)});
        List<Point> pointsT = triangle1.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsT);
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.save_btn), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        TextView messageDuServeur = mainActivityRule.getActivity().messageServeur;
        String reponseServeur = messageDuServeur.getText().toString();
        ColorDrawable backgroundReponse = (ColorDrawable) messageDuServeur.getBackground();
        Drawable choixServeur = this.mainActivityRule.getActivity().imageViewServeur.getDrawable();
        if (choixServeur.getConstantState() == mainActivityRule.getActivity().getResources().getDrawable(R.drawable.triangle).getConstantState()) {
            assertEquals(Color.YELLOW, backgroundReponse.getColor());
            assertEquals("egalite", reponseServeur);

        } else if (choixServeur.getConstantState() == mainActivityRule.getActivity().getResources().getDrawable(R.drawable.cercle).getConstantState()) {
            assertEquals(Color.RED, backgroundReponse.getColor());
            assertEquals("programme gagne", reponseServeur);
        } else if (choixServeur.getConstantState() == mainActivityRule.getActivity().getResources().getDrawable(R.drawable.carre).getConstantState()) {
            assertEquals(Color.GREEN, backgroundReponse.getColor());
            assertEquals("client gagne", reponseServeur);
        }
    }

    @Test
    public void envoiCercleTrainingGame() {
        //un cercle dessiné à la main
        Dessin cercle1 = new Dessin(new Point[]{new Point(964.27734, 83.80957), new Point(964.1455, 83.80957), new Point(963.22266, 83.80957), new Point(957.98535, 80.58081), new Point(952.5073, 77.36157), new Point(948.5232, 74.65619), new Point(944.28906, 72.99213), new Point(940.1433, 71.36334), new Point(937.6814, 71.34375), new Point(936.3502, 71.34375), new Point(934.244, 71.86847), new Point(933.1942, 72.93872), new Point(930.8153, 74.5791), new Point(926.09424, 77.27185), new Point(919.9788, 81.10101), new Point(914.253, 85.40076), new Point(909.5659, 90.1969), new Point(907.13464, 91.90137), new Point(906.26953, 92.75244), new Point(904.9776, 95.15521), new Point(903.926, 97.8103), new Point(901.31555, 102.14551), new Point(898.1853, 105.41559), new Point(896.5632, 110.77362), new Point(895.4782, 112.456116), new Point(895.9863, 116.1864), new Point(896.7817, 119.05682), new Point(897.5326, 123.22803), new Point(897.83203, 129.17981), new Point(897.83203, 135.1427), new Point(898.3452, 139.5473), new Point(899.41406, 143.39496), new Point(900.4368, 144.47986), new Point(904.0283, 148.57764), new Point(902.8113, 148.81726), new Point(903.86365, 150.44086), new Point(907.04803, 152.09485), new Point(908.6333, 156.39825), new Point(910.9426, 158.52948), new Point(918.6176, 163.4519), new Point(920.2741, 166.67273), new Point(924.6116, 168.83142), new Point(928.11, 169.94989), new Point(932.5431, 171.55731), new Point(935.7402, 172.68243), new Point(942.0033, 170.57996), new Point(944.3843, 171.6123), new Point(948.08484, 171.11987), new Point(953.85535, 171.04657), new Point(956.8476, 169.45813), new Point(960.8064, 167.31445), new Point(964.2409, 166.21735), new Point(967.87585, 165.62915), new Point(971.8672, 163.6222), new Point(974.22406, 160.80243), new Point(977.1675, 156.4748), new Point(982.6845, 153.20166), new Point(983.72546, 149.49524), new Point(986.9364, 146.6944), new Point(989.07245, 143.46967), new Point(990.10803, 141.8153), new Point(990.6378, 139.6623), new Point(993.03784, 135.2572), new Point(996.15106, 129.39972), new Point(996.9782, 124.487366), new Point(997.7637, 121.24701), new Point(995.7094, 118.02307), new Point(993.56384, 113.64508), new Point(990.95544, 110.431946), new Point(988.30475, 104.46387), new Point(984.8884, 97.970215), new Point(982.2851, 93.151855), new Point(979.3224, 90.32269), new Point(977.7362, 88.695435), new Point(974.8156, 87.05859), new Point(971.4493, 85.457275), new Point(967.7927, 83.335144), new Point(965.0749, 81.64697), new Point(960.3725, 78.96344), new Point(958.4279, 77.33899), new Point(960.8945, 75.10083), new Point(962.959, 75.67969), new Point(962.959, 75.67969)});
        List<Point> pointsCe = cercle1.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsCe);
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.save_btn), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        TextView messageDuServeur = mainActivityRule.getActivity().messageServeur;
        String reponseServeur = messageDuServeur.getText().toString();
        ColorDrawable backgroundReponse = (ColorDrawable) messageDuServeur.getBackground();
        Drawable choixServeur = this.mainActivityRule.getActivity().imageViewServeur.getDrawable();
        if (choixServeur.getConstantState() == mainActivityRule.getActivity().getResources().getDrawable(R.drawable.triangle).getConstantState()) {
            assertEquals(Color.GREEN, backgroundReponse.getColor());
            assertEquals("client gagne", reponseServeur);


        } else if (choixServeur.getConstantState() == mainActivityRule.getActivity().getResources().getDrawable(R.drawable.cercle).getConstantState()) {
            assertEquals(Color.YELLOW, backgroundReponse.getColor());
            assertEquals("egalite", reponseServeur);

        } else if (choixServeur.getConstantState() == mainActivityRule.getActivity().getResources().getDrawable(R.drawable.carre).getConstantState()) {
            assertEquals(Color.RED, backgroundReponse.getColor());
            assertEquals("programme gagne", reponseServeur);
        }
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
