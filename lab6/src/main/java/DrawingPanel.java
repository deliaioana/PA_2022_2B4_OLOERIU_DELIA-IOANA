import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;
    private boolean currentPlayer = false;
    private Graph graph;
    private boolean firstTime = true;

    BufferedImage image; //the offscreen image
    Graphics2D offscreen; //the offscreen graphics

    public Graph getGraph(){
        return graph;
    }

    public void setFirstTime(Boolean value){
        firstTime = value;
    }

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

        if(graph != null)
            nullifyCurrentGraphAndNodes();
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
        NodeIDGenerator nodeIDGenerator = new NodeIDGenerator();

        graph = new Graph();
        for(int i=0; i<rows; ++i)
            for(int j=0; j<cols; ++j){
                Node node = new Node(i+1, j+1, nodeIDGenerator.getNewID());
                graph.addNode(node);
            }
    }

    private void nullifyCurrentGraphAndNodes() {
        graph.clearGraph();
    }

    public void tryPlacingStone(int x, int y) {
        int circleX = getCircleX(x);
        int circleY = getCircleY(y);

        int thisRow = getRowFromY(y);
        int thisCol = getColFromX(x);

        if(areCoordInsideCircle(x, y, circleX, circleY)) {
            if(respectsRules(thisRow, thisCol, currentPlayer)) {
                graph.getNodeByRowCol(thisRow, thisCol).setUsed(true);
                graph.getNodeByRowCol(thisRow, thisCol).setPlayer(currentPlayer);
                this.setCurrentPlayer(!currentPlayer);
                graph.setLastNode(graph.getNodeByRowCol(thisRow, thisCol));
                if(isAWinningPosition())
                    if(currentPlayer)
                        System.out.println("Playarul portocaliu a castigat!");
                    else
                        System.out.println("Playarul negru a castigat!");
            }
        }
    }

    private boolean isAWinningPosition() {
        int numberOfPossibleMoves = 0;
        for (Node node : graph.getLastNode().getConnections()) {
            if(!node.isUsed())
                ++numberOfPossibleMoves;
        }
        return numberOfPossibleMoves == 0;
    }

    private boolean areCoordInsideCircle(int x, int y, int circleX, int circleY) {
        double distance = Math.sqrt((x-circleX)*(x-circleX) + (y-circleY)*(y-circleY));
        return ((double) stoneSize) / 2 > distance;
    }

    private int getCircleX(int x) {
        int newX = x - padX + stoneSize/2;
        int rectangleCol = ((int) Math.ceil(((double) newX) / ((double) cellWidth)));
        int rectangleX = padX + (rectangleCol-1) * cellWidth;
        return rectangleX;
    }

    private int getCircleY(int y) {
        int newY = y - padY + stoneSize/2;
        int rectangleRow = ((int) Math.ceil(((double) newY) / ((double) cellHeight)));
        int rectangleY = padY + (rectangleRow-1) * cellHeight;
        return rectangleY ;
    }

    private boolean isAValidNode(int row, int col) {
        return graph.getNodeByRowCol(row, col) != null;
    }

    private int getColFromX(int x) {
        int newX = x - padX + stoneSize/2;
        int rectangleCol = ((int) Math.ceil(((double) newX) / ((double) cellWidth)));
        return rectangleCol;
    }

    private int getRowFromY(int y) {
        int newY = y - padY  + stoneSize/2;
        int rectangleRow = ((int) Math.ceil(((double) newY) / ((double) cellHeight)));
        return rectangleRow;

    }

    private boolean respectsRules(int x, int y, boolean currentPlayer) {
        if(graph.getLastNode() == null)
            return (!graph.getNodeByRowCol(x, y).getConnections().isEmpty());
        return (!graph.getNodeByRowCol(x, y).isUsed() &&
                graph.getNodeByRowCol(x, y).isNeighbourWith(graph.getLastNode()));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
        placeSticks(g, firstTime);
        placeStones(g);
    }

    private void placeSticks(Graphics2D g, boolean firstTime) {
        if(firstTime){
            setFirstTime(false);
            placeStartingSticks(g);
        }
        else {
            placeSticksFromMemory(g);
        }
    }

    private void placeSticksFromMemory(Graphics2D g) {
        for ( Node node : graph.getNodes()) {
            for (Node adjacentNode : node.getConnections()) {
                placeStick(g, getNodeCenterX(node), getNodeCenterY(node),
                        getNodeCenterX(adjacentNode), getNodeCenterY(adjacentNode));
            }
        }
    }

    private int getNodeCenterY(Node node) {
        return padY + (node.getRow()-1) * cellHeight;
    }

    private int getNodeCenterX(Node node) {
        return padX + (node.getCol()-1) * cellWidth;
    }

    private void placeStones(Graphics2D g) {
        for (Node node : graph.getNodes()) {
            if(node.isUsed())
                placeStone(g, node.getRow(), node.getCol(), node.getPlayer());
        }
    }

    public void placeStone(Graphics2D graphics, int nodeX, int nodeY, boolean player){
        if(!player){
            graphics.setColor(Color.orange);
        }
        else{
            graphics.setColor(Color.black);
        }
        graphics.fillOval(padX + (nodeY - 1) * cellWidth - stoneSize / 2,
                padY + (nodeX - 1) * cellHeight - stoneSize / 2, stoneSize, stoneSize);
    }

    public void placeStartingSticks(Graphics2D g) {
        for (int row = 0; row < rows; row++) {
            for(int horizontalBridge = 0; horizontalBridge < cols-1; horizontalBridge ++){
                int x1 = padX + horizontalBridge * cellWidth;
                int y1 = padY + row * cellHeight;
                int x2 = padX + (horizontalBridge+1) * cellWidth;
                int y2 = y1;
                if(Math.random()<0.5){
                    placeStick(g, x1, y1, x2, y2);
                    addAdjacency(graph.getNodeByRowCol(row+1, horizontalBridge+1),
                            graph.getNodeByRowCol(row+1, horizontalBridge+2));
                }
            }
        }
        for (int col = 0; col < cols; col++) {
            for(int verticalBridge = 0; verticalBridge < rows-1; verticalBridge ++){
                int x1 = padX + col * cellWidth;
                int y1 = padY + verticalBridge * cellHeight;
                int x2 = x1;
                int y2 = padY + (verticalBridge+1) * cellHeight;
                if(Math.random()<0.5) {
                    placeStick(g, x1, y1, x2, y2);
                    addAdjacency(graph.getNodeByRowCol(verticalBridge+1, col+1),
                            graph.getNodeByRowCol(verticalBridge+2, col+1));
                }
            }
        }
    }

    private void addAdjacency(Node node1, Node node2) {
        node1.addNeighbour(node2);
        node2.addNeighbour(node1);
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

    public void saveAsPng() throws IOException {
        createOffscreenImage();
        File file = new File("newimage.png");
        ImageIO.write(image, "png", file);
    }

    private void createOffscreenImage() {
        image = new BufferedImage(
                canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE); //fill the image with white
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);

        paintGrid(offscreen);
        placeSticks(offscreen, false);
        placeStones(offscreen);
    }

    @Override
    public void update(Graphics g) { } //No need for update

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