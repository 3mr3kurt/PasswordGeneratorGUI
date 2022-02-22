import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
import javafx.geometry.Pos;
import javafx.geometry.HPos;


public class EKPasswordGenerator extends Application{

  PWAlg passwordfinal;
  Slider lengthSetter = new Slider();




  TextField result = new TextField();

  Button generate = new Button("Generate");

  CheckBox lower = new CheckBox();
  CheckBox upper = new CheckBox();
  CheckBox nums = new CheckBox();
  CheckBox symbols = new CheckBox();


  @Override
  public void start(Stage primaryStage){


    lengthSetter.setShowTickLabels(true);
    lengthSetter.setShowTickMarks(true);
    lengthSetter.setMajorTickUnit(8);
    lengthSetter.setMax(32);
    lengthSetter.setSnapToTicks(true);

    result.setEditable(false);


    GridPane gridPane = new GridPane();
    gridPane.setHgap(6);
    gridPane.setVgap(6);
    gridPane.add(new Label("Password length:"),0 ,0);
    gridPane.add(lengthSetter, 1, 0);
    gridPane.add(new Label("Use lowercase letters:"),0,1);
    gridPane.add(lower, 1,1);
    gridPane.add(new Label("Use uppercase letters:"),0,2);
    gridPane.add(upper,1,2);
    gridPane.add(new Label("Use numbers:"),0,3);
    gridPane.add(nums,1,3);
    gridPane.add(new Label("Use symbols:"),0,4);
    gridPane.add(symbols,1,4);
    gridPane.add(new Label("Your password:"),0,5);
    gridPane.add(result,1,5);
    gridPane.add(generate,1,6);

    gridPane.setAlignment(Pos.CENTER);

    lower.setAlignment(Pos.BOTTOM_RIGHT);
    upper.setAlignment(Pos.BOTTOM_RIGHT);
    nums.setAlignment(Pos.BOTTOM_RIGHT);
    symbols.setAlignment(Pos.BOTTOM_RIGHT);
    generate.setAlignment(Pos.BOTTOM_RIGHT);
    result.setAlignment(Pos.BOTTOM_RIGHT);


    generate.setOnAction(e -> generatePassword());


    EventHandler<ActionEvent> checks = e->{
      if (upper.isSelected()&&lower.isSelected()&&nums.isSelected()&&symbols.isSelected()){
        //pw.generate(lengthfromslider).includeUpper(true).....
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeUpper(true).includeLower(true).includeNumbers(true).includeSymbols(true).build();
      }
      else if (upper.isSelected()&&lower.isSelected()&&nums.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeUpper(true).includeLower(true).includeNumbers(true).build();
      }
      else if (upper.isSelected()&&lower.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeUpper(true).includeLower(true).build();
      }
      else if (upper.isSelected()&&nums.isSelected()&&symbols.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeUpper(true).includeNumbers(true).includeSymbols(true).build();
      }
      else if (upper.isSelected()&&nums.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeUpper(true).includeNumbers(true).build();
      }
      else if (upper.isSelected()&&symbols.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeUpper(true).includeSymbols(true).build();
      }
      else if (upper.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeUpper(true).build();
      }
      else if (lower.isSelected()&&nums.isSelected()&&symbols.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeLower(true).includeNumbers(true).includeSymbols(true).build();
      }
      else if (lower.isSelected()&&nums.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeLower(true).includeNumbers(true).build();
      }
      else if (lower.isSelected()&&symbols.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeLower(true).includeSymbols(true).build();
      }
      else if (nums.isSelected()&&symbols.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeNumbers(true).includeSymbols(true).build();
      }
      else if (nums.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeNumbers(true).build();
      }
      else if (symbols.isSelected()){
        passwordfinal = new PWAlg.PasswordGeneratorBuilder().includeSymbols(true).build();
      }
    };

    lower.setOnAction(checks);
    upper.setOnAction(checks);
    nums.setOnAction(checks);
    symbols.setOnAction(checks);

    Scene scene = new Scene(gridPane,400,250);
    primaryStage.setTitle("Emre Kurt's Password Generator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void generatePassword(){
    int passwordlength = (int) Math.round(lengthSetter.getValue());

    result.setText(passwordfinal.generate(passwordlength));
  }
}


//javac --module-path /home/emrentu/Documents/java/javafx-sdk-16/lib --add-modules javafx.controls,javafx.graphics,javafx.media,javafx.fxml EKPasswordGenerator.java
//java --module-path /home/emrentu/Documents/java/javafx-sdk-16/lib --add-modules javafx.controls,javafx.graphics,javafx.media,javafx.fxml EKPasswordGenerator

//create another class with the password algorithm/object
//use handler classes and handlers to transfer button events to object methods (i.e. password.setLength(4))
