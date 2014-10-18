package org.multibit.hd.ui.fest.use_cases.sidebar.manage_wallet.edit_wallet;

import org.fest.swing.fixture.FrameFixture;
import org.multibit.hd.ui.fest.use_cases.AbstractFestUseCase;
import org.multibit.hd.ui.languages.MessageKey;

import java.util.Map;

/**
 * <p>Use case to provide the following to FEST testing:</p>
 * <ul>
 * <li>Verify the "tools" screen about wizard shows</li>
 * </ul>
 * <p>Requires the "tools" screen to be showing</p>
 *
 * @since 0.0.1
 * TODO Implement this
 */
public class ShowThenCancelEditWalletUseCase extends AbstractFestUseCase {

  public ShowThenCancelEditWalletUseCase(FrameFixture window) {
    super(window);
  }

  @Override
  public void execute(Map<String, Object> parameters) {

    // Click on "about"
    window
      .button(MessageKey.SHOW_ABOUT_WIZARD.getKey())
      .click();

    // Verify the "about" wizard appears
    assertLabelText(MessageKey.ABOUT_TITLE);

    // Verify the note appears
    assertLabelText(MessageKey.ABOUT_NOTE_1);

    // Verify "visit website" is present
    window
      .button(MessageKey.VISIT_WEBSITE.getKey())
      .requireVisible()
      .requireEnabled();

    // Verify Finish is present
    window
      .button(MessageKey.FINISH.getKey())
      .requireVisible()
      .requireEnabled();

    // Click Finish
    window
      .button(MessageKey.FINISH.getKey())
      .click();

    // Verify the underlying screen is back
    window
      .button(MessageKey.SHOW_EDIT_WALLET_WIZARD.getKey())
      .requireVisible()
      .requireEnabled();

  }

}
