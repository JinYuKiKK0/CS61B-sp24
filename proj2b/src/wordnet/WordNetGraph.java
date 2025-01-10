package wordnet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class WordNetGraph {
    private HashMap<Integer, LinkedList<Integer>> wordNet;
    private HashMap<Integer, String> IdToWord;
    private HashMap<String, Integer> wordToId;

    public WordNetGraph() {
        wordNet = new HashMap<>();
        wordToId = new HashMap<>();
        IdToWord = new HashMap<>();
    }

    /**
     * create a node to represent a node<id,word> in WordNetGraph
     *
     * @param id
     */
    public void addWord(int id) {
        if (wordNet.containsKey(id)) {
            System.out.println("word existed");
        }
        wordNet.put(id, new LinkedList<>());
    }

    /**
     * find the node by id given and addEdge to this node
     *
     * @param id
     */
    public void addEdges(int id, LinkedList<Integer> IdList) {
        wordNet.put(id, IdList);
    }

    public LinkedList<Integer> hyponyms(int id) {
        return wordNet.get(id);
    }
}
