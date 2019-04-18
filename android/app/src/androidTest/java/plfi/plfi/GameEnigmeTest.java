package plfi.plfi;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AllOf;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;
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
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

@LargeTest
public class GameEnigmeTest {
    private static final String PACKAGE_NAME = "plfi.plfi";
    @Rule
    public IntentsTestRule<gameEnigme> mainActivityRule = new IntentsTestRule<>(gameEnigme.class);
    private boolean dessinEnvoye = false;

    @Test
    public void boutonRetourGameEnigme() {
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
    public void buttonOnlineGameEnigme() {
        // Test transition offline/online

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_rtc_offline), withText("OFFLINE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_enigme),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.menu_rtc_online), withText("ONLINE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_enigme),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());
        intended(AllOf.allOf(hasComponent(hasShortClassName(".gameEnigme")), toPackage(PACKAGE_NAME)));

        // Test si en appuyant une nouvelle fois sur online on reste sur ce mode de jeu (rien ne se passe)
        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.menu_rtc_online), withText("ONLINE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_enigme),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());
        intended(AllOf.allOf(hasComponent(hasShortClassName(".gameEnigme")), toPackage(PACKAGE_NAME)));
    }

    @Test
    public void buttonOfflineGameEnigme() {

        // Test transition online/offline

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_rtc_online), withText("ONLINE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_enigme),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.menu_rtc_offline), withText("OFFLINE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_enigme),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        intended(AllOf.allOf(hasComponent(hasShortClassName(".TrainingEnigme"))));
        // on test si en appuyant une nouvelle fois sur offline on reste sur ce mode de jeu (rien ne se passe)

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.menu_rtc_offline), withText("OFFLINE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_enigme),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        intended(AllOf.allOf(hasComponent(hasShortClassName(".TrainingEnigme"))));

    }

    @Test
    public void gommeEnigme() {

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
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        assertTrue(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());

    }


    @Test
    public void sendEnigme() {
        assertFalse(dessinEnvoye);
        Point[] pointsCercle = new Point[180];
        for (int i = 0; i < 180; i++) {
            pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 180.0) * 50 + 125,
                    (float) Math.sin(i * 2 * Math.PI / 180.0) * 50 + 125);
        }
        for (Point p : pointsCercle)
            mainActivityRule.getActivity().gamePrint.getPoints().add(p);
        mainActivityRule.getActivity().connexion = new Connexion("", "", mainActivityRule.getActivity().controleur) {
            public void sendForme(List<Point> points, String emit) {
                dessinEnvoye = true;
            }

        };
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.enigme_save_btn), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());
        Assert.assertTrue(dessinEnvoye);
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