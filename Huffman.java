import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import TreePackage.BinaryNode;
import TreePackage.BinaryTree;
import TreePackage.BinaryTreeInterface;

public class Huffman {
    private final char LEFT = '0';
    private final char RIGHT = '1';

    private DictionaryInterface<Character, Integer> characterFrequencies;
    private PriorityQueueInterface<FrequencyCharacterPair> frequencyOfCharacters;
    private BinaryTree<FrequencyCharacterPair> huffmanTree;

    public void buildTree(String txt) {
        countCharacterFrequencies(txt);
        enqueueFrequencyCharacterPairs();
        buildHuffmanTree();
    }

    public String encode(String txt) {
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < txt.length(); i++) {
        	List<Character> list = new ArrayList<Character>();
        	char current = txt.charAt(i);
        	search(huffmanTree.getRootNode(), current, list);
        	for(int k = list.size() - 1; k >= 0; k--) {
        		char added = list.get(k);
        		buf.append(added);
        	}
        }

        return buf.toString();
    }

    public String decode(String code) {
        StringBuffer buf = new StringBuffer();

        for(int i = 0; i < code.length();) {
        	BinaryNode<FrequencyCharacterPair> node = huffmanTree.getRootNode();
        	while(i < code.length() && (node != null && node.getData().getCharacter() == null)) {
        		char ec = code.charAt(i++);
        		if(ec == '0') {
        			node = node.getLeftChild();
        		}
        		else {
        			node = node.getRightChild();
        		}
        	}
        	if(node == null || node.getData().getCharacter() == null) {
        		throw new IllegalStateException();
        	}
        	buf.append(node.getData().getCharacter());
        }
        return buf.toString();
    }

    private void countCharacterFrequencies(String txt) {
        characterFrequencies = new SortedArrayDictionary<>();

        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            Integer frequency = characterFrequencies.getValue(c);

            if (frequency == null)
                characterFrequencies.add(c, 1);
            else
                characterFrequencies.add(c, frequency + 1);
        }
    }

    private void enqueueFrequencyCharacterPairs() {
        frequencyOfCharacters = new LinkedPriorityQueue<>();

        Iterator<Character> kit = characterFrequencies.getKeyIterator();

        while (kit.hasNext()) {
            char character = kit.next();
            int frequency = characterFrequencies.getValue(character);

            frequencyOfCharacters.add(new FrequencyCharacterPair(frequency, character));
        }
    }

    private void buildHuffmanTree() {
        huffmanTree = new BinaryTree<>();

        if (frequencyOfCharacters.getSize() < 2)
            return;

        BinaryTreeInterface<FrequencyCharacterPair> leftSubtree = new BinaryTree<>(frequencyOfCharacters.remove());
        BinaryTreeInterface<FrequencyCharacterPair> rightSubtree = new BinaryTree<>(frequencyOfCharacters.remove());
        FrequencyCharacterPair rootData = new FrequencyCharacterPair(
                leftSubtree.getRootData().getFrequency() + rightSubtree.getRootData().getFrequency());

        huffmanTree.setTree(rootData, leftSubtree, rightSubtree);

        while (!frequencyOfCharacters.isEmpty()) {
            leftSubtree = huffmanTree;
            rightSubtree = new BinaryTree<>(frequencyOfCharacters.remove());

            if (leftSubtree.getRootData().getFrequency() > rightSubtree.getRootData().getFrequency()) {
                BinaryTreeInterface<FrequencyCharacterPair> temp = leftSubtree;
                leftSubtree = rightSubtree;
                rightSubtree = temp;
            }

            rootData = new FrequencyCharacterPair(
                    leftSubtree.getRootData().getFrequency() + rightSubtree.getRootData().getFrequency());
            huffmanTree = new BinaryTree<>();
            huffmanTree.setTree(rootData, leftSubtree, rightSubtree);
        }
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();

        buf.append("characterFrequencies:\n");

        Iterator<Character> kit = characterFrequencies.getKeyIterator();

        while (kit.hasNext()) {
            Character character = kit.next();

            buf.append("\t'" + character.toString() + "': " + characterFrequencies.getValue(character) + '\n');
        }

        buf.append("frequencyOfCharacters:\n");

        PriorityQueueInterface<FrequencyCharacterPair> temp = new LinkedPriorityQueue<>();

        while (!frequencyOfCharacters.isEmpty()) {
            FrequencyCharacterPair pair = frequencyOfCharacters.remove();

            buf.append("\t" + pair.getFrequency() + ": '" + pair.getCharacter().toString() + "'\n");
            temp.add(pair);
        }

        frequencyOfCharacters = temp;
        buf.append("huffmanTree:\n");

        Iterator<FrequencyCharacterPair> orderIt = huffmanTree.getLevelOrderIterator();

        while (orderIt.hasNext()) {
            FrequencyCharacterPair pair = orderIt.next();

            buf.append("\t" + pair.getFrequency() + ": '"
                    + (pair.getCharacter() != null ? pair.getCharacter().toString() : ".") + "'\n");
        }

        return buf.toString();
    }

    private boolean search(BinaryNode<FrequencyCharacterPair> node, char value, List<Character> list) {
        // TODO: Implement missing code
    	if(node == null) {
    		return false;
    	}
    	else if(node.getData().getCharacter() != null && node.getData().getCharacter() == value){
    		return true;
    	}
    	else if(search(node.getLeftChild(), value, list)) {
    		return list.add('0');
    	}
    	else if(search(node.getRightChild(), value, list)) {
    		return list.add('1');
    	}
    	else {
        return false;
    	}
    }

    private class FrequencyCharacterPair implements Comparable<FrequencyCharacterPair> {
        private int frequency;
        private Character character;

        public int getFrequency() {
            return frequency;
        }

        public Character getCharacter() {
            return character;
        }

        private FrequencyCharacterPair(int frequency, char character) {
            this.frequency = frequency;
            this.character = character;
        }

        private FrequencyCharacterPair(int frequency) {
            this.frequency = frequency;
            this.character = null;
        }

        @Override
        public int compareTo(FrequencyCharacterPair o) {
            return frequency - o.frequency;
        }
    }
}
