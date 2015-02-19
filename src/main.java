import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class main extends Application {

	Scene scene;
	Tab tab1, tab2;
	TabPane root;
	StackPane containerTab1, containerTab2;
	Pane uiTab1, uiTab2;

	Button calculate;
	TextField yield, orePrice, oreDensity;

	ListView<String> oreList;

	/***
	 * Holds Jita prices for the ore and the density of the ore in m3 for calculating ISK per cycle later
	 * Eventually update these prices automatically via Eve-market API
	 */
	String[][] ores = new String [][] { { "Arkonor", "2200","16"},
			{ "Bistot", "2550", "16"},
			{ "Crokite", "2600", "16"},
			{ "Dark Ochre", "1300", "8"},
			{ "Gneiss", "913", "5"}, 
			{ "Hedbergite", "625", "3"}, 
			{ "Hemorphite", "620", "3"}, 
			{ "Jaspet", "368", "2"}, 
			{ "Kernite", "265", "1.2"}, 
			{ "Omber", "89", "0.6"}, 
			{ "Spodumain", "2050", "16"}, 
			{ "Veldspar", "19", "0.1"}};

	/***
	 * IPC = ISK per cycle
	 */
	double arkonorIPC, bistotIPC, crokiteIPC, darkOchreIPC, gneissIPC, hedbergiteIPC, 
	hemorphiteIPC, jaspetIPC, kerniteIPC, omberIPC, spodumainIPC, veldsparIPC;

	/***
	 * Change these to current ships values
	 */
	int cycleYield = 1941;
	double cycleTime = 169.2;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		System.out.println(ores[0][2]);

		stage.setTitle("EVE - Ore calculator");
		root = new TabPane();
		scene = new Scene(root, 800, 600);
		stage.setScene(scene);

		tab1 = new Tab();
		tab1.setText("Ore");
		tab1.setClosable(false);
		root.getTabs().add(tab1);

		tab2 = new Tab();
		tab2.setText("Update prices");
		tab2.setClosable(false);
		root.getTabs().add(tab2);
		stage.show();

		calculateIPC();
		renderUI();
	}

	public void calculateIPC() {

		double temp;

		temp = (cycleYield / Double.parseDouble(ores[0][2]));
		arkonorIPC = (temp * Double.parseDouble(ores[0][1]));
		temp = 0;	
	}

	public void renderUI() {

		containerTab1 = new StackPane();
		containerTab1.setMaxSize(800,600);
		containerTab1.setTranslateX(0);
		containerTab1.setTranslateY(0);
		containerTab1.setStyle("-fx-background-color: #000000;");

		containerTab2 = new StackPane();
		containerTab2.setMaxSize(800,600);
		containerTab2.setTranslateX(0);
		containerTab2.setTranslateY(0);
		containerTab2.setStyle("-fx-background-color: #000000;");

		uiTab1 = new Pane();
		uiTab1.setMaxSize(800,600);
		uiTab1.setTranslateX(0);
		uiTab1.setTranslateY(0);
		uiTab1.setStyle("-fx-background-color: #000000;");

		//txtArkonor = new TextField();
		//txtArkonor.setLayoutX(10);
		//txtArkonor.setLayoutY(20);

		oreList = new ListView<String>();
		oreList.setLayoutX(10);
		oreList.setLayoutY(10);
		oreList.setPrefWidth(250);
		oreList.setPrefHeight(260);
		oreList.setId("deviceDetailsList");
		oreList.setStyle("-fx-font-family: monospace");

		String arkonorResults = String.format("%-15s %-3s", "Arkonor", Double.toString(arkonorIPC));
		String bistotResults = String.format("%-15s %-3s", "Bistot", Double.toString(bistotIPC));
		String crokiteResults = String.format("%-15s %-3s", "Crokite", Double.toString(crokiteIPC));
		String darkOchreResults = String.format("%-15s %-3s", "Dark Ochre", Double.toString(darkOchreIPC));
		String gneissResults = String.format("%-15s %-3s", "Gneiss", Double.toString(gneissIPC));
		String hedbergiteResults = String.format("%-15s %-3s", "Hedbergite", Double.toString(hedbergiteIPC));
		String hemorphiteResults = String.format("%-15s %-3s", "Hemorphite", Double.toString(hemorphiteIPC));
		String jaspetResults = String.format("%-15s %-3s", "Jaspet", Double.toString(jaspetIPC));
		String kerniteResults = String.format("%-15s %-3s", "Kernite", Double.toString(kerniteIPC));
		String omberResults = String.format("%-15s %-3s", "Omber", Double.toString(omberIPC));
		String spodumainResults = String.format("%-15s %-3s", "Spodumain", Double.toString(spodumainIPC));
		String veldsparResults = String.format("%-15s %-3s", "Veldspar", Double.toString(veldsparIPC));

		oreList.getItems().addAll(arkonorResults, bistotResults, crokiteResults, darkOchreResults, gneissResults, hedbergiteResults, 
				hemorphiteResults, jaspetResults, kerniteResults, omberResults, spodumainResults, veldsparResults);

		//Label lblScore = new Label("0");
		//lblScore.setLayoutX(250);
		//lblScore.setLayoutY(52);
		//lblScore.setStyle("-fx-font-size: 20px;");

		//TextField answerInput = new TextField();
		//answerInput.setLayoutX(15);
		//answerInput.setLayoutY(55);

		//Button btnStart = new Button("Start");
		//btnStart.setLayoutX(700);
		//btnStart.setLayoutY(30);
		//btnStart.setStyle("-fx-font-size: 20px;");

		uiTab1.getChildren().addAll(oreList);
		//uiTab2.getChildren().addAll();

		containerTab1.getChildren().addAll(uiTab1);
		//containerTab1.getChildren().addAll();
		tab1.setContent(containerTab1);
		tab2.setContent(containerTab2);
	}
}
