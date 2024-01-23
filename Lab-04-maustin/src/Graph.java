import java.util.ArrayList;

public class Graph<N, E> {
    ArrayList<Node<N>> nodeList = new ArrayList<>();
    ArrayList<Edge<E>> edgeList = new ArrayList<>();
    ArrayList<ArrayList<Node<N>>> breadthList = null;
    ArrayList<Node<N>> choiceList = new ArrayList<>();
    String explored = "explored";
    String unexplored = "unexplored";
    String backEdge = "backEdge";
    String discovery = "discovery";
    String dfsString = "";
    String tab = "";
    boolean foundChoice = false;
    public class Node<N> {
        public String stringNodeValue = "";
        public N value;
        String label = "unexplored";


        Node(N value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public N getValue() {
            return value;
        }

        public void setValue(N value) {
            this.value = value;
        }

        public String getStringNodeValue() {
            return stringNodeValue;
        }

        public void setStringNodeValue(String stringNodeValue) {
            this.stringNodeValue = stringNodeValue;
        }
    }

    public class Edge<E> {
        public String stringEdgeValue = "";
        public E value = null;
        Node<N> from;
        Node<N> to;
        String label = "unexplored";

        public Edge(E value, Node<N> from, Node<N> to) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Node<N> getFrom() {
            return from;
        }

        public void setFrom(Node<N> from) {
            this.from = from;
        }

        public Node<N> getTo() {
            return to;
        }

        public void setTo(Node<N> to) {
            this.to = to;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

    }

    public ArrayList<Edge<E>> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(ArrayList<Edge<E>> edgeList) {
        this.edgeList = edgeList;
    }
    public Node<N> add(N nodeValue) {
        nodeList.add(new Node<>(nodeValue));
        return new Node<N>(nodeValue);
    }

    public void connect(Node<N> from, Node<N> to, E edgeValue) {
        edgeList.add(new Edge<>(edgeValue, from, to));
    }

    public ArrayList<Node<N>> getNodeList() {
        return nodeList;
    }

    public String dfs() {
        dfsString = null;
        String tab = "  ";
        if (nodeList != null) {
            dfsString = "";
            for (Node<N> nNode : nodeList) {
                nNode.setLabel(unexplored);
            }
            for (Edge<E> eEdge : edgeList) {
                eEdge.setLabel(unexplored);
            }
            for (Node<N> nNode : nodeList) {
                if (nNode.getLabel().equals(unexplored)) {
                    dfsString+= nNode.getValue() + "\n";
                    dfsHelper(nNode);
                }
            }
        }
        return dfsString;
    }

    public String dfsChoice(Node n) {
        dfsString = "";
        String tab = "  ";
        if (nodeList != null) {
            for (Node<N> nNode : choiceListCreator(n)) {
                nNode.setLabel(unexplored);
            }
            for (Edge<E> eEdge : edgeList) {
                eEdge.setLabel(unexplored);
            }
            for (Node<N> nNode : choiceListCreator(n)) {
                if (nNode.getLabel().equals(unexplored)) {
                    dfsString+=nNode.getValue() + "\n";
                    dfsHelper(nNode);
                }
            }
        }
        return dfsString;
    }

    public void dfsHelper(Node<N> v) {
        for (Edge e : incidentEdges(v)){
            if (e.getLabel().equals(unexplored)) {
                Node w = opposite(v, e);
                if (w.getLabel().equals(unexplored)) {
                    dfsString+= tab + "Edge: " + e.getValue() + "\n";
                    dfsString+= tab + "Node: " + opposite(v,e).getValue() + "\n";
                    v.setLabel(explored);
                    e.setLabel(discovery);
                    w.setLabel(explored);
                    dfsHelper(w);
                } else {
                    dfsString+= tab + "Edge:" + e.getValue() + "\n";
                    e.setLabel(backEdge);
                }
            }
        }
    }

    public ArrayList<Node<N>> getNodes() {
        return nodeList;
    }

    public String  removeEdge(Edge<E> e) {
        return (String) e.getValue();
    }

    public String bfs() {
        breadthList = new ArrayList<>();
        breadthList.add(new ArrayList<>());
        String bfsString = "";
        String tab = "    ";
        if(!nodeList.isEmpty()){
            //Reset Nodes and Edges
            for (Node<N> nNode : nodeList) {
                nNode.setLabel(unexplored);
            }
            for (Edge<E> eEdge : edgeList) {
                eEdge.setLabel(unexplored);
            }
            //bfsHelper
            // Hello Dr. Block, the section of code is the following
            for (Node n : nodeList) {
                if (n.getLabel().equals("unexplored")) {
                    bfsHelper(n);
                }
            }
            //BFS String
            for (int i = 0; i < breadthList.size(); i++) {
                bfsString += "Depth " + i + "\n";
                for (int j = 0; j < breadthList.get(i).size(); j++) {
                    Node<N> currentNode = breadthList.get(i).get(j);
                    ArrayList<Edge<E>> incidentEdges = incidentEdges(currentNode);
                    bfsString += "Node: " + currentNode.getValue() + "\n";
                    for (Edge<E> e : incidentEdges) {
                        if (e.getLabel().equals("discovery")) {
                            bfsString += tab + "Edge: " + e.getValue() + "\n";
                            bfsString += tab + "Node: " + opposite(currentNode, e).getValue() + "\n";
                            e.setLabel(backEdge);
                        } else {
                            bfsString += tab + "Edge: " + e.getValue() + " is a Back Edge" + "\n";
                        }
                    }
                }
            }
        }

        return bfsString;
    }

