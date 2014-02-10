package org.multibit.hd.ui.views.components;

import org.multibit.hd.core.dto.Contact;
import org.multibit.hd.core.dto.TransactionData;
import org.multibit.hd.ui.MultiBitUI;
import org.multibit.hd.ui.views.components.tables.ContactTableModel;
import org.multibit.hd.ui.views.components.tables.StripedTable;
import org.multibit.hd.ui.views.components.tables.TransactionTableModel;

import java.util.Set;

/**
 * <p>Utility to provide the following to UI:</p>
 * <ul>
 * <li>Provision of localised tables with themed rendering</li>
 * </ul>
 *
 * @since 0.0.1
 *  
 */
public class Tables {

  /**
   * Utilities have no public constructor
   */
  private Tables() {
  }

  /**
   * @param contacts The contacts to show
   * @return A new "contacts" striped table
   */
  public static StripedTable newContactsTable(Set<Contact> contacts) {

    ContactTableModel model = new ContactTableModel(contacts);

    StripedTable table = new StripedTable(model);

    table.setFillsViewportHeight(true);
    table.setShowHorizontalLines(true);
    table.setShowVerticalLines(false);

    table.setRowHeight(MultiBitUI.LARGE_ICON_SIZE+10);
    table.setAutoCreateRowSorter(true);

    return table;
  }

  /**
    * @param transactions The transactions to show
    * @return A new "transactions" striped table
    */
   public static StripedTable newTransactionsTable(Set<TransactionData> transactions) {

     TransactionTableModel model = new TransactionTableModel(transactions);

     StripedTable table = new StripedTable(model);

     table.setFillsViewportHeight(true);
     table.setShowHorizontalLines(true);
     table.setShowVerticalLines(false);

     table.setRowHeight(MultiBitUI.LARGE_ICON_SIZE+10);
     table.setAutoCreateRowSorter(true);

     return table;
   }
}
