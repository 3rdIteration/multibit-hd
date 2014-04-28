package org.multibit.hd.ui.views.components.enter_confirm_cancel;

import org.multibit.hd.ui.models.Model;

/**
 * <p>Model to provide the following to view:</p>
 * <ul>
 * <li>Store the yes/no response</li>
 * </ul>
 *
 * @since 0.0.1
 *  
 */
public class EnterConfirmCancelModel implements Model<Boolean> {

  private Boolean value;

  // Supporting values
  private final String panelName;

  /**
   * @param panelName The underlying panel name (to identify the correct subscriber)
   */
  public EnterConfirmCancelModel(String panelName) {
    this.panelName = panelName;
  }

  /**
   * @return True if the answer is Yes, false otherwise
   */
  @Override
  public Boolean getValue() {
    return value;
  }

  @Override
  public void setValue(Boolean value) {
    this.value = value;
  }

  /**
   * @return The underlying panel name (to identify the correct subscriber)
   */
  public String getPanelName() {
    return panelName;
  }

}