    public void bfsHelper(Node<N> s) {
        breadthList.get(0).add(s);
        s.setLabel(explored);
        int i = 0;
        
        while (i<breadthList.size() && !breadthList.get(i).isEmpty() ) {
            for (Node<N> v : breadthList.get(i)) {
                v.setLabel(explored);
                for (Edge<E> e : incidentEdges(v)) {
                    if (e.getLabel().equals(unexplored)) {
                        Node w = opposite(v, e);
                        if (w.getLabel().equals(unexplored)) {
                            w.setLabel(explored);
                            e.setLabel(discovery);
                            breadthList.add(new ArrayList<>());
                            breadthList.get(i + 1).add(w);

                        } else {
                            e.setLabel(backEdge);
                        }
                    }
                }
            }
               i++;
        }
    }

    public ArrayList<Node<N>> choiceListCreator(Node choice){
        if(!nodeList.isEmpty()){
            int choiceIndex = 0;
            foundChoice = false;
            choiceList = new ArrayList<>();
            //Creating a New Array for the selected node
            for (Node n : nodeList) {
                if (n.getValue() == choice.getValue()) {
                    foundChoice = true;
                }
                if (foundChoice) {
                    choiceList.add(n);
                } else {
                    choiceIndex++;
                }
            }
            //Adding nodes before the selected node in the node list
            for (int i = 0; i < choiceIndex; i++) {
                choiceList.add(nodeList.get(i));
            }
        }

        return  choiceList;
    }

    public String bfsChoice(Node<N> choice) {
        if(choice!= null){

        }
        String bfsString = "";
        String tab = "    ";
        breadthList = new ArrayList<>();
        breadthList.add(new ArrayList<>());
        choiceListCreator(choice);
        if (foundChoice) {
            //Reset Nodes and Edges
            for (Node<N> nNode : nodeList) {
                nNode.setLabel(unexplored);
            }
            for (Edge<E> eEdge : edgeList) {
                eEdge.setLabel(unexplored);
            }
            //bfsHelper
            for (Node <N> n : choiceList) {
                if (n.getLabel().equals(unexplored)) {
                    bfsHelper(n);
                }
            }
            //BFS String
            bfsString = "BFS" + "\n";
            for (int i = 0; i < breadthList.size(); i++) {
                bfsString += "Depth " + i + "\n";
                for (int j = 0; j < breadthList.get(i).size(); j++) {
                    Node<N> currentNode = breadthList.get(i).get(j);
                    ArrayList<Edge<E>> incidentEdges = incidentEdges(currentNode);
                    bfsString += "Node: " + currentNode.getValue() + "\n";
                    for (Edge<E> e : incidentEdges) {
                        if (e.getLabel().equals("discovery")) {
                            bfsString += tab + "Edge: " + e.getValue() + "\n";
                            bfsString += tab + "Node: " + opposite(currentNode, e).getValue() + "\n";
                            e.setLabel(backEdge);
                        } else {
                            bfsString += tab + "Edge: " + e.getValue() + " is a Back Edge" + "\n";
                        }
                    }
                }
            }
            foundChoice = false;
            return bfsString;
        }
        //If the chosen node doesn't exist
        else {
            return "The selected node is not in the graph";
        }
    }

    public Node<N> opposite(Node<N> n, Edge<E> e) {
        if (e.getFrom().getValue() == n.getValue()) {
            return e.getTo();
        } else if (e.getTo().getValue() == n.getValue()) {
            return e.getFrom();
        }
        return null;
    }

    public ArrayList<Edge<E>> incidentEdges(Node<N> v) {
        ArrayList<Edge<E>> incidentEdge = new ArrayList<>();
        for (Edge<E> e : edgeList) {
            if (e.getTo().getValue() == v.getValue() || e.getFrom().getValue() == v.getValue()) {
                incidentEdge.add(e);
            }
        }
        return incidentEdge;
    }

    public  String deleteEdge(Edge<E> e) {
        int i = 0;
        String deleteValue = (String) e.getValue();
        for(Edge<E> edge : edgeList)
        {
            if(edgeList.get(i)==e.getValue()){
                edgeList.remove(i);
            }
            else {
                i++;
            }
        }
        return deleteValue;
    }

    public String deleteNode(Node<N> n){
        int i = 0;
        N deleteValue = n.getValue();
        for (Node node : nodeList)
        {
            if (n.getValue() == node.getValue()){
                for(Edge e : incidentEdges(n)){
                    deleteEdge(e);
                }
                nodeList.remove(i);
            }
            else {
                i++;
            }
        }
        return deleteValue.toString();
    }

}
