package Controller;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    
    private static Stage stage;
    private static Scene login;
    private static Scene telaGerente;
    private static Scene vendas;
    private static Scene funcionarios;
    private static Scene produtos;
    private static Scene novoFunc;
    private static Scene editaFunc;
    private static Scene novoProd;
    private static Scene editaProd;
    private static Scene lancaVenda;
    
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        
        //este foi o metodo que encontramos para ficar trocando de tela
        Parent log = FXMLLoader.load((getClass().getResource("../view/Login.fxml")));
        login = new Scene(log, 640, 400);
        
        Parent telaGer = FXMLLoader.load((getClass().getResource("../view/TelaGerente.fxml")));
        telaGerente = new Scene(telaGer, 640, 400);

        Parent telatFunc = FXMLLoader.load((getClass().getResource("../view/PFuncionario.fxml")));
        funcionarios = new Scene(telatFunc, 640, 400);
        
        Parent telatVenda = FXMLLoader.load((getClass().getResource("../view/PVenda.fxml")));
        vendas = new Scene(telatVenda, 640, 400);

        Parent telatProd = FXMLLoader.load((getClass().getResource("../view/PProdutos.fxml")));
        produtos = new Scene(telatProd, 640, 400);
        
        Parent telaNfunc = FXMLLoader.load((getClass().getResource("../view/NovoFuncionario.fxml")));
        novoFunc = new Scene(telaNfunc, 640, 400);

        Parent editarFunc = FXMLLoader.load((getClass().getResource("../view/EditarFuncionario.fxml")));
        editaFunc = new Scene(editarFunc, 640, 400);
        
        Parent telaNProd = FXMLLoader.load((getClass().getResource("../view/NovoProduto.fxml")));
        novoProd = new Scene(telaNProd, 640, 400);
        
        Parent editarProd = FXMLLoader.load((getClass().getResource("../view/EditarProduto.fxml")));
        editaProd = new Scene(editarProd, 640, 400);

        Parent lancarVenda = FXMLLoader.load((getClass().getResource("../view/LancarVenda.fxml")));
        lancaVenda = new Scene(lancarVenda, 640, 400);
        
        primaryStage.setTitle("PaladarLanches");
        primaryStage.setScene(login);
        primaryStage.show();
    }

    public static void chamarTela(String a){
        switch (a) {
            case "telaGer":
                stage.setScene(telaGerente);
                break;

            case "Login":
                stage.setScene(login);
                break;

            case "Funcionario":
                stage.setScene(funcionarios);
                break;

            case "Produtos":
                stage.setScene(produtos);
                break;

            case "Vendas":
                stage.setScene(vendas);
                break;
                
            case "nFunc":
                stage.setScene(novoFunc);
                break;

            case "edtFunc":
                stage.setScene(editaFunc);
                break;

            case "novoProd":
                stage.setScene(novoProd);
                break;
            
            case "edtProd":
                stage.setScene(editaProd);
                break;

            case "lancaVenda":
                stage.setScene(lancaVenda);
                break;

            default:
                System.out.println("erro");
                break;
        }
    }
}
