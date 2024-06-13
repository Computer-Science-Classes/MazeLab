// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.junit.Assert;

// import java.awt.Color;

// import graphics.MazeCanvas.Side;
// import graphics.MazeCanvas;

// import helpers.Order;
// import helpers.OrderedRunner;

// @RunWith(OrderedRunner.class)
// @Order(order = 200)
// public class MazeCheckpoint2a {

// // Region: Cell tests
// @Test
// @Order(order = 201)
// public void testCell_Ctor() {
// MazeCanvas mc = new MazeCanvas(6, 9, 32);
// mc.open();
// Cell c = new Cell(mc, 3, 4);
// Assert.assertNotNull(c);
// mc.close();
// }

// @Test
// @Order(order = 202)
// public void testCell_getRow_getCol() {
// MazeCanvas mc = new MazeCanvas(6, 9, 32);
// Cell c = new Cell(mc, 3, 4);
// Assert.assertEquals(3, c.getRow());
// Assert.assertEquals(4, c.getCol());
// }

// @Test
// @Order(order = 203)
// public void testCell_getWalls() {
// MazeCanvas mc = new MazeCanvas(6, 9, 32);
// Cell c = new Cell(mc, 3, 4);
// Assert.assertEquals(4, c.getWalls().size());
// }

// @Test
// @Order(order = 204)
// public void testCell_removeWall() {
// MazeCanvas mc = new MazeCanvas(6, 9, 32);
// mc.open();
// Cell c = new Cell(mc, 3, 4);
// c.removeWall(Side.Left);
// Assert.assertEquals(3, c.getWalls().size());
// mc.close();
// }
// // EndRegion: Cell tests

// // Region: ShadedCell tests
// @Test
// @Order(order = 205)
// public void testShadedCell_Ctor() {
// MazeCanvas mc = new MazeCanvas(6, 9, 32);
// mc.open();
// ShadedCell sc = new ShadedCell(mc, 3, 4, Color.LIGHT_GRAY);
// Assert.assertNotNull(sc);
// mc.close();
// }
// // EndRegion: ShadedCell tests

// // Region: EdgeCell tests
// @Test
// @Order(order = 206)
// public void testEdgeCell_Ctor() {
// MazeCanvas mc = new MazeCanvas(6, 9, 32);
// mc.open();
// EdgeCell ec = new EdgeCell(mc, 0, 0, Color.WHITE);
// Assert.assertNotNull(ec);
// mc.close();
// }

// @Test
// @Order(order = 207)
// public void testEdgeCell_Walls() {
// MazeCanvas mc = new MazeCanvas(7, 9, 32);
// mc.open();
// // top-left edge cells
// EdgeCell tl = new EdgeCell(mc, 0, 0, Color.WHITE);
// Assert.assertEquals(2, tl.getWalls().size());
// // bottom-right edge cells
// EdgeCell br = new EdgeCell(mc, 6, 8, Color.WHITE);
// Assert.assertEquals(2, br.getWalls().size());
// mc.close();
// }
// // EndRegion: EdgeCell tests
// }
