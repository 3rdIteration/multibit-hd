package org.multibit.hd.ui.views.wizards.units_settings;

import org.multibit.hd.core.config.Configuration;
import org.multibit.hd.ui.views.wizards.AbstractWizardPanelModel;

/**
 * <p>Panel model to provide the following to "units settings" wizard:</p>
 * <ul>
 * <li>Storage of state for the "currency selection" panel</li>
 * </ul>
 *
 * @since 0.0.1
 *  
 */
public class UnitsSettingsPanelModel extends AbstractWizardPanelModel {

  private final Configuration configuration;

  /**
   * @param panelName     The panel name
   * @param configuration The configuration (require both Bitcoin and language settings)
   */
  public UnitsSettingsPanelModel(String panelName, Configuration configuration) {
    super(panelName);
    this.configuration = configuration;
  }

  public Configuration getConfiguration() {
    return configuration;
  }

}
