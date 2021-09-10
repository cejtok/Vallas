module Vallas {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	
	// export Application subclass's package to at least javafx.graphics
	exports application to javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
