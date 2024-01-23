public class Starter {
    public static void main(String[] args) {
        Graph<Integer, String> g = new Graph<>();
        System.out.println("Starting");
        System.out.println(g.dfs());
        Graph<Integer, String>.Node a = g.add(420);
        Graph<Integer, String>.Node b = g.add(21);
        Graph<Integer, String>.Node c = g.add(35);
        Graph<Integer, String>.Node d = g.add(66);
        Graph<Integer, String>.Node e = g.add(-40);
        Graph<Integer, String>.Node f = g.add(17);

        System.out.println("No connected nodes");
        System.out.println(g.dfs());

        g.connect(a,b,"Initial");
        g.connect(b,c,"Second");
        g.connect(c,d,"Next");
        g.connect(d,a,"Fall");
        g.connect(d,e,"Whoop");
        g.connect(d,f,"Folly");
        g.connect(a,f,"Nope");
        System.out.println(g.dfs());

        System.out.println("BFS");
        System.out.println(g.bfs());

        System.out.println("Remove a Node");
        g.remove(b);

        System.out.println(g.dfs());

    }
}
