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
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;
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
@RunWith(AndroidJUnit4.class)
public class TrainingEnigmeTest {
    private static final String PACKAGE_NAME = "plfi.plfi";
    @Rule
    public IntentsTestRule<TrainingEnigme> mainActivityRule = new IntentsTestRule<>(TrainingEnigme.class);
    private boolean dessinEnvoye = false;

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
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.enigme_new_btn), withContentDescription("New"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        assertTrue(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());

    }

    @Test
    public void envoiCarreTrainingEnigme() {
        Forme formeAttendueParEnigme = mainActivityRule.getActivity().gameEnigme.getFormeAttendue();
            //un carré dessiné à la main
            Dessin carre1 = new Dessin(new Point[]{new Point(476.48438, 197.62793), new Point(477.2783, 197.08002), new Point(477.2754, 196.27295), new Point(477.2754, 194.91193), new Point(475.42346, 191.6596), new Point(475.94934, 192.1922), new Point(476.22943, 194.9538), new Point(476.75067, 198.71997), new Point(476.48615, 201.41638), new Point(477.53906, 206.29364), new Point(478.30496, 209.48291), new Point(478.32678, 213.31854), new Point(478.59607, 219.82098), new Point(479.11044, 228.46313), new Point(477.81967, 239.80762), new Point(477.79755, 254.36627), new Point(475.97025, 272.3459), new Point(475.1687, 288.2182), new Point(474.1172, 301.64215), new Point(473.85074, 313.59308), new Point(473.0613, 327.0296), new Point(472.00873, 341.7143), new Point(472.00687, 355.22656), new Point(471.4746, 367.2044), new Point(470.95804, 376.828), new Point(470.42398, 388.30096), new Point(469.89987, 398.02997), new Point(469.89258, 408.91437), new Point(469.89258, 418.6587), new Point(470.15314, 428.45898), new Point(470.933, 438.1552), new Point(472.52362, 446.37122), new Point(474.09595, 454.43738), new Point(475.423, 460.45978), new Point(478.3117, 463.1994), new Point(480.4351, 465.3631), new Point(482.02148, 466.18506), new Point(483.34976, 466.45605), new Point(483.60446, 467.0), new Point(484.65662, 465.91516), new Point(486.50494, 465.91406), new Point(488.87344, 465.37207), new Point(491.81467, 465.91406), new Point(495.9593, 466.4485), new Point(502.5796, 466.45605), new Point(510.98236, 466.99805), new Point(520.75793, 468.6192), new Point(532.16235, 468.62402), new Point(549.0218, 469.16772), new Point(566.4696, 468.62402), new Point(583.3781, 469.1731), new Point(600.698, 467.53735), new Point(618.3075, 467.5418), new Point(639.02924, 462.60083), new Point(652.618, 463.2041), new Point(664.19635, 463.2041), new Point(676.2552, 462.6621), new Point(688.3959, 462.12012), new Point(697.6797, 461.03943), new Point(705.61993, 459.95148), new Point(710.59644, 458.86816), new Point(715.1133, 457.78418), new Point(718.2818, 456.68958), new Point(719.86005, 456.7002), new Point(721.69147, 456.7002), new Point(723.54016, 456.7002), new Point(724.86694, 456.7002), new Point(727.22424, 456.1706), new Point(728.26996, 455.6306), new Point(729.3363, 455.6162), new Point(729.85614, 455.6162), new Point(729.61487, 455.6162), new Point(729.47754, 455.6162), new Point(728.8176, 451.8208), new Point(729.08203, 450.72784), new Point(728.8226, 449.66296), new Point(729.60956, 447.48596), new Point(729.87305, 444.78162), new Point(731.44946, 439.95605), new Point(733.0275, 431.2757), new Point(735.3927, 419.94348), new Point(737.5189, 404.67377), new Point(738.83435, 391.70148), new Point(740.1501, 379.78784), new Point(741.2132, 367.24365), new Point(742.25977, 352.72168), new Point(742.5267, 337.5481), new Point(742.5293, 323.39972), new Point(742.5293, 311.99005), new Point(742.2681, 303.35254), new Point(741.47314, 295.17444), new Point(738.8215, 285.3631), new Point(736.99365, 277.3067), new Point(735.3977, 265.29144), new Point(731.70605, 256.12653), new Point(730.67236, 248.61511), new Point(730.1381, 241.54626), new Point(729.3476, 233.96887), new Point(728.81525, 227.39886), new Point(727.76447, 222.026), new Point(726.44086, 217.67249), new Point(724.8322, 213.29462), new Point(724.33575, 209.55048), new Point(724.0709, 207.37823), new Point(723.805, 205.74298), new Point(723.28156, 204.67444), new Point(722.486, 203.58405), new Point(721.96173, 203.58984), new Point(720.38086, 203.58984), new Point(718.5226, 202.49939), new Point(716.147, 203.05823), new Point(714.05176, 202.50537), new Point(710.0881, 202.50916), new Point(705.33136, 200.8761), new Point(699.524, 200.33789), new Point(690.93726, 198.13373), new Point(682.3257, 196.53406), new Point(674.18713, 195.45294), new Point(663.883, 193.27716), new Point(653.8438, 191.1018), new Point(644.30396, 189.47076), new Point(634.1812, 188.41644), new Point(621.62726, 186.77496), new Point(609.8567, 185.69983), new Point(598.76105, 184.07123), new Point(588.80786, 183.53607), new Point(578.29987, 182.45605), new Point(567.1974, 182.45215), new Point(553.678, 181.91016), new Point(540.303, 182.45123), new Point(529.8142, 183.52911), new Point(519.63354, 184.63019), new Point(511.05908, 186.2461), new Point(499.35345, 186.2461), new Point(490.1763, 186.7851), new Point(482.34717, 186.78809), new Point(475.42877, 186.78809), new Point(472.28058, 187.32495), new Point(470.94434, 187.33008), new Point(472.79297, 187.33008), new Point(472.79297, 187.33008)});
            List<Point> pointsC = carre1.asList();
            mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsC);
            ViewInteraction appCompatButton2 = onView(
                    allOf(withId(R.id.enigme_save_btn), withContentDescription("Save"),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.widget.LinearLayout")),
                                            3),
                                    2),
                            isDisplayed()));
            appCompatButton2.perform(click());

            TextView reponseEnigme = this.mainActivityRule.getActivity().message_Serveur_Reponse;
            ColorDrawable backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
            String reponseEnigmeTexte = reponseEnigme.getText().toString();

            if (formeAttendueParEnigme == Forme.CARRE) {
                assertEquals(" JUSTE", reponseEnigmeTexte);
                assertEquals(Color.GREEN, backgroundReponse.getColor());
            } else {
                assertEquals("  FAUX", reponseEnigmeTexte);
                assertEquals(Color.RED, backgroundReponse.getColor());
            }
        }
    @Test
    public void envoiTriangleTrainingEnigme() {
        Forme formeAttendueParEnigme = mainActivityRule.getActivity().gameEnigme.getFormeAttendue();
        //un triangle dessiné à la main
        Dessin triangle4 = new Dessin(new Point[]{new Point(550.5762, 388.40918), new Point(550.83984, 389.4615), new Point(550.83984, 389.76416), new Point(551.3672, 390.03516), new Point(552.6724, 388.96466), new Point(554.4975, 383.65613), new Point(554.53125, 375.47205), new Point(554.5405, 368.48865), new Point(553.73956, 359.6798), new Point(553.48376, 351.1151), new Point(553.47003, 342.989), new Point(554.008, 334.172), new Point(555.78955, 323.0998), new Point(556.6832, 311.28394), new Point(557.44415, 300.2962), new Point(556.6786, 290.11792), new Point(555.8664, 281.87836), new Point(554.54065, 273.64172), new Point(554.0039, 264.97186), new Point(554.5165, 255.13379), new Point(555.01807, 247.1159), new Point(555.56793, 239.62006), new Point(555.8365, 234.10345), new Point(556.8135, 231.30609), new Point(557.14667, 228.55066), new Point(556.91504, 229.04144), new Point(556.78125, 229.31647), new Point(557.1495, 230.65143), new Point(557.67633, 232.77948), new Point(562.0622, 236.5326), new Point(566.8081, 241.39343), new Point(571.79974, 246.26965), new Point(578.11066, 250.64764), new Point(583.9383, 255.52826), new Point(590.2286, 260.8896), new Point(596.40204, 266.41138), new Point(604.37305, 271.88586), new Point(613.84546, 278.2058), new Point(625.29767, 285.32513), new Point(637.7061, 293.8222), new Point(649.4978, 302.36853), new Point(664.1164, 313.34552), new Point(676.16565, 324.30444), new Point(683.589, 329.7771), new Point(688.8393, 333.53265), new Point(691.54224, 336.2768), new Point(693.7081, 338.48853), new Point(694.2685, 339.6117), new Point(694.67285, 340.44287), new Point(694.9319, 340.9754), new Point(692.1937, 345.01007), new Point(688.7642, 347.7351), new Point(684.80664, 350.4532), new Point(680.3114, 352.09125), new Point(673.7019, 355.35193), new Point(666.63257, 358.04565), new Point(657.62256, 360.76862), new Point(648.3778, 364.02704), new Point(638.55396, 367.8499), new Point(628.07477, 372.70276), new Point(619.167, 377.00415), new Point(609.5862, 381.93365), new Point(598.2418, 385.62604), new Point(582.9226, 391.69617), new Point(571.9629, 395.98505), new Point(560.6105, 401.4101), new Point(551.6765, 404.65137), new Point(544.57135, 407.89465), new Point(539.83966, 410.06122), new Point(537.3926, 410.90186), new Point(538.1836, 410.63086)});
        List<Point> pointsT = triangle4.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsT);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.enigme_save_btn), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        TextView reponseEnigme = this.mainActivityRule.getActivity().message_Serveur_Reponse;
        ColorDrawable backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        String reponseEnigmeTexte = reponseEnigme.getText().toString();

        if (formeAttendueParEnigme == Forme.TRIANGLE) {
            assertEquals(" JUSTE", reponseEnigmeTexte);
            assertEquals(Color.GREEN, backgroundReponse.getColor());
        } else {
            assertEquals("  FAUX", reponseEnigmeTexte);
            assertEquals(Color.RED, backgroundReponse.getColor());
        }
    }
    @Test
    public void envoiRondTrainingEnigme() {
        //un rond dessiné à la main
        Forme formeAttendueParEnigme = mainActivityRule.getActivity().gameEnigme.getFormeAttendue();
        Dessin cercle3 = new Dessin(new Point[] {new Point(655.2539, 159.14648), new Point(655.2539, 158.05847), new Point(654.7223, 155.87701), new Point(653.92426, 153.70337), new Point(643.32745, 142.82751), new Point(634.0664, 139.59625), new Point(622.56805, 142.34381), new Point(609.2754, 149.44574), new Point(595.4891, 153.76508), new Point(575.3632, 161.85565), new Point(552.9284, 168.36523), new Point(532.36426, 176.49805), new Point(513.8703, 187.36267), new Point(492.85968, 200.53455), new Point(478.28595, 215.01703), new Point(463.71548, 230.81348), new Point(450.37827, 248.03699), new Point(438.4465, 269.84204), new Point(427.41586, 289.81635), new Point(420.8574, 308.16968), new Point(415.31918, 332.00415), new Point(415.5801, 355.39203), new Point(420.54248, 377.39075), new Point(428.4125, 400.623), new Point(437.19473, 420.38086), new Point(446.103, 436.5464), new Point(457.1864, 450.6776), new Point(472.9768, 464.76044), new Point(493.36246, 478.38177), new Point(516.2518, 491.90332), new Point(544.03357, 505.07532), new Point(573.77985, 514.07544), new Point(605.475, 518.46655), new Point(637.89246, 518.4859), new Point(671.9125, 515.7964), new Point(704.2461, 507.66516), new Point(732.93567, 492.37097), new Point(757.1519, 471.75787), new Point(776.7725, 448.07574), new Point(793.6215, 420.4903), new Point(806.89874, 394.3022), new Point(817.4198, 368.33655), new Point(825.8685, 341.19104), new Point(825.3175, 315.72583), new Point(818.738, 289.25128), new Point(807.1044, 264.24658), new Point(792.1252, 239.39984), new Point(773.94855, 215.5603), new Point(752.8308, 194.93536), new Point(732.03345, 177.61841), new Point(709.57935, 163.48792), new Point(687.42694, 152.10309), new Point(667.3751, 143.96771), new Point(646.174, 140.73297), new Point(623.8023, 141.81024), new Point(603.6944, 146.19073), new Point(585.793, 154.3382), new Point(578.5254, 157.52051), new Point(578.5254, 157.52051)});
        List<Point> pointsCe = cercle3.asList();
        mainActivityRule.getActivity().gamePrint.getPoints().addAll(pointsCe);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.enigme_save_btn), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        TextView reponseEnigme = this.mainActivityRule.getActivity().message_Serveur_Reponse;
        ColorDrawable backgroundReponse = (ColorDrawable) reponseEnigme.getBackground();
        String reponseEnigmeTexte = reponseEnigme.getText().toString();

        if (formeAttendueParEnigme == Forme.ROND) {
            assertEquals(" JUSTE", reponseEnigmeTexte);
            assertEquals(Color.GREEN, backgroundReponse.getColor());
        } else {
            assertEquals("  FAUX", reponseEnigmeTexte);
            assertEquals(Color.RED, backgroundReponse.getColor());
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
