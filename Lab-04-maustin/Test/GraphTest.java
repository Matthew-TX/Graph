import org.junit.Assert;
import org.junit.Test;

public class GraphTest {

    @Test
    public void addNodeTest() throws Exception {
        Graph<Integer, String> g = new Graph<>();
        Assert.assertEquals("",g.dfs());

        Integer add = 42;
        Graph<Integer, String>.Node<Integer> a = g.add(add);

        Assert.assertEquals(add.toString()+"\n",g.dfs());


    }

    @Test
    public void connectEdgeTest() throws Exception {
        Graph<Integer, String> g = new Graph<>();
        Assert.assertEquals("",g.dfs());

        Graph<Integer, String>.Node<Integer> a = g.add(420);
        Graph<Integer, String>.Node<Integer> b = g.add(21);
        g.connect(a,b,"Initial");
        String actual = g.dfs();
        String expected = actual;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void deleteEdge(){
        Graph<Integer, String> g = new Graph<>();
        Assert.assertEquals("",g.dfs());

        Graph<Integer, String>.Node<Integer> a = g.add(420);
        Graph<Integer, String>.Node<Integer> b = g.add(21);
        g.connect(a,b,"Initial");
        String actual = g.dfs();
        String expected = actual;
        Assert.assertEquals(expected,actual);

        g.removeEdge(g.edgeList.get(0));

        String removedEdge = "Initial";
        Assert.assertEquals(removedEdge,g.removeEdge(g.edgeList.get(0)));
    }

    @Test
    public void deleteNode(){
        Graph<Integer, String> g = new Graph<>();
        Assert.assertEquals("",g.dfs());

        Graph<Integer, String>.Node<Integer> a = g.add(420);
        Graph<Integer, String>.Node<Integer> b = g.add(21);

        Assert.assertEquals( a.getValue().toString() ,g.deleteNode(g.getNodeList().get(0)));

    }

    @Test
    public void bfs() throws Exception {
        Graph<Integer, String> g = new Graph<>();
        Assert.assertEquals("",g.bfs());
        Graph<Integer, String>.Node<Integer> a = g.add(420);
        Graph<Integer, String>.Node<Integer> b = g.add(21);
        Graph<Integer, String>.Node<Integer> c = g.add(35);
        Graph<Integer, String>.Node<Integer> d = g.add(66);
        Graph<Integer, String>.Node<Integer> e = g.add(-40);
        Graph<Integer, String>.Node<Integer> f = g.add(17);
        g.bfs();
        String actual = g.bfs();
        String expected = actual;
        Assert.assertEquals(expected, actual);

        g.connect(a,b,"Initial");
        g.connect(b,c,"Second");
        g.connect(c,d,"Next");
        g.connect(d,a,"Fall");
        g.connect(d,e,"Whoop");
        g.connect(d,f,"Folly");
        g.connect(a,f,"Nope");

        actual = g.bfs();
        expected = actual;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void bfsChoice() throws Exception {
        Graph<Integer, String> g = new Graph<>();
        Assert.assertEquals("The selected node is not in the graph",g.bfsChoice(null));
        Graph<Integer, String>.Node<Integer> a = g.add(420);
        Graph<Integer, String>.Node<Integer> b = g.add(21);
        Graph<Integer, String>.Node<Integer> c = g.add(35);
        Graph<Integer, String>.Node<Integer> d = g.add(66);
        Graph<Integer, String>.Node<Integer> e = g.add(-40);
        Graph<Integer, String>.Node<Integer> f = g.add(17);

        String actual = "";
        for(Graph.Node node : g.getNodeList())
        {
            actual += node.getValue() + "\n";
        }

        Assert.assertEquals(g.dfsChoice(g.getNodeList().get(0)), actual);

        g.connect(a,b,"Initial");
        g.connect(b,c,"Second");
        g.connect(c,d,"Next");
        g.connect(d,a,"Fall");
        g.connect(d,e,"Whoop");
        g.connect(d,f,"Folly");
        g.connect(a,f,"Nope");


        actual = g.bfsChoice(g.getNodeList().get(0));
        String expected = actual;
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void dfs() throws Exception {
        Graph<Integer, String> g = new Graph<>();
        Assert.assertEquals("",g.dfs());
        Graph<Integer, String>.Node<Integer> a = g.add(420);
        Graph<Integer, String>.Node<Integer> b = g.add(21);
        Graph<Integer, String>.Node<Integer> c = g.add(35);
        Graph<Integer, String>.Node<Integer> d = g.add(66);
        Graph<Integer, String>.Node<Integer> e = g.add(-40);
        Graph<Integer, String>.Node<Integer> f = g.add(17);

        String actual = "";
        for(Graph.Node node : g.getNodeList())
        {
            actual += node.getValue() + "\n";
        }

        Assert.assertEquals(g.dfs(), actual);


        g.connect(a,b,"Initial");
        g.connect(b,c,"Second");
        g.connect(c,d,"Next");
        g.connect(d,a,"Fall");
        g.connect(d,e,"Whoop");
        g.connect(d,f,"Folly");
        g.connect(a,f,"Nope");


        actual = g.dfs();
        String expected = actual;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void dfsChoice() throws Exception {
        Graph<Integer, String> g = new Graph<>();
        Assert.assertEquals("",g.dfs());
        Graph<Integer, String>.Node<Integer> a = g.add(420);
        Graph<Integer, String>.Node<Integer> b = g.add(21);
        Graph<Integer, String>.Node<Integer> c = g.add(35);
        Graph<Integer, String>.Node<Integer> d = g.add(66);
        Graph<Integer, String>.Node<Integer> e = g.add(-40);
        Graph<Integer, String>.Node<Integer> f = g.add(17);

        String actual = "";
        for(Graph.Node node : g.getNodeList())
        {
            actual += node.getValue() + "\n";
        }

        Assert.assertEquals(g.dfsChoice(g.getNodeList().get(0)), actual);

        g.connect(a,b,"Initial");
        g.connect(b,c,"Second");
        g.connect(c,d,"Next");
        g.connect(d,a,"Fall");
        g.connect(d,e,"Whoop");
        g.connect(d,f,"Folly");
        g.connect(a,f,"Nope");


        actual = g.dfsChoice(g.getNodeList().get(0));
        String expected = actual;
        Assert.assertEquals(expected,actual);
    }





}
