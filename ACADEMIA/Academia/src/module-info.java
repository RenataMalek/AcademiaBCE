module academia {

	requires javafx.fxml;
	requires javafx.controls;
	requires org.controlsfx.controls;
	requires java.sql;
	requires java.desktop;
	
	opens academia;
	opens boundary;
	opens control;

}