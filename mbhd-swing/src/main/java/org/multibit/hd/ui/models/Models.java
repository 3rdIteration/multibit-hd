package org.multibit.hd.ui.models;

import org.multibit.hd.core.dto.RAGStatus;

import javax.swing.*;

/**
 * <p>Factory to provide the following to UI:</p>
 * <ul>
 * <li>Provision of simple model wrappers</li>
 * </ul>
 *
 * @since 0.0.1
 *  
 */
public class Models {

  /**
   * Utilities have no public constructor
   */
  private Models() {
  }

  /**
   * @param value The value to set
   *
   * @return A model wrapping the value
   */
  public static <M> Model<M> newModel(M value) {

    return new Model<M>() {

      private M value;

      @Override
      public M getValue() {
        return value;
      }

      @Override
      public void setValue(M value) {
        this.value = value;
      }
    };

  }

  /**
   * <p>A new alert model with no button</p>
   *
   * @param message The message
   * @param status  The RAG status
   *
   * @return A new alert model
   */
  public static AlertModel newAlertModel(String message, RAGStatus status) {
    return new AlertModel(message, status);
  }

  /**
   * <p>A new alert model with button</p>
   *
   * @param message The message
   * @param status  The RAG status
   * @param button  The button triggering an action
   *
   * @return A new alert model
   */
  public static AlertModel newAlertModel(String message, RAGStatus status, JButton button) {

    AlertModel model = newAlertModel(message, status);
    model.setButton(button);

    return model;
  }

}
