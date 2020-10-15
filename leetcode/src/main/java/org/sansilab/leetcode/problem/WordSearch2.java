package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.*;

import java.util.*;

public class WordSearch2 extends AbstractProblem {

    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    int r = 0;
    int c = 0;

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ret = new LinkedList<>();
        if (board.length == 0 || board[0].length == 0) {
            return ret;
        }
        r = board.length;
        c = board[0].length;
        TrieTree tree = new TrieTree();
        for (String w:words) {
            tree.insert(w);
        }
        boolean[][] visited = new boolean[r][c];
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                char ch = board[i][j];
                int ind = ch-'a';
                TrieNode node = tree.root.children[ind];
                if (node != null) {
                    dfs(board, visited, node, i, j, set);
                }
            }
        }
        ret.addAll(set);
        return ret;
    }

    public void dfs(char[][] b, boolean[][] visited, TrieNode node, int cr, int cc, Set<String> ret){

        if (node.isEnd) {
            ret.add(node.word);
        }
        visited[cr][cc] = true;
        for (int i = 0; i < 4; i++) {
            int nr = cr+dr[i];
            int nc = cc+dc[i];
            if (nr<0 || nr>=r || nc<0 || nc>=c || visited[nr][nc]) {
                continue;
            }
            char ch = b[nr][nc];
            int ind = ch-'a';
            TrieNode n = node.children[ind];
            if (n == null) {
                continue;
            }
            dfs(b, visited, n, nr, nc, ret);
        }
        visited[cr][cc] = false;
    }

    class TrieTree {

        public TrieNode root;

        public TrieTree() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int ind = c - 'a';
                TrieNode node = cur.children[ind];
                if (node == null) {
                    node = new TrieNode();
                    cur.children[ind] = node;
                }
                cur = node;
            }
            cur.isEnd = true;
            cur.word = word;
        }
    }

    class TrieNode {
        public static final int CHILDREN_SIZE = 26;

        boolean isEnd;
        TrieNode[] children;
        String word = null;

        public TrieNode() {
            children = new TrieNode[CHILDREN_SIZE];
            isEnd = false;
        }
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionArrayParser(new CharParser()));
        parserList.add(new ArrayParser(new QuotedParser(new StringParser())));
        setOutputParser(new ListParser(new StringParser()));

        addInput("[[\"a\"]]"
                , "[\"a\"]");
        addOutput("[\"a\"]");

        addInput("[[\"a\",\"b\"],[\"c\",\"d\"]]"
                , "[\"abcd\"]");
        addOutput("[]");

        addInput("[\n" +
                "  ['o','a','a','n'],\n" +
                "  ['e','t','a','e'],\n" +
                "  ['i','h','k','r'],\n" +
                "  ['i','f','l','v']\n" +
                "]"
                , "[\"oath\",\"pea\",\"eat\",\"rain\"]");
        addOutput("[\"oath\",\"eat\"]");

    }

    @Override
    Object execute(Object[] input) {
        return findWords((char[][]) input[0], (String[]) input[1]);
    }

    public static void main(String[] args) {
        new WordSearch2().runTest();
    }
}
