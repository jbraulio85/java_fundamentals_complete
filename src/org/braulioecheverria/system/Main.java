package org.braulioecheverria.system;

import org.braulioecheverria.views.MenuGeneralView;

/**
 *
 * @author Brau
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MenuGeneralView mgv = MenuGeneralView.getInstancia();
        mgv.arranque();
    }
}
