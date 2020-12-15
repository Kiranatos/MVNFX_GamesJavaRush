package net.kiranatos.engine;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Game extends Application implements GameScreen {
    private static final String PATH_TO_SCREEN = "/img/screen.png";
    private static Random random = new Random();
    private int width;
    private int height;
    private static int cellSize;
    private Timeline timeline = new Timeline();
    private int timerStep = 0;
    private StackPane[][] cells;
    private Pane root;
    private Stage primaryStage;
    private boolean showGrid = true;
    private boolean showCoordinates = false;
    private boolean showTV = true;
    private boolean isMessageShown = false;
    private final int APP_WIDTH = 800;
    private final int APP_HEIGHT = 600;
    private final int PADDING_TOP = 110;
    private final int PADDING_DOWN = 140;
    private final int PADDING_SIDE = 125;
    private Text scoreText;
    private Text livesText;
    private TextFlow dialogContainer;

    public Game() { }
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.scoreText = new Text("Score: 0");
        this.initialize();
        Scene scene = new Scene(this.createContent());
        scene.setOnMouseClicked((event) -> {
            if (this.isMessageShown) {
                this.isMessageShown = false;
                this.dialogContainer.setVisible(false);
            }

            if (cellSize != 0) {
                switch(event.getButton()) {
                case PRIMARY:
                    if (this.showTV) {
                        this.onMouseLeftClick((int)(event.getX() - 125.0D) / cellSize, (int)(event.getY() - 110.0D) / cellSize);
                    } else {
                        this.onMouseLeftClick((int)event.getX() / cellSize, (int)event.getY() / cellSize);
                    }
                    break;
                case SECONDARY:
                    if (this.showTV) {
                        this.onMouseRightClick((int)(event.getX() - 125.0D) / cellSize, (int)(event.getY() - 110.0D) / cellSize);
                    } else {
                        this.onMouseRightClick((int)event.getX() / cellSize, (int)event.getY() / cellSize);
                    }
                }

            }
        });
        scene.setOnKeyReleased((event) -> {
            if (!this.isMessageShown) {
                this.onKeyReleased(this.getKey(event.getCode()));
            }
        });
        scene.setOnKeyPressed((event) -> {
            if (this.isMessageShown) {
                if (event.getCode().name().equalsIgnoreCase(KeyJavaRush.SPACE.name())) {
                    this.isMessageShown = false;
                    this.dialogContainer.setVisible(false);
                }

                this.onKeyPress(this.getKey(event.getCode()));
            } else {
                this.onKeyPress(this.getKey(event.getCode()));
            }
        });
        primaryStage.setTitle("JavaRush Game");
        primaryStage.setResizable(false);
        if (this.showTV) {
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
        this.timeline.playFromStart();
    }

    private KeyJavaRush getKey(KeyCode code) {
        String keyName = (String)Arrays.stream(KeyJavaRush.values()).map(Enum::name).filter((name) -> {
            return code.name().equalsIgnoreCase(name);
        }).findFirst().orElse(KeyJavaRush.UNKNOWN.name());
        return KeyJavaRush.valueOf(keyName);
    }

    private Parent createContent() {
        this.root = new Pane();
        this.root.setPrefSize((double)(this.width * cellSize + 250), (double)(this.height * cellSize + 110 + 140));
        this.createBorderImage();

        for(int y = 0; y < this.height; ++y) {
            for(int x = 0; x < this.width; ++x) {
                ObservableList<Node> children = this.cells[y][x].getChildren();
                Rectangle cell;
                if (this.showGrid && children.size() > 0) {
                    cell = (Rectangle)children.get(0);
                    cell.setWidth((double)(cellSize - 1));
                    cell.setHeight((double)(cellSize - 1));                    
                    cell.setStroke(this.toFXColor(ColorJavaRush.BLACK));
                }

                if (this.showCoordinates && children.size() > 2) {
                    Text coordinate = (Text)children.get(2);
                    coordinate.setFont(Font.font((double)cellSize * 0.15D));
                    StackPane.setAlignment(coordinate, Pos.TOP_LEFT);
                    coordinate.setText(x + " - " + y);
                }

                if (children.size() > 0) {
                    cell = (Rectangle)children.get(0);
                    cell.setWidth((double)cellSize);
                    cell.setHeight((double)cellSize);
                    this.cells[y][x].setLayoutX((double)(x * cellSize + 125));
                    this.cells[y][x].setLayoutY((double)(y * cellSize + 110));
                    this.root.getChildren().add(this.cells[y][x]);
                }
            }
        }

        this.createScorePanel();
        this.timeline.setCycleCount(-1);
        return this.root;
    }

    /**
     * Загружает рисунок-рамку и создает рамку
     */
    private void createBorderImage() {        
        InputStream inputStream = Game.class.getResourceAsStream(PATH_TO_SCREEN);
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth((double)(this.width * cellSize + 250));
        imageView.setFitHeight((double)(this.height * cellSize + 110 + 140));
        this.root.getChildren().add(imageView);
    }

    private void createScorePanel() {
        this.scoreText.setFont(Font.font("Verdana", FontWeight.BOLD, 16.0D));
        this.scoreText.setFill(this.toFXColor(ColorJavaRush.BLACK));
        StackPane scorePane = new StackPane(new Node[]{this.scoreText});
        scorePane.setBorder(new Border(new BorderStroke[]{new BorderStroke(this.toFXColor(ColorJavaRush.BLACK), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)}));
        scorePane.setLayoutY((double)(this.height * cellSize + 110 + 6));
        int scoreHeight = 20;
        Rectangle rectangle;
        if (this.showGrid) {
            rectangle = new Rectangle((double)((this.width * cellSize - 1) / 2), (double)scoreHeight, this.toFXColor(ColorJavaRush.WHITE));
            scorePane.setLayoutX((double)(125 + (this.width * cellSize - 1) / 4));
        } else {
            rectangle = new Rectangle((double)(this.width * cellSize / 2), (double)scoreHeight, this.toFXColor(ColorJavaRush.WHITE));
            scorePane.setLayoutX((double)(124 + this.width * cellSize / 4));
        }

        scorePane.getChildren().add(0, rectangle);
        this.root.getChildren().add(scorePane);
    }

    /**
     * Задать размер игрового поля. Обычно он вызывается один раз при запуске игры. 
     * Игровое поле делится игровым движком на клетки. Минимальный размер - 3x3, максимальный - 100x100.
     * @param width количество клеток по горизонтали
     * @param height количество клеток по вертикали
     */
    public void setScreenSize(int width, int height) {
        this.width = width < 3 ? 3 : (width > 100 ? 100 : width);
        this.height = height < 3 ? 3 : (height > 100 ? 100 : height);
        cellSize = 800 / this.width < 600 / this.height ? 800 / this.width : 600 / this.height;
        this.cells = new StackPane[this.height][this.width];

        for(int y = 0; y < this.height; ++y) {
            for(int x = 0; x < this.width; ++x) {
                this.cells[y][x] = new StackPane(new Node[]{new Rectangle(), new Text(), new Text()});
            }
        }

    }

    /**
     * Получить текущую ширину игрового поля
     */
    public int getScreenWidth() {
        return this.width;
    }

    /**
     * Получить текущую высоту игрового поля     
     */
    public int getScreenHeight() {
        return this.height;
    }

    /**
     * устанавливает клетке с координатами (x, y) цвет color
     * @param x
     * @param y
     * @param color 
     */
    public void setCellColor(int x, int y, ColorJavaRush color) {
        if (color != null && color != ColorJavaRush.NONE) {
            ObservableList<Node> children = this.cells[y][x].getChildren();
            if (children.size() > 0 && !Color.valueOf(color.name()).equals(((Rectangle)children.get(0)).getFill())) {
                ((Rectangle)children.get(0)).setFill(Color.valueOf(color.name()));
            }

        }
    }

    /**
     * возвращает цвет клетки с координатами (x, y)
     * @param x
     * @param y
     * @return 
     */
    public ColorJavaRush getCellColor(int x, int y) {
        ObservableList<Node> children = this.cells[y][x].getChildren();
        if (children.size() > 0) {
            Color color = (Color)((Rectangle)children.get(0)).getFill();
            return this.fromFXColor(color);
        } else {
            return ColorJavaRush.NONE;
        }
    }

    /**
     * Включает и выключает сетку, разделяющую клетки. По умолчанию сетка отображается (true).     
     */
    public void showGrid(boolean isShow) {
        this.showGrid = isShow;
    }

    public void showCoordinates(boolean isShow) {
        this.showCoordinates = isShow;
    }

    public void setCellValue(int x, int y, String value) {
        ObservableList<Node> children = this.cells[y][x].getChildren();
        if (children.size() > 1) {
            Text text = (Text)children.get(1);
            if (text.getText().equals(value)) {
                return;
            }

            if (value.length() <= 4) {
                double fontSize = (double)cellSize * 0.4D;
                text.setFont(Font.font(fontSize));
            } else {
                int fontSize = cellSize / value.length();
                text.setFont(Font.font((double)fontSize));
            }

            text.setText(value);
        }

    }

    public String getCellValue(int x, int y) {
        ObservableList<Node> children = this.cells[y][x].getChildren();
        return children.size() > 1 ? ((Text)children.get(1)).getText() : "";
    }

    public void setCellNumber(int x, int y, int value) {
        this.setCellValue(x, y, String.valueOf(value));
    }

    public int getCellNumber(int x, int y) {
        String value = this.getCellValue(x, y);
        if (value != null && !value.isEmpty()) {
            int result = 0;

            try {
                result = Integer.valueOf(value);
            } catch (NumberFormatException var6) {
            }

            return result;
        } else {
            return 0;
        }
    }

    public void setCellTextColor(int x, int y, ColorJavaRush color) {
        ObservableList<Node> children = this.cells[y][x].getChildren();
        if (children.size() > 1) {
            Text text = (Text)children.get(1);
            if (!this.toFXColor(color).equals(text.getFill())) {
                text.setFill(this.toFXColor(color));
            }
        }

    }

    public ColorJavaRush getCellTextColor(int x, int y) {
        ObservableList<Node> children = this.cells[y][x].getChildren();
        if (children.size() > 1) {
            Text text = (Text)children.get(1);
            return this.fromFXColor((Color)text.getFill());
        } else {
            return ColorJavaRush.NONE;
        }
    }

    public void setTurnTimer(int timeMs) {
        this.timeline.stop();
        KeyFrame frame = new KeyFrame(Duration.millis((double)timeMs), (event) -> {
            if (!this.isMessageShown) {
                this.onTurn(++this.timerStep);
            }

        }, new KeyValue[0]);
        this.timeline.getKeyFrames().clear();
        this.timeline.getKeyFrames().add(frame);
        this.timeline.play();
    }

    public void stopTurnTimer() {
        this.timeline.stop();
    }

    /**
     * Возвращает случайное целое число от 0 до (max–1) включительно
     * @param max
     * @return 
     */
    public int getRandomNumber(int max) {
        return random.nextInt(max);
    }

    /**
     * Возвращает случайное целое число от min до (max–1) включительно
     * @param min
     * @param max
     * @return 
     */
    public int getRandomNumber(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public void initialize() {
    }

    public void onMouseLeftClick(int x, int y) {
    }

    public void onMouseRightClick(int x, int y) {
    }

    public void onKeyPress(KeyJavaRush key) {
    }

    public void onKeyReleased(KeyJavaRush key) {
    }

    public void onTurn(int step) {
    }

    private ColorJavaRush fromFXColor(Color color) {
        Field[] fields = Color.class.getFields();
        Map<String, Color> colors = (Map)Arrays.stream(fields).filter((field) -> {
            return Color.class.equals(field.getType());
        }).collect(Collectors.toMap(Field::getName, (field) -> {
            try {
                return (Color)field.get(color);
            } catch (IllegalAccessException var3) {
                var3.printStackTrace();
                return null;
            }
        }));
        Optional<Entry<String, Color>> optionalColorEntry = colors.entrySet().stream().filter((entry) -> {
            return color.equals(entry.getValue());
        }).findFirst();
        ColorJavaRush[] result = new ColorJavaRush[]{ColorJavaRush.NONE};
        optionalColorEntry.ifPresent((entry) -> {
            result[0] = ColorJavaRush.valueOf((String)entry.getKey());
        });
        return result[0];
    }

    private Color toFXColor(ColorJavaRush color) {
        if (color == ColorJavaRush.NONE) {
            return Color.TRANSPARENT;
        } else {
            return color != null ? Color.valueOf(color.name()) : Color.BLACK;
        }
    }

    public void setCellTextSize(int x, int y, int size) {
        ObservableList<Node> children = this.cells[y][x].getChildren();
        if (children.size() > 1) {
            Text text = (Text)children.get(1);
            size = size > 100 ? 100 : size;
            double fontSize = (double)cellSize * ((double)size / 100.0D);
            if (!Font.font(fontSize).equals(text.getFont())) {
                text.setFont(Font.font(fontSize));
            }
        }

    }

    /**
     * Получить размер текста клетки
     * @param x координата
     * @param y координата     
     */
    public int getCellTextSize(int x, int y) {
        ObservableList<Node> children = this.cells[y][x].getChildren();
        if (children.size() > 1) {
            Text text = (Text)children.get(1);
            return (int)(text.getFont().getSize() * 100.0D / (double)cellSize);
        } else {
            return 0;
        }
    }

    /**
     * 
     * @param x координата
     * @param y координата
     * @param cellColor цвет фона клетки
     * @param value текст или символ в клетке     
     */
    public void setCellValueEx(int x, int y, ColorJavaRush cellColor, String value) {
        this.setCellValue(x, y, value);
        this.setCellColor(x, y, cellColor);
    }

    /**
     * 
     * @param x координата
     * @param y координата
     * @param cellColor цвет фона клетки
     * @param value текст или символ в клетке
     * @param textColor цвет символов     
     */    
    public void setCellValueEx(int x, int y, ColorJavaRush cellColor, String value, ColorJavaRush textColor) {
        this.setCellValueEx(x, y, cellColor, value);
        this.setCellTextColor(x, y, textColor);
    }

    /**
     * 
     * @param x координата
     * @param y координата
     * @param cellColor цвет фона клетки
     * @param value текст или символ в клетке
     * @param textColor цвет символов
     * @param textSize размер символов
     */
    public void setCellValueEx(int x, int y, ColorJavaRush cellColor, String value, ColorJavaRush textColor, int textSize) {
        this.setCellValueEx(x, y, cellColor, value, textColor);
        this.setCellTextSize(x, y, textSize);
    }

    /**
     * Выводит диалоговое окно на игровой экран
     * @param cellColor цвет фона диалогового окна
     * @param message текст сообщения
     * @param textColor цвет текста сообщения
     * @param textSize размер текста сообщения
     */
    public void showMessageDialog(ColorJavaRush cellColor, String message, ColorJavaRush textColor, int textSize) {
        if (this.dialogContainer == null) {
            this.dialogContainer = new TextFlow();
            this.root.getChildren().add(this.dialogContainer);
        }

        this.dialogContainer.getChildren().clear();
        Text messageText = new Text();
        messageText.setFont(Font.font("Verdana", FontWeight.BOLD, (double)textSize));
        messageText.setText(message);
        double preferredWidth = messageText.getLayoutBounds().getWidth();
        messageText.setFill(this.toFXColor(textColor));
        this.dialogContainer.setLayoutX((this.root.getWidth() - preferredWidth) / 2.0D);
        this.dialogContainer.setLayoutY(this.root.getHeight() / 2.0D - 30.0D);
        this.dialogContainer.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(this.toFXColor(cellColor), CornerRadii.EMPTY, Insets.EMPTY)}));
        this.dialogContainer.setVisible(true);
        this.dialogContainer.getChildren().add(messageText);
        this.isMessageShown = true;
    }

    public void setScore(int score) {
        this.scoreText.setText("Score: " + score);
    }

    public void setLives(int lives) {
        if (this.livesText == null) {
            this.livesText = new Text();
            this.livesText.setFont(Font.font(20.0D));
            this.livesText.setFill(this.toFXColor(ColorJavaRush.ORANGE));
            this.livesText.setY(160.0D);
            this.livesText.setX(127.0D);
            this.root.getChildren().addAll(new Node[]{this.livesText});
        }

        this.livesText.setText("❤: " + lives);
    }
}
