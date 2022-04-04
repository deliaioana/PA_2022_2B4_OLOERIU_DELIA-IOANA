import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;
    private Graphics graphics;
    private boolean currentPlayer = false;
    private Graph graph;

    BufferedImage image; //the offscreen image
    Graphics2D offscreen; //the offscreen graphics

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init(frame.configPanel.getRows(), frame.configPanel.getCols());
    }

    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        createGraph(rows, cols);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                tryPlacingStone(event.getX(), event.getY());
                repaint();
            }
        });
    }

    private void createGraph(int rows, int cols) {
        graph = new Graph();
        for(int i=0; i<rows; ++i)
            for(int j=0; j<cols; ++j){
                Node node = new Node(i+1, j+1);
                graph.addNode(node);
            }
    }

    public void tryPlacingStone(int x, int y) {
        int thisRow = getRowFromX(y);
        int thisCol = getColFromY(x);

        if(isAValidNode(thisRow, thisCol)) {
            if(respectsRules(thisRow, thisCol, currentPlayer)) {
                graph.getNodeByRowCol(thisRow, thisCol).setUsed(true);
                graph.getNodeByRowCol(thisRow, thisCol).setPlayer(currentPlayer);
                this.setCurrentPlayer(!currentPlayer);
            }
            else {
                System.out.println("Nod incorect! Alegeti alta mutare!");
            }
        }
        else {
            System.out.println("Nu ati apasat un nod!");
        }

    }

    private boolean isAValidNode(int row, int col) {
        return graph.getNodeByRowCol(row, col) != null;
    }

    private int getColFromY(int x) {
        int col = 2;
        //de implementat
        return col;
    }

    private int getRowFromX(int y) {
        int row = 2;
        //de implementat
        return row;
    }

    private boolean respectsRules(int x, int y, boolean currentPlayer) {
        //de implementat
        //sa fie langa ultimul pus
        //
        if(true){
            return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        setGraphics(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
        placeStartingSticks(g);
        placeStones(g);
    }

    private void placeStones(Graphics2D g) {
        for (Node node : graph.getNodes()) {
            if(node.isUsed())
                placeStone(g, node.getRow(), node.getCol(), node.getPlayer());
        }
    }

    public void placeStone(Graphics2D graphics, int nodeX, int nodeY, boolean player){
        Graphics2D g = (Graphics2D) graphics;
        if(!player){
            g.setColor(Color.black);
        }
        else{
            g.setColor(Color.orange);
        }
        g.fillOval(padX + (nodeX - 1) * cellWidth - stoneSize / 2,
                padY + (nodeY - 1) * cellHeight - stoneSize / 2, stoneSize, stoneSize);
    }

    public void placeStartingSticks(Graphics2D g) {
        for (int row = 0; row < rows; row++) {
            for(int horizontalBridge = 0; horizontalBridge < cols-1; horizontalBridge ++){
                int x1 = padX + horizontalBridge * cellWidth;
                int y1 = padY + row * cellHeight;
                int x2 = padX + (horizontalBridge+1) * cellWidth;
                int y2 = y1;
                if(Math.random()<0.5)
                    placeStick(g, x1, y1, x2, y2);
            }
        }
        for (int col = 0; col < cols; col++) {
            for(int verticalBridge = 0; verticalBridge < rows-1; verticalBridge ++){
                int x1 = padX + col * cellWidth;
                int y1 = padY + verticalBridge * cellHeight;
                int x2 = x1;
                int y2 = padY + (verticalBridge+1) * cellHeight;
                if(Math.random()<0.5)
                    placeStick(g, x1, y1, x2, y2);
            }
        }
    }

    private void placeStick(Graphics2D g, int x1, int y1, int x2, int y2){
        g.setStroke(new BasicStroke(5));
        g.setColor(Color.black);
        g.drawLine(x1, y1, x2, y2);
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        //horizontal lines
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
        }
        //vertical lines
        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int x2 = x1;
            int y2 = padY + boardHeight;
            g.drawLine(x1, y1, x2, y2);
        }
        //intersections
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }

    private void createOffscreenImage() {
        image = new BufferedImage(
                canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE); //fill the image with white
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    @Override
    public void update(Graphics g) { } //No need for update

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean getCurrentPlayer() {
        return currentPlayer;
    }

    //Draw the offscreen image, using the original graphics
    /**@Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }*/

}