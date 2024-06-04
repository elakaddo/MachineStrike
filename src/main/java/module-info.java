module etu.ecole.ensi.machine_strike {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens etu.ecole.ensi.machine_strike to javafx.fxml;
    exports etu.ecole.ensi.machine_strike;
}