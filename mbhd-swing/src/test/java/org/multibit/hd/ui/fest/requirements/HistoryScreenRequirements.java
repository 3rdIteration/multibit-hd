package org.multibit.hd.ui.fest.requirements;

import com.google.common.collect.Maps;
import org.fest.swing.fixture.FrameFixture;
import org.multibit.hd.ui.fest.use_cases.history.*;

import java.util.Map;

/**
 * <p>FEST Swing UI test to provide:</p>
 * <ul>
 * <li>Exercise the "contacts" screen to verify its wizards show correctly</li>
 * </ul>
 *
 * @since 0.0.1
 *  
 */
public class HistoryScreenRequirements {

  public static void verifyUsing(FrameFixture window) {

    Map<String,Object> parameters = Maps.newHashMap();

    // Select the history screen
    new ShowHistoryScreenUseCase(window).execute(parameters);

    // Click Edit and update password verified
    new EditPasswordEntryUseCase(window).execute(parameters);

    // Click Edit and fill in some extra info on password but then Cancel
    new EditThenCancelPasswordEntryUseCase(window).execute(parameters);

    // Select wallet created and password then use multi-edit
    new EditOpenedAndPasswordEntryUseCase(window).execute(parameters);

    // Search for the first entry
    new SearchHistoryUseCase(window).execute(parameters);
  }
}
